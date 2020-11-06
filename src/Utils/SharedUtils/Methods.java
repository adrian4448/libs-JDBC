package Utils.SharedUtils;

import Connection.DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class Methods {
    
    private Connection con = DB.getConnection();
    
    public String findPrimaryKeyName(String table) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String columnName = "";
        try {
            st = con.prepareStatement("SELECT * "
                    + "FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE "
                    + "WHERE TABLE_NAME = ? "
                    + "AND CONSTRAINT_NAME = 'PRIMARY' ");
            st.setString(1, table);

            rs = st.executeQuery();

            while (rs.next()) {
                columnName = rs.getString("COLUMN_NAME");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return columnName;
    }
    
    public Integer findPrimaryKey(String table,HashMap<String,Object> params) {
        Integer id = 0;
        StringBuilder sql = new StringBuilder();
        StringBuilder sqlWhere = new StringBuilder();
        PreparedStatement st = null;
        ResultSet rs = null;
        sql.append("SELECT ").append(findPrimaryKeyName(table));
        sql.append(" FROM ").append(table);
        
        params.forEach((key,value) -> {
            sqlWhere.append(key).append(" = ").append(value).append(" AND ");
        });
        sqlWhere.deleteCharAt(sqlWhere.length() - 3);
        sql.append(" WHERE ").append(sqlWhere);
        
        try {
            st = con.prepareStatement(sql.toString());
            rs = st.executeQuery();

            while (rs.next()) {
                id = rs.getInt(findPrimaryKeyName(table));
            }          
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return id;
    }
}
