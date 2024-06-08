package fr.n7.stl.block.ast.element;

import fr.n7.stl.block.*;
import fr.n7.stl.block.ast.instruction.declaration.ParameterDeclaration;
import fr.n7.stl.block.ast.Block;
import fr.n7.stl.util.Logger;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.scope.SymbolTable;
import fr.n7.stl.block.ast.type.Instance;
import fr.n7.stl.block.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import java.util.List;
import java.util.ArrayList;
import fr.n7.stl.block.ast.classElement.*;
import fr.n7.stl.block.ast.classElement.Attribut;
import fr.n7.stl.block.ast.classElement.Constructeur;
import fr.n7.stl.block.ast.classElement.ContentInterface;
import fr.n7.stl.block.ast.classElement.Methode;


public class NormalClass implements ClasseInterface, Declaration {

    private String name;
    private List<ContentInterface> contents;
    private SymbolTable table;

    public NormalClass(String _name, List<ContentInterface> _contents) {
        this.name = _name;
        this.contents = _contents;
    }

    public String toString() {
        String res = "public Class " + this.name + " {\n";
        for (ContentInterface c : this.contents) {
            res += c.toString() + "\n";
        }
        res += "}\n";
        return res;
    }

    public String getName() {
        return this.name;
    }

    public Type getType() {
        return new Instance(name);
    }
    
    public List<ContentInterface> getContent(){
    	return this.contents;
    }
    
    public SymbolTable getElementsTable() {
		return this.table;
	}

    public List<Attribut> getAttributs() {
        List<Attribut> res = new ArrayList<Attribut>();
        for (ContentInterface c : this.contents) {
            if (c instanceof Attribut) {
                res.add((Attribut) c);
            }
        }
        return res;
    }

    public List<Methode> getMethods() {
        List<Methode> res = new ArrayList<Methode>();
        for (ContentInterface c : this.contents) {
            if (c instanceof Methode) {
                res.add((Methode) c);
            }
        }
        return res;
    }

    public List<Constructeur> getConstructors() {
        List<Constructeur> res = new ArrayList<Constructeur>();
        for (ContentInterface c : this.contents) {
            if (c instanceof Constructeur) {
                res.add((Constructeur) c);
            }
        }
        return res;
    }

    public boolean collect(HierarchicalScope<Declaration> _scope) {
    	if (((HierarchicalScope<Declaration>) _scope).accepts(this)) {
			_scope.register(this);
			SymbolTable.classe = this;
			SymbolTable.classes.add(this);
			this.table = new SymbolTable(_scope);
			boolean _result = true;
			for (int i = contents.size() - 1; i >= 0; i--) {
				_result = _result && this.contents.get(i).collectAndBackwardResolve(table);
			}
			return _result;
		} else {
			Logger.error("The class identifier " + this.name + " is already defined.");
			return false;
		}
    }

    public boolean resolve(HierarchicalScope<Declaration> _scope) {
        SymbolTable.classe = this;
        boolean res = true;
        for (ContentInterface c : this.contents) {
            res = res && c.fullResolve(_scope);
        }
        return res;
    }

    public boolean checkType() {
        SymbolTable.classe = this;
        boolean res = true;
        for (ContentInterface c : this.contents) {
            res = res && c.checkType();
        }
        return res;
    }

    public int allocateMemory(Register _register, int _offset) {
        return 0;
    }

    public Fragment getCode(TAMFactory _factory) {
        SymbolTable.classe = this;
        Fragment fragment = _factory.createFragment();
        for (ContentInterface c : this.contents) {
            fragment.append(c.getCode(_factory));
        }
        return fragment;
    }

}
