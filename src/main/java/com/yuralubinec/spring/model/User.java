package com.yuralubinec.spring.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The User class represents {@code User} entity
 * stored in the database.
 */

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -8449816156899969474L;

    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String userRole = UserRole.CUSTOMER.getUserRole();
    private List<Item> userItems = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotBlank
    @Column(name = "login", length = 75, unique = true, nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @NotBlank
    @Column(name = "password", length = 32, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank
    @Column(name = "name", length = 75, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    @Column(name = "surname", length = 75, nullable = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "user_role", length = 15, nullable = false)
    public String getUser_role() {
        return userRole;
    }

    public void setUser_role(String user_role) {
        this.userRole = user_role;
    }

    @ManyToMany
    @JoinTable(name = "user_item", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "item_id") })
    public List<Item> getUserItems() {
        return userItems;
    }

    public void setUserItems(List<Item> userItems) {
        this.userItems = userItems;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, true);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
