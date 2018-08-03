import com.thoughtworks.assignment.merchant.EntityProcessing;
import com.thoughtworks.assignment.merchant.QuestionReading;
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

	@Test
	public void shouldprocessEachLineWithQuestionMarkAndCallcalculateRomanValues() {
	    DataStore dataStore = Mockito.mock(DataStore.class);
	    Calculation calculation = Mockito.mock(Calculation.class);
	
	    List<String> questionMarkStrings = new ArrayList<String>();
	    questionMarkStrings.add("how much is pish tegj glob glob ?");
	
	    when(dataStore.getQuestionMarkLines()).thenReturn(questionMarkStrings);
	
	    QuestionReading questionReading = new QuestionReading(dataStore, calculation);
	    questionReading.processEachLineWithQuestionMark();
	
	    Mockito.verify(calculation, times(1)).getRomanNumerals(Matchers.anyString());
	    Mockito.verify(calculation, times(1)).calculateRomanToDecimal(Matchers.anyString());
	}

	@Test
	public void shouldprocessEachLineWithQuestionMarkAndCallcalculateCreditsValues() {
	    DataStore dataStore = Mockito.mock(DataStore.class);
	    Calculation calculation = Mockito.mock(Calculation.class);
	
	    List<String> questionMarkStrings = new ArrayList<String>();
	    questionMarkStrings.add("how many Credits is glob prok Gold ?");
	    questionMarkStrings.add("how many Credits is glob prok Silver ?");
	    questionMarkStrings.add("how many Credits is glob prok Iron ?");
	
	    when(dataStore.getQuestionMarkLines()).thenReturn(questionMarkStrings);
	
	    QuestionReading questionReading = new QuestionReading(dataStore, calculation);
	    questionReading.processEachLineWithQuestionMark();
	
	    Mockito.verify(calculation, times(3)).calculateRomanToDecimal(Matchers.anyString());
	}
    
}