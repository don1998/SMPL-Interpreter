package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ASTListExp extends ASTExp {
    ASTExp values;

    public ASTListExp(ASTExp values){
        this.values = values;
    }

    public ASTExp getList(){
        return values;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTListExp(this, state);
    }

    @Override
    public String toString() {
        return "[" + values + "]";
    }
}