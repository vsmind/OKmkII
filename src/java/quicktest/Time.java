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
        Gson gson = new Gson();
        
        cal.add(Calendar.DAY_OF_WEEK, 0);
        String a = gson.toJson(cal);
        System.out.println(a);
        
        cal.add(Calendar.DAY_OF_WEEK, 1);
        a = gson.toJson(cal);
        System.out.println(a);
        
        cal.add(Calendar.DAY_OF_WEEK, 1);
        a = gson.toJson(cal);
        System.out.println(a);
      
        
    }
    
    
}

class ht{
    Calendar cal = Calendar.getInstance();
    
    private int hour = cal.get(Calendar.HOUR_OF_DAY);
    private int min = cal.get(Calendar.MINUTE);
    
}

class Alog{
    private boolean login = true;
}
