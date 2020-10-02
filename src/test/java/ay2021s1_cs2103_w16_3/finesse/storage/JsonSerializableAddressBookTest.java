package ay2021s1_cs2103_w16_3.finesse.storage;

import static ay2021s1_cs2103_w16_3.finesse.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.commons.exceptions.IllegalValueException;
import ay2021s1_cs2103_w16_3.finesse.commons.util.JsonUtil;
import ay2021s1_cs2103_w16_3.finesse.model.AddressBook;
import ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_TRANSACTIONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_TRANSACTION_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_TRANSACTION_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    @Test
    public void toModelType_typicalTransactionsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TRANSACTIONS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalTransactionsAddressBook = TypicalTransactions.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalTransactionsAddressBook);
    }

    @Test
    public void toModelType_invalidTransactionFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_TRANSACTION_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTransactions_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TRANSACTION_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_TRANSACTION,
                dataFromFile::toModelType);
    }

}
