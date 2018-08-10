package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.*;

public class ASTEquivalent extends ASTExp {
    ASTExp left;
    ASTExp right;

    public ASTEquivalent(ASTExp left, ASTExp right) {
        this.left = left;
        this.right = right;
    }

    public ASTExp getLeft() {
        return left;
    }

    public ASTExp getRight() {
        return right;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTEquivalent(this, state);
    }

    @Override
    public String toString() {
        return "eqv?"+"(" + left + ", " + right + ")";
    }
}