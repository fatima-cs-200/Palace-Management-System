import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class Palace extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public Palace() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(224, 255, 255));

        JButton addBtn = new JButton("Add Palace");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Palace Controls"));
        inputPanel.add(addBtn);
        inputPanel.add(updateBtn);
        inputPanel.add(deleteBtn);
        add(inputPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"Name", "Location", "Price/Day"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadPalaces();

        addBtn.addActionListener(e -> new AddPalace(this).setVisible(true));

        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                model.setValueAt(JOptionPane.showInputDialog("Enter Name:"), row, 0);
                model.setValueAt(JOptionPane.showInputDialog("Enter Location:"), row, 1);
                model.setValueAt(JOptionPane.showInputDialog("Enter Price/Day:"), row, 2);
                savePalaces();
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                model.removeRow(row);
                savePalaces();
            }
        });
    }

    public void addPalace(String name, String location, String price) {
        model.addRow(new String[]{name, location, price});
        savePalaces();
    }

    private void loadPalaces() {
        model.setRowCount(0);
        File file = new File("palaces.txt");
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

    private void savePalaces() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("palaces.txt"))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                writer.write(model.getValueAt(i, 0) + ";" +
                        model.getValueAt(i, 1) + ";" +
                        model.getValueAt(i, 2));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
