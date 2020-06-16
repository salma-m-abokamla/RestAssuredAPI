package files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelDataSheet {
	static XSSFWorkbook excelWorkbook = null;
	static XSSFSheet excelSheet = null;
	static XSSFRow row = null;
	static XSSFCell cell = null;

	public static Object[][] getDataSheet() throws IOException {

		FileInputStream fis = new FileInputStream(ResourceUrls.pathFile); // Your .xlsx file name along with
																			// path
		excelWorkbook = new XSSFWorkbook(fis);
		// Read sheet inside the workbook by its name
		excelSheet = excelWorkbook.getSheet(ResourceUrls.sheetName); // Your sheet name
		// Find number of rows in excel file
		/*
		 * System.out.println("First Row Number/index:" + excelSheet.getFirstRowNum() +
		 * " *** Last Row Number/index:" + excelSheet.getLastRowNum());
		 */
		int rowCount = excelSheet.getLastRowNum() - excelSheet.getFirstRowNum() + 1;
		int colCount = excelSheet.getRow(0).getLastCellNum();
		// System.out.println("Row Count is: " + rowCount + " *** Column count is: " +
		// colCount);
		Object data1[][] = new Object[rowCount - 1][colCount];
		System.out.println("BaseUrl                                               Subscription         UserName");
		for (int rNum = 2; rNum <= rowCount; rNum++) {
			for (int cNum = 0; cNum < colCount; cNum++) {

				System.out.print(getCellData(ResourceUrls.sheetName, cNum, rNum) + " " +" "); // Your sheet name

				data1[rNum - 2][cNum] = getCellData(ResourceUrls.sheetName, cNum, rNum); // Your
																							// sheet
																							// name

			}
			System.out.println();
		}
		return data1;

	}

	private static void Switch(int rowCount) {
		// TODO Auto-generated method stub

	}

	// Function will always used as below. It returns the data from a cell - No need
	// to make any changes
	public static String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";
			int index = excelWorkbook.getSheetIndex(sheetName);
			if (index == -1)
				return "";
			excelSheet = excelWorkbook.getSheetAt(index);
			row = excelSheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				String cellText = NumberToTextConverter.toText(cell.getNumericCellValue());
				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {
			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist in xls";
		}
	}
}
