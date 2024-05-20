/**
 * 
 */
package fr.n7.stl.block.ast.instruction;

import java.util.Optional;

import fr.n7.stl.block.ast.Block;

import fr.n7.stl.block.ast.expression.Expression;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.type.AtomicType;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for a conditional
 * instruction.
 * 
 * @author Marc Pantel
 *
 */
public class Conditional implements Instruction {

	protected Expression condition;
	protected Block thenBranch;
	protected Block elseBranch;

	public Conditional(Expression _condition, Block _then, Block _else) {
		this.condition = _condition;
		this.thenBranch = _then;
		this.elseBranch = _else;
	}

	public Conditional(Expression _condition, Block _then) {
		this.condition = _condition;
		this.thenBranch = _then;
		this.elseBranch = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "if (" + this.condition + " )" + this.thenBranch
				+ ((this.elseBranch != null) ? (" else " + this.elseBranch) : "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.n7.stl.block.ast.instruction.Instruction#collect(fr.n7.stl.block.ast.scope
	 * .Scope)
	 */
	@Override
	public boolean collectAndBackwardResolve(HierarchicalScope<Declaration> _scope) {
		if (elseBranch != null) {
			return this.condition.collectAndBackwardResolve(_scope) && this.thenBranch.collect(_scope)
					&& this.elseBranch.collect(_scope);
		} else {
			return this.condition.collectAndBackwardResolve(_scope) && this.thenBranch.collect(_scope);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.n7.stl.block.ast.instruction.Instruction#resolve(fr.n7.stl.block.ast.scope
	 * .Scope)
	 */
	@Override
	public boolean fullResolve(HierarchicalScope<Declaration> _scope) {
		if (elseBranch != null) {
			return this.condition.fullResolve(_scope) && this.thenBranch.resolve(_scope)
					&& this.elseBranch.resolve(_scope);
		} else {
			return this.condition.fullResolve(_scope) && this.thenBranch.resolve(_scope);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.n7.stl.block.ast.Instruction#checkType()
	 */
	@Override
	public boolean checkType() {
		if (elseBranch != null) {
			return this.condition.getType().compatibleWith(AtomicType.BooleanType) && this.thenBranch.checkType()
					&& this.elseBranch.checkType();
		} else {
			return this.condition.getType().compatibleWith(AtomicType.BooleanType) && this.thenBranch.checkType();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.n7.stl.block.ast.Instruction#allocateMemory(fr.n7.stl.tam.ast.Register,
	 * int)
	 */
	@Override
	public int allocateMemory(Register _register, int _offset) {
		this.thenBranch.allocateMemory(_register, _offset);
		if (elseBranch != null) {
			this.elseBranch.allocateMemory(_register, _offset);
		}

		return _offset;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.n7.stl.block.ast.Instruction#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override

	public Fragment getCode(TAMFactory _factory) {
		Fragment fragment = _factory.createFragment();

		// Génération du code pour évaluer la condition
		fragment.append(this.condition.getCode(_factory));

		// Ajout d'une instruction de saut conditionnel pour sauter au bloc "else" si la
		// condition est fausse
		fragment.add(_factory.createJumpIf("else" + _factory.createLabelNumber(), 0));

		// Génération du code pour le bloc "then"
		fragment.append(this.thenBranch.getCode(_factory));

		// Si un bloc "else" est présent, ajouter une instruction de saut inconditionnel
		// à la fin du bloc "else"
		if (this.elseBranch != null) {
			fragment.add(_factory.createJump("end" + _factory.createLabelNumber()));
		}

		// Ajout d'une étiquette pour le bloc "else"
		fragment.addSuffix("else" + _factory.createLabelNumber() );

		// Génération du code pour le bloc "else" s'il est présent
		if (this.elseBranch != null) {
			fragment.append(this.elseBranch.getCode(_factory));
		}

		// Ajout d'une étiquette pour la fin du bloc "else" ou de la conditionnelle
		fragment.addSuffix("end" + _factory.createLabelNumber());

		return fragment;
	}

}
