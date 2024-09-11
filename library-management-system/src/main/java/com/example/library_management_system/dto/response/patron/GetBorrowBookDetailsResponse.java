package com.example.library_management_system.dto.response.patron;

import com.example.library_management_system.entity.BookEntity;
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
public class GetBorrowBookDetailsResponse {

    @JsonProperty("book")
    private Long bookId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("due_date")
    private Date dueDate;

}
