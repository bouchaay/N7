package fr.n7.stl.block.ast.element;

import fr.n7.stl.block.ast.instruction.Instruction;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.type.AtomicType;
import fr.n7.stl.block.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import java.util.List;
import fr.n7.stl.block.ast.instruction.declaration.ParameterDeclaration;
import fr.n7.stl.block.ast.Block;
import fr.n7.stl.util.Logger;

public class Main implements ClasseInterface {
    
    private List<ParameterDeclaration> parameters;
    private Block body;

    public Main(List<ParameterDeclaration> _parameters, Block _body) {
        this.parameters = _parameters;
        this.body = _body;
    }

    public String toString() {
        String res = "public Class Main {\n";
        res += "public static void main(";
        for (ParameterDeclaration p : this.parameters) {
            res += p.toString() + ", ";
        }
        if (this.parameters.size() > 0) {
            res = res.substring(0, res.length() - 2);
        }
        res += ") {\n";
        res += this.body.toString();
        res += "}\n";
        return res;
    }

    public boolean collect(HierarchicalScope<Declaration> _scope) {
        for (ParameterDeclaration p : this.parameters) {
            _scope.register(p);
        }
        return this.body.collect(_scope);
    }

    public boolean resolve(HierarchicalScope<Declaration> _scope) {
        return this.body.resolve(_scope);
    }

    public boolean checkType() {
        boolean res = true;
        for (ParameterDeclaration p : this.parameters) {
            if (p.getType().equalsTo(AtomicType.ErrorType)) {
                Logger.error("Error in Main : parameter type is not correct");
                res = false;
            }
        }
        return res && this.body.checkType();
    }

    public int allocateMemory(Register _register, int _offset) {
        this.body.allocateMemory(_register, _offset);
        return 1;
    }

    public Fragment getCode(TAMFactory _factory) {
        Fragment res = _factory.createFragment();
        res.append(this.body.getCode(_factory));
        res.addPrefix("main");
        res.add(_factory.createHalt());
        res.addSuffix("endmain");
        return res;
    }
}
