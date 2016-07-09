package expensesreaders;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

/**
 * Created by anatarajan on 6/4/16.
 */
public class ExpenseReaderfromFileTest {

    ExpenseReaderfromFile subject;
    @org.junit.Before
    public void setUp() throws Exception {
        subject = new ExpenseReaderfromFile();
    }

    @Test
    public void testExecuteReturnsAListOfString() throws Exception{
        subject.setDataFileName("expenses_data.txt");
        List<String> input = subject.execute();
        assertThat(input.size(), is(greaterThan(0)));
        assertThat(input.size(), is(equalTo(2)));
    }

    @Test(expected = Exception.class)
    public void testExecuteThrowsExceptionIfSomeLinesHaveLesserThanSixFields() throws Exception{
        subject.setDataFileName("expenses_data_invalid.txt");
        List<String> input = subject.execute();
        assertThat(input.size(), is(equalTo(0)));
    }
}