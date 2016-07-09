package utils;

import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by anatarajan on 6/26/16.
 */
public class StringUtilsTest {

    StringUtils stringUtils = new StringUtils();

    @Test
    public void stringSplitIgnoreTokenInsideQuotes_ignoresTokenInsideQuotes(){
        String source = "a, \"b, c\"";

        List<String> output = stringUtils.stringSplitIgnoreTokenInsideQuotes(source, ",");
        assertThat(output.size(), is(equalTo(2)));
        assertThat(output.get(0), is(equalTo("a")));
        assertThat(output.get(1), is(equalTo("b, c")));
    }

    @Test
    public void stringSplitIgnoreTokensInsideQuotes_extractsStoreForCreditCardTransactionsas2ndToken(){
        String source = "6/3/16,Online Banking payment to CRD 2089 Confirmation# 3901106231,-500,5540.77\n";

        List<String> output = stringUtils.stringSplitIgnoreTokenInsideQuotes(source, ",");
        assertThat(output.size(), is(equalTo(4)));
        assertThat(output.get(1), is(equalTo("Online Banking payment to CRD 2089 Confirmation# 3901106231")));
    }

}