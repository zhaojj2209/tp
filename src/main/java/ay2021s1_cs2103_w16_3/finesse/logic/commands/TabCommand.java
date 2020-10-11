package ay2021s1_cs2103_w16_3.finesse.logic.commands;

import static java.util.Objects.requireNonNull;

import ay2021s1_cs2103_w16_3.finesse.commons.core.index.Index;
import ay2021s1_cs2103_w16_3.finesse.logic.commands.exceptions.CommandException;
import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.ui.UiState.Tab;

/**
 * Switches UI tabs.
 */
public class TabCommand extends Command {

    /** The number of tabs in the UI. */
    public static final int NUM_OF_TABS = 4;

    public static final String COMMAND_WORD = "tab";

    // TODO: Update this
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to the specified tab by index.\n"
            + "Parameters: INDEX (must be a positive integer between 1 to " + NUM_OF_TABS + " inclusive)";

    public static final String MESSAGE_SWITCH_TABS_SUCCESS = "Switched to %1$s tab.";
    public static final String MESSAGE_TAB_DOES_NOT_EXIST = "The specified tab does not exist.";

    /** The index of the tab to switch to. */
    private final Index tabIndex;

    /**
     * Constructs a {@code TabCommand} with the specified tab index to switch to.
     *
     * @param tabIndex The index of the tab to switch to.
     * @throws CommandException If the specified tab index does not exist.
     */
    public TabCommand(Index tabIndex) {
        requireNonNull(tabIndex);

        this.tabIndex = tabIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (tabIndex.getOneBased() > NUM_OF_TABS) {
            throw new CommandException(TabCommand.MESSAGE_TAB_DOES_NOT_EXIST);
        }

        Tab tabToSwitchTo = Tab.values()[tabIndex.getZeroBased()];
        String formattedSuccessMessage = String.format(MESSAGE_SWITCH_TABS_SUCCESS, tabToSwitchTo);
        return new CommandResult(formattedSuccessMessage, tabToSwitchTo);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // Short circuit if same object.
                || (other instanceof TabCommand // instanceof handles nulls.
                && tabIndex.equals(((TabCommand) other).tabIndex)); // State check.
    }
}
