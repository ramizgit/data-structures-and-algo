package lld.splitwise.model;

import lld.splitwise.enums.SplitType;

import java.util.List;

public class Expense {

    int expenseId;
    double amount;
    int paidBy; //user id who paid it
    List<Split> splits;
    private SplitType splitType;


}
