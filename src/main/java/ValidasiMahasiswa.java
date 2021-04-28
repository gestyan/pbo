
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class ValidasiMahasiswa {

    private final String ERROR_NIM_EXIST = "Nim telah digunakan\n";
    private final String ERROR_NIM_NULL = "Nim Kosong\n";
    private final String ERROR_NIM_NOT_NUMBER = "Nim harus berupa angka\n";
    private final String ERROR_NIM_LENGTH = "Panjang Nim harus 9 karakter\n";
    private final String ERROR_ADD_DATA = "Gagal Menambah data\n";
    private final String ERROR_NAME_NULL = "Nama kosong\n";
    private final String ERROR_NAME_LENGTH = "Nama minimal 6 karakter\n";
    private final String ERROR_NAME_NOT_CHAR = "Nama hanya boleh mengandung alphabet\n";
    private final String ERROR_GENDER_NULL = "Jenis kelamin kosong\n";
    private final String ERROR_PROV_NULL = "Provinsi kosong\n";
    private final String ERROR_KAB_NULL = "Kabupaten kosong\n";
    private final String SUCCES_ADD_DATA = "Sukses menambahkan data\n";
    private final String SUCCES_UPDATE_DATA = "Sukses mengupdate data\n";

    private Mahasiswa dataMhs;
    private Boolean isTrue = true;
    private String message = "";

    public Boolean isExist(Mahasiswa mhs) throws SQLException {
        Database dataMahasiswa = Database.getInstance();
        Boolean bool = false;

        resetMessage();

        for (Mahasiswa m : dataMahasiswa.getListMahasiswa()) {
            if (m.getNim().equals(mhs.getNim())) {
                message += ERROR_NIM_EXIST;
                System.out.println("Masuk");
                return bool = true;
            }
        }
        return bool;
    }

    public void isValid(Mahasiswa mhs) {
        try {
            if (!isExist(mhs)) {
                isTrue = true;
                if (mhs.getNim().equals("")) {
                    message += ERROR_NIM_NULL;
                    isTrue = false;
                }
                if (mhs.getNim().length() != 9) {
                    message += ERROR_NIM_LENGTH;
                    isTrue = false;
                }
                for (int i = 0; i < mhs.getNim().length(); i++) {
                    if (!Character.isDigit(mhs.getNim().charAt(i))) {
                        isTrue = false;
                        message += ERROR_NIM_NOT_NUMBER;
                        break;
                    }
                }
                if (mhs.getName().equals("")) {
                    isTrue = false;
                    message += ERROR_NAME_NULL;
                } else if (mhs.getName().length() < 6) {
                    isTrue = false;
                    message += ERROR_NAME_LENGTH;
                } else if (!mhs.getName().matches("^[a-zA-Z ]*$")) {
                    isTrue = false;
                    message += ERROR_NAME_NOT_CHAR;
                }
                if (mhs.getJk().equals("")) {
                    isTrue = false;
                    message += ERROR_GENDER_NULL;
                }
                if (mhs.getProv().equals("")) {
                    isTrue = false;
                    message += ERROR_PROV_NULL;
                }
                if (mhs.getKab().equals("")) {
                    isTrue = false;
                    message += ERROR_KAB_NULL;
                }
            } else {
                isTrue = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ValidasiMahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!isTrue) {
            message += ERROR_ADD_DATA;
        } else {
            resetMessage();
            message += SUCCES_ADD_DATA;
        }
    }

    public void isValidEdit(Mahasiswa mhs) {
        isTrue = true;
        resetMessage();
        if (mhs.getNim().equals("")) {
            message += ERROR_NIM_NULL;
            isTrue = false;
        }
        if (mhs.getNim().length() != 9) {
            message += ERROR_NIM_LENGTH;
            isTrue = false;
        }
        for (int i = 0; i < mhs.getNim().length(); i++) {
            if (!Character.isDigit(mhs.getNim().charAt(i))) {
                isTrue = false;
                message += ERROR_NIM_NOT_NUMBER;
                break;
            }
        }
        if (mhs.getName().equals("")) {
            isTrue = false;
            message += ERROR_NAME_NULL;
        } else if (mhs.getName().length() < 6) {
            isTrue = false;
            message += ERROR_NAME_LENGTH;
        } else if (!mhs.getName().matches("^[a-zA-Z ]*$")) {
            isTrue = false;
            message += ERROR_NAME_NOT_CHAR;
        }

        if (mhs.getJk().equals("")) {
            isTrue = false;
            message += ERROR_GENDER_NULL;
        }
        if (mhs.getProv().equals("")) {
            isTrue = false;
            message += ERROR_PROV_NULL;
        }
        if (mhs.getKab().equals("")) {
            isTrue = false;
            message += ERROR_KAB_NULL;
        }
        if (!isTrue) {
            message += ERROR_ADD_DATA;
        } else {
            resetMessage();
            message += SUCCES_UPDATE_DATA;
        }
    }

    private void resetMessage() {
        this.message = "";
    }

    /**
     * @return the isTrue
     */
    public Boolean getIsTrue(Mahasiswa m) {
        isValid(m);
        return isTrue;
    }

    public Boolean getIsTrueEdit(Mahasiswa m) {
        isValidEdit(m);
        return isTrue;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
