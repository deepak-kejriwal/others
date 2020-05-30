package com.leetcode;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LeetcodeHttpClient {

	private static String excelPath = "C:\\1\\leetcode\\leetcode-excel";
	public static void main(String[] args) throws Exception {
		Map<Integer, Integer> map = new HashMap<>();
		readExcel("Facebook-All", map);
		Map<String,List<Integer>> res = createList(map);
		int count=0;
		for(Entry<String, List<Integer>> entry: res.entrySet()) {
			String hashId = entry.getKey();
			for(Integer questionId : entry.getValue()) {
				sendingPostRequest(hashId,questionId.toString() );
				count++;
				Thread.sleep(100);
			}
		}
		System.out.println(count);
	}
	
	private static Map<String,List<Integer>> createList(Map<Integer, Integer> map) {
		List<Integer> temp = new ArrayList<>();
		
		List<Integer> greaterThan19 = map.entrySet().stream().filter(entry->entry.getValue()>19).map(entry->entry.getKey()).collect(Collectors.toList());
		temp.addAll(greaterThan19);
		
		List<Integer> greaterThan9 = map.entrySet().stream().filter(entry-> entry.getValue()<20 && (entry.getValue()>9  ||(entry.getKey()>1200 && entry.getValue()>0))).map(entry->entry.getKey()).collect(Collectors.toList());
		temp.addAll(greaterThan9);
		
		List<Integer> lessThan10 = map.entrySet().stream().filter(entry-> entry.getValue()<10 && !temp.contains(entry.getKey()) && (entry.getValue()>4||(entry.getKey()>1000 && entry.getValue()>0) )).map(entry->entry.getKey()).collect(Collectors.toList());					
		temp.addAll(lessThan10);
		
		List<Integer> lessThan5 = map.entrySet().stream().filter(entry->(entry.getValue()>1 &&entry.getValue()<5 && !temp.contains(entry.getKey()))).map(entry->entry.getKey()).collect(Collectors.toList());
		temp.addAll(lessThan5);
		
		List<Integer> all =  map.entrySet().stream().filter(entry -> !temp.contains(entry.getKey())).map(entry->entry.getKey()).collect(Collectors.toList());
		Map<String,List<Integer>> res = new HashMap<>();
		res.put("xspmn221", greaterThan19);
		res.put("xspmwem5", greaterThan9);
		res.put("xspm0y9c", lessThan10);
		res.put("xspm7s5i", lessThan5);
		res.put("xspmt0cj", all);
		System.out.println(map.size());
	//	System.out.println(greaterThan19.size()+greaterThan9.size()+lessThan10.size()+others.size());
		return res;
	}

	private static void readExcel(String sheetName, Map<Integer, Integer> map) throws Exception {

        try
        {
            FileInputStream file = new FileInputStream(new File(excelPath + "\\LeetCode" + ".xlsx"));
 
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheet(sheetName);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) 
            {
                Row row = rowIterator.next();
                int rowNum = row.getRowNum();
                if (rowNum == 0) {
                	continue;
                }
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                 
                Cell cell1 = row.getCell(1);
                Integer questionId = (int) cell1.getNumericCellValue();
                Cell cell2 = row.getCell(4);
                Integer frequency = (int) cell2.getNumericCellValue();
                map.put(questionId, frequency);
            }
            file.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

	
	private static void sendingPostRequest(String hashId, String questionId) throws Exception {
		 
		  String url = "https://leetcode.com/graphql";
		  URL obj = new URL(url);
		  HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		 
		        // Setting basic post request
		  con.setRequestMethod("POST");
		  con.setRequestProperty("authority", "leetcode.com");
		  con.setRequestProperty("pragma", "no-cache");
		  con.setRequestProperty("cache-control", "no-cache");
		  con.setRequestProperty("accept", "*/*");
		  con.setRequestProperty("x-newrelic-id", "UAQDVFVRGwEAXVlbBAg=");
		  con.setRequestProperty("x-csrftoken", "50CNxK65YNqhd6BZdkmeLF9cZy2xEb7FigocYJEnhSQvuIKylMoCqnlx25AUJmY8");
		  con.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.129 Safari/537.36");
		  con.setRequestProperty("content-type", "application/json");
		  con.setRequestProperty("origin", "https://leetcode.com");
		  con.setRequestProperty("sec-fetch-site", "same-origin");
		  con.setRequestProperty("sec-fetch-mode", "cors");
		  con.setRequestProperty("sec-fetch-dest", "empty");
		  con.setRequestProperty("referer", "https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/");
		  con.setRequestProperty("accept-language", "en-US,en;q=0.9");
		  con.setRequestProperty("cookie", "_ga=GA1.2.411294900.1585107613; __stripe_mid=63e16886-4b1f-4579-9780-ad3074306b8a; __cfduid=dcb3fb2d13e63faaedc8da66b55b9d57d1587712374; csrftoken=50CNxK65YNqhd6BZdkmeLF9cZy2xEb7FigocYJEnhSQvuIKylMoCqnlx25AUJmY8; LEETCODE_SESSION=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfYXV0aF91c2VyX2lkIjoiMTQ5NjczNCIsIl9hdXRoX3VzZXJfYmFja2VuZCI6ImRqYW5nby5jb250cmliLmF1dGguYmFja2VuZHMuTW9kZWxCYWNrZW5kIiwiX2F1dGhfdXNlcl9oYXNoIjoiNjU0YzU0NTRkOTM5ZGIxMjI3MWFjZGJiM2QyODIzODViNDIyZDFjMyIsImlkIjoxNDk2NzM0LCJlbWFpbCI6ImRlZXBhay5rZWpyaXdhbEBnbWFpbC5jb20iLCJ1c2VybmFtZSI6ImRlZXBha25ldGZyZW4iLCJ1c2VyX3NsdWciOiJkZWVwYWtuZXRmcmVuIiwiYXZhdGFyIjoiaHR0cHM6Ly93d3cuZ3JhdmF0YXIuY29tL2F2YXRhci9kNTFlZjM0N2FmYmUwMjRkOWZhYmIyODcxMGFhYTFmMy5wbmc_cz0yMDAiLCJ0aW1lc3RhbXAiOiIyMDIwLTA0LTE5IDIzOjAxOjA1LjQzNDM5MSswMDowMCIsIklQIjoiNjcuMTgwLjg2Ljg0IiwiSURFTlRJVFkiOiIyNjdiZGU1YmFkZDVmMjQ0Yjg1YjgzNDUwYWNjZWJiNSIsIl9zZXNzaW9uX2V4cGlyeSI6MTIwOTYwMH0.kmNQuh4v5lpN9uQliF9rwdxGlwUlDjHz2twMI0cK3Uo; __atuvc=4%7C15%2C41%7C16%2C41%7C17%2C43%7C18%2C1%7C19; _gid=GA1.2.1867950346.1588613970; c_a_u=\"ZGVlcGFrbmV0ZnJlbg==:1jVf68:07bnAx9j8JKxz1xSGWGEGov0v6c\"; _gat=1; __cf_bm=e17cbd540b246fed90a2d4a0022d66d780f8c6aa-1588615319-1800-AVJUBFLCU/nHy0hAVU83pT3+RY7MCVITrluTaPFk6d58Aq5NvfBAtjUb9c95zDBWWUqPoFHxIfPCT+ufrej7gLor2DkcDJric9rcOZoGHxLO6OhiW7w0A3rIJnPQKybpuw==");
		 
		  String postJsonData = "{\"operationName\":\"addQuestionToFavorite\",\"variables\":{\"favoriteIdHash\":\""+hashId+"\",\"questionId\":\""+questionId+"\"},\"query\":\"mutation addQuestionToFavorite($favoriteIdHash: String!, $questionId: String!) {\\n  addQuestionToFavorite(favoriteIdHash: $favoriteIdHash, questionId: $questionId) {\\n    ok\\n    error\\n    favoriteIdHash\\n    questionId\\n    __typename\\n  }\\n}\\n\"}";
		  
		  // Send post request
		  con.setDoOutput(true);
		  DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		  wr.writeBytes(postJsonData);
		  wr.flush();
		  wr.close();
		 
		  int responseCode = con.getResponseCode();
		  System.out.println("nSending 'POST' request to URL : " + url);
		  System.out.println("Post Data : " + postJsonData);
		  System.out.println("Response Code : " + responseCode);
		 
		  BufferedReader in = new BufferedReader(
		          new InputStreamReader(con.getInputStream()));
		  String output;
		  StringBuffer response = new StringBuffer();
		 
		  while ((output = in.readLine()) != null) {
		   response.append(output);
		  }
		  in.close();
		  
		  //printing result from response
		  System.out.println(response.toString());
		 }

}
