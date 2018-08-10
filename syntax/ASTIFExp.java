package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;


public class ASTIFExp extends ASTExp {

    public ASTExp predicate;
    public ASTExp consequent;
    public ASTExp otherwise;

    public ASTIFExp(ASTExp predicate, ASTExp consequent) {
        this.predicate = predicate;
        this.consequent = consequent;
        this.otherwise = null;
    }

    public ASTIFExp(ASTExp predicate, ASTExp consequent, ASTExp otherwise) {
        this.predicate = predicate;
        this.consequent = consequent;
        this.otherwise = otherwise;
    }

    public ASTExp getPredicate() {
        return predicate;
    }

    public ASTExp getConsequent() {
        return consequent;
    }

    public boolean consequent(){
        return otherwise != null;
    }

    public ASTExp getOtherwise() {
        return otherwise;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTIFExp(this, state);
    }

    public String toString() {
    String result = "if " + predicate + " then " + consequent;
        if(consequent())
            result += " else " + otherwise;
        return result;
}
}
