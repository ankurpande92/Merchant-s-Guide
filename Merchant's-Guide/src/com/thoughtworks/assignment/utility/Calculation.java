package com.thoughtworks.assignment.utility;

import com.thoughtworks.assignment.merchant.Metal;
import com.thoughtworks.assignment.repository.DataStore;

/**
 * Calculation class perform all type of calulation
 * 
 * @author Ankur Pande
 * @version 1.0
 * @since 12 Jun 2016
 * @updated
 * @update by
 *
 */
public class Calculation {
	DataStore dataStoreObj;

	public Calculation(DataStore dataStoreObj) {
		this.dataStoreObj = dataStoreObj;
	}
	/**
	 * getRomanNumerals get the roman numbers string
	 * 
	 * @param romanNumerals
	 * @return
	 */
	public String getRomanNumerals(String romanNumerals) {

		String[] tokens = romanNumerals.split(Constants.SPACE);
		String romanName = "";
		for (String string : tokens) {
			romanName = romanName + this.dataStoreObj.getMetalMeasureValue(string);
		}
		return romanName;

	}

	/**
	 * calculateMetalValueAndStore
	 * 
	 * @param metalName
	 * @param entityMeasureInRoman
	 * @param creditsValue
	 */
	public void calculateMetalValueAndStore(String metalName,
			String entityMeasureInRoman, int creditsValue) {
		Metal entity = new Metal();
		float value = calculateRomanToDecimal(entityMeasureInRoman);
		float metalValue = creditsValue / value;

		entity.setMetalName(metalName);
		entity.setMetalValue(metalValue);
		dataStoreObj.addMetal(entity);
	}

	/**
	 * calculateRomanToDecimal method convert Roman number to decimal format
	 * 
	 * @param romanName
	 * @return
	 */
	public int calculateRomanToDecimal(String romanName) {
		
		int decimalNum = 0;
		romanName = romanName.toUpperCase();
		int romanCharValue = 0;
		int previousnum = 0;
		
		if (isRomanNumber(romanName)){
			
		}else{
			
			System.err.println("Invalid Roman Name:" + romanName);
		}
		for (int i = romanName.length() - 1; i >= 0; i--) {
			char x = romanName.charAt(i);
			romanCharValue = dataStoreObj.romanNumeralsValueMapping.get(x);
			if (romanCharValue < previousnum) {
				previousnum = romanCharValue;
				decimalNum = decimalNum - romanCharValue;
			} else {
				previousnum = romanCharValue;
				decimalNum = decimalNum + romanCharValue;
			}
		}
		return decimalNum;
	}
	
	private boolean isRomanNumber(String romanName){
		boolean flag = false;
		for (int i =0 ; i < romanName.length();) {
			if (dataStoreObj.romanNumeralsValueMapping.containsKey(romanName.charAt(i))){
				flag = true;
				return flag;
			}else{
				flag = false;
				return flag;
			}
		}
		return flag;
	}
}
