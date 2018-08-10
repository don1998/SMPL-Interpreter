package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.*;


public class ASTPrintExp extends ASTExp {

    ASTExp exp;

    public ASTPrintExp(ASTExp exp) {
        this.exp = exp;
    }

    public ASTExp getExp() {
        return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTPrintExp(this, state);
    }

    @Override
    public String toString() {
        return "print(" + exp + ")";
}
}