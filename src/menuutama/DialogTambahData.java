
package menuutama;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JRootPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;

/**
 *
 * @author Neubri
 */
public class DialogTambahData extends javax.swing.JDialog {
    private Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    /**
     * Creates new form DialogTambahData
     */
    public DialogTambahData(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        //add Panel, add panel(sebuah panel)
        Pane.add(PanelKandidat);
        Pane.repaint();
        Pane.revalidate();
    }
    
    
    public void setDataTabel(String id_paket, String nama_paket, String jenis_menu, String tingkat_vegetarian, String jumlah_orang, String menu_paket, String harga_paket, String jumlah_menu, String tingkat_popularitas){
        tNoId.setText(id_paket);
        tNamaPaket.setText(nama_paket);
        if(jenis_menu.equals("Indian Food")){
            rbWesternFood.setSelected(true);
            rbIndianFood.setSelected(false);
        }else if(jenis_menu.equals("Western Food")){
            rbWesternFood.setSelected(false);
            rbIndianFood.setSelected(true);
        }
        cbTingkatVegetarian.setSelectedItem(tingkat_vegetarian);
        tJumlahOrang.setText(jumlah_orang);
        tMenuPaket.setText(menu_paket);
        tHargaPaket.setText(harga_paket);
        cbJumlahMenu.setSelectedItem(jumlah_menu);
        cbTingkatPopularitas.setSelectedItem(tingkat_popularitas);
    }
    
    protected void kosong(){
        tNoId.setText("");
        tNamaPaket.setText("");
        btnG.clearSelection();
        cbTingkatVegetarian.setSelectedIndex(0);
        tMenuPaket.setText("");
        tJumlahOrang.setText("");
        tHargaPaket.setText("");
        cbJumlahMenu.setSelectedIndex(0);
        cbTingkatPopularitas.setSelectedIndex(0);
    }
    
    private void insertDataPaket(){        
        String sql = "INSERT INTO data_paket VALUES (?,?,?,?,?,?,?,?,?)";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, tNoId.getText());
                stat.setString(2, tNamaPaket.getText());

