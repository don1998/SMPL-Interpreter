package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ASTListCon extends ASTExp {
    ASTExp first, second;

    public ASTListCon(ASTExp first, ASTExp right) {
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
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTListCon(this, state);
    }

    @Override
    public String toString() {
        return "" + first + " @ " + second;
    }
}
