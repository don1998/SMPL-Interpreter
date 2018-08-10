/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.sys;

import smpl.syntax.ASTNode;
import smpl.values.SmplType;


public class SmplTypeException extends SmplException {
    private static final long serialVersionUID = 1L;
    
    SmplType expectedType;
    SmplType actualType;
    
    public SmplTypeException(SmplType expected, SmplType actual) {
        super("Type Error: Expected a " + expected + ", but got a " + actual);
        expectedType = expected;
        actualType = actual;
    }
    
    public SmplTypeException(ASTNode src, SmplType expected, SmplType actual) {
        this(expected, actual);
        setSource(src);
    }

    public SmplTypeException(String expected, String actual) {
        super("Type Error: Expected a " + expected + ", but got a " + actual);
    }

    public SmplTypeException(String result){
        super(result);
    }
    
    public SmplTypeException(ASTNode src, String expected, String actual) {
        this(expected, actual);
        setSource(src);
    }
}
