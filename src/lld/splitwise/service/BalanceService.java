package lld.splitwise.service;

import lld.splitwise.model.Expense;
import lld.splitwise.model.Split;
import lld.splitwise.model.User;

import java.util.HashMap;
import java.util.Map;

/*
Responsibilities:-
    Maintain balances
    Print balances
    Update balances
 */
public class BalanceService {

    /*
    The outer key is the person who should receive money.
    The inner key is the person who owes money.
    The value is how much.
     */
    Map<User, Map<User, Double>> balances = new HashMap<>(); //{to user : {from user : balance}} -> Alice should receive ₹300 from Bob

    public void updateBalances(Expense expense)
    {
        User paidBy = expense.getPaidBy();

        for (Split split : expense.getSplits()) {
            if (split.getUser().equals(paidBy)) {
                continue;
            }

            addBalance(paidBy, split.getUser(), split.getAmount());
        }

    }

    private void addBalance(User receiver, User debtor, double amount)
    {
        balances.putIfAbsent(receiver, new HashMap<>());

        Map<User, Double> debtorMap = balances.get(receiver);

        debtorMap.put(debtor, debtorMap.getOrDefault(debtor, 0.0) + amount);
    }

    public void printBalances() {

        for (Map.Entry<User, Map<User, Double>> receiverEntry : balances.entrySet()) {

            User receiver = receiverEntry.getKey();

            Map<User, Double> debtorMap = receiverEntry.getValue();

            for (Map.Entry<User, Double> debtorEntry : debtorMap.entrySet()) {

                User debtor = debtorEntry.getKey();

                Double amount = debtorEntry.getValue();

                System.out.println(
                        debtor.getName()
                                + " owes "
                                + receiver.getName()
                                + " : "
                                + amount);
            }
        }
    }

}
