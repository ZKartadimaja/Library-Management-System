package com.example.library_management_system.dto.response.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAvailableBookCopies {

    @JsonProperty("title")
    private String title;

    @JsonProperty("available_copies")
    private Integer availableCopies;
}
