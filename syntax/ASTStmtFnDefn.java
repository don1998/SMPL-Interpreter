package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.*;


import java.util.ArrayList;

public class ASTStmtFnDefn extends ASTExp {

    String prest;
    ArrayList parms;
    public ASTExp bod;
    String fname;


    public ASTStmtFnDefn(ArrayList parms, String prest, ASTExp bod) {
    this.fname=fname;
	this.prest = prest;
	this.parms = parms;
	this.bod = bod;
    }

    public String getPrest() {
	return prest;
    }

    public ArrayList getParms() {
	return parms;
    }

    public ASTExp getBody() {
	return bod;
    }

    public boolean isVariableArity(){
        return !prest.equals("");
    }

    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
	return v.visitASTStmtFnDefn(this, state);
    }

    public String toString() {
    	String name;
    if (fname==null)
    	name="proc";
    else{
    	name=fname;
    }
	String pList = "";
	if (parms.size() > 0) {
	    pList = parms.get(0).toString();
	    for (int i = 1; i < parms.size(); i++) {
		pList = pList + ", " + parms.get(i);
	    }
	} 
	return name + "(" + pList + ") = " + bod.toString();
    }
}
