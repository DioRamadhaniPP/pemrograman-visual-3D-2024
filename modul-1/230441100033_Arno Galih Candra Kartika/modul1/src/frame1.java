
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
public class frame1 extends javax.swing.JFrame {

    /**
     * Creates new form frame1
     */
    String NamaLengkap, Angkatan, Prodi, NoTelepon, Jeniskelamin, Divisi, Alasan;
    public frame1() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        arno = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        rdbtnlaki = new javax.swing.JRadioButton();
        rdbtnperempuan = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        pilihdivisi = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        chkvalidasi = new javax.swing.JCheckBox();
        btnreset = new javax.swing.JButton();
        btnsimpan = new javax.swing.JButton();
        btnkeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setToolTipText("");

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 51, 204), new java.awt.Color(0, 153, 153), null, null));

        jPanel3.setBackground(new java.awt.Color(0, 255, 255));

        jLabel1.setFont(new java.awt.Font("Swis721 Blk BT", 0, 24)); // NOI18N
        jLabel1.setText("PENDAFTARAN");

        jLabel2.setFont(new java.awt.Font("Swis721 Blk BT", 0, 36)); // NOI18N
        jLabel2.setText("UKM-FT BLUE MURDER");

        jLabel11.setFont(new java.awt.Font("Swis721 BlkCn BT", 0, 14)); // NOI18N
        jLabel11.setText("Solidaritas Loyalitas Totalitas Tanpa Batas");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/WhatsApp_Image_2024-09-01_at_14.43.38_abe642e1-removebg-preview.png"))); // NOI18N
        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(69, 69, 69))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jLabel11))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N
        jLabel4.setText("Nama Lengkap :");

        jTextField1.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N
        jLabel5.setText("Angkatan :");

        jTextField2.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N
        jLabel6.setText("Prodi :");

        jTextField3.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N
        jLabel7.setText("No Telepon :");

        jTextField4.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N
        jLabel8.setText("Jenis Kelamin :");

        arno.add(rdbtnlaki);
        rdbtnlaki.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N
        rdbtnlaki.setText("Laki - laki");

        arno.add(rdbtnperempuan);
        rdbtnperempuan.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N
        rdbtnperempuan.setText("Perempuan");

        jLabel9.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N
        jLabel9.setText("Divisi :");

        pilihdivisi.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N
        pilihdivisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "musik", "seni rupa", "tari", "teater" }));
        pilihdivisi.setSelectedIndex(-1);

        jLabel10.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N
        jLabel10.setText("Alasan memilih divisi tersebut :");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        chkvalidasi.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N
        chkvalidasi.setText("Data yang saya masukkan sudah benar");

        btnreset.setBackground(new java.awt.Color(102, 102, 102));
        btnreset.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N
        btnreset.setForeground(new java.awt.Color(255, 255, 255));
        btnreset.setText("Reset");
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });

        btnsimpan.setBackground(new java.awt.Color(102, 102, 102));
        btnsimpan.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N
        btnsimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnkeluar.setBackground(new java.awt.Color(102, 102, 102));
        btnkeluar.setFont(new java.awt.Font("TT Rounds Neue Trial", 0, 12)); // NOI18N
        btnkeluar.setForeground(new java.awt.Color(255, 255, 255));
        btnkeluar.setText("Keluar");
        btnkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnreset)
                                .addGap(39, 39, 39)
                                .addComponent(btnsimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                .addComponent(btnkeluar))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(rdbtnlaki, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdbtnperempuan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4)
                            .addComponent(pilihdivisi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(chkvalidasi)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbtnlaki)
                    .addComponent(rdbtnperempuan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pilihdivisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkvalidasi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnreset)
                    .addComponent(btnsimpan)
                    .addComponent(btnkeluar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
    
    jTextField1.setText("");
    jTextField2.setText("");
    jTextField3.setText("");
    jTextField4.setText("");
    jTextArea1.setText("");
        
        
    rdbtnlaki.setSelected(false);
    rdbtnperempuan.setSelected(false);
        
        
    pilihdivisi.setSelectedIndex(-1);
        
        
    chkvalidasi.setSelected(false);
        
    }//GEN-LAST:event_btnresetActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        
        
      if (!chkvalidasi.isSelected()) {
        JOptionPane.showMessageDialog(this,
                "isi semua sampai benar", "isi dengan benar", JOptionPane.ERROR_MESSAGE);
    } else {
        if (jTextField1.getText().isEmpty() || jTextField2.getText().isEmpty() || jTextField3.getText().isEmpty() ||
            jTextField4.getText().isEmpty() || jTextArea1.getText().isEmpty()){
            
            javax.swing.JOptionPane.showMessageDialog(this, 
                    "Penuhi semua input untuk melanjutkan!", 
                    "bagian kosong", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            
        } else if (!rdbtnlaki.isSelected() && !rdbtnperempuan.isSelected()) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                "Pilih jenis kelamin untuk melanjutkan!", 
                "bagian kosong", 
                javax.swing.JOptionPane.WARNING_MESSAGE);
  
        } else if (pilihdivisi.getSelectedIndex() == -1) { 
                javax.swing.JOptionPane.showMessageDialog(this, 
                "Pilih jurusan sebelum melanjutkan!", 
                "bagian kosong", 
                javax.swing.JOptionPane.WARNING_MESSAGE);
        } else {
            String NamaLengkap = jTextField1.getText();
            String Angkatan = jTextField2.getText();
            String Jeniskelamin = "";
            if (rdbtnlaki.isSelected()) {
                Jeniskelamin = "laki-laki";
            } else if (rdbtnperempuan.isSelected()) {
                Jeniskelamin = "perempuan";
            }
            String Prodi = jTextField3.getText();
            String  NoTelepon = jTextField4.getText();
            String Divisi = pilihdivisi.getSelectedItem().toString();
            String Alasan = jTextArea1.getText();
            
            frame2 b = new frame2(NamaLengkap, Angkatan, Jeniskelamin, Prodi, NoTelepon, Divisi, Alasan);
            b.setVisible(true);
            dispose();

        }
    }
    
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkeluarActionPerformed
    
    btnkeluar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Menampilkan dialog konfirmasi dengan opsi Ya dan Tidak
        int response = JOptionPane.showConfirmDialog(
                null,
                "Yakin ingin keluar?",
                "Konfirmasi Keluar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        
        if (response == JOptionPane.YES_OPTION) {
            
            System.exit(0);
        } else if (response == JOptionPane.NO_OPTION) {
            
        }
    }
});
        System.exit(0);
    }//GEN-LAST:event_btnkeluarActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frame1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup arno;
    private javax.swing.JButton btnkeluar;
    private javax.swing.JButton btnreset;
    private javax.swing.JButton btnsimpan;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox chkvalidasi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JComboBox<String> pilihdivisi;
    private javax.swing.JRadioButton rdbtnlaki;
    private javax.swing.JRadioButton rdbtnperempuan;
    // End of variables declaration//GEN-END:variables
}
