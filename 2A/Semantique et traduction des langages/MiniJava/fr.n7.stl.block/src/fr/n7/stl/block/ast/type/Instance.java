package fr.n7.stl.block.ast.type;

import java.util.Iterator;
import java.util.List;

import javax.swing.text.html.HTMLDocument.BlockElement;

import fr.n7.stl.block.ast.classElement.*;
import fr.n7.stl.block.ast.element.NormalClass;
import fr.n7.stl.block.ast.SemanticsUndefinedException;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.scope.Scope;
import fr.n7.stl.block.ast.scope.SymbolTable;
import fr.n7.stl.util.Logger;

public class Instance implements Type, Scope<ContentInterface> {

    private String name;

    private NormalClass classe;

    public Instance(String _name) {
        this.name = _name;
        this.classe = null;
    }

    public Instance(String _name, NormalClass _class) {
        this.name = _name;
        this.classe = _class;
    }

    public NormalClass getClassDeclaration() {
        return this.classe;
    }

    public void setClassDeclaration(NormalClass d) {
        this.classe = d;
    }

    public List<ContentInterface> getAllElements() {
        return this.classe.getContent();
    }

    public List<Attribut> getAttributes() {
        return this.classe.getAttributs();
    }

    public List<Methode> getMethods() {
        return this.classe.getMethods();
    }

    public List<Constructeur> getConstructors() {
        return this.classe.getConstructors();
    }

    @Override
    public boolean equalsTo(Type _other) {
        boolean _result = true;
        if (_other instanceof Instance) {
            for (NormalClass c : SymbolTable.classes) {
                if (c.getName().equals(this.name)) {
                    if (_other.length() == length()) {
                        for (int i = 0; i < length(); i++) {
                            if (!this.getAllElements().get(i).equals(c.getContent().get(i))) {
                                _result = false;
                            }
                        }
                        _result = true;
                    } else {
                        _result = false;
                    }
                }
            }
        } else {
            _result = false;
        }
        return _result;
    }

    @Override
    public boolean compatibleWith(Type _other) {
        return equalsTo(_other);
    }

    @Override
    public Type merge(Type _other) {
        Type _result;
        if (_other instanceof Instance) {
            _result = this;
        } else {
            Logger.error(_other + " has not the same type of " + this + ".");
            _result = AtomicType.ErrorType;
        }
        return _result;
    }

    @Override
    public int length() {
        int _result = 0;
        for (NormalClass c : SymbolTable.classes) {
            if (c.getName().equals(this.name)) {
                /**for (MethodDeclaration m : c.getClassMethods()) {
                  _result += m.getType().length();
                }*/
                for (Attribut a : c.getAttributs()) {
                    _result += a.getType().length();
                }
            }
        }
        return _result;
    }

    @Override
    public boolean resolve(HierarchicalScope<Declaration> _scope) {
        Declaration d = _scope.get(this.name);
        boolean _result = true;
        if (d instanceof NormalClass) {
            this.classe = (NormalClass) d;
        } else {
            Logger.error("The class identifier " + this.name + " is not defined.");
            return false;
        }
        return _result;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public ContentInterface get(String _name) {
        boolean _found = false;
        Iterator<ContentInterface> _iter = this.getAllElements().iterator();
        ContentInterface _current = null;
        while (_iter.hasNext() && (!_found)) {
            _current = _iter.next();
            _found = _found || _current.getName().contentEquals(_name);
        }
        if (_found) {
            return _current;
        } else {
            return null;
        }
    }

    @Override
    public boolean contains(String _name) {
        boolean _result = false;
        Iterator<ContentInterface> _iter = this.classe.getContent().iterator();
        while (_iter.hasNext() && (!_result)) {
            _result = _result || _iter.next().getName().contentEquals(_name);
        }
        return _result;
    }

    @Override
    public boolean accepts(ContentInterface _declaration) {
        return !this.contains(_declaration.getName());
    }

    @Override
    public void register(ContentInterface _declaration) {
        throw new SemanticsUndefinedException("register undifined");
    }

}
