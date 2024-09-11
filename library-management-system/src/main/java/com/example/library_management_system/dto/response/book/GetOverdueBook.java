package com.example.library_management_system.dto.response.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetOverdueBook {
    @JsonProperty("book_title")
    private String bookTitle;

    @JsonProperty("patron_name")
    private String patronName;

    @JsonProperty("due_date")
    private Date dueDate;

    @JsonProperty("days_overdue")
    private Long daysOverdue;

    @JsonProperty("fine")
    private Double fine;

}
