package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

/**
  Class ASTExpVar.
  Intermediate representation class autogenerated by CS34Q semantic generator.
  Created on Sat Oct 12 03:13:16 2013
*/
public class ASTExpVar extends ASTExp {
  String var;

  public ASTExpVar (String var) {
    this.var = var;
  }

  public String getVar() {
    return var;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
    return v.visitASTExpVar(this, state);
  }

  public String toString() {
    return var;
  }
}
