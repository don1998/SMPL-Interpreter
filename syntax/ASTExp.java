package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

import java.util.ArrayList;

public class ASTExp extends ASTStatement {
    private ArrayList<ASTExp> values;

    public ASTExp(){
    	
    }

    public ASTExp(ArrayList<ASTExp> values) {
        this.values = values;
    }

    public ArrayList<ASTExp> getValues() {
        return values;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTExp(this, state);
    }


    @Override
    public String toString() {
        String result = "";
        for(int i=0; i< values.size(); i++){
            ASTExp value = values.get(i);
            result += value;
            if(i != (values.size() - 1))
                result += ", ";
        }

        return result;
    }

}
