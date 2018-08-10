package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.*;


public class ASTPrintLnExp extends ASTExp {

    ASTExp exp;

    public ASTPrintLnExp(ASTExp exp) {
        this.exp = exp;
    }

    public ASTExp getExp() {
        return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTPrintLnExp(this, state);
    }

    @Override
    public String toString() {
        return "println(" + exp + ")";
}
}