package Utils.SharedUtils;

import Connection.DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class Methods {
    
    private Connection con = DB.getConnection();
    
    public String findPrimaryKey(String table) {
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
}
