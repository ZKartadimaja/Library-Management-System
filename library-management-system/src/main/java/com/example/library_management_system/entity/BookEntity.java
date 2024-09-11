package com.example.library_management_system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "patrons")
@Setter
@Getter
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("title")
    private String title;

    @Column(nullable = false)
    @JsonProperty("author")
    private String author;

    @Column(nullable = false, unique = true)
    @JsonProperty("isbn")
    private String isbn;

    @Column(nullable = false)
    @JsonProperty("quantity")
    private Integer quantity;

    @Column(nullable = false)
    @JsonProperty("available_copies")
    private Integer availableCopies;

    @Column(nullable = false)
    @JsonProperty("crated_at")
    private Date createdAt;

    @Column(nullable = false)
    @JsonProperty("updated_at")
    private Date updatedAt;
}
