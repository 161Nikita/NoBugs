package iteration2.api.database;

import configs.Config;
import iteration2.api.dao.AccountDao;
import iteration2.api.dao.UserDao;
import lombok.Builder;
import lombok.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class DBRequest {
    private RequestType requestType;
    private String table;
    private List<Condition> conditions;
    private Map<String, Object> updateValues; // Поля и значения для UPDATE
    private Class<?> extractAsClass;

    public enum RequestType {
        SELECT, INSERT, UPDATE, DELETE
    }

    @FunctionalInterface
    private interface RowMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }

    private static final Map<Class<?>, RowMapper<?>> MAPPERS = new HashMap<>();

    static {
        MAPPERS.put(UserDao.class, DBRequest::mapToUserDao);
        MAPPERS.put(AccountDao.class, DBRequest::mapToAccountDao);
    }

    public <T> T extractAs(Class<T> clazz) {
        this.extractAsClass = clazz;
        return executeQuery(clazz);
    }

    private <T> T executeQuery(Class<T> clazz) {
        String sql = buildSQL();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            int paramIndex = 1;

            if (requestType == RequestType.UPDATE && updateValues != null) {
                for (Object value : updateValues.values()) {
                    statement.setObject(paramIndex++, value);
                }
            }

            if (conditions != null) {
                for (Condition condition : conditions) {
                    statement.setObject(paramIndex++, condition.getValue());
                }
            }

            if (requestType == RequestType.DELETE || requestType == RequestType.UPDATE) {
                statement.executeUpdate();
                return null;
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                RowMapper<T> mapper = (RowMapper<T>) MAPPERS.get(clazz);

                if (mapper == null) {
                    throw new UnsupportedOperationException("Mapping for " + clazz.getSimpleName() + " not implemented");
                }

                return mapper.map(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database query failed", e);
        }
    }

    private static UserDao mapToUserDao(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return UserDao.builder()
                    .id(resultSet.getLong("id"))
                    .username(resultSet.getString("username"))
                    .password(resultSet.getString("password"))
                    .role(resultSet.getString("role"))
                    .name(resultSet.getString("name"))
                    .build();
        }
        return null;
    }

    private static AccountDao mapToAccountDao(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return AccountDao.builder()
                    .id(resultSet.getLong("id"))
                    .accountNumber(resultSet.getString("account_number"))
                    .balance(resultSet.getDouble("balance"))
                    .customerId(resultSet.getLong("customer_id"))
                    .build();
        }
        return null;
    }

    private String buildSQL() {
        StringBuilder sql = new StringBuilder();

        switch (requestType) {
            case SELECT:
                sql.append("SELECT * FROM ").append(table);
                appendWhereClause(sql);
                break;
            case DELETE:
                sql.append("DELETE FROM ").append(table);
                appendWhereClause(sql);
                break;
            case UPDATE:
                sql.append("UPDATE ").append(table).append(" SET ");
                if (updateValues == null || updateValues.isEmpty()) {
                    throw new IllegalArgumentException("Update values cannot be empty for UPDATE request");
                }
                int i = 0;
                for (String column : updateValues.keySet()) {
                    if (i > 0) sql.append(", ");
                    sql.append(column).append(" = ?");
                    i++;
                }
                appendWhereClause(sql);
                break;
            default:
                throw new UnsupportedOperationException("Request type " + requestType + " not implemented");
        }

        return sql.toString();
    }

    private void appendWhereClause(StringBuilder sql) {
        if (conditions != null && !conditions.isEmpty()) {
            sql.append(" WHERE ");
            for (int i = 0; i < conditions.size(); i++) {
                if (i > 0) sql.append(" AND ");
                sql.append(conditions.get(i).getColumn()).append(" ").append(conditions.get(i).getOperator()).append(" ?");
            }
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                Config.getProperty("db.url"),
                Config.getProperty("db.username"),
                Config.getProperty("db.password")
        );
    }

    public static DBRequestBuilder builder() {
        return new DBRequestBuilder();
    }

    public static class DBRequestBuilder {
        private RequestType requestType;
        private String table;
        private List<Condition> conditions = new ArrayList<>();
        private Map<String, Object> updateValues = new HashMap<>();
        private Class<?> extractAsClass;

        public DBRequestBuilder requestType(RequestType requestType) {
            this.requestType = requestType;
            return this;
        }

        public DBRequestBuilder where(Condition condition) {
            this.conditions.add(condition);
            return this;
        }

        public DBRequestBuilder table(String table) {
            this.table = table;
            return this;
        }

        // МЕТОД ДЛЯ УСТАНОВКИ ПОЛЕЙ ОБНОВЛЕНИЯ
        public DBRequestBuilder set(String column, Object value) {
            this.updateValues.put(column, value);
            return this;
        }

        public <T> T extractAs(Class<T> clazz) {
            this.extractAsClass = clazz;
            DBRequest request = DBRequest.builder()
                    .requestType(requestType)
                    .table(table)
                    .conditions(conditions)
                    .updateValues(updateValues)
                    .extractAsClass(extractAsClass)
                    .build();
            return request.extractAs(clazz);
        }
    }
}