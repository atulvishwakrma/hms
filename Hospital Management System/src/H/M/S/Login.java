package H.M.S;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener{ //alt+enter for impliment actionlistner button
    JTextField textField;
    JPasswordField jPasswordField;
    JButton button1,button2;
    Login(){


        JLabel newlable = new JLabel("Username");
        newlable.setBounds(30,20,100,30);
        newlable.setFont(new Font("Aboreto",Font.BOLD,15));
        newlable.setForeground(Color.red);
        add(newlable); // it is use for add on frame

        JLabel password=new JLabel("Password");
        password.setBounds(30,70,100,30);
        password.setFont(new Font("tahoma", Font.BOLD,15));
        password.setForeground(Color.red);
        add(password);

        textField=new JTextField();
        textField.setBounds(150,20,150,30);
        textField.setFont(new Font("tahoma",Font.PLAIN,15));
        textField.setBackground(new Color(255,179,0));
        add(textField);

        jPasswordField =new JPasswordField();
        jPasswordField.setBounds(150,70 ,150,30);
        jPasswordField.setFont(new Font("tahoma",Font.PLAIN,15));
        jPasswordField.setBackground(new Color(255,179,0));
        add(jPasswordField);

        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("Templates/tr.png"));
        Image i1=imageIcon.getImage().getScaledInstance(320,320,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1=new ImageIcon(i1);
        JLabel label=new JLabel(imageIcon1);
        label.setBounds(350,-50,400,400);
        add(label);

        button1 = new JButton("Login");
        button1.setBounds(30,140,128,38);
        button1.setFont(new Font( "serif",Font.BOLD,15));
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.white);
        button1.addActionListener(this);
        add(button1);


        button2= new JButton("Cancel");
        button2.setBounds(170,140,128,38);
        button2.setFont(new Font("serif",Font.BOLD,15));
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.white);
        button2.addActionListener(this);
        add(button2);


        getContentPane().setBackground (new Color(255,206,153));
        setSize(750,300);
        setLocation(300,240); //for set frame {x for l to r , y for u to lo}
        setLayout(null);
        setVisible(true);



        }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource()==button1){
            try {
                conn c=new conn();
                String user=textField.getText();
                String Pass=jPasswordField.getText();


                String  q= "select * from login WHERE ID='"+user+"'and Password='"+Pass+"'";
                ResultSet resultSet= c.statement.executeQuery(q);

                if (resultSet.next()){
                    new Reception();
                    setVisible(false);
                } else{
                    JOptionPane.showMessageDialog(null,"dont match");
                }

            }catch (Exception E){
                E.printStackTrace();
            }


        }else {

            System.exit(100);

        }

    }
}

