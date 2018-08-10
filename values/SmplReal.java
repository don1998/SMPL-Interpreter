package smpl.values;

import smpl.sys.*;


public class SmplReal extends SmplValue<SmplReal> {
    
    double value;

    public SmplReal() {
       this(0D);
    }

    public SmplReal(Double v) {
        value = v;
    }
    
    @Override
    public SmplType getType() {
        return SmplType.REAL;
    }
    
    @Override
    public SmplReal add(SmplValue<?> arg) throws SmplException {
        return make(value + arg.doubleValue());
    }

    @Override
    public SmplReal sub(SmplValue<?> arg) throws SmplException {
        return make(value - arg.doubleValue());
    }

    @Override
    public SmplReal mul(SmplValue<?> arg) throws SmplException {
        return make(value * arg.doubleValue());
    }

    @Override
    public SmplReal div(SmplValue<?> arg) throws SmplException {
        return make(value / arg.doubleValue());
    }

    @Override
    public SmplReal mod(SmplValue<?> arg) throws SmplException {
        if (arg.isInteger()) {
            return make(value % arg.intValue());
        } else {
            return make(value % arg.doubleValue());
        }
    }

    @Override
    public SmplReal pow(SmplValue<?> arg) throws SmplException {
        double res = 1;

        if (arg.isInteger()) {
            for (int i = 0; i < arg.intValue(); i++) {
                res *= value;
            }
        } else {
            for (int i = 0; i < arg.doubleValue(); i++) {
                res *= value;
            }
        }

        return make(res);
    }

    @Override
    public SmplValue<?> bitwiseOr(SmplValue<?> arg) throws SmplException {
        return make(intValue() | arg.intValue());
    }

    @Override
    public SmplValue<?> bitwiseAnd(SmplValue<?> arg) throws SmplException {
        return make(intValue() & arg.intValue());
    }

    public static SmplValue<?> bitwiseNot(SmplValue<?> arg) throws SmplException {
        return make(~arg.intValue());
    }
    
    @Override
    public int intValue() {
        return (int) value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}