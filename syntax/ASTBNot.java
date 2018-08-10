package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;


public class ASTBNot extends ASTExp {
    ASTExp exp;

    public ASTBNot(ASTExp exp) {
        this.exp = exp;
    }

    public ASTExp getExp() {
        return exp;
    }
    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitASTBNot(this, arg);
    }

    @Override
    public String toString() {
        return "~" + exp.toString();
    }
}
