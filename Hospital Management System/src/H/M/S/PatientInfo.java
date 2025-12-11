package H.M.S;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class PatientInfo extends JFrame {

    // Table model & table patient list ke liye
    private DefaultTableModel tableModel;
    private JTable table;

    // Constructor
    public PatientInfo() {
        // --------- BASIC WINDOW SETTINGS (responsive base) ----------
        setTitle("Patient Information - TR Hospital");          // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         // Close behavior
        setSize(1000, 600);                                    // Initial size
        setLocationRelativeTo(null);                           // Center screen

        // BorderLayout use kar rahe hain: NORTH (header), WEST (form), CENTER (table)
        setLayout(new BorderLayout());

        // --------- HEADER PANEL (fixed height, full width) ----------
        JPanel header = new JPanel();                          // Top bar
        header.setBackground(new Color(109, 164, 170));        // Teal color
        header.setPreferredSize(new Dimension(1000, 70));      // Fixed height
        header.setLayout(new FlowLayout(FlowLayout.CENTER));   // Centered content
        add(header, BorderLayout.NORTH);                       // Add to NORTH

        JLabel title = new JLabel("PATIENT INFORMATION");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        header.add(title);

        // --------- MAIN CONTENT PANEL (form + table side by side) ----------
        JPanel content = new JPanel();
        // JSplitPane-like feel ke liye horizontal split using BorderLayout
        content.setLayout(new BorderLayout());
        add(content, BorderLayout.CENTER);

        // --------- LEFT FORM PANEL (GridBagLayout = responsive form) ----------
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(248, 249, 250));
        formPanel.setBorder(BorderFactory.createTitledBorder("Search / Filter Patients"));
        // GridBagLayout responsive form ke liye best hai.[web:70][web:83]
        formPanel.setLayout(new GridBagLayout());
        content.add(formPanel, BorderLayout.WEST);             // Left side

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);                 // Gap around components
        gbc.fill = GridBagConstraints.HORIZONTAL;              // Stretch horizontally
        gbc.weightx = 1;                                      // Share horizontal space

        // Label & field helper method ke liye variables
        int row = 0;                                          // Current grid row

        // ----- Patient ID -----
        gbc.gridx = 0; gbc.gridy = row;                       // Column 0, current row
        formPanel.add(new JLabel("Patient ID:"), gbc);        // Label add

        JTextField tfId = new JTextField();
        gbc.gridy = row++;                                    // Same row next position
        formPanel.add(tfId, gbc);                             // TextField add

        // ----- Patient Name -----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Name:"), gbc);

        JTextField tfName = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfName, gbc);

        // ----- Phone / Mobile -----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Phone:"), gbc);

        JTextField tfPhone = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfPhone, gbc);

        // ----- Disease / Problem -----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Disease:"), gbc);

        JTextField tfDisease = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfDisease, gbc);

        // ----- Room No -----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Room No:"), gbc);

        JTextField tfRoom = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfRoom, gbc);

        // ----- Buttons row (SEARCH, CLEAR, BACK) -----
        JPanel buttonBar = new JPanel(new GridLayout(1, 3, 5, 0)); // 3 buttons in a row
        JButton btnSearch = new JButton("SEARCH");
        JButton btnClear  = new JButton("CLEAR");
        JButton btnBack   = new JButton("BACK");
        buttonBar.add(btnSearch);
        buttonBar.add(btnClear);
        buttonBar.add(btnBack);

        gbc.gridx = 0; gbc.gridy = row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(buttonBar, gbc);

        // Form panel ki preferred width set; height auto-resize karega
        formPanel.setPreferredSize(new Dimension(280, 0));    // Width fix, height flexible

        // --------- RIGHT TABLE PANEL (auto-resize with window) ----------
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("All Patients"));
        content.add(tablePanel, BorderLayout.CENTER);         // Center area

        // Table ke columns define
        String[] columnNames = {
                "Patient ID", "Name", "Gender", "Age",
                "Phone", "Disease", "Room No"
        };

        // DefaultTableModel jab data add/remove karna ho tab use hota hai.[web:45]
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setRowHeight(22);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Columns resize with window

        // ScrollPane table ko scrollable aur responsive banata hai.[web:62]
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Demo sample data (baad me DB se replace kar sakte ho)
        tableModel.addRow(new Object[]{"P001", "Rohan", "Male", 25, "9876543210", "Fever", "101"});
        tableModel.addRow(new Object[]{"P002", "Aisha", "Female", 30, "9876543211", "Fracture", "205"});

        // --------- BUTTON ACTIONS ---------

        // SEARCH: simple filter logic (ID ya name ya phone match)
        btnSearch.addActionListener(e -> {
            String idText = tfId.getText().trim().toLowerCase();
            String nameText = tfName.getText().trim().toLowerCase();
            String phoneText = tfPhone.getText().trim().toLowerCase();
            String diseaseText = tfDisease.getText().trim().toLowerCase();
            String roomText = tfRoom.getText().trim().toLowerCase();

            // Simple loop se row highlight / select karenge
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String id = tableModel.getValueAt(i, 0).toString().toLowerCase();
                String name = tableModel.getValueAt(i, 1).toString().toLowerCase();
                String phone = tableModel.getValueAt(i, 4).toString().toLowerCase();
                String disease = tableModel.getValueAt(i, 5).toString().toLowerCase();
                String room = tableModel.getValueAt(i, 6).toString().toLowerCase();

                // Agar koi bhi field match ho jaye to us row ko select kar do
                if ((!idText.isEmpty() && id.contains(idText)) ||
                        (!nameText.isEmpty() && name.contains(nameText)) ||
                        (!phoneText.isEmpty() && phone.contains(phoneText)) ||
                        (!diseaseText.isEmpty() && disease.contains(diseaseText)) ||
                        (!roomText.isEmpty() && room.contains(roomText))) {

                    table.setRowSelectionInterval(i, i); // row select
                    // Scroll us row tak le jao
                    table.scrollRectToVisible(table.getCellRect(i, 0, true));
                    return;
                }
            }

            // Agar kuch nahi mila
            JOptionPane.showMessageDialog(this, "No matching patient found!");
        });

        // CLEAR: search form fields empty + table selection clear
        btnClear.addActionListener(e -> {
            tfId.setText("");
            tfName.setText("");
            tfPhone.setText("");
            tfDisease.setText("");
            tfRoom.setText("");
            table.clearSelection();
        });

        // BACK: is window ko band karke Reception open
        btnBack.addActionListener(e -> {
            setVisible(false);
            new Reception();
        });

        // Table row click: selected patient ke details form me show
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = table.getSelectedRow();
                if (rowIndex >= 0) {
                    tfId.setText(tableModel.getValueAt(rowIndex, 0).toString());
                    tfName.setText(tableModel.getValueAt(rowIndex, 1).toString());
                    tfPhone.setText(tableModel.getValueAt(rowIndex, 4).toString());
                    tfDisease.setText(tableModel.getValueAt(rowIndex, 5).toString());
                    tfRoom.setText(tableModel.getValueAt(rowIndex, 6).toString());
                }
            }
        });

        // Window ko end me visible karna
        setVisible(true);
    }

    public static void main(String[] args) {
        // Program run hone par PatientInfo window open
        SwingUtilities.invokeLater(PatientInfo::new);
    }
}
