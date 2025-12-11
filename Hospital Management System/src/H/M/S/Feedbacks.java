package H.M.S;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Feedbacks extends JFrame {

    private DefaultTableModel tableModel;   // Feedback list ka model
    private JTable table;                   // Feedback table

    public Feedbacks() {

        // ===== BASIC WINDOW SETTINGS =====
        setTitle("Feedbacks - TR Hospital");             // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close behaviour
        setSize(950, 550);                               // Initial size
        setLocationRelativeTo(null);                     // Center screen
        setLayout(new BorderLayout());                   // Responsive base layout[web:85]

        // ===== HEADER (TOP BAR) =====
        JPanel header = new JPanel();
        header.setBackground(new Color(109, 164, 170));  // Teal like other tabs
        header.setPreferredSize(new Dimension(950, 70)); // Fixed height
        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(header, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("PATIENT FEEDBACKS");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        header.add(lblTitle);

        // ===== MAIN CONTENT: LEFT FORM + RIGHT TABLE =====
        JPanel content = new JPanel(new BorderLayout());
        add(content, BorderLayout.CENTER);

        // ---------- LEFT FEEDBACK FORM (simple & responsive) ----------
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(248, 249, 250));
        formPanel.setBorder(BorderFactory.createTitledBorder("Give Feedback"));
        // GridBagLayout form ko neat & resizable rakhta hai.[web:69][web:106]
        formPanel.setLayout(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(320, 0)); // Fixed-ish width
        content.add(formPanel, BorderLayout.WEST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);            // Space around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        int row = 0;

        // ---- Patient Name ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Name:"), gbc);

        JTextField tfName = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfName, gbc);

        // ---- Contact (optional) ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Contact:"), gbc);

        JTextField tfContact = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfContact, gbc);

        // ---- Department visited ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Department:"), gbc);

        JComboBox<String> cbDept = new JComboBox<>(new String[]{
                "General", "OPD", "Emergency", "Cardiology", "Orthopedics", "Other"
        });
        gbc.gridy = row++;
        formPanel.add(cbDept, gbc);

        // ---- Rating (1–5 simple combo) ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Rating (1–5):"), gbc);

        JComboBox<String> cbRating = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        cbRating.setSelectedIndex(4); // Default 5
        gbc.gridy = row++;
        formPanel.add(cbRating, gbc);

        // ---- Feedback message (multi-line) ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Feedback:"), gbc);

        JTextArea taFeedback = new JTextArea(4, 15);
        taFeedback.setLineWrap(true);
        taFeedback.setWrapStyleWord(true);
        JScrollPane feedbackScroll = new JScrollPane(taFeedback);
        gbc.gridy = row++;
        formPanel.add(feedbackScroll, gbc);

        // ---------- BUTTON BAR (SUBMIT, CLEAR, BACK) ----------
        JPanel buttonBar = new JPanel(new GridLayout(1, 3, 5, 0));
        JButton btnSubmit = new JButton("SUBMIT");
        JButton btnClear  = new JButton("CLEAR");
        JButton btnBack   = new JButton("BACK");
        buttonBar.add(btnSubmit);
        buttonBar.add(btnClear);
        buttonBar.add(btnBack);

        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(buttonBar, gbc);

        // ---------- RIGHT TABLE: FEEDBACK LIST ----------
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("All Feedbacks"));
        content.add(tablePanel, BorderLayout.CENTER);

        // Columns: basic feedback info[web:173]
        String[] cols = {"Name", "Contact", "Department", "Rating", "Feedback"};
        tableModel = new DefaultTableModel(cols, 0);      // Model for table rows
        table = new JTable(tableModel);
        table.setRowHeight(22);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Responsive columns[web:166]

        JScrollPane scroll = new JScrollPane(table);
        tablePanel.add(scroll, BorderLayout.CENTER);

        // Demo sample feedback row
        tableModel.addRow(new Object[]{
                "Rohan", "9876543210", "Emergency", "5", "Very good service."
        });

        // ===== ACTIONS =====

        // SUBMIT: new feedback table me add karega
        btnSubmit.addActionListener(e -> {
            String name = tfName.getText().trim();
            String feedbackText = taFeedback.getText().trim();

            // Basic validation: name + feedback required
            if (name.isEmpty() || feedbackText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill Name and Feedback!");
                return;
            }

            tableModel.addRow(new Object[]{
                    name,
                    tfContact.getText().trim(),
                    cbDept.getSelectedItem(),
                    cbRating.getSelectedItem(),
                    feedbackText
            });

            JOptionPane.showMessageDialog(this, "Thank you for your feedback!");

            // Form clear after submit
            tfName.setText("");
            tfContact.setText("");
            cbDept.setSelectedIndex(0);
            cbRating.setSelectedIndex(4);
            taFeedback.setText("");
        });

        // CLEAR: sirf form reset kare
        btnClear.addActionListener(e -> {
            tfName.setText("");
            tfContact.setText("");
            cbDept.setSelectedIndex(0);
            cbRating.setSelectedIndex(4);
            taFeedback.setText("");
            table.clearSelection();
        });

        // BACK: Reception par wapas
        btnBack.addActionListener(e -> {
            setVisible(false);
            new Reception();
        });

        // Table row click: feedback detail popup dikhana (optional UX)
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = table.getSelectedRow();
                if (rowIndex >= 0) {
                    String name = tableModel.getValueAt(rowIndex, 0).toString();
                    String dept = tableModel.getValueAt(rowIndex, 2).toString();
                    String rating = tableModel.getValueAt(rowIndex, 3).toString();
                    String fb = tableModel.getValueAt(rowIndex, 4).toString();

                    JOptionPane.showMessageDialog(
                            Feedbacks.this,
                            "Name: " + name +
                                    "\nDepartment: " + dept +
                                    "\nRating: " + rating +
                                    "\nFeedback:\n" + fb,
                            "Feedback Detail",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });

        setVisible(true);                                   // Show window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Feedbacks::new);         // Run on EDT
    }
}
