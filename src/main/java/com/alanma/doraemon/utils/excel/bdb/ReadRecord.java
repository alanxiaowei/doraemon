package com.alanma.doraemon.utils.excel.bdb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadRecord {

	private Logger logger = LoggerFactory.getLogger(ReadRecord.class);

	private Workbook wb;
	private Sheet sheet;
	private Row row;

	private List<PurchaseInfoVO> getPurchaseInfo(String filepath) {
		List<PurchaseInfoVO> list = null;
		if (filepath == null) {
			return null;
		}
		String ext = filepath.substring(filepath.lastIndexOf("."));
		try {
			InputStream is = new FileInputStream(filepath);
			if (".xlsx".equals(ext)) {
				wb = new XSSFWorkbook(is);
			} else {
				wb = null;
			}
			list = readExcelContent();
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		} catch (Exception e) {
			logger.error("Exception", e);
		}
		return list;
	}

	public List<PurchaseInfoVO> readExcelContent() throws Exception {

		if (wb == null) {
			throw new Exception("Workbook对象为空！");
		}
		List<PurchaseInfoVO> list = new ArrayList<PurchaseInfoVO>();
		sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		System.out.println("总行数：" + rowNum);
		// int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i < rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			PurchaseInfoVO vo = new PurchaseInfoVO();
			if (getCellFormatValue(row.getCell(0)) == null) {
				break;
			}
			vo.setNumber(getCellFormatValue(row.getCell(j++)));
			vo.setName(getCellFormatValue(row.getCell(j++)));
			vo.setWechat(getCellFormatValue(row.getCell(j++)));
			vo.setCellphone(getCellFormatValue(row.getCell(j++)));
			vo.setMail(getCellFormatValue(row.getCell(j++)));
			vo.setCurrency(getCellFormatValue(row.getCell(j++)));
			vo.setAmount(getCellFormatValue(row.getCell(j++)));
			vo.setRechargeAddr(getCellFormatValue(row.getCell(j++)));
			vo.setDate(getCellFormatValue(row.getCell(j++)));
			vo.setScreenshort(getCellFormatValue(row.getCell(j++)));
			vo.setQuantity(getCellFormatValue(row.getCell(j++)));
			list.add(vo);
		}
		return list;
	}

	@SuppressWarnings("deprecation")
	private String getCellFormatValue(Cell cell) {
		String cellvalue = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
			DecimalFormat decimalFormat = new DecimalFormat("###################.###########"); 
			cellvalue = decimalFormat.format(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
			// 取得当前的Cell字符串
			cellvalue = cell.getRichStringCellValue().getString();
			break;
		default:// 默认的Cell值
			cellvalue = "";
		}

		return cellvalue;
	}
	
	public static void main(String[] args) {
		String path = "D:\\数据类型.xlsx";
		ReadRecord rr = new ReadRecord();
		List<PurchaseInfoVO> list = rr.getPurchaseInfo(path);
		System.out.println(list);
	}
}
