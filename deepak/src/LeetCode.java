import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.att.idp.catalog.common.constants.util.JsonService;
import com.example.Example;
import com.example.StatStatusPair;

public class LeetCode {
	private static Map<Integer, String> difficulty = new HashMap<>();
	static {
		difficulty.put(1, "Easy");
		difficulty.put(2, "Medium");
		difficulty.put(3, "Hard");
	}

	public static void main(String[] args) throws Exception {
		LeetCode lc = new LeetCode();
		String dirPath = "C:\\Users\\dk882q\\Desktop\\leetcode";
		Map<String, List<LeetCodeRow>> map=new HashMap<>();
		File dir = new File(dirPath);
		for (File file : dir.listFiles()) {
			if(file.isFile())
			lc.processFiles(map,file);
		}
		lc.writeToExcel(map);
	}

	private void processFiles(Map<String, List<LeetCodeRow>> map,File file) throws Exception {
		String jsonString = new String(Files.readAllBytes(file.toPath()));
		Example dlb = JsonService.getObjectFromJson(jsonString, Example.class);
		List<LeetCodeRow> data = loadData(dlb);
		String fileName = FilenameUtils.getBaseName(file.getName());
		map.put(fileName, data);
		
	}

	private List<LeetCodeRow> loadData(Example dlb) {
		List<LeetCodeRow> list = new ArrayList<>();
		list.add(new LeetCodeRow("Question", "Difficulty", "Frequency"));
		for (StatStatusPair statStatusPair : dlb.getStatStatusPairs()) {
			list.add(new LeetCodeRow(statStatusPair.getStat().getQuestionTitle(),
					difficulty.get(statStatusPair.getDifficulty().getLevel()),
					statStatusPair.getFrequency().toString()));
		}
		return list;
	}

	private void writeToExcel(Map<String, List<LeetCodeRow>> data) throws Exception {

		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		for (Map.Entry<String, List<LeetCodeRow>> entry : data.entrySet()) {
			addSheet(workbook, entry.getValue(), entry.getKey());
		}

		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("C:\\SC\\" + "LeetCode" + ".xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("LeetCode.xlsx written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void addSheet(XSSFWorkbook workbook, List<LeetCodeRow> data, String sheetName) throws Exception {
		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet(sheetName);
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		Row row = null;
		Cell cell1 = null;
		Cell cell2 = null;
		int rownum = 0;
		for (LeetCodeRow rowData : data) {
			row = sheet.createRow(rownum++);
			cell1 = row.createCell(0);
			cell1.setCellValue(rowData.getQuestion());
			cell2 = row.createCell(1);
			cell2.setCellValue(rowData.getDifficulty());
			cell2 = row.createCell(2);
			cell2.setCellValue(rowData.getFrequency());
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
		}
	}

	class LeetCodeRow {
		private String question;
		private String difficulty;
		private String frequency;

		public LeetCodeRow(String question, String difficulty, String frequency) {
			super();
			this.question = question;
			this.difficulty = difficulty;
			this.frequency = frequency;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public String getDifficulty() {
			return difficulty;
		}

		public void setDifficulty(String difficulty) {
			this.difficulty = difficulty;
		}

		public String getFrequency() {
			return frequency;
		}

		public void setFrequency(String frequency) {
			this.frequency = frequency;
		}

	}
}
