/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kutuphane_gui_2;

/**
 *
 * @author mobyrktr
 */
public class Kitap {
    private String kitap_adi, yazar_adi, yayinevi, bolum, raf, isbn; 
    private int kitap_id, kitap_adet;

    public Kitap(String kitap_adi, String yazar_adi, String yayinevi, String bolum, String raf, String isbn, int kitap_id, int kitap_adet) {
        this.kitap_adi = kitap_adi;
        this.yazar_adi = yazar_adi;
        this.yayinevi = yayinevi;
        this.bolum = bolum;
        this.raf = raf;
        this.isbn = isbn;
        this.kitap_id = kitap_id;
        this.kitap_adet = kitap_adet;
    }

    public String getKitap_adi() {
        return kitap_adi;
    }

    public void setKitap_adi(String kitap_adi) {
        this.kitap_adi = kitap_adi;
    }

    public String getYazar_adi() {
        return yazar_adi;
    }

    public void setYazar_adi(String yazar_adi) {
        this.yazar_adi = yazar_adi;
    }

    public String getYayinevi() {
        return yayinevi;
    }

    public void setYayinevi(String yayinevi) {
        this.yayinevi = yayinevi;
    }

    public String getBolum() {
        return bolum;
    }

    public void setBolum(String bolum) {
        this.bolum = bolum;
    }

    public String getRaf() {
        return raf;
    }

    public void setRaf(String raf) {
        this.raf = raf;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getKitap_id() {
        return kitap_id;
    }

    public void setKitap_id(int kitap_id) {
        this.kitap_id = kitap_id;
    }

    public int getKitap_adet() {
        return kitap_adet;
    }

    public void setKitap_adet(int kitap_adet) {
        this.kitap_adet = kitap_adet;
    }
    
    
}
