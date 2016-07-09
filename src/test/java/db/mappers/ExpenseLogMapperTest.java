package db.mappers;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import db.pojos.ExpenseLog;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.util.Date;

/**
 * Created by anatarajan on 6/4/16.
 */
public class ExpenseLogMapperTest {

    ExpenseLogMapper subject;
    private static final String sqlMapConfig = "SqlMapConfigTest.xml";

    @Before
    public void setUp() throws Exception {
        subject = new ExpenseLogMapper();
        Reader reader = Resources.getResourceAsReader(sqlMapConfig);
        SqlMapClient sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
        subject.setSqlMapClient(sqlMapClient);
    }

    @Test
    public void testInsert() throws Exception {
        ExpenseLog expenseLog = new ExpenseLog(1,1, new Date(), 1.00, 1, 1, null);
        subject.insert(expenseLog);
    }
}