package Koneksi;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import TA.FProsesData.*;

public class koneksi {
     private Connection koneksi;
     public Statement st;
    public Connection connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Berhasil Koneksi");
        } catch (ClassNotFoundException ex){
            System.out.println("Gagal Koneksi"+ex);
        }
        String url = "jdbc:mysql://localhost:3306/ta";
        try{
            koneksi = DriverManager.getConnection(url,"root","");
            System.out.println("Berhasil Koneksi Database");
        } catch(SQLException ex){
            System.out.println("Gagal Koneksi Database"+ex);
        }
        return koneksi;
    }
    
    public void koneksi(){
        try{
           connect();
            st=koneksi.createStatement();
        }catch(Exception x){
            JOptionPane.showMessageDialog(null,"Koneksi ambil Gagal, Pesan error \n"+x);
        }
    }

    public ResultSet ambilData(String sql){
         ResultSet rs=null;
         try{
            koneksi();
            rs=st.executeQuery(sql);
         }catch(Exception x){
             JOptionPane.showMessageDialog(null,"Ambil Data Gagal, Pesan error : \n"+x);
         }
         return rs;
    }  
}
