
package TA;

import Koneksi.koneksi;
import TA.FDataMotor;
import TA.FDataKriteria;
import TA.FPenilaian;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;
import javax.swing.text.html.parser.DTDConstants;

public class FProsesData extends javax.swing.JFrame {
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    private DefaultTableModel tabmodeNormalisasi;
    private DefaultTableModel tabmodePeringkat;

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

    protected void tabelmodelnormalisasi() {
        Object[] Baris = {"ID Motor", "Harga", "Tahun Produksi", "Kondisi Mesin", "Konsumsi BBM", "Kondisi Fisik"};
        tabmodeNormalisasi = new DefaultTableModel(null, Baris);
        TabelNormalisasi.setModel(tabmodeNormalisasi);
    }

    protected void tabelmodelperingkat() {
        Object[] Baris = {"ID Motor", "Merk", "Nilai SAW", "Rank"};
        tabmodePeringkat = new DefaultTableModel(null, Baris);
        TabelPeringkat.setModel(tabmodePeringkat);
    }

    public void normalisasi() {
        LinkedList<Float> max = new LinkedList<>();
        LinkedList<Float> min = new LinkedList<>();
        LinkedList<String> jenisKriteria = new LinkedList<>();
        try {
            // Clear existing data in normalisasi table
            String clearSql = "DELETE FROM normalisasi";
            conn.createStatement().executeUpdate(clearSql);

            // Ambil jenis kriteria dari tabel kriteria
            ResultSet resKriteria = conn.createStatement().executeQuery("SELECT jenis FROM kriteria ORDER BY id");
            while (resKriteria.next()) {
                jenisKriteria.add(resKriteria.getString("jenis"));
            }

            // Ambil nilai max dan min dari penilaian
            ResultSet res = conn.createStatement().executeQuery(
                "SELECT MAX(harga), MAX(tahun_produksi), MAX(kondisi_mesin), MAX(konsumsi_bbm), MAX(kondisi_fisik), " +
                "MIN(harga), MIN(tahun_produksi), MIN(kondisi_mesin), MIN(konsumsi_bbm), MIN(kondisi_fisik) " +
                "FROM penilaian"
            );
            if (res.next()) {
                max.add(res.getFloat(1)); // max harga
                max.add(res.getFloat(2)); // max tahun_produksi
                max.add(res.getFloat(3)); // max kondisi_mesin
                max.add(res.getFloat(4)); // max konsumsi_bbm
                max.add(res.getFloat(5)); // max kondisi_fisik
                min.add(res.getFloat(6)); // min harga
                min.add(res.getFloat(7)); // min tahun_produksi
                min.add(res.getFloat(8)); // min kondisi_mesin
                min.add(res.getFloat(9)); // min konsumsi_bbm
                min.add(res.getFloat(10)); // min kondisi_fisik
            }

            tabelmodelnormalisasi();
            String insertSql = "INSERT INTO normalisasi (id_motor, id_penilaian, harga, tahun_produksi, kondisi_mesin, konsumsi_bbm, kondisi_fisik) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            ResultSet res2 = conn.createStatement().executeQuery("SELECT * FROM penilaian");
            while (res2.next()) {
                String id_motor = res2.getString("id_motor");
                String id_penilaian = res2.getString("id_penilaian");
                float harga = res2.getFloat("harga");
                float tahun_produksi = res2.getFloat("tahun_produksi");
                float kondisi_mesin = res2.getFloat("kondisi_mesin");
                float konsumsi_bbm = res2.getFloat("konsumsi_bbm");
                float kondisi_fisik = res2.getFloat("kondisi_fisik");

                float normHarga = jenisKriteria.get(0).equals("Cost") ? min.get(0) / harga : harga / max.get(0);
                float normTahun = jenisKriteria.get(1).equals("Cost") ? min.get(1) / tahun_produksi : tahun_produksi / max.get(1);
                float normMesin = jenisKriteria.get(2).equals("Cost") ? min.get(2) / kondisi_mesin : kondisi_mesin / max.get(2);
                float normKonsumsi = jenisKriteria.get(3).equals("Cost") ? min.get(3) / konsumsi_bbm : konsumsi_bbm / max.get(3);
                float normFisik = jenisKriteria.get(4).equals("Cost") ? min.get(4) / kondisi_fisik : kondisi_fisik / max.get(4);

                // Add to table model
                tabmodeNormalisasi.addRow(new Object[]{
                    id_motor,
                    String.format("%.2f", normHarga),
                    String.format("%.2f", normTahun),
                    String.format("%.2f", normMesin),
                    String.format("%.2f", normKonsumsi),
                    String.format("%.2f", normFisik)
                });

                // Insert into normalisasi table
                pstmt.setString(1, id_motor);
                pstmt.setString(2, id_penilaian);
                pstmt.setFloat(3, normHarga);
                pstmt.setFloat(4, normTahun);
                pstmt.setFloat(5, normMesin);
                pstmt.setFloat(6, normKonsumsi);
                pstmt.setFloat(7, normFisik);
                pstmt.executeUpdate();
            }
            pstmt.close();
            JOptionPane.showMessageDialog(this, "Data Normalisasi Berhasil Tersimpan!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Normalisasi Error: " + ex.getMessage());
        }
    }

