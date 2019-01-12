import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;

import java.util.*;  

public class Client {  
    private static final String IP_ADDRESS = "142.58.15.192";
    private static final int PORT = 5656;
    private Client() {

    }  
    public static void main(String[] args) {  
        Client client = new Client();

        long startTimeInMillis = System.currentTimeMillis();
        long serverTimeInMillis = client.callServer();
        long endTimeInMillis = System.currentTimeMillis();
        long calculatedClientTime = (endTimeInMillis - startTimeInMillis) / 2 + serverTimeInMillis;
        
        Calendar calendar = Calendar.getInstance();
        // calendar.setTimeInMillis(calculatedClientTime);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);

        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);
        int msec = calendar.get(Calendar.MILLISECOND);

        // set windows system time        
        setWindowsTime(calendar.getTime());

        System.out.println(hour + ":" + min + ":" + sec + ":" + msec);
    } 

    public long callServer() {
        long timeInMillis = 0;
        try {  
            // Getting the registry 
            Registry registry = LocateRegistry.getRegistry(IP_ADDRESS, PORT); 
            // Registry registry = LocateRegistry.getRegistry(null); 

            // Looking up the registry for the remote object 
            ImplInterface stub = (ImplInterface) registry.lookup("Impl"); 

            // Calling the remote method using the obtained object 
            Date serverDate = stub.getServerTime();
            
            System.out.println("Server timestamp received.");
            timeInMillis = serverDate.getTime();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString()); 
            e.printStackTrace(); 
        } 

        // TODO: we need to return serverDate here instead, and then apply latency to that time
        // and then use the result date to set the client's system time
        return timeInMillis;
    }

    public static boolean isSameTime(Date serverDate) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        return date.equals(serverDate);
    }

    private static void setWindowsTime(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yy-MM-dd");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");

        String strDateToSet = dateFormatter.format(date);        
        String strTimeToSet = timeFormatter.format(date);

        try {
            // change the system time using native commands, for windows, date and time need
            // to be set seperately
            Runtime.getRuntime().exec("cmd /C date " + strDateToSet); // dd-MM-yy
            Runtime.getRuntime().exec("cmd /C time " + strTimeToSet); // hh:mm:ss
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
