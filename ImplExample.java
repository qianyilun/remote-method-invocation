import java.util.*;

// Implementing the remote interface 
public class ImplExample implements ImplInterface {  
   
    // Implementing the interface method 
    @Override
    public void printMsg() {  
       System.out.println("This is an example RMI program");  
    }  

    @Override
    public Date getServerTime() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        System.out.println("Server unicasted current timestamp"); 
        int hour = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        int msec = cal.get(Calendar.MILLISECOND);

        System.out.println(hour + ":" + min + ":" + sec + ":" + msec);
        return date;
    }
 } 