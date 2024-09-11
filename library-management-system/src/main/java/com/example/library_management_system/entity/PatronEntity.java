package com.example.library_management_system.entity;

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

    @Column(nullable = false)
    @JsonProperty("email")
    private String email;

    @Column(nullable = false)
    @JsonProperty("membership_type")
    private String membershipType;

    @Column(nullable = false)
    @JsopProperty("crated_at")
    private Date createdAt;

    @Column(nullavle = false)
    @JsonProperty('updated_at')
    private Date updatedAt;

}
