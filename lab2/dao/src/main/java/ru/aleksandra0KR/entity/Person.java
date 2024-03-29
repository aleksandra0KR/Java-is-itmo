package ru.aleksandra0KR.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Entity;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "People")
@Entity
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "person_id")
  private long person_id;

  @Column(name = "name")
  private String name;

  @Column(name = "birthdate")
  private LocalDate birthdate;

  public Person(String name, LocalDate birthdate) {
    this.name = name;
    this.birthdate = birthdate;
  }
}

