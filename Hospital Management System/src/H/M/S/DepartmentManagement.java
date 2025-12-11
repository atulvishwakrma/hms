package H.M.S;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class DepartmentManagement extends JFrame {

    // Text fields for department details
    JTextField tfDeptId, tfDeptName, tfHead, tfContact;
    // Area for description
    JTextArea taDescription;
    // Buttons
    JButton btnAdd, btnUpdate, btnDelete, btnClear, btnBack;
    // Table
    JTable table;
    DefaultTableModel tableModel;

    public DepartmentManagement() {

        // ===== WINDOW BASIC SETTINGS =====
        setTitle("Department Management - TR Hospital"); // Window title
        setSize(950, 600);                               // Window size
        setLocationRelativeTo(null);                     // Center screen
        setLayout(null);
        setVisible(true);

        // Using absolute positioning
        getContentPane().setBackground(new Color(240, 248, 255)); // Light background color

        // ===== HEADER PANEL WITH ICON =====
        JPanel header = new JPanel();                    // Top header panel
        header.setBounds(0, 0, 950, 80);                 // Position + size
        header.setBackground(new Color(109, 164, 170));  // Teal color (same as other pages)
        header.setLayout(null);                          // Manual layout
       setVisible(true);
        add(header);                                     // Add header to frame

        // Load department icon from resources (Templates/department.png)
        // Make sure icon file path is correct inside your src folder
        ImageIcon deptIcon = new ImageIcon(
                ClassLoader.getSystemResource("Templates/department.png")); // Department icon
        Image deptImg = deptIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Resize icon
        ImageIcon deptIconSmall = new ImageIcon(deptImg); // New small icon object

        // JLabel with icon + text together
        JLabel lblTitle = new JLabel(" DEPARTMENT MANAGEMENT", deptIconSmall, JLabel.LEFT);
        lblTitle.setBounds(280, 15, 400, 50);            // Position in header
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22)); // Bold font
        lblTitle.setForeground(Color.WHITE);             // White text
        lblTitle.setIconTextGap(10);                     // Gap between icon and text
        header.add(lblTitle);                            // Add label to header

        // ===== LEFT SIDE FORM PANEL =====
        JPanel formPanel = new JPanel();                 // Panel for form fields
        formPanel.setBounds(20, 90, 380, 460);           // Position + size
        formPanel.setBackground(new Color(248, 249, 250)); // Slightly off‑white
        setVisible(true);
        formPanel.setBorder(
                BorderFactory.createTitledBorder("Department Details")); // Titled border
        add(formPanel);                                  // Add to frame

        // ---- Department ID ----
        JLabel lblDeptId = new JLabel("Dept ID:");
        lblDeptId.setBounds(20, 40, 100, 25);
        lblDeptId.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblDeptId);

        tfDeptId = new JTextField();                     // Text field for ID
        tfDeptId.setBounds(130, 40, 220, 25);
        formPanel.add(tfDeptId);

        // ---- Department Name ----
        JLabel lblDeptName = new JLabel("Dept Name:");
        lblDeptName.setBounds(20, 80, 100, 25);
        lblDeptName.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblDeptName);

        tfDeptName = new JTextField();                   // Text field for name
        tfDeptName.setBounds(130, 80, 220, 25);
        formPanel.add(tfDeptName);

        // ---- Head of Department ----
        JLabel lblHead = new JLabel("Head (HOD):");
        lblHead.setBounds(20, 120, 100, 25);
        lblHead.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblHead);

        tfHead = new JTextField();                       // Text field for HOD name
        tfHead.setBounds(130, 120, 220, 25);
        formPanel.add(tfHead);

        // ---- Contact No ----
        JLabel lblContact = new JLabel("Contact No:");
        lblContact.setBounds(20, 160, 100, 25);
        lblContact.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblContact);

        tfContact = new JTextField();                    // Text field for contact
        tfContact.setBounds(130, 160, 220, 25);
        formPanel.add(tfContact);

        // ---- Description (multi‑line) ----
        JLabel lblDesc = new JLabel("Description:");
        lblDesc.setBounds(20, 200, 100, 25);
        lblDesc.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblDesc);

        taDescription = new JTextArea();                 // Text area for description
        taDescription.setLineWrap(true);                 // Auto wrap
        taDescription.setWrapStyleWord(true);            // Wrap full words
        JScrollPane descScroll = new JScrollPane(taDescription); // Scroll for long text
        descScroll.setBounds(130, 200, 220, 80);
        formPanel.add(descScroll);

        // ===== BUTTONS SECTION =====
        // ADD button
        btnAdd = new JButton("ADD");
        btnAdd.setBounds(20, 300, 80, 35);
        btnAdd.setBackground(new Color(46, 204, 113));   // Green
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(btnAdd);

        // UPDATE button
        btnUpdate = new JButton("UPDATE");
        btnUpdate.setBounds(110, 300, 90, 35);
        btnUpdate.setBackground(new Color(52, 152, 219)); // Blue
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(btnUpdate);

        // DELETE button
        btnDelete = new JButton("DELETE");
        btnDelete.setBounds(210, 300, 90, 35);
        btnDelete.setBackground(new Color(231, 76, 60)); // Red
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(btnDelete);

        // CLEAR button
        btnClear = new JButton("CLEAR");
        btnClear.setBounds(310, 300, 80, 35);
        btnClear.setBackground(new Color(241, 196, 15)); // Yellow
        btnClear.setForeground(Color.WHITE);
        btnClear.setFont(new Font("Arial", Font.BOLD, 12));
        //formPanel.add(btnClear);
        add(btnClear);

        // BACK button (Reception pe return)
        btnBack = new JButton("← BACK");
        btnBack.setBounds(20, 350, 370, 35);
        btnBack.setBackground(new Color(149, 165, 166)); // Grey
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(btnBack);

        // ===== RIGHT SIDE TABLE PANEL =====
        JPanel tablePanel = new JPanel();                // Panel for table
        tablePanel.setBounds(420, 90, 500, 460);
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setLayout(null);
        //setVisible(true);
        tablePanel.setBorder(
                BorderFactory.createTitledBorder("Departments List")); // Titled border
        add(tablePanel);

        // Table ke columns
        String[] columns = {"Dept ID", "Name", "HOD", "Contact"};
        // DefaultTableModel jisse rows dynamically add/remove kar sakte hain
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);                  // JTable with model
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(22);

        JScrollPane tableScroll = new JScrollPane(table); // Scroll for table
        tableScroll.setBounds(10, 30, 480, 420);
        tablePanel.add(tableScroll);

        // Sample data add (demo ke liye)
        tableModel.addRow(new Object[]{"D001", "Cardiology", "Dr. Mehta", "9876543210"});
        tableModel.addRow(new Object[]{"D002", "Orthopedics", "Dr. Verma", "9876543211"});

        // ===== ACTION LISTENERS =====

        // ADD: form se row table model me add karega
        btnAdd.addActionListener(e -> {
            String id = tfDeptId.getText();
            String name = tfDeptName.getText();
            String hod = tfHead.getText();
            String contact = tfContact.getText();

            // Simple validation: required fields check
            if (id.isEmpty() || name.isEmpty() || hod.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill Dept ID, Name and HOD!");
                return;
            }

            // Table me new row add karo
            tableModel.addRow(new Object[]{id, name, hod, contact});
            JOptionPane.showMessageDialog(this, "Department added successfully!");
            clearFields();                               // Form clear
        });

        // UPDATE: selected row ko update karega
        btnUpdate.addActionListener(e -> {
            int row = table.getSelectedRow();            // Selected row index
            if (row == -1) {                             // No row selected
                JOptionPane.showMessageDialog(this, "Please select a department from table to update!");
                return;
            }

            // Text fields se updated values
            tableModel.setValueAt(tfDeptId.getText(), row, 0);
            tableModel.setValueAt(tfDeptName.getText(), row, 1);
            tableModel.setValueAt(tfHead.getText(), row, 2);
            tableModel.setValueAt(tfContact.getText(), row, 3);

            JOptionPane.showMessageDialog(this, "Department updated successfully!");
        });

        // DELETE: selected row remove karega
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a department from table to delete!");
                return;
            }
            int choice = JOptionPane.showConfirmDialog(
                    this, "Are you sure to delete this department?", "Confirm",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                tableModel.removeRow(row);               // Remove row from model
            }
        });

        // CLEAR: sirf form fields clear karega
        btnClear.addActionListener(e -> clearFields());

        // BACK: Department window close karke Reception open karega
        btnBack.addActionListener(e -> {
            setVisible(false);
            new Reception();
        });

        // Table row click: row data ko form me load karega
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();        // Selected row index
                tfDeptId.setText(tableModel.getValueAt(row, 0).toString());
                tfDeptName.setText(tableModel.getValueAt(row, 1).toString());
                tfHead.setText(tableModel.getValueAt(row, 2).toString());
                tfContact.setText(tableModel.getValueAt(row, 3).toString());
            }
        });

        // Close button click pe program end nahi, sirf window band kare
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);                                // Show window
    }

    // Helper method: saare form fields reset karta hai
    private void clearFields() {
        tfDeptId.setText("");
        tfDeptName.setText("");
        tfHead.setText("");
        tfContact.setText("");
        taDescription.setText("");
    }

    public static void main(String[] args) {
        new DepartmentManagement();                      // Start window
    }
}
