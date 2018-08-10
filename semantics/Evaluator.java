package smpl.semantics;

import java.util.*;
import smpl.syntax.*;
import smpl.values.*;
import smpl.sys.*;

public class Evaluator implements Visitor<Environment, SmplValue> {
    /* For this visitor, the argument passed to all visit
       methods will be the environment object that used to
       be passed to the eval method in the first style of
       implementation. */

    // allocate state here
    //protected Integer result;	// result of evaluation

    public Evaluator() {
	// perform initialisations here
	//result = new Integer(0);
    }
    @Override
    public SmplValue visitASTProgram(ASTProgram p, Environment arg) throws SmplException {
	SmplValue result = p.getSeq().visit(this, arg);
	return result;
    }
    @Override
    public SmplValue visitASTPrintLnExp(ASTPrintLnExp form, Environment arg) throws SmplException {
    	SmplValue<?> val=form.getExp().visit(this,arg);
    	System.out.println(val);
    	return SmplValue.NO_VALUE;
    }
    @Override
    public SmplValue visitASTPrintExp(ASTPrintExp form, Environment arg) throws SmplException {
    	SmplValue<?> val=form.getExp().visit(this,arg);
    	System.out.print(val);
    	return SmplValue.NO_VALUE;
    }
    @Override
    public SmplValue visitASTReadExp(ASTReadExp form, Environment arg) throws SmplException {
    	Scanner scan = new Scanner(System.in);
    	String result = scan.nextLine();
    	return SmplValue.NO_VALUE;
    }
    @Override
    public SmplValue visitASTReadIntExp(ASTReadIntExp form, Environment arg) throws SmplException {
    	Scanner scan = new Scanner(System.in);
    	int result = scan.nextInt();
    	return SmplValue.NO_VALUE;
    }
    @Override
    public SmplValue visitASTCaseExp(ASTCaseExp form, Environment arg) throws SmplException{
        ArrayList<ASTCaseBinding> bindings = form.getBindings(); //binding of the form exp:exp
        for(int i = bindings.size()-1;i>-1;i--) 
        {
            ASTCaseBinding binding = bindings.get(i);
            ASTExp logic = binding.getLogic();
            SmplValue<?> value = logic.visit(this,arg);
            if(value.boolValue())   
            {
                ASTExp outcome = binding.getResult();
                if(outcome!=null) 
                {
                    outcome.visit(this,arg);
                }
                else
                {
                    binding.getResults().visit(this,arg);
                }
                break;
            }
                

        }
        return SmplValue.NO_VALUE;
    }

    @Override
    public SmplValue visitASTLetBinding(ASTLetBinding form, Environment arg) throws SmplException {  //Visit for Bindings for a let statement
		SmplValue result = form.getExp().visit(this, arg);
        arg.put(form.getId(), result);
        return result;
    }

    @Override
    public SmplValue visitASTBinding(ASTBinding form, Environment arg) throws SmplException {  //Visit for assignments
    	SmplValue result = form.getExp().visit(this, arg);
        arg.put(form.getId(), result);
        return result;
    }

    @Override
    public SmplValue visitASTExp(ASTExp form, Environment arg) throws SmplException{
        if(form.getClass() != ASTExp.class)
            return form.visit(this, arg);
        ArrayList<ASTExp> expValues = form.getValues();
        ArrayList<SmplValue<?>> smplValues = new ArrayList<>();
        int size = expValues.size();
        if(size == 0)
            return new SmplVariableLengthExp(smplValues);
        ASTExp expValue;
        for(int i=(size - 1); i>=0; i--) {
            expValue = expValues.get(i);
            smplValues.add(expValue.visit(this, arg));
        }
        return new SmplVariableLengthExp(smplValues);
    }




    @Override
    public SmplValue visitASTStmtSequence(ASTStmtSequence sseq,Environment arg)throws SmplException{
    // remember that arg is the environment
    ASTStatement s;
    ArrayList seq = sseq.getSeq();
    Iterator iter = seq.iterator();
    SmplValue result = SmplValue.NO_VALUE; // default result
    while(iter.hasNext()) {
        s = (ASTStatement) iter.next();
        result = s.visit(this, arg);
    }
    // return last value evaluated
    return result;
    }

    @Override
    public SmplValue visitASTListCon(ASTListCon exp, Environment arg)throws SmplException{
        ASTExp val1 = exp.getFirst();
        ASTExp val2 = exp.getSecond();
        SmplValue<?> leftValue = val1.visit(this, arg);
        SmplValue<?> rightValue = val2.visit(this, arg);

        if (!(rightValue instanceof SmplList) || !(leftValue instanceof SmplList) )
                throw new SmplException("Cannot concatenate");

        SmplList leftList = (SmplList) leftValue;
        SmplList rightList = (SmplList) rightValue;
        return (leftList).concat(rightList);
    }


