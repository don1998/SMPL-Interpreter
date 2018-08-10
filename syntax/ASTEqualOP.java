package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ASTEqualOP extends ASTExp {

    ASTExp first, second;
    String cmp;

    public ASTEqualOP(String cmp, ASTExp first, ASTExp second) {
        this.cmp = cmp;
        this.first = first;
        this.second = second;
    }

    public String getCmp() {
        return cmp;
    }

    public ASTExp getFirst() {
        return first;
    }

    public ASTExp getSecond() {
        return second;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitASTEqualOP(this, arg);
    }

    @Override
    public String toString() {
        return first.toString() + cmp + second.toString();
    }
}
