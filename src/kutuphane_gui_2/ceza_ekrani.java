/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kutuphane_gui_2;

import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mobyrktr
 */
public class ceza_ekrani extends javax.swing.JFrame {
    String tckn;
    DefaultTableModel model;
    
    public ceza_ekrani() {
        initComponents();
    }
    
    public ceza_ekrani(String tckn) throws ParseException
    {
        initComponents();
        this.tckn = tckn;
        model = (DefaultTableModel)tbl_ceza.getModel();
        cezalariGor();
    }
    
    public void cezalariGor() throws ParseException
    {
        Odunc_Islemleri o = new Odunc_Islemleri();
        Kitap_Islemleri k = new Kitap_Islemleri();
        ArrayList<ArrayList> cezalar = o.cezalariVer(tckn);
        
        for(ArrayList ceza : cezalar)
        {
            int kitap_id = Integer.valueOf(ceza.get(0).toString());
            String kitap_adi = k.getBookName(kitap_id);
            String verilme_tarihi = o.getVerilmeTarihi(kitap_id, tckn);
            int ceza_gun = Integer.valueOf(ceza.get(1).toString());
            String ceza_bedel = String.valueOf(ceza_gun * 0.5) + "TL";
            Object[] eklenecek = {kitap_id, kitap_adi, verilme_tarihi, ceza_gun, ceza_bedel};
            model.addRow(eklenecek);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_ceza = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ceza Görme Ekranı");
        setLocation(new java.awt.Point(500, 200));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/go_back.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        tbl_ceza.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kitap ID", "Kitap Adı", "Verilme Tarihi", "Ceza", "Ceza Bedeli"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_ceza);
        if (tbl_ceza.getColumnModel().getColumnCount() > 0) {
            tbl_ceza.getColumnModel().getColumn(0).setResizable(false);
            tbl_ceza.getColumnModel().getColumn(1).setResizable(false);
            tbl_ceza.getColumnModel().getColumn(2).setResizable(false);
            tbl_ceza.getColumnModel().getColumn(3).setResizable(false);
            tbl_ceza.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        user_screen u = new user_screen(tckn);
        this.setVisible(false);
        u.setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(ceza_ekrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ceza_ekrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ceza_ekrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ceza_ekrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ceza_ekrani().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_ceza;
    // End of variables declaration//GEN-END:variables
}
