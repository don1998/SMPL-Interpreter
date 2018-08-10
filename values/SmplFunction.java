package smpl.values;

import smpl.syntax.*;
import smpl.semantics.*;
import smpl.sys.*;

import java.util.ArrayList;

public class SmplFunction extends SmplValue<SmplFunction> {
    ASTStmtFnDefn fnDef;
    Environment<SmplValue<?>> closure;

    public SmplFunction(ASTStmtFnDefn fnDef, Environment<SmplValue<?>> closure) {
        this.fnDef = fnDef;
        this.closure = closure;
    }

    @Override
    public SmplType getType() {
        return SmplType.FUNCTION;
    }

    @Override
    public boolean equal(SmplValue<?> value) throws SmplException {
        return true;
    }

    @Override
    public SmplFunction funValue() {
        return this;
    }

    public ASTStmtFnDefn getFunVar() {
        return fnDef;
    }

    public Environment<SmplValue<?>> getclosure() {
        return closure;
    }
    
    public String toString() {
        return "Function definition: " + getFunVar();
    }
    
}