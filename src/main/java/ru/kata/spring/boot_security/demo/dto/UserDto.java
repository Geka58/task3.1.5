package ru.kata.spring.boot_security.demo.dto;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Objects;
import java.util.Set;

public class UserDto {
    private Long id;
    private String lastName;

    private String firstName;
    private String email;
    private int age;
    private String password;
    private Set<Role> roles;

    public UserDto() {
    }

    public UserDto(Long id, String lastName, String firstName, String email, int age, String password, Set<Role> roles) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.age = age;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return age == userDto.age && Objects.equals(id, userDto.id) && Objects.equals(lastName, userDto.lastName) && Objects.equals(firstName, userDto.firstName) && Objects.equals(email, userDto.email) && Objects.equals(password, userDto.password) && Objects.equals(roles, userDto.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, email, age, password, roles);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
