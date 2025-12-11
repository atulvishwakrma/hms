package H.M.S;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class BookStretcher extends JFrame {

    // Table & model for bookings
    private DefaultTableModel tableModel;
    private JTable table;

    public BookStretcher() {

        // ===== BASIC WINDOW SETTINGS =====
        setTitle("Book Stretcher - TR Hospital");          // Title bar text
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // Close behaviour
        setSize(900, 550);                                 // Initial window size
        setLocationRelativeTo(null);                       // Center of screen
        setLayout(new BorderLayout());                     // Responsive base layout[web:79]

        // ===== HEADER (TOP) =====
        JPanel header = new JPanel();
        header.setBackground(new Color(109, 164, 170));    // Teal color
        header.setPreferredSize(new Dimension(900, 70));   // Fixed header height
        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(header, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("BOOK STRETCHER");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        header.add(lblTitle);

        // ===== MAIN CONTENT (LEFT FORM + RIGHT TABLE) =====
        JPanel content = new JPanel(new BorderLayout());
        add(content, BorderLayout.CENTER);

        // ---------- LEFT SIDE: SIMPLE BOOKING FORM (GridBagLayout) ----------
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(248, 249, 250));
        formPanel.setBorder(BorderFactory.createTitledBorder("Booking Details"));
        // GridBagLayout se form resize hone par bhi neat rahega.[web:106]
        formPanel.setLayout(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(320, 0)); // Fixed-ish width
        content.add(formPanel, BorderLayout.WEST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);            // Space around fields
        gbc.fill = GridBagConstraints.HORIZONTAL;         // Stretch horizontally
        gbc.weightx = 1;
        int row = 0;

        // ---- Booking ID (optional, manual/auto) ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Booking ID:"), gbc);

        JTextField tfBookingId = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfBookingId, gbc);

        // ---- Patient Name ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Patient Name:"), gbc);

        JTextField tfPatientName = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfPatientName, gbc);

        // ---- Ward / Room No ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Ward / Room No:"), gbc);

        JTextField tfRoom = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfRoom, gbc);

        // ---- From Location (current place) ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("From (Current):"), gbc);

        JTextField tfFrom = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfFrom, gbc);

        // ---- To Location (destination) ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("To (Destination):"), gbc);

        JTextField tfTo = new JTextField();
        gbc.gridy = row++;
        formPanel.add(tfTo, gbc);

        // ---- Priority (Normal / Emergency) ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Priority:"), gbc);

        JComboBox<String> cbPriority = new JComboBox<>(
                new String[]{"Normal", "Emergency"});
        gbc.gridy = row++;
        formPanel.add(cbPriority, gbc);

        // ---- Time (simple text; later use TimePicker/DB) ----
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Time:"), gbc);

        JTextField tfTime = new JTextField("HH:MM");
        gbc.gridy = row++;
        formPanel.add(tfTime, gbc);

        // ---------- BUTTONS (BOOK, CLEAR, BACK) ----------
        JPanel buttonBar = new JPanel(new GridLayout(1, 3, 5, 0));
        JButton btnBook  = new JButton("BOOK");
        JButton btnClear = new JButton("CLEAR");
        JButton btnBack  = new JButton("BACK");
        buttonBar.add(btnBook);
        buttonBar.add(btnClear);
        buttonBar.add(btnBack);

        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(buttonBar, gbc);

        // ---------- RIGHT SIDE: BOOKINGS TABLE (auto-resize) ----------
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Stretcher Bookings"));
        content.add(tablePanel, BorderLayout.CENTER);

        // Table columns: simple & minimal
        String[] cols = {"Booking ID", "Patient", "Room", "From", "To", "Priority", "Time"};
        tableModel = new DefaultTableModel(cols, 0);      // Model for rows
        table = new JTable(tableModel);
        table.setRowHeight(22);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Responsive columns[web:147]

        JScrollPane scroll = new JScrollPane(table);       // Scroll + center fill
        tablePanel.add(scroll, BorderLayout.CENTER);

        // Demo sample row (baad me MySQL se data la sakte ho)
        tableModel.addRow(new Object[]{"B001", "Rohan", "101",
                "Ward A", "X-Ray Room", "Normal", "10:30"});

        // ===== ACTIONS =====

        // BOOK: new booking row table me add karo
        btnBook.addActionListener(e -> {
            // Basic validation: patient name, from, to required
            if (tfPatientName.getText().trim().isEmpty()
                    || tfFrom.getText().trim().isEmpty()
                    || tfTo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill Patient Name, From and To location!");
                return;
            }

            String bookingId = tfBookingId.getText().trim();
            if (bookingId.isEmpty()) {
                // Agar user ID nahi deta to simple auto ID bana sakte ho
                bookingId = "B" + String.format("%03d", tableModel.getRowCount() + 1);
            }

            // TableModel me nayi row insert
            tableModel.addRow(new Object[]{
                    bookingId,
                    tfPatientName.getText().trim(),
                    tfRoom.getText().trim(),
                    tfFrom.getText().trim(),
                    tfTo.getText().trim(),
                    cbPriority.getSelectedItem(),
                    tfTime.getText().trim()
            });

            JOptionPane.showMessageDialog(this, "Stretcher booked successfully!");

            // Form clear after booking
            tfBookingId.setText("");
            tfPatientName.setText("");
            tfRoom.setText("");
            tfFrom.setText("");
            tfTo.setText("");
            cbPriority.setSelectedIndex(0);
            tfTime.setText("HH:MM");
        });

        // CLEAR: sirf form data reset kare
        btnClear.addActionListener(e -> {
            tfBookingId.setText("");
            tfPatientName.setText("");
            tfRoom.setText("");
            tfFrom.setText("");
            tfTo.setText("");
            cbPriority.setSelectedIndex(0);
            tfTime.setText("HH:MM");
            table.clearSelection();
        });

        // BACK: Reception window par return
        btnBack.addActionListener(e -> {
            setVisible(false);
            new Reception();
        });

        // Table row click: existing booking ko form me load (agar edit future me chahiye)
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = table.getSelectedRow();
                if (rowIndex >= 0) {
                    tfBookingId.setText(tableModel.getValueAt(rowIndex, 0).toString());
                    tfPatientName.setText(tableModel.getValueAt(rowIndex, 1).toString());
                    tfRoom.setText(tableModel.getValueAt(rowIndex, 2).toString());
                    tfFrom.setText(tableModel.getValueAt(rowIndex, 3).toString());
                    tfTo.setText(tableModel.getValueAt(rowIndex, 4).toString());
                    cbPriority.setSelectedItem(tableModel.getValueAt(rowIndex, 5).toString());
                    tfTime.setText(tableModel.getValueAt(rowIndex, 6).toString());
                }
            }
        });

        setVisible(true);                                   // Show window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BookStretcher::new);     // Start safely on EDT
    }
}
