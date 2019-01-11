import java.rmi.Remote; 
import java.rmi.RemoteException;
import java.util.Date;  

// Creating Remote interface for our application 
public interface ImplInterface extends Remote {  
   void printMsg() throws RemoteException;  
   Date getServerTime() throws RemoteException;  
} 