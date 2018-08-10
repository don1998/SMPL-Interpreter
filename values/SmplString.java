package smpl.values;

import smpl.sys.SmplException;

public class SmplString extends SmplValue<SmplString> {
    String value;
    int decimal;

    public SmplString(){
        this("");
    }

    public SmplString(String value){
        value = value.replace("\\n","\n");
        value = value.replace("\\t","\t");
        value = value.replace("\\\\","\\");
        this.value = value;
    }

    public SmplString substr(int start, int end) throws SmplException {
        if(end < start)
            return new SmplString("");
        else if(start < 0 || start >= value.length())
            throw new SmplException("String index out of bounds");
        else
            return new SmplString(value.substring(start, end));
    }


    public int getDecimal(){
        return decimal;
    }

    @Override
    public SmplType getType() {
        return SmplType.STRING;
    }

    @Override
    public boolean equal(SmplValue<?> value) throws SmplException {
        return value instanceof SmplString && ((SmplString)value).equals(this.value);
    }

    @Override
    public String toString() {
        return value;
    }
}