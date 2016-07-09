package expensesreaders;

import miscellaneous.ExtractCategoryFromStore;
import utils.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by anatarajan on 6/19/16.
 */
public class ExpenseReadersFromCSV implements ExpenseReader{
    String csvFileName;
    File csvFile;

    public File getCsvFile() {
        return csvFile;
    }

    public void setCsvFile(File csvFile) {
        this.csvFile = csvFile;
    }

    public List<String> read(){
        List<String> output = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(csvFile);
            ignoreInitialFewLines(scanner);
            ExtractCategoryFromStore.getInstance().refreshStoreCategoryList();
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String outputLine;

                List<String> tokenList = StringUtils.stringSplitIgnoreTokenInsideQuotes(currentLine, ",");
                String[] tokens = new String[tokenList.size()];
                tokens = tokenList.toArray(tokens);

                outputLine = buildOutputLine(tokens);
                if (outputLine == null){
                    continue;
                }
                output.add(outputLine);
            }
            return output;
        } catch (FileNotFoundException e){
            System.out.println("Data file not found " + e.toString());
        }
        return output;
    }

    private void ignoreInitialFewLines(Scanner scanner) {

        while (scanner.hasNextLine()){
            String currentLine = scanner.nextLine();
            String[] tokens = currentLine.split(",");
            if(tokens.length > 0 && tokens[0].equals("Date") && tokens[1].equals("Description")){
                break;
            }
        }
        scanner.nextLine();
    }

    public String buildOutputLine(String[] tokens) {
        StringBuilder outputLine = new StringBuilder();

        String transactionDate = tokens[0];

        Double amount = Double.parseDouble(tokens[2]);
        if(amount > 0){
            return null;
        }

        String store = extractStoreFromDescription(tokens[1]);
        String category = ExtractCategoryFromStore.getInstance().execute(store);
        String expense_type = extractExpenseTypeFromDescription(tokens[1]);

        return outputLine.append(transactionDate).append(",")
                .append(store).append(",")
                .append(category).append(",")
                .append(expense_type).append(",")
                .append(amount)
                .toString();
    }

    public String extractExpenseTypeFromDescription(String token) {
        if(token.contains("DES:") || token.contains("RECURRING")){
            return "recurring";
        }

        return "one-time";
    }

    public String extractStoreFromDescription(String token) {
       CharSequence DESSequence = new StringBuffer("DES:");
       if(token.startsWith("CHECKCARD")){
           return extractStoreFromCheckCardDescription(token);
       } else if(token.contains(DESSequence)){
           return extractStoreFromDESDescription(token);
       } else {
           String storeTransactionMatcher = extractStoreBeforeDate(token);
           if (storeTransactionMatcher != null) return storeTransactionMatcher;
       }
       return token;
    }

    private String extractStoreBeforeDate(String token) {
        String datePatternString = "[0-9]{2}/[0-9]{2}";
        String storeTransactionString = new String("([A-Za-z0-9/#\\s]+?)" + datePatternString);
        Pattern storeTransactionPattern = Pattern.compile(storeTransactionString);

        Matcher storeTransactionMatcher = storeTransactionPattern.matcher(token);
        if(storeTransactionMatcher.find()){
            return storeTransactionMatcher.group(1).trim();
        }
        return null;
    }

    private String extractStoreFromCheckCardDescription(String token) {
       String checkCardPatternString = "CHECKCARD [0-9]{4} ([A-Za-z\\s]+)";
       Pattern checkCardTransactionPattern = Pattern.compile(checkCardPatternString);
       Matcher checkCardPatternMatcher = checkCardTransactionPattern.matcher(token);

       if(checkCardPatternMatcher.find()){
           return checkCardPatternMatcher.group(1).trim();
       }
       return null;
    }

    private String extractStoreFromDESDescription(String token){
        String DESTransactionString = "([A-Za-z0-9\\s/]+?) DES:";
        Pattern DESTransactionPattern = Pattern.compile(DESTransactionString);
        Matcher DESTransactionMatcher = DESTransactionPattern.matcher(token);

        if(DESTransactionMatcher.find()){
            return DESTransactionMatcher.group(1).trim();
        }
        return null;
    }
}
