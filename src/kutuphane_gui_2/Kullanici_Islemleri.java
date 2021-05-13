/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kutuphane_gui_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author mobyrktr
 */
public class Kullanici_Islemleri {
    private Connection conn;
    String dburl = "jdbc:derby://localhost:1527/Kutuphane";
    String user = "user";
    String pass = "pass";
    
    public Kullanici_Islemleri()
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
    
    public int getLastRowID(int userType)
    {
        int satir_sayisi = 0;
        int en_buyuk = 0;
        
        if(userType == 0) 
        {
            try
            {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select id from standart");
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
        }
        
        else if(userType == 1) 
        {
            try
            {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select id from yonetici");
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
        }
        
        return en_buyuk;
    }
    
    
    public void addUsers(String ad, String soyad, String tckn, String parola)
    {
        try
        {
            int id = this.getLastRowID(0) + 1;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("INSERT INTO standart " + "VALUES(%d, '%s', '%s', '%s', '%s')", id, ad, soyad, tckn, parola));
        }
        
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
    }
    
    public void addAdmins(String ad, String soyad, String tckn, String parola)
    {
        try
        {
            int id = this.getLastRowID(1) + 1;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("INSERT INTO yonetici " + "VALUES(%d, '%s', '%s', '%s', '%s')", id, ad, soyad, tckn, parola));
        }
        
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
    }
    
    public int getUserType(String tckn) // -1 bulunamadı, 0 standart, 1 yönetici, 2 hatalı giriş
    {
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("select id from yonetici where tckn = '%s'", tckn));
            ResultSetMetaData rsmd = rs.getMetaData();
            if(rs.next())
            {
                return 1;
            }
            
            else
            {
                rs = stmt.executeQuery(String.format("select id from standart where tckn = '%s'", tckn));
                rsmd = rs.getMetaData();
                if(rs.next())
                {
                    return 0;
                }
                
                else
                {
                    return -1;
                }
            }
        }
        
        catch(SQLException e)
        {
            System.out.println(e.toString());
            return -1;
        }
    }
    
    
    public int isRegistered(String tckn, String parola, int userType)
    {
        if(userType == -1)
                return userType;
        
        else if(userType == 1){
                try
                {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(String.format("select id from yonetici " + "where tckn = '%s' and parola = '%s'", tckn, parola));
                    ResultSetMetaData rsmd = rs.getMetaData();
                    if(rs.next())
                    {
                        return 1;
                    }
                    else
                    {
                        return 2;
                    }
                }
                
                catch(SQLException e)
                {
                    System.out.println(e.toString());
                    return userType;
                }}
            else if(userType == 0)
            {
                try
                {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(String.format("select id from standart " + "where tckn = '%s' and parola = '%s'", tckn, parola));
                    ResultSetMetaData rsmd = rs.getMetaData();
                    if(rs.next())
                    {
                        return 0;
                    }
                    
                    return 2;
                }
                
                catch(SQLException e)
                {
                    System.out.println(e.toString());
                    return -1;
                }
            }
        return -1;
    }
    
    public String getFullName(String tckn) throws SQLException
    {
        String full_name = "";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format("select f_name, l_name from standart where tckn = '%s'", tckn));
        if(rs.next())
        {
            full_name = rs.getString(1) + " " + rs.getString(2);
        }
        return full_name;
    }
    
    public void deleteUsers(String tckn) throws SQLException
    {
        Odunc_Islemleri o = new Odunc_Islemleri();
        
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(String.format("delete from standart where tckn = '%s'", tckn));
    }
    
    public ArrayList<ArrayList> getAllUsers() throws SQLException
    {
        ArrayList<ArrayList> kullanicilar = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from standart");
        while(rs.next())
        {
            String ad = rs.getString(2);
            String soyad = rs.getString(3);
            String tckn = rs.getString(4);
            ArrayList tmp = new ArrayList();
            tmp.add(tckn);
            tmp.add(ad);
            tmp.add(soyad);
            kullanicilar.add(tmp);
        }
        return kullanicilar;
    }
}

