package com.example.library_management_system.dto.response.patron;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCurrentBorrowedResponse {
    @JsonProperty("title")
    private String title;

    @JsonProperty("borrowed_date")
    private Date borrowedDate;

    @JsonProperty("due_date")
    private Date dueDate;
}
