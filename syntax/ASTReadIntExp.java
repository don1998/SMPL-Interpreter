package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ASTReadIntExp extends ASTExp {

    boolean integer;

    public ASTReadIntExp() {
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTReadIntExp(this, state);
    }

    @Override
    public String toString() {
            return "readInt()";
}
}