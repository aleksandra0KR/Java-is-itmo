package ru.aleksandra0KR.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
  private long person_id;

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

