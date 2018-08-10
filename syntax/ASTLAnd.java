package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;


public class ASTLAnd extends ASTExp {

    ASTExp first, second;

    public ASTLAnd(ASTExp first, ASTExp second) {
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
        return v.visitASTLAnd(this, arg);
    }

    @Override
    public String toString() {
        return first.toString() + " and " + second.toString();
    }
}
