package techno_kryon.spring_boot.Repository;

import org.springframework.data.repository.CrudRepository;
import techno_kryon.spring_boot.Entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
