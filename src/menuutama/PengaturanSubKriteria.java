
package menuutama;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;

/**
 *
 * @author Neubri
 */
public class PengaturanSubKriteria extends javax.swing.JPanel {
    private Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    /**
     * Creates new form Pengaturan
     */
    public PengaturanSubKriteria() {
        initComponents();
        updateDataTabel();
    }
    
    protected void kosong(){
        cbSubTingkatVegetarian1.setSelectedIndex(0);
        cbSubTingkatVegetarian2.setSelectedIndex(0);
        cbSubTingkatVegetarian3.setSelectedIndex(0);
        cbSubTingkatVegetarian4.setSelectedIndex(0);
        cbSubHargaPaket1.setSelectedIndex(0);
        cbSubHargaPaket2.setSelectedIndex(0);
        cbSubHargaPaket3.setSelectedIndex(0);
        cbSubJumlahMenu1.setSelectedIndex(0);
        cbSubJumlahMenu2.setSelectedIndex(0);
        cbSubJumlahMenu3.setSelectedIndex(0);
        cbSubTingkatPopularitas1.setSelectedIndex(0);
        cbSubTingkatPopularitas2.setSelectedIndex(0);
        cbSubTingkatPopularitas3.setSelectedIndex(0);
        cbSubTingkatPopularitas4.setSelectedIndex(0);
    }
    
