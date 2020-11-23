package org.example.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Table(name = "address")
@Entity
public class AddressEntity {

  public AddressEntity() {
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private int id;
  @Column(nullable = false)
  private String name;

}