    @Override
    public SmplValue visitASTListExp(ASTListExp exp, Environment arg)throws SmplException{
        ASTExp values = exp.getList();
        SmplValue<?> smplValue = visitASTExp(values, arg);
        if(smplValue instanceof SmplList)
            return smplValue;
        else if (!(smplValue instanceof SmplVariableLengthExp)) {
            System.out.println("This is an instance of: " + smplValue.getClass());
            return new SmplList(smplValue, null);
        } else {
            SmplVariableLengthExp smplNValue = (SmplVariableLengthExp) smplValue;
            ArrayList<SmplValue<?>> smplValues = smplNValue.getValues();
            SmplList list = null;
            SmplPairs pair = null;
            for (int i = (smplValues.size() - 1); i >= 0; i--) {
                SmplValue<?> value = smplValues.get(i);
                if (i == 0)
                    list = new SmplList(value, pair);
                if (pair == null)
                    pair = new SmplPairs(value, null);
                else
                    pair = new SmplPairs(value, pair);
            }

            if (list != null)
                return list;
            else
                return new SmplList(null, null);
        }
    }


    @Override
    public SmplValue visitASTStmtDefinition(ASTStmtDefinition sd, Environment arg)
	throws SmplException
    {
	SmplValue result;
	result = sd.getExp().visit(this,arg);
	arg.put(sd.getVar(), result);
	return result;
    }

    @Override
    public SmplValue visitASTStmtFnDefn(ASTStmtFnDefn exp, Environment arg) throws SmplException {
	return new SmplFunction(exp,arg);
    }

    @Override
    public SmplValue visitASTCmp(ASTCmp exp, Environment arg) throws SmplException{
    	SmplValue firstVal= exp.getFirst().visit(this, arg);
        SmplValue secondVal = exp.getSecond().visit(this, arg);
        int firstint = firstVal.intValue();
        int secondint = secondVal.intValue();
        String opt = exp.getCmp();
        if (opt.equals(">")){
            if(firstint > secondint)
                return SmplValue.make(true);
            else
                return SmplValue.make(false);
        }
        if (opt.equals("<")){
            if(firstint < secondint)
                return SmplValue.make(true);
            else
                return SmplValue.make(false);
        }
        if (opt.equals("=")){
            if(firstint == secondint)
                return SmplValue.make(true);
            else
                return SmplValue.make(false);
        }
        if (opt.equals("!=")){
            if(firstint != secondint)
                return SmplValue.make(true);
            else
                return SmplValue.make(false);
        }
        if (opt.equals("<=")){
            if(firstint <= secondint)
                return SmplValue.make(true);
            else
                return SmplValue.make(false);
        }
        if (opt.equals(">=")){
            if(firstint >= secondint)
                return SmplValue.make(true);
            else
                return SmplValue.make(false);
        }
        return SmplValue.NO_VALUE;
    }
    @Override
    public SmplValue visitASTIFExp(ASTIFExp exp, Environment arg) throws SmplException{
    SmplBoolean bool = (SmplBoolean) exp.getPredicate().visit(this, arg);
    SmplValue val1=SmplValue.NO_VALUE;
    SmplValue val2=SmplValue.NO_VALUE;
        if(bool.boolValue()){
            val1 = exp.getConsequent().visit(this, arg);
            return val1;
        }
        else if(exp.consequent()){
            val2 = exp.getOtherwise().visit(this, arg);
        return val2;
        }
        else
            return SmplValue.NO_VALUE;
    }
    @Override
    public SmplValue visitASTLOr(ASTLOr exp, Environment arg) throws SmplException{
        SmplValue val1 = exp.getFirst().visit(this, arg);
        SmplValue val2 = exp.getSecond().visit(this, arg);
        return val1.logicalOr(val2);
    }
    @Override
    public SmplValue visitASTLAnd(ASTLAnd exp, Environment arg) throws SmplException{
        SmplValue val1 = exp.getFirst().visit(this, arg);
        SmplValue val2 = exp.getSecond().visit(this, arg);
        return val1.logicalAnd(val2);
    }
    @Override
    public SmplValue visitASTLNot(ASTLNot exp, Environment arg) throws SmplException{
        SmplValue val = exp.getExp().visit(this, arg);
        return SmplBoolean.logicalNot(val);
    }
    @Override
    public SmplValue visitASTBAnd(ASTBAnd exp, Environment arg) throws SmplException{
        SmplValue val1, val2;
        val1 = exp.getFirst().visit(this, arg);
        val2 = exp.getSecond().visit(this, arg);
        return val1.bitwiseAnd(val2);
    }
    @Override
    public SmplValue visitASTBOr(ASTBOr exp, Environment arg) throws SmplException{
        SmplValue val1, val2;
        val1 = exp.getFirst().visit(this, arg);
        val2 = exp.getSecond().visit(this, arg);
        return val1.bitwiseOr(val2);
    }
    @Override
    public SmplValue visitASTBNot(ASTBNot exp, Environment arg) throws SmplException{
    SmplValue val = exp.getExp().visit(this, arg);
        if(val.isInteger())
            return SmplInt.bitwiseNot(val);
        else
            return SmplReal.bitwiseNot(val);
    }

