package fr.n7.stl.block.ast.classElement;

import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.type.PartialType;
import fr.n7.stl.util.Pair;

import java.util.Iterator;
import java.util.List;



import fr.n7.stl.block.ast.instruction.declaration.ParameterDeclaration;
import fr.n7.stl.block.ast.type.Type;

public class Signature implements Declaration {

    private Pair<String,PartialType> name;
    private Type type;
    private List<ParameterDeclaration> parameters;

    public Signature(Pair<String,PartialType> _name, Type _type, List<ParameterDeclaration> _parameters) {
        this.name = _name;
        this.type = _type;
        this.parameters = _parameters;
    }
    
    public Signature(Pair<String,PartialType> _name, Type _type) {
        this.name = _name;
        this.type = _type;
        this.parameters = null;
    }

    public String toString() {
        String _result = type.toString() + " " + this.name.getLeft() + "(";
        if (parameters != null) {
            Iterator<ParameterDeclaration> _iter = this.parameters.iterator();
            if (_iter.hasNext()) {
                _result += _iter.next();
                while (_iter.hasNext()) {
                    _result += " ," + _iter.next();
                }
            }
        }
        return _result + ")";
       
    }

    @Override
    public String getName() {
        return this.name.getLeft();
    }

    @Override
    public Type getType() {
        return this.type;
    }

    public List<ParameterDeclaration> getParameters() {
        return this.parameters;
    }

    public void setName(String _name) {
        this.name.setLeft(_name);
    }
    
}
