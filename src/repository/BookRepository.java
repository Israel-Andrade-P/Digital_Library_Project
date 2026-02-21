package repository;

import model.AbstractFileRepository;
import model.Book;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public List<Book> findByTitle(String title) {
        var search = title.toLowerCase();

        return retrieveAll().stream()
                .filter(Objects::nonNull)
                .filter(b -> b.getTitle().toLowerCase().contains(search))
                .toList();

    }

    public List<Book> getByAuthor(String author) {
        var search = author.toLowerCase();

        return retrieveAll().stream()
                .filter(Objects::nonNull)
                .filter(book -> book.getAuthor().toLowerCase().contains(search))
                .toList();
    }

    public Map<String, List<Book>> groupByAuthor() {
        return retrieveAll().stream().collect(Collectors.groupingBy(Book::getAuthor));
    }
}
