package Utils.Select;

import java.sql.ResultSet;

public interface SelectInterface {
    
    ResultSet findAll(String table) throws Exception;
    ResultSet findById(String table,Integer id) throws Exception;

}
