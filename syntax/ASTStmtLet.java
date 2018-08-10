package smpl.syntax;

import java.util.ArrayList;

import smpl.sys.SmplException;
import smpl.semantics.Visitor;

public class ASTStmtLet extends ASTExp{

    ASTExp bindings;
    ASTExp body;

    public ASTStmtLet(ASTExp bs, ASTExp bod) {
	bindings = bs;
	body = bod;
    }

    public ASTExp getBindings(){
	return bindings;
    }

    public ASTExp getBody() {
	return body;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitASTStmtLet(this, arg);
    }

    public String toString() {
	return body.toString();
    }
}
