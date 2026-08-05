package lld.splitwise.service;

/*
Responsibilities:
    Create expense
    Call strategy
    Update balances

Flow:-
    Expense arrives
          |
    Find Strategy
          |
    Calculate / Validate
          |
    Update balances
          |
    Done
 */

import lld.splitwise.enums.SplitType;
import lld.splitwise.model.Expense;
import lld.splitwise.strategy.SplitStrategy;

import java.util.Map;

public class ExpenseService {

    private Map<SplitType, SplitStrategy> strategies;
    private BalanceService balanceService;

    public ExpenseService(Map<SplitType, SplitStrategy> strategies, BalanceService balanceService) {
        this.strategies = strategies;
        this.balanceService = balanceService;
    }

    public void addExpense(Expense expense)
    {
        SplitStrategy strategy = strategies.get(expense.getSplitType());

        strategy.calculate(expense);

        balanceService.updateBalances(expense);

    }

}
