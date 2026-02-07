import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Booking extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private Map<String, Double> palacePriceMap = new HashMap<>();

    public Booking() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(255, 248, 220));

        loadPalaces();

        JButton bookBtn = new JButton("Book Now");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        JPanel btnPanel = new JPanel();
        btnPanel.setBorder(BorderFactory.createTitledBorder("Booking Controls"));
        btnPanel.add(bookBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
                add(btnPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"Client", "Palace", "Date", "Days", "Total Price"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadBookings();

        bookBtn.addActionListener(e -> new BookingFrame(this, palacePriceMap).setVisible(true));

        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                model.setValueAt(JOptionPane.showInputDialog("Enter Client:"), row, 0);
                model.setValueAt(JOptionPane.showInputDialog("Enter Palace:"), row, 1);
                model.setValueAt(JOptionPane.showInputDialog("Enter Date:"), row, 2);
                model.setValueAt(JOptionPane.showInputDialog("Enter Days:"), row, 3);
                model.setValueAt(JOptionPane.showInputDialog("Enter Total Price:"), row, 4);
                saveBookings();
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                model.removeRow(row);
                saveBookings();
            }
        });
    }

    public void addBooking(String client, String palace, String date, String days, String total) {
        model.addRow(new String[]{client, palace, date, days, total});
        saveBookings();
    }

    public boolean checkAvailability(String palace, String date, int days) {
        return isPalaceAvailable(palace, date, days);
    }

    private boolean isPalaceAvailable(String palace, String newStartStr, int newDays) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date newStart = sdf.parse(newStartStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(newStart);
            cal.add(Calendar.DATE, newDays);
            Date newEnd = cal.getTime();

            for (int i = 0; i < model.getRowCount(); i++) {
                String existingPalace = model.getValueAt(i, 1).toString();
                if (!existingPalace.equals(palace)) continue;

                String existingStartStr = model.getValueAt(i, 2).toString();
                int existingDays = Integer.parseInt(model.getValueAt(i, 3).toString());

                Date existingStart = sdf.parse(existingStartStr);
                Calendar ec = Calendar.getInstance();
                ec.setTime(existingStart);
                ec.add(Calendar.DATE, existingDays);
                Date existingEnd = ec.getTime();

                if (newStart.before(existingEnd) && newEnd.after(existingStart)) {
                    return false; // overlap detected
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private void loadPalaces() {
        File file = new File("palaces.txt");
        if (!file.exists()) return;

        palacePriceMap.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", -1);
                if (parts.length >= 3) {
                    palacePriceMap.put(parts[0], Double.parseDouble(parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveBookings() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bookings.txt"))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                writer.write(model.getValueAt(i, 0) + ";" +
                        model.getValueAt(i, 1) + ";" +
                        model.getValueAt(i, 2) + ";" +
                        model.getValueAt(i, 3) + ";" +
                        model.getValueAt(i, 4));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBookings() {
        File file = new File("bookings.txt");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                model.addRow(line.split(";", -1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
