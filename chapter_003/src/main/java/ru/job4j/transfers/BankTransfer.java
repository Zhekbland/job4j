package ru.job4j.transfers;

import java.util.*;

/**
 * Class BankTransfer does a lot of operations.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 05.11.2018.
 */
public class BankTransfer {

    private Map<User, List<Account>> bankData = new HashMap<>();

    public void addUser(User user) {
        this.bankData.put(user, new ArrayList<>());
    }

    public boolean deleteUser(User user) {
        return this.bankData.remove(user) != null;
    }

    public void addAccountToUser(String passport, Account account) {
        this.bankData.get(this.bankData.keySet().stream()
                .filter(x -> x.getPassport().equals(passport)).findFirst().orElseThrow()).add(account);
    }

    public void deleteAccountFromUser(String passport, Account account) {
        this.bankData.get(this.bankData.keySet().stream()
                .filter(x -> x.getPassport().equals(passport)).findFirst().orElseThrow()).remove(account);
    }

    public List<Account> getUserAccounts(String passport) {
        return this.bankData.get(this.bankData.keySet().stream()
                .filter(x -> x.getPassport().equals(passport)).findFirst().orElseThrow());
    }

    public Account findAccountByRequisites(String passport, String requisite) {
        return getUserAccounts(passport).stream()
                .filter(x -> x.getRequisites().equals(requisite)).findFirst().orElseThrow();
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport,
                                 String destRequisite, double amount) {
        boolean result = false;
        Account srcAccount = findAccountByRequisites(srcPassport, srcRequisite);
        Account destAccount = findAccountByRequisites(destPassport, destRequisite);
        if (srcAccount.getValue() >= amount) {
            srcAccount.setValue(srcAccount.getValue() - amount);
            destAccount.setValue(destAccount.getValue() + amount);
            result = true;
        }
        return result;
    }
}
