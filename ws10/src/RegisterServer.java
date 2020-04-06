import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class RegisterServer {

    public static void main(String[] args) {
        try{
            Register regObj = new RegisterImpl();
            Registry registry = LocateRegistry.createRegistry(1234);
            //bind service name to the object that responsible for handling request
            // localhost/RegisterService
            registry.rebind("RegisterService", regObj);
            System.out.println("Register Service is running");
        }catch (RemoteException e){
            System.err.println(e);
            System.exit(1);
        }
    }
}
