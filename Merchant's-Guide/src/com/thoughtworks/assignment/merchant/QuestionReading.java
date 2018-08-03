package com.thoughtworks.assignment.merchant;

import com.thoughtworks.assignment.exception.InvalidInputException;
import com.thoughtworks.assignment.repository.DataStore;
import com.thoughtworks.assignment.utility.Calculation;
import com.thoughtworks.assignment.utility.Constants;

/**
 * 
 * QuestionReading class reads eachLine of question and perform calculation
 * 
 * @author Ankur Pande
 * @version 1.0
 * @since 12 Jun 2016
 * @updated
 * @update by
 * 
 */
public class QuestionReading{

	DataStore dataStoreObj;
	Calculation calculation;

	public QuestionReading(DataStore dataStoreObj, Calculation calculation) {
		this.dataStoreObj = dataStoreObj;
		this.calculation = calculation;
	}


	/**
	 *
	 * processEachLineWithQuestionMark took each line from bufferedReder and
	 * start parsing question.
	 */
	public void processEachLineWithQuestionMark() {
		for (String question : this.dataStoreObj.getQuestionMarkLines()) {
			try {
				answerQuestions(question);
			} catch (InvalidInputException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	/**
	 * parsingQuestionMarkStrings perform action based on "how much"/ "how many"
	 * string literls
	 * 
	 * @param question
	 */
	private void answerQuestions(String question) throws InvalidInputException {
		if (question.toLowerCase().startsWith(Constants.HOW_MUCH)) {
			calculateRomanValues(question);
		} else if (question.toLowerCase().startsWith(Constants.HOW_MANY)) {
			calculateCreditsValues(question);
		}else{
			throw new InvalidInputException(Constants.INVALID_MESSAGE);
		}
	}

	/**
	 * calculateRomanValues calculate decimal value against given roman literals
	 * sequence for ex. pish tegj glob glob
	 * 
	 * @param question
	 */
	private void calculateRomanValues(String question) {

		String parsedQuestionString;
		try {
			parsedQuestionString = getParsedQuestionString(question);
			String romanString = this.calculation.getRomanNumerals(parsedQuestionString);
			float value = this.calculation.calculateRomanToDecimal(romanString);
			System.out.println(parsedQuestionString + Constants.IS
					+ Constants.SPACE + value);
		} catch (InvalidInputException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * getParsedQuestionString
	 * 
	 * @param question
	 * @return
	 * @throws InvalidInputException
	 */
	private String getParsedQuestionString(String question)
			throws InvalidInputException {
		
			String[] splitData = question.split(Constants.QUESTION_DELIMETER);
			return splitData[1].substring(0, splitData[1].length() - 1);
		
	}

	/**
	 * calculateCreditsValues calculates the credits against given metal such
	 * as "Silver", "Iorn","Gold" etc.
	 * 
	 * @param question
	 */
	private void calculateCreditsValues(String question) {
		String romanName = "";
		float metalValue = 0;
		int metalMeasureInDecimal = 0;
		try {
			String parsedQuestionString = getParsedQuestionString(question);
			String[] tokens = parsedQuestionString.split(Constants.SPACE);
			for (String token : tokens) {
				if (dataStoreObj.hasThisMetalMeasure(token)) {
					romanName = romanName
							+ dataStoreObj.getMetalMeasureValue(token);
				} else if (dataStoreObj.hasThisMetal(token)) {
					metalValue = metalValue
							+ dataStoreObj.getMetalNameValue(token).getMetalValue();
				}
			}
			metalMeasureInDecimal = this.calculation.calculateRomanToDecimal(romanName);
			float value = metalValue * metalMeasureInDecimal;
			System.out.println(parsedQuestionString + Constants.IS
					+ Constants.SPACE + value + Constants.SPACE
					+ Constants.CREDITS);
		} catch (InvalidInputException e) {
			System.err.println(e.getMessage());
		}
	}
}
