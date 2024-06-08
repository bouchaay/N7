package fr.n7.stl.block.ast.classElement;

import fr.n7.stl.block.ast.expression.*;

import fr.n7.stl.block.ast.expression.Expression;
import fr.n7.stl.block.ast.instruction.Instruction;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.type.PartialType;
import fr.n7.stl.block.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.util.Logger;
import fr.n7.stl.util.Pair;

public class Attribut implements ContentInterface {

    private AccessRight visibility;
    private State state; 
    private Pair<String, PartialType> identifiant;
    private Type type;
    private Expression value;

    private int offset;
    private Register register;


    public Attribut(AccessRight _visibility, State _state, Type _type, Pair<String, PartialType> _identifiant, Expression _value) {
        this.visibility = _visibility;
        this.state = _state;
        this.identifiant = _identifiant;
        this.type = _type;
        this.value = _value;
    }

    public Attribut(AccessRight _visibility, State _state, Type _type, Pair<String, PartialType> _identifiant) {
        this.visibility = _visibility;
        this.state = _state;
        this.identifiant = _identifiant;
        this.type = _type;
        this.value = null;
    }
    
   
    
    

    public String toString() {
        return this.visibility.toString() + " " + this.state.toString() + " " + this.type + " " + this.identifiant.getLeft() + (this.value != null ? " = " + this.value : "") + ";";
    }


	@Override
	public String getName() {
		
		return this.identifiant.getLeft();
	}

	@Override
	public Type getType() {
		
		return this.type;
	}
	
	 public AccessRight getAccessRight() {
	        return this.visibility;
	   }

	@Override
	public boolean collectAndBackwardResolve(HierarchicalScope<Declaration> _scope) {
		
        if (_scope.accepts(this)) {
            _scope.register(this);
            if(this.value != null) {
                return this.value.collectAndBackwardResolve(_scope);
            } else {
                return true;
            }
        } else {
            Logger.error("Error : Attribut " + this.identifiant.getLeft() + " already exists in the scope");
            return false;
        }
	}

	@Override
	public boolean fullResolve(HierarchicalScope<Declaration> _scope) {

        if (this.type.resolve(_scope)){
            if (this.value != null) {
                return this.value.fullResolve(_scope);
            } else {
                return true;
            }
        } else {
            Logger.error("Error : Attribut " + this.identifiant.getLeft() + " type not resolved");
            return false;
        }
		
	}

	

	@Override
	public boolean checkType() {
		
        if (this.value != null) {
            if(this.value.getType().compatibleWith(this.type)) {
                return true;
            } else {
                Logger.error("Error : Attribut " + this.identifiant.getLeft() + " value type not compatible with the attribut type");
                return false;
            }
        } else {
            return true;
        }
	}

    
    @Override
	public int allocateMemory(Register _register, int _offset) {
		this.register = _register;
        this.offset = _offset;

        if (this.state == State.Static || this.state == State.StaticFinal) {
            return this.value.getType().length();
        } else {
            return 0;
        }
		
	}
    
    @Override
	public Fragment getCode(TAMFactory _factory) {
		
        Fragment _fragment = _factory.createFragment();
        if(this.state == State.Static || this.state == State.StaticFinal) {
            if(this.value != null) {
                _fragment.append(this.value.getCode(_factory));
                _fragment.add(_factory.createStore(this.register, this.offset, this.value.getType().length()));
            }else{
                _fragment.add(_factory.createPush(this.type.length()));
                _fragment.add(_factory.createStore(this.register, this.offset, this.type.length()));
            }
        }
        return _fragment;
		
	}

	


    
    
}
