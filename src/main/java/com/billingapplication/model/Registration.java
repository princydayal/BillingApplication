package com.billingapplication.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String username;
    private String password;
    private String profileImage;

    @Column(nullable = false, updatable = false) // Make the date unchangeable after initial save
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private Erole role;

    // This method is called automatically before saving a new entity to set the current date
    @PrePersist
    protected void onCreate() {
        this.date = LocalDateTime.now(); // Sets the current date and time
    }
}
