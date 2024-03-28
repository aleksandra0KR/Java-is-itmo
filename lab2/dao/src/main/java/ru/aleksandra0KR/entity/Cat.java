package ru.aleksandra0KR.entity;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Entity;

@Data
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cats", schema = "TheCatEmpire")
public class Cat {

  @Id
  @GeneratedValue
  private long id;

  private String name;

  private String color;

  private String breed;

  @Column(name = "date_of_birth")
  private LocalDate birthday;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private Person owner;

  public Cat(String name, String  color, String breed, LocalDate birthday, Person owner){
    this.name = name;
    this.breed = breed;
    this.color = color;
    this.birthday = birthday;
    this.owner = owner;

  }

  /*
  @ManyToMany
  @JoinTable(name = "Friends", schema = "TheCatEmpire",
      joinColumns = @JoinColumn(name = "cat_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "cat_friend_id", referencedColumnName = "id"))
  private List<Cat> friends;
  */

}


