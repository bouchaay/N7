/**
 * 
 */
package fr.n7.stl.block.ast.expression.assignable;

import fr.n7.stl.block.ast.classElement.Attribut;
import fr.n7.stl.block.ast.classElement.Methode;
import fr.n7.stl.block.ast.element.NormalClass;
import fr.n7.stl.block.ast.expression.AbstractField;
import fr.n7.stl.block.ast.expression.BinaryOperator;
import fr.n7.stl.block.ast.instruction.declaration.VariableDeclaration;
import fr.n7.stl.block.ast.scope.SymbolTable;
import fr.n7.stl.block.ast.type.NamedType;
import fr.n7.stl.block.ast.type.declaration.FieldDeclaration;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import java_cup.runtime.SymbolFactory;

/**
 * Abstract Syntax Tree node for an expression whose computation assigns a field in a record.
 * @author Marc Pantel
 *
 */
public class FieldAssignment extends AbstractField implements AssignableExpression {

	/**
	 * Construction for the implementation of a record field assignment expression Abstract Syntax Tree node.
	 * @param _record Abstract Syntax Tree for the record part in a record field assignment expression.
	 * @param _name Name of the field in the record field assignment expression.
	 */
	public FieldAssignment(AssignableExpression _record, String _name) {
		super(_record, _name);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.impl.FieldAccessImpl#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment _result = _factory.createFragment();
		FieldDeclaration _field = null;
		int _value = 0;

		_result.append(this.record.getCode(_factory));
		if (recordType == null) {
			if (SymbolTable.classe != null) {
				for (Attribut a : SymbolTable.classe.getAttributs()) {
					if (a.getName().equals(this.name)) {
						_result.add(_factory.createStore(Register.HT, 0, a.getType().length()));
					}
				}
				
				
			} else {
				for (NormalClass c : SymbolTable.classes) {
					for (Attribut a : c.getAttributs()) {
						if (a.getName().equals(this.name)) {
							_value = a.getType().length();
							_result.add(_factory.createLoadL(_value));
							_result.add(TAMFactory.createBinaryOperator(BinaryOperator.Add));
							_result.add(_factory.createStoreI(a.getType().length()));
						}
					}
					for (Methode m : c.getMethods()) {
						if (m.getName().equals(this.name)) {
							_value = m.getType().length();
							_result.add(_factory.createLoadL(_value));
							_result.add(TAMFactory.createBinaryOperator(BinaryOperator.Add));
							_result.add(_factory.createStoreI(m.getType().length()));
						}
					}
				}
			}
		} else {
			for (FieldDeclaration fieldDeclaration: recordType.getFields()) {
				
				if (fieldDeclaration.getName().equals(name)) {
					_field = fieldDeclaration;
					break;
				}
				_value += fieldDeclaration.getType().length();
			}

			if (this.field.getType() instanceof NamedType) {
				_result = _factory.createFragment();
				_result.add(_factory.createLoadL(VariableDeclaration.namedTypeOffset));
				_result.add(_factory.createLoadL(_value));
				_result.add(TAMFactory.createBinaryOperator(BinaryOperator.Add));
			} else {
				_result.add(_factory.createLoadL(_value));
				_result.add(TAMFactory.createBinaryOperator(BinaryOperator.Add));
				_result.add(_factory.createStoreI(field.getType().length()));
			}	
		}
			
		return _result;
	}
	
}
