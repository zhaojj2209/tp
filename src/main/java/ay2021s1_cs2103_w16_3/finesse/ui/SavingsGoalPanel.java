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
        monthlyExpenseLimit.textProperty().bind(expenseLimitBinding);
        monthlySavingsGoal.textProperty().bind(savingsGoalBinding);
        remainingBudget.textProperty().bind(monthlyBudgetBinding);
    }
}
