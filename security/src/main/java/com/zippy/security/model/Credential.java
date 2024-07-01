package com.zippy.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Collection;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true, fluent = false)
@Table(name = "credential")
public class Credential implements UserDetails{
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credential_id_seq")
  @SequenceGenerator(name = "credential_id_seq", sequenceName = "credential_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name="personal_information_id")
  private long personalInformationId;

  @Column(name="role_id")
  private long roleId;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="role_id", referencedColumnName = "id", updatable = false, insertable = false)
  private Role role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority(("user")));
  }

  @Override
  public boolean isAccountNonExpired() {
      return true     ;
  }

  @Override
  public boolean isAccountNonLocked() {
      return true     ;
  }

  @Override
  public boolean isCredentialsNonExpired() {
      return true     ;
  }

  @Override
  public boolean isEnabled() {
      return true     ;
  }

}
