import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private Palace palacePanel;
    private Booking bookingPanel;

    public ControlPanel() {
        setTitle("Palace Booking Management System");
        setSize(800, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));


        JLabel titleLabel = new JLabel("Palace Booking Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 26));
        titleLabel.setForeground(new Color(0, 51, 102));

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        JRadioButton palaceBtn = new JRadioButton("Palace");
        JRadioButton bookingBtn = new JRadioButton("Booking");
        palaceBtn.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(palaceBtn);
        group.add(bookingBtn);
        navPanel.add(palaceBtn);
        navPanel.add(bookingBtn);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(navPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        palacePanel = new Palace();
        bookingPanel = new Booking();
        contentPanel.add(palacePanel, "Palace");
        contentPanel.add(bookingPanel, "Booking");
        add(contentPanel, BorderLayout.CENTER);


        palaceBtn.addActionListener(e -> cardLayout.show(contentPanel, "Palace"));
        bookingBtn.addActionListener(e -> cardLayout.show(contentPanel, "Booking"));
    }
}
