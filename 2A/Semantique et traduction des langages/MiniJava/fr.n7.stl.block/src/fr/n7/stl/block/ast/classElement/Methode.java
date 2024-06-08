package fr.n7.stl.block.ast.classElement;

import fr.n7.stl.block.ast.expression.Expression;
import fr.n7.stl.block.ast.instruction.declaration.ParameterDeclaration;
import fr.n7.stl.block.ast.Block;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.scope.SymbolTable;
import fr.n7.stl.block.ast.type.AtomicType;
import fr.n7.stl.block.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.util.Logger;


public class Methode implements ContentInterface {

    private AccessRight visibility;
    private State state;
    private Signature signature;
    private Block body;

    private int offset;
	private SymbolTable parametersTable;

    

    public Methode(AccessRight _visibility, State _state, Signature _signature, Block _body) {
        this.visibility = _visibility;
        this.state = _state;
        this.signature = _signature;
        this.body = _body;
    }
    
    public Methode(AccessRight _visibility, State _state, Signature _signature) {
        this.visibility = _visibility;
        this.state = _state;
        this.signature = _signature;
        this.body = null;
    }

    public String toString() {
        return this.visibility.toString() + " " + this.state.toString() + " " + this.signature + " " + this.body;
    }
   public AccessRight getVisibility() {
	   return this.visibility;
   }

    public String getName() {
        return this.signature.getName();
    }

    public Type getType() {
        return this.signature.getType();
    }

    public boolean collectAndBackwardResolve(HierarchicalScope<Declaration> _scope) {
       
        String name = signature.getName();
        if (this.signature.getParameters() != null) {
            for (ParameterDeclaration p : this.signature.getParameters()) {
                name += p.getType().toString();
            }
        }
        signature.setName(name);
        if (((HierarchicalScope<Declaration>) _scope).accepts(this)) {
            _scope.register(this);
            SymbolTable tableParametres = new SymbolTable(_scope);
            boolean result = true;
            if (this.signature.getParameters() != null) {
                for (ParameterDeclaration d : this.signature.getParameters()) {
                    tableParametres.register(d);
                }
            }
            this.parametersTable = tableParametres;
            if (this.body != null) {
            	result = this.body.collect(tableParametres);
            } else {result = true;}
            return result;
        } else {
            Logger.error("The method " + this.signature.getName() + " is already defined in the class.");
            return false;
        }
    }

    
    public boolean fullResolve(HierarchicalScope<Declaration> _scope) {
        SymbolTable.methode = this;
        if (this.body != null) {
        	return this.body.resolve(_scope);	
        }else {
        	return true;
        }
        
    }
    
    
    public AccessRight getAccessRight() {
    	return this.visibility;
    }

    
    public boolean checkType() {
        boolean _result = true;
        SymbolTable.methode = this;
        if (this.signature.getParameters() != null) {
            for(ParameterDeclaration parameterDeclaration : this.signature.getParameters()) {
                if (parameterDeclaration.getType().equalsTo(AtomicType.ErrorType)) {
                    Logger.error(parameterDeclaration + " has an error type.");
                    _result = false;
                }
            }
        }
        if (this.body != null) {
        	_result = _result && body.checkType();
        }
        return _result;
    }

    
    public int allocateMemory(Register _register, int _offset) {
        this.offset = _offset;
        for (ParameterDeclaration p : this.signature.getParameters()) {
            offset += p.getType().length();
        }
        this.body.allocateMemory(Register.LB, offset);
        return 0;
    }

    
    public Fragment getCode(TAMFactory _factory) {
        Fragment fragment = _factory.createFragment();
        if (this.body != null) {
        	fragment.append(this.body.getCode(_factory));
        	
        }
        
        
        if(this.signature.getType().equalsTo(AtomicType.VoidType)) {
            fragment.add(_factory.createReturn(0, this.offset));
        }
       

             
        return fragment;
    }
}
