package H.M.S;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchRoom extends JFrame {

    private DefaultTableModel tableModel;   // Room list ka model
    private JTable table;                   // Room list table
    private TableRowSorter<DefaultTableModel> sorter; // Filter/sort ke liye
    private JTextField tfSearch;            // Search text field
    private JComboBox<String> cbFilterBy;   // Kis column par filter kare

    public SearchRoom() {

        // ===== BASIC WINDOW SETTINGS =====
        setTitle("Search Room - TR Hospital");        // Title bar text
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 550);                            // Initial size
        setLocationRelativeTo(null);                  // Center screen
        setLayout(new BorderLayout());                // Responsive base layout[web:79]

        // ===== HEADER (TOP) =====
        JPanel header = new JPanel();
        header.setBackground(new Color(109, 164, 170));   // Teal
        header.setPreferredSize(new Dimension(900, 70));  // Fixed height
        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(header, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("SEARCH ROOM");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        header.add(lblTitle);

        // ===== MAIN CONTENT (CENTER) : LEFT FILTERS + RIGHT TABLE =====
        JPanel content = new JPanel(new BorderLayout());
        add(content, BorderLayout.CENTER);

        // ---------- LEFT FILTER PANEL (responsive width fix, height flex) ----------
        JPanel filterPanel = new JPanel();
        filterPanel.setBackground(new Color(248, 249, 250));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filter Rooms"));
        // GridBagLayout = flexible form jo resize ke sath adjust hota hai[web:106][web:85]
        filterPanel.setLayout(new GridBagLayout());
        filterPanel.setPreferredSize(new Dimension(260, 0));   // Width approx fix
        content.add(filterPanel, BorderLayout.WEST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);    // Components ke around gap
        gbc.fill = GridBagConstraints.HORIZONTAL; // Horizontally stretch
        gbc.weightx = 1;                         // Width share kare

        int row = 0;                             // Current row index

        // --- Filter By label + combo (Room No / Type / Status) ---
        gbc.gridx = 0; gbc.gridy = row;
        filterPanel.add(new JLabel("Filter By:"), gbc);

        cbFilterBy = new JComboBox<>(new String[]{
                "All Columns", "Room No", "Type", "Status"
        });
        gbc.gridy = row++;
        filterPanel.add(cbFilterBy, gbc);

        // --- Search Text label ---
        gbc.gridx = 0; gbc.gridy = row;
        filterPanel.add(new JLabel("Search Text:"), gbc);

        // Search field jisme user text likhega
        tfSearch = new JTextField();
        gbc.gridy = row++;
        filterPanel.add(tfSearch, gbc);

        // --- Status quick buttons (Available / Occupied / Cleaning) ---
        gbc.gridx = 0; gbc.gridy = row;
        filterPanel.add(new JLabel("Quick Status:"), gbc);

        JPanel statusButtons = new JPanel(new GridLayout(1, 3, 5, 0));
        JButton btnAvailable = new JButton("Available");
        JButton btnOccupied  = new JButton("Occupied");
        JButton btnCleaning  = new JButton("Cleaning");
        statusButtons.add(btnAvailable);
        statusButtons.add(btnOccupied);
        statusButtons.add(btnCleaning);

        gbc.gridy = row++;
        filterPanel.add(statusButtons, gbc);

        // --- Clear + Back buttons row ---
        JPanel bottomButtons = new JPanel(new GridLayout(1, 2, 5, 0));
        JButton btnClear = new JButton("CLEAR");
        JButton btnBack  = new JButton("BACK");
        bottomButtons.add(btnClear);
        bottomButtons.add(btnBack);

        gbc.gridx = 0; gbc.gridy = row;
        filterPanel.add(bottomButtons, gbc);

        // ---------- RIGHT TABLE PANEL (auto-resize with window) ----------
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Rooms List"));
        content.add(tablePanel, BorderLayout.CENTER);

        // Table columns define
        String[] columns = {"Room No", "Type", "Charges (₹)", "Status"};
        // DefaultTableModel dynamic rows ke liye[web:62]
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setRowHeight(22);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Columns window width ke according adjust

        // TableRowSorter sorting + filtering dono handle karega[web:105][web:62]
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(table);  // Scroll + responsive center
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Demo sample data (baad me DB data se replace kar sakte ho)
        tableModel.addRow(new Object[]{"101", "General", "1500", "Available"});
        tableModel.addRow(new Object[]{"102", "ICU",     "5000", "Occupied"});
        tableModel.addRow(new Object[]{"103", "Private", "3000", "Available"});
        tableModel.addRow(new Object[]{"104", "Ward",    "1200", "Under Cleaning"});

        // ====== LISTENERS / FUNCTIONALITY ======

        // ---- Generic text filter: search box me type karte hi filter apply ----
        // RowFilter regexFilter ke through text match karega[web:101][web:100]
        tfSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void filter() {
                String text = tfSearch.getText().trim();

                // Agar text empty hai to koi filter nahi
                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                    return;
                }

                int colIndex = -1; // -1 means all columns
                String selected = cbFilterBy.getSelectedItem().toString();

                // ComboBox ke hisab se specific column select kar rahe hain
                if (selected.equals("Room No"))  colIndex = 0;
                if (selected.equals("Type"))     colIndex = 1;
                if (selected.equals("Status"))   colIndex = 3;

                try {
                    if (colIndex == -1) {
                        // All columns par filter (kisi bhi column me match chalega)[web:100][web:113]
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    } else {
                        // Sirf selected column par filter
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, colIndex));
                    }
                } catch (java.util.regex.PatternSyntaxException ex) {
                    // Regex galat ho to filter ignore kar dena
                    sorter.setRowFilter(null);
                }
            }

            @Override public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            @Override public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            @Override public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }
        });

        // ---- Quick status buttons: sirf ek particular status dikhao ----
        btnAvailable.addActionListener(e -> {
            cbFilterBy.setSelectedItem("Status");                  // Status column select
            tfSearch.setText("Available");                         // Search text set
        });

        btnOccupied.addActionListener(e -> {
            cbFilterBy.setSelectedItem("Status");
            tfSearch.setText("Occupied");
        });

        btnCleaning.addActionListener(e -> {
            cbFilterBy.setSelectedItem("Status");
            tfSearch.setText("Cleaning");                          // "Cleaning" matches "Under Cleaning"
        });

        // ---- CLEAR: search text, filter, selection sab reset ----
        btnClear.addActionListener(e -> {
            tfSearch.setText("");
            cbFilterBy.setSelectedIndex(0);                        // All Columns
            sorter.setRowFilter(null);                             // Remove filter
            table.clearSelection();
        });

        // ---- BACK: Reception screen par return ----
        btnBack.addActionListener(e -> {
            setVisible(false);
            new Reception();
        });

        // ---- Table row click: detail popup (optional info) ----
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = table.getSelectedRow();
                if (rowIndex >= 0) {
                    // View index ko model index me convert (filter/sort hone par jaruri)
                    int modelRow = table.convertRowIndexToModel(rowIndex);
                    String roomNo = tableModel.getValueAt(modelRow, 0).toString();
                    String type   = tableModel.getValueAt(modelRow, 1).toString();
                    String charge = tableModel.getValueAt(modelRow, 2).toString();
                    String status = tableModel.getValueAt(modelRow, 3).toString();

                    JOptionPane.showMessageDialog(
                            SearchRoom.this,
                            "Room: " + roomNo + "\nType: " + type +
                                    "\nCharges: ₹" + charge + "\nStatus: " + status,
                            "Room Details",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });

        setVisible(true);                                      // Window show
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SearchRoom::new);            // Start on EDT
    }
}
