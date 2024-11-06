package tugaspraktikum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ManajemenKP extends javax.swing.JFrame {
    Connection conn;
    private DefaultTableModel model;
    private DefaultTableModel modelProyek;
    private DefaultTableModel modelTransaksi;
    public ManajemenKP() {
        initComponents();
        conn = koneksi.getConnection();
        loadComboBoxProyek();
        loadComboBoxKaryawan();
        //tabel Karyawan
        model = new DefaultTableModel();
        tblKaryawan.setModel(model);
        model.addColumn("Id Proyek");
        model.addColumn("Nama");
        model.addColumn("Jabatan");
        model.addColumn("Departemen");        
        loadData();
        
        // Tabel Proyek
        modelProyek = new DefaultTableModel();
        tblProyek.setModel(modelProyek);
        modelProyek.addColumn("Id Proyek");
        modelProyek.addColumn("Nama Proyek");
        modelProyek.addColumn("Durasi Pengerjaan");
        loadDataProyek(); 
        
        // Tabel Transaksi
        modelTransaksi = new DefaultTableModel();
        tblTransaksi.setModel(modelTransaksi);
//        modelTransaksi.addColumn("Nama Karyawan");
//        modelTransaksi.addColumn("Nama Proyek");
        modelTransaksi.addColumn("Id Karyawan");
        modelTransaksi.addColumn("id Proyek");
        modelTransaksi.addColumn("Peran");
        loadDataTransaksi();
    }
    
 

// Load Karyawan
    private void loadData() {
          model.setRowCount(0);
          try {
              String sql = "SELECT * FROM karyawan ";
              PreparedStatement ps = conn.prepareStatement(sql);
              ResultSet rs = ps.executeQuery();
              while (rs.next()) {
                 model.addRow(new Object[]{
                 rs.getInt("ID"),
                 rs.getString("Nama"),
                 rs.getString("Jabatan"),
                 rs.getString("Departemen")
               });
              }
          } catch (SQLException e) {
             System.out.println("Error Save Data" + e.getMessage());
           }          
        }
// Load Proyek
    private void loadDataProyek() {
            modelProyek.setRowCount(0);
            try {
                String sql = "SELECT * FROM proyek";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    modelProyek.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nama_proyek"),
                        rs.getInt("durasi_pengerjaan")
                    });
                }
            } catch (SQLException e) {
                System.out.println("Error Load Data Proyek: " + e.getMessage());
            }
        }
//load Transaksi
    private void loadDataTransaksi() {
        modelTransaksi.setRowCount(0);
        try {
            String sql = "SELECT k.nama as nama_karyawan, p.nama_proyek, " +
                         "t.peran, t.id_karyawan, t.id_proyek " +
                         "FROM transaksi t " +
                         "JOIN karyawan k ON t.id_karyawan = k.id " +
                         "JOIN proyek p ON t.id_proyek = p.id " +
                         "ORDER BY k.nama, p.nama_proyek";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelTransaksi.addRow(new Object[]{
                    rs.getString("nama_karyawan"),
                    rs.getString("nama_proyek"),
                    rs.getString("peran"),
                    rs.getInt("id_karyawan"),
                    rs.getInt("id_proyek")
                    
                });
            }
        } catch (SQLException e) {
            System.out.println("Error Load Data Transaksi: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Gagal memuat data transaksi: " + e.getMessage());
        }
    }
//Load ComboBox Karyawan
    private void loadComboBoxKaryawan() {
        cbKaryawan.removeAllItems();
        try {
            String sql = "SELECT id, nama FROM karyawan";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tampilan = String.format("%d - %s", 
                    rs.getInt("id"),
                    rs.getString("nama")
                );
                cbKaryawan.addItem(tampilan);
            }
        } catch (SQLException e) {
            System.out.println("Kesalahan Memuat Data Karyawan: " + e.getMessage());
        }
        }
//Load ComboBox Proyek
    private void loadComboBoxProyek() {
        cbProyek.removeAllItems();
        try {
            String sql = "SELECT id, nama_proyek FROM proyek";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tampilan = String.format("%d - %s", 
                    rs.getInt("id"),
                    rs.getString("nama_proyek")
                );
                cbProyek.addItem(tampilan);
            }
        } catch (SQLException e) {
            System.out.println("Kesalahan Memuat Data Proyek: " + e.getMessage());
        }
    }
       private int getSelectedId(String comboBoxText) {
        if (comboBoxText == null || comboBoxText.isEmpty()) return -1;
        try {
            // Format combo box: "1 - Nama"
            return Integer.parseInt(comboBoxText.split(" - ")[0]);
        } catch (Exception e) {
            System.out.println("Error parsing ID: " + e.getMessage());
            return -1;
        }
    }
