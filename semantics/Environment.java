package smpl.semantics;

import java.util.ArrayList;
import java.util.HashMap;
import smpl.values.*;
import smpl.sys.*;
import smpl.syntax.*;

public class Environment <T extends SmplValue<?>> {

    HashMap dictionary;
    HashMap<String, Boolean> predefined;
    Environment parent = null;

    /**
     * Create a new (empty) top level Environment.
     *
     */
    public Environment() {
    dictionary = new HashMap();
    predefined = new HashMap();
    try{
        initPredefinedFunctions();
        }
    catch(Exception e){
        System.out.println(e.getMessage());
    }
    }

    /**
     * Creates a new <code>Environment</code> instance that is
     * initialised with the given collection of bindings
     * (presented as separate arrays of names and values).
     *
     * @param ids the collection of identifiers to be bound.
     * @param values the corresponding collection of values
     * for the identifiers.  Note that the two arrays must
     * have the same length.
     */
    public Environment(String[] ids, SmplValue[] values) {
    dictionary = new HashMap();
    predefined = new HashMap();
    for (int i = 0; i < ids.length; i++) {
        define(ids[i]);
        put(ids[i], values[i]);
    }
    }

    public Environment(ArrayList<String> ids,ArrayList<SmplValue> values, Environment p) {
    parent = p;
    predefined = new HashMap();
    dictionary = new HashMap();
    for (int i = 0; i < ids.size(); i++) {
        predefined.put(ids.get(i),true);
        dictionary.put(ids.get(i), values.get(i));
    }
    }

    public Environment(String[] ids, SmplValue[] values, Environment p) {
    parent = p;
    predefined = new HashMap();
    dictionary = new HashMap();
    for (int i = 0; i < ids.length; i++) {
        put(ids[i], values[i]);
        define(ids[i]);
    }
    }


    public void define(String id){
        predefined.put(id, new Boolean(true));
    }


