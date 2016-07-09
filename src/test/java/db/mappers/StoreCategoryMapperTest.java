package db.mappers;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by anatarajan on 6/25/16.
 */
public class StoreCategoryMapperTest {

    StoreCategoryMapper subject;

    @BeforeMethod
    public void setUp() throws Exception {
        subject = StoreCategoryMapper.getInstance();
    }

    @Test
    public void getAll_ReturnsAllRecords(){
        assertThat(subject.getAll().size(), is(greaterThan(0)));
    }
}