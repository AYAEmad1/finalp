/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author islam-bilisim
 */
@Entity
@Table(name = "users")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user-id")
    private int id;

    public user(int id, String user_name, String password, String email, String role, String full_name, organization organization) {
        this.id = id;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.full_name = full_name;
        this.organization = organization;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setOrganization(organization organization) {
        this.organization = organization;
    }

    public int getId() {
        return id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getFull_name() {
        return full_name;
    }

    public organization getOrganization() {
        return organization;
    }
    private String user_name;
    private String password;
    private String email;
    private String role;

    public user() {
    }
    private String full_name;
    @ManyToOne
    @JoinColumn(name = "org_id")
    private organization organization;
    @OneToMany(mappedBy = "coordinator")
    private List<aidDistribution> distributions;
}
