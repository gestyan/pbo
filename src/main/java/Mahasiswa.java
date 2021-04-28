/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

/**
 *
 * @author user
 */
public class Mahasiswa implements Serializable{
    private String nim;
    private String name;
    private Integer umur;
    private String prov;
    private String kab;
    private String jk;

    /**
     * @return the nim
     */
    public String getNim() {
        return nim;
    }

    /**
     * @param nim the nim to set
     */
    public void setNim(String nim) {
        this.nim = nim;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the prov
     */
    public String getProv() {
        return prov;
    }

    /**
     * @param prov the prov to set
     */
    public void setProv(String prov) {
        this.prov = prov;
    }

    /**
     * @return the kab
     */
    public String getKab() {
        return kab;
    }

    /**
     * @param kab the kab to set
     */
    public void setKab(String kab) {
        this.kab = kab;
    }

    /**
     * @return the jk
     */
    public String getJk() {
        return jk;
    }

    /**
     * @param jk the jk to set
     */
    public void setJk(String jk) {
        this.jk = jk;
    }

    /**
     * @return the umur
     */
    public Integer getUmur() {
        return umur;
    }

    /**
     * @param umur the umur to set
     */
    public void setUmur(Integer umur) {
        this.umur = umur;
    }
    
}
