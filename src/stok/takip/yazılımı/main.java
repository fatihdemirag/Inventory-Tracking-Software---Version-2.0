//developer fatihdemirag\fxd
package stok.takip.yazılımı;

import java.awt.Color;
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
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

public class main extends javax.swing.JFrame {

    ButtonGroup buttonGroup = new ButtonGroup();
    ButtonModel buttonModel;

    class Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //Tarihler
            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);
            DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            satisTarih.setText(date.format(cal.getTime()));
            satisTarih2.setText(hour + ":" + min + ":" + sec);
        }
    }

    class yenile implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            tablo();
        }
    }

    public main() {
        initComponents();
        this.setExtendedState(this.MAXIMIZED_BOTH);
        Timer timer = new Timer(0, new Listener());
        Timer t2 = new Timer(1000 * 30 * 1, new yenile());
        t2.start();
        timer.start();
        new Listener();
        new yenile();
        buttonGroup.add(nakitRadio);
        buttonGroup.add(krediRadio);
        ImageIcon icon = new ImageIcon("/home/fxd/Workspace/stokTakip/stokTakip/icon.png");
        this.setIconImage(icon.getImage());
        connection();
        tablo();
        logoPanel.setBackground(Color.darkGray);
        logo.setForeground(Color.white);
        sürümNo.setForeground(Color.white);
        menüPanel.setBackground(Color.darkGray);
        satisTarih2.setForeground(Color.white);
        this.setLocationRelativeTo(null);

    }
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

    public void tablo() {
        try {
            String sql = "select id as Id ,ürün_adi as Ürün_Adi,alis_fiyati as Alis_Fiyati,satis_fiyati as Satis_Fiyati,"
                    + "ürün_grubu as Ürün_Grubu,stok_miktari as Stok_Miktari from ürünler order by ürün_Grubu asc";
            prs = con.prepareStatement(sql);
            rs = prs.executeQuery();
            tablo.setModel(DbUtils.resultSetToTableModel(rs));
            tablo.getColumnModel().getColumn(0).setMaxWidth(40);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablo = new javax.swing.JTable();
        logoPanel = new javax.swing.JPanel();
        menüPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        satisTarih2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        ürünSil = new javax.swing.JButton();
        ürünGüncelle = new javax.swing.JButton();
        ürünEkle = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        sürümNo = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        alisFiyatiText = new javax.swing.JLabel();
        satisFiyatiLabel = new javax.swing.JLabel();
        ürünAdiLabel = new javax.swing.JLabel();
        stokMiktariLabel = new javax.swing.JLabel();
        krediRadio = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        nakitRadio = new javax.swing.JRadioButton();
        alisFiyatiLabel = new javax.swing.JLabel();
        ürünAdiText = new javax.swing.JLabel();
        satisFiyatiText = new javax.swing.JLabel();
        ürünGrubuLabel = new javax.swing.JLabel();
        stokMiktariText = new javax.swing.JLabel();
        satisButon = new javax.swing.JButton();
        ürünGrubuText = new javax.swing.JLabel();
        satisTarih = new javax.swing.JLabel();
        satisTarihText = new javax.swing.JLabel();
        ödemeSekliLabel = new javax.swing.JLabel();
        ödemeSekliText = new javax.swing.JLabel();
        satisMiktariText = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        refreshTable = new javax.swing.JButton();
        filtreCombobox = new javax.swing.JComboBox<>();
        sorterText = new javax.swing.JTextField();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Stok Takip Programı");
        setIconImages(null);
        setResizable(false);

        tablo.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
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
        tablo.setToolTipText("Ürünler Her 30 Saniyede Bir Güncellenmektedir");
        tablo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabloMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablo);

        logoPanel.setForeground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout menüPanelLayout = new javax.swing.GroupLayout(menüPanel);
        menüPanel.setLayout(menüPanelLayout);
        menüPanelLayout.setHorizontalGroup(
            menüPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menüPanelLayout.setVerticalGroup(
            menüPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/time-passing.png"))); // NOI18N

        satisTarih2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        satisTarih2.setText("-");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newspaper.png"))); // NOI18N
        jButton2.setText("RAPORLAR         ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        ürünSil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/error.png"))); // NOI18N
        ürünSil.setText("ÜRÜN SİL            ");
        ürünSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ürünSilActionPerformed(evt);
            }
        });

        ürünGüncelle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/contract.png"))); // NOI18N
        ürünGüncelle.setText("ÜRÜN GÜNCELLE");
        ürünGüncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ürünGüncelleActionPerformed(evt);
            }
        });

        ürünEkle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus.png"))); // NOI18N
        ürünEkle.setText("ÜRÜN EKLE       ");
        ürünEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ürünEkleActionPerformed(evt);
            }
        });

        logo.setFont(new java.awt.Font("Dialog", 0, 30)); // NOI18N
        logo.setText("Stok Takip");

        sürümNo.setText("2.0");

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(logoPanelLayout.createSequentialGroup()
                        .addGroup(logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ürünSil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ürünEkle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ürünGüncelle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(logoPanelLayout.createSequentialGroup()
                        .addGroup(logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(logoPanelLayout.createSequentialGroup()
                                .addGroup(logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(sürümNo)
                                    .addComponent(logo))
                                .addGap(57, 57, 57)
                                .addComponent(menüPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(logoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(satisTarih2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        logoPanelLayout.setVerticalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(menüPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sürümNo)
                .addGap(47, 47, 47)
                .addGroup(logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(satisTarih2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ürünEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ürünGüncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ürünSil, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        alisFiyatiText.setText("-");

        satisFiyatiLabel.setText("SATIŞ FİYATI (TL)");

        ürünAdiLabel.setText("ÜRÜN ADI");

        stokMiktariLabel.setText("Stok Miktarı");

        krediRadio.setText("Kredi Karti");
        krediRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                krediRadioActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setText("Satış Miktarı");

        nakitRadio.setText("Nakit");
        nakitRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nakitRadioActionPerformed(evt);
            }
        });

        alisFiyatiLabel.setText("ALIŞ FİYATI (TL)");

        ürünAdiText.setText("-");

        satisFiyatiText.setText("-");

        ürünGrubuLabel.setText("ÜRÜN GRUBU");

        stokMiktariText.setText("-");

        satisButon.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        satisButon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/voucher.png"))); // NOI18N
        satisButon.setText("SATIŞ");
        satisButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                satisButonActionPerformed(evt);
            }
        });

        ürünGrubuText.setText("-");

        satisTarih.setText("-");

        satisTarihText.setText("Satış Tarihi");

        ödemeSekliLabel.setText("Ödeme Şekli");

        ödemeSekliText.setText("-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ürünAdiLabel)
                    .addComponent(alisFiyatiLabel)
                    .addComponent(satisFiyatiLabel)
                    .addComponent(stokMiktariLabel)
                    .addComponent(ürünGrubuLabel)
                    .addComponent(satisTarihText)
                    .addComponent(ödemeSekliLabel))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ödemeSekliText)
                    .addComponent(satisTarih, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alisFiyatiText)
                    .addComponent(ürünAdiText)
                    .addComponent(satisFiyatiText)
                    .addComponent(stokMiktariText)
                    .addComponent(ürünGrubuText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(satisMiktariText)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nakitRadio)
                            .addComponent(krediRadio)))
                    .addComponent(satisButon, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(satisMiktariText, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nakitRadio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(krediRadio)))
                        .addGap(18, 18, 18)
                        .addComponent(satisButon, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ürünAdiLabel)
                            .addComponent(ürünAdiText))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(alisFiyatiLabel)
                            .addComponent(alisFiyatiText))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(satisFiyatiLabel)
                            .addComponent(satisFiyatiText))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ürünGrubuText)
                            .addComponent(ürünGrubuLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stokMiktariLabel)
                            .addComponent(stokMiktariText))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ödemeSekliLabel)
                            .addComponent(ödemeSekliText))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(satisTarih)
                            .addComponent(satisTarihText)))))
        );

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 22)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/magnifying-glass.png"))); // NOI18N

        refreshTable.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        refreshTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/refresh.png"))); // NOI18N
        refreshTable.setText("Listeyi Güncelle");
        refreshTable.setToolTipText("Ürünler Her 30 Saniyede Bir Güncellenmektedir El İle Güncellemek İçin TIklayınız");
        refreshTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshTableActionPerformed(evt);
            }
        });

        filtreCombobox.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        filtreCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ürün Grubuna Göre", "Ürün İsmine Göre", "Stok Miktarına Göre Düşükten Yükseğe", "Stok Miktarına Göre Yüksekten Düşüğe" }));
        filtreCombobox.setToolTipText("Filtrele");
        filtreCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtreComboboxActionPerformed(evt);
            }
        });

        sorterText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sorterTextKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sorterText, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refreshTable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtreCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sorterText)
                    .addComponent(filtreCombobox, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshTable, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nakit)
                    .addComponent(jLabel13))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nakitToplam)
                    .addComponent(krediKartiToplam))
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
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(günlükKarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kasaToplamParaLabel))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(günlükKarSayi)
                            .addComponent(kasaToplamSayiLabel))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kasaToplamParaLabel)
                    .addComponent(kasaToplamSayiLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(günlükKarLabel)
                    .addComponent(günlükKarSayi))
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
            .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabloMouseClicked
        try {
            int row = tablo.getSelectedRow();
            String tableClick = (tablo.getModel().getValueAt(row, 0).toString());
            String sqlString = "select * from ürünler where id='" + tableClick + "'";
            prs = con.prepareStatement(sqlString);
            rs = prs.executeQuery();
            if (rs.next()) {
                String add1 = rs.getString("ürün_adi");
                ürünAdiText.setText(add1);
                int add2 = rs.getInt("alis_fiyati");
                alisFiyatiText.setText(add2 + "");
                int add3 = rs.getInt("satis_fiyati");
                satisFiyatiText.setText(add3 + "");
                int add5 = rs.getInt("stok_miktari");
                stokMiktariText.setText(add5 + "");
                String add4 = rs.getString("ürün_grubu");
                ürünGrubuText.setText(add4);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tabloMouseClicked

    private void satisButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_satisButonActionPerformed
        if (buttonGroup.isSelected(buttonModel)) {
            JOptionPane.showMessageDialog(null, "Lütfen Ödeme Şeklini Seçiniz", "Ödeme Şeklini Seçiniz", NORMAL);
        } else {
            try {
                //-------------------------------------------------------------------------------------------//    
                String urun = ürünAdiText.getText();
                String geciciUrunAdeti = stokMiktariText.getText();
                int gecici = Integer.parseInt(geciciUrunAdeti);
                String satilacakAdet = satisMiktariText.getText();
                int geciciSatilacakAdet = Integer.parseInt(satilacakAdet);
                //-------------------------------------------------------------------------------------------//
                int stokMiktar = Integer.parseInt(stokMiktariText.getText());
                int satisMiktar = Integer.parseInt(satisMiktariText.getText());
                //Stok durumu kontrol
                if (satisMiktar > stokMiktar) {
                    JOptionPane.showMessageDialog(null, "Stokta o kadar ürün yok\nStoktaki Ürün Adedi : " + stokMiktar, "Stok Yetersiz", NORMAL);

                } else {
                    int kalanAdet = gecici - geciciSatilacakAdet;
                    //-------------------------
                    int satisFiyat = Integer.parseInt(satisFiyatiText.getText());
                    int alisFiyat = Integer.parseInt(alisFiyatiText.getText());
                    int satisMiktari = Integer.parseInt(satisMiktariText.getText());
                    int ürünKari = (satisFiyat - alisFiyat) * satisMiktari;

                    String query = ("update ürünler set stok_miktari='" + kalanAdet + "' where ürün_adi='" + urun + "'");
                    st = (com.mysql.jdbc.Statement) con.createStatement();
                    st.executeUpdate(query);

                    String ekleSql = ("insert into satislar(satilan_ürün,satis_miktari,satis_tipi,satis_tarihi,ürün_kari,satis_fiyati) "
                            + "values('" + ürünAdiText.getText() + "','" + satisMiktariText.getText() + "','" + ödemeSekliText.getText() + "'"
                            + ",'" + satisTarih.getText() + "','" + ürünKari + "','" + satisFiyat + "')");
                    st = con.createStatement();
                    st.executeUpdate(ekleSql);
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
                    JOptionPane.showMessageDialog(null, "Ürün Satıldı", "Ürün Satıldı", ICONIFIED);
                }
                ürünAdiText.setText("-");
                alisFiyatiText.setText("-");
                satisFiyatiText.setText("-");
                ürünGrubuText.setText("-");
                stokMiktariText.setText("-");
                ödemeSekliText.setText("-");
                satisMiktariText.setText("");
                buttonGroup.clearSelection();
                tablo();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ürün Satılamadı Lütfen Tekrar Deneyin", "Ürün Satılamadı", NORMAL);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_satisButonActionPerformed

    private void ürünGüncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ürünGüncelleActionPerformed
        ürünGüncelle satis = new ürünGüncelle();
        satis.setVisible(true);
    }//GEN-LAST:event_ürünGüncelleActionPerformed

    private void ürünSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ürünSilActionPerformed
        ürünSil sil = new ürünSil();
        sil.setVisible(true);
    }//GEN-LAST:event_ürünSilActionPerformed

    private void ürünEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ürünEkleActionPerformed
        ürünEkle ekle = new ürünEkle();
        ekle.setVisible(true);
    }//GEN-LAST:event_ürünEkleActionPerformed

    private void refreshTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshTableActionPerformed
        tablo();
    }//GEN-LAST:event_refreshTableActionPerformed

    private void krediRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_krediRadioActionPerformed
        ödemeSekliText.setText("Kredi Kartı");
    }//GEN-LAST:event_krediRadioActionPerformed

    private void nakitRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nakitRadioActionPerformed
        ödemeSekliText.setText("Nakit");
    }//GEN-LAST:event_nakitRadioActionPerformed

    private void filtreComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtreComboboxActionPerformed
        String c = String.valueOf(filtreCombobox.getSelectedItem());
        if (c == "Ürün Grubuna Göre") {
            try {
                String sql = "select * from ürünler order by ürün_grubu asc";
                prs = con.prepareStatement(sql);
                rs = prs.executeQuery();
                tablo.setModel(DbUtils.resultSetToTableModel(rs));
                tablo.getColumnModel().getColumn(0).setMaxWidth(40);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else if (c == "Ürün İsmine Göre") {
            try {
                String sql = "select * from ürünler order by ürün_adi asc";
                prs = con.prepareStatement(sql);
                rs = prs.executeQuery();
                tablo.setModel(DbUtils.resultSetToTableModel(rs));
                tablo.getColumnModel().getColumn(0).setMaxWidth(40);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else if (c == "Stok Miktarına Göre Düşükten Yükseğe") {
            try {
                String sql = "select * from ürünler order by stok_miktari asc";
                prs = con.prepareStatement(sql);
                rs = prs.executeQuery();
                tablo.setModel(DbUtils.resultSetToTableModel(rs));
                tablo.getColumnModel().getColumn(0).setMaxWidth(40);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else if (c == "Stok Miktarına Göre Yüksekten Düşüğe") {
            try {
                String sql = "select * from ürünler order by stok_miktari desc";
                prs = con.prepareStatement(sql);
                rs = prs.executeQuery();
                tablo.setModel(DbUtils.resultSetToTableModel(rs));
                tablo.getColumnModel().getColumn(0).setMaxWidth(40);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }//GEN-LAST:event_filtreComboboxActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        raporlar rapor = new raporlar();
        rapor.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void sorterTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sorterTextKeyPressed
        TableRowSorter<TableModel> sorter = new TableRowSorter<>((DefaultTableModel) tablo.getModel());
        sorter.setRowFilter(RowFilter.regexFilter(this.sorterText.getText()));
        tablo.setRowSorter(sorter);
        tablo();
    }//GEN-LAST:event_sorterTextKeyPressed

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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new main().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alisFiyatiLabel;
    private javax.swing.JLabel alisFiyatiText;
    private javax.swing.JComboBox<String> filtreCombobox;
    private javax.swing.JLabel günlükKarLabel;
    private javax.swing.JLabel günlükKarSayi;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel kasaToplamParaLabel;
    private javax.swing.JLabel kasaToplamSayiLabel;
    private javax.swing.JLabel krediKartiToplam;
    private javax.swing.JRadioButton krediRadio;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JPanel menüPanel;
    private javax.swing.JLabel nakit;
    private javax.swing.JRadioButton nakitRadio;
    private javax.swing.JLabel nakitToplam;
    private javax.swing.JButton refreshTable;
    private javax.swing.JButton satisButon;
    private javax.swing.JLabel satisFiyatiLabel;
    private javax.swing.JLabel satisFiyatiText;
    private javax.swing.JTextField satisMiktariText;
    public javax.swing.JLabel satisTarih;
    private javax.swing.JLabel satisTarih2;
    private javax.swing.JLabel satisTarihText;
    private javax.swing.JTextField sorterText;
    private javax.swing.JLabel stokMiktariLabel;
    private javax.swing.JLabel stokMiktariText;
    private javax.swing.JLabel sürümNo;
    public javax.swing.JTable tablo;
    private javax.swing.JLabel ödemeSekliLabel;
    private javax.swing.JLabel ödemeSekliText;
    private javax.swing.JLabel ürünAdiLabel;
    private javax.swing.JLabel ürünAdiText;
    private javax.swing.JButton ürünEkle;
    private javax.swing.JLabel ürünGrubuLabel;
    private javax.swing.JLabel ürünGrubuText;
    private javax.swing.JButton ürünGüncelle;
    private javax.swing.JButton ürünSil;
    // End of variables declaration//GEN-END:variables
}
