package common.storage;

import models.CreateUserRequest;
import models.CreateAccountResponse;
import requests.skelethon.steps.UserSteps;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SessionStorage {
    private static final SessionStorage INSTANCE = new SessionStorage();

    private final List<CreateUserRequest> usersList = new ArrayList<>();

    private final LinkedHashMap<CreateUserRequest, UserSteps> userStepsMap = new LinkedHashMap<>();

    private final LinkedHashMap<CreateUserRequest, List<CreateAccountResponse>> userAccountsMap = new LinkedHashMap<>();

    private SessionStorage() {}

    public static void addUsers(List<CreateUserRequest> users) {
        INSTANCE.usersList.addAll(users);
        for (CreateUserRequest user : users) {
            INSTANCE.userStepsMap.put(user, new UserSteps(user.getUsername(), user.getPassword()));
        }
    }
    public static UserSteps getSteps(int number) {
        if (INSTANCE.userStepsMap.isEmpty() || number > INSTANCE.userStepsMap.size()) {
            throw new RuntimeException("Шаги для пользователя под номером " + number + " не найдены в SessionStorage!");
        }
        return new ArrayList<>(INSTANCE.userStepsMap.values()).get(number - 1);
    }
    public static UserSteps getSteps() {
        return getSteps(1);
    }

    // Сохранить счет для пользователя (добавляет в список счетов этого юзера)
    public static void saveAccount(CreateUserRequest user, CreateAccountResponse account) {
        INSTANCE.userAccountsMap
                .computeIfAbsent(user, k -> new ArrayList<>())
                .add(account);
    }

    // Получить список всех счетов конкретного пользователя
    public static List<CreateAccountResponse> getAccounts(CreateUserRequest user) {
        return INSTANCE.userAccountsMap.getOrDefault(user, new ArrayList<>());
    }

    // Получить конкретный счет пользователя по его индексу
    public static CreateAccountResponse getAccount(CreateUserRequest user, int accountIndex) {
        List<CreateAccountResponse> accounts = getAccounts(user);
        if (accounts.isEmpty() || accountIndex >= accounts.size()) {
            throw new RuntimeException("Счет с индексом " + accountIndex + " не найден для пользователя " + user.getUsername());
        }
        return accounts.get(accountIndex);
    }

    // Получить самый первый счет самого первого пользователя
    public static CreateAccountResponse getAccount() {
        return getAccount(getUser(1), 0);
    }

    public static CreateUserRequest getUser(int number) {
        if (INSTANCE.usersList.isEmpty() || number > INSTANCE.usersList.size()) {
            throw new RuntimeException("Пользователь под номером " + number + " не найден в SessionStorage!");
        }
        return INSTANCE.usersList.get(number - 1);
    }

    public static CreateUserRequest getUser() {
        return getUser(1);
    }

    public static void clear() {
        INSTANCE.usersList.clear();
        INSTANCE.userStepsMap.clear();
        INSTANCE.userAccountsMap.clear();
    }
}
