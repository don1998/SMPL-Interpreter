package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ASTReadExp extends ASTExp {

    boolean integer;

    public ASTReadExp() {
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTReadExp(this, state);
    }

    @Override
    public String toString() {
            return "read()";
}
}