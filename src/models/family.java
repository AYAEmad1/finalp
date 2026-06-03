/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author islam-bilisim
 */
@Entity
public class family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int family_id;

    public family() {
    }

    public family(int family_id, String household_name, String phone, String location, int family_size, String national_id, String vulnerability_level, LocalDate registeration_date, LocalDate last_aid_date) {
        this.family_id = family_id;
        this.household_name = household_name;
        this.phone = phone;
        this.location = location;
        this.family_size = family_size;
        this.national_id = national_id;
        this.vulnerability_level = vulnerability_level;
        this.registeration_date = registeration_date;
        this.last_aid_date = last_aid_date;
    }

    public void setFamily_id(int family_id) {
        this.family_id = family_id;
    }

    public void setHousehold_name(String household_name) {
        this.household_name = household_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFamily_size(int family_size) {
        this.family_size = family_size;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public void setVulnerability_level(String vulnerability_level) {
        this.vulnerability_level = vulnerability_level;
    }

    public void setRegisteration_date(LocalDate registeration_date) {
        this.registeration_date = registeration_date;
    }

    public void setLast_aid_date(LocalDate last_aid_date) {
        this.last_aid_date = last_aid_date;
    }

    public int getFamily_id() {
        return family_id;
    }

    public String getHousehold_name() {
        return household_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public int getFamily_size() {
        return family_size;
    }

    public String getNational_id() {
        return national_id;
    }

    public String getVulnerability_level() {
        return vulnerability_level;
    }

    public LocalDate getRegisteration_date() {
        return registeration_date;
    }

    public LocalDate getLast_aid_date() {
        return last_aid_date;
    }
    private String household_name;
    private String phone;
    private String location;
    private int family_size;
    private String national_id;
    private String vulnerability_level;
    private LocalDate registeration_date;
    private LocalDate last_aid_date;	
   @OneToMany(mappedBy = "family")
   private List<aidDistribution> distributions;
}
