import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Register extends Remote {
    //public abstrct by defautl
    String register(Car aCar) throws RemoteException;
}
