package db.mappers;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import db.pojos.StoreCategory;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by anatarajan on 6/25/16.
 */
public class StoreCategoryMapper {

    SqlMapClient sqlMapClient;
    private static final String sqlMapConfig = "SqlMapConfig.xml";
    private static StoreCategoryMapper currentInstance;

    private StoreCategoryMapper(){
        try {
            Reader reader = Resources.getResourceAsReader(sqlMapConfig);
            sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StoreCategoryMapper getInstance(){
        if(currentInstance == null){
            currentInstance = new StoreCategoryMapper();
        }
        return currentInstance;
    }

    public List<StoreCategory> getAll(){
        try{
            List<StoreCategory> storeCategories = (List<StoreCategory>) sqlMapClient.queryForList("db.mappers.StoreCategoryMapper.getAll");
            return storeCategories;
        } catch (SQLException e){
            System.out.println(e.toString());
            return null;
        }
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }
}