    @Override
    public SmplValue visitASTExpFnCall(ASTExpFnCall exp, Environment arg) throws SmplException {
    	

        ASTExp functionName = exp.getName();//Name the function is being called by

    	ASTExp functionExp = exp.getExp();//The args

        SmplValue<?> functionobj = functionName.visit(this,arg);

        if (functionobj instanceof SmplVariableLengthExp){
            SmplVariableLengthExp varlen = (SmplVariableLengthExp) functionName.visit(this,arg);
            //SmplVariableLengthExp func = functionobj;
            SmplValue<?> obj = varlen.getValues().get(0);
            SmplFunction func = (SmplFunction) obj;

            Environment<SmplValue<?>> closure = func.getclosure();//Environment in which function was defined.

            ASTStmtFnDefn functionDefinition = func.getFunVar();// The function's definitio

            ArrayList parm = functionDefinition.getParms();

            ArrayList lst = new ArrayList();

            ArrayList<ASTExp> functionvalues = functionExp.getValues();

            for(ASTExp e: functionvalues){
                lst.add(e.visit(this,arg));
            }
            Collections.reverse(lst);
            Environment currentEnv= new Environment(parm,lst,closure);
            return functionDefinition.getBody().visit(this,currentEnv);
        }

        else{
        SmplFunction funcobj = (SmplFunction)functionobj;//This is the SmplFunction Object

        Environment<SmplValue<?>> closure = funcobj.getclosure();//Environment in which function was defined.

        ASTStmtFnDefn functionDefinition = funcobj.getFunVar();// The function's definitio

        ArrayList parm = functionDefinition.getParms();

        ArrayList lst = new ArrayList();

        ArrayList<ASTExp> functionvalues = functionExp.getValues();

        for(ASTExp e: functionvalues){
            lst.add(e.visit(this,arg));
        }
        Collections.reverse(lst);
        Environment currentEnv= new Environment(parm,lst,closure);
        return functionDefinition.getBody().visit(this,currentEnv);
        }

    }


    
	@Override
    public SmplValue visitASTStmtLet(ASTStmtLet exp, Environment arg) {
    	try{
       	ASTExp vals = exp.getBindings();
        ASTExp body = exp.getBody();
        ArrayList<ASTExp> bindings = vals.getValues();
        int size = bindings.size();
        String[] vars = new String[size];
        SmplValue<?>[] values = new SmplValue<?>[size];

        for(int i=0; i<size; i++){
            ASTExp value = bindings.get(i);
            ASTLetBinding assignment = (ASTLetBinding) value;
            String var = assignment.getId();
            ASTExp assignedVal = assignment.getExp();
            SmplValue result = assignedVal.visit(this,arg);
            vars[i] = var;
            values[i] = result;
        }
        Environment<SmplValue<?>> currentEnv = new Environment<> (vars,values,arg);
        return body.visit(this, currentEnv);
    	}
    	catch(Exception e){
    		SmplValue.make(e.getMessage());
    	}
        return SmplValue.NO_VALUE;
    }
    @Override
    public SmplValue visitASTExpAdd(ASTExpAdd exp, Environment arg)
	throws SmplException
    {
	SmplValue val1, val2;
	val1 = exp.getFirst().visit(this, arg);
	val2 = exp.getSecond().visit(this, arg);
	return SmplValue.make(val1.intValue()+val2.intValue());
    }
    @Override
    public SmplValue visitASTExpSub(ASTExpSub exp, Environment arg)
	throws SmplException
    {
	SmplValue val1, val2;
	val1 = exp.getFirst().visit(this, arg);
	val2 = exp.getSecond().visit(this, arg);
	return val1.sub(val2);
    }
    @Override
    public SmplValue visitASTExpMul(ASTExpMul exp, Environment arg)
	throws SmplException
    {
	SmplValue val1, val2;
	val1 = exp.getFirst().visit(this, arg);
	val2 = exp.getSecond().visit(this, arg);
	return val1.mul(val2);
    }
    @Override
    public SmplValue visitASTExpDiv(ASTExpDiv exp, Environment arg)throws SmplException{
	SmplValue val1, val2;
	val1 = exp.getFirst().visit(this, arg);
	val2 = exp.getSecond().visit(this, arg);
	return val1.div(val2);
    }
    @Override
    public SmplValue visitASTExpMod(ASTExpMod exp, Environment arg)throws SmplException{
	SmplValue val1, val2;
	val1 = exp.getFirst().visit(this, arg);
	val2 = exp.getSecond().visit(this, arg);
	return val1.mod(val2);
    }
    @Override
    public SmplValue visitASTExpPow(ASTExpPow exp, Environment arg)throws SmplException{
	SmplValue val1, val2;
	val1 =exp.getFirst().visit(this, arg);
	val2 =exp.getSecond().visit(this, arg);
	return val1.pow(val2);
    }
    @Override
    public SmplValue visitASTExpLit(ASTExpLit exp, Environment arg)throws SmplException{
	   return exp.getVal();
    }
    @Override
    public SmplValue visitASTExpVar(ASTExpVar exp, Environment arg) throws SmplException{
	   SmplValue val = SmplValue.NO_VALUE;
	   try{
	       return arg.get(exp.getVar());
	   }
	   catch(Exception e){
		  System.out.println(e.getMessage());
        //System.out.println("Test.");
            return val;
	   }
    }
    @Override
    public SmplValue visitASTPair(ASTPair exp, Environment arg) throws SmplException{
        SmplValue left = exp.getLeft().visit(this,arg);
        SmplValue right = exp.getRight().visit(this,arg);
        return new SmplPairs(left,right);
    }
    @Override
    public SmplValue visitASTIsPair(ASTIsPair exp, Environment arg) throws SmplException{
        return SmplValue.make(exp.getPair().visit(this, arg).getType() == SmplType.PAIRS);
    }


