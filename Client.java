import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;

import java.util.*;

public class Client {
    private static final String IP_ADDRESS = "142.58.15.52";
    private static final int PORT = 5656;

    private Client() {

    }

    public static void main(String[] args) {
        Client client = new Client();

        // get the client time before calling the server
        long startTimeInMillis = System.currentTimeMillis();

        // get the server time in milliseconds
        long serverTimeInMillis = client.getServerTimeInMilliseconds();

        // get the client time after calling the server
        long endTimeInMillis = System.currentTimeMillis();

        // calculate the new client time using client time + RTT / 2
        long calculatedClientTime = (endTimeInMillis - startTimeInMillis) / 2 + serverTimeInMillis;

        // create a new date object based on the calculated time for client
        Calendar newClientTime = Calendar.getInstance();
        newClientTime.setTimeInMillis(calculatedClientTime);

        System.out.println("calculated Client Time in Milliseconds is: " + calculatedClientTime);
        System.out.println("calculated Client Time is: " + newClientTime.getTime());

        // set windows system time using our new date object
        client.setWindowsTime(newClientTime.getTime());
    }

    private long getServerTimeInMilliseconds() {
        long timeInMillis = 0;
        try {
            // Getting the registry
            System.out.println("Connection to server with IP address: " + IP_ADDRESS + ", port: " + PORT);
            Registry registry = LocateRegistry.getRegistry(IP_ADDRESS, PORT);

            // Looking up the registry for the remote object
            ImplInterface stub = (ImplInterface) registry.lookup("Impl");

            // Calling the remote method using the obtained object, getServerTime will
            // return the server time as a date object
            Date serverDate = stub.getServerTime();

            // get the server time in milliseconds to make the calculation easier
            timeInMillis = serverDate.getTime();

            System.out.println("Server time is: " + serverDate);
            System.out.println("Server time in milliseconds is: " + timeInMillis);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

        return timeInMillis;
    }

    private void setWindowsTime(Date date) {
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
