import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.util.*;

public class RegisterClient {
    Register regObj = null;
    public Car action(Car aCar) {
        try {
            regObj = (Register) Naming.lookup("rmi://localhost:1234/RegisterService");
            String plate = regObj.register(aCar);
            aCar.getRegistered(plate);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
        return aCar;
    }

    public static void main(String[] args) {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("Honda", "White", 10000));
        carList.add(new Car("Toyota", "Black", 123123));
        carList.add(new Car("Suzuki", "Green", 4532534));
        carList.forEach(System.out::println);
        RegisterClient rc = new RegisterClient();
        carList.forEach(rc::action);
        System.out.println("Registered cars: ");
        carList.forEach(System.out::println);
    }
}
