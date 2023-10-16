package de.gedoplan.showcase.domain;

import java.time.LocalDate;
import java.time.Period;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private LocalDate birthDay;

  public Person(String name, LocalDate birthDay) {
    this.name = name;
    this.birthDay = birthDay;
  }

  @JsonbTransient
  public int getAge() {
    return Period
      .between(this.birthDay, LocalDate.now())
      .getYears();
  }

}