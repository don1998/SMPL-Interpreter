package smpl.values;

import smpl.sys.*;

public class SmplBoolean extends SmplValue<SmplBoolean> {

    boolean value;

    public SmplBoolean(){
       this(false);
    }

    public SmplBoolean(boolean value){
        this.value = value;
    }

    @Override
    public SmplType getType() {
        return SmplType.BOOLEAN;
    }

    @Override
    public SmplValue<?> logicalAnd(SmplValue<?> arg) throws SmplException {
        boolean result = value && arg.boolValue();
        return make(result);
    }

    @Override
    public SmplBoolean logicalOr(SmplValue<?> arg) throws SmplException{
        boolean result = value || arg.boolValue();
        return make(result);
    }

    public static SmplValue<?> logicalNot(SmplValue<?> arg) throws SmplException {
        boolean result = !arg.boolValue();
        return make(result);
    }

    @Override
    public boolean equal(SmplValue<?> value) throws SmplException {
        return this.value == value.boolValue();
    }

    @Override
    public boolean boolValue() throws SmplTypeException {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}