
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gestyan ramadhan
 */
public class Database{

    private static final String DB_TYPE = "mysql";
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "latihan_pbo_1";
    private static final String DB_USER = "root";
    private static final String DB_PSW = "";

    private static final String DATA_MHS_LOC = "E:\\My File\\Lecture\\Semester\\IV\\PBO\\Latihan\\netbeans\\basicGUI2\\src\\main\\java\\files\\database.dat";
    private static final String DATA_ACCOUNT_LOC = "E:\\My File\\Lecture\\Semester\\IV\\PBO\\Latihan\\netbeans\\basicGUI2\\src\\main\\java\\files\\account.dat";
    private static Database instance;
    private ArrayList<Mahasiswa> dataMahasiswa = null;

//    private static ArrayList<Account> dataAccount = new ArrayList<>();
    private Database() {
    }

    /**
     * @return the instance
     */
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * @return the dataMahasiswa
     */
    public List<Mahasiswa> getListMahasiswa() throws SQLException {
        List<Mahasiswa> mhsList = new ArrayList<>();
        Connection conn = getConnection();
        try {
            String sql = "SELECT * FROM mahasiswa";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Mahasiswa mhs = new Mahasiswa();
                mhs.setNim(rs.getString("nim"));
                mhs.setName(rs.getString("nama"));
                mhs.setJk(rs.getString("jenis_kelamin"));
                mhs.setUmur(rs.getInt("umur"));
                mhs.setProv(rs.getString("asal_prov"));
                mhs.setKab(rs.getString("asal_kab"));

                mhsList.add(mhs);
            }
            System.out.println("Berhasil mengambil data");
        } catch (SQLException ex) {
            System.out.println("Gagal mengambil data");
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
                System.out.println("Menutup server");
            }
        }
        return mhsList;
    }

    /**
     * @param mhs
     */
    //Perlu di edit biar bisa update
    public void updateMahasiswa(Mahasiswa mhs) {
        Connection conn = null;
        try {
            //        try {
//            for (Mahasiswa m : getListMahasiswa()) {
//                if (m.getNim().equals(mhs.getNim())) {
//                    int i = dataMahasiswa.indexOf(m);
//                    dataMahasiswa.set(i, mhs);
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
//        }
            conn = getConnection();
            String sql = "UPDATE mahasiswa SET nim=?, nama=?, jenis_kelamin=?, umur=?, asal_prov=?, asal_kab=? WHERE nim=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mhs.getNim());
            pstmt.setString(2, mhs.getName());
            pstmt.setString(3, mhs.getJk());
            pstmt.setInt(4, mhs.getUmur());
            pstmt.setString(5, mhs.getProv());
            pstmt.setString(6, mhs.getKab());
            pstmt.setString(7, mhs.getNim());
            pstmt.executeUpdate();
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//        pstmt.setString(1, mhs.getNim());
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void addMahasiswa(Mahasiswa mhs) throws SQLException {
        Connection conn = getConnection();
        try {
            String sql = "INSERT INTO mahasiswa VALUES (?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mhs.getNim());
            pstmt.setString(2, mhs.getName());
            pstmt.setString(3, mhs.getJk());
            pstmt.setInt(4, mhs.getUmur());
            pstmt.setString(5, mhs.getProv());
            pstmt.setString(6, mhs.getKab());
            pstmt.executeUpdate();
            System.out.println("Berhasil menambahkan");
        } catch (SQLException ex) {
            System.out.println(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
                System.out.println("menutup server");
            }
        }

    }

    public Mahasiswa getMahasiswa(String nim) {
        Mahasiswa m = null;
        try {
            for (Mahasiswa mhs : getListMahasiswa()) {
                if (mhs.getNim().equals(nim)) {
                    m = mhs;
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    public void delMahasiswa(String nim) {
        Connection conn = null;
        try {
            //        try {
//            for (Mahasiswa mhs : getListMahasiswa()) {
//                if (mhs.getNim().equals(nim)) {
//                    int i = dataMahasiswa.indexOf(mhs);
//                    dataMahasiswa.remove(i);
//                    break;
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
//        }
            conn = getConnection();
            String sql = "DELETE FROM mahasiswa WHERE nim=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nim);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:" + DB_TYPE + "://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME, DB_USER, DB_PSW);
    }
}