    public void saw() {
//        try {
//            LinkedList<Float> bobot = new LinkedList<>();
//            LinkedList<String> jenisKriteria = new LinkedList<>();
//            LinkedList<Float> max = new LinkedList<>();
//            LinkedList<Float> min = new LinkedList<>();
//            LinkedList<Object[]> scores = new LinkedList<>();
//
//            // Clear existing data in peringkat table
//            String clearSql = "DELETE FROM peringkat";
//            conn.createStatement().executeUpdate(clearSql);
//
//            // Ambil bobot dan jenis kriteria dari tabel kriteria
//            ResultSet resKriteria = conn.createStatement().executeQuery("SELECT bobot, jenis FROM kriteria ORDER BY id");
//            while (resKriteria.next()) {
//                bobot.add(resKriteria.getFloat("bobot"));
//                jenisKriteria.add(resKriteria.getString("jenis"));
//            }
//
//            // Ambil nilai max dan min dari penilaian
//            ResultSet maxMinRes = conn.createStatement().executeQuery(
//                "SELECT MAX(harga), MAX(tahun_produksi), MAX(kondisi_mesin), MAX(konsumsi_bbm), MAX(kondisi_fisik), " +
//                "MIN(harga), MIN(tahun_produksi), MIN(kondisi_mesin), MIN(konsumsi_bbm), MIN(kondisi_fisik) " +
//                "FROM penilaian"
//            );
//            if (maxMinRes.next()) {
//                max.add(maxMinRes.getFloat(1)); // max harga
//                max.add(maxMinRes.getFloat(2)); // max tahun_produksi
//                max.add(maxMinRes.getFloat(3)); // max kondisi_mesin
//                max.add(maxMinRes.getFloat(4)); // max konsumsi_bbm
//                max.add(maxMinRes.getFloat(5)); // max kondisi_fisik
//                min.add(maxMinRes.getFloat(6)); // min harga
//                min.add(maxMinRes.getFloat(7)); // min tahun_produksi
//                min.add(maxMinRes.getFloat(8)); // min kondisi_mesin
//                min.add(maxMinRes.getFloat(9)); // min konsumsi_bbm
//                min.add(maxMinRes.getFloat(10)); // min kondisi_fisik
//            }
//
//            // Ambil data penilaian
//            ResultSet res = conn.createStatement().executeQuery(
//                "SELECT p.id_motor, p.harga, p.tahun_produksi, p.kondisi_mesin, p.konsumsi_bbm, p.kondisi_fisik, m.merk " +
//                "FROM penilaian p LEFT JOIN motor m ON p.id_motor = m.id_motor"
//            );
//            while (res.next()) {
//                String id_motor = res.getString("id_motor");
//                String merk = res.getString("merk");
//                float harga = res.getFloat("harga");
//                float tahun_produksi = res.getFloat("tahun_produksi");
//                float kondisi_mesin = res.getFloat("kondisi_mesin");
//                float konsumsi_bbm = res.getFloat("konsumsi_bbm");
//                float kondisi_fisik = res.getFloat("kondisi_fisik");
//
//                // Normalisasi
//                float normHarga = jenisKriteria.get(0).equals("Cost") ? min.get(0) / harga : harga / max.get(0);
//                float normTahun = jenisKriteria.get(1).equals("Cost") ? min.get(1) / tahun_produksi : tahun_produksi / max.get(1);
//                float normMesin = jenisKriteria.get(2).equals("Cost") ? min.get(2) / kondisi_mesin : kondisi_mesin / max.get(2);
//                float normKonsumsi = jenisKriteria.get(3).equals("Cost") ? min.get(3) / konsumsi_bbm : konsumsi_bbm / max.get(3);
//                float normFisik = jenisKriteria.get(4).equals("Cost") ? min.get(4) / kondisi_fisik : kondisi_fisik / max.get(4);
//
//                // Hitung skor SAW
//                double sawScore = (normHarga * bobot.get(0)) +
//                                 (normTahun * bobot.get(1)) +
//                                 (normMesin * bobot.get(2)) +
//                                 (normKonsumsi * bobot.get(3)) +
//                                 (normFisik * bobot.get(4));
//
//                scores.add(new Object[]{id_motor, merk, sawScore});
//            }
//
//            // Urutkan berdasarkan skor (descending)
//            scores.sort((a, b) -> Double.compare((double) b[2], (double) a[2]));
//
//            // Tampilkan di tabel peringkat dan simpan ke database
//            tabelmodelperingkat();
//            String insertSql = "INSERT INTO peringkat (id_motor, merk, saw_score, rank) VALUES (?, ?, ?, ?)";
//            PreparedStatement pstmt = conn.prepareStatement(insertSql);
//            int rank = 1;
//            for (Object[] score : scores) {
//                tabmodePeringkat.addRow(new Object[]{
//                    score[0],
//                    score[1],
//                    String.format("%.2f", score[2]),
//                    rank
//                });
//
//                // Insert into peringkat table
//                pstmt.setString(1, (String) score[0]);
//                pstmt.setString(2, (String) score[1]);
//                pstmt.setDouble(3, (Double) score[2]);
//                pstmt.setInt(4, rank);
//                pstmt.executeUpdate();
//                rank++;
//            }
//            pstmt.close();
//            JOptionPane.showMessageDialog(this, "Data Peringkat Berhasil tersimpan!");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(this, "Error dalam perhitungan: " + ex.getMessage());
//        }
         try {
        LinkedList<Float> bobot = new LinkedList<>();
        LinkedList<String> jenisKriteria = new LinkedList<>();
        LinkedList<Float> max = new LinkedList<>();
        LinkedList<Float> min = new LinkedList<>();
        LinkedList<Object[]> scores = new LinkedList<>();

        // Clear existing data in peringkat table
        String clearSql = "DELETE FROM peringkat";
        conn.createStatement().executeUpdate(clearSql);

        // Ambil bobot dan jenis kriteria dari tabel kriteria
        ResultSet resKriteria = conn.createStatement().executeQuery("SELECT bobot, jenis FROM kriteria ORDER BY id");
        while (resKriteria.next()) {
            bobot.add(resKriteria.getFloat("bobot"));
            jenisKriteria.add(resKriteria.getString("jenis"));
        }
        if (bobot.size() != 5 || jenisKriteria.size() != 5) {
            throw new SQLException("Incomplete kriteria data: expected 5 criteria, got " + bobot.size());
        }

        // Ambil nilai max dan min dari penilaian
        ResultSet maxMinRes = conn.createStatement().executeQuery(
            "SELECT MAX(harga), MAX(tahun_produksi), MAX(kondisi_mesin), MAX(konsumsi_bbm), MAX(kondisi_fisik), " +
            "MIN(harga), MIN(tahun_produksi), MIN(kondisi_mesin), MIN(konsumsi_bbm), MIN(kondisi_fisik) " +
            "FROM penilaian"
        );
        if (maxMinRes.next()) {
            max.add(maxMinRes.getFloat(1)); // max harga
            max.add(maxMinRes.getFloat(2)); // max tahun_produksi
            max.add(maxMinRes.getFloat(3)); // max kondisi_mesin
            max.add(maxMinRes.getFloat(4)); // max konsumsi_bbm
            max.add(maxMinRes.getFloat(5)); // max kondisi_fisik
            min.add(maxMinRes.getFloat(6)); // min harga
            min.add(maxMinRes.getFloat(7)); // min tahun_produksi
            min.add(maxMinRes.getFloat(8)); // min kondisi_mesin
            min.add(maxMinRes.getFloat(9)); // min konsumsi_bbm
            min.add(maxMinRes.getFloat(10)); // min kondisi_fisik
        }
        if (max.size() != 5 || min.size() != 5) {
            throw new SQLException("Incomplete max/min data: expected 5 values each, got max=" + max.size() + ", min=" + min.size());
        }

        // Ambil data penilaian
        ResultSet res = conn.createStatement().executeQuery(
            "SELECT p.id_motor, p.harga, p.tahun_produksi, p.kondisi_mesin, p.konsumsi_bbm, p.kondisi_fisik, m.merk " +
            "FROM penilaian p LEFT JOIN motor m ON p.id_motor = m.id_motor"
        );
        while (res.next()) {
            String id_motor = res.getString("id_motor");
            String merk = res.getString("merk");
            float harga = res.getFloat("harga");
            float tahun_produksi = res.getFloat("tahun_produksi");
            float kondisi_mesin = res.getFloat("kondisi_mesin");
            float konsumsi_bbm = res.getFloat("konsumsi_bbm");
            float kondisi_fisik = res.getFloat("kondisi_fisik");

            if (id_motor == null || merk == null) {
                System.err.println("Warning: Null data detected for id_motor=" + id_motor + ", merk=" + merk);
                continue; // Skip invalid rows
            }

            // Normalisasi
            float normHarga = jenisKriteria.get(0).equals("Cost") ? min.get(0) / harga : harga / max.get(0);
            float normTahun = jenisKriteria.get(1).equals("Cost") ? min.get(1) / tahun_produksi : tahun_produksi / max.get(1);
            float normMesin = jenisKriteria.get(2).equals("Cost") ? min.get(2) / kondisi_mesin : kondisi_mesin / max.get(2);
            float normKonsumsi = jenisKriteria.get(3).equals("Cost") ? min.get(3) / konsumsi_bbm : konsumsi_bbm / max.get(3);
            float normFisik = jenisKriteria.get(4).equals("Cost") ? min.get(4) / kondisi_fisik : kondisi_fisik / max.get(4);

            // Hitung skor SAW
            double sawScore = (normHarga * bobot.get(0)) +
                            (normTahun * bobot.get(1)) +
                            (normMesin * bobot.get(2)) +
                            (normKonsumsi * bobot.get(3)) +
                            (normFisik * bobot.get(4));

            // Tentukan peringkat berdasarkan rentang skor
            int rank;
            if (sawScore >= 0.00 && sawScore <= 0.20) {
                rank = 5;
            } else if (sawScore > 0.21 && sawScore <= 0.40) {
                rank = 4;
            } else if (sawScore > 0.41 && sawScore <= 0.60) {
                rank = 3;
            } else if (sawScore > 0.61 && sawScore <= 0.80) {
                rank = 2;
            } else {
                rank = 1;
            }

            scores.add(new Object[]{id_motor, merk, sawScore, rank});
        }
        if (scores.isEmpty()) {
            throw new SQLException("No valid data retrieved from penilaian/motor tables");
        }

        // Urutkan berdasarkan skor (descending)
        scores.sort((a, b) -> Double.compare((double) b[2], (double) a[2]));

        // Tampilkan di tabel peringkat dan simpan ke database
        tabelmodelperingkat();
        String insertSql = "INSERT INTO peringkat (id_motor, merk, saw_score, rank) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(insertSql);
        for (Object[] score : scores) {
            if (score.length != 4) {
                System.err.println("Invalid score array length: " + score.length + ", skipping");
                continue;
            }
            tabmodePeringkat.addRow(new Object[]{
                score[0], // id_motor
                score[1], // merk
                String.format("%.2f", score[2]), // saw_score
                score[3] // rank
            });

            // Insert into peringkat table
            pstmt.setString(1, (String) score[0]);
            pstmt.setString(2, (String) score[1]);
            pstmt.setDouble(3, (Double) score[2]);
            pstmt.setInt(4, (Integer) score[3]);
            pstmt.executeUpdate();
        }
        pstmt.close();
        JOptionPane.showMessageDialog(this, "Data Peringkat Berhasil tersimpan!");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error dalam perhitungan: " + ex.getMessage());
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Unexpected error: " + ex.getMessage());
    }
    }

