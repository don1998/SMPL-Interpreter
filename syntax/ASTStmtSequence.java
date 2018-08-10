package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;
import java.util.*;

public class ASTStmtSequence extends ASTExp {

    ArrayList<ASTStatement> seq;		// sequence of commands

    public ASTStmtSequence() {
	seq = new ArrayList<>();
    }
    
    public ASTStmtSequence(ArrayList<ASTStatement> stmts) {
        seq = stmts;
    }

    public ASTStmtSequence(ASTStatement s) {
	this();
	seq.add(s);
    }

    public ArrayList<ASTStatement> getSeq() {
	return seq;
    }

    public ASTStmtSequence add(ASTStatement s) {
	seq.add(s);
	return this;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException
    {
	return v.visitASTStmtSequence(this, arg);
    }

    @Override
    public String toString() {

	String result = "";
        for (ASTStatement stmt : seq) {
            result = result + stmt.toString() + "\n";
        }
        
	return result;
    }

}

