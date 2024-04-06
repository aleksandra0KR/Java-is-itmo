package ru.aleksandra0KR.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
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

  @Column(name = "name")
  private String name;

  @Column(name = "birthdate")
  private LocalDate birthdate;

  @OneToMany(mappedBy = "person", orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Cat> cats = new ArrayList<>();

  public Person(String name, LocalDate birthdate) {
    this.name = name;
    this.birthdate = birthdate;
  }

  public void addCat(Cat cat) {
    if (!cats.contains(cat)) {
      cats.add(cat);
    }
  }

}

