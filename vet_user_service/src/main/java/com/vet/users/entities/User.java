package com.vet.users.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("users")
public class User {
  @Id
  private Long id;

  //varchar 50 not null unique
  @Column("username")
  private String username;

  //varchar 100 not null
  @Column("password")
  private String password;

  @Column("names")
  private String names;

  @Column("surnames")
  private String surnames;

  //varchar 30 not null default 'USER'
  @Column("role")
  private Role role;

  //boolean not null default true
  @Column("enabled")
  private Boolean enabled;
}
