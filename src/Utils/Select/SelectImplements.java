package Utils.Select;

import Connection.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SelectImplements implements SelectInterface {
    
    Connection con = DB.getConnection();
    
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
        String primaryKey = findPrimaryKey(table);
        
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
    
    protected String findPrimaryKey(String table) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String columnName = "";
        try {
            st = con.prepareStatement("SELECT * " +
                                      "FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE " +
                                      "WHERE TABLE_NAME = ? " +
                                      "AND CONSTRAINT_NAME = 'PRIMARY' ");
            st.setString(1, table);
            
            rs = st.executeQuery();
            
            while(rs.next()) {
                columnName = rs.getString("COLUMN_NAME");
            }
        }catch(SQLException e) {
            e.getMessage();
        }
        return columnName;
    }
    
}
