
package smpl.values;

import smpl.semantics.Environment;
import smpl.sys.*;
import java.util.*;
import smpl.syntax.*;

public abstract class SmplValue<T extends SmplValue<T>> {

    private static final long serialVersionUID = 1L;

    public static SmplValue NO_VALUE=null;


    public static SmplReal make(Double v) {
        return new SmplReal(v);
    }
    
    public static SmplInt make(Integer v) {
        return new SmplInt(v);
    }

    public static SmplChar make(char v){
        return new SmplChar(v);
    }
    
    public static SmplBoolean make(Boolean v) {
        return new SmplBoolean(v);
    }

    public static SmplString make(String v) {
        return new SmplString(v);
    }
    
    public abstract SmplType getType();
    

    public boolean isInteger() {
        return getType() == SmplType.INTEGER;
    }


    public SmplValue<?> add(SmplValue<?> arg) throws SmplException {
        throw new SmplTypeException("Incorrect parameter type.");
    }

    public SmplValue<?> sub(SmplValue<?> arg) throws SmplException {
            throw new SmplTypeException("Incorrect parameter type.");
    }

    public SmplValue<?> mul(SmplValue<?> arg) throws SmplException {
            throw new SmplTypeException("Incorrect parameter type.");
    }

    public SmplValue<?> div(SmplValue<?> arg) throws SmplException {
            throw new SmplTypeException("Incorrect parameter type.");
    }
    
    public SmplValue<?> mod(SmplValue<?> arg) throws SmplException {
            throw new SmplTypeException("Incorrect parameter type.");
    }

    public SmplValue<?> pow(SmplValue<?> arg) throws SmplException {
            throw new SmplTypeException("Incorrect parameter type.");
    }

    public SmplValue<?> logicalOr(SmplValue<?> arg) throws SmplException {
        throw new SmplTypeException("Incorrect parameter type.");
    }

    public SmplValue<?> logicalAnd(SmplValue<?> arg) throws SmplException {
        throw new SmplTypeException("Incorrect parameter type.");
    }

    public static SmplValue<?> logicalNot(SmplValue<?> arg) throws SmplException {
        throw new SmplTypeException("Incorrect parameter type.");
    }


    public SmplValue<?> bitwiseOr(SmplValue<?> arg) throws SmplException {
        throw new SmplTypeException("Incorrect parameter type.");
    }

    public SmplValue<?> bitwiseAnd(SmplValue<?> arg) throws SmplException {
        throw new SmplTypeException("Operation bAnd called with non-numeric type");
    }

    public static SmplValue<?> bitwiseNot(SmplValue<?> arg) throws SmplException {
        throw new SmplTypeException("Operation bNot called with non-numeric type");
    }


    public SmplValue<?> eqv(SmplValue<?> arg) throws SmplException {
        return make(this == arg);
    }

    public boolean equal(SmplValue<?> value) throws SmplException{
        return true;
    }

    public int intValue() throws SmplTypeException {
        throw new SmplTypeException(SmplType.INTEGER, getType());
    }

    public double doubleValue() throws SmplTypeException {
        throw new SmplTypeException(SmplType.REAL, getType());
    }

    public SmplFunction funValue() throws SmplTypeException {
        throw new SmplTypeException(SmplType.FUNCTION, getType());
    }

    public boolean boolValue() throws SmplTypeException {
        throw new SmplTypeException(SmplType.BOOLEAN, getType());
    }

}