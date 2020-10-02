package ay2021s1_cs2103_w16_3.finesse.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import ay2021s1_cs2103_w16_3.finesse.commons.exceptions.DataConversionException;
import ay2021s1_cs2103_w16_3.finesse.model.ReadOnlyAddressBook;
import ay2021s1_cs2103_w16_3.finesse.model.ReadOnlyUserPrefs;
import ay2021s1_cs2103_w16_3.finesse.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

}
