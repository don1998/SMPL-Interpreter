
package smpl.values;

import smpl.sys.SmplException;
import smpl.sys.SmplTypeException;

/**
 *
 * @author newts
 */
public class SmplChar extends SmplValue {
    
    char value;

    public SmplChar(char d) {
        value = d;
    }
    
    public String toString() {
        return "Value: " + value;
    }

    public boolean isChar() {
        return true;
    }
    
    public char charValue() {
        return value;
    }

    @Override
    public SmplType getType() {
        return SmplType.CHAR;
    }

}
