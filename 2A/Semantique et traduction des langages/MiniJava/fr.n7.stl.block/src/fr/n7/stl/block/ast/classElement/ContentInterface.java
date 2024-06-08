package fr.n7.stl.block.ast.classElement;

import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;


public interface ContentInterface extends Declaration {

    public boolean collectAndBackwardResolve(HierarchicalScope<Declaration> _scope);

    public boolean fullResolve(HierarchicalScope<Declaration> _scope);

    public boolean checkType();

    public int allocateMemory(Register _register, int _offset);

    public Fragment getCode(TAMFactory _factory);
    
}
