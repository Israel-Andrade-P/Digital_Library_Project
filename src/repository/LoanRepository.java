package repository;

import model.AbstractFileRepository;
import model.Loan;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static constant.Constants.LOAN_PATH;

public class LoanRepository extends AbstractFileRepository<Loan> {
    @Override
    protected String filePath() {
        return LOAN_PATH;
    }

    @Override
    protected Comparator<Loan> defaultSort() {
        return Comparator.comparing(Loan::getBorrowDate);
    }

    public List<Loan> findByEmail(String email) {
        return retrieveAll().stream().filter(loan -> loan.getUserEmail().equals(email)).toList();
    }

    public Optional<Loan> findActiveLoan(String email, String bookId) {
        return retrieveAll().stream()
                .filter(l -> l.getUserEmail().equals(email) && l.getBookId().equals(bookId) && l.isActive())
                .findFirst();
    }

    public boolean existsActiveLoanByBookId(String bookId) {
        return retrieveAll().stream()
                .filter(loan -> loan.getBookId().equals(bookId) && loan.isActive())
                .findFirst().orElse(null) != null;
    }

    public void update(Loan updatedLoan) {
        List<Loan> updatedLoans = retrieveAll().stream()
                .map(loan -> loan.getId().equals(updatedLoan.getId()) ? updatedLoan : loan).toList();
        saveAll(updatedLoans);
    }

    public Set<String> returnBookIdsActiveLoan() {
        return retrieveAll().stream().filter(Loan::isActive).map(Loan::getBookId).collect(Collectors.toSet());
    }
}
