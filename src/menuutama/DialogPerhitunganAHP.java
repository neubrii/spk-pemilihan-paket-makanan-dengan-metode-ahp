
package menuutama;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
public class DialogPerhitunganAHP extends javax.swing.JDialog {
    private Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    protected KriteriaAhp kriteria = new KriteriaAhp();
    protected SubKriteriaAhp SubK = new SubKriteriaAhp();
    DecimalFormat df = new DecimalFormat("#.##");
    ArrayList<String> K = new ArrayList<String>();
    ArrayList<Double> KS4x4 = new ArrayList<Double>();
    ArrayList<Double> KS3x3 = new ArrayList<Double>();
    String noIdAlternatif, namaPaketAlternatif, jumlahOrangAlternatif, tingkatVegetarianAlternatif, jumlahMenuAlternatif, tingkatPopularitasAlternatif;
    int hargaPaketAlternatif;
    double nilaiAlternatif, totalNilai;
    
    /**
     * Creates new form DialogTambahData
     */
    public DialogPerhitunganAHP(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getRelasiId();
        
        //add Panel, add panel(sebuah panel)
        Pane.add(PanelPerhitungan);
        Pane.repaint();
        Pane.revalidate();
    }
    
    void kosong(){
        TotalNilai.setText("");
    }
    
    //nilai matriks berpasangan kriteria
    public void getMatriksK(){
        k1k1.setText(df.format(kriteria.matriksBerpasangan[0][0]));
        k1k2.setText(df.format(kriteria.matriksBerpasangan[0][1]));
        k1k3.setText(df.format(kriteria.matriksBerpasangan[0][2]));
        k1k4.setText(df.format(kriteria.matriksBerpasangan[0][3]));
        k2k1.setText(df.format(kriteria.matriksBerpasangan[1][0]));
        k2k2.setText(df.format(kriteria.matriksBerpasangan[1][1]));
        k2k3.setText(df.format(kriteria.matriksBerpasangan[1][2]));
        k2k4.setText(df.format(kriteria.matriksBerpasangan[1][3]));
        k3k1.setText(df.format(kriteria.matriksBerpasangan[2][0]));
        k3k2.setText(df.format(kriteria.matriksBerpasangan[2][1]));
        k3k3.setText(df.format(kriteria.matriksBerpasangan[2][2]));
        k3k4.setText(df.format(kriteria.matriksBerpasangan[2][3]));
        k4k1.setText(df.format(kriteria.matriksBerpasangan[3][0]));
        k4k2.setText(df.format(kriteria.matriksBerpasangan[3][1]));
        k4k3.setText(df.format(kriteria.matriksBerpasangan[3][2]));
        k4k4.setText(df.format(kriteria.matriksBerpasangan[3][3]));
    }
    
