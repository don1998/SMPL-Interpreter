/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.sys;

import smpl.syntax.ASTNode;

/**
 *
 * @author newts
 */
public class SmplUnboundException extends SmplException {
    private static final long serialVersionUID = 1L;

    public SmplUnboundException(String message) {
        super(message);
    }

    public SmplUnboundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmplUnboundException(ASTNode node, String message) {
        super(node, message);
    }

    public SmplUnboundException(ASTNode node, String message, Throwable cause) {
        super(node, message, cause);
    }
    
    public SmplUnboundException(ASTNode node) {
        super(node, "Unbound variable " + node);
    }
}
