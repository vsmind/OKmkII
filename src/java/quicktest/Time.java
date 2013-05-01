/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quicktest;

import com.google.gson.Gson;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Vitaly
 */
public class Time {

    public Time()
    {
        Calendar cal = Calendar.getInstance();
        Date now;
        now = cal.getTime();
        
        ht h = new ht();
        
        Gson gson = new Gson();
        
        String a = gson.toJson(h);
        
        
        System.out.println(a);
    }
}

class ht{
    Calendar cal = Calendar.getInstance();
    
    private int hour = cal.get(Calendar.HOUR_OF_DAY);
    private int min = cal.get(Calendar.MINUTE);
    
}
