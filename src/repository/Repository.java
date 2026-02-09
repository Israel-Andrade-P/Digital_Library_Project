package repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    void add(T t);
    List<T> retrieveAll();
    Optional<T> findById(String id);
}
