package smpl.syntax;

import smpl.sys.SmplException;
import smpl.semantics.Visitor;

public class ASTExpFnCall extends ASTExp {

    ASTExp fnName;
    ASTExp exp;

    public ASTExpFnCall(ASTExp name, ASTExp exp) {
	fnName = name;
	this.exp = exp;
    }

    public ASTExp getName() {
	return fnName;
    }

    public ASTExp getExp() {
        return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitASTExpFnCall(this, arg);
    }

    @Override
    public String toString(){
        return "" + getExp() + "";
    }
}
