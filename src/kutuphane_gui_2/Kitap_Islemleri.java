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
public class Kitap_Islemleri {
    private Connection conn;
    String dburl = "jdbc:derby://localhost:1527/Kutuphane";
    String user = "user";
    String pass = "pass";
    
    
    public Kitap_Islemleri()
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
            ResultSet rs = stmt.executeQuery("select kitap_id from kitap");
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
    
    public void addBooks(String kitap_adi, String yazar_adi, String yayinevi, String bolum, String raf, String isbn, int kitap_adet)
    {
        try
        {
            int id = this.getLastRowID() + 1;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("INSERT INTO kitap " + "VALUES(%d, '%s', '%s', '%s', '%s', '%s', '%s', %d)", id, kitap_adi, yazar_adi, yayinevi, bolum, raf, isbn, kitap_adet));
        }
        
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
    }
    
    public ArrayList<Kitap> showAllBooks()
    {
        ArrayList<Kitap> cikti = new ArrayList<>();
        
        try
        {
            Statement stmt = conn.createStatement();
            String sql = "select * from kitap";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                int kitap_id = rs.getInt("kitap_id");
                String kitap_adi = rs.getString("kitap_adi");
                String yazar_adi = rs.getString("yazar_adi");
                String yayinevi = rs.getString("yayinevi");
                String bolum = rs.getString("bolum");
                String raf = rs.getString("raf");
                String isbn = rs.getString("isbn");
                int kitap_adet = rs.getInt("kitap_adet");
                cikti.add(new Kitap(kitap_adi, yazar_adi, yayinevi, bolum, raf, isbn, kitap_id, kitap_adet));
            }
            return cikti;
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public String getBookName(int kitap_id)
    {
        String kitap_adi = null;
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("select kitap_adi from kitap where kitap_id = %d", kitap_id));
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next())
            {
                kitap_adi = rs.getString(1);
            }
        }

        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        return kitap_adi;
    }
    
    public int getBookQty(int kitap_id)
    {
        int book_qty = 0;
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("select kitap_adet from kitap where kitap_id = %d", kitap_id));
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next())
            {
                book_qty = rs.getInt(1);
            }
        }

        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        
        return book_qty;
    
    }
    
    public int decreaseBookQty(int kitap_id)
    {
        int book_qty = this.getBookQty(kitap_id);
        if(book_qty == 0)
        {
            return -1;
        }
        
        try
        {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("update kitap set kitap_adet = %d where kitap_id = %d", book_qty - 1,kitap_id));
        }

        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        
        return 0;
    }
    
    public void increaseBookQty(int kitap_id)
    {
        int book_qty = this.getBookQty(kitap_id);
        try
        {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("update kitap set kitap_adet = %d where kitap_id = %d", book_qty + 1,kitap_id));
        }

        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
    }
    
    public int getRowCount() throws SQLException
    {
        int count = 0;
        Statement stmt = conn.createStatement();
        stmt.executeQuery("select count(*) from kitap");
        ResultSet rs = stmt.getResultSet();
        if(rs.next())
            count = rs.getInt(1);
        return count;
    }
    
    public static void main(String[] args) throws SQLException {
        Kitap_Islemleri k = new Kitap_Islemleri();
        System.out.println(k.getRowCount());
    }
}
