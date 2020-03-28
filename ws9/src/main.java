import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class main {
    public static void main(String[] args) {
        TreeMap<String, Double> depositEntry = new TreeMap<>();
        depositEntry.put("Dollar", 1.0);
        depositEntry.put("Euros",  2.0);
        depositEntry.put("Pounds", 3.0);

        BankAccount sharedBankAcc = new BankAccount();
        Deposit dep = new Deposit(sharedBankAcc, depositEntry);
        Withdraw wit = new Withdraw(sharedBankAcc);
        dep.start();
        wit.start();
    }
}


class Deposit extends Thread {
    private BankAccount depositAccount;
    TreeMap<String, Double> depositEntry;
    public Deposit(BankAccount aBankAcc, TreeMap<String, Double> depositEntry){
        super("Deposit money process");//naming thread
        this.depositEntry = depositEntry;
        this.depositAccount = aBankAcc;
    }

    @Override
    public void run() {
        for(Map.Entry<String, Double> anEntry : this.depositEntry.entrySet() ){
            try{
                Thread.sleep((int)(Math.random()*3000));
            } catch (InterruptedException e) {
                System.err.println(e);
            }
            depositAccount.deposit(anEntry.getKey(), anEntry.getValue());
        }

    }
}

class Withdraw extends Thread{
    private BankAccount withdrawAccount;
    TreeMap<String, Double> depositEntry;
    public Withdraw(BankAccount aBankAcc){
        super("With draw money process");//naming thread
        this.withdrawAccount = aBankAcc;
    }

    @Override
    public void run() {
        for(int i = 1; i<= 3; i++){ //temporary
            try{
                Thread.sleep((int)(Math.random()*3000));
            }catch(InterruptedException e){
                System.err.println(e);
            }
            this.withdrawAccount.withdraw();
        }
    }
}

class BankAccount{
    private String currency;
    private double balance = 0;
    private boolean writeable = true;

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public double getBalance() {
        return balance;
    }

    public void updateBalance(double balance) {
        this.balance += balance;
    }
    synchronized void deposit(String currency, double amount){
        //if withdrawable = false, go wait
        while(!writeable){
            try{
                //if this bank account has balance, it has currency as well
//                if(this.getBalance() > 0 ){ //check if currency match
//                    if(!this.getCurrency().equals(currency)){
//                        wait();
//                    }
//                }
                System.out.println("Waiting for withdraw");
                wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //if above conditions are satisfied, below codes will never be ignored
        this.updateBalance(amount);
        this.setCurrency(currency);
        System.out.printf("%s: deposited %s %s \n", Thread.currentThread().getName(), this.getBalance(), this.getCurrency());
        writeable = false; // ready to withdraw
        notify();//tell withdraw() that shared bank account is ready to use
    }
    synchronized void withdraw(){
        while(writeable){
            try{
//                //in case withdraw process is executed before deposit, lets check if there is anything in the bankaccount
//                if(this.getBalance() == 0 ){
//                    System.out.println(Thread.currentThread().getName() + " account is not ready to withdraw");
//                    wait(); //if you are bankrupt, wait bro!!
//                }
                System.out.println("Waiting for deposit");
                wait(); //if you are bankrupt, wait bro!!

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //if withdrawable  = true
        System.out.printf("%s: withdrew %s %s \n", Thread.currentThread().getName(), this.getBalance(), this.getCurrency());
        this.updateBalance(-this.getBalance());//balance goes zero
        this.setCurrency(""); //currency goes blank
        this.writeable = true; //after withdraw all money, balance = 0
        notify();
    }
}
