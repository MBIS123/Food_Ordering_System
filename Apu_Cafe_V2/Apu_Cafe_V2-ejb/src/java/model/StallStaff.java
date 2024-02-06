/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author User
 */
@Entity
public class StallStaff implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    public Long getId() {
        return id;
    }

    public StallStaff() {
    }
    ;

    @ManyToOne
    @JoinColumn(name = "stall_id")
    private Stall stall;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoles role = UserRoles.STALL_STAFF;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    
     public void setUserAccountPasword(String password) {
        this.userAccount.setPassword(password);
    }

    
    private String city;

    private String addressLine1;

    @Column(nullable = true)
    private String addressLine2;

    private String gender;

    private String race;

    private int age;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Stall getStall() {
        return stall;
    }

    public void setStall(Stall stall) {
        this.stall = stall;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return (userAccount != null) ? userAccount.getUsername() : null;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }
    
     public String getStallName() {
        return stall != null ? stall.getStoreName(): null;
    }
     
        public Long getStallId() {
        return stall != null ? stall.getId(): null;
    }

    public String getPassword() {
        return userAccount != null ? userAccount.getPassword() : null;
    }

    public Long getUserAccountID() {
        return userAccount != null ? userAccount.getId() : null;
    }

    public StallStaff(
            String username, String password, String fullName, String email,
            String phoneNumber, String role,
            String city, String addressLine1, String addressLine2,
            String gender, String race, int age, Stall stall
    ) {
        this.userAccount = new UserAccount();
        this.userAccount.setUsername(username);
        this.userAccount.setPassword(password);
        this.userAccount.setRole(role);
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        // additional parameters
        this.city = city;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.gender = gender;
        this.race = race;
        this.age = age;
        this.stall = stall;

    }



    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StallStaff)) {
            return false;
        }
        StallStaff other = (StallStaff) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getAssociatedStoreName() {
        if (this.stall != null) {
            return this.stall.getStoreName();
        } else {
            return null;
        }
    }
    
    

    @Override
    public String toString() {
        return "entities.StallStaff[ id=" + id + " ]";
    }

}