    @Override
    public SmplValue visitASTCdr(ASTCdr form, Environment arg) throws SmplException{
        SmplPairs pair = (SmplPairs) form.getPair().visit(this, arg);
        return pair.getRight();
    }


    @Override
    public SmplValue visitASTCar(ASTCar form, Environment arg) throws SmplException{
        SmplPairs pair = (SmplPairs) form.getPair().visit(this, arg);
        return pair.getLeft();
    }

    @Override
    public SmplValue<?> visitASTEqual(ASTEqual exp, Environment arg) throws SmplException{
        ASTExp exp1 = exp.getLeft();
        ASTExp exp2 = exp.getRight();
        SmplValue<?> val1 = exp1.visit(this,arg);
        SmplValue<?> val2 = exp2.visit(this,arg);

        return SmplValue.make(val1.equal(val2));
    }
    @Override
    public SmplValue visitASTEquivalent(ASTEquivalent exp, Environment arg) throws SmplException{
        ASTExp exp1 = exp.getLeft();
        ASTExp exp2 = exp.getRight();
        SmplValue<?> val1 = exp1.visit(this,arg);
        SmplValue<?> val2 = exp2.visit(this,arg);
        return val1.eqv(val2);
    }


    @Override
    public SmplValue visitASTSubstring(ASTSubstring exp, Environment arg) throws SmplException {
        SmplValue<?> exp1 = exp.getExp1().visit(this,arg);
        SmplValue<?> exp2 = exp.getExp2().visit(this,arg);
        SmplValue<?> exp3 = exp.getExp3().visit(this,arg);

        if((exp1 instanceof SmplString)==false)
            throw new SmplException("First argument must be a string");
        else if(((exp2 instanceof SmplInt)==false) && (exp2 instanceof SmplReal)==false) {
            throw new SmplException("Second argument must be a number");
            }
        else if(((exp3 instanceof SmplInt)==false) && (exp3 instanceof SmplReal)==false){
            throw new SmplException("Third argument must be a number");
            }
        SmplString exp1_val = (SmplString) exp1;
        int exp2_val = exp2.intValue();
        int exp3_val = exp3.intValue();

        return exp1_val.substr(exp2_val, exp3_val);
    }
    @Override
    public SmplValue visitASTSize(ASTSize exp, Environment arg) throws SmplException{
        SmplValue<?> vector = exp.getVector().visit(this,arg);
        if ((vector instanceof SmplVector)==false){
            System.out.println("Not a vector.");
        }
        SmplVector result_vector = (SmplVector) vector;
        return result_vector.size();
    }
    @Override
    public SmplValue visitASTWhileExp(ASTWhileExp exp, Environment arg) throws SmplException {
        SmplValue result = SmplValue.NO_VALUE;
        while((exp.getPredicate().visit(this,arg)).boolValue()){
            result = exp.getExp().visit(this,arg);
        }

        return result;
    }

