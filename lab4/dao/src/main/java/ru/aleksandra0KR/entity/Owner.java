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
@Table(name = "Owners")
@Entity
public class Owner {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "owner_id")
  private Long owner_id;

  private String name;

  private LocalDate birthday;

  @OneToMany(mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Cat> cats = new ArrayList<>();

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "owner_id", referencedColumnName = "person_id")
  private Person person;

}

