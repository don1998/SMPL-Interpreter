package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ASTVectorDef extends ASTExp {

    ASTExp content;

    public ASTVectorDef(ASTExp content){
        this.content = content;
    }

    public ASTExp getVec(){
        return content;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitASTVectorDef(this, state);
    }

    @Override
    public String toString() {
        String result = content.toString();
        result = result.replace(",", "");
        return "[:" + result + ":]";
    }
}