    public FProsesData() {
        initComponents();
        datatable();
        tabelmodelnormalisasi();
        tabelmodelperingkat();
        setLocationRelativeTo(this);
    }

    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelPenilaian = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabelNormalisasi = new javax.swing.JTable();
        Btn_Normalisasi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelPeringkat = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1190, 600));
        setMinimumSize(new java.awt.Dimension(1190, 600));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setMaximumSize(new java.awt.Dimension(1190, 600));
        jPanel1.setMinimumSize(new java.awt.Dimension(1190, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(1190, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(28, 28, 28));

        jLabel1.setFont(new java.awt.Font("Sitka Subheading", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Motor");

        jLabel2.setFont(new java.awt.Font("Sitka Subheading", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tabel Peringkat");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(268, 268, 268))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1243, -1));

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
        jScrollPane1.setViewportView(TabelPenilaian);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 568, 220));

        jLabel3.setFont(new java.awt.Font("Sitka Subheading", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tabel Normalisasi");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        TabelNormalisasi.setBackground(new java.awt.Color(204, 204, 204));
        TabelNormalisasi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(TabelNormalisasi);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 568, 220));

        Btn_Normalisasi.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        Btn_Normalisasi.setText("Normalisasikan");
        Btn_Normalisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_NormalisasiActionPerformed(evt);
            }
        });
        jPanel1.add(Btn_Normalisasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, 210, -1));

        TabelPeringkat.setBackground(new java.awt.Color(204, 204, 204));
        TabelPeringkat.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(TabelPeringkat);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, 568, 460));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/icons8-gear-100.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1137, 679, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Motor 2.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1025, 679, -1, -1));

        jButton1.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jButton1.setText("Tentukan Peringkat");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 40, 200, -1));

        jButton2.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jButton2.setText("Kembali");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 550, 116, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_NormalisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_NormalisasiActionPerformed
       normalisasi();
    }//GEN-LAST:event_Btn_NormalisasiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        saw();        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        FHalamanUtama menu = new FHalamanUtama();
        menu.setVisible(true);
        menu.pack();
        menu.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButton2MouseClicked

    
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
            java.util.logging.Logger.getLogger(FProsesData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FProsesData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FProsesData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FProsesData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FProsesData().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Normalisasi;
    private javax.swing.JTable TabelNormalisasi;
    private javax.swing.JTable TabelPenilaian;
    private javax.swing.JTable TabelPeringkat;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

}    
    

    

