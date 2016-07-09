package db.pojos;

import java.util.Date;

/**
 * Created by anatarajan on 6/4/16.
 */
public class ExpenseLog {
    private int id;
    private int store_id;
    private int category_id;
    private Date date;
    private double amount;
    private int expense_type_id;
    private int money_source_id;
    private String comments;

    private ExpenseLog(int store_id, int category_id, Date date,
                      double amount, int expense_type_id, int money_source_id, String comments){
        this.store_id = store_id;
        this.category_id = category_id;
        this.amount = amount;
        this.date = date;
        this.expense_type_id = expense_type_id;
        this.money_source_id = money_source_id;
        this.comments = comments;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getExpense_type_id() {
        return expense_type_id;
    }

    public void setExpense_type_id(int expense_type_id) {
        this.expense_type_id = expense_type_id;
    }

    public int getMoney_source_id() {
        return money_source_id;
    }

    public void setMoney_source_id(int money_source_id) {
        this.money_source_id = money_source_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static class ExpenseLogBuilder{

        private int store_id;
        private int category_id;
        private Date date;
        private double amount;
        private int expense_type_id;
        private int money_source_id;
        private String comments;

        public ExpenseLogBuilder(){

        }

        public ExpenseLogBuilder setStoreId(int store_id){
            this.store_id = store_id;
            return this;
        }

        public ExpenseLogBuilder setCategoryId(int category_id){
            this.category_id = category_id;
            return this;
        }

        public ExpenseLogBuilder setDate(Date date){
            this.date = date;
            return this;
        }

        public ExpenseLogBuilder setAmount(double amount){
            this.amount = amount;
            return this;
        }

        public ExpenseLogBuilder setExpenseTypeId(int expense_type_id){
            this.expense_type_id = expense_type_id;
            return this;
        }

        public ExpenseLogBuilder setMoneySourceId(int money_source_id){
            this.money_source_id = money_source_id;
            return this;
        }

        public ExpenseLogBuilder setComments(String comments){
            this.comments = comments;
            return this;
        }

        public ExpenseLog createExpenseLog(){
            return new ExpenseLog(store_id, category_id, date, amount, expense_type_id, money_source_id, comments);
        }
    }
}
