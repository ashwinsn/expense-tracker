package expensesreaders;

import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by anatarajan on 6/19/16.
 */
public class ExpenseReadersFromCSVTest {

    ExpenseReadersFromCSV subject = null;

    @org.testng.annotations.BeforeMethod
    public void setUp() throws Exception {
       subject = new ExpenseReadersFromCSV();
    }

    @Test
    public void readFromFile_Does_Not_Build_output_lines_on_non_Financial_data() throws IOException {
        ExpenseReadersFromCSV mockExpenseReader = mock(ExpenseReadersFromCSV.class);

        File someCSVFile = new File("someCSVFile.csv");
        FileWriter fileWriter = new FileWriter(someCSVFile);
        BufferedWriter bufferedFileWriter = new BufferedWriter(fileWriter);

        bufferedFileWriter.write("some random string\n another random string");
        bufferedFileWriter.close();

        mockExpenseReader.setCsvFile(someCSVFile);

        mockExpenseReader.read();

        verify(mockExpenseReader, never()).buildOutputLine(new String[] {});
    }

    @Test
    public void readFromFile_returns_correct_number_of_output_lines(){
        File testCSVFile = new File("teststatement.csv");

        subject.setCsvFile(testCSVFile);
        List<String> output = subject.read();

        assertThat(output.size(), is(equalTo(73)));
    }

    @Test
    public void extractStoreFromDescription_returns_The_Store_ForNormalTransaction(){
       String store = subject.extractStoreFromDescription("SPROUTS/store FARMER 05/30 #000205827 PURCHASE SPROUTS FARMERS M SUNNYVALE CA");
       assertThat(store, is(equalTo("SPROUTS/store FARMER")));
    }

    @Test
    public void extractStoreFromDescription_returns_The_Store_ForCreditCardTransaction(){
        String store = subject.extractStoreFromDescription("Online Banking payment to CRD 2089 Confirmation# 3901106231");
        assertThat(store, is(equalTo("Online Banking payment to CRD 2089 Confirmation# 3901106231")));
    }

    @Test
    public void extractStoreFromDescription_returns_The_Store_ForCheckCardTransaction(){
        String store = subject.extractStoreFromDescription("CHECKCARD 0530 COCONUT HILL FREMONT CA 24765016152006000681043");
        assertThat(store, is(equalTo("COCONUT HILL FREMONT CA")));
    }

    @Test
    public void extractStoreFromDescription_returns_The_Store_ForDESDescription(){
        String store = subject.extractStoreFromDescription("SAMSUNG ELECTRON DES:DIRECT DEP ID:XXXXX32514744AF INDN:NATARAJAN,ASHWIN S CO ID:XXXXX11103 PPD");
        assertThat(store, is(equalTo("SAMSUNG ELECTRON")));
    }

    @Test
    public void extractExpenseTypeFromDescription_returns_Recurring_For_Recurring_type(){
        String expenseType = subject.extractExpenseTypeFromDescription("ATT DES:Payment ID:XXXXX9004COL1S INDN:Bank of America CO ID:XXXXX31005 WEB");
        assertThat(expenseType, is(equalTo("recurring")));
    }

    @Test
    public void extractExpenseTypeFromDescription_returns_one_time_For_onetime_transaction(){
        String expenseType = subject.extractExpenseTypeFromDescription("CHECKCARD 0530 COCONUT HILL FREMONT CA 24765016152006000681043");
        assertThat(expenseType, is(equalTo("one-time")));
    }
}