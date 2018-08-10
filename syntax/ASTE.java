package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;


public class ASTE extends ASTExp {

    ASTExp exp1;
    ASTExp exp2;

    public ASTE(ASTExp e1, ASTExp e2) {
        exp1 = e1;
        exp2 = e2;
    }

    public ASTExp getExpL() {
        return exp1;
    }

    public ASTExp getExpR() {
        return exp2;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitASTExp(this, arg);
    }

    @Override
    public String toString() {
        return exp1.toString() + " ^ " + exp2.toString();
    }
}