                String jenis_menu = "";
                if(rbWesternFood.isSelected()){
                    jenis_menu="Western Food";
                }else if(rbIndianFood.isSelected()){
                    jenis_menu="Indian Food";
                }
                stat.setString(3, jenis_menu);
                stat.setString(4, cbTingkatVegetarian.getSelectedItem().toString());
                stat.setString(5, tJumlahOrang.getText());
                stat.setString(6, tMenuPaket.getText());
                stat.setString(7, tHargaPaket.getText());
                stat.setString(8, cbJumlahMenu.getSelectedItem().toString());
                stat.setString(9, cbTingkatPopularitas.getSelectedItem().toString());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "DATA Berhasil Disimpan");
                kosong();
                tNoId.requestFocus();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan Biodata"+e);
            }
    }
    
    private void editDataPaket(){
        try{
            String sql = "UPDATE data_paket set nama_paket=?, jenis_menu=?, tingkat_vegetarian=?, jumlah_orang=?, menu_paket=?, harga_paket=?, jumlah_menu=?, tingkat_popularitas=?  WHERE id_paket=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, tNamaPaket.getText());
            String jenis_menu = "";
                if(rbWesternFood.isSelected()){
                    jenis_menu="Western Food";
                }else if(rbIndianFood.isSelected()){
                    jenis_menu="Indian Food";
                }
            stat.setString(2, jenis_menu);
            stat.setString(3, cbTingkatVegetarian.getSelectedItem().toString());
            stat.setString(4, tJumlahOrang.getText());
            stat.setString(5, tMenuPaket.getText());
            stat.setString(6, tHargaPaket.getText());
            stat.setString(7, cbJumlahMenu.getSelectedItem().toString());
            stat.setString(8, cbTingkatPopularitas.getSelectedItem().toString());
            stat.setString(9, tNoId.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil diUbah ");
            kosong();
            tNoId.requestFocus();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Data Gagal DiUbah " + e);
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

        btnG = new javax.swing.ButtonGroup();
        PanelKandidat = new javax.swing.JPanel();
        tombolEdit = new javax.swing.JLabel();
        judul = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        tHargaPaket = new javax.swing.JTextField();
        cbJumlahMenu = new javax.swing.JComboBox<>();
        cbTingkatPopularitas = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tNamaPaket = new javax.swing.JTextField();
        tJumlahOrang = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tMenuPaket = new javax.swing.JTextArea();
        rbWesternFood = new javax.swing.JRadioButton();
        rbIndianFood = new javax.swing.JRadioButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tNoId = new javax.swing.JTextField();
        cbTingkatVegetarian = new javax.swing.JComboBox<>();
        tombolTambah = new javax.swing.JLabel();
        IsiKosong = new javax.swing.JOptionPane();
        Pane = new javax.swing.JPanel();

        PanelKandidat.setBackground(new java.awt.Color(255, 237, 192));

        tombolEdit.setBackground(new java.awt.Color(22, 65, 53));
        tombolEdit.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tombolEdit.setForeground(new java.awt.Color(255, 237, 192));
        tombolEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tombolEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Ubah Small.png"))); // NOI18N
        tombolEdit.setText("UBAH");
        tombolEdit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tombolEdit.setOpaque(true);
        tombolEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tombolEditMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tombolEditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tombolEditMouseExited(evt);
            }
        });

        judul.setBackground(new java.awt.Color(22, 65, 53));
        judul.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        judul.setForeground(new java.awt.Color(255, 237, 192));
        judul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        judul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Paket Small.png"))); // NOI18N
        judul.setText("Tambah Data Paket Makanan");
        judul.setOpaque(true);

        jPanel12.setBackground(new java.awt.Color(22, 65, 53));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Penilaian Bobot Paket Makanan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(255, 237, 192))); // NOI18N
        jPanel12.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 237, 192));
        jLabel24.setText("Harga Paket");

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 237, 192));
        jLabel25.setText("Jumlah Menu");

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 237, 192));
        jLabel26.setText("Tingkat Popularitas");

        tHargaPaket.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        cbJumlahMenu.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbJumlahMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Jumlah Menu -", "5 Menu", "4 Menu", "3 Menu" }));

        cbTingkatPopularitas.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbTingkatPopularitas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Tingkat Popularitas -", "Cukup Populer", "Tidak Populer", "Kurang Populer", "Sangat Populer" }));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addGap(23, 23, 23)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tHargaPaket)
                    .addComponent(cbJumlahMenu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbTingkatPopularitas, 0, 288, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(tHargaPaket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(cbJumlahMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(cbTingkatPopularitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(22, 65, 53));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Paket Makanan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(255, 237, 192))); // NOI18N
        jPanel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 237, 192));
        jLabel6.setText("Nama Paket");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 237, 192));
        jLabel7.setText("Jenis Masakan");

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 237, 192));
        jLabel21.setText("Menu Paket");

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 237, 192));
        jLabel22.setText("Jumlah Orang");

        tNamaPaket.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        tJumlahOrang.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        tMenuPaket.setColumns(20);
        tMenuPaket.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tMenuPaket.setRows(5);
        jScrollPane3.setViewportView(tMenuPaket);

        rbWesternFood.setBackground(new java.awt.Color(22, 65, 53));
        btnG.add(rbWesternFood);
        rbWesternFood.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        rbWesternFood.setForeground(new java.awt.Color(255, 237, 192));
        rbWesternFood.setText("Western Food");

        rbIndianFood.setBackground(new java.awt.Color(22, 65, 53));
        btnG.add(rbIndianFood);
        rbIndianFood.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        rbIndianFood.setForeground(new java.awt.Color(255, 237, 192));
        rbIndianFood.setText("Indian Food");

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 237, 192));
        jLabel28.setText("Tingkat Vegetarian");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 237, 192));
        jLabel9.setText("No Id");

        tNoId.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        cbTingkatVegetarian.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbTingkatVegetarian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Tingkat Vegetarian -", "Tinggi", "Sedang", "Rendah", "Tidak sama sekali" }));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel28)
                    .addComponent(jLabel9))
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tNoId)
                    .addComponent(tNamaPaket)
                    .addComponent(jScrollPane3)
                    .addComponent(tJumlahOrang)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(rbWesternFood)
                        .addGap(18, 18, 18)
                        .addComponent(rbIndianFood)
                        .addGap(0, 106, Short.MAX_VALUE))
                    .addComponent(cbTingkatVegetarian, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tNoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tNamaPaket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(rbWesternFood)
                    .addComponent(rbIndianFood))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(cbTingkatVegetarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tJumlahOrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        tombolTambah.setBackground(new java.awt.Color(22, 65, 53));
        tombolTambah.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tombolTambah.setForeground(new java.awt.Color(255, 237, 192));
        tombolTambah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tombolTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Tambah Small.png"))); // NOI18N
        tombolTambah.setText("TAMBAH");
        tombolTambah.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tombolTambah.setOpaque(true);
        tombolTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tombolTambahMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tombolTambahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tombolTambahMouseExited(evt);
            }
        });

        javax.swing.GroupLayout PanelKandidatLayout = new javax.swing.GroupLayout(PanelKandidat);
        PanelKandidat.setLayout(PanelKandidatLayout);
        PanelKandidatLayout.setHorizontalGroup(
            PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(judul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelKandidatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelKandidatLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(PanelKandidatLayout.createSequentialGroup()
                        .addComponent(tombolTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tombolEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        PanelKandidatLayout.setVerticalGroup(
            PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKandidatLayout.createSequentialGroup()
                .addComponent(judul, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tombolTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tombolEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelKandidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(202, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("dialog1"); // NOI18N

        Pane.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(888, 577));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tombolTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolTambahMouseClicked
        // TODO add your handling code here:
        if(!tNamaPaket.getText().equals("") && btnG.getSelection() != null && cbTingkatVegetarian.getSelectedIndex() != 0
                && !tMenuPaket.getText().equals("") && !tJumlahOrang.getText().equals("") && !tHargaPaket.getText().equals("") 
                && cbJumlahMenu.getSelectedIndex() != 0 && cbTingkatPopularitas.getSelectedIndex() != 0){    
            insertDataPaket();
            dispose();
        }else{
            IsiKosong.showMessageDialog(rootPane, "Mohon isi semua kolom isian pada form !", "Error", ERROR_MESSAGE );
        }
    }//GEN-LAST:event_tombolTambahMouseClicked

    private void tombolTambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolTambahMouseEntered
        // TODO add your handling code here:
        tombolTambah.setBackground(new Color(0,51,153));
        tombolTambah.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_tombolTambahMouseEntered

    private void tombolTambahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolTambahMouseExited
        // TODO add your handling code here:
        tombolTambah.setBackground(new Color(0,51,102));
    }//GEN-LAST:event_tombolTambahMouseExited

    private void tombolEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolEditMouseClicked
        // TODO add your handling code here:
        editDataPaket();
        dispose();
    }//GEN-LAST:event_tombolEditMouseClicked

    private void tombolEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolEditMouseEntered
        // TODO add your handling code here:
        tombolEdit.setBackground(new Color(0,51,153));
        tombolEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_tombolEditMouseEntered

    private void tombolEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolEditMouseExited
        // TODO add your handling code here:
        tombolEdit.setBackground(new Color(0,51,102));
    }//GEN-LAST:event_tombolEditMouseExited

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
            java.util.logging.Logger.getLogger(DialogTambahData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogTambahData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogTambahData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogTambahData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogTambahData dialog = new DialogTambahData(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JOptionPane IsiKosong;
    private javax.swing.JPanel Pane;
    private javax.swing.JPanel PanelKandidat;
    private javax.swing.ButtonGroup btnG;
    private javax.swing.JComboBox<String> cbJumlahMenu;
    private javax.swing.JComboBox<String> cbTingkatPopularitas;
    private javax.swing.JComboBox<String> cbTingkatVegetarian;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel judul;
    private javax.swing.JRadioButton rbIndianFood;
    private javax.swing.JRadioButton rbWesternFood;
    private javax.swing.JTextField tHargaPaket;
    private javax.swing.JTextField tJumlahOrang;
    private javax.swing.JTextArea tMenuPaket;
    private javax.swing.JTextField tNamaPaket;
    private javax.swing.JTextField tNoId;
    private javax.swing.JLabel tombolEdit;
    private javax.swing.JLabel tombolTambah;
    // End of variables declaration//GEN-END:variables

    void show(JRootPane rootPane) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
