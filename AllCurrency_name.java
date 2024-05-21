

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
public class AllCurrency_name extends JFrame {
private JLabel amountLabel, fromLabel, toLabel, resultLabel;
private JTextField amountTextField;
private JComboBox<String> fromComboBox, toComboBox;
private JButton convertButton;
private String[] currencies = {"USD - United States Dollar", "EUR - Euro", "GBP - British Pound
Sterling",
"INR - Indian Rupee", "JPY - Japanese Yen", "AUD - Australian Dollar"};
private double[] conversionRates = {1.0, 0.85, 0.73, 75.0, 110.45, 1.25};
public AllCurrency_name() {
setTitle("Currency Converter");
setSize(400, 250);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
amountLabel = new JLabel("Amount:");
amountTextField = new JTextField(10);
fromLabel = new JLabel("From:");
fromComboBox = new JComboBox<>(currencies);
toLabel = new JLabel("To:");
toComboBox = new JComboBox<>(currencies);
convertButton = new JButton("Convert");
resultLabel = new JLabel();
resultLabel.setVerticalAlignment(SwingConstants.TOP);
resultLabel.setHorizontalTextPosition(SwingConstants.LEFT);
JPanel panel = new JPanel();
GroupLayout layout = new GroupLayout(panel);
panel.setLayout(layout);
layout.setAutoCreateGaps(true);
layout.setAutoCreateContainerGaps(true);
layout.setHorizontalGroup(layout.createSequentialGroup()
.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
.addComponent(amountLabel)
.addComponent(fromLabel)
.addComponent(toLabel))
.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
.addComponent(amountTextField)
.addComponent(fromComboBox)
.addComponent(toComboBox)
.addComponent(convertButton)
.addComponent(resultLabel))
);
layout.setVerticalGroup(layout.createSequentialGroup()
.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
.addComponent(amountLabel)
.addComponent(amountTextField))
.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
.addComponent(fromLabel)
.addComponent(fromComboBox))
.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
.addComponent(toLabel)
.addComponent(toComboBox))
.addComponent(convertButton)
.addComponent(resultLabel)
);
convertButton.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
convertCurrency();
}
});
add(panel);
setVisible(true);
}
private void convertCurrency() {
try {
double amount = Double.parseDouble(amountTextField.getText());
double result = convert(amount, (String) fromComboBox.getSelectedItem(), (String)
toComboBox.getSelectedItem());
DecimalFormat df = new DecimalFormat("#.##");
String resultText = df.format(result);
resultLabel.setText("<html>Converted amount: " + resultText + " " +
getCurrencyCode((String) toComboBox.getSelectedItem()) + "</html>");
} catch (NumberFormatException e) {
resultLabel.setText("Invalid input. Please enter a number.");
}
}
private double convert(double amount, String fromCurrency, String toCurrency) {
int fromIndex = findIndex(fromCurrency);
int toIndex = findIndex(toCurrency);
return amount * conversionRates[toIndex] / conversionRates[fromIndex];
}
private int findIndex(String currency) {
for (int i = 0; i < currencies.length; i++) {
if (currencies[i].startsWith(currency)) {
return i;
}
}
return -1;
}
private String getCurrencyCode(String fullForm) {
return fullForm.substring(0, 3);
}
public static void main(String[] args) {
SwingUtilities.invokeLater(new Runnable() {
@Override
public void run() {
new AllCurrency_name();
}
});
}
}
