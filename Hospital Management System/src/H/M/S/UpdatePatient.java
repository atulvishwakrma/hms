package H.M.S;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class UpdatePatient extends JFrame {

    // Table model & table for all patients
    private DefaultTableModel tableModel;
    private JTable table;

    public UpdatePatient() {

        // ===== BASIC WINDOW SETTINGS =====
        setTitle("Update Patient - TR Hospital");          // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // Exit on close
        setSize(1000, 600);                                // Initial size
        setLocationRelativeTo(null);                       // Center screen
        // BorderLayout se responsive layout milega (NORTH, WEST, CENTER)[web:79]
        setLayout(new BorderLayout());

        // ===== HEADER PANEL =====
        JPanel header = new JPanel();
        header.setBackground(new Color(109, 164, 170));    // Teal color
        header.setPreferredSize(new Dimension(1000, 70));  // Fixed height
        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(header, BorderLayout.NORTH);

        JLabel title = new JLabel("UPDATE PATIENT DETAILS");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        header.add(title);

        // ===== MAIN CONTENT (FORM LEFT + TABLE RIGHT) =====
        JPanel content = new JPanel(new BorderLayout());
        add(content, BorderLayout.CENTER);

        // ------------- LEFT FORM PANEL (GridBagLayout = responsive) -------------
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(248, 249, 250));
        formPanel.setBorder(BorderFactory.createTitledBorder("Edit Patient"));
        // GridBagLayout simple responsive forms ke liye useful hai.[web:106]
        formPanel.setLayout(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(320, 0));   // Width approx fix
        content.add(formPanel, BorderLayout.WEST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);              // Gap around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        int row = 0;

        // --- Patient ID (non-editable, primary key) ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Patient ID:"), gbc);

        JTextField tfId = new JTextField();
        tfId.setEditable(false);                            // ID change nahi karte
        gbc.gridy = row++;
        formPanel.add(tfId, gbc);

        // --- Name ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Name:"), gbc);

        JTextField tfName = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfName, gbc);

        // --- Gender ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Gender:"), gbc);

        JComboBox<String> cbGender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        gbc.gridy = row++;
        formPanel.add(cbGender, gbc);

        // --- Age ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Age:"), gbc);

        JTextField tfAge = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfAge, gbc);

        // --- Phone ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Phone:"), gbc);

        JTextField tfPhone = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfPhone, gbc);

        // --- Disease ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Disease:"), gbc);

        JTextField tfDisease = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfDisease, gbc);

        // --- Room No ---
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Room No:"), gbc);

        JTextField tfRoom = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfRoom, gbc);

        // ------------- BUTTON BAR (UPDATE, CLEAR, BACK) -------------
        JPanel buttonBar = new JPanel(new GridLayout(1, 3, 5, 0));
        JButton btnUpdate = new JButton("UPDATE");
        JButton btnClear  = new JButton("CLEAR");
        JButton btnBack   = new JButton("BACK");
        buttonBar.add(btnUpdate);
        buttonBar.add(btnClear);
        buttonBar.add(btnBack);

        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(buttonBar, gbc);

        // ------------- RIGHT TABLE PANEL (auto-resize) -------------
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Patients List"));
        content.add(tablePanel, BorderLayout.CENTER);

        // Table columns (same order as form fields)
        String[] cols = {"Patient ID", "Name", "Gender", "Age",
                "Phone", "Disease", "Room No"};

        // DefaultTableModel: edit/update ke liye easy structure.[web:45]
        tableModel = new DefaultTableModel(cols, 0);
        table = new JTable(tableModel);
        table.setRowHeight(22);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Responsive columns

        JScrollPane scroll = new JScrollPane(table);       // Scrollable table
        tablePanel.add(scroll, BorderLayout.CENTER);

        // Demo sample rows (later DB se load kar sakte ho)
        tableModel.addRow(new Object[]{"P001", "Rohan", "Male", 25, "9876543210", "Fever", "101"});
        tableModel.addRow(new Object[]{"P002", "Aisha", "Female", 30, "9876543211", "Fracture", "205"});

        // ====== EVENT HANDLERS ======

        // Table row click: data ko form me load karega, taaki edit kar sako.[web:132][web:130]
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = table.getSelectedRow();      // Selected row index
                if (rowIndex >= 0) {
                    tfId.setText(tableModel.getValueAt(rowIndex, 0).toString());
                    tfName.setText(tableModel.getValueAt(rowIndex, 1).toString());
                    cbGender.setSelectedItem(tableModel.getValueAt(rowIndex, 2).toString());
                    tfAge.setText(tableModel.getValueAt(rowIndex, 3).toString());
                    tfPhone.setText(tableModel.getValueAt(rowIndex, 4).toString());
                    tfDisease.setText(tableModel.getValueAt(rowIndex, 5).toString());
                    tfRoom.setText(tableModel.getValueAt(rowIndex, 6).toString());
                }
            }
        });

        // UPDATE button: form ke values se selected row ko update karega.
        btnUpdate.addActionListener(e -> {
            int rowIndex = table.getSelectedRow();          // Kaun sa row select hai
            if (rowIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select a patient from table to update!");
                return;
            }

            // Basic validation: name, phone empty na ho
            if (tfName.getText().trim().isEmpty() || tfPhone.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Phone cannot be empty!");
                return;
            }

            // TableModel me values setValueAt se update kar rahe hain.[web:125][web:130]
            tableModel.setValueAt(tfId.getText(),               rowIndex, 0);
            tableModel.setValueAt(tfName.getText(),             rowIndex, 1);
            tableModel.setValueAt(cbGender.getSelectedItem(),   rowIndex, 2);
            tableModel.setValueAt(tfAge.getText(),              rowIndex, 3);
            tableModel.setValueAt(tfPhone.getText(),            rowIndex, 4);
            tableModel.setValueAt(tfDisease.getText(),          rowIndex, 5);
            tableModel.setValueAt(tfRoom.getText(),             rowIndex, 6);

            // Yahi jagah future me: MySQL UPDATE query bhi chalegi.[web:121][web:131]
            JOptionPane.showMessageDialog(this, "Patient details updated successfully!");
        });

        // CLEAR button: form reset karega, selection hata dega.
        btnClear.addActionListener(e -> {
            tfId.setText("");
            tfName.setText("");
            cbGender.setSelectedIndex(0);
            tfAge.setText("");
            tfPhone.setText("");
            tfDisease.setText("");
            tfRoom.setText("");
            table.clearSelection();
        });

        // BACK button: Update window band, Reception kholega.
        btnBack.addActionListener(e -> {
            setVisible(false);
            new Reception();
        });

        setVisible(true);                                    // Window dikhana
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UpdatePatient::new);      // Run on EDT
    }
}
