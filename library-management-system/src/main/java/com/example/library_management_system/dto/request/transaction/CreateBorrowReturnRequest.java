package com.example.library_management_system.dto.request.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBorrowReturnRequest {
    @JsonProperty("patron_id")
    private Long patronId;

    @JsonProperty("book_id")
    private Long bookId;
}
