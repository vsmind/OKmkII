/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import java.io.Serializable;

/**
 *
 * @author Vitaly
 */
public class ConnectionResult implements Serializable
{

    boolean result;

    public ConnectionResult()
    {}
    
    public ConnectionResult(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    } 
    
}
