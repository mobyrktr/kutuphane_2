/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kutuphane_gui_2;

import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author mobyrktr
 */
public class kitap_ara extends javax.swing.JFrame {

    DefaultTableModel model;
    Kitap_Islemleri islemler = new Kitap_Islemleri();
    
    public void showBooks()
    {
        model.setRowCount(0);
        
        ArrayList<Kitap> kitaplar =  islemler.showAllBooks();
        
        if(kitaplar != null)
        {
            for(Kitap kitap: kitaplar)
            {
                Object[] eklenecek = {kitap.getKitap_id(), kitap.getKitap_adi(), kitap.getYazar_adi(), kitap.getYayinevi(), kitap.getIsbn(), kitap.getKitap_adet()};
                model.addRow(eklenecek);
            }
        }
    }
    
    public void dinamikAra(String ara)
    {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        tbl_kitap.setRowSorter(tr);
        switch (cb_arama.getSelectedIndex()) {
            case 0:
                tr.setRowFilter(RowFilter.regexFilter(ara, 1));
                break;
            case 1:
                tr.setRowFilter(RowFilter.regexFilter(ara, 2));
                break;
            case 2:
                tr.setRowFilter(RowFilter.regexFilter(ara, 3));
                break;
            case 3:
                tr.setRowFilter(RowFilter.regexFilter(ara, 4));
                break;
            default:
                break;
        }
        
    }
    public kitap_ara() {
        initComponents();
        model = (DefaultTableModel)tbl_kitap.getModel();
        showBooks();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_kitap = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tf_arama = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cb_arama = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kitap Arama Ekranı");
        setLocation(new java.awt.Point(500, 200));

        tbl_kitap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kitap ID", "Kitap Adı", "Yazar Adı", "Yayınevi", "ISBN", "Kitap Adet"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_kitap);
        if (tbl_kitap.getColumnModel().getColumnCount() > 0) {
            tbl_kitap.getColumnModel().getColumn(0).setResizable(false);
            tbl_kitap.getColumnModel().getColumn(1).setResizable(false);
            tbl_kitap.getColumnModel().getColumn(2).setResizable(false);
            tbl_kitap.getColumnModel().getColumn(3).setResizable(false);
            tbl_kitap.getColumnModel().getColumn(4).setResizable(false);
            tbl_kitap.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel1.setText("Arama Türü:");

        tf_arama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_aramaKeyReleased(evt);
            }
        });

        jLabel2.setText("Arama:");

        cb_arama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kitap Adı", "Yazar Adı", "Yayınevi", "ISBN" }));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/go_back.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_arama, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_arama, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 103, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_arama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(cb_arama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_aramaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_aramaKeyReleased
        String ara = tf_arama.getText();
        dinamikAra(ara);
    }//GEN-LAST:event_tf_aramaKeyReleased

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        user_screen u = new user_screen();
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
            java.util.logging.Logger.getLogger(kitap_ara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kitap_ara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kitap_ara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kitap_ara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new kitap_ara().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb_arama;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_kitap;
    private javax.swing.JTextField tf_arama;
    // End of variables declaration//GEN-END:variables
}
