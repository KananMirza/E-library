package com.orient.library.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@DynamicInsert
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    @JoinColumn(name = "user_role_id")
    UserRole userRole;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    String patryonomic;
    String email;
    String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<UserPhone> phones;
    @Column(name = "seria_code")
    String seriaCode;
    @Column(name = "seria_number")
    Integer seriaNumber;
    String fin;
    Integer status;
    @Column(name = "is_deleted")
    Integer isDeleted;
    @Column(name = "created_at")
    LocalDateTime createdAt;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

}
