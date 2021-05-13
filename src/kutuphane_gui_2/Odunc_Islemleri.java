/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kutuphane_gui_2;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author mobyrktr
 */
public class Odunc_Islemleri {
    private Connection conn;
    String dburl = "jdbc:derby://localhost:1527/Kutuphane";
    String user = "user";
    String pass = "pass";
    
    public Odunc_Islemleri()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(dburl, user, pass);
        }
        
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e)
        {
            
            System.out.println(e.toString());
            System.out.println("Bağlantı sağlanamadı.");
        }
    }
    
    public int getLastRowID()
    {
        int satir_sayisi = 0;
        int en_buyuk = 0;
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select id from odunc");
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next())
            {
                satir_sayisi = rs.getInt(1);
                if(satir_sayisi > en_buyuk)
                    en_buyuk = satir_sayisi;
            }
        }

        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        
        return en_buyuk;
    }
    
    public int oduncAl(int kitap_id, String tckn, String tarih)
    {
        Kitap_Islemleri k = new Kitap_Islemleri();
        int adet = k.decreaseBookQty(kitap_id);
        if(adet != -1)
        {
            try
            {
                int id = this.getLastRowID() + 1;
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(String.format("INSERT INTO odunc " + "VALUES(%d, %d, '%s', '%s')", id, kitap_id, tckn, tarih));

            }

            catch(SQLException e)
            {
                System.out.println(e.toString());
            }
            return 0;
        }
        
        else
        {
            return -1;
        }
    }
    
    public ArrayList<ArrayList> cezalariVer(String tckn) throws ParseException
    {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<String> eski_tarihler = new ArrayList<>();
        ArrayList<ArrayList> cezalar = new ArrayList<>();
        LocalDate today = LocalDate.now(ZoneId.of("GMT+03:00"));
        Date dt = myFormat.parse(today.format(DateTimeFormatter.ofPattern("d/MM/uuuu")));
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("select kitap_id, tarih from odunc where tckn = '%s'", tckn));
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next())
            {
                String tarih = rs.getString(2);
                Date old = myFormat.parse(tarih);
                dt = myFormat.parse("16/01/2020");
                long diff = dt.getTime() - old.getTime();
                long gun = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                if(gun > 15)
                {
                    int kitap_id = rs.getInt(1);
                    ArrayList tmp = new ArrayList();
                    tmp.add(kitap_id);
                    tmp.add(gun);
                    cezalar.add(tmp);
                }
            }
        }

        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
    return cezalar;  
    }
    
    public String getVerilmeTarihi(int kitap_id, String tckn)
    {
        String verilme_tarihi = null;
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("select tarih from odunc where kitap_id = %d and tckn = '%s'", kitap_id, tckn));
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next())
            {
                verilme_tarihi = rs.getString(1);
            }
        }

        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        return verilme_tarihi;
    }
    
    public ArrayList<ArrayList> oduncAlinmislariVer(String tckn) throws ParseException
    {
        ArrayList<ArrayList> oduncler = new ArrayList<>();
        
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("select kitap_id, tarih from odunc where tckn = '%s'", tckn));
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next())
            {
                String tarih = rs.getString(2);
                int kitap_id = rs.getInt(1);
                ArrayList tmp = new ArrayList();
                tmp.add(kitap_id);
                oduncler.add(tmp);
                
            }
            
            if(oduncler.isEmpty())
            {
                return null;
            }
        }

        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
    return oduncler;  
    }
    
    public void iadeEt(int kitap_id, String tckn)
    {
        Kitap_Islemleri k = new Kitap_Islemleri();
        try
        {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("delete from odunc where kitap_id = %d and tckn = '%s'", kitap_id, tckn));
            k.increaseBookQty(kitap_id);
        }
        
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
    }
    
    public boolean kitapAlinmisMi(String tckn, int kitap_id) throws SQLException
    {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format("select id from odunc where kitap_id = %d and tckn = '%s'", kitap_id, tckn));
        return rs.next();
    }
    
    public ArrayList<ArrayList> tumCezalariVer() throws ParseException, SQLException
    {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<String> eski_tarihler = new ArrayList<>();
        ArrayList<ArrayList> cezalar = new ArrayList<>();
        LocalDate today = LocalDate.now(ZoneId.of("GMT+03:00"));
        Date dt = myFormat.parse(today.format(DateTimeFormatter.ofPattern("d/MM/uuuu")));
        Kullanici_Islemleri kullanici = new Kullanici_Islemleri();
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select tckn, kitap_id, tarih from odunc");
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next())
            {
                String tarih = rs.getString(3);
                Date old = myFormat.parse(tarih);
                dt = myFormat.parse("16/01/2020");
                long diff = dt.getTime() - old.getTime();
                long gun = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                if(gun > 15)
                {
                    int kitap_id = rs.getInt(2);
                    String tckn = rs.getString(1);
                    String tam_ad = kullanici.getFullName(tckn);
                    ArrayList tmp = new ArrayList();
                    tmp.add(tam_ad);
                    tmp.add(tckn);
                    tmp.add(kitap_id);
                    tmp.add(gun);
                    cezalar.add(tmp);
                }
            }
        }
        
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
    return cezalar;  
    }
    
    public boolean kitapAlmisMi(String tckn) throws SQLException
    {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format("select id from odunc where tckn = '%s'", tckn));
        return rs.next();
    }
    
}

