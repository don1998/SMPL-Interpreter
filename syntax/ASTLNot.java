package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ASTLNot extends ASTExp {
    ASTExp exp;

    public ASTLNot(ASTExp e) {
        exp = e;
    }

    public ASTExp getExp() {
        return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitASTLNot(this, arg);
    }

    @Override
    public String toString() {
        return "not " + exp.toString();
    }
}
