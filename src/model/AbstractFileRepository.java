package model;

import common.Identifiable;
import exception.RepositoryException;
import repository.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class AbstractFileRepository<T extends Identifiable> implements Repository<T> {
    protected final Logger logger = Logger.getLogger(getClass().getName());

    protected abstract String filePath();

    protected abstract Comparator<T> defaultSort();

    @Override
    public void add(T t) {
        List<T> entities = retrieveAll();
        entities.add(t);
        saveAll(entities);
    }

    @Override
    public List<T> retrieveAll() {
        File file = new File(filePath());

        if (!file.exists()) {
            return new ArrayList<>();
        }

        return load(file).stream().sorted(defaultSort()).collect(Collectors.toList());
    }

    @Override
    public Optional<T> findById(String id) {
        return retrieveAll().stream()
                .filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public boolean exists(String id) {
        return retrieveAll().stream().anyMatch(t -> t.getId().equals(id));
    }

    protected void saveAll(List<T> entities) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(filePath()))) {
            oos.writeObject(entities);
        } catch (IOException e) {
            throw new RepositoryException("Failed to write file", e);
        }
    }

    protected List<T> load(File file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            return (List<T>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RepositoryException("Failed to read file", e);
        }
    }
}
