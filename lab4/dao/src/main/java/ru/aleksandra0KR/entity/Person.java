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
@Table(name = "People")
@Entity
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "person_id")
  private Long person_id;

  @Column(unique = true, nullable = false)
  private String username;

  private String password;

  @OneToOne(mappedBy = "person")
  private Owner owner;

  /*@ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "person_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles = new HashSet<>();*/
  private String roles;

  public Person(String name, String password, String roles) {
    this.username = name;
    this.password = password;
    this.roles = roles;
  }


}

