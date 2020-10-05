package ay2021s1_cs2103_w16_3.finesse.model;

import java.nio.file.Path;

import ay2021s1_cs2103_w16_3.finesse.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFinanceTrackerFilePath();

}
