package service;

import java.util.List;

public interface FileService<T> {
    void create(T t);
    List<T> getAll();
    boolean exists(String id);
}
