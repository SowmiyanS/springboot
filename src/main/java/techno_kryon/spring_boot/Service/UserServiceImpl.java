package techno_kryon.spring_boot.Service;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techno_kryon.spring_boot.Entity.User;
import techno_kryon.spring_boot.Repository.UserRepository;

import java.util.Optional;

// Encryption Tutorial : http://www.jasypt.org/howtoencryptuserpasswords.html


@Service
@EnableEncryptableProperties
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(user.getUserPassword());
        user.setUserPassword(encryptedPassword);
        return userRepository.save(user);
    }

    public User deleteUser(String email) {
        Optional<User> deletedUser = userRepository.findById(email);
        userRepository.deleteById(email);
        return deletedUser.orElse(new User());
    }

    @Override
    public boolean validateUser() {
        return false;
    }
}
