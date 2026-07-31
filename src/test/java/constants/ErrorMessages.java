package constants;

public final class ErrorMessages {
    private ErrorMessages() {
    }

    public static final String DEPOSIT_EXCEEDS_LIMIT = "Deposit amount exceeds the 5000 limit";
    public static final String UNAUTHORIZED_ACCOUNT_ACCESS = "Unauthorized access to account";
    public static final String TRANSFER_EXCEEDS_LIMIT = "Transfer amount cannot exceed 10000";
    public static final String INSUFFICIENT_FUNDS = "Invalid transfer: insufficient funds or invalid accounts";
    public static final String INVALID_NAME_FORMAT = "Name must consist of at least two words";
    public static final String PASSWORD_CHANGE_NOT_ALLOWED = "Password update is not allowed via profile endpoint";


}