    //nilai matriks berpasangan kriteria
    public void getMatriksNorK(){
        k1k1N.setText(df.format(kriteria.matriksNormalisasi[0][0]));
        k1k2N.setText(df.format(kriteria.matriksNormalisasi[0][1]));
        k1k3N.setText(df.format(kriteria.matriksNormalisasi[0][2]));
        k1k4N.setText(df.format(kriteria.matriksNormalisasi[0][3]));
        k2k1N.setText(df.format(kriteria.matriksNormalisasi[1][0]));
        k2k2N.setText(df.format(kriteria.matriksNormalisasi[1][1]));
        k2k3N.setText(df.format(kriteria.matriksNormalisasi[1][2]));
        k2k4N.setText(df.format(kriteria.matriksNormalisasi[1][3]));
        k3k1N.setText(df.format(kriteria.matriksNormalisasi[2][0]));
        k3k2N.setText(df.format(kriteria.matriksNormalisasi[2][1]));
        k3k3N.setText(df.format(kriteria.matriksNormalisasi[2][2]));
        k3k4N.setText(df.format(kriteria.matriksNormalisasi[2][3]));
        k4k1N.setText(df.format(kriteria.matriksNormalisasi[3][0]));
        k4k2N.setText(df.format(kriteria.matriksNormalisasi[3][1]));
        k4k3N.setText(df.format(kriteria.matriksNormalisasi[3][2]));
        k4k4N.setText(df.format(kriteria.matriksNormalisasi[3][3]));
        Prior1.setText(df.format(kriteria.prioritas[0]));
        Prior2.setText(df.format(kriteria.prioritas[1]));
        Prior3.setText(df.format(kriteria.prioritas[2]));
        Prior4.setText(df.format(kriteria.prioritas[3]));
        
    }
    //nilai prioritas untuk sub-kriteria dari kriteria yang tersedia
    public void getPrioritasSub(){
        getKriteria();
        if(K.get(0).equals("Tingkat Vegetarian") || K.get(0).equals("Tingkat Popularitas")){
            PriorS11.setText(df.format(SubK.prioritasSub4x4[0]));
            PriorS12.setText(df.format(SubK.prioritasSub4x4[1]));
            PriorS13.setText(df.format(SubK.prioritasSub4x4[2]));
            PriorS14.setText(df.format(SubK.prioritasSub4x4[3]));
        }
        if(K.get(1).equals("Tingkat Vegetarian") || K.get(1).equals("Tingkat Popularitas")){
            PriorS21.setText(df.format(SubK.prioritasSub4x4[0]));
            PriorS22.setText(df.format(SubK.prioritasSub4x4[1]));
            PriorS23.setText(df.format(SubK.prioritasSub4x4[2]));
            PriorS24.setText(df.format(SubK.prioritasSub4x4[3]));
        }
        if(K.get(2).equals("Tingkat Vegetarian") || K.get(2).equals("Tingkat Popularitas")){
            PriorS31.setText(df.format(SubK.prioritasSub4x4[0]));
            PriorS32.setText(df.format(SubK.prioritasSub4x4[1]));
            PriorS33.setText(df.format(SubK.prioritasSub4x4[2]));
            PriorS34.setText(df.format(SubK.prioritasSub4x4[3]));
        }
        if(K.get(3).equals("Tingkat Vegetarian") || K.get(3).equals("Tingkat Popularitas")){
            PriorS41.setText(df.format(SubK.prioritasSub4x4[0]));
            PriorS42.setText(df.format(SubK.prioritasSub4x4[1]));
            PriorS43.setText(df.format(SubK.prioritasSub4x4[2]));
            PriorS44.setText(df.format(SubK.prioritasSub4x4[3]));
        }
        if(K.get(0).equals("Jumlah Menu") || K.get(0).equals("Harga Paket")){
            PriorS11.setText(df.format(SubK.prioritasSub3x3[0]));
            PriorS12.setText(df.format(SubK.prioritasSub3x3[1]));
            PriorS13.setText(df.format(SubK.prioritasSub3x3[2]));
        }
        if(K.get(1).equals("Jumlah Menu") || K.get(1).equals("Harga Paket")){
            PriorS21.setText(df.format(SubK.prioritasSub3x3[0]));
            PriorS22.setText(df.format(SubK.prioritasSub3x3[1]));
            PriorS23.setText(df.format(SubK.prioritasSub3x3[2]));
        }
        if(K.get(2).equals("Jumlah Menu") || K.get(2).equals("Harga Paket")){
            PriorS31.setText(df.format(SubK.prioritasSub3x3[0]));
            PriorS32.setText(df.format(SubK.prioritasSub3x3[1]));
            PriorS33.setText(df.format(SubK.prioritasSub3x3[2]));
        }
        if(K.get(3).equals("Jumlah Menu") || K.get(3).equals("Harga Paket")){
            PriorS41.setText(df.format(SubK.prioritasSub3x3[0]));
            PriorS42.setText(df.format(SubK.prioritasSub3x3[1]));
            PriorS43.setText(df.format(SubK.prioritasSub3x3[2]));
        }
        
    }
    //menentukan kriteria pada kode K1, K2, K3, K4
     public void getKriteria(){
        String sql = "SELECT nama_kriteria FROM kriteria ORDER BY kd_kriteria";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("nama_kriteria");
                K.add(a);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    
     
    //Mendapatkan alternatif dari data paket yang ada
    public void getAlternatif(){
        String sql = "SELECT DISTINCT * FROM data_paket WHERE id_paket='"+cbIdPaket.getSelectedItem().toString()+"'";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id_paket");
                String b = hasil.getString("nama_paket");
                String c = hasil.getString("jumlah_orang");
                String d = hasil.getString("tingkat_vegetarian");
                String e = hasil.getString("harga_paket");
                String f = hasil.getString("jumlah_menu");
                String g = hasil.getString("tingkat_popularitas");
                noIdAlternatif = a;
                namaPaketAlternatif = b;
                jumlahOrangAlternatif = c;
                tingkatVegetarianAlternatif = d;
                hargaPaketAlternatif = Integer.parseInt(e);
                jumlahMenuAlternatif = f;
                tingkatPopularitasAlternatif = g;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    } 
    //melakukan perhitungan dari alternatif yang dipilih
    //untuk mendapatkan hasil penilaian
    public void getPenilaian(){
        //perhitungan untuk tingkat popularitas
        if(tingkatPopularitasAlternatif.equals("Sangat Populer")){
            nilaiAlternatif = SubK.prioritasSub4x4[0] * kriteria.prioritas[0];
            totalNilai += nilaiAlternatif;
        }else if(tingkatPopularitasAlternatif.equals("Cukup Populer")){
            nilaiAlternatif = SubK.prioritasSub4x4[1] * kriteria.prioritas[0];
            totalNilai += nilaiAlternatif;
        }else if(tingkatPopularitasAlternatif.equals("Kurang Populer")){
            nilaiAlternatif = SubK.prioritasSub4x4[2] * kriteria.prioritas[0];
            totalNilai += nilaiAlternatif;
        }else{
            nilaiAlternatif = SubK.prioritasSub4x4[3] * kriteria.prioritas[0];
            totalNilai += nilaiAlternatif;
        }
        //perhitungan untuk jumlah menu
        if(jumlahMenuAlternatif.equals("5 Menu")){
            nilaiAlternatif = SubK.prioritasSub3x3[0] * kriteria.prioritas[1];
            totalNilai += nilaiAlternatif;
        }else if(jumlahMenuAlternatif.equals("4 Menu")){
            nilaiAlternatif = SubK.prioritasSub3x3[1] * kriteria.prioritas[1];
            totalNilai += nilaiAlternatif;
        }else{
            nilaiAlternatif = SubK.prioritasSub3x3[2] * kriteria.prioritas[1];
            totalNilai += nilaiAlternatif;
        }
        //perhitungan untuk harga paket
        if(hargaPaketAlternatif>0 && hargaPaketAlternatif<=60){
            nilaiAlternatif = SubK.prioritasSub3x3[2] * kriteria.prioritas[2];
            totalNilai += nilaiAlternatif;
        }else if(hargaPaketAlternatif>60 && hargaPaketAlternatif<81){
            nilaiAlternatif = SubK.prioritasSub3x3[1] * kriteria.prioritas[2];
            totalNilai += nilaiAlternatif;
        }else{
            nilaiAlternatif = SubK.prioritasSub3x3[0] * kriteria.prioritas[2];
            totalNilai += nilaiAlternatif;
        }
        //perhitungan untuk tingkat vegetarian
        if(tingkatVegetarianAlternatif.equals("Tinggi")){
            nilaiAlternatif = SubK.prioritasSub4x4[0] * kriteria.prioritas[3];
            totalNilai += nilaiAlternatif;
        }else if(tingkatVegetarianAlternatif.equals("Sedang")){
            nilaiAlternatif = SubK.prioritasSub4x4[1] * kriteria.prioritas[3];
            totalNilai += nilaiAlternatif;
        }else if(tingkatVegetarianAlternatif.equals("Rendah")){
            nilaiAlternatif = SubK.prioritasSub4x4[2] * kriteria.prioritas[3];
            totalNilai += nilaiAlternatif;
        }else{
            nilaiAlternatif = SubK.prioritasSub4x4[3] * kriteria.prioritas[3];
            totalNilai += nilaiAlternatif;
        }
        TotalNilai.setText(Double.toString(totalNilai));
    }
    
    //Mendapatkan relasi pada combobox pada database data paket
    public void getRelasiId(){
        String sql = "SELECT DISTINCT id_paket, nama_paket FROM data_paket ORDER BY id_paket";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id_paket");
                cbIdPaket.addItem(a);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
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
        PanelPerhitungan = new javax.swing.JPanel();
        judul = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        mulaiHitung = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        k1k1 = new javax.swing.JTextField();
        k1k2 = new javax.swing.JTextField();
        k1k3 = new javax.swing.JTextField();
        k1k4 = new javax.swing.JTextField();
        k2k1 = new javax.swing.JTextField();
        k2k2 = new javax.swing.JTextField();
        k2k3 = new javax.swing.JTextField();
        k2k4 = new javax.swing.JTextField();
        k3k1 = new javax.swing.JTextField();
        k3k2 = new javax.swing.JTextField();
        k3k3 = new javax.swing.JTextField();
        k3k4 = new javax.swing.JTextField();
        k4k1 = new javax.swing.JTextField();
        k4k2 = new javax.swing.JTextField();
        k4k3 = new javax.swing.JTextField();
        k4k4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        k1k1N = new javax.swing.JTextField();
        k1k2N = new javax.swing.JTextField();
        k1k3N = new javax.swing.JTextField();
        k1k4N = new javax.swing.JTextField();
        k2k1N = new javax.swing.JTextField();
        k2k2N = new javax.swing.JTextField();
        k2k3N = new javax.swing.JTextField();
        k2k4N = new javax.swing.JTextField();
        k3k1N = new javax.swing.JTextField();
        k3k2N = new javax.swing.JTextField();
        k3k3N = new javax.swing.JTextField();
        k3k4N = new javax.swing.JTextField();
        k4k1N = new javax.swing.JTextField();
        k4k2N = new javax.swing.JTextField();
        k4k3N = new javax.swing.JTextField();
        k4k4N = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        Prior1 = new javax.swing.JTextField();
        Prior2 = new javax.swing.JTextField();
        Prior3 = new javax.swing.JTextField();
        Prior4 = new javax.swing.JTextField();
        Simpan = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        PriorS11 = new javax.swing.JTextField();
        PriorS12 = new javax.swing.JTextField();
        PriorS13 = new javax.swing.JTextField();
        PriorS14 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        PriorS21 = new javax.swing.JTextField();
        PriorS22 = new javax.swing.JTextField();
        PriorS23 = new javax.swing.JTextField();
        PriorS24 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        PriorS31 = new javax.swing.JTextField();
        PriorS32 = new javax.swing.JTextField();
        PriorS33 = new javax.swing.JTextField();
        PriorS34 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        PriorS41 = new javax.swing.JTextField();
        PriorS42 = new javax.swing.JTextField();
        PriorS43 = new javax.swing.JTextField();
        PriorS44 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        namaPaket = new javax.swing.JTextField();
        TotalNilai = new javax.swing.JTextField();
        cbIdPaket = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        Pane = new javax.swing.JPanel();

        PanelPerhitungan.setBackground(new java.awt.Color(255, 237, 192));

        judul.setBackground(new java.awt.Color(22, 65, 53));
        judul.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        judul.setForeground(new java.awt.Color(255, 237, 192));
        judul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        judul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Hitung Small.png"))); // NOI18N
        judul.setText("Hitung Hasil Penilaian Paket Makanan");
        judul.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 237, 192));

