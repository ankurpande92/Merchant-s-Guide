package com.thoughtworks.assignment.main;

import com.thoughtworks.assignment.merchant.MetalProcessing;
import com.thoughtworks.assignment.merchant.InputFileReader;
import com.thoughtworks.assignment.merchant.QuestionReading;
import com.thoughtworks.assignment.repository.DataStore;
import com.thoughtworks.assignment.utility.Calculation;

/**
 * Start is entry point for execution.
 *
 * @author Ankur Pande
 * @version 1.0
 * @updated by Ankur Pande
 * @update 13 Jun 2016
 * @since 12 Jun 2016
 */
public class Start {
	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Start start = new Start();
		start.startExecution();
	}
	/**
	 * startExecution
	 * 
	 */
	private void startExecution(){
		DataStore dataStore = DataStore.getInstance();
		Calculation calculation = new Calculation(dataStore);

		InputFileReader inputFileReader = new InputFileReader(dataStore);
		inputFileReader.readInputFile("InputFile.txt");

		MetalProcessing entityProcessing = new MetalProcessing(dataStore, calculation);
		entityProcessing.processEachMetal();

		QuestionReading questionReading = new QuestionReading(dataStore, calculation);
		questionReading.processEachLineWithQuestionMark();
	}
}