    protected void updateDataTabel(){
        Object[] Baris = {
            "Kode Kriteria",
            "Nama Kriteria",
            "Nama SubKriteria",
            "Kepentingan SubKriteria"
        };
        tabmode = new DefaultTableModel(null, Baris);
        tabelSubKriteria.setModel(tabmode);
        String sql = "SELECT * FROM sub_kriteria ORDER BY kd_kriteria, no_sub";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("kd_kriteria");
                String b = hasil.getString("nama_kriteria");
                String c = hasil.getString("nama_sub_kriteria");
                String d = hasil.getString("prioritas_kepentingan");
                
                String[] data={a, b, c, d};
                tabmode.addRow(data);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    protected void getDataTabel(){
        String sql = "SELECT nama_sub_kriteria FROM sub_kriteria ORDER BY kd_kriteria, no_sub";
        int n = 1;
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("nama_sub_kriteria");
                if(n==1){
                    cbSubTingkatVegetarian1.setSelectedItem(a);
                }else if(n==2){
                    cbSubTingkatVegetarian2.setSelectedItem(a);
                }else if(n==3){
                    cbSubTingkatVegetarian3.setSelectedItem(a);
                }else if(n==4){
                    cbSubTingkatVegetarian4.setSelectedItem(a);
                }else if(n==5){
                    cbSubHargaPaket1.setSelectedItem(a);
                }else if(n==6){
                    cbSubHargaPaket2.setSelectedItem(a);
                }else if(n==7){
                    cbSubHargaPaket3.setSelectedItem(a);
                }else if(n==8){
                    cbSubJumlahMenu1.setSelectedItem(a);
                }else if(n==9){
                    cbSubJumlahMenu2.setSelectedItem(a);
                }else if(n==10){
                    cbSubJumlahMenu3.setSelectedItem(a);
                }else if(n==11){
                    cbSubTingkatPopularitas1.setSelectedItem(a);
                }else if(n==12){
                    cbSubTingkatPopularitas2.setSelectedItem(a);
                }else if(n==13){
                    cbSubTingkatPopularitas3.setSelectedItem(a);
                }else if(n==14){
                    cbSubTingkatPopularitas4.setSelectedItem(a);
                } 
                n++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    //masukan data subkriteria
    protected void insertDataSubKriteria(){
        try{
        int n=1, nTingkatVegetarian=1, nHargaPaket=1, nJumlahMenu=1, nTingkatPopularitas=1, i=1;
            do{
                String kepentingan;
                String sql = "INSERT INTO sub_kriteria VALUES (?,?,?,?,?)";
                PreparedStatement stat = conn.prepareStatement(sql);
                java.sql.Statement statCek = conn.createStatement();
                String sqlSub = "SELECT kd_kriteria, nama_kriteria FROM kriteria";
                String sqlTingkatVegetarian = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Tingkat Vegetarian'";
                String sqlHargaPaket = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Harga Paket'";
                String sqlJumlahMenu = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Jumlah Menu'";
                String sqlTingkatPopularitas = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Tingkat Popularitas'";
                ResultSet hasil = statCek.executeQuery(sqlSub);
                if(n==1){
                    hasil = statCek.executeQuery(sqlTingkatVegetarian);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nTingkatVegetarian == 1){
                        stat.setString(4, cbSubTingkatVegetarian1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nTingkatVegetarian == 2){
                        stat.setString(4, cbSubTingkatVegetarian2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nTingkatVegetarian == 3){
                        stat.setString(4, cbSubTingkatVegetarian3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else{
                        stat.setString(4, cbSubTingkatVegetarian4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nTingkatVegetarian++;
                }else if(n==2){
                    hasil = statCek.executeQuery(sqlHargaPaket);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nHargaPaket == 1){
                        stat.setString(4, cbSubHargaPaket1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nHargaPaket == 2){
                        stat.setString(4, cbSubHargaPaket2.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-2";
                    }else{
                        stat.setString(4, cbSubHargaPaket3.getSelectedItem().toString());
                        kepentingan="Biasa ke-3";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nHargaPaket++;
                }else if(n==3){
                    hasil = statCek.executeQuery(sqlJumlahMenu);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nJumlahMenu == 1){
                        stat.setString(4, cbSubJumlahMenu1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nJumlahMenu == 2){
                        stat.setString(4, cbSubJumlahMenu2.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-2";
                    }else{
                        stat.setString(4, cbSubJumlahMenu3.getSelectedItem().toString());
                        kepentingan="Biasa ke-3";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nJumlahMenu++;
                }else{    
                    hasil = statCek.executeQuery(sqlTingkatPopularitas);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nTingkatPopularitas == 1){
                        stat.setString(4, cbSubTingkatPopularitas1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nTingkatPopularitas == 2){
                        stat.setString(4, cbSubTingkatPopularitas2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nTingkatPopularitas == 3){
                        stat.setString(4, cbSubTingkatPopularitas3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else{
                        stat.setString(4, cbSubTingkatPopularitas4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nTingkatPopularitas++;
                }
            }while(n<=4);   
            JOptionPane.showMessageDialog(null, "DATA Berhasil Disimpan");
            kosong();
            updateDataTabel();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
        }
    }
    
    protected void hapusDataSubKriteria(){
        int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog",JOptionPane.YES_NO_OPTION);
        if(ok == 0){
            String sql = "DELETE FROM sub_kriteria";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil diHapus ");
                kosong();
                updateDataTabel();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Data Gagal diHapus " + e);
            }
        }
    }
    
    protected void editDataSubKriteria(){
        try{
        int n=1, nTingkatVegetarian=1, nHargaPaket=1, nJumlahMenu=1, nTingkatPopularitas=1, i=1;
            do{
                String kepentingan;
                String sql = "UPDATE sub_kriteria set kd_kriteria=?, nama_kriteria=?, nama_sub_kriteria=?, prioritas_kepentingan=? WHERE no_sub=?";
                PreparedStatement stat = conn.prepareStatement(sql);
                java.sql.Statement statCek = conn.createStatement();
                String sqlSub = "SELECT kd_kriteria, nama_kriteria FROM kriteria";
                String sqlTingkatVegetarian = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Tingkat Vegetarian'";
                String sqlHargaPaket = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Harga Paket'";
                String sqlJumlahMenu = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Jumlah Menu'";
                String sqlTingkatPopularitas = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Tingkat Popularitas'";
                ResultSet hasil = statCek.executeQuery(sqlSub);
                if(n==1){
                    hasil = statCek.executeQuery(sqlTingkatVegetarian);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nTingkatVegetarian == 1){
                        stat.setString(3, cbSubTingkatVegetarian1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nTingkatVegetarian == 2){
                        stat.setString(3, cbSubTingkatVegetarian2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nTingkatVegetarian == 3){
                        stat.setString(3, cbSubTingkatVegetarian3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else{
                        stat.setString(3, cbSubTingkatVegetarian4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nTingkatVegetarian++;
                }else if(n==2){
                    hasil = statCek.executeQuery(sqlHargaPaket);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nHargaPaket == 1){
                        stat.setString(3, cbSubHargaPaket1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nHargaPaket == 2){
                        stat.setString(3, cbSubHargaPaket2.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-2";
                    }else{
                        stat.setString(3, cbSubHargaPaket3.getSelectedItem().toString());
                        kepentingan="Biasa ke-3";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nHargaPaket++;
                }else if(n==3){
                    hasil = statCek.executeQuery(sqlJumlahMenu);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nJumlahMenu == 1){
                        stat.setString(3, cbSubJumlahMenu1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nJumlahMenu == 2){
                        stat.setString(3, cbSubJumlahMenu2.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-2";
                    }else{
                        stat.setString(3, cbSubJumlahMenu3.getSelectedItem().toString());
                        kepentingan="Biasa ke-3";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nJumlahMenu++;
                }else{    
                    hasil = statCek.executeQuery(sqlTingkatPopularitas);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nTingkatPopularitas == 1){
                        stat.setString(3, cbSubTingkatPopularitas1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nTingkatPopularitas == 2){
                        stat.setString(3, cbSubTingkatPopularitas2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nTingkatPopularitas == 3){
                        stat.setString(3, cbSubTingkatPopularitas3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else{
                        stat.setString(3, cbSubTingkatPopularitas4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nTingkatPopularitas++;
                }
            }while(n<=4);   
            JOptionPane.showMessageDialog(null, "DATA Berhasil DiUbah");
            kosong();
            updateDataTabel();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal DiUbah "+e);
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

        judul = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelSubKriteria = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cbSubTingkatPopularitas4 = new javax.swing.JComboBox<>();
        cbSubTingkatPopularitas3 = new javax.swing.JComboBox<>();
        cbSubTingkatPopularitas2 = new javax.swing.JComboBox<>();
        cbSubTingkatPopularitas1 = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cbSubTingkatVegetarian4 = new javax.swing.JComboBox<>();
        cbSubTingkatVegetarian3 = new javax.swing.JComboBox<>();
        cbSubTingkatVegetarian2 = new javax.swing.JComboBox<>();
        cbSubTingkatVegetarian1 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cbSubHargaPaket3 = new javax.swing.JComboBox<>();
        cbSubHargaPaket2 = new javax.swing.JComboBox<>();
        cbSubHargaPaket1 = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        cbSubJumlahMenu3 = new javax.swing.JComboBox<>();
        cbSubJumlahMenu2 = new javax.swing.JComboBox<>();
        cbSubJumlahMenu1 = new javax.swing.JComboBox<>();
        tombolSimpan = new javax.swing.JButton();
        tombolEdit = new javax.swing.JButton();
        tombolHapus = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 237, 192));
        setPreferredSize(new java.awt.Dimension(990, 700));

        judul.setBackground(new java.awt.Color(22, 65, 53));
        judul.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        judul.setForeground(new java.awt.Color(255, 237, 192));
        judul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        judul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Subkriteria Small.png"))); // NOI18N
        judul.setText("  Pengaturan Bobot Kepentingan Subkriteria");
        judul.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 237, 192));
        jPanel1.setPreferredSize(new java.awt.Dimension(990, 620));

        tabelSubKriteria.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tabelSubKriteria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode Kriteria", "Nama Kriteria", "Nama SubKriteria", "Kepentingan SubKriteria"
            }
        ));
        tabelSubKriteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelSubKriteriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelSubKriteria);

        jPanel3.setBackground(new java.awt.Color(22, 65, 53));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kepentingan Tingkat Popularitas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(255, 237, 192))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(255, 237, 192));
        jPanel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jPanel3.setName("Tingkat Popularitas"); // NOI18N

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 237, 192));
        jLabel17.setText("Popularitas Yang Sangat Penting ke-1");

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 237, 192));
        jLabel18.setText("Popularitas Penting ke-2");

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 237, 192));
        jLabel19.setText("Popularitas Cukup Penting ke-3");

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 237, 192));
        jLabel20.setText("Popularitas biasa ke-4");

        cbSubTingkatPopularitas4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih SubKriteria Popularitas -", "Cukup Populer", "Tidak Populer", "Kurang Populer", "Sangat Populer" }));

        cbSubTingkatPopularitas3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih SubKriteria Popularitas -", "Cukup Populer", "Tidak Populer", "Kurang Populer", "Sangat Populer" }));

        cbSubTingkatPopularitas2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih SubKriteria Popularitas -", "Cukup Populer", "Tidak Populer", "Kurang Populer", "Sangat Populer" }));

        cbSubTingkatPopularitas1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih SubKriteria Popularitas -", "Cukup Populer", "Tidak Populer", "Kurang Populer", "Sangat Populer" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbSubTingkatPopularitas4, 0, 197, Short.MAX_VALUE)
                            .addComponent(cbSubTingkatPopularitas3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbSubTingkatPopularitas2, 0, 197, Short.MAX_VALUE)
                            .addComponent(cbSubTingkatPopularitas1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cbSubTingkatPopularitas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cbSubTingkatPopularitas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cbSubTingkatPopularitas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cbSubTingkatPopularitas4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(22, 65, 53));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kepentingan Tingkat Vegetarian", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(255, 237, 192))); // NOI18N

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 237, 192));
        jLabel21.setText("Vegetarian Yang Sangat Penting ke-1");

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 237, 192));
        jLabel22.setText("Vegetarian Penting ke-2");

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 237, 192));
        jLabel23.setText("Vegetarian Cukup Penting ke-3");

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 237, 192));
        jLabel24.setText("Vegetarian biasa ke-4");

        cbSubTingkatVegetarian4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- SubKriteria Tingkat Vegetarian -", "Tinggi", "Sedang", "Rendah", "Tidak sama sekali" }));

        cbSubTingkatVegetarian3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- SubKriteria Tingkat Vegetarian -", "Tinggi", "Sedang", "Rendah", "Tidak sama sekali" }));

        cbSubTingkatVegetarian2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- SubKriteria Tingkat Vegetarian -", "Tinggi", "Sedang", "Rendah", "Tidak sama sekali" }));

        cbSubTingkatVegetarian1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- SubKriteria Tingkat Vegetarian -", "Tinggi", "Sedang", "Rendah", "Tidak sama sekali" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbSubTingkatVegetarian1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 196, Short.MAX_VALUE)
                    .addComponent(cbSubTingkatVegetarian2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubTingkatVegetarian3, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubTingkatVegetarian4, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cbSubTingkatVegetarian1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbSubTingkatVegetarian2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(cbSubTingkatVegetarian3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cbSubTingkatVegetarian4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel4.setBackground(new java.awt.Color(22, 65, 53));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kepentingan Bobot Harga Paket", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(255, 237, 192))); // NOI18N

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 237, 192));
        jLabel9.setText("Bobot Yang Sangat Penting ke-1");

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 237, 192));
        jLabel15.setText("Bobot Cukup Penting ke-2");

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 237, 192));
        jLabel16.setText("Bobot biasa ke-3");

        cbSubHargaPaket3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih SubKriteria Harga Paket -", "Lebih dari 8 Juta", "Lebih dari 6 Juta", "Kurang atau sama dengan 6 Juta" }));

        cbSubHargaPaket2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih SubKriteria Harga Paket -", "Lebih dari 8 Juta", "Lebih dari 6 Juta", "Kurang atau sama dengan 6 Juta" }));

        cbSubHargaPaket1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih SubKriteria Harga Paket -", "Lebih dari 8 Juta", "Lebih dari 6 Juta", "Kurang atau sama dengan 6 Juta" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbSubHargaPaket2, javax.swing.GroupLayout.Alignment.LEADING, 0, 195, Short.MAX_VALUE)
                    .addComponent(cbSubHargaPaket3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubHargaPaket1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbSubHargaPaket1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(cbSubHargaPaket2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(cbSubHargaPaket3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel6.setBackground(new java.awt.Color(22, 65, 53));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kepentingan Jumlah Menu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(255, 237, 192))); // NOI18N
        jPanel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 237, 192));
        jLabel10.setText("Jumlah Menu Yang Sangat Penting ke-1");

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 237, 192));
        jLabel25.setText("Jumlah Menu Cukup Penting ke-2");

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 237, 192));
        jLabel26.setText("Jumlah Menu biasa ke-3");

        cbSubJumlahMenu3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih SubKriteria Jumlah Menu -", "5 Menu", "4 Menu", "3 Menu" }));

        cbSubJumlahMenu2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih SubKriteria Jumlah Menu -", "5 Menu", "4 Menu", "3 Menu" }));

        cbSubJumlahMenu1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih SubKriteria Jumlah Menu -", "5 Menu", "4 Menu", "3 Menu" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbSubJumlahMenu3, 0, 184, Short.MAX_VALUE)
                    .addComponent(cbSubJumlahMenu2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubJumlahMenu1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbSubJumlahMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSubJumlahMenu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSubJumlahMenu3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)))
        );

        tombolSimpan.setBackground(new java.awt.Color(22, 65, 53));
        tombolSimpan.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tombolSimpan.setForeground(new java.awt.Color(255, 237, 192));
        tombolSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Simpan Small.png"))); // NOI18N
        tombolSimpan.setText("SIMPAN");
        tombolSimpan.setBorder(null);
        tombolSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolSimpanActionPerformed(evt);
            }
        });

        tombolEdit.setBackground(new java.awt.Color(22, 65, 53));
        tombolEdit.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tombolEdit.setForeground(new java.awt.Color(255, 237, 192));
        tombolEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Ubah Small.png"))); // NOI18N
        tombolEdit.setText("UBAH");
        tombolEdit.setBorder(null);
        tombolEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolEditActionPerformed(evt);
            }
        });

        tombolHapus.setBackground(new java.awt.Color(22, 65, 53));
        tombolHapus.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tombolHapus.setForeground(new java.awt.Color(255, 237, 192));
        tombolHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Hapus Small.png"))); // NOI18N
        tombolHapus.setText("HAPUS");
        tombolHapus.setBorder(null);
        tombolHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolHapusActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Catatan : Edit data, klik data pada tabel terlebih dahulu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(tombolSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tombolEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tombolHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tombolSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tombolEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tombolHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(184, Short.MAX_VALUE))
        );

        jPanel3.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(judul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(judul, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tombolSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolSimpanActionPerformed
        insertDataSubKriteria();
    }//GEN-LAST:event_tombolSimpanActionPerformed

    private void tombolEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolEditActionPerformed
        editDataSubKriteria();
    }//GEN-LAST:event_tombolEditActionPerformed

    private void tombolHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolHapusActionPerformed
        hapusDataSubKriteria();
    }//GEN-LAST:event_tombolHapusActionPerformed

    private void tabelSubKriteriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelSubKriteriaMouseClicked
        getDataTabel();
    }//GEN-LAST:event_tabelSubKriteriaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbSubHargaPaket1;
    private javax.swing.JComboBox<String> cbSubHargaPaket2;
    private javax.swing.JComboBox<String> cbSubHargaPaket3;
    private javax.swing.JComboBox<String> cbSubJumlahMenu1;
    private javax.swing.JComboBox<String> cbSubJumlahMenu2;
    private javax.swing.JComboBox<String> cbSubJumlahMenu3;
    private javax.swing.JComboBox<String> cbSubTingkatPopularitas1;
    private javax.swing.JComboBox<String> cbSubTingkatPopularitas2;
    private javax.swing.JComboBox<String> cbSubTingkatPopularitas3;
    private javax.swing.JComboBox<String> cbSubTingkatPopularitas4;
    private javax.swing.JComboBox<String> cbSubTingkatVegetarian1;
    private javax.swing.JComboBox<String> cbSubTingkatVegetarian2;
    private javax.swing.JComboBox<String> cbSubTingkatVegetarian3;
    private javax.swing.JComboBox<String> cbSubTingkatVegetarian4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel judul;
    private javax.swing.JTable tabelSubKriteria;
    private javax.swing.JButton tombolEdit;
    private javax.swing.JButton tombolHapus;
    private javax.swing.JButton tombolSimpan;
    // End of variables declaration//GEN-END:variables
}
