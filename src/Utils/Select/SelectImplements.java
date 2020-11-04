package Utils.Select;

import Connection.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Utils.SharedUtils.Methods;

public class SelectImplements implements SelectInterface {
    
    Connection con = DB.getConnection();
    Methods utilsShared = new Methods();
    
    @Override
    public ResultSet findAll(String table) throws Exception {
        StringBuilder sb = new StringBuilder();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        sb.append("SELECT * FROM ");
        sb.append(table);    

        try {
            st = con.prepareStatement(sb.toString());
            rs = st.executeQuery();
        }catch(SQLException e) {
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }    
        return rs;
    }    

    @Override
    public ResultSet findById(String table, Integer id) throws Exception {
        StringBuilder sb = new StringBuilder();
        PreparedStatement st = null;
        ResultSet rs = null;
        String primaryKey = utilsShared.findPrimaryKey(table);
        
        sb.append("SELECT * FROM ");       
        sb.append(table);
        sb.append(" WHERE ").append(primaryKey).append(" = ").append(id);
        try {
            st = con.prepareStatement(sb.toString());           
            rs = st.executeQuery();       
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return rs;
    } 
}
