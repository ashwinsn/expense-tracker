import db.pojos.ExpenseLog;
import expensesreaders.ExpenseReader;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by anatarajan on 7/1/16.
 */
public class ExpenseLogLoader {

    ExpenseReader expenseReader;

    public void execute()  {
        List<String> expenseLogList = expenseReader.read();
        for(String expenseLogString : expenseLogList){
            String[] expenseLogAttributes = expenseLogString.split(",");

            try {
                DateFormat dateFormat = new SimpleDateFormat("MM/DD/YY");
                Date date = dateFormat.parse(expenseLogAttributes[0]);
                int store_id = extractStoreId(expenseLogAttributes[1]);
                int category_id = extractCategoryId(expenseLogAttributes[2]);
                int expense_type_id = extractExpenseTypeId(expenseLogAttributes[3]);
                Double amount = Double.valueOf(expenseLogAttributes[4]);

                new ExpenseLog.ExpenseLogBuilder().setDate(date)
                        .setStoreId(store_id)
                        .setCategoryId(category_id)
                        .setAmount(amount)
                        .setExpenseTypeId(expense_type_id);
            } catch (ParseException e){
                System.out.println(e.toString());
            }
        }

    }

    private int extractExpenseTypeId(String expenseLogAttribute) {
        return 0;
    }

    private int extractCategoryId(String expenseLogAttribute) {
        return 0;
    }

    private int extractStoreId(String Store) {
        return -1;
    }
}
