package repository;

import model.AbstractFileRepository;
import model.Book;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static constant.Constants.BOOK_PATH;

public class BookRepository extends AbstractFileRepository<Book> {

    @Override
    protected String filePath() {
        return BOOK_PATH;
    }

    @Override
    protected Comparator<Book> defaultSort() {
        return Comparator.comparing(Book::getTitle);
    }

    @Override
    public Optional<Book> findById(String title) {
        return Optional.empty();
    }

    public List<Book> findByTitle(String title) {
       return retrieveAll().stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .toList();
    }
}