        mulaiHitung.setBackground(new java.awt.Color(22, 65, 53));
        mulaiHitung.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        mulaiHitung.setForeground(new java.awt.Color(255, 255, 255));
        mulaiHitung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Hitung Small.png"))); // NOI18N
        mulaiHitung.setText("HITUNG");
        mulaiHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mulaiHitungActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(22, 65, 53));
        jPanel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 237, 192));
        jLabel2.setText("Matriks Perbandingan Kriteria");

        jLabel3.setForeground(new java.awt.Color(255, 237, 192));
        jLabel3.setText("K1");

        jLabel4.setForeground(new java.awt.Color(255, 237, 192));
        jLabel4.setText("K2");

        jLabel5.setForeground(new java.awt.Color(255, 237, 192));
        jLabel5.setText("K3");

        jLabel6.setForeground(new java.awt.Color(255, 237, 192));
        jLabel6.setText("K4");

        k1k1.setEditable(false);
        k1k1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k1k2.setEditable(false);
        k1k2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k1k3.setEditable(false);
        k1k3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k1k4.setEditable(false);
        k1k4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k2k1.setEditable(false);
        k2k1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k2k2.setEditable(false);
        k2k2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k2k3.setEditable(false);
        k2k3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k2k4.setEditable(false);
        k2k4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k3k1.setEditable(false);
        k3k1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k3k2.setEditable(false);
        k3k2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k3k3.setEditable(false);
        k3k3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k3k4.setEditable(false);
        k3k4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k4k1.setEditable(false);
        k4k1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k4k2.setEditable(false);
        k4k2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k4k3.setEditable(false);
        k4k3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k4k4.setEditable(false);
        k4k4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setForeground(new java.awt.Color(255, 237, 192));
        jLabel7.setText("K1");

        jLabel8.setForeground(new java.awt.Color(255, 237, 192));
        jLabel8.setText("K2");

        jLabel9.setForeground(new java.awt.Color(255, 237, 192));
        jLabel9.setText("K3");

        jLabel10.setForeground(new java.awt.Color(255, 237, 192));
        jLabel10.setText("K4");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(k2k1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(k2k2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(k3k1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(k3k2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(k4k1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(k4k2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(k1k1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(k1k2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(k1k3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(k2k3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(k3k3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(k4k3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(k1k4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(k2k4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(k3k4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(k4k4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel7)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel8)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel9)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel10))
                    .addComponent(jLabel2))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k1k1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k2k1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k3k1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k4k1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k1k3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(k1k2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k2k3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(k2k2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k3k3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(k3k2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k4k3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(k4k2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(k1k4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(k2k4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(k3k4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(k4k4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        jPanel3.setBackground(new java.awt.Color(22, 65, 53));
        jPanel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 237, 192));
        jLabel11.setText("Matriks Normalisasi");

        jLabel12.setForeground(new java.awt.Color(255, 237, 192));
        jLabel12.setText("K1");

        jLabel13.setForeground(new java.awt.Color(255, 237, 192));
        jLabel13.setText("K2");

        jLabel14.setForeground(new java.awt.Color(255, 237, 192));
        jLabel14.setText("K3");

        jLabel15.setForeground(new java.awt.Color(255, 237, 192));
        jLabel15.setText("K4");

        k1k1N.setEditable(false);
        k1k1N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k1k2N.setEditable(false);
        k1k2N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k1k3N.setEditable(false);
        k1k3N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k1k4N.setEditable(false);
        k1k4N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k2k1N.setEditable(false);
        k2k1N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k2k2N.setEditable(false);
        k2k2N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k2k3N.setEditable(false);
        k2k3N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k2k4N.setEditable(false);
        k2k4N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k3k1N.setEditable(false);
        k3k1N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k3k2N.setEditable(false);
        k3k2N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k3k3N.setEditable(false);
        k3k3N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k3k4N.setEditable(false);
        k3k4N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k4k1N.setEditable(false);
        k4k1N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k4k2N.setEditable(false);
        k4k2N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k4k3N.setEditable(false);
        k4k3N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        k4k4N.setEditable(false);
        k4k4N.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel16.setForeground(new java.awt.Color(255, 237, 192));
        jLabel16.setText("K1");

        jLabel17.setForeground(new java.awt.Color(255, 237, 192));
        jLabel17.setText("K2");

        jLabel18.setForeground(new java.awt.Color(255, 237, 192));
        jLabel18.setText("K3");

        jLabel19.setForeground(new java.awt.Color(255, 237, 192));
        jLabel19.setText("K4");

        jLabel20.setForeground(new java.awt.Color(255, 237, 192));
        jLabel20.setText("Prioritas");

        Prior1.setEditable(false);
        Prior1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Prior2.setEditable(false);
        Prior2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Prior3.setEditable(false);
        Prior3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Prior4.setEditable(false);
        Prior4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(k2k1N, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(k2k2N, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(k3k1N, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(k3k2N, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(k4k1N, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(k4k2N, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(k1k1N, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(k1k2N, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(k1k3N, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(k2k3N, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(k3k3N, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(k4k3N, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(k1k4N, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(k2k4N, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(k3k4N, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(k4k4N, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel16)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel17)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel18)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel19)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Prior4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(Prior3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Prior1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Prior2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k1k1N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k2k1N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k3k1N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k4k1N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k1k3N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(k1k2N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k2k3N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(k2k2N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k3k3N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(k3k2N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k4k3N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(k4k2N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k1k4N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Prior1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k2k4N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Prior2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k3k4N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Prior3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(k4k4N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Prior4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10))
        );

        Simpan.setBackground(new java.awt.Color(22, 65, 53));
        Simpan.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Simpan.setForeground(new java.awt.Color(255, 255, 255));
        Simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Icon Simpan Small.png"))); // NOI18N
        Simpan.setText("SIMPAN");
        Simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpanActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(22, 65, 53));

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 237, 192));
        jLabel23.setText("Prioritas SubKriteria Sesuai Kriteria");

        jLabel28.setForeground(new java.awt.Color(255, 237, 192));
        jLabel28.setText("K1");

        jLabel29.setForeground(new java.awt.Color(255, 237, 192));
        jLabel29.setText("K2");

        jLabel30.setForeground(new java.awt.Color(255, 237, 192));
        jLabel30.setText("K3");

        jLabel31.setForeground(new java.awt.Color(255, 237, 192));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("K4");

        jLabel21.setForeground(new java.awt.Color(255, 237, 192));
        jLabel21.setText("Prioritas Sub-Kriteria");

        PriorS11.setEditable(false);
        PriorS11.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        PriorS12.setEditable(false);
        PriorS12.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        PriorS13.setEditable(false);
        PriorS13.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        PriorS14.setEditable(false);
        PriorS14.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel24.setForeground(new java.awt.Color(255, 237, 192));
        jLabel24.setText("Prioritas Sub-Kriteria");

        PriorS21.setEditable(false);
        PriorS21.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        PriorS22.setEditable(false);
        PriorS22.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        PriorS23.setEditable(false);
        PriorS23.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        PriorS24.setEditable(false);
        PriorS24.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel25.setForeground(new java.awt.Color(255, 237, 192));
        jLabel25.setText("Prioritas Sub-Kriteria");

        PriorS31.setEditable(false);
        PriorS31.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        PriorS32.setEditable(false);
        PriorS32.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        PriorS33.setEditable(false);
        PriorS33.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        PriorS34.setEditable(false);
        PriorS34.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel26.setForeground(new java.awt.Color(255, 237, 192));
        jLabel26.setText("Prioritas Sub-Kriteria");

        PriorS41.setEditable(false);
        PriorS41.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        PriorS42.setEditable(false);
        PriorS42.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        PriorS43.setEditable(false);
        PriorS43.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        PriorS44.setEditable(false);
        PriorS44.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel23))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel28))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(PriorS14)
                                .addComponent(PriorS13)
                                .addComponent(PriorS12)
                                .addComponent(PriorS11, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(PriorS24)
                                .addComponent(PriorS23)
                                .addComponent(PriorS22)
                                .addComponent(PriorS21, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel29)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(PriorS34)
                                    .addComponent(PriorS33)
                                    .addComponent(PriorS32)
                                    .addComponent(PriorS31, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(59, 59, 59)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PriorS44)
                            .addComponent(PriorS43)
                            .addComponent(PriorS42)
                            .addComponent(PriorS41, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {PriorS11, PriorS12, PriorS13, PriorS14, PriorS21, PriorS22, PriorS23, PriorS24, PriorS31, PriorS32, PriorS33, PriorS34, PriorS41, PriorS42, PriorS43, PriorS44});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorS31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorS32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorS33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorS34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorS21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorS22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorS23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorS24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorS11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorS12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorS13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorS14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorS41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorS42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorS43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorS44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {PriorS11, PriorS12, PriorS13, PriorS14, PriorS21, PriorS22, PriorS23, PriorS24, PriorS31, PriorS32, PriorS33, PriorS34, PriorS41, PriorS42, PriorS43, PriorS44});

        jPanel6.setBackground(new java.awt.Color(22, 65, 53));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 237, 192));
        jLabel1.setText("ID Paket");

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 237, 192));
        jLabel27.setText("Nama Paket");

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 237, 192));
        jLabel22.setText("Total Penilaian Paket");

        namaPaket.setEditable(false);
        namaPaket.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        TotalNilai.setEditable(false);
        TotalNilai.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        cbIdPaket.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbIdPaket.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbIdPaketItemStateChanged(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 237, 192));
        jLabel32.setText("Pilih Data Paket");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(namaPaket)
                            .addComponent(TotalNilai)
                            .addComponent(cbIdPaket, 0, 150, Short.MAX_VALUE)))
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbIdPaket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namaPaket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalNilai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(mulaiHitung, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Simpan)
                        .addGap(125, 125, 125))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mulaiHitung)
                    .addComponent(Simpan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout PanelPerhitunganLayout = new javax.swing.GroupLayout(PanelPerhitungan);
        PanelPerhitungan.setLayout(PanelPerhitunganLayout);
        PanelPerhitunganLayout.setHorizontalGroup(
            PanelPerhitunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(judul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        PanelPerhitunganLayout.setVerticalGroup(
            PanelPerhitunganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPerhitunganLayout.createSequentialGroup()
                .addComponent(judul, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addGap(0, 0, 0))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("dialog3"); // NOI18N

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
    //tombol mulai perhitungan
    private void mulaiHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mulaiHitungActionPerformed
        // Mendapatkan perhitungan metode AHP
        getMatriksK();
        getMatriksNorK();
        getPrioritasSub();
        getAlternatif();
        getPenilaian();
        totalNilai = 0;
        // Menyimpan Alternatif hasil penilaian metode AHP
        
    }//GEN-LAST:event_mulaiHitungActionPerformed
    
    //mendapatkan nama paket dari id yang dipilih
    private void cbIdPaketItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbIdPaketItemStateChanged
       String sql = "SELECT DISTINCT nama_paket FROM data_paket WHERE id_paket='"+cbIdPaket.getSelectedItem().toString()+"';";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String b = hasil.getString("nama_paket");
                namaPaket.setText(b);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_cbIdPaketItemStateChanged
    //simpan data
    private void SimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpanActionPerformed
        // TODO add your handling code here:
        String sql = "INSERT INTO seleksi VALUES (?,?,?,?)";
        try{
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setString(1, noIdAlternatif);
            stat.setString(2, namaPaketAlternatif);
            stat.setString(3, jumlahOrangAlternatif);
            stat.setString(4, TotalNilai.getText());
            
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATA berhasil disimpan");
            kosong();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data gagal disimpan "+e);
        }
    }//GEN-LAST:event_SimpanActionPerformed

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
            java.util.logging.Logger.getLogger(DialogPerhitunganAHP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogPerhitunganAHP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogPerhitunganAHP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogPerhitunganAHP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogPerhitunganAHP dialog = new DialogPerhitunganAHP(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel Pane;
    private javax.swing.JPanel PanelPerhitungan;
    private javax.swing.JTextField Prior1;
    private javax.swing.JTextField Prior2;
    private javax.swing.JTextField Prior3;
    private javax.swing.JTextField Prior4;
    private javax.swing.JTextField PriorS11;
    private javax.swing.JTextField PriorS12;
    private javax.swing.JTextField PriorS13;
    private javax.swing.JTextField PriorS14;
    private javax.swing.JTextField PriorS21;
    private javax.swing.JTextField PriorS22;
    private javax.swing.JTextField PriorS23;
    private javax.swing.JTextField PriorS24;
    private javax.swing.JTextField PriorS31;
    private javax.swing.JTextField PriorS32;
    private javax.swing.JTextField PriorS33;
    private javax.swing.JTextField PriorS34;
    private javax.swing.JTextField PriorS41;
    private javax.swing.JTextField PriorS42;
    private javax.swing.JTextField PriorS43;
    private javax.swing.JTextField PriorS44;
    private javax.swing.JButton Simpan;
    private javax.swing.JTextField TotalNilai;
    private javax.swing.ButtonGroup btnG;
    private javax.swing.JComboBox<String> cbIdPaket;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel judul;
    private javax.swing.JTextField k1k1;
    private javax.swing.JTextField k1k1N;
    private javax.swing.JTextField k1k2;
    private javax.swing.JTextField k1k2N;
    private javax.swing.JTextField k1k3;
    private javax.swing.JTextField k1k3N;
    private javax.swing.JTextField k1k4;
    private javax.swing.JTextField k1k4N;
    private javax.swing.JTextField k2k1;
    private javax.swing.JTextField k2k1N;
    private javax.swing.JTextField k2k2;
    private javax.swing.JTextField k2k2N;
    private javax.swing.JTextField k2k3;
    private javax.swing.JTextField k2k3N;
    private javax.swing.JTextField k2k4;
    private javax.swing.JTextField k2k4N;
    private javax.swing.JTextField k3k1;
    private javax.swing.JTextField k3k1N;
    private javax.swing.JTextField k3k2;
    private javax.swing.JTextField k3k2N;
    private javax.swing.JTextField k3k3;
    private javax.swing.JTextField k3k3N;
    private javax.swing.JTextField k3k4;
    private javax.swing.JTextField k3k4N;
    private javax.swing.JTextField k4k1;
    private javax.swing.JTextField k4k1N;
    private javax.swing.JTextField k4k2;
    private javax.swing.JTextField k4k2N;
    private javax.swing.JTextField k4k3;
    private javax.swing.JTextField k4k3N;
    private javax.swing.JTextField k4k4;
    private javax.swing.JTextField k4k4N;
    private javax.swing.JButton mulaiHitung;
    private javax.swing.JTextField namaPaket;
    // End of variables declaration//GEN-END:variables

    void show(JRootPane rootPane) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
