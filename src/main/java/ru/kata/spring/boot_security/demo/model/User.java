package ru.kata.spring.boot_security.demo.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotNull
    @NotEmpty
    private String username;
    @Column(name = "last_name")
    @NotNull
    @NotEmpty
    private String lastName;
    @Column(name = "age")
    @NotNull
    @Min(value = 0, message = "Возрост не может быть меньше нуля")
    @Max(value = 100, message = "Максимальный возрост не привышает 100 лет")
    private int age;
    @Column(name = "password")
    @NotNull
    @NotEmpty
    private String password;
    @Column(name = "email")
    @Email(message = "адресс не существует")
    @NotNull
    @NotEmpty(message = " Адрес не должен быть пустым")
    private String email;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;


    public User(String name, String lastName, int age, String email) {
        this.username = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
