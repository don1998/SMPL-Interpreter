
package smpl.values;

import smpl.sys.SmplException;


public class SmplInt extends SmplValue<SmplInt> {
    
    int value;

    public SmplInt() {
        this(0);
    }

    public SmplInt(Integer v) {
        value = v;
    }
    
    @Override
    public SmplType getType() {
        return SmplType.INTEGER;
    }
    
    @Override
    public SmplInt add(SmplValue<?> arg) throws SmplException {
        return make(value + arg.intValue());
    }

    /**
     * Subtract the given value from this value.
     * @param arg The value to be subtracted
     * @return The difference as a new instance of SmplValue
     * @throws smpl.sys.SmplException
     */
    @Override
    public SmplInt sub(SmplValue<?> arg) throws SmplException {
        return make(value - arg.intValue());
    }

    /**
     * Multiply the given value by this value.
     * @param arg The multiplicand
     * @return The product as a new instance of SmplValue
     * @throws smpl.sys.SmplException
     */
    @Override
    public SmplInt mul(SmplValue<?> arg) throws SmplException {
        return make(value * arg.intValue());
    }

    /**
     * Divide the given value by this value.
     * @param arg The divisor
     * @return The quotient as a new instance of SmplValue
     * @throws smpl.sys.SmplException
     */
    @Override
    public SmplInt div(SmplValue<?> arg) throws SmplException {
        return make(value / arg.intValue());
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The divisor
     * @return The residue modulo arg as a new instance of SmplValue
     * @throws smpl.values.TypeSmplException
     */
    @Override
    public SmplInt mod(SmplValue<?> arg) throws SmplException {
        if (arg.isInteger()) {
            return make(value % arg.intValue());
        } else {
            return make(value % arg.intValue());
        }
    }

    /**
     * Compute the result of raising this value to the given exponent.
     * @param arg The exponent
     * @return The result as a new instance of SmplValue
     * @throws smpl.values.TypeSmplException
     */
    @Override
    public SmplInt pow(SmplValue<?> arg) throws SmplException {
        int res = 1;
        for (int i = 0; i < arg.intValue(); i++) {
            res *= value;
        }
        return make(res);
    }

    @Override
    public SmplValue<?> bitwiseOr(SmplValue<?> arg) throws SmplException {
        return make(value | arg.intValue());
    }

    @Override
    public SmplValue<?> bitwiseAnd(SmplValue<?> arg) throws SmplException {
        return make(value & arg.intValue());
    }


    public static SmplValue<?> bitwiseNot(SmplValue<?> arg) throws SmplException {
        if(arg.intValue() >= 0) {
            double logargbase2 = Math.log10(arg.intValue()) / Math.log10(2);
            double ceiling = Math.ceil(logargbase2);
            double exponent = Math.pow(2, ceiling);
            int mask = (int) exponent - 1;
            return make(~arg.intValue() & mask);
        }
        else
            return make(~arg.intValue());
    }

    @Override
    public int intValue() {
        return value;
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