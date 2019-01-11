import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;
import java.util.*;  

public class Client {  
    private Client() {}  
    public static void main(String[] args) {  
        Client client = new Client();

        long startTimeInMillis = System.currentTimeMillis();

        long serverTimeInMillis = client.callServer();

        long endTimeInMillis = System.currentTimeMillis();

        long calculatedClientTime = (endTimeInMillis - startTimeInMillis) / 2 + serverTimeInMillis;
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calculatedClientTime);

        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);
        int msec = calendar.get(Calendar.MILLISECOND);

        System.out.println(hour + ":" + min + ":" + sec + ":" + msec);
    } 

    public long callServer() {
        long timeInMillis = 0;
        try {  
            // Getting the registry 
            Registry registry = LocateRegistry.getRegistry(null); 

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
        return timeInMillis;
    }

    public static boolean isSameTime(Date serverDate) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        return date.equals(serverDate);
    }
}