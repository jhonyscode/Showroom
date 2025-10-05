package TA;
import java.awt.Color;

public class FHalamanUtama extends javax.swing.JFrame {

    public FHalamanUtama() {
        initComponents();
        DataMotor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DataKriteria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DataPenilaian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProsesData.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Showroom.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        DataMotor = new custom.PanelCustom();
        jLabel2 = new javax.swing.JLabel();
        DataKriteria = new custom.PanelCustom();
        jLabel4 = new javax.swing.JLabel();
        DataPenilaian = new custom.PanelCustom();
        jLabel3 = new javax.swing.JLabel();
        ProsesData = new custom.PanelCustom();
        jLabel5 = new javax.swing.JLabel();
        Showroom = new custom.PanelCustom();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1190, 600));
        setMinimumSize(new java.awt.Dimension(1190, 600));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jPanel2.setBackground(new java.awt.Color(28, 28, 28));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        DataMotor.setPreferredSize(new java.awt.Dimension(216, 53));
        DataMotor.setRoundBottomLeft(15);
        DataMotor.setRoundBottomRight(15);
        DataMotor.setRoundTopLeft(15);
        DataMotor.setRoundTopRight(15);
        DataMotor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataMotorMouseClicked(evt);
            }
        });
        DataMotor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Sitka Subheading", 1, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/icons8-book-18.png"))); // NOI18N
        jLabel2.setText("Data Motor");
        DataMotor.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 110, 30));

        DataKriteria.setRoundBottomLeft(15);
        DataKriteria.setRoundBottomRight(15);
        DataKriteria.setRoundTopLeft(15);
        DataKriteria.setRoundTopRight(15);
        DataKriteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataKriteriaMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Sitka Subheading", 1, 15)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/icons8-checklist-18.png"))); // NOI18N
        jLabel4.setText("Data Kriteria");

        javax.swing.GroupLayout DataKriteriaLayout = new javax.swing.GroupLayout(DataKriteria);
        DataKriteria.setLayout(DataKriteriaLayout);
        DataKriteriaLayout.setHorizontalGroup(
            DataKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataKriteriaLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DataKriteriaLayout.setVerticalGroup(
            DataKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DataKriteriaLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(14, 14, 14))
        );

        DataPenilaian.setRoundBottomLeft(15);
        DataPenilaian.setRoundBottomRight(15);
        DataPenilaian.setRoundTopLeft(15);
        DataPenilaian.setRoundTopRight(15);
        DataPenilaian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataPenilaianMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Sitka Subheading", 1, 15)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/icons8-evaluation-18.png"))); // NOI18N
        jLabel3.setText("Penilaian");

        javax.swing.GroupLayout DataPenilaianLayout = new javax.swing.GroupLayout(DataPenilaian);
        DataPenilaian.setLayout(DataPenilaianLayout);
        DataPenilaianLayout.setHorizontalGroup(
            DataPenilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataPenilaianLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DataPenilaianLayout.setVerticalGroup(
            DataPenilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DataPenilaianLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(14, 14, 14))
        );

        ProsesData.setRoundBottomLeft(15);
        ProsesData.setRoundBottomRight(15);
        ProsesData.setRoundTopLeft(15);
        ProsesData.setRoundTopRight(15);
        ProsesData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProsesDataMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Sitka Subheading", 1, 15)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/icons8-services-18.png"))); // NOI18N
        jLabel5.setText("Proses Data");

        javax.swing.GroupLayout ProsesDataLayout = new javax.swing.GroupLayout(ProsesData);
        ProsesData.setLayout(ProsesDataLayout);
        ProsesDataLayout.setHorizontalGroup(
            ProsesDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProsesDataLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ProsesDataLayout.setVerticalGroup(
            ProsesDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProsesDataLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(14, 14, 14))
        );

        Showroom.setRoundBottomLeft(15);
        Showroom.setRoundBottomRight(15);
        Showroom.setRoundTopLeft(15);
        Showroom.setRoundTopRight(15);
        Showroom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShowroomMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Sitka Subheading", 1, 15)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/icons8-information-18.png"))); // NOI18N
        jLabel6.setText("Cetak");

        javax.swing.GroupLayout ShowroomLayout = new javax.swing.GroupLayout(Showroom);
        Showroom.setLayout(ShowroomLayout);
        ShowroomLayout.setHorizontalGroup(
            ShowroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShowroomLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel6)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        ShowroomLayout.setVerticalGroup(
            ShowroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ShowroomLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ProsesData, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DataPenilaian, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DataKriteria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DataMotor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Showroom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(DataMotor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DataKriteria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DataPenilaian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ProsesData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Showroom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Sitka Subheading", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Selamat Datang Di Aplikasi SPK Pemilihan Sepeda Motor Terbaik SAW");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/Logo_GJB_FIX_2-removebg-preview.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(262, 262, 262))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void DataMotorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataMotorMouseClicked
        FDataMotor motor = new FDataMotor();
        motor.setVisible(true);
        motor.pack();
        motor.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_DataMotorMouseClicked

    private void DataKriteriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataKriteriaMouseClicked
        FDataKriteria kriteria = new FDataKriteria();
        kriteria.setVisible(true);
        kriteria.pack();
        kriteria.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_DataKriteriaMouseClicked

    private void DataPenilaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataPenilaianMouseClicked
        
        FPenilaian bobot = new FPenilaian();
        bobot.setVisible(true);
        bobot.pack();
        bobot.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_DataPenilaianMouseClicked

    private void ProsesDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProsesDataMouseClicked
        FProsesData proses = new FProsesData();
        proses.setVisible(true);
        proses.pack();
        proses.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_ProsesDataMouseClicked

    private void ShowroomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowroomMouseClicked
        Cetak showroom = new Cetak();
        showroom.setVisible(true);
        showroom.pack();
        showroom.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_ShowroomMouseClicked

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
            java.util.logging.Logger.getLogger(FHalamanUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FHalamanUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FHalamanUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FHalamanUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FHalamanUtama().setVisible(true);
            }
        });
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private custom.PanelCustom DataKriteria;
    private custom.PanelCustom DataMotor;
    private custom.PanelCustom DataPenilaian;
    private custom.PanelCustom ProsesData;
    private custom.PanelCustom Showroom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
