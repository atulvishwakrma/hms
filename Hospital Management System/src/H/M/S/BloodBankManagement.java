package H.M.S;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class BloodBankManagement extends JFrame {

    // Table & model for blood stock
    private DefaultTableModel tableModel;
    private JTable table;

    public BloodBankManagement() {

        // ===== BASIC WINDOW SETTINGS =====
        setTitle("Blood Bank - TR Hospital");               // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // Close behaviour
        setSize(950, 550);                                  // Initial size
        setLocationRelativeTo(null);                        // Center screen
        setLayout(new BorderLayout());                      // Responsive base layout[web:85]

        // ===== HEADER (TOP BAR) =====
        JPanel header = new JPanel();
        header.setBackground(new Color(109, 164, 170));     // Teal
        header.setPreferredSize(new Dimension(950, 70));    // Fixed height
        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(header, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("BLOOD BANK");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        header.add(lblTitle);

        // ===== MAIN CONTENT: LEFT FORM + RIGHT TABLE =====
        JPanel content = new JPanel(new BorderLayout());
        add(content, BorderLayout.CENTER);

        // ---------- LEFT FORM (add / update stock) ----------
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(248, 249, 250));
        formPanel.setBorder(BorderFactory.createTitledBorder("Add / Update Blood Units"));
        // GridBagLayout simple responsive form ke liye.[web:106]
        formPanel.setLayout(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(320, 0));  // Width approx fix
        content.add(formPanel, BorderLayout.WEST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);              // Gap around fields
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        int row = 0;

        // ---- Blood Group (dropdown) ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Blood Group:"), gbc);

        JComboBox<String> cbGroup = new JComboBox<>(new String[]{
                "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
        });
        gbc.gridy = row++;
        formPanel.add(cbGroup, gbc);

        // ---- Units (in bags) ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Units (Bags):"), gbc);

        JTextField tfUnits = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfUnits, gbc);

        // ---- Bag ID (optional unique id) ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Bag ID:"), gbc);

        JTextField tfBagId = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfBagId, gbc);

        // ---- Donor Name (optional) ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Donor Name:"), gbc);

        JTextField tfDonor = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfDonor, gbc);

        // ---- Expiry Date ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Expiry Date:"), gbc);

        JTextField tfExpiry = new JTextField("dd-mm-yyyy");
        gbc.gridy = row++;
        formPanel.add(tfExpiry, gbc);

        // ---- Storage Location (Fridge / Rack) ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Storage:"), gbc);

        JTextField tfStorage = new JTextField("Fridge-1 / Rack-A");
        gbc.gridy = row++;
        formPanel.add(tfStorage, gbc);

        // ---------- BUTTON BAR (ADD, UPDATE, CLEAR, BACK) ----------
        JPanel buttonBar = new JPanel(new GridLayout(1, 4, 5, 0));
        JButton btnAdd    = new JButton("ADD");
        JButton btnUpdate = new JButton("UPDATE");
        JButton btnClear  = new JButton("CLEAR");
        JButton btnBack   = new JButton("BACK");
        buttonBar.add(btnAdd);
        buttonBar.add(btnUpdate);
        buttonBar.add(btnClear);
        buttonBar.add(btnBack);

        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(buttonBar, gbc);

        // ---------- RIGHT TABLE: BLOOD STOCK ----------
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Blood Stock"));
        content.add(tablePanel, BorderLayout.CENTER);

        // Columns: simple stock-style table[web:167][web:166]
        String[] cols = {"Blood Group", "Units", "Bag ID", "Donor", "Expiry", "Storage"};
        tableModel = new DefaultTableModel(cols, 0);
        table = new JTable(tableModel);
        table.setRowHeight(22);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Responsive columns

        JScrollPane scroll = new JScrollPane(table);
        tablePanel.add(scroll, BorderLayout.CENTER);

        // Demo sample data (later DB se load kar sakte ho)
        tableModel.addRow(new Object[]{"A+", 10, "BG001", "Rohan", "20-12-2025", "Fridge-1"});
        tableModel.addRow(new Object[]{"O-", 5, "BG010", "Neha",  "15-12-2025", "Fridge-2"});

        // ===== EVENTS =====

        // Table row click: stock item form me load (edit ke liye)
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = table.getSelectedRow();
                if (rowIndex >= 0) {
                    cbGroup.setSelectedItem(tableModel.getValueAt(rowIndex, 0).toString());
                    tfUnits.setText(tableModel.getValueAt(rowIndex, 1).toString());
                    tfBagId.setText(tableModel.getValueAt(rowIndex, 2).toString());
                    tfDonor.setText(tableModel.getValueAt(rowIndex, 3).toString());
                    tfExpiry.setText(tableModel.getValueAt(rowIndex, 4).toString());
                    tfStorage.setText(tableModel.getValueAt(rowIndex, 5).toString());
                }
            }
        });

        // ADD: naya blood bag / stock record add karega
        btnAdd.addActionListener(e -> {
            // Simple validation: units numeric + group selected
            if (tfUnits.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter units (bags)!");
                return;
            }
            try {
                Integer.parseInt(tfUnits.getText().trim()); // Just check number
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Units must be a number!");
                return;
            }

            tableModel.addRow(new Object[]{
                    cbGroup.getSelectedItem(),
                    tfUnits.getText().trim(),
                    tfBagId.getText().trim(),
                    tfDonor.getText().trim(),
                    tfExpiry.getText().trim(),
                    tfStorage.getText().trim()
            });

            JOptionPane.showMessageDialog(this, "Blood stock added successfully!");
            clearForm(cbGroup, tfUnits, tfBagId, tfDonor, tfExpiry, tfStorage);
        });

        // UPDATE: selected row update karega
        btnUpdate.addActionListener(e -> {
            int rowIndex = table.getSelectedRow();
            if (rowIndex == -1) {
                JOptionPane.showMessageDialog(this, "Select a row from table to update!");
                return;
            }

            tableModel.setValueAt(cbGroup.getSelectedItem(), rowIndex, 0);
            tableModel.setValueAt(tfUnits.getText().trim(), rowIndex, 1);
            tableModel.setValueAt(tfBagId.getText().trim(), rowIndex, 2);
            tableModel.setValueAt(tfDonor.getText().trim(), rowIndex, 3);
            tableModel.setValueAt(tfExpiry.getText().trim(), rowIndex, 4);
            tableModel.setValueAt(tfStorage.getText().trim(), rowIndex, 5);

            JOptionPane.showMessageDialog(this, "Blood stock updated!");
        });

        // CLEAR: sirf form fields reset karega
        btnClear.addActionListener(e -> {
            clearForm(cbGroup, tfUnits, tfBagId, tfDonor, tfExpiry, tfStorage);
            table.clearSelection();
        });

        // BACK: Reception par wapas
        btnBack.addActionListener(e -> {
            setVisible(false);
            new Reception();
        });

        setVisible(true);                                    // Show window
    }

    // Helper method: form ko clear karne ke liye
    private void clearForm(JComboBox<String> cbGroup,
                           JTextField tfUnits,
                           JTextField tfBagId,
                           JTextField tfDonor,
                           JTextField tfExpiry,
                           JTextField tfStorage) {
        cbGroup.setSelectedIndex(0);
        tfUnits.setText("");
        tfBagId.setText("");
        tfDonor.setText("");
        tfExpiry.setText("dd-mm-yyyy");
        tfStorage.setText("Fridge-1 / Rack-A");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BloodBankManagement::new);
    }
}
