package techno_kryon.spring_boot.Repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import techno_kryon.spring_boot.Entity.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Iterable<Employee> findAll(Sort empCreatedOn);
//    public List<Employee> findAllByOrderByEmpCreatedOnAsc();
}
