package lld.splitwise.strategy;

import lld.splitwise.model.Expense;

public interface SplitStrategy {

    void calculate(Expense expense);
}
