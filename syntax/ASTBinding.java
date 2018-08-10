package smpl.syntax;

import java.util.*;
import smpl.semantics.*;
import smpl.sys.*;

public class ASTBinding extends ASTExp{
    String var;
    ASTExp valueExp;

    public ASTBinding(String v, ASTExp valExp) {
	var = v;
	valueExp = valExp;
    }

    public String getId() {
	return var;
    }

    public ASTExp getExp() {
	return valueExp;
    }

    public String toString() {
	return var + ":=" + valueExp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTBinding(this, state);
    }
}
