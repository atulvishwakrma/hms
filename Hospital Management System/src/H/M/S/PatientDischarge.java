package H.M.S;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class PatientDischarge extends JFrame {

    // Table model & table for admitted patients list
    private DefaultTableModel tableModel;
    private JTable table;

    public PatientDischarge() {

        // ===== BASIC WINDOW SETTINGS =====
        setTitle("Patient Discharge - TR Hospital");        // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // Close on exit
        setSize(1000, 600);                                // Initial size
        setLocationRelativeTo(null);                       // Center window
        setLayout(new BorderLayout());                     // Responsive base layout

        // ===== HEADER PANEL =====
        JPanel header = new JPanel();
        header.setBackground(new Color(109, 164, 170));    // Teal color
        header.setPreferredSize(new Dimension(1000, 70));  // Fixed header height
        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(header, BorderLayout.NORTH);

        JLabel title = new JLabel("PATIENT DISCHARGE");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        header.add(title);

        // ===== CENTER CONTENT (LEFT FORM + RIGHT TABLE) =====
        JPanel content = new JPanel(new BorderLayout());
        add(content, BorderLayout.CENTER);

        // ================= LEFT FORM PANEL =================
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(248, 249, 250));
        formPanel.setBorder(BorderFactory.createTitledBorder("Discharge Details"));
        // GridBagLayout form ko flexible / responsive banata hai.[web:70][web:83]
        formPanel.setLayout(new GridBagLayout());
        content.add(formPanel, BorderLayout.WEST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);             // Space around components
        gbc.fill = GridBagConstraints.HORIZONTAL;          // Stretch horizontally
        gbc.weightx = 1;                                   // Share width
        int row = 0;                                       // Current row index

        // --- Patient ID (search ke liye) ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Patient ID:"), gbc);

        JTextField tfId = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfId, gbc);

        // --- Patient Name (read-only after load) ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Name:"), gbc);

        JTextField tfName = new JTextField();
        tfName.setEditable(false);                         // User manually edit nahi karega
        gbc.gridy = row++;
        formPanel.add(tfName, gbc);

        // --- Room No (read-only) ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Room No:"), gbc);

        JTextField tfRoom = new JTextField();
        tfRoom.setEditable(false);
        gbc.gridy = row++;
        formPanel.add(tfRoom, gbc);

        // --- Admit Date (read-only) ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Admit Date:"), gbc);

        JTextField tfAdmitDate = new JTextField("dd-mm-yyyy");
        tfAdmitDate.setEditable(false);
        gbc.gridy = row++;
        formPanel.add(tfAdmitDate, gbc);

        // --- Discharge Date (editable) ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Discharge Date:"), gbc);

        JTextField tfDischargeDate = new JTextField("dd-mm-yyyy");
        gbc.gridy = row++;
        formPanel.add(tfDischargeDate, gbc);

        // --- Total Days (auto calculate idea) ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Total Days:"), gbc);

        JTextField tfDays = new JTextField();
        tfDays.setEditable(false);                         // Future me auto-calc kar sakte ho
        gbc.gridy = row++;
        formPanel.add(tfDays, gbc);

        // --- Room Charges per day ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Room / Day (₹):"), gbc);

        JTextField tfRoomPerDay = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfRoomPerDay, gbc);

        // --- Other Charges (doctor, tests, etc.) ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Other Charges (₹):"), gbc);

        JTextField tfOther = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfOther, gbc);

        // --- Total Bill ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Total Bill (₹):"), gbc);

        JTextField tfTotalBill = new JTextField();
        tfTotalBill.setEditable(false);                    // Calculate button se set hoga
        gbc.gridy = row++;
        formPanel.add(tfTotalBill, gbc);

        // ===== BUTTON BAR (LOAD, CALCULATE, DISCHARGE, CLEAR, BACK) =====
        JPanel buttonBar = new JPanel(new GridLayout(1, 5, 5, 0));
        JButton btnLoad      = new JButton("LOAD");
        JButton btnCalc      = new JButton("CALCULATE");
        JButton btnDischarge = new JButton("DISCHARGE");
        JButton btnClear     = new JButton("CLEAR");
        JButton btnBack      = new JButton("BACK");

        buttonBar.add(btnLoad);
        buttonBar.add(btnCalc);
        buttonBar.add(btnDischarge);
        buttonBar.add(btnClear);
        buttonBar.add(btnBack);

        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(buttonBar, gbc);

        // Form panel ka width; height window ke saath change hoga
        formPanel.setPreferredSize(new Dimension(320, 0));

        // ================= RIGHT TABLE PANEL =================
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Admitted Patients"));
        content.add(tablePanel, BorderLayout.CENTER);

        // Table columns (current admitted patients list)
        String[] columns = {
                "Patient ID", "Name", "Room No", "Admit Date", "Room / Day", "Other", "Status"
        };

        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setRowHeight(22);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Responsive width

        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Demo sample rows (baad me MySQL se laa sakte ho)
        tableModel.addRow(new Object[]{"P010", "Rohan", "201", "01-12-2025", "1500", "500", "Admitted"});
        tableModel.addRow(new Object[]{"P011", "Neha", "305", "03-12-2025", "2000", "0",   "Admitted"});

        // ===== ACTIONS =====

        // LOAD: selected patient (table ya ID) ke details form me laana
        btnLoad.addActionListener(e -> {
            int rowIndex = table.getSelectedRow();
            // Agar user ne table se select nahi kiya, to patient ID se basic search
            if (rowIndex == -1 && !tfId.getText().trim().isEmpty()) {
                String searchId = tfId.getText().trim().toLowerCase();
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    String id = tableModel.getValueAt(i, 0).toString().toLowerCase();
                    if (id.equals(searchId)) {
                        rowIndex = i;
                        table.setRowSelectionInterval(i, i);
                        break;
                    }
                }
            }

            if (rowIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select a patient from table or enter valid Patient ID!");
                return;
            }

            // Table se data nikal kar form me set karna
            tfId.setText(tableModel.getValueAt(rowIndex, 0).toString());
            tfName.setText(tableModel.getValueAt(rowIndex, 1).toString());
            tfRoom.setText(tableModel.getValueAt(rowIndex, 2).toString());
            tfAdmitDate.setText(tableModel.getValueAt(rowIndex, 3).toString());
            tfRoomPerDay.setText(tableModel.getValueAt(rowIndex, 4).toString());
            tfOther.setText(tableModel.getValueAt(rowIndex, 5).toString());
        });

        // CALCULATE: days * room/day + other charges = total bill
        btnCalc.addActionListener(e -> {
            try {
                // Simple numeric parsing (future me admit/discharge date se days calculate kar sakte ho)
                int days = Integer.parseInt(tfDays.getText().isEmpty() ? "1" : tfDays.getText());
                double roomPerDay = Double.parseDouble(tfRoomPerDay.getText());
                double other = Double.parseDouble(tfOther.getText().isEmpty() ? "0" : tfOther.getText());
                double total = days * roomPerDay + other;
                tfTotalBill.setText(String.valueOf(total));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values for days and charges!");
            }
        });

        // DISCHARGE: record ko 'Discharged' mark karo (ya DB se delete idea)
        btnDischarge.addActionListener(e -> {
            int rowIndex = table.getSelectedRow();
            if (rowIndex == -1) {
                JOptionPane.showMessageDialog(this, "Select a patient from table to discharge!");
                return;
            }

            // Status column ko update karke 'Discharged' kar diya
            tableModel.setValueAt("Discharged", rowIndex, 6);
            JOptionPane.showMessageDialog(this, "Patient discharged successfully!\nTotal Bill: ₹" + tfTotalBill.getText());

            // Yahi jagah future me: DB se delete ya discharge table me insert kar sakte ho.[web:35][web:52]
        });

        // CLEAR: form reset + selection clear
        btnClear.addActionListener(e -> {
            tfId.setText("");
            tfName.setText("");
            tfRoom.setText("");
            tfAdmitDate.setText("dd-mm-yyyy");
            tfDischargeDate.setText("dd-mm-yyyy");
            tfDays.setText("");
            tfRoomPerDay.setText("");
            tfOther.setText("");
            tfTotalBill.setText("");
            table.clearSelection();
        });

        // BACK: Reception screen open
        btnBack.addActionListener(e -> {
            setVisible(false);
            new Reception();
        });

        // Table row click: instantly basic details form me dikhana
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = table.getSelectedRow();
                if (rowIndex >= 0) {
                    tfId.setText(tableModel.getValueAt(rowIndex, 0).toString());
                    tfName.setText(tableModel.getValueAt(rowIndex, 1).toString());
                    tfRoom.setText(tableModel.getValueAt(rowIndex, 2).toString());
                    tfAdmitDate.setText(tableModel.getValueAt(rowIndex, 3).toString());
                    tfRoomPerDay.setText(tableModel.getValueAt(rowIndex, 4).toString());
                    tfOther.setText(tableModel.getValueAt(rowIndex, 5).toString());
                }
            }
        });

        setVisible(true);                                  // Show window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PatientDischarge::new); // Run on EDT
    }
}
