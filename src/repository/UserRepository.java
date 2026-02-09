package repository;

import exception.UserNotFoundException;
import model.AbstractFileRepository;
import model.User;

import java.util.Comparator;
import java.util.Optional;

import static constant.Constants.USER_PATH;

public class UserRepository extends AbstractFileRepository<User> {

    @Override
    protected String filePath() {
        return USER_PATH;
    }

    @Override
    protected Comparator<User> defaultSort() {
        return Comparator.comparing(User::getName);
    }

    @Override
    public Optional<User> findById(String id) {
        return retrieveAll().stream()
                .filter(u -> u.getEmail().equals(id))
                .findFirst();
    }
}