// Create Transaksi
   private void tambahTransaksi() {
try {
        // Cek apakah ComboBox Karyawan atau Proyek tidak dipilih
        if (cbKaryawan.getSelectedItem() == null || cbProyek.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Pilih karyawan dan proyek terlebih dahulu");
            return;
        }
        
        // Cek apakah tf_peran kosong
        if (tf_peran.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Masukkan peran terlebih dahulu");
            return;
        }
        
        String selectedKaryawan = cbKaryawan.getSelectedItem().toString();
        String selectedProyek = cbProyek.getSelectedItem().toString();
        
        int idKaryawan = getSelectedId(selectedKaryawan);
        int idProyek = getSelectedId(selectedProyek);
        
        if (idKaryawan == -1 || idProyek == -1) {
            JOptionPane.showMessageDialog(null, "ID Karyawan atau ID Proyek tidak valid");
            return;
        }
        
        // Menambahkan data baru ke dalam tabel transaksi
        String sql = "INSERT INTO transaksi (id_karyawan, id_proyek, peran) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idKaryawan);
        ps.setInt(2, idProyek);
        ps.setString(3, tf_peran.getText().trim());
        
        int rowsInserted = ps.executeUpdate(); // Mengecek apakah data berhasil ditambahkan
        if (rowsInserted > 0) {
            JOptionPane.showMessageDialog(null, "Data Transaksi berhasil ditambahkan");
            loadDataTransaksi();
            clearTransaksiFields();
        } else {
            JOptionPane.showMessageDialog(null, "Data tidak berhasil disimpan.");
        }
        
    } catch (SQLException e) {
        System.out.println("Error Tambah Transaksi: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "Gagal menambah data: " + e.getMessage());
    }
}


private void clearTransaksiFields() {
    tf_peran.setText("");
    cbKaryawan.setSelectedIndex(0);
    cbProyek.setSelectedIndex(0);
} 
//Update Transaksi
   private void updateTransaksi() {
       if (tf_peran.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pastikan semua data telah diisi.");
            return;
       }
    try {
        String sql = "UPDATE transaksi SET peran = ? WHERE id_karyawan = ? AND id_proyek = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int idKaryawan = getSelectedId(cbKaryawan.getSelectedItem().toString());
        int idProyek = getSelectedId(cbProyek.getSelectedItem().toString());
        
        ps.setString(1, tf_peran.getText());
        ps.setInt(2, idKaryawan);
        ps.setInt(3, idProyek);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Data Transaksi berhasil diperbarui");
        loadDataTransaksi();
    } catch (SQLException e) {
        System.out.println("Kesalahan Memperbarui Data Transaksi: " + e.getMessage());
    }
}
//Hapus Transaksi
   private void hapusTransaksi() {
    try {
        // Memastikan pengguna sudah memilih karyawan dan proyek dari ComboBox
        if (cbKaryawan.getSelectedItem() == null || cbProyek.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Pilih karyawan dan proyek terlebih dahulu");
            return;
        }

        // Mendapatkan ID karyawan dan proyek dari ComboBox
        int idKaryawan = getSelectedId(cbKaryawan.getSelectedItem().toString());
        int idProyek = getSelectedId(cbProyek.getSelectedItem().toString());

        int confirm = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin menghapus data transaksi ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM transaksi WHERE id_karyawan = ? AND id_proyek = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idKaryawan);
            ps.setInt(2, idProyek);

            int result = ps.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Data Transaksi berhasil dihapus");
                loadDataTransaksi();
                clearTransaksiFields();  // Membersihkan field setelah menghapus data
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan atau sudah terhapus");
            }
        }
    } catch (SQLException e) {
        System.out.println("Kesalahan Menghapus Data Transaksi: " + e.getMessage());
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}

