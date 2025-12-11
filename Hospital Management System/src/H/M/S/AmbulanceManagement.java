package H.M.S;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class AmbulanceManagement extends JFrame {

    // Table + model for ambulance list
    private DefaultTableModel tableModel;
    private JTable table;

    public AmbulanceManagement() {

        // ===== BASIC WINDOW SETTINGS =====
        setTitle("Hospital Ambulance - TR Hospital");        // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      // Close behavior
        setSize(1000, 600);                                  // Initial size
        setLocationRelativeTo(null);                         // Center window
        setLayout(new BorderLayout());                       // Responsive base layout[web:85]

        // ===== HEADER (TOP BAR) =====
        JPanel header = new JPanel();
        header.setBackground(new Color(109, 164, 170));      // Teal like other screens
        header.setPreferredSize(new Dimension(1000, 70));    // Fixed height
        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(header, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("AMBULANCE MANAGEMENT");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        header.add(lblTitle);

        // ===== MAIN CONTENT (LEFT FORM + RIGHT TABLE) =====
        JPanel content = new JPanel(new BorderLayout());
        add(content, BorderLayout.CENTER);

        // ------------ LEFT FORM PANEL (GridBagLayout = responsive) ------------
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(248, 249, 250));
        formPanel.setBorder(BorderFactory.createTitledBorder("Ambulance / Booking Details"));
        formPanel.setLayout(new GridBagLayout());            // Flexible form layout[web:106]
        formPanel.setPreferredSize(new Dimension(330, 0));   // Fixed-ish width, height flexible
        content.add(formPanel, BorderLayout.WEST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        int row = 0;

        // --- Ambulance ID ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Ambulance ID:"), gbc);

        JTextField tfAmbId = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfAmbId, gbc);

        // --- Vehicle No (UP14 XX 1234) ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Vehicle No:"), gbc);

        JTextField tfVehicleNo = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfVehicleNo, gbc);

        // --- Driver Name ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Driver Name:"), gbc);

        JTextField tfDriver = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfDriver, gbc);

        // --- Driver Phone ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Driver Phone:"), gbc);

        JTextField tfPhone = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfPhone, gbc);

        // --- Status (Available / On Duty / Maintenance) ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Status:"), gbc);

        JComboBox<String> cbStatus = new JComboBox<>(
                new String[]{"Available", "On Duty", "Maintenance"});
        gbc.gridy = row++;
        formPanel.add(cbStatus, gbc);

        // --- Current Location / Area ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Location:"), gbc);

        JTextField tfLocation = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfLocation, gbc);

        // --- Booking: Patient Name ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Patient Name:"), gbc);

        JTextField tfPatient = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfPatient, gbc);

        // --- Booking: Pickup Address ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Pickup Address:"), gbc);

        JTextField tfPickup = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfPickup, gbc);

        // --- Booking: Emergency / Normal ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Type:"), gbc);

        JComboBox<String> cbType = new JComboBox<>(
                new String[]{"Emergency", "Non-Emergency"});
        gbc.gridy = row++;
        formPanel.add(cbType, gbc);

        // ------------- BUTTON BAR (ADD/UPDATE/BOOK/CLEAR/BACK) -------------
        JPanel buttonBar = new JPanel(new GridLayout(1, 5, 4, 0));
        JButton btnAdd    = new JButton("ADD");
        JButton btnUpdate = new JButton("UPDATE");
        JButton btnBook   = new JButton("BOOK");
        JButton btnClear  = new JButton("CLEAR");
        JButton btnBack   = new JButton("BACK");
        buttonBar.add(btnAdd);
        buttonBar.add(btnUpdate);
        buttonBar.add(btnBook);
        buttonBar.add(btnClear);
        buttonBar.add(btnBack);

        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(buttonBar, gbc);

        // ------------ RIGHT TABLE PANEL (resizable) ------------
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Ambulances List"));
        content.add(tablePanel, BorderLayout.CENTER);

        // Columns: basic ambulance info + status
        String[] cols = {"Amb ID", "Vehicle No", "Driver", "Phone",
                "Status", "Location"};
        tableModel = new DefaultTableModel(cols, 0);
        table = new JTable(tableModel);
        table.setRowHeight(22);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Adjust columns with width[web:147]

        JScrollPane scroll = new JScrollPane(table);
        tablePanel.add(scroll, BorderLayout.CENTER);

        // Demo data rows (baad me DB se la sakte ho)[web:133][web:135]
        tableModel.addRow(new Object[]{"A01", "UP14 AB 1234", "Ramesh", "9876543210", "Available", "TR Hospital"});
        tableModel.addRow(new Object[]{"A02", "UP14 CD 5678", "Suresh", "9876543211", "On Duty", "City Center"});

        // ===== EVENTS =====

        // Table row click: form me data load karo (booking patient fields blank rahenge)
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = table.getSelectedRow();
                if (rowIndex >= 0) {
                    tfAmbId.setText(tableModel.getValueAt(rowIndex, 0).toString());
                    tfVehicleNo.setText(tableModel.getValueAt(rowIndex, 1).toString());
                    tfDriver.setText(tableModel.getValueAt(rowIndex, 2).toString());
                    tfPhone.setText(tableModel.getValueAt(rowIndex, 3).toString());
                    cbStatus.setSelectedItem(tableModel.getValueAt(rowIndex, 4).toString());
                    tfLocation.setText(tableModel.getValueAt(rowIndex, 5).toString());
                }
            }
        });

        // ADD: new ambulance record table me add karo
        btnAdd.addActionListener(e -> {
            if (tfAmbId.getText().trim().isEmpty() || tfVehicleNo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ambulance ID and Vehicle No are required!");
                return;
            }
            tableModel.addRow(new Object[]{
                    tfAmbId.getText(), tfVehicleNo.getText(),
                    tfDriver.getText(), tfPhone.getText(),
                    cbStatus.getSelectedItem(), tfLocation.getText()
            });
            JOptionPane.showMessageDialog(this, "Ambulance added successfully!");
        });

        // UPDATE: selected row update karo
        btnUpdate.addActionListener(e -> {
            int rowIndex = table.getSelectedRow();
            if (rowIndex == -1) {
                JOptionPane.showMessageDialog(this, "Select an ambulance from table to update!");
                return;
            }
            tableModel.setValueAt(tfAmbId.getText(),            rowIndex, 0);
            tableModel.setValueAt(tfVehicleNo.getText(),        rowIndex, 1);
            tableModel.setValueAt(tfDriver.getText(),           rowIndex, 2);
            tableModel.setValueAt(tfPhone.getText(),            rowIndex, 3);
            tableModel.setValueAt(cbStatus.getSelectedItem(),   rowIndex, 4);
            tableModel.setValueAt(tfLocation.getText(),         rowIndex, 5);
            JOptionPane.showMessageDialog(this, "Ambulance details updated!");
        });

        // BOOK: selected ambulance ko “On Duty” mark + basic popup (future me DB insert)
        btnBook.addActionListener(e -> {
            int rowIndex = table.getSelectedRow();
            if (rowIndex == -1) {
                JOptionPane.showMessageDialog(this, "Select an ambulance to book!");
                return;
            }
            String patient = tfPatient.getText().trim();
            String address = tfPickup.getText().trim();
            if (patient.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter patient name and pickup address!");
                return;
            }

            // Status change karke On Duty kar rahe hain
            tableModel.setValueAt("On Duty", rowIndex, 4);
            JOptionPane.showMessageDialog(
                    this,
                    "Ambulance booked for " + patient +
                            "\nPickup: " + address +
                            "\nType: " + cbType.getSelectedItem(),
                    "Booking Confirmed",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Yahi jagah future me: AmbulanceBooking table me INSERT query kar sakte ho.[web:134][web:94]
        });

        // CLEAR: form fields reset
        btnClear.addActionListener(e -> {
            tfAmbId.setText("");
            tfVehicleNo.setText("");
            tfDriver.setText("");
            tfPhone.setText("");
            cbStatus.setSelectedIndex(0);
            tfLocation.setText("");
            tfPatient.setText("");
            tfPickup.setText("");
            cbType.setSelectedIndex(0);
            table.clearSelection();
        });

        // BACK: Reception par wapas
        btnBack.addActionListener(e -> {
            setVisible(false);
            new Reception();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AmbulanceManagement::new);
    }
}
