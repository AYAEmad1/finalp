/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author islam-bilisim
 */
@Entity
@Table(name = "organization")
public class organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int org_id;

    public organization() {
    }

   
    public organization(int org_id, String name, String type, String contact_info) {
        this.org_id = org_id;
        this.name = name;
        this.type = type;
        this.contact_info = contact_info;
    }

    public void setOrg_id(int org_id) {
        this.org_id = org_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    public int getOrg_id() {
        return org_id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getContact_info() {
        return contact_info;
    }
    private String name;
    private String type;
    private String contact_info;
    @OneToMany(mappedBy = "organization")
    private List<user> users ;
    
    
}
