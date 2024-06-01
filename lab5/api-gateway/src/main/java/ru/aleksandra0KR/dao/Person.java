package ru.aleksandra0KR.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

  private Long ownerId;

  private String roles;

  public Person(String name, String password, String roles, Long ownerId) {
    this.username = name;
    this.password = password;
    this.roles = roles;
    this.ownerId = ownerId;
  }

}

