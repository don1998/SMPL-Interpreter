package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.*;



public class ASTCdr extends ASTExp {
    ASTExp pair;

    public ASTCdr(ASTExp pair) {
        this.pair = pair;
    }

    public ASTExp getPair() {
        return pair;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTCdr(this, state);
    }

    @Override
    public String toString() {
        return "cdr" + "(" + pair + ")";
    }
}