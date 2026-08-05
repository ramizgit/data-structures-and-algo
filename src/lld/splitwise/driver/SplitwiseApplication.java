package lld.splitwise.driver;

import lld.splitwise.enums.SplitType;
import lld.splitwise.model.EqualSplit;
import lld.splitwise.model.Expense;
import lld.splitwise.model.Split;
import lld.splitwise.model.User;
import lld.splitwise.service.BalanceService;
import lld.splitwise.service.ExpenseService;
import lld.splitwise.strategy.SplitStrategy;
import lld.splitwise.strategy.impl.EqualSplitStrategy;
import lld.splitwise.strategy.impl.PercentageSplitStrategy;

import java.util.*;

public class SplitwiseApplication {

    public static void main(String[] args) {

        // Create Users
        User alice = new User(1, "Alice");
        User bob = new User(2, "Bob");
        User charlie = new User(3, "Charlie");

        // Register Strategies
        Map<SplitType, SplitStrategy> strategies = new HashMap<>();
        strategies.put(SplitType.EQUAL, new EqualSplitStrategy());
        strategies.put(SplitType.PERCENTAGE, new PercentageSplitStrategy());

        // Create Services
        BalanceService balanceService = new BalanceService();
        ExpenseService expenseService = new ExpenseService(strategies, balanceService);

        // Alice paid ₹900
        List<Split> splits = new ArrayList<>();
        splits.add(new EqualSplit(alice));
        splits.add(new EqualSplit(bob));
        splits.add(new EqualSplit(charlie));

        Expense expense = new Expense(1, alice, 900, SplitType.EQUAL, splits);

        expenseService.addExpense(expense);

        balanceService.printBalances();
    }
}
