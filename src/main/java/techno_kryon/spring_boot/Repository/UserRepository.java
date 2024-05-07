package techno_kryon.spring_boot.Repository;

import org.springframework.data.repository.CrudRepository;
import techno_kryon.spring_boot.Entity.User;

public interface UserRepository extends CrudRepository<User, String> {
}
