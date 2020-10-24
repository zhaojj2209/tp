package ay2021s1_cs2103_w16_3.finesse.ui;

import ay2021s1_cs2103_w16_3.finesse.model.budget.MonthlyBudget;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;


public class SavingsGoalPanel extends UiPart<Region> {
    private static final String FXML = "SavingsGoalPanel.fxml";
    private final Image savingsPanelPicture = new Image(this.getClass()
            .getResourceAsStream("/images/SavingsImage.png"));

    @FXML
    private Label monthlyExpenseLimit;
    @FXML
    private Label monthlySavingsGoal;
    @FXML
    private Label remainingBudget;
    @FXML
    private Label currentSavings;
    @FXML
    private Label budgetDeficit;
    @FXML
    private Label savingsDeficit;

    /**
     * Constructor of SavingsGoalPanel.
     */
    public SavingsGoalPanel(MonthlyBudget monthlyBudget) {
        super(FXML);
        StringBinding expenseLimitBinding = Bindings.createStringBinding(() ->
                        String.format(
                                "Monthly Expense Limit: %s",
                                monthlyBudget.getMonthlyExpenseLimit().getAmount().toString()),
                monthlyBudget.getMonthlyExpenseLimit().getObservableAmount());
        StringBinding savingsGoalBinding = Bindings.createStringBinding(() ->
                        String.format(
                                "Monthly Savings Goal: %s",
                                monthlyBudget.getMonthlySavingsGoal().getAmount().toString()),
                monthlyBudget.getMonthlySavingsGoal().getObservableAmount());
        StringBinding monthlyBudgetBinding = Bindings.createStringBinding(() ->
                        String.format(
                                "Remaining Budget: %s",
                                monthlyBudget.getRemainingBudget().getAmount().toString()),
                monthlyBudget.getRemainingBudget().getObservableAmount());
        StringBinding currentSavingsBinding = Bindings.createStringBinding(() ->
                        String.format(
                                "Current Savings: %s",
                                monthlyBudget.getCurrentSavings().getAmount().toString()),
                monthlyBudget.getCurrentSavings().getObservableAmount());
        StringBinding budgetDeficitBinding = Bindings.createStringBinding(() ->
                        String.format(
                                "Budget Deficit: %s",
                                monthlyBudget.getBudgetDeficit().getAmount().toString()),
                monthlyBudget.getBudgetDeficit().getObservableAmount());
        StringBinding savingsDeficitBinding = Bindings.createStringBinding(() ->
                        String.format(
                                "Savings Deficit: %s",
                                monthlyBudget.getSavingsDeficit().getAmount().toString()),
                monthlyBudget.getSavingsDeficit().getObservableAmount());
        monthlyExpenseLimit.textProperty().bind(expenseLimitBinding);
        monthlySavingsGoal.textProperty().bind(savingsGoalBinding);
        remainingBudget.textProperty().bind(monthlyBudgetBinding);
        currentSavings.textProperty().bind(currentSavingsBinding);
        budgetDeficit.textProperty().bind(budgetDeficitBinding);
        savingsDeficit.textProperty().bind(savingsDeficitBinding);
    }
}
