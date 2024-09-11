package com.example.library_management_system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
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
    private Timestamp createdAt;

    @Column()
    @JsonProperty("updated_at")
    private Timestamp updatedAt;
}
