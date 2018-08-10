package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ASTStmtDefinition extends ASTExp {

    String var;
    ASTExp exp;

    public ASTStmtDefinition(String id, ASTExp e) {
	var = id;
	exp = e;
    }

    public String getVar(){
	return var;
    }

    public ASTExp getExp() {
	return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
      return v.visitASTStmtDefinition(this, state);
    }
}
