import com.thoughtworks.assignment.merchant.InputFileReader;
import com.thoughtworks.assignment.repository.DataStore;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;

public class InputFileReaderTest {
    @Test
    public void shouldReadInputFileSuccessfullyAndSaveDataInDataStore() {
        DataStore dataStore = Mockito.mock(DataStore.class);

        InputFileReader inputFileReader = new InputFileReader(dataStore);
        inputFileReader.readInputFile("InputFile.txt");

        Mockito.verify(dataStore, atLeast(1)).addMetalMeasure(Matchers.anyString(), Matchers.anyString());
        Mockito.verify(dataStore, atLeast(1)).addValueCalculationLine(Matchers.anyString());
        Mockito.verify(dataStore, atLeast(1)).addQuestionMarkLine(Matchers.anyString());
    }
    @Test
    public void shouldNotSavedataInDataStoreWhenFileDoesNotExists() {
        DataStore dataStore = Mockito.mock(DataStore.class);

        InputFileReader inputFileReader = new InputFileReader(dataStore);
        inputFileReader.readInputFile("Invalid.txt");

        Mockito.verify(dataStore, times(0)).addMetalMeasure(Matchers.anyString(), Matchers.anyString());
        Mockito.verify(dataStore, times(0)).addValueCalculationLine(Matchers.anyString());
        Mockito.verify(dataStore, times(0)).addQuestionMarkLine(Matchers.anyString());
    }
}
