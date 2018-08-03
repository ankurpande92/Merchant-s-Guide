package com.thoughtworks.assignment.main;

import java.util.Arrays;

public class CharSort {

	public static void main(String[] args) {
        //int[] intArr={3, -1, -1, -1, -1, -1, 2, 0, 0, 0 };
        int[] intArr = {-1, 3, -5, 4, 6, -1, 2, -7, -3};		
        //int[] intArr={-7,-8,-6,-9,-10};
        findMaxSubArray(intArr);
        abc();
    }

    public static void findMaxSubArray(int[] inputArray){

        int maxSum = Integer.MIN_VALUE; 
        int cumulativeSum= 0;
        for (int currentIndex = 0; currentIndex < inputArray.length; currentIndex++) {
            int eachArrayItem = inputArray[currentIndex];
            cumulativeSum+=eachArrayItem;
            if(cumulativeSum>maxSum){
                maxSum = cumulativeSum;
            }
            else if (cumulativeSum<0){
                cumulativeSum=0;
            }
        }
        System.out.println("Max sum: "+maxSum);
    }
		
	public static void abc(){	
		String theWord = "wwwwbbaacdrrrrpppd";
		int count =1;
		char[] arr = new char[theWord.length()];
		for(int i=0;i<theWord.length();i++)
		{
		    char ch =theWord.charAt(i);
		    if (i+1 != theWord.length() && ch == theWord.charAt(i+1)){
		    	count++;
		    }else{
		    	System.out.print(ch);
		    	System.out.print(count);
		    	count =1;
		    }
		}
		
	}
}

