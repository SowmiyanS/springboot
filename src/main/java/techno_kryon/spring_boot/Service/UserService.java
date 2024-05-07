package techno_kryon.spring_boot.Service;

import techno_kryon.spring_boot.Entity.User;

public interface UserService {
    //create
    public User createUser(User user);
    //delete
    public User deleteUser(String email);

    public boolean validateUser();
}
