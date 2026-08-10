package iteration2.ui.pages;


import lombok.Getter;

@Getter
public enum BankAlert {
    TOP_UP_LIMIT_EXCEEDED("❌ Please deposit less or equal to 5000$."),
    TOP_UP_DEPOSIT_ERROR("❌ Failed to deposit. Please try again."),
    TOP_UP_DEPOSIT_SUCCESSFUL(" Deposit successful!"),
    NAME_UPDATED("✅ Name updated successfully!"),
    INVALID_NAME_FORMAT("Name must contain two words with letters only"),
    INVALID_NAME_FORMAT_CORRECT("[object Object]");

    private final String message;
    BankAlert(String message) {
        this.message = message;
    }
}
