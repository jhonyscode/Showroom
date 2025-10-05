package TA;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Koneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;

public class FPenilaian extends javax.swing.JFrame {
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;

    protected void kosong() {
        idMotor.setText("");
        idPenilaian.setText("");
        tHarga.setText("");
        tProduksi.setText("");
        tKodemesin.setText("");
        tKonBBM.setText("");
        tTampilan.setText("");
    }

    protected void datatable() {
        Object[] Baris = {"ID Motor", "ID Penilaian", "Harga", "Tahun Produksi", "Kondisi Mesin", "Konsumsi BBM", "Kondisi Fisik"};
        tabmode = new DefaultTableModel(null, Baris);
        TabelPenilaian.setModel(tabmode);
        String sql = "SELECT * FROM penilaian";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String id_motor = hasil.getString("id_motor");
                String id_penilaian = hasil.getString("id_penilaian");
                String harga = hasil.getString("harga");
                String tahun_produksi = hasil.getString("tahun_produksi");
                String kondisi_mesin = hasil.getString("kondisi_mesin");
                String konsumsi_bbm = hasil.getString("konsumsi_bbm");
                String kondisi_fisik = hasil.getString("kondisi_fisik");
                String[] data = {id_motor, id_penilaian, harga, tahun_produksi, kondisi_mesin, konsumsi_bbm, kondisi_fisik};
                tabmode.addRow(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
        }
    }

    public void simpan() {
        String sql = "INSERT INTO penilaian (id_motor, id_penilaian, harga, tahun_produksi, kondisi_mesin, konsumsi_bbm, kondisi_fisik) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, idMotor.getText());
            stat.setString(2, idPenilaian.getText());
            stat.setString(3, tHarga.getText());
            stat.setString(4, tProduksi.getText());
            stat.setString(5, tKodemesin.getText());
            stat.setString(6, tKonBBM.getText());
            stat.setString(7, tTampilan.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            kosong();
            idMotor.requestFocus();
            datatable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Tersimpan: " + e.getMessage());
        }
    }

