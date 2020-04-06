import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Register extends Remote {
    //public abstrct by defautl
    Car register(Car aCar) throws RemoteException;
}
