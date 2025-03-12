package org.example.user;

import org.example.account.Account;
import org.example.account.AccountService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {

    private final AccountService accountService;
    private final Map<Integer, User> usersMap;
    private final Set<String> userLogins;
    private int idCounter;

    public UserService(AccountService accountService) {
        this.accountService = accountService;
        this.usersMap = new HashMap<>();
        this.userLogins = new HashSet<>();
        this.idCounter = 0;
    }

    public User createUser(String login) {
        if (userLogins.add(login)) {
            idCounter++;
            User user = new User(idCounter, login, new ArrayList<>());
            usersMap.put(user.getId(), user);
            Account account = accountService.createAccount(user);
            user.getAccountList().add(account);
            return user;
        } else {
            throw new IllegalArgumentException("User " + login + " already exists");
        }
    }

    public Optional<User> findUserById(int id) {
        return Optional.ofNullable(usersMap.get(id));
    }

    public List<User> getAllUsers() {
        return usersMap.values().stream().toList();
    }

}