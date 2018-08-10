package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;


public class ASTVectorBinding extends ASTExp {

    private ASTExp id, function;

    public ASTVectorBinding(){
    }

    public ASTVectorBinding(ASTExp id, ASTExp function){
        this.id = id;
        this.function = function;
    }

    public void setId(ASTExp id) {
        this.id = id;
    }

    public void setFunction(ASTStmtFnDefn function) {
        this.function = function;
    }

    public ASTExp getId() {
        return id;
    }

    public ASTExp getFunction() {
        return function;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTVectorBinding(this, state);
    }

    @Override
    public String toString() {
        return "" + id + ": " + function;
    }
}
