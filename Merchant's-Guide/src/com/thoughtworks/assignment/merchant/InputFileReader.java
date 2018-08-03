package com.thoughtworks.assignment.merchant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.thoughtworks.assignment.repository.DataStore;
import com.thoughtworks.assignment.utility.Constants;

/**
 * InputFileReader class read file line by line
 *
 * @author Ankur Pande
 * @version 1.0
 * @updated
 * @update by
 * @since 12 Jun 2016
 */
public class InputFileReader {

    DataStore dataStoreObj;

    public InputFileReader(DataStore dataStoreObj) {
        this.dataStoreObj = dataStoreObj;
    }

    /**
     * readInputFile read specified fileName
     *
     * @param fileName
     */
    public void readInputFile(String fileName) {

        BufferedReader inputBufferedReader = null;
        String eachLine;
        try {
            inputBufferedReader = new BufferedReader(new FileReader(fileName));
            while ((eachLine = inputBufferedReader.readLine()) != null) {
                storeEachLineInDataStore(eachLine);
            }
            inputBufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (inputBufferedReader != null) {
                    inputBufferedReader.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    /**
     * storeEachLineInDataStore does separating of input lines
     *
     * @param line
     */
    private void storeEachLineInDataStore(String line) {
        String[] tokens = line.split(Constants.SPACE);
        if (tokens.length == 3) {
            this.dataStoreObj.addMetalMeasure(tokens[0], tokens[2]);
        } else if (line.contains(Constants.IS)
                && line.contains(Constants.CREDITS)
                && !(line.endsWith(Constants.QUESTION_MARK))) {
            this.dataStoreObj.addValueCalculationLine(line);
        } else if (line.endsWith(Constants.QUESTION_MARK)) {
            this.dataStoreObj.addQuestionMarkLine(line);
        }
    }

}
