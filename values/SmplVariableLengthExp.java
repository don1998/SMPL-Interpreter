package smpl.values;

import smpl.sys.*;
import java.util.ArrayList;

public class SmplVariableLengthExp extends SmplValue<SmplVariableLengthExp> {

    ArrayList<SmplValue<?>> values;

    public SmplVariableLengthExp(ArrayList<SmplValue<?>> values){
        this.values = values;
    }

    @Override
    public SmplType getType() {
        return SmplType.VLENEXP;
    }

    public ArrayList<SmplValue<?>> getValues() {
        return values;
    }

    @Override
    public boolean equal(SmplValue<?> value) throws SmplException {
        if((value instanceof SmplVariableLengthExp)==false){
            return false;
        }
        else{
            return ((SmplVariableLengthExp)value).values.equals(this.values);
        }
    }

    @Override
    public String toString() {
        String result = "";
        for(int i=0; i< values.size(); i++){
            SmplValue<?> value = values.get(i);
            result += value;
            if(i != (values.size() - 1))
                result += ", ";
        }

        return result;
    }
}