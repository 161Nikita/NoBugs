package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserTopUpAccountResponse extends BaseModel{
    private String id;
    private String accountNumber;
    private double balance;
    private double depositAmount;
    private long transactionId;
}
