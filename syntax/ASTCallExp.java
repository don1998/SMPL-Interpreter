package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.*;

public class ASTCallExp extends ASTExp {

    ASTExp proc;
    ASTExp lst;

    public ASTCallExp(ASTExp proc, ASTExp lst) {
        this.proc = proc;
        this.lst = lst;
    }

    public ASTExp getProc() {
        return proc;
    }

    public ASTExp getList() {
        return lst;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTCallExp(this, state);
    }

    @Override
    public String toString() {
        return "call(" + proc + "," + lst + ")";
    }
}