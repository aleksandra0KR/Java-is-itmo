package ru.aleksandra0KR.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long person_id;

  private String username;

  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  private Set<Role> roles = new HashSet<>();

  @OneToOne(mappedBy = "user")
  private Owner owner;

}

