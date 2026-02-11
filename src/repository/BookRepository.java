package repository;

import model.AbstractFileRepository;
import model.Book;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
       return retrieveAll().stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .toList();
    }

    public List<Book> getByAuthor(String author) {
        return retrieveAll().stream().filter(book -> book.getAuthor().equals(author)).toList();
    }

    public Map<String, List<Book>> groupByAuthor() {
        return retrieveAll().stream().collect(Collectors.groupingBy(Book::getAuthor));
    }
}
