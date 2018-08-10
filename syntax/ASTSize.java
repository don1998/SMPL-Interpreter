package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.*;

public class ASTSize extends ASTExp {

    ASTExp vec;

    public ASTSize(ASTExp vec) {
        this.vec = vec;
    }

    public ASTExp getVector() {
        return vec;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTSize(this, state);
    }

    @Override
    public String toString() {
        return "size(" + vec + ")";
    }
}