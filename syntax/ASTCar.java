package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.*;



public class ASTCar extends ASTExp {
    ASTExp pair;

    public ASTCar(ASTExp pair) {
        this.pair = pair;
    }

    public ASTExp getPair() {
        return pair;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTCar(this, state);
    }

    @Override
    public String toString() {
        return "car" + "(" + pair + ")";
    }
}