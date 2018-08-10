package smpl.values;

import smpl.sys.*;
import java.util.ArrayList;

public class SmplVector extends SmplValue<SmplVector> {

    ArrayList<SmplValue<?>> values;

    public SmplVector(ArrayList<SmplValue<?>> values){
        this.values = values;
    }

    public ArrayList<SmplValue<?>> getValues() {
        return values;
    }

    public SmplValue<?> getValue(SmplValue<?> integer) throws SmplException {
        int intValue = integer.intValue();
        return values.get(integer.intValue());
    }

    public SmplInt size(){
        return SmplValue.make(values.size());
    }

    @Override
    public SmplType getType() {
        return SmplType.VECTOR;
    }

    @Override
    public boolean equal(SmplValue<?> value) throws SmplException {
        return true;
    }

    @Override
    public String toString() {
        String result = "[:";
        for(int i=0; i< values.size(); i++){
            SmplValue<?> value = values.get(i);
            result += value;
            if(i != (values.size() - 1))
                result += ", ";
        }
        result += ":]";

        return result;
    }
}