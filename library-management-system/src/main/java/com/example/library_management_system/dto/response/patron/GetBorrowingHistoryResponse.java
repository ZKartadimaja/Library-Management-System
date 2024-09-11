package com.example.library_management_system.dto.response.patron;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetBorrowingHistoryResponse {
    @JsonProperty("title")
    private String title;

    @JsonProperty("borrowed_date")
    private Date borrowedDate;

    @JsonProperty("returned_date")
    private Date returnedDate;

    @JsonProperty("fine")
    private Double fine;

}
