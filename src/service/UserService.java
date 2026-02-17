package service;

import exception.UserAlreadyRegisteredException;
import exception.UserNotFoundException;
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
        if (exists(user.getId())) throw new UserAlreadyRegisteredException("User already registered");

        userRepository.add(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.retrieveAll();
        if (users.isEmpty()) throw new UserNotFoundException("No users in system");
        return userRepository.retrieveAll();
    }

    @Override
    public User getById(String email) {
        return userRepository.findById(email).orElseThrow(() -> new UserNotFoundException(String.format("User with email %s not found", email)));
    }

    @Override
    public boolean exists(String id) {
        return userRepository.exists(id);
    }
}
