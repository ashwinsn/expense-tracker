package miscellaneous;

import configuration.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by anatarajan on 7/10/16.
 */
public class ConfigurationTest {

    Configuration subject;

    @BeforeMethod
    public void setUp() throws Exception {
       subject = Configuration.getInstance();
       subject.setPropertyFile("application.test.properties");
    }

    @Test
    public void loadFromFile_loadsIntegerPropertiesCorrectly(){
        subject.loadFromFile();

        Configuration.KeyWithValueTypeT<Integer> someIntegerValueKey = new Configuration.KeyWithValueTypeT<Integer>("someintegervaluekey");

        assertThat(subject.get(someIntegerValueKey,0), is(equalTo(342)));
    }

    @Test
    public void loadFromFile_loadsBooleanPropertiesCorrectly(){
        subject.loadFromFile();

        Configuration.KeyWithValueTypeT<Boolean> someBooleanValueKey = new Configuration.KeyWithValueTypeT<Boolean>("somebooleanvaluekey");

        assertTrue(subject.get(someBooleanValueKey,false));
    }

    @Test
    public void loadFromFile_loadsStringPropertiesCorrectly(){
        subject.loadFromFile();

        Configuration.KeyWithValueTypeT<String> someStringValueKey = new Configuration.KeyWithValueTypeT<String>("somestringvaluekey");

        assertThat(subject.get(someStringValueKey, "random"), is(equalTo("somestring")));
    }
}