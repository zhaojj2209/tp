package ay2021s1_cs2103_w16_3.finesse.ui;

import static java.util.Objects.requireNonNull;

/**
 * Represents the state of the current UI.
 */
public class UiState {
    /**
     * Tabs present in the UI.
     */
    public enum Tab {
        OVERVIEW,
        INCOME,
        EXPENSES,
        ANALYTICS
    }

    /**
     * The current selected tab.
     */
    private Tab currentTab;

    /**
     * Creates a {@code UiState} with the currently selected tab set to the 'Overview' tab.
     */
    public UiState() {
        currentTab = Tab.OVERVIEW;
    }

    /**
     * Creates a {@code UiState} with the specified currently selected tab.
     *
     * @param currentTab The currently selected tab.
     */
    public UiState(Tab currentTab) {
        requireNonNull(currentTab);
        this.currentTab = currentTab;
    }

    /**
     * Returns the currently selected tab.
     *
     * @return The currently selected tab.
     */
    public Tab getCurrentTab() {
        return currentTab;
    }

    /**
     * Updates the currently selected tab.
     *
     * @param currentTab The currently selected tab.
     */
    public void setCurrentTab(Tab currentTab) {
        requireNonNull(currentTab);
        this.currentTab = currentTab;
    }
}