    @Override
    public SmplValue visitASTVectorDef(ASTVectorDef exp, Environment arg) throws SmplException {
    ArrayList<SmplValue<?>> smplvalues = new ArrayList<>();
    ArrayList<ASTExp> values = exp.getVec().getValues();
    ASTExp val;
        int size = values.size();

        if(size == 0)
            return new SmplVector(smplvalues);
        
        for(int i=(size - 1); i>=0; i--) {
            val = values.get(i);
            smplvalues.add(val.visit(this, arg));
        }
        return new SmplVector(smplvalues);
    }

//Needs function call to be working before implementation
    @Override
    public SmplValue visitASTVectorBinding(ASTVectorBinding exp, Environment arg) throws SmplException {
        ASTExp id = exp.getId();
        ASTExp function = exp.getFunction();
        SmplValue val = id.visit(this, arg);

        if(!(val instanceof SmplInt) && !(val instanceof SmplReal)){
            throw new SmplException("Incorrect Format");
        }

        ArrayList<SmplValue<?>> values = new ArrayList<>();

        for(int i=0; i<val.intValue(); i++){
            ASTExpFnCall call = new ASTExpFnCall(function, new ASTExpLit(i));
            SmplValue result = call.visit(this, arg);
            values.add(result);
        }
        return new SmplVector(values);
    }
    @Override
public SmplValue visitASTVectorExp(ASTVectorExp exp, Environment arg) throws SmplException {
        ASTExp vector = exp.getVec();
        ASTExp index = exp.getIndex();
        SmplValue val1 = vector.visit(this, arg);
        SmplValue val2 = index.visit(this, arg);

        if(!(val1 instanceof SmplVector) || !(val2 instanceof SmplInt) && !(val2 instanceof SmplReal))
            throw new SmplException("Incorrect Format");

        SmplVector val3 = (SmplVector) val1;
        return val3.getValue(val2);
    } 


public SmplValue visitASTRedefineExp(ASTRedefineExp exp, Environment arg) throws SmplException{
    String name= exp.getVariable();
    if (!(name.equals("pair")) && (!(name.equals("pair?"))) && (!(name.equals("substr"))) && (!(name.equals("equal?"))) && (!(name.equals("list"))) && (!(name.equals("eqv?"))) && (!(name.equals("size"))) && (!(name.equals("cdr"))) && (!(name.equals("car"))) ){
        throw new SmplException("This is not a builtin-in function and cannot be redefined in this manner.");
    }
    else{
        SmplValue result = SmplValue.NO_VALUE;
        result = exp.getDefinition().visit(this,arg);
        SmplFunction funcobj = (SmplFunction) result;
        arg.put(name, result);
        return SmplValue.NO_VALUE;
    }
}

public SmplValue visitASTCallExp(ASTCallExp exp, Environment arg) throws SmplException{
        ASTExp proc = exp.getProc();
        ASTExp list = exp.getList();
        SmplValue<?> lst = list.visit(this, arg);
        if((lst instanceof SmplList)==false){
            throw new SmplException("\nExpected a list but got " + lst.getType() + "\n");
        }
        ArrayList<ASTExp> arguments = new ArrayList<>();
        arguments.add(list);
        ASTExp args = new ASTExp(arguments);
        ASTExpFnCall func = new ASTExpFnCall(proc,args);
        return func.visit(this, arg);
}


    public SmplValue visitASTEqualOP(ASTEqualOP exp, Environment arg) throws SmplException{
        SmplValue firstVal= exp.getFirst().visit(this, arg);
        SmplValue secondVal = exp.getSecond().visit(this, arg);
        int firstint = firstVal.intValue();
        int secondint = secondVal.intValue();
        String opt = exp.getCmp();
        if (opt.equals("=")){
            if(firstint > secondint)
                return SmplValue.make(true);
            else
                return SmplValue.make(false);
        }
        else{
            throw new SmplException("Warning.");
        }


}
}

