/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author islam-bilisim
 */
@Entity
@Table(name = "aid_distribution")
public class aidDistribution {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
   private int distribution_id;
   private LocalDate distribution_date;

    public String getAid_type() {
        return aid_type;
    }

    public void setAid_type(String aid_type) {
        this.aid_type = aid_type;
    }
   private String aid_type;

    public aidDistribution() {
    }

    public aidDistribution(int distribution_id, LocalDate distribution_date,String aid_type) {
        this.distribution_id = distribution_id;
        this.distribution_date = distribution_date;
        this.aid_type = aid_type;
    }
   
  @ManyToOne
  @JoinColumn(name = "family_id")
  private family family;
  @ManyToOne
  @JoinColumn(name = "org_id")
  private organization organization;
  @ManyToOne
  @JoinColumn( name = "distributed_by")
  private user coordinator;

    public void setDistribution_id(int distribution_id) {
        this.distribution_id = distribution_id;
    }

    public void setDistribution_date(LocalDate distribution_date) {
        this.distribution_date = distribution_date;
    }

    public void setFamily(family family) {
        this.family = family;
    }

    public void setOrganization(organization organization) {
        this.organization = organization;
    }

    public void setCoordinator(user coordinator) {
        this.coordinator = coordinator;
    }

    public int getDistribution_id() {
        return distribution_id;
    }

    public LocalDate getDistribution_date() {
        return distribution_date;
    }

    public family getFamily() {
        return family;
    }

    public organization getOrganization() {
        return organization;
    }

    public user getCoordinator() {
        return coordinator;
    }
  
  
}

