import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankomatGUI {
    private static double eurokontostand = 1000.0; // Aktueller Kontostand
    private static double dollarkontostand = 0;
    private static double rubelkontostand = 0;
    private static String correctPassword = "0000";
    private static int versuch = 3;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bankautomat");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());

        // Login Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 1));
        JLabel passwordLabel = new JLabel("Passwort eingeben:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        frame.add(loginPanel);

        // Main Menu Panel
        JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new GridLayout(7, 1));
        JLabel welcomeLabel = new JLabel("Willkommen! Wählen Sie eine Option:");
        JButton depositButton = new JButton("1. Geld einzahlen");
        JButton transferButton = new JButton("2. Geld überweisen");
        JButton withdrawButton = new JButton("3. Geld abheben");
        JButton balanceButton = new JButton("4. Kontostandabfrage");
        JButton changePinButton = new JButton("5. PIN ändern");
        JButton logoutButton = new JButton("6. Abmelden");

        mainMenu.add(welcomeLabel);
        mainMenu.add(depositButton);
        mainMenu.add(transferButton);
        mainMenu.add(withdrawButton);
        mainMenu.add(balanceButton);
        mainMenu.add(changePinButton);
        mainMenu.add(logoutButton);

        frame.add(mainMenu);

        // ActionListener für Login
        loginButton.addActionListener(e -> {
            String password = new String(passwordField.getPassword());
            if (password.equals(correctPassword)) {
                frame.getContentPane().removeAll();
                frame.add(mainMenu);
                frame.revalidate();
                frame.repaint();
            } else {
                versuch--;
                if (versuch == 0) {
                    JOptionPane.showMessageDialog(frame, "Zu viele falsche Versuche. Zugriff verweigert.");
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(frame, "Falsches Passwort. Verbleibende Versuche: " + versuch);
                }
            }
        });

        // ActionListener für "Geld einzahlen"
        depositButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Wie viel möchten Sie einzahlen?");
            if (input != null) {
                try {
                    double amount = Double.parseDouble(input);
                    if (amount > 0) {
                        eurokontostand += amount;
                        JOptionPane.showMessageDialog(frame, "Einzahlung erfolgreich! Neuer Kontostand: " + eurokontostand + " Euro");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Ungültiger Betrag.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Bitte eine gültige Zahl eingeben.");
                }
            }
        });

        // ActionListener für "Geld überweisen"
        transferButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Wie viel möchten Sie überweisen?");
            if (input != null) {
                try {
                    double amount = Double.parseDouble(input);
                    if (amount > 0 && amount <= eurokontostand) {
                        eurokontostand -= amount;
                        JOptionPane.showMessageDialog(frame, "Überweisung erfolgreich! Neuer Kontostand: " + eurokontostand + " Euro");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Ungültiger Betrag oder nicht genügend Guthaben.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Bitte eine gültige Zahl eingeben.");
                }
            }
        });

        // ActionListener für "Geld abheben"
        withdrawButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Wie viel möchten Sie abheben?");
            if (input != null) {
                try {
                    double amount = Double.parseDouble(input);
                    if (amount > 0 && amount <= eurokontostand) {
                        eurokontostand -= amount;
                        JOptionPane.showMessageDialog(frame, "Abhebung erfolgreich! Neuer Kontostand: " + eurokontostand + " Euro");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Ungültiger Betrag oder nicht genügend Guthaben.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Bitte eine gültige Zahl eingeben.");
                }
            }
        });

        // ActionListener für "Kontostandabfrage"
        balanceButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Ihr Kontostand: " + eurokontostand + " Euro");
        });

        // ActionListener für "PIN ändern"
        changePinButton.addActionListener(e -> {
            String oldPin = JOptionPane.showInputDialog(frame, "Aktuellen PIN eingeben:");
            if (oldPin != null && oldPin.equals(correctPassword)) {
                String newPin = JOptionPane.showInputDialog(frame, "Neuen PIN eingeben:");
                if (newPin != null && !newPin.isEmpty()) {
                    correctPassword = newPin;
                    JOptionPane.showMessageDialog(frame, "PIN erfolgreich geändert.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Ungültiger neuer PIN.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Falscher aktueller PIN.");
            }
        });

        // ActionListener für "Abmelden"
        logoutButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.add(loginPanel);
            frame.revalidate();
            frame.repaint();
        });

        frame.setVisible(true);
    }
}
