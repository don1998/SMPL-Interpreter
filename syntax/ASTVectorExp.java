package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;


public class ASTVectorExp extends ASTExp {

    ASTExp vector, index;

    public ASTVectorExp(ASTExp vector, ASTExp index) {
        this.vector = vector;
        this.index = index;
    }

    public ASTExp getVec() {
        return vector;
    }

    public ASTExp getIndex() {
        return index;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTVectorExp(this, state);
    }

    @Override
    public String toString() {
        return "" + vector + "[" + index + "]";
    }
}
