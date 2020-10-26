package ay2021s1_cs2103_w16_3.finesse.logic.commands.stubs;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import ay2021s1_cs2103_w16_3.finesse.commons.core.GuiSettings;
import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.model.ReadOnlyFinanceTracker;
import ay2021s1_cs2103_w16_3.finesse.model.ReadOnlyUserPrefs;
import ay2021s1_cs2103_w16_3.finesse.model.budget.MonthlyBudget;
import ay2021s1_cs2103_w16_3.finesse.model.frequent.FrequentExpense;
import ay2021s1_cs2103_w16_3.finesse.model.frequent.FrequentIncome;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Expense;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Income;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;
import javafx.collections.ObservableList;

/**
 * A default model stub that has all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getFinanceTrackerFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setFinanceTrackerFilePath(Path financeTrackerFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addExpense(Expense expense) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addIncome(Income income) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addFrequentExpense(FrequentExpense frequentExpense) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addFrequentIncome(FrequentIncome frequentIncome) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setFinanceTracker(ReadOnlyFinanceTracker newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyFinanceTracker getFinanceTracker() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteTransaction(Transaction target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteFrequentExpense(FrequentExpense target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteFrequentIncome(FrequentIncome target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTransaction(Transaction target, Transaction editedTransaction) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setFrequentExpense(FrequentExpense target, FrequentExpense editedFrequentExpense) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setFrequentIncome(FrequentIncome target, FrequentIncome editedFrequentIncome) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public MonthlyBudget getMonthlyBudget() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setExpenseLimit(Amount limit) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setSavingsGoal(Amount goal) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void calculateBudgetInfo() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Transaction> getFilteredTransactionList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Expense> getFilteredExpenseList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Income> getFilteredIncomeList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<FrequentExpense> getFilteredFrequentExpenseList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<FrequentIncome> getFilteredFrequentIncomeList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredTransactionList(Predicate<Transaction> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredTransactionList(List<Predicate<Transaction>> predicates) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredExpenseList(Predicate<Transaction> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredExpenseList(List<Predicate<Transaction>> predicates) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredIncomeList(Predicate<Transaction> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredIncomeList(List<Predicate<Transaction>> predicates) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredFrequentExpenseList(Predicate<FrequentExpense> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredFrequentIncomeList(Predicate<FrequentIncome> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}
