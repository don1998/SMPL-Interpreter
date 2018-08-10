package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ASTWhileExp extends ASTExp {

    ASTExp predicate, exp;

    public ASTWhileExp(ASTExp predicate, ASTExp exp) {
        this.predicate = predicate;
        this.exp = exp;
    }

    public ASTExp getPredicate(){
        return predicate;
    }

    public ASTExp getExp(){
        return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTWhileExp(this, state);
    }

    @Override
    public String toString() {
        return "while(" + predicate + "){ " + exp + "}";
    }
}
