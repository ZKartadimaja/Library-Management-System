package com.example.library_management_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Entity
@Table(name = "transactions")
@Setter
@Getter
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patron_id", referencedColumnName = "id", nullable = false)
    @JsonProperty("patron")
    private PatronEntity patronId;

    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    @JsonProperty("book")
    private BookEntity bookId;

    @Column(nullable = false)
    @JsonProperty("borrowed_date")
    private Date borrowedDate;

    @Column(nullable = false)
    @JsonProperty("due_date")
    private Date dueDate;

    @Column(nullable = false)
    @JsonProperty("returned_date")
    private Date returnedDate;

    @Column(nullable = false)
    @JsonProperty("fine")
    private Double fine;

}
