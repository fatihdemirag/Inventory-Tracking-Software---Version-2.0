//developer fatihdemirag\fxd
package stok.takip.yazılımı;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

public class ürünSil extends javax.swing.JFrame {

    Connection con;
    ResultSet rs;
    PreparedStatement prs;
    Statement st;
    String dbUrl = "jdbc:mysql://localhost:3306/stok_takip";

    public ürünSil() {
        initComponents();
        ImageIcon icon = new ImageIcon("src/error.png");
        this.setIconImage(icon.getImage());
        connection();
        tablo();
        this.setLocationRelativeTo(null);

    }

    public void connection() {
        try {
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection(dbUrl, "root", "root");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Veritabanı Bağlantısı Başarısız");
        }
    }

    public void tablo() {
        try {
            String sql = "select id as Id ,ürün_adi as Ürün_Adi,alis_fiyati as Alis_Fiyati,satis_fiyati as Satis_Fiyati,"
                    + "ürün_grubu as Ürün_Grubu,stok_miktari as Stok_Miktari from ürünler order by ürün_Grubu asc";
            prs = con.prepareStatement(sql);
            rs = prs.executeQuery();
            tablo.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ürünSil = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablo = new javax.swing.JTable();
        silUrunAdi = new javax.swing.JLabel();
        silAlisFiyati = new javax.swing.JLabel();
        silSatisFiyati = new javax.swing.JLabel();
        silUrunGrubu = new javax.swing.JLabel();
        silStokMiktari = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        sorterText1 = new javax.swing.JTextPane();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        silId = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ürün Silme");
        setBounds(new java.awt.Rectangle(300, 100, 0, 0));

        jLabel11.setText("ÜRÜN ADI");

        jLabel10.setText("ALIŞ FİYATI");

        jLabel9.setText("SATIŞ FİYATI");

        jLabel8.setText("ÜRÜN GRUBU");

        jLabel6.setText("STOK MİKTARI");

        ürünSil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/error.png"))); // NOI18N
        ürünSil.setText("ÜRÜNÜ SİL");
        ürünSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ürünSilActionPerformed(evt);
            }
        });

        tablo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabloMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablo);

        silUrunAdi.setText("-");

        silAlisFiyati.setText("-");

        silSatisFiyati.setText("-");

        silUrunGrubu.setText("-");

        silStokMiktari.setText("-");

        sorterText1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sorterText1KeyPressed(evt);
            }
        });
        jScrollPane19.setViewportView(sorterText1);

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/magnifying-glass.png"))); // NOI18N

        jLabel1.setText("ID");

        silId.setText("-");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(silId)
                            .addComponent(silStokMiktari)
                            .addComponent(silUrunGrubu)
                            .addComponent(silSatisFiyati)
                            .addComponent(silUrunAdi)
                            .addComponent(silAlisFiyati)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ürünSil, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(silId))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(silUrunAdi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(silAlisFiyati))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(silSatisFiyati))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(silUrunGrubu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(silStokMiktari))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ürünSil, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ürünSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ürünSilActionPerformed
        int onay = JOptionPane.showConfirmDialog(null, "Ürünü Silinecek Onaylıyor Musunuz ?", "Onaylıyor Musunuz ?", HEIGHT);
        if (onay == JOptionPane.YES_OPTION) {
            try {
                int id = Integer.valueOf(silId.getText());
                String query = "delete from ürünler where id='" + id + "'";
                st = con.createStatement();
                st.executeUpdate(query);

                silUrunAdi.setText("-");
                silAlisFiyati.setText("-");
                silSatisFiyati.setText("-");
                silUrunGrubu.setText("-");
                silStokMiktari.setText("-");
                tablo();
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ürünSilActionPerformed

    private void tabloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabloMouseClicked
        try {
            int row = tablo.getSelectedRow();
            String tableClick = (tablo.getModel().getValueAt(row, 0).toString());
            String sqlString = "select * from ürünler where id='" + tableClick + "'  ";
            prs = con.prepareStatement(sqlString);
            rs = prs.executeQuery();
            if (rs.next()) {
                String add1 = rs.getString("ürün_adi");
                silUrunAdi.setText(add1);
                String add2 = rs.getString("alis_fiyati");
                silAlisFiyati.setText(add2);
                String add3 = rs.getString("satis_fiyati");
                silSatisFiyati.setText(add3);
                String add4 = rs.getString("ürün_grubu");
                silUrunGrubu.setText(add4);
                String add5 = rs.getString("stok_miktari");
                silStokMiktari.setText(add5);
                String add6 = rs.getString("id");
                silId.setText(add6);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tabloMouseClicked

    private void sorterText1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sorterText1KeyPressed
        TableRowSorter<TableModel> sorter = new TableRowSorter<>((DefaultTableModel) tablo.getModel());
        sorter.setRowFilter(RowFilter.regexFilter(this.sorterText1.getText()));
        tablo.setRowSorter(sorter);
        tablo();
    }//GEN-LAST:event_sorterText1KeyPressed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(ürünSil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ürünSil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ürünSil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ürünSil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ürünSil().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JLabel silAlisFiyati;
    private javax.swing.JLabel silId;
    private javax.swing.JLabel silSatisFiyati;
    private javax.swing.JLabel silStokMiktari;
    private javax.swing.JLabel silUrunAdi;
    private javax.swing.JLabel silUrunGrubu;
    private javax.swing.JTextPane sorterText1;
    private javax.swing.JTable tablo;
    private javax.swing.JButton ürünSil;
    // End of variables declaration//GEN-END:variables
}
