package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.*;
import java.util.Map;
import java.util.HashMap;

public class ASTLetBinding extends ASTExp{

	String id;
	ASTExp exp;
	
	public ASTLetBinding(String id, ASTExp exp){
		this.id=id;
		this.exp=exp;
	}

	public String getId(){
		return id;
	}

	public ASTExp getExp(){
		return exp;
	}


	@Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTLetBinding(this, state);
    }

    @Override
    public String toString() {
            return id+ " = "+ exp;
}

}