package H.M.S;

import javax.swing.*;
import java.awt.*;
public class EmployeeInfo extends JFrame {
    JTextField tfEmpId, tfName, tfAge, tfContact, tfSalary, tfDepartment, tfDesignation;
    JComboBox<String> cbGender, cbStatus;
    JButton btnAdd, btnUpdate, btnDelete, btnClear, btnBack;
    JTable table;

    EmployeeInfo() {
        // Window properties
        setSize(1200, 700);
        setTitle("Employee Information - TR Hospital");
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        getContentPane().setBackground(new Color(240, 248, 255));

        // Header Panel
        JPanel header = new JPanel();
        header.setBounds(0, 0, 1200, 80);
        header.setBackground(new Color(109, 164, 170));
        header.setLayout(null);
        add(header);

        JLabel title = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        title.setBounds(450, 25, 350, 30);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        header.add(title);

        // Form Panel (Left side)
        JPanel formPanel = new JPanel();
        formPanel.setBounds(30, 100, 550, 550);
        formPanel.setBackground(new Color(248, 249, 250));
        formPanel.setLayout(null);
        formPanel.setBorder(BorderFactory.createTitledBorder("Employee Details"));
        add(formPanel);

        // Employee ID
        JLabel lblEmpId = new JLabel("Employee ID:");
        lblEmpId.setBounds(20, 30, 120, 25);
        lblEmpId.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblEmpId);

        tfEmpId = new JTextField();
        tfEmpId.setBounds(150, 30, 150, 25);
        formPanel.add(tfEmpId);

        // Name
        JLabel lblName = new JLabel("Full Name:");
        lblName.setBounds(20, 70, 120, 25);
        lblName.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblName);

        tfName = new JTextField();
        tfName.setBounds(150, 70, 350, 25);
        formPanel.add(tfName);

        // Age & Gender
        JLabel lblAge = new JLabel("Age:");
        lblAge.setBounds(20, 110, 50, 25);
        lblAge.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblAge);

        tfAge = new JTextField();
        tfAge.setBounds(80, 110, 80, 25);
        formPanel.add(tfAge);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(180, 110, 70, 25);
        lblGender.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblGender);

        cbGender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        cbGender.setBounds(260, 110, 100, 25);
        formPanel.add(cbGender);

        // Contact
        JLabel lblContact = new JLabel("Contact:");
        lblContact.setBounds(20, 150, 120, 25);
        lblContact.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblContact);

        tfContact = new JTextField();
        tfContact.setBounds(150, 150, 350, 25);
        formPanel.add(tfContact);

        // Department
        JLabel lblDept = new JLabel("Department:");
        lblDept.setBounds(20, 190, 120, 25);
        lblDept.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblDept);

        tfDepartment = new JTextField();
        tfDepartment.setBounds(150, 190, 350, 25);
        formPanel.add(tfDepartment);

        // Designation
        JLabel lblDesignation = new JLabel("Designation:");
        lblDesignation.setBounds(20, 230, 120, 25);
        lblDesignation.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblDesignation);

        tfDesignation = new JTextField();
        tfDesignation.setBounds(150, 230, 350, 25);
        formPanel.add(tfDesignation);

        // Salary
        JLabel lblSalary = new JLabel("Salary (₹):");
        lblSalary.setBounds(20, 270, 120, 25);
        lblSalary.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblSalary);

        tfSalary = new JTextField();
        tfSalary.setBounds(150, 270, 350, 25);
        formPanel.add(tfSalary);

        // Status
        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setBounds(20, 310, 120, 25);
        lblStatus.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblStatus);

        cbStatus = new JComboBox<>(new String[]{"Active", "Inactive", "On Leave"});
        cbStatus.setBounds(150, 310, 150, 25);
        formPanel.add(cbStatus);

        // Buttons
        btnAdd = new JButton("ADD EMPLOYEE");
        btnAdd.setBounds(20, 370, 130, 35);
        btnAdd.setBackground(new Color(46, 204, 113));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(btnAdd);

        btnUpdate = new JButton("UPDATE");
        btnUpdate.setBounds(160, 370, 110, 35);
        btnUpdate.setBackground(new Color(52, 152, 219));
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(btnUpdate);

        btnDelete = new JButton("DELETE");
        btnDelete.setBounds(280, 370, 110, 35);
        btnDelete.setBackground(new Color(231, 76, 60));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(btnDelete);

        btnClear = new JButton("CLEAR");
        btnClear.setBounds(400, 370, 100, 35);
        btnClear.setBackground(new Color(241, 196, 15));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(btnClear);

        // Table Panel (Right side)
        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(600, 100, 550, 550);
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setLayout(null);
        tablePanel.setBorder(BorderFactory.createTitledBorder("Employees List"));
        add(tablePanel);

        String[] columns = {"Emp ID", "Name", "Age", "Gender", "Contact", "Department", "Salary"};
        Object[][] data = {
                {"EMP001", "Dr. Rahul Sharma", "35", "Male", "9876543210", "Cardiology", "85000"},
                {"EMP002", "Nurse Priya", "28", "Female", "9876543211", "General", "45000"}
        };

        table = new JTable(data, columns);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(10, 30, 530, 500);
        tablePanel.add(scroll);

        // Back Button
        btnBack = new JButton("← BACK TO DASHBOARD");
        btnBack.setBounds(30, 620, 200, 35);
        btnBack.setBackground(new Color(149, 165, 166));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Arial", Font.BOLD, 13));
        add(btnBack);

        // Action Listeners
        btnAdd.addActionListener(e -> {
            // TODO: Add to database and table
            JOptionPane.showMessageDialog(null, "Employee Added Successfully!");
        });

        btnClear.addActionListener(e -> {
            clearFields();
        });

        btnBack.addActionListener(e -> {
            setVisible(true);
            new Reception();
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void clearFields() {
        tfEmpId.setText(""); tfName.setText(""); tfAge.setText("");
        tfContact.setText(""); tfDepartment.setText("");
        tfSalary.setText(""); tfDesignation.setText("");
        cbGender.setSelectedIndex(0); cbStatus.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new EmployeeInfo();
    }
}
