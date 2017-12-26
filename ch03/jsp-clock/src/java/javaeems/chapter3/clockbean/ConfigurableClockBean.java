/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeems.chapter3.clockbean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author barcvilla
 */
public class ConfigurableClockBean 
{
    String dateFormat = "EEEEEEEE";
    
    public long getCurrentTimeSinceEpoch()
    {
        return System.currentTimeMillis();
    }
    
    public void setDateFormat(String dateFormat)
    {
        this.dateFormat = dateFormat;
    }
    
    public String getReadableDate()
    {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(this.dateFormat);
        String today = sdf.format(now);
        return today;
    }
}
