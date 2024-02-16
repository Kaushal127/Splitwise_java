package org.example.models;

public class Expense {
    private String groupName ;
    private String description ;
    private int amount ;
    private ExpenseType expenseType ;

    public Expense(String groupName, String description, int amount, ExpenseType expenseType) {
        this.groupName = groupName;
        this.description = description;
        this.amount = amount;
        this.expenseType = expenseType;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "groupName='" + groupName + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", expenseType=" + expenseType +
                '}';
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }
}
