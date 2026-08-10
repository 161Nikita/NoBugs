package common.storage;

import models.CreateUserRequest;
import models.CreateAccountResponse;
import requests.skelethon.steps.UserSteps;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SessionStorage {
    /**
     * Thread Local - способ сделать SessionStorage потокобезопасным
     *
     * Каждый потом обращаясь к INSTANCE.get() получают свою КОПИЮ
     *
     * Map<Thread, SessionStorage>
     *
     */
    private static final ThreadLocal<SessionStorage> INSTANCE = ThreadLocal.withInitial(SessionStorage::new);

    private final List<CreateUserRequest> usersList = new ArrayList<>();

    private final LinkedHashMap<CreateUserRequest, UserSteps> userStepsMap = new LinkedHashMap<>();

    private final LinkedHashMap<CreateUserRequest, List<CreateAccountResponse>> userAccountsMap = new LinkedHashMap<>();

    private SessionStorage() {}

    public static void addUsers(List<CreateUserRequest> users) {
        INSTANCE.get().usersList.addAll(users);
        for (CreateUserRequest user : users) {
            INSTANCE.get().userStepsMap.put(user, new UserSteps(user.getUsername(), user.getPassword()));
        }
    }
    public static UserSteps getSteps(int number) {
        if (INSTANCE.get().userStepsMap.isEmpty() || number > INSTANCE.get().userStepsMap.size()) {
            throw new RuntimeException("Шаги для пользователя под номером " + number + " не найдены в SessionStorage!");
        }
        return new ArrayList<>(INSTANCE.get().userStepsMap.values()).get(number - 1);
    }
    public static UserSteps getSteps() {
        return getSteps(1);
    }

    // Сохранить счет для пользователя (добавляет в список счетов этого юзера)
    public static void saveAccount(CreateUserRequest user, CreateAccountResponse account) {
        INSTANCE.get().userAccountsMap
                .computeIfAbsent(user, k -> new ArrayList<>())
                .add(account);
    }

    // Получить список всех счетов конкретного пользователя
    public static List<CreateAccountResponse> getAccounts(CreateUserRequest user) {
        return INSTANCE.get().userAccountsMap.getOrDefault(user, new ArrayList<>());
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
        if (INSTANCE.get().usersList.isEmpty() || number > INSTANCE.get().usersList.size()) {
            throw new RuntimeException("Пользователь под номером " + number + " не найден в SessionStorage!");
        }
        return INSTANCE.get().usersList.get(number - 1);
    }

    public static CreateUserRequest getUser() {
        return getUser(1);
    }

    public static void clear() {
        INSTANCE.get().usersList.clear();
        INSTANCE.get().userStepsMap.clear();
        INSTANCE.get().userAccountsMap.clear();
    }
}
