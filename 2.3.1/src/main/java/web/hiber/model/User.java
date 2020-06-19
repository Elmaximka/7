package web.hiber.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private Long age;

    @ManyToMany(cascade = {CascadeType.ALL, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Collection<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String name, String password, String lastName, String email, Long age, Role role) {
        this.name = name;
        this.password = password;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        addRole(role);
    }

    public User(String username, String password, Collection<Role> authorities) {
        name = username;
        this.password = password;
        roles = authorities;
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getUserRoles().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUserRoles().remove(this);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRole() {
        return roles;
    }

    public void setRoles(String role) {
        roles.add(new Role(role));
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return name + " " + lastName + " " + email + " " + age + " " + password + " "
                + roles;
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
        return name;
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

}