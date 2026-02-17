package service;

import exception.LoanNotFoundException;
import model.Loan;
import repository.LoanRepository;

import java.util.List;
import java.util.Set;

public class LoanService implements FileService<Loan> {
    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public void create(Loan loan) {
        loanRepository.add(loan);
    }

    @Override
    public List<Loan> getAll() {
        return loanRepository.retrieveAll();
    }

    @Override
    public Loan getById(String id) {
        return loanRepository.findById(id).orElseThrow(() -> new LoanNotFoundException("Loan not found"));
    }

    @Override
    public boolean exists(String id) {
        return loanRepository.exists(id);
    }

    public boolean hasActiveLoan(String bookId) {
        return loanRepository.existsActiveLoanByBookId(bookId);
    }

    public void updateReturnStatus(String email, String bookId) {
        Loan loan = loanRepository.findActiveLoan(email, bookId)
                .orElseThrow(() -> new IllegalArgumentException("No loan registered for that email and book"));
        loan.markAsReturned();
        loanRepository.update(loan);
    }

    public List<Loan> getByEmail(String email) {
        List<Loan> loans = loanRepository.findByEmail(email);
        if (loans.isEmpty()) throw new LoanNotFoundException("Loan not found");
        return loans;
    }

    public Set<String> getBooksWithActiveLoans() {
        return loanRepository.returnBookIdsActiveLoan();
    }
}
