package smpl.values;

import smpl.sys.SmplException;

public class SmplList extends SmplPairs {

    SmplValue<?> left;
    SmplValue<?> right;

    public SmplList(SmplValue<?> left, SmplValue<?> right){
        this.left = left;
        this.right = right;
    }

    public SmplValue<?> getLeft() {
        return left;
    }

    public SmplValue<?> getRight() {
        return right;
    }

    public void setRight(SmplValue<?> val) {
        right = val;
    }

    public void setLeft(SmplValue<?> val) {
        left = val;
    }

    public SmplList concat(SmplList subList){
        if(left == null && right == null)
            return subList;

        SmplValue<?> value = right;
        SmplPairs pairAbove = this;
        while (value instanceof SmplPairs) {
            pairAbove = (SmplPairs) value;
            value = ((SmplPairs) value).getRight();
        }

        pairAbove.setRight(subList);

        return this;
    }


    @Override
    public SmplType getType() {
        return SmplType.LIST;
    }

    @Override
    public String toString() {
        String result = "[";
        if(left == null){
            result += "]";
            return result; //if list is empty just return []
        } else {
            result += left; //otherwise result=the rest of the list
        }

        SmplValue<?> value = right;  
        SmplValue<?> value2 = null;
        while(true){
            if (value == null){ 
                result += "]";
                break;
            } else {
                value2 = ((SmplPairs)value).getLeft();
                if(value2 != null)
                    result += ", " + value2;
                value = ((SmplPairs)value).getRight();
            }
        }
        return  result;
    }
}