package service;

import model.User;
import repository.UserRepository;

import java.util.List;

public class UserService implements FileService<User>{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        userRepository.add(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.retrieveAll();
    }

    @Override
    public boolean exists(String id) {
        return userRepository.exists(id);
    }
}
