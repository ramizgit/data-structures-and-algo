package lld.splitwise.model;

import lld.splitwise.enums.SplitType;

import java.util.List;

public class Expense {

    int expenseId;
    double amount;
    User paidBy; //user id who paid it
    List<Split> splits;
    private SplitType splitType;


    public Expense(int expenseId, User paidBy, double amount, SplitType splitType, List<Split> splits) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.splitType = splitType;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public double getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public SplitType getSplitType() {
        return splitType;
    }
}