    public void hapus() {
        int ok = JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == 0) {
            String sql = "DELETE FROM penilaian WHERE id_penilaian = ?";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, idPenilaian.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                kosong();
                idMotor.requestFocus();
                datatable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus: " + e.getMessage());
            }
        }
    }

    public void ubah() {
        try {
            String sql = "UPDATE penilaian SET id_motor = ?, harga = ?, tahun_produksi = ?, kondisi_mesin = ?, konsumsi_bbm = ?, kondisi_fisik = ? WHERE id_penilaian = ?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, idMotor.getText());
            stat.setString(2, tHarga.getText());
            stat.setString(3, tProduksi.getText());
            stat.setString(4, tKodemesin.getText());
            stat.setString(5, tKonBBM.getText());
            stat.setString(6, tTampilan.getText());
            stat.setString(7, idPenilaian.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosong();
            idMotor.requestFocus();
            datatable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah: " + e.getMessage());
        }
    }

    public void cari() {
        String sql = "select * from motor where id_motor = '"+idMotor.getText()+"'";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String mrk = hasil.getString("merk");
                merkMotor.setText(mrk);
                merkMotor.setEnabled(false);
            }
        } catch (Exception e){}
    }

    public void penilaian() {
        int bar = TabelPenilaian.getSelectedRow();
        if (bar >= 0) {
            String id_motor = tabmode.getValueAt(bar, 0).toString();
            String id_penilaian = tabmode.getValueAt(bar, 1).toString();
            String harga = tabmode.getValueAt(bar, 2).toString();
            String tahun_produksi = tabmode.getValueAt(bar, 3).toString();
            String kondisi_mesin = tabmode.getValueAt(bar, 4).toString();
            String konsumsi_bbm = tabmode.getValueAt(bar, 5).toString();
            String kondisi_fisik = tabmode.getValueAt(bar, 6).toString();

            idMotor.setText(id_motor);
            idPenilaian.setText(id_penilaian);
            tHarga.setText(harga);
            tProduksi.setText(tahun_produksi);
            tKodemesin.setText(kondisi_mesin);
            tKonBBM.setText(konsumsi_bbm);
            tTampilan.setText(kondisi_fisik);

            // Update combo boxes based on text field values
            updateComboBoxSelections(harga, tahun_produksi, kondisi_mesin, konsumsi_bbm, kondisi_fisik);
        }
    }

    private void updateComboBoxSelections(String harga, String tahun_produksi, String kondisi_mesin, String konsumsi_bbm, String kondisi_fisik) {
        // Update cbb_harga
        switch (harga) {
            case "5": cbb_harga.setSelectedIndex(0); break;
            case "4": cbb_harga.setSelectedIndex(1); break;
            case "3": cbb_harga.setSelectedIndex(2); break;
            case "2": cbb_harga.setSelectedIndex(3); break;
            case "1": cbb_harga.setSelectedIndex(4); break;
            default: cbb_harga.setSelectedIndex(-1); break;
        }

        // Update cbb_tahun
        switch (tahun_produksi) {
            case "5": cbb_tahun.setSelectedIndex(0); break;
            case "4": cbb_tahun.setSelectedIndex(1); break;
            case "3": cbb_tahun.setSelectedIndex(2); break;
            case "2": cbb_tahun.setSelectedIndex(3); break;
            case "1": cbb_tahun.setSelectedIndex(4); break;
            default: cbb_tahun.setSelectedIndex(-1); break;
        }

        // Update cbb_mesin
        switch (kondisi_mesin) {
            case "5": cbb_mesin.setSelectedIndex(0); break;
            case "4": cbb_mesin.setSelectedIndex(1); break;
            case "3": cbb_mesin.setSelectedIndex(2); break;
            case "2": cbb_mesin.setSelectedIndex(3); break;
            case "1": cbb_mesin.setSelectedIndex(4); break;
            default: cbb_mesin.setSelectedIndex(-1); break;
        }

        // Update cbb_bbm
        switch (konsumsi_bbm) {
            case "5": cbb_bbm.setSelectedIndex(0); break;
            case "4": cbb_bbm.setSelectedIndex(1); break;
            case "3": cbb_bbm.setSelectedIndex(2); break;
            case "2": cbb_bbm.setSelectedIndex(3); break;
            case "1": cbb_bbm.setSelectedIndex(4); break;
            default: cbb_bbm.setSelectedIndex(-1); break;
        }

        // Update cbb_tampilan
        switch (kondisi_fisik) {
            case "5": cbb_tampilan.setSelectedIndex(0); break;
            case "4": cbb_tampilan.setSelectedIndex(1); break;
            case "3": cbb_tampilan.setSelectedIndex(2); break;
            case "2": cbb_tampilan.setSelectedIndex(3); break;
            case "1": cbb_tampilan.setSelectedIndex(4); break;
            default: cbb_tampilan.setSelectedIndex(-1); break;
        }
    }

    public FPenilaian() {
        initComponents();
        datatable();
        setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        idMotor = new javax.swing.JTextField();
        cari = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        merkMotor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        idPenilaian = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tHarga = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tProduksi = new javax.swing.JTextField();
        cbb_tahun = new javax.swing.JComboBox<>();
        cbb_harga = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        tKodemesin = new javax.swing.JTextField();
        cbb_mesin = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        tKonBBM = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tTampilan = new javax.swing.JTextField();
        cbb_tampilan = new javax.swing.JComboBox<>();
        cbb_bbm = new javax.swing.JComboBox<>();
        btEdit = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        btBack = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelPenilaian = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1190, 600));
        setMinimumSize(new java.awt.Dimension(1190, 600));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setMaximumSize(new java.awt.Dimension(1280, 780));
        jPanel2.setMinimumSize(new java.awt.Dimension(110, 25));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Sitka Subheading", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Penilaian");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 1280, -1));

        jLabel10.setFont(new java.awt.Font("Sitka Subheading", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Id Motor");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 230, 20));
        jPanel2.add(idMotor, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 300, -1));

        cari.setFont(new java.awt.Font("Sitka Subheading", 1, 12)); // NOI18N
        cari.setText("Cari");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        jPanel2.add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, -1, -1));

        jLabel6.setFont(new java.awt.Font("Sitka Subheading", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Merk Motor");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 230, 20));
        jPanel2.add(merkMotor, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 300, -1));

        jLabel2.setFont(new java.awt.Font("Sitka Subheading", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Id Penilaian");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 230, 20));
        jPanel2.add(idPenilaian, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 300, -1));

        jLabel3.setFont(new java.awt.Font("Sitka Subheading", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Harga");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 230, 20));
        jPanel2.add(tHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 300, -1));

        jLabel8.setFont(new java.awt.Font("Sitka Subheading", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tahun Produksi");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 230, 20));
        jPanel2.add(tProduksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 300, -1));

        cbb_tahun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2022 - 2020", "2019 - 2015", "2014 - 2006", "≤ 2005" }));
        cbb_tahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_tahunActionPerformed(evt);
            }
        });
        jPanel2.add(cbb_tahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, 177, -1));

        cbb_harga.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "< 5.000.000", "6.000.000 – 10.000.000", "11.000.000 – 19.000.000", "20.000.000 – 25.000.000", "≥ 25.000.000" }));
        cbb_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_hargaActionPerformed(evt);
            }
        });
        jPanel2.add(cbb_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 177, -1));

        jLabel4.setFont(new java.awt.Font("Sitka Subheading", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kondisi Mesin");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 230, 20));
        jPanel2.add(tKodemesin, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, 300, -1));

        cbb_mesin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sangat Baik", "Baik", "Cukup", "Kurang", "Buruk" }));
        cbb_mesin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_mesinActionPerformed(evt);
            }
        });
        jPanel2.add(cbb_mesin, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 190, 177, -1));

        jLabel9.setFont(new java.awt.Font("Sitka Subheading", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Konsumsi BBM");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 230, 20));
        jPanel2.add(tKonBBM, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 300, -1));

        jLabel5.setFont(new java.awt.Font("Sitka Subheading", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tampilan/Kondisi Fisik");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 230, 20));
        jPanel2.add(tTampilan, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 300, -1));

        cbb_tampilan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mulus", "Lecet Ringan", "Lecet Sedang", "Banyak Goresan", "Cat rusak, body penyok atau tidak lengkap" }));
        cbb_tampilan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_tampilanActionPerformed(evt);
            }
        });
        jPanel2.add(cbb_tampilan, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 250, 177, -1));

        cbb_bbm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "≥ 50 (Sangat Irit)", "45 – 49 (Irit)", "40 – 44 (Cukup Irit)", "35 – 39 (Boros)", "< 35 (Sangat Boros)" }));
        cbb_bbm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_bbmActionPerformed(evt);
            }
        });
        jPanel2.add(cbb_bbm, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 220, -1, -1));

        btEdit.setFont(new java.awt.Font("Sitka Subheading", 1, 12)); // NOI18N
        btEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Update.png"))); // NOI18N
        btEdit.setText("Ubah");
        btEdit.setMaximumSize(new java.awt.Dimension(98, 24));
        btEdit.setMinimumSize(new java.awt.Dimension(98, 24));
        btEdit.setPreferredSize(new java.awt.Dimension(98, 24));
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });
        jPanel2.add(btEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, -1, 30));

        btSave.setFont(new java.awt.Font("Sitka Subheading", 1, 12)); // NOI18N
        btSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Save.png"))); // NOI18N
        btSave.setText("Simpan");
        btSave.setMaximumSize(new java.awt.Dimension(98, 24));
        btSave.setMinimumSize(new java.awt.Dimension(98, 24));
        btSave.setPreferredSize(new java.awt.Dimension(98, 24));
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });
        jPanel2.add(btSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, -1, 30));

        btDelete.setFont(new java.awt.Font("Sitka Subheading", 1, 12)); // NOI18N
        btDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Delete.png"))); // NOI18N
        btDelete.setText("Hapus");
        btDelete.setMaximumSize(new java.awt.Dimension(98, 24));
        btDelete.setMinimumSize(new java.awt.Dimension(98, 24));
        btDelete.setPreferredSize(new java.awt.Dimension(98, 24));
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(btDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 280, -1, 30));

        btBack.setFont(new java.awt.Font("Sitka Subheading", 1, 12)); // NOI18N
        btBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Back.png"))); // NOI18N
        btBack.setText("Kembali");
        btBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btBackMouseClicked(evt);
            }
        });
        jPanel2.add(btBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 280, -1, 30));

        jPanel4.setBackground(new java.awt.Color(28, 28, 28));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Sitka Subheading", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Tabel Penilaian");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(540, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(472, 472, 472))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 1190, -1));

        TabelPenilaian.setBackground(new java.awt.Color(204, 204, 204));
        TabelPenilaian.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelPenilaian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelPenilaianMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelPenilaian);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 370, 1050, 220));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/icons8-evaluation-100.png"))); // NOI18N
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1190, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
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

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
       ubah();
    }//GEN-LAST:event_btEditActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        hapus();
    }//GEN-LAST:event_btDeleteActionPerformed

    private void TabelPenilaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelPenilaianMouseClicked
         penilaian();
    }//GEN-LAST:event_TabelPenilaianMouseClicked

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
       cari();
    }//GEN-LAST:event_cariActionPerformed

    private void cbb_tampilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_tampilanActionPerformed
        String selected = (String) cbb_tampilan.getSelectedItem();
        if (selected != null) {
            switch (selected) {
                case "Mulus":
                    tTampilan.setText("5");
                    break;
                case "Lecet Ringan":
                    tTampilan.setText("4");
                    break;
                case "Lecet Sedang":
                    tTampilan.setText("3");
                    break;
                case "Banyak Goresan":
                    tTampilan.setText("2");
                    break;
                case "Cat rusak, body penyok atau tidak lengkap":
                    tTampilan.setText("1");
                    break;
                default:
                    tTampilan.setText("");
                    break;
            }
        }
    }//GEN-LAST:event_cbb_tampilanActionPerformed

    private void cbb_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_hargaActionPerformed
       String selected = (String) cbb_harga.getSelectedItem();
        if (selected != null) {
            switch (selected) {
                case "< 5.000.000":
                    tHarga.setText("5");
                    break;
                case "6.000.000 – 10.000.000":
                    tHarga.setText("4");
                    break;
                case "11.000.000 – 19.000.000":
                    tHarga.setText("3");
                    break;
                case "20.000.000 – 25.000.000":
                    tHarga.setText("2");
                    break;
                case "≥ 25.000.000":
                    tHarga.setText("1");
                    break;
                default:
                    tHarga.setText("");
                    break;
            }
        }
    }//GEN-LAST:event_cbb_hargaActionPerformed

    private void cbb_tahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_tahunActionPerformed
        String selected = (String) cbb_tahun.getSelectedItem();
        if (selected != null) {
            switch (selected) {
                case "2023":
                    tProduksi.setText("5");
                    break;
                case "2022 - 2020":
                    tProduksi.setText("4");
                    break;
                case "2019 - 2015":
                    tProduksi.setText("3");
                    break;
                case "2014 - 2006":
                    tProduksi.setText("2");
                    break;
                case "≤ 2005":
                    tProduksi.setText("1");
                    break;
                default:
                    tProduksi.setText("");
                    break;
            }
    }//GEN-LAST:event_cbb_tahunActionPerformed
    }
    
    private void cbb_mesinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_mesinActionPerformed
       String selected = (String) cbb_mesin.getSelectedItem();
        if (selected != null) {
            switch (selected) {
                case "Sangat Baik":
                    tKodemesin.setText("5");
                    break;
                case "Baik":
                    tKodemesin.setText("4");
                    break;
                case "Cukup":
                    tKodemesin.setText("3");
                    break;
                case "Kurang":
                    tKodemesin.setText("2");
                    break;
                case "Buruk":
                    tKodemesin.setText("1");
                    break;
                default:
                    tKodemesin.setText("");
                    break;
            }
        }
    }//GEN-LAST:event_cbb_mesinActionPerformed

    private void cbb_bbmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_bbmActionPerformed
       String selected = (String) cbb_bbm.getSelectedItem();
        if (selected != null) {
            switch (selected) {
                case "≥ 50 (Sangat Irit)":
                    tKonBBM.setText("5");
                    break;
                case "45 – 49 (Irit)":
                    tKonBBM.setText("4");
                    break;
                case "40 – 44 (Cukup Irit)":
                    tKonBBM.setText("3");
                    break;
                case "35 – 39 (Boros)":
                    tKonBBM.setText("2");
                    break;
                case "< 35 (Sangat Boros)":
                    tKonBBM.setText("1");
                    break;
                default:
                    tKonBBM.setText("");
                    break;
            }
        }
    }//GEN-LAST:event_cbb_bbmActionPerformed

    
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
            java.util.logging.Logger.getLogger(FPenilaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FPenilaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FPenilaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FPenilaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FPenilaian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelPenilaian;
    private javax.swing.JButton btBack;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btSave;
    private javax.swing.JButton cari;
    private javax.swing.JComboBox<String> cbb_bbm;
    private javax.swing.JComboBox<String> cbb_harga;
    private javax.swing.JComboBox<String> cbb_mesin;
    private javax.swing.JComboBox<String> cbb_tahun;
    private javax.swing.JComboBox<String> cbb_tampilan;
    private javax.swing.JTextField idMotor;
    private javax.swing.JTextField idPenilaian;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField merkMotor;
    private javax.swing.JTextField tHarga;
    private javax.swing.JTextField tKodemesin;
    private javax.swing.JTextField tKonBBM;
    private javax.swing.JTextField tProduksi;
    private javax.swing.JTextField tTampilan;
    // End of variables declaration//GEN-END:variables
}
