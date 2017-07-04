package stok.takip.yazılımı;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import net.proteanit.sql.DbUtils;

public class raporlar extends javax.swing.JFrame {

    Connection con;
    ResultSet rs;
    PreparedStatement prs;
    Statement st;
    String dbUrl = "jdbc:mysql://localhost:3306/stok_takip";

    public void connection() {
        try {
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection(dbUrl, "root", "root");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Veritabanı Bağlantısı Başarısız");
        }
    }

    class Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);
            DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            satisTarih.setText(date.format(cal.getTime()));
            satisSaat.setText(hour + ":" + min + ":" + sec);
        }
    }

    public void tablo() {
        try {
            String sql = "select id as Id ,satilan_ürün as Ürün_Adi,satis_miktari as Satis_Miktari from satislar";
            prs = con.prepareStatement(sql);
            rs = prs.executeQuery();
            günlükTablo.setModel(DbUtils.resultSetToTableModel(rs));
            günlükTablo.getColumnModel().getColumn(0).setMaxWidth(40);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void tablo2() {
        try {
            SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
            String tarih_ilk = d.format(jDateChooser2.getDate());
            String tarih_son = d.format(jDateChooser3.getDate());
            String sql2 = "select distinct * from satislar where satis_tarihi between '" + tarih_ilk + "' and '" + tarih_son + "'";
            prs = con.prepareStatement(sql2);
            ResultSet rs1 = prs.executeQuery();
            tarihlerTablo.setModel(DbUtils.resultSetToTableModel(rs1));
            tarihlerTablo.getColumnModel().getColumn(0).setMaxWidth(40);
        } catch (Exception e) {
        }
    }

    public void tablo3() {
        try {
            String sql = "select sum(stok_miktari) from ürünler";
            prs = con.prepareStatement(sql);
            ResultSet rs1 = prs.executeQuery();
            while (rs1.next()) {
                String s = rs1.getString(1);
                System.out.println(s);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    public void listele() {
        DefaultListModel listModel = new DefaultListModel();
        try {
            String s = satisTarih.getText();
            String sql = "select distinct satilan_ürün from satislar where satis_tarihi='" + s + "'";
            prs = con.prepareStatement(sql);
            rs = prs.executeQuery();
            while (rs.next()) {
                String ürünBaslik = rs.getString("satilan_ürün");
                listModel.addElement(ürünBaslik);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void yetersizStok() {

        DefaultListModel listModel = new DefaultListModel();
        try {

            String sql = "select distinct * from ürünler where stok_miktari=0";
            prs = con.prepareStatement(sql);
            rs = prs.executeQuery();
            while (rs.next()) {
                String ürünBaslik = rs.getString("ürün_adi");
                listModel.addElement(ürünBaslik);
            }
            stokYetersiz.setModel(listModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class yenile implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            listele();
        }
    }

    public void günlükRapor() {
        //Nakit Toplamlarını hesaplar
        try {
            String sql = "select sum(satis_fiyati) from satislar where satis_tarihi='" + satisTarih.getText() + "' and satis_tipi like '%Nakit%'";
            prs = con.prepareStatement(sql);
            rs = prs.executeQuery();
            while (rs.next()) {
                String result = rs.getString(1);
                nakitToplam.setText(result + " TL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Kredi Kartı Toplamlarını hesaplar
        try {
            String sql = "select sum(satis_fiyati) from satislar where satis_tarihi='" + satisTarih.getText() + "' and satis_tipi like '%Kredi%'";
            prs = con.prepareStatement(sql);
            rs = prs.executeQuery();
            while (rs.next()) {
                String result = rs.getString(1);
                krediKartiToplam.setText(result + " TL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Ürün Karlarını hesapla-yolla
        try {
            String sql = "select sum(ürün_kari) from satislar where satis_tarihi='" + satisTarih.getText() + "'";
            prs = con.prepareStatement(sql);
            rs = prs.executeQuery();
            while (rs.next()) {
                String result = rs.getString(1);
                günlükKarSayi.setText(result + " TL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Ürünlerin Satış Fiyatını Hesapla ve yolla
        try {
            String sql = "select sum(satis_fiyati) from satislar where satis_tarihi='" + satisTarih.getText() + "'";
            prs = con.prepareStatement(sql);
            rs = prs.executeQuery();
            while (rs.next()) {
                String result = rs.getString(1);
                kasaToplamSayiLabel.setText(result + " TL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public raporlar() {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }
        initComponents();
        Timer t = new Timer(1000, new Listener());
        t.start();
        Timer tt = new Timer(1000, new yenile());
        tt.start();
        connection();
        listele();
        yetersizStok();
        this.setLocationRelativeTo(null);

        header.setBackground(Color.black);
        baslik.setForeground(Color.white);
        satisTarih.setForeground(Color.white);
        satisSaat.setForeground(Color.white);

        stokYetersiz.setPreferredSize(new Dimension(200, 403));
        ImageIcon icon = new ImageIcon("src/newspaper.png");
        this.setIconImage(icon.getImage());
        tablo();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        baslik = new javax.swing.JLabel();
        satisTarih = new javax.swing.JLabel();
        satisSaat = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        kasaToplamSayiLabel = new javax.swing.JLabel();
        kasaToplamParaLabel = new javax.swing.JLabel();
        günlükKarSayi = new javax.swing.JLabel();
        günlükKarLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        nakitToplam = new javax.swing.JLabel();
        krediKartiToplam = new javax.swing.JLabel();
        nakit = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        günlükTablo = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tarihlerTablo = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        stokYetersiz = new javax.swing.JList<>();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        tarihlerArası = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Raporlar");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newspaper-Rapolar.png"))); // NOI18N

        baslik.setBackground(new java.awt.Color(255, 255, 255));
        baslik.setFont(new java.awt.Font("Dialog", 0, 28)); // NOI18N
        baslik.setText("RAPORLAR");

        satisTarih.setText("-");

        satisSaat.setText("-");

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jPanel3.setBackground(new java.awt.Color(255, 252, 252));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("RAPORLARI YENİLE");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/refresh.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel6)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(baslik)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(satisSaat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(satisTarih, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(960, 960, 960))))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(baslik)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(satisTarih)
                            .addComponent(satisSaat)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, headerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        kasaToplamSayiLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        kasaToplamSayiLabel.setText("0");

        kasaToplamParaLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        kasaToplamParaLabel.setText("Kasadaki Toplam Para");

        günlükKarSayi.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        günlükKarSayi.setText("0");

        günlükKarLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        günlükKarLabel.setText("Günlük Kar ");

        jLabel13.setText("Kredi Kartı Toplam");

        nakitToplam.setText("0");

        krediKartiToplam.setText("0");

        nakit.setText("Nakit Toplam");

        günlükTablo.setAutoCreateRowSorter(true);
        günlükTablo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        günlükTablo.setEnabled(false);
        günlükTablo.setGridColor(new java.awt.Color(255, 255, 255));
        günlükTablo.setShowHorizontalLines(false);
        günlükTablo.setShowVerticalLines(false);
        jScrollPane1.setViewportView(günlükTablo);

        jLabel4.setText("Bugün Satışı Yapılan Ürünler");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nakit)
                            .addComponent(jLabel13))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nakitToplam)
                            .addComponent(krediKartiToplam)))
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nakit)
                    .addComponent(nakitToplam))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(krediKartiToplam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        tarihlerTablo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tarihlerTablo.setEnabled(false);
        tarihlerTablo.setShowHorizontalLines(false);
        tarihlerTablo.setShowVerticalLines(false);
        jScrollPane3.setViewportView(tarihlerTablo);

        stokYetersiz.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        stokYetersiz.setToolTipText("");
        stokYetersiz.setMaximumSize(new java.awt.Dimension(300, 0));
        stokYetersiz.setMinimumSize(new java.awt.Dimension(400, 403));
        stokYetersiz.setOpaque(false);
        jScrollPane2.setViewportView(stokYetersiz);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(günlükKarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kasaToplamParaLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(günlükKarSayi)
                            .addComponent(kasaToplamSayiLabel))
                        .addGap(164, 164, 164))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(534, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kasaToplamParaLabel)
                    .addComponent(kasaToplamSayiLabel))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(günlükKarLabel)
                    .addComponent(günlükKarSayi))
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 74, Short.MAX_VALUE))
        );

        jDateChooser2.setDateFormatString("d/mm/yyyy");

        jLabel7.setText("Arası");

        jDateChooser3.setDateFormatString("d/mm/yyyy");

        tarihlerArası.setText("Satışları Getir");
        tarihlerArası.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarihlerArasıActionPerformed(evt);
            }
        });

        jLabel5.setText("Stoğu Biten Ürünler");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tarihlerArası)
                .addGap(108, 108, 108)
                .addComponent(jLabel5)
                .addGap(132, 132, 132))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tarihlerArası)
                    .addComponent(jLabel7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tarihlerArasıActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarihlerArasıActionPerformed
        tablo2();
        tablo3();
    }//GEN-LAST:event_tarihlerArasıActionPerformed

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        günlükRapor();
    }//GEN-LAST:event_jPanel3MouseClicked

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
            java.util.logging.Logger.getLogger(raporlar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(raporlar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(raporlar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(raporlar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new raporlar().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel baslik;
    private javax.swing.JLabel günlükKarLabel;
    private javax.swing.JLabel günlükKarSayi;
    private javax.swing.JTable günlükTablo;
    private javax.swing.JPanel header;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel kasaToplamParaLabel;
    private javax.swing.JLabel kasaToplamSayiLabel;
    private javax.swing.JLabel krediKartiToplam;
    private javax.swing.JLabel nakit;
    private javax.swing.JLabel nakitToplam;
    private javax.swing.JLabel satisSaat;
    private javax.swing.JLabel satisTarih;
    private javax.swing.JList<String> stokYetersiz;
    private javax.swing.JButton tarihlerArası;
    private javax.swing.JTable tarihlerTablo;
    // End of variables declaration//GEN-END:variables
}
