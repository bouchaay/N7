/**
 * 
 */
package fr.n7.stl.block.ast.type;
import java.util.Iterator;
import java.util.List;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.type.declaration.LabelDeclaration;

/**
 * @author Marc Pantel
 *
 */
public class EnumerationType implements Type, Declaration {
	
	private String name;
	
	private List<LabelDeclaration> labels;

	/**
	 * 
	 */
	public EnumerationType(String _name, List<LabelDeclaration> _labels) {
		this.name = _name;
		this.labels = _labels;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String _result = "enum" + this.name + " { ";
		Iterator<LabelDeclaration> _iter = this.labels.iterator();
		if (_iter.hasNext()) {
			_result += _iter.next();
			while (_iter.hasNext()) {
				_result += " ," + _iter.next();
			}
		}
		return _result + " }";
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.type.Type#equalsTo(fr.n7.stl.block.ast.type.Type)
	 */
	@Override
	public boolean equalsTo(Type _other) {
		if (_other instanceof EnumerationType) {
			EnumerationType _inter = (EnumerationType) _other;
			if (this.labels.size() != _inter.labels.size()) {
				return false;
			} else {
				for (int i=0; i < this.labels.size(); i++) {
					if (!nameAndTypeEqual(this.labels.get(i), _inter.labels.get(i))) {
						return false;
					}
				}
				return true;
			}
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.type.Type#compatibleWith(fr.n7.stl.block.ast.type.Type)
	 */
	@Override
	public boolean compatibleWith(Type _other) {
		return _other instanceof EnumerationType;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.type.Type#merge(fr.n7.stl.block.ast.type.Type)
	 */
	@Override
	public Type merge(Type _other) {
		if (compatibleWith(_other)) {
			EnumerationType _inter = (EnumerationType) _other;
			for (LabelDeclaration _label : _inter.labels) {
				if (!this.labels.contains(_label)) {
					this.labels.add(_label);
				}
			}
			return this;
		} else {
			return AtomicType.ErrorType;

		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.type.Type#length()
	 */
	@Override
	public int length() {
		return this.labels.size();
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.type.Type#resolve(fr.n7.stl.block.ast.scope.Scope)
	 */
	@Override
	public boolean resolve(HierarchicalScope<Declaration> _scope) {
		return true;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.scope.Declaration#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.scope.Declaration#getType()
	 */
	@Override
	public Type getType() {
		return this;
	}

	/* --------------------------------------------------------------------------- */
	/* ------------------------ Fonctions Intermédiaires ------------------------ */
	/* -------------------------------------------------------------------------- */

	/** Vérifie si les noms et les types des labels sont égaux
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean nameAndTypeEqual(LabelDeclaration a, LabelDeclaration b) {
		return a.getName().equals(b.getName()) && a.getType().equals(b.getType());
	}
}