    public void initPredefinedFunctions() throws SmplException{
        String prest="";

        /*Pair builtins section*/

        //make a pair
        String pair = "pair";
        define(pair);
        ArrayList<String> pair_parameters = new ArrayList<String>();
        pair_parameters.add("a");
        pair_parameters.add("b");
        ASTPair pairval = new ASTPair(new ASTExpVar("a"),new ASTExpVar("b"));
        ASTStmtFnDefn pairfunc = new ASTStmtFnDefn(pair_parameters,prest,pairval);
        SmplFunction pairfunction = new SmplFunction(pairfunc,(Environment<SmplValue<?>>)this);
        put(pair,pairfunction);


        //ispair
        String ispair = "pair?";
        define(ispair);
        ArrayList<String> ispair_parameters = new ArrayList<String>();
        ispair_parameters.add("a");
        ASTIsPair pairval2 = new ASTIsPair(new ASTExpVar("a"));
        ASTStmtFnDefn ispairfunc = new ASTStmtFnDefn(ispair_parameters,prest,pairval2);
        SmplFunction ispairfunction = new SmplFunction(ispairfunc,(Environment<SmplValue<?>>)this);
        put(ispair,ispairfunction);

        

        /*Car and Cdr builtin section*/

        String car = "car";
        define(car);
        ArrayList<String> car_parameters = new ArrayList<String>();
        car_parameters.add("a");
        ASTCar carval = new ASTCar(new ASTExpVar("a"));
        ASTStmtFnDefn carfunc = new ASTStmtFnDefn(car_parameters,prest,carval);
        SmplFunction carfunction = new SmplFunction(carfunc,(Environment<SmplValue<?>>)this);
        put(car,carfunction);


        String cdr = "cdr";
        define(cdr);
        ArrayList<String> cdr_parameters = new ArrayList<String>();
        cdr_parameters.add("b");
        ASTCdr cdrval = new ASTCdr(new ASTExpVar("b"));
        ASTStmtFnDefn cdrfunc = new ASTStmtFnDefn(cdr_parameters,prest,carval);
        SmplFunction cdrfunction = new SmplFunction(cdrfunc,(Environment<SmplValue<?>>)this);
        put(cdr,cdrfunction);

        /*Equal and Equivalent builtin section*/

        String equal = "equal?";
        define(equal);
        ArrayList<String> equal_parameters = new ArrayList<String>();
        equal_parameters.add("a");
        equal_parameters.add("b");
        ASTEqual equalval = new ASTEqual(new ASTExpVar("a"),new ASTExpVar("b"));
        ASTStmtFnDefn equalfunc = new ASTStmtFnDefn(equal_parameters,prest,equalval);
        SmplFunction equalfunction = new SmplFunction(equalfunc,(Environment<SmplValue<?>>)this);
        put(equal,equalfunction);


        String eqv = "eqv?";
        define(eqv);
        ArrayList<String> eqv_parameters = new ArrayList<String>();
        eqv_parameters.add("a");
        eqv_parameters.add("b");
        ASTEquivalent eqvval = new ASTEquivalent(new ASTExpVar("a"),new ASTExpVar("b"));
        ASTStmtFnDefn eqvfunc = new ASTStmtFnDefn(eqv_parameters,prest,eqvval);
        SmplFunction eqvfunction = new SmplFunction(eqvfunc,(Environment<SmplValue<?>>)this);
        put(eqv,eqvfunction);


        /*List and Vectors builtin section */

        String substring = "substr";
        define(substring);
        ArrayList<String> substring_parameters = new ArrayList<String>();
        substring_parameters.add("a");
        substring_parameters.add("b");
        substring_parameters.add("c");
        ASTSubstring substringval = new ASTSubstring(new ASTExpVar("a"),new ASTExpVar("b"),new ASTExpVar("c"));
        ASTStmtFnDefn substringfunc = new ASTStmtFnDefn(substring_parameters,prest,substringval);
        SmplFunction substringfunction = new SmplFunction(substringfunc,(Environment<SmplValue<?>>)this);
        put(substring,substringfunction);

        String lst = "list";
        define(lst);
        ArrayList<String> lst_parameters = new ArrayList<String>();
        lst_parameters.add("a");
        ASTListExp lstval = new ASTListExp(new ASTExpVar("a"));
        ASTStmtFnDefn lstfunc = new ASTStmtFnDefn(lst_parameters,prest,lstval);
        SmplFunction lstfunction = new SmplFunction(lstfunc,(Environment<SmplValue<?>>)this);
        put(lst,lstfunction);


        String size = "size";
        define(size);
        ArrayList<String> size_parameters = new ArrayList<String>();
        lst_parameters.add("a");
        ASTSize sizeval = new ASTSize(new ASTExpVar("a"));
        ASTStmtFnDefn sizefunc = new ASTStmtFnDefn(size_parameters,prest,sizeval);
        SmplFunction sizefunction = new SmplFunction(sizefunc,(Environment<SmplValue<?>>)this);
        put(size,sizefunction);




    }



    /**
     * Create an instance of a global environment suitable for
     * evaluating an program.
     *
     * @return the <code>Environment</code> created.
     */
    public static Environment makeGlobalEnv() {
    Environment result =  new Environment();
    // add definitions for any primitive procedures or
    // constants here
    return result;
    }

    /**
     * Store a binding for the given identifier to the given
     * int within this environment.
     *
     * @param id the name to be bound
     * @param value the value to which the name is bound.
     */
    public void put(String id, SmplValue value) {
    dictionary.put(id, value);
    }

    /**
     * Return the int associated with the given identifier.
     *
     * @param id the identifier.
     * @return the int associated with the identifier in
     * this environment.
     * @exception Exception if <code>id</code> is unbound
     */
    public SmplValue get(String id) throws Exception {
    SmplValue result = (SmplValue) dictionary.get(id);
    if (result == null)
        if (parent == null)
        throw new Exception("Unbound variable " + id);
        else
        return parent.get(id);
    else
        return result;
    }

/*
    public String toString() {
    StringBuffer result = new StringBuffer();

    Iterator iter = dictionary.keySet().iterator();
    while (iter.hasNext()) {
        result = result.append(iter.next().toString());
    }
    return result.toString();
    }
*/


}
