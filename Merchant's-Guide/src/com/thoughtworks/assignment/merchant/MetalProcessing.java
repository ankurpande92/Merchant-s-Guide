package com.thoughtworks.assignment.merchant;

import com.thoughtworks.assignment.repository.DataStore;
import com.thoughtworks.assignment.utility.Calculation;
import com.thoughtworks.assignment.utility.Constants;

/**
 * EntitieProcessing class find and evaluate value for each entity.
 * 
 * @author Ankur Pande
 * @version 1.0
 * @since 12 Jun 2016
 * @updated
 * @update by
 *
 */
public class MetalProcessing {
	DataStore dataStore;
	Calculation calculation;

	public MetalProcessing(DataStore dataStore, Calculation calculation) {
		this.dataStore = dataStore;
		this.calculation = calculation;
	}

	/**
	 * processEachEntity took each metal line from bufferedReder and start
	 * evaluating it.
	 * 
	 */
	public void processEachMetal() {
		for (String string : this.dataStore.getValueCalculationLines()) {
			MetalValueCalculation(string);
		}
	}

	/**
	 * EntityValueCalculation perform calculation based input
	 * 
	 * @param eachLine
	 */
	private void MetalValueCalculation(String eachLine) {
		String[] tokens = eachLine.split(Constants.IS_WITH_SPACE);

		String[] metalData = tokens[0].split(Constants.SPACE);
		String metalName = metalData[metalData.length - 1];
		String metalMeasureInRoman = getMetalMeasureInRoman(metalData);

		String[] creditsData = tokens[1].split(Constants.SPACE);
		int creditsValue = Integer.parseInt(creditsData[0]);

		this.calculation.calculateMetalValueAndStore(metalName, metalMeasureInRoman,
				creditsValue);

	}

	/**
	 * getEntityMeasureInRoman
	 * 
	 * @param metalData
	 * @return
	 */
	private String getMetalMeasureInRoman(String[] metalData) {
		String entityMeasure = "";
		for (int i = 0; i < (metalData.length - 1); i++) {
			entityMeasure += metalData[i] + Constants.SPACE;
		}
		return this.calculation.getRomanNumerals(entityMeasure);
	}

}
