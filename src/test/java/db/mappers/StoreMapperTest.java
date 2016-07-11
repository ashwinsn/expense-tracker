package db.mappers;

import db.pojos.Stores;
import org.hamcrest.core.IsNull;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by anatarajan on 7/10/16.
 */
public class StoreMapperTest {

    StoreMapper subject;
    @BeforeMethod
    public void setUp() throws Exception {
       subject.setSqlMapConfig("SqlMapConfigTest.xml");
       subject = StoreMapper.getInstance();
    }

    @Test
    public void testGetAll_returnsSomeResult() throws Exception {
        List<Stores> storesList = subject.getAll();
        assertThat(storesList, IsNull.notNullValue());
    }

    @Test
    public void testGetAll_returnsTarget() throws Exception {
        List<Stores> storesList = subject.getAll();

        boolean targetPresent = false;
        for(Stores store : storesList){
           if(store.getName().equalsIgnoreCase("target")){
               targetPresent = true;
           }
        }
        assertTrue(targetPresent);
    }


}