import com.thoughtworks.assignment.merchant.EntityProcessing;
import com.thoughtworks.assignment.repository.DataStore;
import com.thoughtworks.assignment.utility.Calculation;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


//These methods will test that parsing is working correctly

public class MetalProcessingTest {
    @Test
    public void shouldProcessEachMetalAndCallCalculateMethod() {
        DataStore dataStore = Mockito.mock(DataStore.class);
        Calculation calculation = Mockito.mock(Calculation.class);

        List<String> metalValueCalculationStrings = new ArrayList<String>();
        metalValueCalculationStrings.add("glob glob Silver is 34 Credits");
        metalValueCalculationStrings.add("glob prok Gold is 57800 Credits");
        metalValueCalculationStrings.add("pish pish Iron is 3910 Credits");

        when(dataStore.getValueCalculationLines()).thenReturn(metalValueCalculationStrings);

        MetalProcessing metalProcessing = new MetalProcessing(dataStore, calculation);
        metalProcessing.processEachMetal();

        Mockito.verify(calculation, times(3)).calculateMetalValueAndStore(Matchers.anyString(),
                Matchers.anyString(), Matchers.anyInt());
    }

    @Test
    public void shouldCallCalculateMethodWithNullmetalMeasureInRoman() {
        DataStore dataStore = Mockito.mock(DataStore.class);
        Calculation calculation = Mockito.mock(Calculation.class);

        List<String> metalValueCalculationStrings = new ArrayList<String>();
        metalValueCalculationStrings.add("glob globSilver is 34 Credits");

        when(dataStore.getValueCalculationLines()).thenReturn(metalValueCalculationStrings);

        MetalProcessing metalProcessing = new MetalProcessing(dataStore, calculation);
        metalProcessing.processEachEntity();

        Mockito.verify(calculation, times(1)).calculateEntityValueAndStore("globSilver",
                null, 34);
    }
}