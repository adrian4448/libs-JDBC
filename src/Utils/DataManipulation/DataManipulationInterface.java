package Utils.DataManipulation;

import java.util.HashMap;

public interface DataManipulationInterface {
    
    void insertInto(HashMap<String,Object> params,String table);
    void updateById(HashMap<String,Object> params,String table);
}
