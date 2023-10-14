import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class ATMInterface extends JFrame {
    private BankAccount account;

    private JTextField amountField;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton checkBalanceButton;
    private JLabel messageLabel;

    public ATMInterface(BankAccount account) {
        this.account = account;
        setTitle("ATM Interface");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        amountField = new JTextField();
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        checkBalanceButton = new JButton("Check Balance");
        messageLabel = new JLabel();

        add(new JLabel("Enter Amount:"));
        add(amountField);
        add(depositButton);
        add(withdrawButton);
        add(checkBalanceButton);
        add(messageLabel);

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                account.deposit(amount);
                messageLabel.setText("Deposit of ₹" + amount + " successful.");
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                if (account.withdraw(amount)) {
                    messageLabel.setText("Withdrawal of ₹" + amount + " successful.");
                } else {
                    messageLabel.setText("Insufficient balance for withdrawal.");
                }
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double balance = account.getBalance();
                messageLabel.setText("Account balance: ₹" + balance);
            }
        });
    }
}

public class ATM11 {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0); 
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ATMInterface atm = new ATMInterface(userAccount);
                atm.setVisible(true);
            }
        });
    }
}

