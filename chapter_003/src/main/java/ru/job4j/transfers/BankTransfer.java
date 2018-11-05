package ru.job4j.transfers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class BankTransfer does a lot of operations.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 05.11.2018.
 */
public class BankTransfer {

    private Map<User, List<Account>> bankData = new HashMap<>();

    public void addUser(User user) {
        this.bankData.put(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        for (Map.Entry<User, List<Account>> bank : this.bankData.entrySet()) {
            if (bank.getKey().equals(user)) {
                this.bankData.remove(bank.getKey());
                break;
            }
        }
    }

    public void addAccountToUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> bank : this.bankData.entrySet()) {
            if (bank.getKey().getPassport().equals(passport)) {
                bank.getValue().add(account);
                break;
            }
        }
    }

    public void deleteAccountFromUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> bank : this.bankData.entrySet()) {
            if (bank.getKey().getPassport().equals(passport)) {
                bank.getValue().remove(account);
                break;
            }
        }
    }

    public List<Account> getUserAccounts(String passport) {
        List<Account> accounts = new ArrayList<>();
        for (Map.Entry<User, List<Account>> bank : this.bankData.entrySet()) {
            if (bank.getKey().getPassport().equals(passport)) {
                accounts.addAll(bank.getValue());
                break;
            }
        }
        return accounts;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport,
                                 String destRequisite, double amount) {
        boolean result = false;
        for (Account accountSrc : getUserAccounts(srcPassport)) {
            if (accountSrc.getRequisites().equals(srcRequisite)
                    && accountSrc.getValue() >= amount) {
                for (Account accountDest : getUserAccounts(destPassport)) {
                    if (accountDest.getRequisites().equals(destRequisite)) {
                        accountDest.setValue(accountDest.getValue() + amount);
                        accountSrc.setValue(accountSrc.getValue() - amount);
                        result = true;
                        break;
                    }
                }
                break;
            }
        }
        return result;
    }
}
