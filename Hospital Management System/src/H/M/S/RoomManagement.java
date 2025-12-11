package H.M.S;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class RoomManagement extends JFrame {

    // Text fields for room details
    JTextField tfRoomNo, tfRoomType, tfCharges;
    // ComboBox for room status (Available / Occupied / Cleaning)
    JComboBox<String> cbStatus;
    // Buttons
    JButton btnAdd, btnUpdate, btnDelete, btnClear, btnBack;
    // Table to show room list
    JTable table;
    DefaultTableModel tableModel;

    public RoomManagement() {
        // Window ka size set kar rahe hain
        setSize(900, 600);
        // Window ka title set kar rahe hain
        setTitle("Room Management - TR Hospital");
        // Window ko center me dikhane ke liye
        setLocationRelativeTo(null);
        // Null layout use kar rahe ho isliye manual setBounds karenge
        setLayout(null);
        // Background color set kar rahe hain (light)
        getContentPane().setBackground(new Color(240, 248, 255));

        // ================== HEADER PANEL ==================
        // Upar header ke liye panel
        JPanel header = new JPanel();
        header.setBounds(0, 0, 900, 70);      // x, y, width, height
        header.setBackground(new Color(109, 164, 170)); // Teal color
        header.setLayout(null);
        add(header); // Frame me add kar diya

        // Header ke andar title label
        JLabel lblTitle = new JLabel("ROOM MANAGEMENT");
        lblTitle.setBounds(320, 20, 300, 30);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        header.add(lblTitle);

        // ================== FORM PANEL (LEFT SIDE) ==================
        // Left side form ke liye panel
        JPanel formPanel = new JPanel();
        formPanel.setBounds(20, 90, 350, 450);
        formPanel.setBackground(new Color(248, 249, 250));
        formPanel.setLayout(null);
        formPanel.setBorder(BorderFactory.createTitledBorder("Room Details")); // Border with title
        add(formPanel);

        // ---- Room No ----
        JLabel lblRoomNo = new JLabel("Room No:");
        lblRoomNo.setBounds(20, 40, 100, 25);
        lblRoomNo.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblRoomNo);

        tfRoomNo = new JTextField();
        tfRoomNo.setBounds(130, 40, 180, 25);
        formPanel.add(tfRoomNo);

        // ---- Room Type ----
        JLabel lblRoomType = new JLabel("Room Type:");
        lblRoomType.setBounds(20, 85, 100, 25);
        lblRoomType.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblRoomType);

        // Yaha tum "General / ICU / Private / Ward" jaise text likh sakte ho
        tfRoomType = new JTextField();
        tfRoomType.setBounds(130, 85, 180, 25);
        formPanel.add(tfRoomType);

        // ---- Room Charges ----
        JLabel lblCharges = new JLabel("Charges (₹):");
        lblCharges.setBounds(20, 130, 100, 25);
        lblCharges.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblCharges);

        tfCharges = new JTextField();
        tfCharges.setBounds(130, 130, 180, 25);
        formPanel.add(tfCharges);

        // ---- Room Status (ComboBox) ----
        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setBounds(20, 175, 100, 25);
        lblStatus.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(lblStatus);

        // ComboBox me teen options: Available, Occupied, Cleaning
        cbStatus = new JComboBox<>(new String[]{"Available", "Occupied", "Under Cleaning"});
        cbStatus.setBounds(130, 175, 180, 25);
        formPanel.add(cbStatus);

        // ================== BUTTONS ==================
        // ADD button
        btnAdd = new JButton("ADD ROOM");
        btnAdd.setBounds(20, 230, 130, 35);
        btnAdd.setBackground(new Color(46, 204, 113)); // Green
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(btnAdd);

        // UPDATE button
        btnUpdate = new JButton("UPDATE");
        btnUpdate.setBounds(170, 230, 130, 35);
        btnUpdate.setBackground(new Color(52, 152, 219)); // Blue
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(btnUpdate);

        // DELETE button
        btnDelete = new JButton("DELETE");
        btnDelete.setBounds(20, 280, 130, 35);
        btnDelete.setBackground(new Color(231, 76, 60)); // Red
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(btnDelete);

        // CLEAR button
        btnClear = new JButton("CLEAR");
        btnClear.setBounds(170, 280, 130, 35);
        btnClear.setBackground(new Color(241, 196, 15)); // Yellow
        btnClear.setForeground(Color.WHITE);
        btnClear.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(btnClear);

        // BACK button (Reception par jaane ke liye)
        btnBack = new JButton("← BACK");
        btnBack.setBounds(20, 340, 280, 35);
        btnBack.setBackground(new Color(149, 165, 166)); // Grey
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Arial", Font.BOLD, 13));
        formPanel.add(btnBack);

        // ================== TABLE PANEL (RIGHT SIDE) ==================
        // Right side room list ke liye panel
        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(390, 90, 480, 450);
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setLayout(null);
        tablePanel.setBorder(BorderFactory.createTitledBorder("Rooms List"));
        add(tablePanel);

        // Table columns ke naam
        String[] columnNames = {"Room No", "Type", "Charges", "Status"};
        // DefaultTableModel use kar rahe hain taa ki rows add/remove karein
        tableModel = new DefaultTableModel(columnNames, 0);
        // JTable banaya model ke saath
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(22);

        // ScrollPane me table ko daala (scrolling ke liye)
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 30, 460, 400);
        tablePanel.add(scrollPane);

        // Example sample data add kar rahe hain (demo ke liye)
        tableModel.addRow(new Object[]{"101", "General", "1500", "Available"});
        tableModel.addRow(new Object[]{"102", "ICU", "5000", "Occupied"});

        // ================== ACTION LISTENERS ==================

        // ADD ROOM button par click event
        btnAdd.addActionListener(e -> {
            // Text fields se values le rahe hain
            String roomNo = tfRoomNo.getText();
            String type = tfRoomType.getText();
            String charges = tfCharges.getText();
            String status = (String) cbStatus.getSelectedItem();

            // Simple validation: koi field empty na ho
            if (roomNo.isEmpty() || type.isEmpty() || charges.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return; // Niche ka code run nahi hoga
            }

            // Table me new row add kar rahe hain
            tableModel.addRow(new Object[]{roomNo, type, charges, status});
            // User ko success message
            JOptionPane.showMessageDialog(this, "Room added successfully!");

            // Fields clear karne ke liye function call
            clearFields();
        });

        // UPDATE button: selected row update karega
        btnUpdate.addActionListener(e -> {
            // Selected row index nikal rahe hain
            int selectedRow = table.getSelectedRow();
            // Agar koi row select nahi hai to -1 milta hai
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a room from table to update!");
                return;
            }

            // Text fields se updated values le rahe hain
            String roomNo = tfRoomNo.getText();
            String type = tfRoomType.getText();
            String charges = tfCharges.getText();
            String status = (String) cbStatus.getSelectedItem();

            // TableModel me selected row update kar rahe hain
            tableModel.setValueAt(roomNo, selectedRow, 0);
            tableModel.setValueAt(type, selectedRow, 1);
            tableModel.setValueAt(charges, selectedRow, 2);
            tableModel.setValueAt(status, selectedRow, 3);

            JOptionPane.showMessageDialog(this, "Room updated successfully!");
        });

        // DELETE button: selected row delete karega
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a room from table to delete!");
                return;
            }
            // Confirm dialog user se poochne ke liye
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure to delete this room?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                // Model se row remove kar rahe hain
                tableModel.removeRow(selectedRow);
            }
        });

        // CLEAR button: form fields clear karega
        btnClear.addActionListener(e -> clearFields());

        // BACK button: is window ko band karke Reception open karega
        btnBack.addActionListener(e -> {
            setVisible(false);      // Current RoomManagement frame hide
            new Reception();        // Reception screen open
        });

        // Table row click pe data form me load karne ke liye
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Selected row index
                int row = table.getSelectedRow();
                // Har column ka value nikal kar text fields me set kar rahe hain
                tfRoomNo.setText(tableModel.getValueAt(row, 0).toString());
                tfRoomType.setText(tableModel.getValueAt(row, 1).toString());
                tfCharges.setText(tableModel.getValueAt(row, 2).toString());
                cbStatus.setSelectedItem(tableModel.getValueAt(row, 3).toString());
            }
        });

        // Default close operation set kar rahe hain
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Frame ko visible kar rahe hain
        setVisible(true);
    }

    // Yeh function saare text fields ko empty karta hai
    private void clearFields() {
        tfRoomNo.setText("");
        tfRoomType.setText("");
        tfCharges.setText("");
        cbStatus.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        // Program start hone par RoomManagement window open karega
        new RoomManagement();
    }
}
