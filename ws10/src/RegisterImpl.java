import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RegisterImpl extends UnicastRemoteObject implements Register {

    protected RegisterImpl() throws RemoteException {
        super();
    }

    @Override
    public String register(Car aCar) throws RemoteException {
        return String.valueOf(aCar.hashCode());
    }
    //take a car object and generate plate number
}
