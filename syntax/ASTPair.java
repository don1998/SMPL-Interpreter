package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.*;

public class ASTPair extends ASTExp {
    ASTExp left;
    ASTExp right;

    public ASTPair(ASTExp left, ASTExp right) {
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
        return v.visitASTPair(this, state);
    }

    @Override
    public String toString() {
        return "(" + left + ", " + right + ")";
    }
}