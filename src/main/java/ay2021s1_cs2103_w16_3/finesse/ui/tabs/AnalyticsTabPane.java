package ay2021s1_cs2103_w16_3.finesse.ui.tabs;

import ay2021s1_cs2103_w16_3.finesse.model.budget.MonthlyBudget;
import ay2021s1_cs2103_w16_3.finesse.ui.UiPart;
import javafx.scene.canvas.Canvas;

/**
 * Tab pane that displays analytics.
 */
public class AnalyticsTabPane extends UiPart<Canvas> {
    private static final String FXML = "AnalyticsTabPane.fxml";

    /**
     * Creates an {@code AnalyticsTabPane}.
     */
    public AnalyticsTabPane(MonthlyBudget monthlyBudget) {
        super(FXML);
    }

    // TODO: Add visualizations.
}
