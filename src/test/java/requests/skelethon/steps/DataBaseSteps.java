package requests.skelethon.steps;

import helpers.StepLogger;
import iteration2.api.dao.AccountDao;
import iteration2.api.dao.UserDao;
import iteration2.api.database.Condition;
import iteration2.api.database.DBRequest;

public class DataBaseSteps {

    public enum Table {
        CUSTOMERS("customers"),
        ACCOUNTS("accounts");

        private final String name;

        Table(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static UserDao getUserByUsername(String username) {
        return StepLogger.log("Get user from database by username: " + username, () -> {
            return DBRequest.builder()
                    .requestType(DBRequest.RequestType.SELECT)
                    .table(Table.CUSTOMERS.getName())
                    .where(Condition.equalTo("username", username))
                    .extractAs(UserDao.class);
        });
    }

    public static AccountDao getAccountById(long accountId) {
        return StepLogger.log("Get account from database by id: " + accountId, () -> {
            return DBRequest.builder()
                    .requestType(DBRequest.RequestType.SELECT)
                    .table(Table.ACCOUNTS.getName())
                    .where(Condition.equalTo("id", accountId))
                    .extractAs(AccountDao.class);
        });
    }
}