package smpl.syntax;


import smpl.sys.*;
import smpl.semantics.Visitor;
import java.util.ArrayList;


public class ASTCaseExp extends ASTExp {

	ArrayList<ASTCaseBinding> bindings;

	public ASTCaseExp(ArrayList<ASTCaseBinding> bindings)
	{
		this.bindings = bindings;
	}

	public ArrayList<ASTCaseBinding> getBindings()
	{
		return bindings;
	}

	@Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitASTCaseExp(this, arg);
    }

    @Override
    public String toString() {
        return "case stmt";
    }
}