package ru.aleksandra0KR.entity;

import java.time.LocalDate;
import java.util.List;
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
import lombok.ToString;
import javax.persistence.Entity;

@Data
@ToString

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cats")
@Entity
public class Cat {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String name;

  private String color;

  private String breed;

  private LocalDate birthday;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private Person owner;

  @ManyToMany
  @JoinTable(name = "Friends",
      joinColumns = @JoinColumn(name = "cat_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
  private List<Cat> friends;


  public Cat(String name, String color, String breed, LocalDate birthday, Person owner) {
    this.name = name;
    this.breed = breed;
    this.color = color;
    this.birthday = birthday;
    this.owner = owner;

  }

}


