import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Server extends ImplExample {
   public Server() {
   }

   private static final String HOST_NAME = "java.rmi.server.hostname";
   private static final String IP_ADDRESS = "142.58.15.106";

   public static void main(String args[]) {
      System.setProperty(HOST_NAME, IP_ADDRESS);

      try {
         // Instantiating the implementation class
         ImplExample obj = new ImplExample();

         // Exporting the object of implementation class
         // (here we are exporting the remote object to the stub)
         ImplInterface stub = (ImplInterface) UnicastRemoteObject.exportObject(obj, 0);

         // Bind the remote object (stub) in the registry
         // Registry registry = LocateRegistry.getRegistry();
         Registry registry = LocateRegistry.createRegistry(5656);

         registry.bind("Impl", stub);
         System.out.println("Server has been started successfully");
      } catch (Exception e) {
         System.err.println("Server exception: " + e.toString());
         e.printStackTrace();
      }
   }
}