package Utils.Select;

import Connection.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Utils.SharedUtils.Methods;
import java.util.HashMap;

public class SelectImplements implements SelectInterface {
    
    Connection con = DB.getConnection();
    Methods utilsShared = new Methods();
    
    @Override
    public ResultSet findAll(String table) {
        StringBuilder sql = new StringBuilder();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        sql.append("SELECT * FROM ");
        sql.append(table);    

        try {
            st = con.prepareStatement(sql.toString());
            rs = st.executeQuery();
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return rs;
    }    


    @Override
    public ResultSet findByFieldName(HashMap<String, Object> params, String table) {
        StringBuilder sql = new StringBuilder();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        sql.append("SELECT * FROM ");       
        sql.append(table);
        sql.append(" WHERE ");
        params.forEach((key,value) -> {
            sql.append(key).append(" = ").append(" '"+ value +"' ");
        });
        
        try {
            st = con.prepareStatement(sql.toString());           
            rs = st.executeQuery();       
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return rs;
    }

    @Override
    public ResultSet selectWithWhere(StringBuilder where, String table) {
        StringBuilder sql = new StringBuilder();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        sql.append("SELECT * FROM ");       
        sql.append(table);
        sql.append(" WHERE ");
        sql.append(where);
        try {
            st = con.prepareStatement(sql.toString());           
            rs = st.executeQuery();
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return rs;
    }
}
