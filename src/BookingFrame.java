import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class BookingFrame extends JFrame {
    public BookingFrame(Booking bookingPanel, Map<String, Double> priceMap) {
        setTitle("Add Booking");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(255, 245, 230));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        JLabel clientLabel = new JLabel("Client Name:");
        JLabel palaceLabel = new JLabel("Select Palace:");
        JLabel daysLabel = new JLabel("No. of Days:");
        JLabel dateLabel = new JLabel("Booking Date:");

        clientLabel.setFont(labelFont);
        palaceLabel.setFont(labelFont);
        daysLabel.setFont(labelFont);
        dateLabel.setFont(labelFont);

        JTextField clientField = new JTextField();
        JComboBox<String> palaceBox = new JComboBox<>(priceMap.keySet().toArray(new String[0]));
        JTextField daysField = new JTextField();

        clientField.setFont(fieldFont);
        palaceBox.setFont(fieldFont);
        daysField.setFont(fieldFont);

        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        dateSpinner.setFont(fieldFont);

        JButton saveBtn = new JButton("Save Booking");
        saveBtn.setFont(new Font("Arial", Font.BOLD, 13));
        saveBtn.setBackground(new Color(255, 250, 205));
        saveBtn.setForeground(Color.BLACK);

        panel.add(clientLabel);  panel.add(clientField);
        panel.add(palaceLabel);  panel.add(palaceBox);
        panel.add(daysLabel);    panel.add(daysField);
        panel.add(dateLabel);    panel.add(dateSpinner);
        panel.add(new JLabel()); panel.add(saveBtn);

        add(panel);

        saveBtn.addActionListener(e -> {
            String client = clientField.getText().trim();
            String palace = (String) palaceBox.getSelectedItem();
            String daysStr = daysField.getText().trim();
            Date date = (Date) dateSpinner.getValue();
            String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

            if (!client.isEmpty() && !daysStr.isEmpty()) {
                try {
                    int days = Integer.parseInt(daysStr);
                    double price = priceMap.get(palace);
                    double total = days * price;

                    if (!bookingPanel.checkAvailability(palace, formattedDate, days)) {
                        JOptionPane.showMessageDialog(this, "Palace is not available for selected dates.", "Unavailable", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    bookingPanel.addBooking(client, palace, formattedDate, String.valueOf(days), String.valueOf(total));
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid number for days.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please complete all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}
