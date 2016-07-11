package db.mappers;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import db.pojos.Stores;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by anatarajan on 7/10/16.
 */
public class StoreMapper {
    SqlMapClient sqlMapClient;

    private static String sqlMapConfig = "SqlMapConfig.xml";
    private static StoreMapper currentInstance;

    private StoreMapper(){
        try{
            Reader reader = Resources.getResourceAsReader(sqlMapConfig);
            sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static StoreMapper getInstance(){
        if(currentInstance == null){
            currentInstance = new StoreMapper();
        }
        return currentInstance;
    }

    public List<Stores> getAll(){
        List<Stores> storesList = null;
        try {
            storesList = sqlMapClient.queryForList("db.mappers.StoreMapper.getAll");
        } catch(SQLException e){
            e.printStackTrace();
        }
        return storesList;
    }

    public static String getSqlMapConfig() {
        return sqlMapConfig;
    }

    public static void setSqlMapConfig(String sqlMapConfig) {
        StoreMapper.sqlMapConfig = sqlMapConfig;
    }

}
