package techno_kryon.spring_boot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import techno_kryon.spring_boot.Dto.EmployeeCreateDto;
import techno_kryon.spring_boot.Dto.EmployeeDto;
import techno_kryon.spring_boot.Entity.Employee;
import techno_kryon.spring_boot.Repository.EmployeeRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeCreateDto employeeCreateDto) {
        EmployeeCreateDto newEmployeeCreateDto = new EmployeeCreateDto(null, employeeCreateDto.getEmpName(), employeeCreateDto.getEmpEmail(), employeeCreateDto.getEmpAge(), employeeCreateDto.getEmpPhone(), Timestamp.from(Instant.now())+"", "Api", employeeCreateDto.getEmpModifiedOn(), employeeCreateDto.getEmpModifiedBy(), employeeCreateDto.getEmpProfile(), employeeCreateDto.getEmpPassword());
        Optional<Employee> employee = convertFromEmpCreateDto(newEmployeeCreateDto);
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
    public List<EmployeeDto> getEmployees(Long count) {
        Long cnt = employeeRepository.count();
        List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
        //Iterable<Employee> employees = employeeRepository.findAllByOrderByEmpCreatedOnAsc();
        Iterable<Employee> employees = employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "empCreatedOn"));
        employees.forEach((employee) -> {
            employeeDtos.add(convertToEmpDto(employee));
        });
        List<EmployeeDto> empDtos = new ArrayList<EmployeeDto>();
        if(cnt < count) {
            count = cnt;
        }
        for(int i = 0;i < count;i++) {
            empDtos.add(employeeDtos.get(i));
        }
        return empDtos;
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeCreateDto employeeCreateDto, Integer empId) {
        EmployeeDto oEmployeeDto;
        Optional<EmployeeDto> oldEmployeeDto = getEmployee(empId);
        if(oldEmployeeDto.isEmpty()) {
            return new EmployeeDto();
        }
        else {
            oEmployeeDto = oldEmployeeDto.orElseThrow(() -> new RuntimeException("The given employee Does not exits in the database"));
        }
        EmployeeCreateDto newEmployeeCreateDto = new EmployeeCreateDto(employeeCreateDto.getEmpId(), employeeCreateDto.getEmpName(), employeeCreateDto.getEmpEmail(), employeeCreateDto.getEmpAge(), employeeCreateDto.getEmpPhone(), oEmployeeDto.getEmpCreatedOn(), oEmployeeDto.getEmpCreatedBy(), Timestamp.from(Instant.now())+"", "Api", employeeCreateDto.getEmpProfile(), employeeCreateDto.getEmpPassword());
        Optional<Employee> employee = convertFromEmpCreateDto(newEmployeeCreateDto);
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
//        try {
//            Optional<Employee> employee = employeeRepository.findById(employeeCreateDto.getEmpId());
//            if(employee.isEmpty()) {
//                Optional<Employee> newEmployee = Optional.of(new Employee(employeeCreateDto.getEmpId(), employeeCreateDto.getEmpName(), employeeCreateDto.getEmpEmail(), employeeCreateDto.getEmpAge(), employeeCreateDto.getEmpPhone(), employeeCreateDto.getEmpCreatedOn(), employeeCreateDto.getEmpCreatedBy(), employeeCreateDto.getEmpModifiedOn(), employeeCreateDto.getEmpModifiedBy(), employeeCreateDto.getEmpProfile(), employeeCreateDto.getEmpPassword()));
//                return newEmployee;
//            }
//            return employee;
//        }
//        catch(Exception e) {
//            System.out.println("ConvertFromEmpCreateDto findById failed");
//            Optional<Employee> newEmployee = Optional.of(new Employee(employeeCreateDto.getEmpId(), employeeCreateDto.getEmpName(), employeeCreateDto.getEmpEmail(), employeeCreateDto.getEmpAge(), employeeCreateDto.getEmpPhone(), employeeCreateDto.getEmpCreatedOn(), employeeCreateDto.getEmpCreatedBy(), employeeCreateDto.getEmpModifiedOn(), employeeCreateDto.getEmpModifiedBy(), employeeCreateDto.getEmpProfile(), employeeCreateDto.getEmpPassword()));
//            return newEmployee;
//        }
        return Optional.of(new Employee(employeeCreateDto.getEmpId(), employeeCreateDto.getEmpName(), employeeCreateDto.getEmpEmail(), employeeCreateDto.getEmpAge(), employeeCreateDto.getEmpPhone(), employeeCreateDto.getEmpCreatedOn(), employeeCreateDto.getEmpCreatedBy(), employeeCreateDto.getEmpModifiedOn(), employeeCreateDto.getEmpModifiedBy(), employeeCreateDto.getEmpProfile(), employeeCreateDto.getEmpPassword()));
    }
}
