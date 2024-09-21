import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class CurrencyConverterAWT extends Frame implements ActionListener {

  
    Choice baseCurrencyChoice, targetCurrencyChoice;
    TextField amountField;
    Label resultLabel, statusLabel;


    HashMap<String, Double> exchangeRates;


    public CurrencyConverterAWT() {
   
        setLayout(new GridLayout(6, 2, 10, 10));
        setTitle("Currency Converter");
        setSize(400, 300);


        exchangeRates = new HashMap<>();
        exchangeRates.put("USD_EUR", 0.84);
        exchangeRates.put("EUR_USD", 1.19);
        exchangeRates.put("USD_INR", 74.50);
        exchangeRates.put("INR_USD", 0.013);
        exchangeRates.put("EUR_INR", 88.60);
        exchangeRates.put("INR_EUR", 0.0113);


        add(new Label("Base Currency: "));
        baseCurrencyChoice = new Choice();
        baseCurrencyChoice.add("USD");
        baseCurrencyChoice.add("EUR");
        baseCurrencyChoice.add("INR");
        add(baseCurrencyChoice);

        add(new Label("Target Currency: "));
        targetCurrencyChoice = new Choice();
        targetCurrencyChoice.add("USD");
        targetCurrencyChoice.add("EUR");
        targetCurrencyChoice.add("INR");
        add(targetCurrencyChoice);


        add(new Label("Amount: "));
        amountField = new TextField();
        add(amountField);


        Button convertButton = new Button("Convert");
        convertButton.addActionListener(this);
        add(convertButton);

     
        resultLabel = new Label("");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setForeground(Color.BLUE);
        add(resultLabel);


        statusLabel = new Label("");
        statusLabel.setForeground(Color.RED);
        add(statusLabel);

      
        setVisible(true);

   
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String baseCurrency = baseCurrencyChoice.getSelectedItem();
        String targetCurrency = targetCurrencyChoice.getSelectedItem();
        String amountStr = amountField.getText();

    
        try {
            double amount = Double.parseDouble(amountStr);

            if (baseCurrency.equals(targetCurrency)) {
                statusLabel.setText("Base and target currencies must be different.");
                resultLabel.setText("");
                return;
            }


            String conversionKey = baseCurrency + "_" + targetCurrency;
            if (exchangeRates.containsKey(conversionKey)) {
                double exchangeRate = exchangeRates.get(conversionKey);
                double convertedAmount = amount * exchangeRate;


                resultLabel.setText("Converted Amount: " + String.format("%.2f", convertedAmount) + " " + targetCurrency);
                statusLabel.setText("");
            } else {
                statusLabel.setText("Exchange rate not available.");
                resultLabel.setText("");
            }

        } catch (NumberFormatException ex) {
            statusLabel.setText("Invalid amount. Please enter a numeric value.");
        }
    }

    public static void main(String[] args) {
        new CurrencyConverterAWT();
    }
}
