package db.mappers;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import db.pojos.ExpenseLog;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

/**
 * Created by anatarajan on 6/4/16.
 */
public class ExpenseLogMapper {

    SqlMapClient sqlMapClient;
    private static final String sqlMapConfig = "SqlMapConfig.xml";

    public ExpenseLogMapper(){
        try {
            Reader reader = Resources.getResourceAsReader(sqlMapConfig);
            sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (IOException e){
            System.out.println(e.toString());
        }
    }

    public void insert(ExpenseLog expenseLog){
        try {
            sqlMapClient.insert("db.mappers.ExpenseLogMapper.insert", expenseLog);
        } catch (SQLException e){
            System.out.println(e.toString());
        }

    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }
}
