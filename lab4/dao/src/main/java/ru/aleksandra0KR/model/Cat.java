package ru.aleksandra0KR.model;

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
@Table(name = "Cats")
@Entity
public class Cat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String color;

  private String breed;

  private LocalDate birthday;

  @ManyToOne(fetch = FetchType.EAGER)
  private Owner owner;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "friends",
      joinColumns = @JoinColumn(name = "cat_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
  private List<Cat> friends = new ArrayList<>();

  public void addFriend(Cat cat) {
    if (!friends.contains(cat)) {
      friends.add(cat);
    }
  }

}


