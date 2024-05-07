package techno_kryon.spring_boot.Service;

import org.springframework.stereotype.Service;
import techno_kryon.spring_boot.Dto.EmployeeCreateDto;
import techno_kryon.spring_boot.Dto.EmployeeDto;
import techno_kryon.spring_boot.Entity.Employee;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {
    //Create
    public EmployeeDto createEmployee(EmployeeCreateDto employeeCreateDto);
    //Read
    public Optional<EmployeeDto> getEmployee(Integer employeeId);
    //Update
    public EmployeeDto updateEmployee(EmployeeCreateDto employeeCreateDto, Integer empId);
    //Delete
    public EmployeeDto deleteEmployee(Integer empId);

    //Read
    public List<EmployeeDto> getEmployees(Long count);

    // Dto to Entity and vice versa
    public EmployeeDto convertToEmpDto(Employee employee);
    public EmployeeCreateDto convertToEmpCreateDto(Employee employee);
    public Optional<Employee> convertFromEmpDto(EmployeeDto employeeDto);
    public Optional<Employee> convertFromEmpCreateDto(EmployeeCreateDto employeeCreateDto);
}
