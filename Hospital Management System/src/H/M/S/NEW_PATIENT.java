package H.M.S;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NEW_PATIENT extends JFrame {
    JTextField tfName, tfAge, tfContact, tfAddress, tfDisease;
    JComboBox<String> cbGender, cbBloodGroup;
    JButton btnSave, btnClear, btnBack;

    NEW_PATIENT() {
        // Window properties
        setSize(850, 550);
        setTitle("Add New Patient - TR Hospital");
        setLocationRelativeTo(null); // Center screen
        setLayout(null);
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background

        // Header Panel
        JPanel header = new JPanel();
        header.setBounds(0, 0, 850, 80);
        header.setBackground(new Color(109, 164, 170));
        header.setLayout(null);
        add(header);

        JLabel title = new JLabel("NEW PATIENT REGISTRATION");
        title.setBounds(280, 25, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        header.add(title);

        // Main Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setBounds(50, 100, 750, 380);
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setLayout(null);
        add(formPanel);

        // Labels and Fields (2-column layout)
        // Row 1
        JLabel lblName = new JLabel("Patient Name:");
        lblName.setBounds(20, 20, 120, 30);
        lblName.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblName);

        tfName = new JTextField();
        tfName.setBounds(150, 20, 250, 30);
        tfName.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(tfName);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setBounds(420, 20, 80, 30);
        lblAge.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblAge);

        tfAge = new JTextField();
        tfAge.setBounds(500, 20, 100, 30);
        tfAge.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(tfAge);

        // Row 2
        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(20, 65, 120, 30);
        lblGender.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblGender);

        cbGender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        cbGender.setBounds(150, 65, 250, 30);
        formPanel.add(cbGender);

        JLabel lblContact = new JLabel("Contact No:");
        lblContact.setBounds(420, 65, 120, 30);
        lblContact.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblContact);

        tfContact = new JTextField();
        tfContact.setBounds(500, 65, 200, 30);
        tfContact.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(tfContact);

        // Row 3
        JLabel lblBlood = new JLabel("Blood Group:");
        lblBlood.setBounds(20, 110, 120, 30);
        lblBlood.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblBlood);

        cbBloodGroup = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
        cbBloodGroup.setBounds(150, 110, 250, 30);
        formPanel.add(cbBloodGroup);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(20, 155, 120, 30);
        lblAddress.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(150, 155, 550, 30);
        tfAddress.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(tfAddress);

        // Row 4
        JLabel lblDisease = new JLabel("Major Disease:");
        lblDisease.setBounds(20, 200, 120, 30);
        lblDisease.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(lblDisease);

        tfDisease = new JTextField();
        tfDisease.setBounds(150, 200, 550, 30);
        tfDisease.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(tfDisease);

        // Buttons
        btnSave = new JButton("SAVE PATIENT");
        btnSave.setBounds(150, 280, 150, 40);
        btnSave.setBackground(new Color(46, 204, 113));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Arial", Font.BOLD, 14));
        btnSave.setBorder(BorderFactory.createRaisedBevelBorder());
        formPanel.add(btnSave);

        btnClear = new JButton("CLEAR");
        btnClear.setBounds(320, 280, 120, 40);
        btnClear.setBackground(new Color(241, 196, 15));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFont(new Font("Arial", Font.BOLD, 14));
        btnClear.setBorder(BorderFactory.createRaisedBevelBorder());
        formPanel.add(btnClear);

        btnBack = new JButton("BACK");
        btnBack.setBounds(460, 280, 120, 40);
        btnBack.setBackground(new Color(231, 76, 60));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBorder(BorderFactory.createRaisedBevelBorder());
        formPanel.add(btnBack);

        // Action Listeners
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Database Save Logic
                try {
                    String name = tfName.getText();
                    String age = tfAge.getText();
                    String gender = (String) cbGender.getSelectedItem();
                    String contact = tfContact.getText();
                    String blood = (String) cbBloodGroup.getSelectedItem();
                    String address = tfAddress.getText();
                    String disease = tfDisease.getText();

                    conn c = new conn();

                    String q = "INSERT INTO patient(name, age, gender, contact, blood_group, address, disease) VALUES('"
                            + name + "', '"
                            + age + "', '"
                            + gender + "', '"
                            + contact + "', '"
                            + blood + "', '"
                            + address + "', '"
                            + disease + "')";

                   // c.s.executeUpdate(q);

                    JOptionPane.showMessageDialog(null, "✔ Patient Saved Successfully!");

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "❌ Error: " + ex.getMessage());
                }

                // TODO: Database save logic
                JOptionPane.showMessageDialog(null, "Patient Saved Successfully!");
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfName.setText("");
                tfAge.setText("");
                tfContact.setText("");
                tfAddress.setText("");
                tfDisease.setText("");
                cbGender.setSelectedIndex(0);
                cbBloodGroup.setSelectedIndex(0);
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Back to Reception
                new Reception();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new NEW_PATIENT();
    }
}