//Create Karyawan
    private void saveData() {
              try {
        
        if (tf_nama.getText().trim().isEmpty() || tf_jabatan.getText().trim().isEmpty() || tf_departemen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pastikan semua data telah diisi.");
            return;
        }
        
        String sql = "INSERT INTO karyawan (nama, jabatan, departemen) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tf_nama.getText().trim());
        ps.setString(2, tf_jabatan.getText().trim());
        ps.setString(3, tf_departemen.getText().trim());
        
        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Data saved successfully");
        
        loadData(); 
        loadComboBoxKaryawan(); 
        clearKaryawanFields(); 
        
    } catch (SQLException e) {
        System.out.println("Error Save Data: " + e.getMessage());
        JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + e.getMessage());
    }

         }
    private void clearKaryawanFields() {
        tf_idKaryawan.setText("");
        tf_nama.setText("");
        tf_jabatan.setText("");
        tf_departemen.setText("");
    }
// Create proyek
    private void saveDataProyek() {
    try {
        // Memastikan semua data telah diisi
        if (tf_namaProyek.getText().trim().isEmpty() || tf_durasiPengerjaan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pastikan semua data telah diisi.");
            return;
        }
        
        // Validasi: Memeriksa apakah durasi pengerjaan adalah angka
        try {
            Integer.parseInt(tf_durasiPengerjaan.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Durasi pengerjaan harus berupa angka.");
            return;
        }

        // Menyimpan data proyek ke dalam database
        String sql = "INSERT INTO proyek (nama_proyek, durasi_pengerjaan) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tf_namaProyek.getText());
        ps.setInt(2, Integer.parseInt(tf_durasiPengerjaan.getText()));
        ps.executeUpdate();

        JOptionPane.showMessageDialog(this, "Data proyek berhasil disimpan");
        
        // Memperbarui data di tabel dan ComboBox
        loadDataProyek();
        loadComboBoxProyek();
        clearProyekFields();
    } catch (SQLException e) {
        System.out.println("Error Save Data Proyek: " + e.getMessage());
    }
}

// Update Karyawan
    private void updateData() {
          try {
              String sql = "UPDATE karyawan SET nama = ?, jabatan = ?, departemen = ? WHERE id = ?";
              PreparedStatement ps = conn.prepareStatement(sql);
              ps.setString(1, tf_nama.getText());
              ps.setString(2, tf_jabatan.getText());
              ps.setString(3, tf_departemen.getText());
              ps.setInt(4, Integer.parseInt(tf_idKaryawan.getText()));
              ps.executeUpdate();
              JOptionPane.showMessageDialog(this, "Data updated successfully");
              loadData();
              loadComboBoxKaryawan();
              clearKaryawanFields();
          }  catch (SQLException e) {
              System.out.println("Error Save Data" + e.getMessage());
          }
         }
//Update Proyek
    private void updateDataProyek() {
        try {
            String sql = "UPDATE proyek SET nama_proyek = ?, durasi_pengerjaan = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tf_namaProyek.getText());
            ps.setInt(2, Integer.parseInt(tf_durasiPengerjaan.getText()));
            ps.setInt(3, Integer.parseInt(tf_idProyek.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data proyek updated successfully");
            loadDataProyek();
            loadComboBoxProyek();
            clearProyekFields();
        } catch (SQLException e) {
            System.out.println("Error Update Data Proyek: " + e.getMessage());
        }
    }
    private void aturUlangIDKaryawan() {
    try {
        // Ambil data yang sudah diurutkan berdasarkan ID
        String selectSQL = "SELECT id_karyawan FROM karyawan ORDER BY id_karyawan";
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery(selectSQL);

        int newId = 1;
        while (rs.next()) {
            // Memperbarui ID dengan nilai berurutan mulai dari 1
            rs.updateInt("id_karyawan", newId++);
            rs.updateRow();
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error saat mengatur ulang ID: " + e);
    }
}
    private void resetAutoIncrementKaryawan() {
    try {
        String sql = "ALTER TABLE karyawan AUTO_INCREMENT = 1";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error saat mereset auto-increment: " + e);
    }
}
//Delete Karyawan
        private void deleteData() {
         try  {
              String sql = "DELETE FROM karyawan WHERE id = ?";
              PreparedStatement ps = conn.prepareStatement(sql);
              ps.setInt(1, Integer.parseInt(tf_idKaryawan.getText()));
              ps.executeUpdate();
              JOptionPane.showMessageDialog(this, "Data deleted successfully");
              loadData();
              loadComboBoxKaryawan();
              aturUlangIDKaryawan();
              resetAutoIncrementKaryawan();
              clearKaryawanFields();
         } catch (SQLException e) {
              System.out.println("Error Save Data" + e.getMessage());
          }
        }
        private void deleteDataProyek() {
        try {
        int id = Integer.parseInt(tf_idProyek.getText());
        
        // Mulai transaksi
        conn.setAutoCommit(false);
        
        try {
            // Hapus data di tabel proyek
            String sql = "DELETE FROM proyek WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            // Reset auto increment dan reorder ID
            String resetSql = "SET @count = 0";
            String updateSql = "UPDATE proyek SET id = @count:= @count + 1";
            String alterSql = "ALTER TABLE proyek AUTO_INCREMENT = 1";
            
            Statement stmt = conn.createStatement();
            stmt.execute(resetSql);
            stmt.execute(updateSql);
            stmt.execute(alterSql);
            
            // Commit transaksi
            conn.commit();
            
            JOptionPane.showMessageDialog(this, "Data Proyek berhasil dihapus.");
            loadDataProyek();
            loadDataTransaksi();
            loadComboBoxProyek();
            clearProyekFields();
            
        } catch (SQLException ex) {
            // Rollback jika terjadi error
            conn.rollback();
            throw ex;
        } finally {
            // Kembalikan auto commit ke true
            conn.setAutoCommit(true);
        }
        
    } catch (SQLException e) {
        System.out.println("Error Hapus Data Proyek: " + e.getMessage());
        JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + e.getMessage());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "ID tidak valid");
    }
    }
private void clearProyekFields() {
    tf_idProyek.setText("");
    tf_namaProyek.setText("");
    tf_durasiPengerjaan.setText("");
}

            
        

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        p_karyawan = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tf_nama = new javax.swing.JTextField();
        tf_idKaryawan = new javax.swing.JTextField();
        tf_jabatan = new javax.swing.JTextField();
        tf_departemen = new javax.swing.JTextField();
        btnC1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        bntU1 = new javax.swing.JButton();
        btnD1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblKaryawan = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnC2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnU2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnD2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        tf_namaProyek = new javax.swing.JTextField();
        tf_idProyek = new javax.swing.JTextField();
        tf_durasiPengerjaan = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblProyek = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbKaryawan = new javax.swing.JComboBox<>();
        cbProyek = new javax.swing.JComboBox<>();
        tf_peran = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        btnC3 = new javax.swing.JButton();
        btnU3 = new javax.swing.JButton();
        btnD3 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(255, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Aplikasi ManajemenKP");
        jPanel5.add(jLabel1);

        getContentPane().add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jTabbedPane1.setBackground(new java.awt.Color(51, 51, 51));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        p_karyawan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("ID  Karyawan");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nama");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Jabatan");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Departemen");

        tf_nama.setBackground(new java.awt.Color(0, 102, 255));
        tf_nama.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tf_nama.setForeground(new java.awt.Color(255, 255, 255));
        tf_nama.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tf_idKaryawan.setBackground(new java.awt.Color(0, 102, 255));
        tf_idKaryawan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tf_idKaryawan.setForeground(new java.awt.Color(255, 255, 255));
        tf_idKaryawan.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tf_jabatan.setBackground(new java.awt.Color(0, 102, 255));
        tf_jabatan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tf_jabatan.setForeground(new java.awt.Color(255, 255, 255));
        tf_jabatan.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tf_departemen.setBackground(new java.awt.Color(0, 102, 255));
        tf_departemen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tf_departemen.setForeground(new java.awt.Color(255, 255, 255));
        tf_departemen.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnC1.setBackground(new java.awt.Color(0, 102, 255));
        btnC1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnC1.setForeground(new java.awt.Color(255, 255, 255));
        btnC1.setText("Create");
        btnC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Action");

        bntU1.setBackground(new java.awt.Color(255, 204, 0));
        bntU1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bntU1.setForeground(new java.awt.Color(255, 255, 255));
        bntU1.setText("Update");
        bntU1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntU1ActionPerformed(evt);
            }
        });

        btnD1.setBackground(new java.awt.Color(255, 0, 0));
        btnD1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnD1.setForeground(new java.awt.Color(255, 255, 255));
        btnD1.setText("Delete");
        btnD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD1ActionPerformed(evt);
            }
        });

        tblKaryawan.setBackground(new java.awt.Color(0, 102, 255));
        tblKaryawan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblKaryawan.setForeground(new java.awt.Color(255, 255, 255));
        tblKaryawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Karyawan", "Nama Karyawan", "Jabatan", "Departemen"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblKaryawan);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tugaspraktikum/Identification Documents_1.png"))); // NOI18N

        javax.swing.GroupLayout p_karyawanLayout = new javax.swing.GroupLayout(p_karyawan);
        p_karyawan.setLayout(p_karyawanLayout);
        p_karyawanLayout.setHorizontalGroup(
            p_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_karyawanLayout.createSequentialGroup()
                .addGroup(p_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(p_karyawanLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnC1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntU1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnD1))
                    .addGroup(p_karyawanLayout.createSequentialGroup()
                        .addGroup(p_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(p_karyawanLayout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(jLabel3)
                                .addGap(117, 117, 117))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_karyawanLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(p_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(p_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(p_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(p_karyawanLayout.createSequentialGroup()
                                                    .addGroup(p_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(tf_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(tf_departemen, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(tf_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(tf_idKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(50, 50, 50))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_karyawanLayout.createSequentialGroup()
                                                    .addComponent(jLabel6)
                                                    .addGap(122, 122, 122)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_karyawanLayout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(135, 135, 135)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_karyawanLayout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(141, 141, 141)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_karyawanLayout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addGap(108, 108, 108)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56))
        );
        p_karyawanLayout.setVerticalGroup(
            p_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_karyawanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(p_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p_karyawanLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_idKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(7, 7, 7)
                        .addComponent(tf_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_departemen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p_karyawanLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(p_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(btnC1)
                    .addComponent(bntU1)
                    .addComponent(btnD1))
                .addGap(30, 30, 30))
        );

        jTabbedPane1.addTab("Karyawan", p_karyawan);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnC2.setBackground(new java.awt.Color(0, 102, 255));
        btnC2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnC2.setForeground(new java.awt.Color(255, 255, 255));
        btnC2.setText("Create");
        btnC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Action");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("ID Proyek ");

        btnU2.setBackground(new java.awt.Color(255, 204, 0));
        btnU2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnU2.setForeground(new java.awt.Color(255, 255, 255));
        btnU2.setText("Update");
        btnU2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnU2ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Nama Proyek");

        btnD2.setBackground(new java.awt.Color(255, 0, 0));
        btnD2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnD2.setForeground(new java.awt.Color(255, 255, 255));
        btnD2.setText("Delete");
        btnD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD2ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Durasi Pekerjaan");

        tf_namaProyek.setBackground(new java.awt.Color(0, 102, 255));
        tf_namaProyek.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tf_namaProyek.setForeground(new java.awt.Color(255, 255, 255));
        tf_namaProyek.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tf_idProyek.setBackground(new java.awt.Color(0, 102, 255));
        tf_idProyek.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tf_idProyek.setForeground(new java.awt.Color(255, 255, 255));
        tf_idProyek.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tf_idProyek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_idProyekActionPerformed(evt);
            }
        });

        tf_durasiPengerjaan.setBackground(new java.awt.Color(0, 102, 255));
        tf_durasiPengerjaan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tf_durasiPengerjaan.setForeground(new java.awt.Color(255, 255, 255));
        tf_durasiPengerjaan.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tblProyek.setBackground(new java.awt.Color(0, 102, 255));
        tblProyek.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblProyek.setForeground(new java.awt.Color(255, 255, 255));
        tblProyek.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID proyek", "Nama Proyek", "Durasi Pengerjaan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblProyek);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tugaspraktikum/Project Management.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnC2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnU2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnD2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(jLabel11))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_namaProyek, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_durasiPengerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_idProyek, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(132, 132, 132)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel9))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(jLabel18)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)))
                .addGap(58, 58, 58))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_idProyek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_namaProyek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_durasiPengerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(btnC2)
                    .addComponent(btnU2)
                    .addComponent(btnD2))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Proyek", jPanel3);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("ID Karyawan");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("ID Proyek");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Peran");

        cbKaryawan.setBackground(new java.awt.Color(0, 102, 255));
        cbKaryawan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbKaryawan.setForeground(new java.awt.Color(255, 255, 255));

        cbProyek.setBackground(new java.awt.Color(0, 102, 255));
        cbProyek.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbProyek.setForeground(new java.awt.Color(255, 255, 255));

        tf_peran.setBackground(new java.awt.Color(0, 102, 255));
        tf_peran.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tf_peran.setForeground(new java.awt.Color(255, 255, 255));

        tblTransaksi.setBackground(new java.awt.Color(0, 102, 255));
        tblTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID karyawan", "ID Proyek", "Peran"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblTransaksi);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Action");

        btnC3.setBackground(new java.awt.Color(0, 102, 255));
        btnC3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnC3.setForeground(new java.awt.Color(255, 255, 255));
        btnC3.setText("Create");
        btnC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC3ActionPerformed(evt);
            }
        });

        btnU3.setBackground(new java.awt.Color(255, 204, 0));
        btnU3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnU3.setForeground(new java.awt.Color(255, 255, 255));
        btnU3.setText("Update");
        btnU3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnU3ActionPerformed(evt);
            }
        });

        btnD3.setBackground(new java.awt.Color(255, 0, 0));
        btnD3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnD3.setForeground(new java.awt.Color(255, 255, 255));
        btnD3.setText("Delete");
        btnD3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD3ActionPerformed(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tugaspraktikum/Transaction.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(69, 69, 69)
                                        .addComponent(jLabel14)
                                        .addGap(77, 77, 77))
                                    .addComponent(cbProyek, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_peran, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbKaryawan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(58, 58, 58)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(64, 64, 64)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel19)))
                        .addGap(59, 59, 59)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnC3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnU3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnD3)))
                .addGap(37, 37, 37))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbProyek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_peran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(btnC3)
                    .addComponent(btnU3)
                    .addComponent(btnD3))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Transaksi", jPanel6);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tugaspraktikum/Management.png"))); // NOI18N
        jLabel2.setText("Menu Navigation ");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tugaspraktikum/Logout Rounded Left_1.png"))); // NOI18N
        jLabel16.setText("Keluar");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel16)
                .addContainerGap())
        );

        getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntU1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntU1ActionPerformed
        // TODO add your handling code here:
        updateData();
    }//GEN-LAST:event_bntU1ActionPerformed

    private void btnC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnC1ActionPerformed
        // TODO add your handling code here:
        saveData();
    }//GEN-LAST:event_btnC1ActionPerformed

    private void btnC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnC2ActionPerformed
        // TODO add your handling code here:
        saveDataProyek();
    }//GEN-LAST:event_btnC2ActionPerformed

    private void tf_idProyekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_idProyekActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_idProyekActionPerformed

    private void btnD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD2ActionPerformed
        // TODO add your handling code here:
        deleteDataProyek();
    }//GEN-LAST:event_btnD2ActionPerformed

    private void btnD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD1ActionPerformed
        // TODO add your handling code here:
        deleteData();
    }//GEN-LAST:event_btnD1ActionPerformed

    private void btnU2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnU2ActionPerformed
        // TODO add your handling code here:
        updateDataProyek();
    }//GEN-LAST:event_btnU2ActionPerformed

    private void btnC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnC3ActionPerformed
        // TODO add your handling code here:
       tambahTransaksi();
    }//GEN-LAST:event_btnC3ActionPerformed

    private void btnU3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnU3ActionPerformed
        // TODO add your handling code here:
        updateTransaksi();
    }//GEN-LAST:event_btnU3ActionPerformed

    private void btnD3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD3ActionPerformed
        // TODO add your handling code here:
        hapusTransaksi();
    }//GEN-LAST:event_btnD3ActionPerformed

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel16MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManajemenKP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManajemenKP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManajemenKP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManajemenKP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManajemenKP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntU1;
    private javax.swing.JButton btnC1;
    private javax.swing.JButton btnC2;
    private javax.swing.JButton btnC3;
    private javax.swing.JButton btnD1;
    private javax.swing.JButton btnD2;
    private javax.swing.JButton btnD3;
    private javax.swing.JButton btnU2;
    private javax.swing.JButton btnU3;
    private javax.swing.JComboBox<String> cbKaryawan;
    private javax.swing.JComboBox<String> cbProyek;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel p_karyawan;
    private javax.swing.JTable tblKaryawan;
    private javax.swing.JTable tblProyek;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField tf_departemen;
    private javax.swing.JTextField tf_durasiPengerjaan;
    private javax.swing.JTextField tf_idKaryawan;
    private javax.swing.JTextField tf_idProyek;
    private javax.swing.JTextField tf_jabatan;
    private javax.swing.JTextField tf_nama;
    private javax.swing.JTextField tf_namaProyek;
    private javax.swing.JTextField tf_peran;
    // End of variables declaration//GEN-END:variables

}




