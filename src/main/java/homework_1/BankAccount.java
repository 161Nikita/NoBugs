package homework_1;

public class BankAccount {

    String owner;
    int balance;

    BankAccount(String someOwner, int someBalance) {
        this.balance = someBalance;
        this.owner = someOwner;
    }

    String getOwner() {
        return this.owner;
    }

    int getBalance() {
        return this.balance;
    }

    void setOwner(String newOwner) {
        this.owner = newOwner;
    }

    void deposit(int amount) {
        this.balance = this.balance + amount;
    }

    void withdraw(int amount) {
        if (amount > this.balance) {
            System.out.println("Нельзя снять больше, чем есть на балансе");
        } else {
            this.balance = this.balance - amount;
        }
    }

    void printBalance() {
        System.out.println("Баланс равен: " + this.balance);
    }
}