package ru.aleksandra0KR.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Owner")
@Entity
public class Owner{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "owner_id")
  private Long owner_id;

  @Column(name = "name")
  private String name;

  @Column(name = "birthday")
  private LocalDate birthdate;

  @OneToMany(mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Cat> cats = new ArrayList<>();

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "person_id", referencedColumnName = "owner_id")
  private User user;
}

