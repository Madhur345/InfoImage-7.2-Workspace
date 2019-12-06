package apd.infoimage.iwm.genericLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.InvalidArgumentException;
import org.testng.Assert;

public class ExcelLib {

	CommonUtils util = new CommonUtils();

	/**
	 * Description - This method get the data
	 * 
	 * @author KencharV
	 * @param fileName,
	 *            sheetName, row no, colNum, value
	 * 
	 */

	public String getExcelData(String fileName, String sheetName, int rowNum, int colNum) {

		try {
			String data = "";
			double dataNum;
			boolean dataBool;
			FileInputStream fis = new FileInputStream(fileName);

			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			Row row = sh.getRow(rowNum);
			Cell cel = row.getCell(colNum);

			if (cel == null || cel.getCellType() == Cell.CELL_TYPE_BLANK) {
				System.out.println("null");
				String s1 = "null or empty";
				return s1;
			} else {
				switch (cel.getCellType()) {

				case Cell.CELL_TYPE_NUMERIC:
					dataNum = cel.getNumericCellValue();
					Double d = new Double(dataNum);
					int g = d.intValue();
					data = String.valueOf(g);
					break;

				case Cell.CELL_TYPE_STRING:
					data = cel.getStringCellValue();

					break;

				case Cell.CELL_TYPE_BOOLEAN:
					dataBool = cel.getBooleanCellValue();
					data = Boolean.toString(dataBool);

					break;
				}
				return data.toString();
			}
		} catch (NullPointerException nullPointerException) {
			System.out.println(" sheetname/row number/column number is null/not exists ");
			Assert.fail("sheetname/row number/column number is null/not exists ");
			return null;
		} catch (InvalidArgumentException invalidFormatException) {
			System.out.println(" Excel file formate is wrong ");
			Assert.fail("Excel file formate is wrong");
			return null;
		} catch (IOException IOException) {
			System.out.println("   there is a problem in reading or writing data from/to file");
			Assert.fail(" there is a problem in reading or writing data from/to file");
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(" exception is raised,while perform operations with excel file");
			Assert.fail(" exception is raised,while perform operations with excel file");
			return null;
		}

	}

	public static int getRowCount(String xlpath, String sheet) {
		int rc = 0;
		try {
			FileInputStream fis = new FileInputStream(xlpath);
			Workbook wb = WorkbookFactory.create(fis);

			rc = wb.getSheet(sheet).getLastRowNum();
		} catch (Exception e) {
		}

		return rc;
	}// end of method1

	// ---------------------------------

	public static String getCellValue(String xlpath, String sheet, int r, int c) {
		String v = " ";
		try {
			FileInputStream fis = new FileInputStream(xlpath);
			Workbook wb = WorkbookFactory.create(fis);
			v = wb.getSheet(sheet).getRow(r).getCell(c).toString();
		} catch (Exception e) {
		}
		return v;
	}// end of method2

	public static int getCellValueInt(String xlpath, String sheet, int r, int c) {

		int k = 0;
		try {
			FileInputStream fis = new FileInputStream(xlpath);
			Workbook wb = WorkbookFactory.create(fis);
			k = (int) wb.getSheet(sheet).getRow(r).getCell(c).getNumericCellValue();

		} catch (Exception e) {
		}
		return k;
	}// end of method2

	/**
	 * Description - This method set the excel data in a specified column and row
	 * 
	 * @author KencharV
	 * @param fileName,
	 *            sheetName, value, rowNum, colNum
	 * 
	 */
	public void setExcelData(String fileName, String sheetName, String value, int rowNum, int colNum) {

		try {
			File excel = new File(fileName);
			FileInputStream fis = new FileInputStream(excel);
			XSSFWorkbook workBook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workBook.getSheet(sheetName);
			XSSFRow row = sheet.getRow(rowNum);

			if (row == null) {
				// System.out.println("row is "+row);
				row = sheet.createRow(rowNum);
			}

			XSSFCell cell = row.createCell(colNum);
			cell.setCellValue(value);

			// open an OutputStream to save written data into Excel file
			FileOutputStream os = new FileOutputStream(new File(fileName));
			workBook.write(os);

			// Close workbook, OutputStream and Excel file to prevent leak
			os.flush();
			os.close();
			fis.close();
			System.out.println("Written '" + value + "' to row " + rowNum + " and column " + colNum);

		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

}
