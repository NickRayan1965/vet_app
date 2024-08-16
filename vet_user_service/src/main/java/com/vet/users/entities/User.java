package com.vet.users.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"authorities", "accountNonExpired", "accountNonLocked", "credentialsNonExpired"})
@Table("users")
public class User implements UserDetails{
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

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (role == null) {
      return List.of();
    }
    return List.of(new SimpleGrantedAuthority(role.name()));
  }
}
