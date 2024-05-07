package techno_kryon.spring_boot.Dto;

public class EmployeeDto {
    private Integer empId;
    private String empName;
    private String empEmail;
    private Integer empAge;
    private String empPhone;
    private String empCreatedOn;
    private String empCreatedBy;
    private String empModifiedOn;
    private String empModifiedBy;
    private String empProfile;

    public EmployeeDto() {
    }

    public EmployeeDto(Integer empId, String empName, String empEmail, Integer empAge, String empPhone, String empCreatedOn, String empCreatedBy, String empModifiedOn, String empModifiedBy, String empProfile) {
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
    }

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

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEmpCreatedOn() {
        return empCreatedOn;
    }

    public void setEmpCreatedOn(String empCreatedOn) {
        this.empCreatedOn = empCreatedOn;
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
}
