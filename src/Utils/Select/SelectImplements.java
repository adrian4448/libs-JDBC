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
        }finally {
            DB.closeStatement(st);
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
        sql.append(" WHERE ").append(params.get("NOME_ATRIBUTO")).append(" = ");
        sql.append(" '" + params.get("VALOR_ATRIBUTO") + "' ");
        try {
            st = con.prepareStatement(sql.toString());           
            rs = st.executeQuery();       
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getStackTrace());
        }finally {
            DB.closeStatement(st);
        }
        return rs;
    }
}
