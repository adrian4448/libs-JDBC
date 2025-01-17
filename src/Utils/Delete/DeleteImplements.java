package Utils.Delete;

import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Connection.DB;
import Utils.SharedUtils.Methods;
import java.util.HashMap;

public class DeleteImplements implements DeleteInterface{

    private Connection con = DB.getConnection();
    private Methods utilsShared = new Methods();
    
    @Override
    public void deleteByFieldName(HashMap<String, Object> params,String table) {
        StringBuilder sql = new StringBuilder();
        PreparedStatement st = null;
        
        sql.append(" DELETE FROM ");
        sql.append(table);
        sql.append(" WHERE ");
        params.forEach((key,value) -> {
            sql.append(key).append(" = ").append(value);
        });
        
        try {
            st = con.prepareStatement(sql.toString());
            st.executeUpdate();
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getStackTrace());
        }finally {
            DB.closeStatement(st);
        }
    }
}
