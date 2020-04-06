import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.util.Scanner;

public class RegisterClient {
    public static void main(String[] args)  {
        Register regObj = null;
        Car aCar =  new Car("Honda", "White", 10000);
        System.out.println(aCar);
        try{
            regObj = (Register) Naming.lookup("rmi://localhost:1234/RegisterService");
            aCar = regObj.register(aCar);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
        System.out.println(aCar);

    }
}
