package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ASTBAnd extends ASTExp {
    ASTExp first, second;

    public ASTBAnd(ASTExp first, ASTExp second) {
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
        return v.visitASTBAnd(this, arg);
    }

    @Override
    public String toString() {
        return first.toString() + " & " + second.toString();
    }
}
