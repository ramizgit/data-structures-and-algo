package lld.splitwise.strategy.impl;

import lld.splitwise.model.Expense;
import lld.splitwise.model.PercentageSplit;
import lld.splitwise.model.Split;
import lld.splitwise.strategy.SplitStrategy;

public class PercentageSplitStrategy implements SplitStrategy {
    @Override
    public void calculate(Expense expense) {
        /*
        VALIDATION:-
        double totalPercentage = 0;

        for (Split split : expense.getSplits()) {

            totalPercentage += ((PercentageSplit) split).getPercentage();
        }

        if (Double.compare(totalPercentage, 100) != 0) {
            throw new IllegalArgumentException("Percentage must add up to 100.");
        }*/

        for (Split split : expense.getSplits()) {
            split.setAmount(expense.getAmount() * ((PercentageSplit) split).getPercentage() / 100);
        }
    }
}
