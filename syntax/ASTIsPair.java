package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.*;


public class ASTIsPair extends ASTExp {
    ASTExp pair;

    public ASTIsPair(ASTExp pair) {
        this.pair = pair;
    }

    public ASTExp getPair() {
        return pair;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTIsPair(this, state);
    }

    @Override
    public String toString() {
        return "pair?" + "(" + pair + ")";
    }
}