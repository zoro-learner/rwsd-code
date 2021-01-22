package zom.zoro.rwsd.chapter_03;

import java.time.Month;
import java.util.DoubleSummaryStatistics;
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

    public SummaryStatistics summarizeTransactions() {

        final DoubleSummaryStatistics doubleSummaryStatistics = bankTransactions.stream()
            .mapToDouble(BankTransaction::getAmount)
            .summaryStatistics();

        return new SummaryStatistics(doubleSummaryStatistics.getSum(),
                                     doubleSummaryStatistics.getMax(),
                                     doubleSummaryStatistics.getMin(),
                                     doubleSummaryStatistics.getAverage());
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
        return summarizeTransactions((acc, bankTransaction) -> bankTransaction.getDate().getMonth() == month
            ? acc + bankTransaction.getAmount()
            : acc);
    }

    public double calculateTotalForCategory(final String category) {
        return bankTransactions.stream()
            .filter(t -> category.equals(t.getDescription()))
            .mapToDouble(BankTransaction::getAmount)
            .sum();
    }

    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
        double result = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            result = bankTransactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }

    public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
        return bankTransactions.stream()
            .filter(bankTransactionFilter::test)
            .collect(Collectors.toList());
    }

}
