package smpl.semantics;

import smpl.syntax.*;
import smpl.sys.SmplException;

public interface Visitor<S, T> {

    // program
    public T visitASTProgram(ASTProgram p,
				    S arg)
	throws SmplException;

    // statements
    public T visitASTStmtSequence(ASTStmtSequence exp,
				    S arg)
	throws SmplException ;
    public T visitASTStmtDefinition(ASTStmtDefinition sd,
				      S arg)
	throws SmplException;

    public T visitASTStmtLet(ASTStmtLet exp, S arg) throws SmplException;
    public T visitASTStmtFnDefn(ASTStmtFnDefn exp, S arg) throws SmplException;
    public T visitASTExpFnCall(ASTExpFnCall exp, S arg) throws SmplException;

    // expressions
    public T visitASTExp(ASTExp exp, S arg) throws SmplException;

    public T visitASTExpAdd(ASTExpAdd exp, S arg)
	throws SmplException ;
    public T visitASTExpSub(ASTExpSub exp, S arg)
	throws SmplException;
    public T visitASTExpMul(ASTExpMul exp, S arg)
	throws SmplException;
    public T visitASTExpDiv(ASTExpDiv exp, S arg)
	throws SmplException;
    public T visitASTExpMod(ASTExpMod exp, S arg)
	throws SmplException;
    public T visitASTExpPow(ASTExpPow exp, S arg)
	throws SmplException;
    public T visitASTExpLit(ASTExpLit exp, S arg)
	throws SmplException;
    public T visitASTExpVar(ASTExpVar exp, S arg)
	throws SmplException;


    public T visitASTListExp(ASTListExp exp, S arg) throws SmplException;


    /*Print and read statement*/
    public T visitASTPrintLnExp(ASTPrintLnExp exp, S arg) throws SmplException;

    public T visitASTPrintExp(ASTPrintExp exp, S arg) throws SmplException;

    public T visitASTReadIntExp(ASTReadIntExp exp, S arg) throws SmplException;

    public T visitASTReadExp(ASTReadExp exp, S arg) throws SmplException;
    /*Print and read statement*/


    /*Let Binding and assignment*/

    public T visitASTLetBinding(ASTLetBinding exp, S arg) throws SmplException;

    public T visitASTBinding(ASTBinding exp, S arg) throws SmplException;

    /*Let Binding and assignment*/


    /*Logic and Control structure stuff*/

    public T visitASTCmp(ASTCmp exp, S arg) throws SmplException;

    public T visitASTIFExp(ASTIFExp exp, S arg) throws SmplException;

    public T visitASTLOr(ASTLOr exp, S arg) throws SmplException;

    public T visitASTLAnd(ASTLAnd exp, S arg) throws SmplException;

    public T visitASTLNot(ASTLNot exp, S arg) throws SmplException;

    public T visitASTBAnd(ASTBAnd exp, S arg) throws SmplException;

    public T visitASTBOr(ASTBOr exp, S arg) throws SmplException;

    public T visitASTBNot(ASTBNot exp, S arg) throws SmplException;

    public T visitASTCaseExp(ASTCaseExp exp, S arg) throws SmplException;

    /*Logic and Control structure stuff*/


    public T visitASTWhileExp(ASTWhileExp exp, S arg) throws SmplException;


    /*Builtin Functions*/

    public T visitASTPair(ASTPair exp, S arg) throws SmplException;

    public T visitASTIsPair(ASTIsPair exp, S arg) throws SmplException;

    public T visitASTCdr(ASTCdr exp, S arg) throws SmplException;

    public T visitASTCar(ASTCar exp, S arg) throws SmplException;

    public T visitASTEqual(ASTEqual exp, S arg) throws SmplException;

    public T visitASTEquivalent(ASTEquivalent exp, S arg) throws SmplException;

    public T visitASTSubstring(ASTSubstring exp, S arg) throws SmplException;

    public T visitASTSize(ASTSize exp, S arg) throws SmplException;

    public T visitASTVectorBinding(ASTVectorBinding exp, S arg) throws SmplException;

    public T visitASTVectorDef(ASTVectorDef exp, S arg) throws SmplException;

    public T visitASTVectorExp(ASTVectorExp exp, S arg) throws SmplException;


    public T visitASTRedefineExp(ASTRedefineExp exp, S arg) throws SmplException;

    public T visitASTCallExp(ASTCallExp exp, S arg) throws SmplException;

    public T visitASTEqualOP(ASTEqualOP exp, S arg) throws SmplException;
    
    
    public T visitASTListCon(ASTListCon exp, S arg) throws SmplException;





}
