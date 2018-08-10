/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.sys;

import smpl.syntax.ASTNode;

public class SmplException extends Exception {
    private static final long serialVersionUID = 1L;
    
    private ASTNode source;
    
    public SmplException() {
        super();
    }
    
    public SmplException(String message) {
        super (message);
    }
    
    public SmplException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public SmplException(ASTNode node, String message) {
        super(message);
        source = node;
    }
    
    public SmplException(ASTNode node, String message, Throwable cause) {
        super(message, cause);
        source = node;
    }
    
    public ASTNode getSource() {
        return source;
    }
    
    protected void setSource(ASTNode src) {
        source = src;
    }
}
