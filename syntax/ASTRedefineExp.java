package smpl.syntax;

import smpl.sys.*;
import smpl.semantics.Visitor;

public class ASTRedefineExp extends ASTExp{

String variable;
ASTExp definition;
	
	public ASTRedefineExp(String variable, ASTExp definition){
		this.variable = variable;
		this.definition = definition;
	}

	public ASTExp getDefinition(){
		return definition;
	}

	public String getVariable(){
		return variable;
	}

	@Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTRedefineExp(this, state);
    }

     @Override
    public String toString() {
    	return variable + "(" + definition + ")";
    }
}