package lld.splitwise.strategy.impl;

import lld.splitwise.model.Expense;
import lld.splitwise.model.Split;
import lld.splitwise.strategy.SplitStrategy;

public class EqualSplitStrategy implements SplitStrategy {
    @Override
    public void calculate(Expense expense) {

        double share = expense.getAmount() / expense.getSplits().size();

        for (Split split : expense.getSplits()) {
            split.setAmount(share);
        }
    }
}
