import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject; 
import java.util.*;  

public class Server extends ImplExample { 
   public Server() {} 
   public static void main(String args[]) { 
      System.setProperty("java.rmi.server.hostname", "142.58.15.106");
   
      try { 
         // Instantiating the implementation class 
         ImplExample obj = new ImplExample(); 
    
         // Exporting the object of implementation class  
         // (here we are exporting the remote object to the stub) 
         ImplInterface stub = (ImplInterface) UnicastRemoteObject.exportObject(obj, 0);  
         
         // Binding the remote object (stub) in the registry 
         // Registry registry = LocateRegistry.getRegistry();
         Registry registry = LocateRegistry.createRegistry(5656);
          
         
         registry.bind("Impl", stub);  
         System.out.println("Server ready"); 
      } catch (Exception e) { 
         System.err.println("Server exception: " + e.toString()); 
         e.printStackTrace(); 
      } 
   } 
} 