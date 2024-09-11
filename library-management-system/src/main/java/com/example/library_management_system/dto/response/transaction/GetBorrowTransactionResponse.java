package com.example.library_management_system.dto.response.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBorrowTransactionResponse {
    @JsonProperty("due_date")
    public Date dueDate;
}
