package Utils.DataManipulation;

import Connection.DB;
import Utils.SharedUtils.Methods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

public class DataManipulationImplements implements DataManipulationInterface{

    private Connection con = DB.getConnection();
    private Methods utilsShared = new Methods();
    
    @Override
    public void insertInto(HashMap<String, Object> params, String table) {
        List<String> columns = new ArrayList<String>();
        List<Object> values = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        sql.append(" INSERT INTO ");
        sql.append(table);
        params.forEach((key,value) -> {
            columns.add(key);
            values.add(value);
        });
        sql.append(" ( ");
            atribuirColunas(sql, columns);
        sql.append(" ) ");
        sql.append(" VALUES ( ");
            atribuirValores(sql, values);
        sql.append(" ) ");
        try {
            st = con.prepareStatement(sql.toString());
            st.executeUpdate();
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getStackTrace());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void updateById(HashMap<String, Object> params, String table) {
        StringBuilder sql = new StringBuilder();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        sql.append(" UPDATE ");
        sql.append(table);
        sql.append(" SET ");
        params.forEach((key,value) -> {
            if(!key.equals("ID_FIND")) {
              sql.append(key).append(" = ").append("'"+value+"', ");
            }
        });
        sql.delete(sql.length() - 2,sql.length());
        sql.append(" WHERE ").append(utilsShared.findPrimaryKeyName(table)).append(" = ").append(params.get("ID_FIND"));
        
        try {
            st = con.prepareStatement(sql.toString());
            st.executeUpdate();
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getStackTrace());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    
    void atribuirValores(StringBuilder sql,List<Object> values) {
        Integer index = 0;
        for(Object value : values) {
            sql.append("'"+value+"'");
            index++;
                if (values.size() != 1 && index != values.size()) {
                    sql.append(",");
                }
            }
            index = 0;
    }
    
    void atribuirColunas(StringBuilder sql,List<String> columns) {
        Integer index = 0;
        for(String column : columns) {
                sql.append(column);
                index++;
                if(columns.size() != 1 && index != columns.size()) {
                    sql.append(",");
                }
            }
            index = 0;
    }
    
}
