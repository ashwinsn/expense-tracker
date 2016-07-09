package expensesreaders;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by anatarajan on 6/4/16.
 */
public class ExpenseReaderfromFile {

    String dataFileName;
    File dataFile;

    public List<String> execute() throws Exception{
        List<String> output = new ArrayList<String>();
        boolean someLinesInvalidInput = false;
        try {
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] tokens = currentLine.split("::");
                if(tokens.length < 6){
                    someLinesInvalidInput = true;
                }
                output.add(currentLine);
            }

            if(someLinesInvalidInput){
                throw new Exception("some lines have invalid input");
            }
            return output;
        } catch (FileNotFoundException e){
            System.out.println("Data file not found " + e.toString());
        }
        return output;
    }

    public String getDataFileName() {
        return dataFileName;
    }

    public void setDataFileName(String dataFileName) {
        this.dataFileName = dataFileName;
        this.dataFile = new File(dataFileName);
    }

    public static void main(String args[]) throws Exception{
        ExpenseReaderfromFile subject = new ExpenseReaderfromFile();
        subject.execute();
    }
}
