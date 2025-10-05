package TA;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Koneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FDataMotor extends javax.swing.JFrame {
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;

    private void aktif() {
        id.setEnabled(true);
        tMerk.setEnabled(true);
        tHarga.setEnabled(true);
        tProduksi.setEnabled(true);
        tKmesin.setEnabled(true);
        tKbbm.setEnabled(true);
        tTfisik.setEnabled(true);
        id.requestFocus();
    }

    protected void kosong() {
        id.setText("");
        tMerk.setText("");
        tHarga.setText("");
        tProduksi.setText("");
        tKmesin.setText("");
        tKbbm.setText("");
        tTfisik.setText("");
    }

    protected void datatable() {
    Object[] Baris = {"ID Motor", "Merk", "Harga", "Tahun Produksi", "Kondisi Mesin", "Konsumsi BBM", "Tampilan Fisik"};
    tabmode = new DefaultTableModel(null, Baris);
    TabelMotor.setModel(tabmode);
    String sql = "SELECT * FROM motor ORDER BY id_motor"; // Urutkan berdasarkan id_motor
    int counter = 1; // Counter untuk nomor urut
    try {
        java.sql.Statement stat = conn.createStatement();
        ResultSet hasil = stat.executeQuery(sql);
        while (hasil.next()) {
            String id_motor = String.valueOf(counter); // Gunakan counter sebagai ID Motor
            String merk = hasil.getString("merk");
            String harga = hasil.getString("harga");
            String tahun_produksi = hasil.getString("tahun_produksi");
            String kondisi_mesin = hasil.getString("kondisi_mesin");
            String konsumsi_bbm = hasil.getString("konsumsi_bbm");
            String tampilan_fisik = hasil.getString("tampilan_fisik");
            String[] data = {id_motor, merk, harga, tahun_produksi, kondisi_mesin, konsumsi_bbm, tampilan_fisik};
            tabmode.addRow(data);
            counter++; // Tambah counter untuk nomor berikutnya
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
    }
}

    public void simpan() {
        String sql = "INSERT INTO motor (id_motor, merk, harga, tahun_produksi, kondisi_mesin, konsumsi_bbm, tampilan_fisik) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, id.getText());
            stat.setString(2, tMerk.getText());
            stat.setString(3, tHarga.getText());
            stat.setString(4, tProduksi.getText());
            stat.setString(5, tKmesin.getText());
            stat.setString(6, tKbbm.getText());
            stat.setString(7, tTfisik.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            kosong();
            id.requestFocus();
            datatable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Tersimpan: " + e.getMessage());
        }
    }

    public void hapus() {
        int ok = JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == 0) {
            String sql = "DELETE FROM motor WHERE id_motor = ?";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, id.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                kosong();
                id.requestFocus();
                datatable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus: " + e.getMessage());
            }
        }
    }

    public void ubah() {
        try {
            String sql = "UPDATE motor SET merk = ?, harga = ?, tahun_produksi = ?, kondisi_mesin = ?, konsumsi_bbm = ?, tampilan_fisik = ? WHERE id_motor = ?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, tMerk.getText());
            stat.setString(2,tHarga.getText());
            stat.setString(3, tProduksi.getText());
            stat.setString(4, tKmesin.getText());
            stat.setString(5, tKbbm.getText());
            stat.setString(6, tTfisik.getText());
            stat.setString(7, id.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosong();
            id.requestFocus();
            datatable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah: " + e.getMessage());
        }
    }

    public void motor() {
        int bar = TabelMotor.getSelectedRow();
        if (bar >= 0) {
            String id_motor = tabmode.getValueAt(bar, 0).toString();
            String merk = tabmode.getValueAt(bar, 1).toString();
            String harga = tabmode.getValueAt(bar, 2).toString();
            String tahun_produksi = tabmode.getValueAt(bar, 3).toString();
            String kondisi_mesin = tabmode.getValueAt(bar, 4).toString();
            String konsumsi_bbm = tabmode.getValueAt(bar, 5).toString();
            String tampilan_fisik = tabmode.getValueAt(bar, 6).toString();

            id.setText(id_motor);
            tMerk.setText(merk);
            tHarga.setText(harga);
            tProduksi.setText(tahun_produksi);
            tKmesin.setText(kondisi_mesin);
            tKbbm.setText(konsumsi_bbm);
            tTfisik.setText(tampilan_fisik);
        }
    }

    public FDataMotor() {
        initComponents();
        datatable();
        aktif();
        setLocationRelativeTo(null);
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        tMerk = new javax.swing.JTextField();
        tHarga = new javax.swing.JTextField();
        tProduksi = new javax.swing.JTextField();
        tKmesin = new javax.swing.JTextField();
        tKbbm = new javax.swing.JTextField();
        tTfisik = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btSave = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        btBack = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelMotor = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1190, 600));
        setMinimumSize(new java.awt.Dimension(1190, 600));
        setPreferredSize(new java.awt.Dimension(1190, 600));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setDoubleBuffered(false);
        jPanel1.setMaximumSize(new java.awt.Dimension(1190, 600));
        jPanel1.setMinimumSize(new java.awt.Dimension(1190, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(1190, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Sitka Subheading", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Motor");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Sitka Subheading", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID Motor");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 198, -1));

        jLabel11.setFont(new java.awt.Font("Sitka Subheading", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Merk Motor");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 198, -1));

        jLabel3.setFont(new java.awt.Font("Sitka Subheading", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tahun Produksi");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 198, -1));

        jLabel4.setFont(new java.awt.Font("Sitka Subheading", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kondisi Mesin");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 198, -1));

        jLabel5.setFont(new java.awt.Font("Sitka Subheading", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Harga");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 198, 28));

        jLabel8.setFont(new java.awt.Font("Sitka Subheading", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Konsumsi BBM");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jLabel9.setFont(new java.awt.Font("Sitka Subheading", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Tampilan Fisik");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 198, -1));

        id.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jPanel1.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 200, -1));

        tMerk.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jPanel1.add(tMerk, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 200, -1));
        jPanel1.add(tHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 200, -1));

        tProduksi.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jPanel1.add(tProduksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 200, -1));

        tKmesin.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jPanel1.add(tKmesin, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 200, -1));

        tKbbm.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jPanel1.add(tKbbm, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 200, -1));

        tTfisik.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jPanel1.add(tTfisik, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, 200, -1));

        jLabel10.setFont(new java.awt.Font("Sitka Subheading", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Data Motor");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 30, -1, -1));

        btSave.setFont(new java.awt.Font("Sitka Subheading", 1, 14)); // NOI18N
        btSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Save.png"))); // NOI18N
        btSave.setText("Simpan");
        btSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 110, -1));

        btEdit.setFont(new java.awt.Font("Sitka Subheading", 1, 15)); // NOI18N
        btEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Update.png"))); // NOI18N
        btEdit.setText("Ubah");
        btEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });
        jPanel1.add(btEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 400, -1, -1));

        btDelete.setFont(new java.awt.Font("Sitka Subheading", 1, 15)); // NOI18N
        btDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Delete.png"))); // NOI18N
        btDelete.setText("Hapus");
        btDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 400, -1, -1));

        btBack.setFont(new java.awt.Font("Sitka Subheading", 1, 15)); // NOI18N
        btBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Back.png"))); // NOI18N
        btBack.setText("Kembali");
        btBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btBackMouseClicked(evt);
            }
        });
        jPanel1.add(btBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 400, -1, -1));

        TabelMotor.setBackground(new java.awt.Color(204, 204, 204));
        TabelMotor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabelMotor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelMotorMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TabelMotor);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 670, 320));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Motor 2.png"))); // NOI18N
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 668, -1, -1));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Motor 2.png"))); // NOI18N
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, -1, -1));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Motor 2.png"))); // NOI18N
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 450, -1, -1));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Motor 2.png"))); // NOI18N
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 450, -1, -1));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Motor 2.png"))); // NOI18N
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 450, -1, -1));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Motor 2.png"))); // NOI18N
        jPanel1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 450, -1, -1));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Motor 2.png"))); // NOI18N
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 450, -1, -1));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Motor 2.png"))); // NOI18N
        jPanel1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 450, -1, -1));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Motor 2.png"))); // NOI18N
        jPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 450, -1, -1));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Motor 2.png"))); // NOI18N
        jPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 450, -1, -1));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Motor 2.png"))); // NOI18N
        jPanel1.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 450, -1, -1));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Motor 2.png"))); // NOI18N
        jPanel1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 450, -1, -1));

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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btBackMouseClicked
        FHalamanUtama menu = new FHalamanUtama();
        menu.setVisible(true);
        menu.pack();
        menu.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_btBackMouseClicked

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
       simpan();
    }//GEN-LAST:event_btSaveActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        hapus();
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
        ubah();
    }//GEN-LAST:event_btEditActionPerformed

    private void TabelMotorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelMotorMouseClicked
        motor();
    }//GEN-LAST:event_TabelMotorMouseClicked

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
            java.util.logging.Logger.getLogger(FDataMotor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FDataMotor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FDataMotor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FDataMotor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FDataMotor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelMotor;
    private javax.swing.JButton btBack;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btSave;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField tHarga;
    private javax.swing.JTextField tKbbm;
    private javax.swing.JTextField tKmesin;
    private javax.swing.JTextField tMerk;
    private javax.swing.JTextField tProduksi;
    private javax.swing.JTextField tTfisik;
    // End of variables declaration//GEN-END:variables
}
