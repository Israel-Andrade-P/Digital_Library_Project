package repository;

import model.AbstractFileRepository;
import model.User;

import java.util.Comparator;

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
}
