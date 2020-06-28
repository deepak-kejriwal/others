package com.leetcode;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.example.Example;
import com.leetcode.bean.AllQuestionMain;
import com.leetcode.bean.Data;

import util.JsonService;

public class LeetcodeHttpClient {

	private static String excelPath = "C:\\Users\\deepak\\Google Drive (deepak1037@gmail.com)\\Leetcode\\leetcode-excel";
	static List<String> list = Arrays.asList("6M20", "6M15","6M10", "6M5", "6M0", "All");
	private static String csrftoken =null;
	public static void main(String[] args) throws Exception {
		start();
	}






	public static void start() throws Exception, InterruptedException {
		Map<Integer, Integer> map = new HashMap<>();
		readExcel("Facebook-All", map);
		Map<String, String> fvList = loadFavoriteList();
		deleteList("FB",  fvList);
		createList("FB");
		fvList = loadFavoriteList();
		Map<String,List<Integer>> res = createList("FB", fvList,map);
		int count=0;
		for(Entry<String, List<Integer>> entry: res.entrySet()) {
			String hashId = entry.getKey();
			for(Integer questionId : entry.getValue()) {
				addQuestionToList(hashId,questionId.toString() );
				count++;
				System.out.println(count);
				Thread.sleep(100);
			}
		}
		System.out.println(count);
	}
	





	private static Map<String,List<Integer>> createList(String string, Map<String, String> fvList, Map<Integer, Integer> map) {
		List<Integer> temp = new ArrayList<>();
		
		List<Integer> greaterThan19 = map.entrySet().stream().filter(entry->selectedFor20List(entry)).map(entry->entry.getKey()).collect(Collectors.toList());
		temp.addAll(greaterThan19);
		
		List<Integer> greaterThan14 = map.entrySet().stream().filter(entry->selectedFor15List(temp, entry)).map(entry->entry.getKey()).collect(Collectors.toList());
		temp.addAll(greaterThan14);
		
		List<Integer> greaterThan9 = map.entrySet().stream().filter(entry-> selectedFor10List(temp, entry)).map(entry->entry.getKey()).collect(Collectors.toList());
		temp.addAll(greaterThan9);
		
		List<Integer> lessThan10 = map.entrySet().stream().filter(entry-> selectedFor5List(temp, entry)).map(entry->entry.getKey()).collect(Collectors.toList());					
		temp.addAll(lessThan10);
		
		List<Integer> lessThan5 = map.entrySet().stream().filter(entry->selectedFor0List(temp, entry)).map(entry->entry.getKey()).collect(Collectors.toList());
		temp.addAll(lessThan5);
		
		List<Integer> all =  map.entrySet().stream().filter(entry -> !temp.contains(entry.getKey())).map(entry->entry.getKey()).collect(Collectors.toList());
		Map<String,List<Integer>> res = new HashMap<>();
		res.put(fvList.get(string+"6M20"), greaterThan19);//20
		res.put(fvList.get(string+"6M15"), greaterThan14);//15
		res.put(fvList.get(string+"6M10"), greaterThan9);//10
		res.put(fvList.get(string+"6M5"), lessThan10);//5
		res.put(fvList.get(string+"6M0"), lessThan5);//0
		res.put(fvList.get(string+"All"), all);//All
		System.out.println(map.size());
	//	System.out.println(greaterThan19.size()+greaterThan9.size()+lessThan10.size()+others.size());
		return res;
	}

	private static boolean selectedFor0List(List<Integer> temp, Entry<Integer, Integer> entry) {
		return !temp.contains(entry.getKey())&& entry.getValue()>1 &&entry.getValue()<5  ;
	}

	private static boolean selectedFor5List(List<Integer> temp, Entry<Integer, Integer> entry) {
		return !temp.contains(entry.getKey()) && entry.getValue()<10 &&  (entry.getValue()>4||(entry.getKey()>999 && entry.getValue()>0) );
	}

	private static boolean selectedFor10List(List<Integer> temp, Entry<Integer, Integer> entry) {
		return !temp.contains(entry.getKey()) && entry.getValue()<15 && (entry.getValue()>9  ||(entry.getKey()>999 && entry.getValue()>4) || (entry.getKey()>1199 && entry.getValue()>2) || (entry.getKey()>1399 && entry.getValue()>0));
	}
	
	private static boolean selectedFor15List(List<Integer> temp, Entry<Integer, Integer> entry) {
		return !temp.contains(entry.getKey()) && entry.getValue()<20 &&( entry.getValue()>14 || (entry.getKey()>999 && entry.getValue()>9));
	}

	private static boolean selectedFor20List(Entry<Integer, Integer> entry) {
		return entry.getValue()>19 || (entry.getKey()>1199 && entry.getValue()>9);
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


	private static void deleteList(String string, Map<String, String> fvList) throws Exception {
		for(String str : list) {
			String idhash = fvList.get(string+str);
			  String url = "https://leetcode.com/list/api/"+idhash;
			  URL obj = new URL(url);
			  HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			 
			  loadHeader(con);
			  con.setRequestMethod("DELETE");
			  // Send post request
			  con.setDoOutput(true);
			  DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			  wr.flush();
			  wr.close();
			 
			  int responseCode = con.getResponseCode();
		}		  		
	}
	
	private static void createList(String string) throws Exception {
		for(String str : list) {
			
			  String url = "https://leetcode.com/list/api/";
			  URL obj = new URL(url);
			  HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			 
			  loadHeader(con);
			  String postJsonData = "{\"name\":\""+string+str+"\"}";
			  // Send post request
			  con.setDoOutput(true);
			  DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			  wr.writeBytes(postJsonData);
			  wr.flush();
			  wr.close();
			 
			  int responseCode = con.getResponseCode();
		}		  		
	}
	
	
	private static Map<String, String> loadFavoriteList() throws Exception {
		  String url = "https://leetcode.com/graphql";
		  URL obj = new URL(url);
		  HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		 
		  loadHeader(con);
		  String postJsonData = "{\"operationName\":\"favoritesList\",\"variables\":{},\"query\":\"query favoritesList { favoritesLists { allFavorites {idHash name}}}\"}";
		
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
		  csrftoken=con.getHeaderField("csrftoken");
		  while ((output = in.readLine()) != null) {
		   response.append(output);
		  }
		  in.close();
		  AllQuestionMain dlb = JsonService.getObjectFromJson(response.toString(), AllQuestionMain.class);
		  Map<String, String> map = new HashMap<>();
		  dlb.getData().getFavoritesLists().getAllFavorites().stream().forEach(fv->map.put(fv.getName(), fv.getIdHash()));
		  return map;
	}
	private static void addQuestionToList(String hashId, String questionId) throws Exception {
		 
		  String url = "https://leetcode.com/graphql";
		  URL obj = new URL(url);
		  HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		 
		  loadHeader(con);
		 
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
		 // System.out.println(response.toString());
	}



}
