package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.*;


public class ASTSubstring extends ASTExp {

    ASTExp exp1;
    ASTExp exp2;
    ASTExp exp3;

    public ASTSubstring(ASTExp exp1, ASTExp exp2, ASTExp exp3) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }

    public ASTExp getExp1() {
        return exp1;
    }

    public ASTExp getExp2() {
        return exp2;
    }

    public ASTExp getExp3() {
        return exp3;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTSubstring(this, state);
    }

    @Override
    public String toString() {
        return "substr(" + exp1 + ", " + exp2 + ", " + exp3 + ")";
    }
}