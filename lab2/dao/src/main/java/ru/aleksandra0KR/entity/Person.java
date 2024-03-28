package ru.aleksandra0KR.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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
@Table(name = "People", schema = "TheCatEmpire")
public class Person {

  @Id
  @GeneratedValue
  private long id;

  private String name;

  @Column(name = "date_of_birth")
  private Date birthdate;

  //@OneToMany(mappedBy = "user", orphanRemoval = true)
  //private List<Cat> cats;
}

