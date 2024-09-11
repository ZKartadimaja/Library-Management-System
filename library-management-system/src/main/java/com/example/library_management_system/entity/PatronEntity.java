package com.example.library_management_system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "patrons")
@Setter
@Getter
public class PatronEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(nullable = false, unique = true)
    @JsonProperty("email")
    private String email;

    @Column(nullable = false)
    @JsonProperty("membership_type")
    private String membershipType;

    @Column( nullable = false)
    @JsonProperty("crated_at")
    private Timestamp createdAt;

    @Column()
    @JsonProperty("updated_at")
    private Timestamp updatedAt;

}
