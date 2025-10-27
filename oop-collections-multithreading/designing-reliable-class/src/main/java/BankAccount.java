public class BankAccount {
    private String ownerName;
    private double balance;

    public BankAccount(String ownerName) {
        validateOwnerName(ownerName);

        this.ownerName = ownerName;
        this.balance = 0.0;
    }


    public BankAccount(String ownerName, double balance) {
        validateOwnerName(ownerName);
        validateBalance(balance);

        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        validateOwnerName(ownerName);

        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        validateBalance(balance);

        this.balance = balance;
    }

    private void validateOwnerName(String ownerName) {
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Не задано имя пользователя");
        }
    }

    private void validateBalance(double balance) {
        if (balance < 0.0) {
            throw new IllegalArgumentException("Баланс не может быть отрицательным");
        }
    }
}
