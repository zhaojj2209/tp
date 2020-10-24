package ay2021s1_cs2103_w16_3.finesse.ui;

import ay2021s1_cs2103_w16_3.finesse.model.budget.MonthlyBudget;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;


public class SavingsGoalPanel extends UiPart<Region> {
    private static final String FXML = "SavingsGoalPanel.fxml";
    private final Image savingsPanelPicture = new Image(this.getClass()
            .getResourceAsStream("/images/SavingsImage.png"));


    @FXML
    private ImageView savingsPicture;
    @FXML
    private Label monthlySavingsGoal;
    @FXML
    private Label monthlyExpenseLimit;

    /**
     * Constructor of SavingsGoalPanel.
     */
    public SavingsGoalPanel(MonthlyBudget monthlyBudget) {
        super(FXML);
        savingsPicture.setImage(savingsPanelPicture);
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
        monthlySavingsGoal.textProperty().bind(savingsGoalBinding);
        monthlyExpenseLimit.textProperty().bind(expenseLimitBinding);
    }
}
