package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;


public class ASTLOr extends ASTExp {

    ASTExp first, second;

    public ASTLOr(ASTExp first, ASTExp second) {
        this.first = first;
        this.second = second;
    }

    public ASTExp getFirst() {
        return first;
    }

    public ASTExp getSecond() {
        return second;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitASTLOr(this, arg);
    }

    @Override
    public String toString() {
        return first.toString() + " or " + second.toString();
    }
}
