package techno_kryon.spring_boot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techno_kryon.spring_boot.Dto.EmployeeCreateDto;
import techno_kryon.spring_boot.Dto.EmployeeDto;
import techno_kryon.spring_boot.Entity.Employee;
import techno_kryon.spring_boot.Repository.EmployeeRepository;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeCreateDto employeeCreateDto) {
        Optional<Employee> employee = convertFromEmpCreateDto(employeeCreateDto);
        Employee employeeSaved = employeeRepository.save(employee.orElseThrow(() -> new RuntimeException("Cannot Convert employeeCreateDto to Employee in createEmployee")));
        EmployeeDto employeeDtoSaved = convertToEmpDto(employeeSaved);
        return employeeDtoSaved;
    }

    @Override
    public Optional<EmployeeDto> getEmployee(Integer employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Optional<EmployeeDto> employeeDto = Optional.of(convertToEmpDto(employee.orElseThrow(() -> new RuntimeException("Cannot Convert employeeId to EmployeeDto in getEmployee"))));
        return employeeDto;
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeCreateDto employeeCreateDto, Integer empId) {
        Optional<Employee> employee = convertFromEmpCreateDto(employeeCreateDto);
        Employee employeeSaved = employeeRepository.save(employee.orElseThrow(() -> new RuntimeException("Cannot Convert employeeCreate Dto to Employee in updateEmployee")));
        EmployeeDto employeeDtoUpdated = convertToEmpDto(employeeSaved);
        return employeeDtoUpdated;
    }

    @Override
    public EmployeeDto deleteEmployee(Integer empId) {
        Optional<EmployeeDto> employeeDtoDeleted = getEmployee(empId);
        employeeRepository.deleteById(empId);
        return employeeDtoDeleted.orElseThrow(() -> new RuntimeException("Cannot Convert employeeId to Employee does not exists in database | deleteEmployee"));
    }

    public EmployeeDto convertToEmpDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto(employee.getEmpId(), employee.getEmpName(), employee.getEmpEmail(), employee.getEmpAge(), employee.getEmpPhone(), employee.getEmpCreatedOn(), employee.getEmpCreatedBy(), employee.getEmpModifiedOn(), employee.getEmpModifiedBy(), employee.getEmpProfile());
        return employeeDto;
    }

    public EmployeeCreateDto convertToEmpCreateDto(Employee employee) {
        EmployeeCreateDto employeeCreateDto = new EmployeeCreateDto(employee.getEmpId(), employee.getEmpName(), employee.getEmpEmail(), employee.getEmpAge(), employee.getEmpPhone(), employee.getEmpCreatedOn(), employee.getEmpCreatedBy(), employee.getEmpModifiedOn(), employee.getEmpModifiedBy(), employee.getEmpProfile(), employee.getEmpPassword());
        return employeeCreateDto;
    }

    public Optional<Employee> convertFromEmpDto(EmployeeDto employeeDto) {
        Optional<Employee> employee = employeeRepository.findById(employeeDto.getEmpId());
        return employee;
    }

    public Optional<Employee> convertFromEmpCreateDto(EmployeeCreateDto employeeCreateDto) {
        Optional<Employee> employee = employeeRepository.findById(employeeCreateDto.getEmpId());
        if(employee.isEmpty()) {
            Optional<Employee> newEmployee = Optional.of(new Employee(employeeCreateDto.getEmpId(), employeeCreateDto.getEmpName(), employeeCreateDto.getEmpEmail(), employeeCreateDto.getEmpAge(), employeeCreateDto.getEmpPhone(), employeeCreateDto.getEmpCreatedOn(), employeeCreateDto.getEmpCreatedBy(), employeeCreateDto.getEmpModifiedOn(), employeeCreateDto.getEmpModifiedBy(), employeeCreateDto.getEmpProfile(), employeeCreateDto.getEmpPassword()));
            return newEmployee;
        }
        return employee;
    }
}
