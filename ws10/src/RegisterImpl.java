import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RegisterImpl extends UnicastRemoteObject implements Register {

    protected RegisterImpl() throws RemoteException {
        super();
    }

    @Override
    public Car register(Car aCar) throws RemoteException {
        aCar.getRegistered(String.valueOf(aCar.hashCode()));
        return aCar;
    }
    //take a car object and generate plate number
}
