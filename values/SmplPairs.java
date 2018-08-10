package smpl.values;

import smpl.sys.SmplException;

public class SmplPairs extends SmplValue<SmplPairs> {
    public SmplValue<?> left;
    public SmplValue<?> right;

    public SmplPairs(SmplValue<?> left, SmplValue<?> right){
        this.left = left;
        this.right = right;
    }

    public SmplPairs() {
    }

    public SmplValue<?> getLeft() {
        return left;
    }

    public SmplValue<?> getRight() {
        return right;
    }


    public void setLeft(SmplValue<?> left) {
        this.left = left;
    }

    public void setRight(SmplValue<?> right) {
        this.right = right;
    }

    public boolean equal(SmplPairs pair) throws SmplException {
        if(pair instanceof SmplPairs) {
            if ((this.getLeft() == pair.getLeft()) && (this.getRight() == pair.getRight())) {
            	return true;
            }
            else
            	return false;
        } 
    	else 
    		return false;
    }

    @Override
    public SmplType getType() {
        return SmplType.PAIRS;
    }

    @Override
    public String toString() {
        return "(" + left + " , " + right + ")";
    }
}