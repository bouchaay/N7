package fr.n7.stl.block.ast.expression.accessible;

import fr.n7.stl.block.ast.SemanticsUndefinedException;

import fr.n7.stl.block.ast.element.NormalClass;
import fr.n7.stl.block.ast.expression.AbstractAccess;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

public class ThisAccess extends AbstractAccess {

    protected NormalClass declaration;

    public ThisAccess(NormalClass _declaration) {
        this.declaration = _declaration;
    }

    @Override
    protected Declaration getDeclaration() {
        return this.declaration;
    }

    public NormalClass getClassDeclaration() {
        return this.declaration;
    }

    @Override
    public Fragment getCode(TAMFactory _factory) {
        Fragment _result = _factory.createFragment();
        return _result;
    }

}
