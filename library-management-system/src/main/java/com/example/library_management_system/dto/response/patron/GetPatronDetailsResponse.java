package com.example.library_management_system.dto.response.patron;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPatronDetailsResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("membership_type")
    private String membershipType;

    @JsonProperty("borrowed_books")
    private List<GetBorrowBookDetailsResponse> borrowedBooks;

}
