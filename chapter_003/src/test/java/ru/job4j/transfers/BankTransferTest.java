package ru.job4j.transfers;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class BankTransferTest tests BankTransfer.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 05.11.2018.
 */
public class BankTransferTest {
    @Test
    public void whenWeGetUserAccounts() {
        BankTransfer bank = new BankTransfer();
        bank.addUser(new User("Alex", "2222 Europe"));
        bank.addAccountToUser("2222 Europe", new Account(30000, "#001"));
        bank.addAccountToUser("2222 Europe", new Account(90000, "#002"));
        List<Account> result = bank.getUserAccounts("2222 Europe");
        List<Account> expect = new ArrayList<>();
        expect.add(new Account(30000, "#001"));
        expect.add(new Account(90000, "#002"));
        assertThat(result, is(expect));
    }

    @Test
    public void whenWeDeleteAccountFromUser() {
        BankTransfer bank = new BankTransfer();
        bank.addUser(new User("Alex", "2222 Europe"));
        bank.addAccountToUser("2222 Europe", new Account(30000, "#001"));
        bank.addAccountToUser("2222 Europe", new Account(90000, "#002"));
        bank.addAccountToUser("2222 Europe", new Account(500, "#003"));
        bank.deleteAccountFromUser("2222 Europe", new Account(90000, "#002"));
        List<Account> result = bank.getUserAccounts("2222 Europe");
        List<Account> expect = new ArrayList<>();
        expect.add(new Account(30000, "#001"));
        expect.add(new Account(500, "#003"));
        assertThat(result, is(expect));
    }

    @Test
    public void whenWeTransferMoney() {
        BankTransfer bank = new BankTransfer();
        bank.addUser(new User("Alex", "2222 Europe"));
        bank.addUser(new User("Evgeny", "2323 Russia"));
        bank.addAccountToUser("2222 Europe", new Account(50, "#001"));
        bank.addAccountToUser("2222 Europe", new Account(30, "#002"));
        bank.addAccountToUser("2323 Russia", new Account(60_000_000, "#111"));
        bank.addAccountToUser("2323 Russia", new Account(30_000_000, "#777"));
        bank.transferMoney("2323 Russia", "#111", "2222 Europe",
                "#001", 800_000);
        bank.transferMoney("2323 Russia", "#777", "2222 Europe",
                "#002", 100_000);
        List<Account> result = bank.getUserAccounts("2222 Europe");
        List<Account> expect = new ArrayList<>();
        expect.add(new Account(800_050, "#001"));
        expect.add(new Account(100_030, "#002"));
        assertThat(result, is(expect));
    }

    @Test
    public void whenWeDeleteUser() {
        BankTransfer bank = new BankTransfer();
        bank.addUser(new User("Alex", "2222 Europe"));
        bank.addAccountToUser("2222 Europe", new Account(30000, "#001"));
        bank.addAccountToUser("2222 Europe", new Account(90000, "#002"));
        bank.addAccountToUser("2222 Europe", new Account(500, "#003"));
        bank.deleteUser(new User("Alex", "2222 Europe"));
        List<Account> result = bank.getUserAccounts("2222 Europe");
        List<Account> expect = new ArrayList<>();
        assertThat(result, is(expect));
    }
}
