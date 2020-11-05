package Utils.Insert;

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

public class InsertImplements implements InsertInterface{

    private Connection con = DB.getConnection();
    private Methods utilsShared = new Methods();
    
    @Override
    public void insertInto(HashMap<String, Object> params, String table) {
        List<String> columns = new ArrayList<String>();
        List<Object> values = new ArrayList<Object>();
        Integer index = 0;
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
            for(String column : columns) {
                sql.append(column);
                index++;
                if(columns.size() != 1 && index != columns.size()) {
                    sql.append(",");
                }
            }
            index = 0;
        sql.append(" ) ");
        sql.append(" VALUES ( ");
            for(Object value : values) {
            sql.append("'"+value+"'");
            index++;
                if (values.size() != 1 && index != values.size()) {
                    sql.append(",");
                }
            }
            index = 0;
        sql.append(" ) ");
        try {
            st = con.prepareStatement(sql.toString());
            st.executeUpdate();
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
