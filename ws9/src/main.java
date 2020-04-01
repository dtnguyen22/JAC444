import javafx.util.Pair;

import java.util.*;

public class main {
    public static void main(String[] args) {
        List<Pair<String, Double>> depositEntry = new ArrayList<>();
        depositEntry.add(new Pair<>("Dollar", 1.0));
        depositEntry.add(new Pair<>("Euros", 1.0));
        depositEntry.add(new Pair<>("Euros", 1.0));
        depositEntry.add(new Pair<>("Pounds", 1.0));
        depositEntry.add(new Pair<>("Pounds", 1.0));
        depositEntry.add(new Pair<>("Pounds", 1.0));

        double witAmount = 1;
        BankAccount sharedBankAcc = new BankAccount();
        Deposit dep = new Deposit(sharedBankAcc, depositEntry);
        Withdraw wit = new Withdraw(sharedBankAcc, depositEntry, witAmount);
        dep.start();
        wit.start();
    }
}


class Deposit extends Thread {
    private BankAccount depositAccount;
    private List<Pair<String, Double>> depositEntry;

    public Deposit(BankAccount aBankAcc, List<Pair<String, Double>> depositEntry) {
        super("Deposit money process");//naming thread
        this.depositEntry = depositEntry;
        this.depositAccount = aBankAcc;
    }

    @Override
    public void run() {
        for (Pair<String, Double> anEntry : this.depositEntry) {
            try {
                Thread.sleep((int) (Math.random() * 3000));
            } catch (InterruptedException e) {
                System.err.println(e);
            }
            depositAccount.deposit(anEntry.getKey(), anEntry.getValue());
        }
    }
}

class Withdraw extends Thread {
    private BankAccount withdrawAccount;
    private List<Pair<String, Double>> depositEntry;
    private double witAmount;

    public Withdraw(BankAccount aBankAcc, List<Pair<String, Double>> depositEntry, double witAmount) {
        super("With draw money process");//naming thread
        this.withdrawAccount = aBankAcc;
        this.depositEntry = depositEntry;
        this.witAmount = witAmount;
    }

    @Override
    public void run() {
        int counter = 0;
        //because we can only deposit 1 at a time and withdraw 1 and a time
        // this loop will stop when withdraw time equals to deposit time
        do {
            try {
                Thread.sleep((int) (Math.random() * 3000));
            } catch (InterruptedException e) {
                System.err.println(e);
            }
            this.withdrawAccount.withdraw(witAmount);
            counter++;
        } while (counter < this.depositEntry.size());
    }
}

class BankAccount {
    private String currency = "";
    private double balance = 0;
    private boolean hasToDeposit = true;
    //at the beginning of the program execution, there is no money in bank account
    //that's why hasToDeposit flag is on, which will trigger wait() inside while()
    //and it won't allow withdraw process to finish up until deposit process finished its job

    private void setCurrency(String currency) {
        this.currency = currency;
    }

    private String getCurrency() {
        return currency;
    }

    private double getBalance() {
        return balance;
    }

    private void setBalance(double oBalance) {
        this.balance = oBalance;
    }

    synchronized public void deposit(String currency, double amount) {
        while (!this.hasToDeposit) {
            //the first process of all processes will never go here
            //program instruction will only go here if balance = 0
            try {
                //if a deposit process comes after another deposit process, the first one will go inside ELSE
                //because at that point this.currency == ""
                //the next one will go inside IF in which this.currency is already set to a value
                //we only allow same currency deposit
                if (!this.getCurrency().equals("") && !this.getCurrency().equals(currency)) {
                    System.out.printf("%s - currency does not match, current: %s - incoming: %s\n", Thread.currentThread().getName(), this.getCurrency(), currency);
                } else { //current balance == 0 || upcoming currency is the same as current one
                    break;// break the while loop to update balance and set currency, don't need to wait()
                }
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //if the first process of all is deposit process, it will go right into here
        this.setBalance(this.getBalance() + amount);
        this.setCurrency(currency);
        System.out.printf("%s: deposited %s %s - balance: %s\n", Thread.currentThread().getName(), amount, currency, this.getBalance());
        this.hasToDeposit = false;// done deposited, allows next deposit or withdraw process to execute
        notify();//tell other processes that shared bank account is ready to use
    }

    synchronized public void withdraw(double witAmount) {
        //if hasToDeposit flag is on, it means you are not ready to withdraw
        while (this.hasToDeposit) {
            try {
                System.out.printf("%s - no money in your account, please wait for deposit\n", Thread.currentThread().getName());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //program instruction reach to this point only if hasToDeposit = false
        // which means there is at least 1 completed deposit process -> we have money to withdraw
        if(this.getBalance() > 0){
            this.setBalance(this.getBalance() - witAmount);//balance - 1
        }
        if(this.getBalance() == 0){
            this.setCurrency(""); //currency goes blank
            this.hasToDeposit = true; //after withdraw all money
        }
        System.out.printf("%s: withdrew %s %s - balance: %s\n", Thread.currentThread().getName(), witAmount, this.getCurrency(), this.getBalance());
        notify(); //tell deposit process it is good to go
    }
}
