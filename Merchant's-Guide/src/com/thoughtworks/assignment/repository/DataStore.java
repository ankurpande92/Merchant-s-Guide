package com.thoughtworks.assignment.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.assignment.merchant.Metal;

/**
 * DataStore class work as singleton class which has one point of contact for
 * stored data.
 * 
 * @author Ankur Pande
 * @version 1.0
 * @since 12 Jun 2016
 * @updated
 * @update by
 *
 */
public class DataStore {

	private Map<String, String> metalMeasureMap;
	private List<String> metalValueCalculationStrings;
	private List<String> questionMarkLines;
	private Map<String, Metal> entityMap;

	public Map<Character, Integer> romanNumeralsValueMapping;

	private static DataStore dataStore;

	/**
	 * Private constructor for singleton class
	 * 
	 */
	private DataStore() {
		romanNumeralsValueMapping = new HashMap<Character, Integer>();
		romanNumeralsValueMapping.put('I', 1);
		romanNumeralsValueMapping.put('V', 5);
		romanNumeralsValueMapping.put('X', 10);
		romanNumeralsValueMapping.put('L', 50);
		romanNumeralsValueMapping.put('C', 100);
		romanNumeralsValueMapping.put('D', 500);
		romanNumeralsValueMapping.put('M', 1000);

		entityMap = new HashMap<String, Metal>();
		metalMeasureMap = new HashMap<String, String>();
		metalValueCalculationStrings = new ArrayList<String>();
		questionMarkLines = new ArrayList<String>();
	}

	/**
	 * getInstance of dataStore
	 * 
	 * @return
	 */
	public static DataStore getInstance() {
		if (dataStore == null) {
			synchronized (DataStore.class) {
				if (dataStore == null) {
					dataStore = new DataStore();
				}
			}
		}
		return dataStore;
	}

	public void addMetal(Metal entityObj) {
		this.entityMap.put(entityObj.getMetalName(),
				entityObj);
	}

	public boolean hasThisMetal(String entityName) {
		return this.entityMap.containsKey(entityName);
	}

	public Metal getMetalNameValue(String entityName) {
		return this.entityMap.get(entityName);
	}

	public void addMetalMeasure(String name, String value) {
		this.metalMeasureMap.put(name, value);
	}

	public boolean hasThisMetalMeasure(String entityMeasure) {
		return this.metalMeasureMap.containsKey(entityMeasure);
	}

	public String getMetalMeasureValue(String entityMeasure) {
		return this.metalMeasureMap.get(entityMeasure);
	}

	public List<String> getValueCalculationLines() {
		return this.metalValueCalculationStrings;
	}

	public void addValueCalculationLine(String line) {
		this.metalValueCalculationStrings.add(line);
	}

	public List<String> getQuestionMarkLines() {
		return questionMarkLines;
	}

	public void addQuestionMarkLine(String line) {
		this.questionMarkLines.add(line);
	}

}
