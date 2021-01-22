package zom.zoro.rwsd.chapter_02;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author lenovo
 * @create 2021-01-18 15:20
 */
public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        return bankTransactions.stream().mapToDouble(BankTransaction::getAmount).sum();
    }

    public List<BankTransaction> selectInMonth(final Month month) {
        return bankTransactions.stream()
            .filter(t -> month.equals(t.getDate().getMonth()))
            .collect(Collectors.toList());
    }

    public double calculateTotalInMonth(final Month month) {
        return bankTransactions.stream()
            .filter(t -> month.equals(t.getDate().getMonth()))
            .mapToDouble(BankTransaction::getAmount)
            .sum();
    }

    public double calculateTotalForCategory(final String category) {
        return bankTransactions.stream()
            .filter(t -> category.equals(t.getDescription()))
            .mapToDouble(BankTransaction::getAmount)
            .sum();
    }


}
