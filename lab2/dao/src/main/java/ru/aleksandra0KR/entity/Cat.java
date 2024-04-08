package ru.aleksandra0KR.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cats")
@Entity
public class Cat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  private String color;

  private String breed;

  private LocalDate birthday;

  @ManyToOne(fetch = FetchType.EAGER)
  private Person person;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "friends",
      joinColumns = @JoinColumn(name = "cat_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
  private List<Cat> friends = new ArrayList<>();

  public Cat(String name, String color, String breed, LocalDate birthday, Person owner) {
    this.name = name;
    this.breed = breed;
    this.color = color;
    this.birthday = birthday;
    this.person = owner;
  }

  public Cat(Long id, String name, String color, String breed, LocalDate birthday, Person owner) {
    this.id = id;
    this.name = name;
    this.breed = breed;
    this.color = color;
    this.birthday = birthday;
    this.person = owner;
  }

  public void addFriend(Cat cat) {
    if (!friends.contains(cat)) {
      friends.add(cat);
    }
  }

}


