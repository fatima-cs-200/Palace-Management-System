import javax.swing.*;
import java.awt.*;

public class AddPalace extends JFrame {
    public AddPalace(Palace palacePanel) {
        setTitle("Add Palace");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(230, 240, 250));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        JLabel nameLabel = new JLabel("Palace Name:");

        JLabel locationLabel = new JLabel("Location:");
        JLabel priceLabel = new JLabel("Price per Day:");



        nameLabel.setFont(labelFont);
        locationLabel.setFont(labelFont);
        priceLabel.setFont(labelFont);

        JTextField nameField = new JTextField();
        JTextField locationField = new JTextField();
        JTextField priceField = new JTextField();

        nameField.setFont(fieldFont);
        locationField.setFont(fieldFont);
        priceField.setFont(fieldFont);

        JButton saveBtn = new JButton("Save Palace");
        saveBtn.setFont(new Font("Arial", Font.BOLD, 13));
        saveBtn.setBackground(new Color(173, 216, 230));
        saveBtn.setForeground(Color.BLACK);

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(locationLabel);
        panel.add(locationField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(new JLabel());
        panel.add(saveBtn);

        add(panel);

        saveBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String location = locationField.getText().trim();
            String price = priceField.getText().trim();

            if (!name.isEmpty() && !location.isEmpty() && !price.isEmpty()) {
                palacePanel.addPalace(name, location, price);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}
