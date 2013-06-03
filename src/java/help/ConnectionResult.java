/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import java.io.Serializable;

/**
 * Help class which is used to create the jSON object for Android client
 * that used in connection
 * @author Vitaly
 */
public class ConnectionResult implements Serializable
{

    boolean result;
    int userID;
    
    public ConnectionResult()
    {}
    
    public ConnectionResult(boolean result) {
        this.result = result;
    }
    
    public ConnectionResult(boolean result, int userID){
        this.result = result;
        this.userID = userID;
    }
            
    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    } 

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
