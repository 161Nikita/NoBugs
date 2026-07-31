package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CreateAccountResponse extends BaseModel {
    private long id;
    private String accountNumber;
    private double balance;

}
