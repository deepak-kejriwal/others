package com.coders;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* 
* @author Deepak Kejriwal
*
*/
/*
 * 
 * Each row represent score of the candidate. Consider average score of each candidate, find max score in integer. Take floor value.
 * Example input:
 * {
	{"deepak","20"},
	{"deepak","31"},
	{"kumar","24"},
	{"kejriwal","23"}
	}	
	Here average of deepak=25.5, kumar=24, kejriwal=23. Max score here is 25.5. Floor int value is 25.
 * 
 * 
 */
public class MaxAverageSCore {

	public static void main(String[] args) {
		String[][] input= {
				{"deepak","20"},
				{"deepak","31"},
				{"kumar","24"},
				{"kejriwal","23"}};
		System.out.println(getMaxAverageScore(input));
	}

	public static int getMaxAverageScore(String[][] input) {
		if(input==null||input.length==0||input[0].length==0) return 0;
		Stream<String[]> st=Arrays.stream(input);
		Map<String,Double> map=st.collect(Collectors.groupingBy(
				record->record[0],Collectors.averagingDouble(record->Double.parseDouble(record[1]))));
		Double d=map.values().stream().mapToDouble(x->x).max().getAsDouble();
		System.out.println(map);
		return (int)Math.floor(d);		
	}
}
