package techno_kryon.spring_boot.Entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer empId;

    @Column(name = "name")
    private String empName;

    @Column(name = "email")
    private String empEmail;

    @Column(name = "age")
    private Integer empAge;

    @Column(name = "phone")
    private String empPhone;

    @Column(name = "created on")
    private String empCreatedOn;

    @Column(name = "created by")
    private String empCreatedBy;

    @Column(name = "modified on")
    private String empModifiedOn;

    @Column(name = "modified by")
    private String empModifiedBy;

    @Column(name = "profile")
    private String empProfile;

    @Column(name = "password")
    private String empPassword;

    public Employee() {
    }

    public Employee(Integer empId, String empName, String empEmail, Integer empAge, String empPhone, String empCreatedOn, String empCreatedBy, String empModifiedOn, String empModifiedBy, String empProfile, String empPassword) {
        this.empId = empId;
        this.empName = empName;
        this.empEmail = empEmail;
        this.empAge = empAge;
        this.empPhone = empPhone;
        this.empCreatedOn = empCreatedOn;
        this.empCreatedBy = empCreatedBy;
        this.empModifiedOn = empModifiedOn;
        this.empModifiedBy = empModifiedBy;
        this.empProfile = empProfile;
        this.empPassword = empPassword;
    }

    // Getters and Setters
    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public Integer getEmpAge() {
        return empAge;
    }

    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }

    public String getEmpCreatedOn() {
        return empCreatedOn;
    }

    public void setEmpCreatedOn(String empCreatedOn) {
        this.empCreatedOn = empCreatedOn;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEmpCreatedBy() {
        return empCreatedBy;
    }

    public void setEmpCreatedBy(String empCreatedBy) {
        this.empCreatedBy = empCreatedBy;
    }

    public String getEmpModifiedOn() {
        return empModifiedOn;
    }

    public void setEmpModifiedOn(String empModifiedOn) {
        this.empModifiedOn = empModifiedOn;
    }

    public String getEmpModifiedBy() {
        return empModifiedBy;
    }

    public void setEmpModifiedBy(String empModifiedBy) {
        this.empModifiedBy = empModifiedBy;
    }

    public String getEmpProfile() {
        return empProfile;
    }

    public void setEmpProfile(String empProfile) {
        this.empProfile = empProfile;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }
}
