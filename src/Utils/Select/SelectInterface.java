package Utils.Select;

import java.sql.ResultSet;
import java.util.HashMap;

public interface SelectInterface {
    
    ResultSet findAll(String table) throws Exception;
    ResultSet findByFieldName(HashMap<String, Object> params, String table);
}
