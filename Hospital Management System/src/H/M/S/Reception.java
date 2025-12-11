package H.M.S;

import javax.swing.*;
import java.awt.*;

public class Reception extends JFrame {
    Reception() {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 160, 1525, 670);
        panel.setBackground(new Color(109, 164, 170));
        add(panel);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(5, 5, 1525, 150);
        panel1.setBackground(new Color(109, 164, 170));
        add(panel1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Templates/doctor.png"));
        Image image = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(image);
        JLabel lable = new JLabel(i2);
        lable.setBounds(1100, 0, 250, 250);
        panel1.add(lable);

        JButton btn1 = new JButton("Add New Patient");
        btn1.setBounds(30, 25, 150, 30);
        btn1.setBackground(new Color(246, 215, 118));
        panel1.add(btn1);




        btn1.addActionListener(e -> {
            setVisible(false);
            new NEW_PATIENT();
        });




        JButton btn2 = new JButton("Room");
        btn2.setBounds(30, 60, 150, 30);
        btn2.setBackground(new Color(246, 215, 118));
        panel1.add(btn2);

        btn2.addActionListener(e -> {
            setVisible(false);        // Reception page hide
            new RoomManagement();     // Room section open
        });


        JButton btn3 = new JButton("Department");
        btn3.setBounds(30, 95, 150, 30);
        btn3.setBackground(new Color(246, 215, 118));
        panel1.add(btn3);

        btn3.addActionListener(e -> {
            setVisible(false);              // Reception hide
            new DepartmentManagement();     // Department window open
        });


        JButton btn4 = new JButton("Employee info");
        btn4.setBounds(205, 25, 150, 30);
        btn4.setBackground(new Color(246, 215, 118));
        panel1.add(btn4);


        btn4.addActionListener(e -> {
            setVisible(false);
            new EmployeeInfo();
        });


        JButton btn5 = new JButton("Patient Info");
        btn5.setBounds(205, 60, 150, 30);
        btn5.setBackground(new Color(246, 215, 118));
        panel1.add(btn5);
        btn3.addActionListener(e -> {
            setVisible(false);              // Reception hide
            new DepartmentManagement();     // Department window open
        });


        JButton btn6 = new JButton("Patient Discharge");
        btn6.setBounds(205, 95, 150, 30);
        btn6.setBackground(new Color(246, 215, 118));
        panel1.add(btn6);

        btn6.addActionListener(e -> {
            setVisible(false);          // Reception window hide
            new PatientDischarge();     // Discharge window open
        });


        JButton btn7 = new JButton("Search room");
        btn7.setBounds(380, 25, 150, 30);
        btn7.setBackground(new Color(246, 215, 118));
        panel1.add(btn7);

        btn7.addActionListener(e -> {
            setVisible(false);
            new SearchRoom();
        });


        JButton btn8 = new JButton("Update Patient");
        btn8.setBounds(380, 60, 150, 30);
        btn8.setBackground(new Color(246, 215, 118));
        panel1.add(btn8);

        btn8.addActionListener(e -> {
            setVisible(false);
            new UpdatePatient();
        });


        JButton btn9 = new JButton("Hospital Ambulance");
        btn9.setBounds(380, 95, 150, 30);
        btn9.setBackground(new Color(246, 215, 118));
        panel1.add(btn9);

        btn9.addActionListener(e -> {
            setVisible(false);
            new AmbulanceManagement();
        });


        JButton btn10 = new JButton("Book Stretcher");
        btn10.setBounds(555, 25, 150, 30);
        btn10.setBackground(new Color(246, 215, 118));
        panel1.add(btn10);

        btn10.addActionListener(e -> {
            setVisible(false);
            new BookStretcher();
        });


        // NEW BUTTONS
        JButton btn11 = new JButton("Blood Bank");
        btn11.setBounds(555, 60, 150, 30);
        btn11.setBackground(new Color(246, 215, 118));
        panel1.add(btn11);

        btn11.addActionListener(e -> {
            setVisible(false);
            new BloodBankManagement();
        });


        JButton btn12 = new JButton("Feedback's");
        btn12.setBounds(555, 95, 150, 30);
        btn12.setBackground(new Color(246, 215, 118));
        panel1.add(btn12);
        btn12.addActionListener(e -> {
            setVisible(false);
            new Feedbacks();
        });


        JButton btn13 = new JButton("Logout");
        btn13.setBounds(730, 25, 150, 30);
        btn13.setBackground(new Color(246, 215, 118));
        panel1.add(btn13);

        btn13.addActionListener(e -> {
            // User se confirm puchne ke liye YES / NO dialog dikhate hain
            int choice = JOptionPane.showConfirmDialog(
                    Reception.this,                           // Parent frame
                    "Are you sure you want to logout?",       // Message
                    "Confirm Logout",                         // Dialog title
                    JOptionPane.YES_NO_OPTION,                // Buttons type
                    JOptionPane.QUESTION_MESSAGE              // Icon type
            );

            // Agar user YES dabata hai to hi logout kare
            if (choice == JOptionPane.YES_OPTION) {
                // Current Reception window band / hide
                setVisible(false);

                // Login screen wapas open karo
                new Login();   // Yahan tumhari login JFrame class ka naam use karo
            }
            // Agar NO dabaya to kuch nahi hoga (sirf dialog close ho jayega)
        });


        // You can add ActionListeners here for each new button
        // Example:
        /*
        btn11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // open Blood Bank window
            }
        });
        */

        setSize(2000, 1090);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setVisible(true);
    }




    public static void main(String[] args) {
        new Reception();
    }
}
