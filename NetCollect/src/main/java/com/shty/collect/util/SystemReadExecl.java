package com.shty.collect.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.shty.collect.domain.TbLinkedinSysKeyword;
import com.shty.collect.domain.TbSystemSocialaccount;
import com.shty.collect.domain.TbTwitterSysTask;

public class SystemReadExecl {

	// 总行数
	private int totalRows = 0;
	// 总条数
	private int totalCells = 0;
	// 错误信息接收器
	private String errorMsg;

	// 构造方法
	public SystemReadExecl() {
	}

	// 获取总行数
	public int getTotalRows() {
		return totalRows;
	}

	// 获取总列数
	public int getTotalCells() {
		return totalCells;
	}

	// 获取错误信息
	public String getErrorInfo() {
		return errorMsg;
	}

	public List<TbLinkedinSysKeyword> getExcelLinkedin(MultipartFile mFile) {

		String fileName = mFile.getOriginalFilename();// 获取文件名

		List<TbLinkedinSysKeyword> list = null;
		try {
			if (!validateExcel(fileName)) {// 验证文件名是否合格
				return null;
			}
			boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
			if (isExcel2007(fileName)) {
				isExcel2003 = false;
			}
			list = createLinkedinExcel(mFile.getInputStream(), isExcel2003);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据excel里面的内容读取客户信息
	 * 
	 * @param is
	 *            输入流
	 * @param isExcel2003
	 *            excel是2003还是2007版本
	 * @return
	 * @throws IOException
	 */
	public List<TbLinkedinSysKeyword> createLinkedinExcel(InputStream is, boolean isExcel2003) {
		List<TbLinkedinSysKeyword> list = null;
		try {
			Workbook wb = null;
			if (isExcel2003) {// 当excel是2003时,创建excel2003
				wb = new HSSFWorkbook(is);
			} else {// 当excel是2007时,创建excel2007
				wb = new XSSFWorkbook(is);
			}
			list = readLinkedinValue(wb);// 读取Excel里面客户的信息
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 读取Excel里面客户的信息
	 * 
	 * @param wb
	 * @return
	 */
	private List<TbLinkedinSysKeyword> readLinkedinValue(Workbook wb) {
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);
		// 得到Excel的行数
		this.totalRows = sheet.getPhysicalNumberOfRows();
		// 得到Excel的列数(前提是有行数)
		if (totalRows > 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<TbLinkedinSysKeyword> userList = new ArrayList<TbLinkedinSysKeyword>();
		// 循环Excel行数
		for (int r = 1; r < totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			TbLinkedinSysKeyword tbLinkedinSysKeyword = new TbLinkedinSysKeyword();
			// 循环Excel的列
			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {
					if (c == 0) {
						tbLinkedinSysKeyword.setKeyword(cell.getStringCellValue());// keyword
					}
				}
			}
			// 添加到list
			userList.add(tbLinkedinSysKeyword);
		}
		return userList;
	}

	/**
	 * 读EXCEL文件，获取信息集合
	 * 
	 * @param fielName
	 * @return
	 */
	public List<TbTwitterSysTask> getExcelInfo(MultipartFile mFile) {
		String fileName = mFile.getOriginalFilename();// 获取文件名
		List<TbTwitterSysTask> twitterSysTasksList = null;
		try {
			if (!validateExcel(fileName)) {// 验证文件名是否合格
				return null;
			}
			boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
			if (isExcel2007(fileName)) {
				isExcel2003 = false;
			}
			twitterSysTasksList = createExcel(mFile.getInputStream(), isExcel2003);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twitterSysTasksList;
	}

	/**
	 * 根据excel里面的内容读取客户信息
	 * 
	 * @param is
	 *            输入流
	 * @param isExcel2003
	 *            excel是2003还是2007版本
	 * @return
	 * @throws IOException
	 */
	public List<TbTwitterSysTask> createExcel(InputStream is, boolean isExcel2003) {
		List<TbTwitterSysTask> userList = null;
		try {
			Workbook wb = null;
			if (isExcel2003) {// 当excel是2003时,创建excel2003
				wb = new HSSFWorkbook(is);
			} else {// 当excel是2007时,创建excel2007
				wb = new XSSFWorkbook(is);
			}
			userList = readExcelValue(wb);// 读取Excel里面客户的信息
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList;
	}

	/**
	 * 读取Excel里面客户的信息
	 * 
	 * @param wb
	 * @return
	 */
	private List<TbTwitterSysTask> readExcelValue(Workbook wb) {
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);
		// 得到Excel的行数
		this.totalRows = sheet.getPhysicalNumberOfRows();
		// 得到Excel的列数(前提是有行数)
		if (totalRows > 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<TbTwitterSysTask> userList = new ArrayList<TbTwitterSysTask>();
		// 循环Excel行数
		for (int r = 1; r < totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			TbTwitterSysTask twitterSysTask = new TbTwitterSysTask();
			// 循环Excel的列
			for (int c = 1; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {
					if (c == 1) {

						twitterSysTask.setUrl(cell.getStringCellValue());// URL

					} else if (c == 2) {

						twitterSysTask.setTaskName(cell.getStringCellValue());// Name

					} else if (c == 3) {
						String attributeId = String.valueOf(cell.getNumericCellValue());// attributeId
						twitterSysTask.setTaskattributeid(Long.parseLong(
								attributeId.substring(0, attributeId.length() - 2 > 0 ? attributeId.length() - 2 : 1)));

					} else if (c == 4) {
						String groupId = String.valueOf(cell.getNumericCellValue());// groupId
						twitterSysTask.setTaskgroupid(Long
								.parseLong(groupId.substring(0, groupId.length() - 2 > 0 ? groupId.length() - 2 : 1)));
					}
				}
			}
			// 添加到list
			userList.add(twitterSysTask);
		}
		return userList;
	}
	/**
	 * 读EXCEL文件，获取信息集合
	 * 
	 * @param fielName
	 * @return
	 */
	public List<TbSystemSocialaccount> getAccountExcelInfo(MultipartFile mFile) {
		String fileName = mFile.getOriginalFilename();// 获取文件名
		List<TbSystemSocialaccount> tbSystemSocialaccounts = null;
		try {
			if (!validateExcel(fileName)) {// 验证文件名是否合格
				return null;
			}
			boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
			if (isExcel2007(fileName)) {
				isExcel2003 = false;
			}
			tbSystemSocialaccounts = createAccountExcel(mFile.getInputStream(), isExcel2003);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tbSystemSocialaccounts;
	}

	/**
	 * 根据excel里面的内容读取客户信息
	 * 
	 * @param is
	 *            输入流
	 * @param isExcel2003
	 *            excel是2003还是2007版本
	 * @return
	 * @throws IOException
	 */
	public List<TbSystemSocialaccount> createAccountExcel(InputStream is, boolean isExcel2003) {
		List<TbSystemSocialaccount> userList = null;
		try {
			Workbook wb = null;
			if (isExcel2003) {// 当excel是2003时,创建excel2003
				wb = new HSSFWorkbook(is);
			} else {// 当excel是2007时,创建excel2007
				wb = new XSSFWorkbook(is);
			}
			userList = readAccountExcelValue(wb);// 读取Excel里面客户的信息
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList;
	}
	/**
	 * 读取AccountExcel里面客户的信息
	 * 
	 * @param wb
	 * @return
	 */
	private List<TbSystemSocialaccount> readAccountExcelValue(Workbook wb) {
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);
		// 得到Excel的行数
		this.totalRows = sheet.getPhysicalNumberOfRows();
		// 得到Excel的列数(前提是有行数)
		if (totalRows > 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<TbSystemSocialaccount> userList = new ArrayList<TbSystemSocialaccount>();
		// 循环Excel行数
		for (int r = 1; r < totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			TbSystemSocialaccount tbSystemSocialaccount = new TbSystemSocialaccount();
			// 循环Excel的列
			for (int c = 1; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {
					if (c == 1) {

						tbSystemSocialaccount.setAccountname(cell.getStringCellValue());// account

					} else if (c == 2) {

						tbSystemSocialaccount.setPassword(getCellValue(cell));// password

					} else if (c == 3) {
						String attributeId = String.valueOf(cell.getNumericCellValue());// accountType
						tbSystemSocialaccount.setAccounttype(
								attributeId.substring(0, attributeId.length() - 2 > 0 ? attributeId.length() - 2 : 1));

					}
				}
			}
			// 添加到list
			userList.add(tbSystemSocialaccount);
		}
		return userList;
	}

	/**
	 * 验证EXCEL文件
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean validateExcel(String filePath) {
		if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
			errorMsg = "文件名不是Excel格式";
			return false;
		}
		return true;
	}

	// @描述：是否是2003的excel，返回true是2003
	public static boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}

	// @描述：是否是2007的excel，返回true是2007
	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}
	
	 //获取单元格的值  
    private String getCellValue(Cell cell) {  
        String cellValue = "";  
        DataFormatter formatter = new DataFormatter();  
        if (cell != null) {  
            //判断单元格数据的类型，不同类型调用不同的方法  
            switch (cell.getCellType()) {  
                //数值类型  
                case Cell.CELL_TYPE_NUMERIC:  
                    //进一步判断 ，单元格格式是日期格式   
                    if (DateUtil.isCellDateFormatted(cell)) {  
                        cellValue = formatter.formatCellValue(cell);  
                    } else {  
                        //数值  
                        double value = cell.getNumericCellValue();  
                        int intValue = (int) value;  
                        cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);  
                    }  
                    break;  
                case Cell.CELL_TYPE_STRING:  
                    cellValue = cell.getStringCellValue();  
                    break;  
                case Cell.CELL_TYPE_BOOLEAN:  
                    cellValue = String.valueOf(cell.getBooleanCellValue());  
                    break;  
                    //判断单元格是公式格式，需要做一种特殊处理来得到相应的值  
                case Cell.CELL_TYPE_FORMULA:{  
                    try{  
                        cellValue = String.valueOf(cell.getNumericCellValue());  
                    }catch(IllegalStateException e){  
                        cellValue = String.valueOf(cell.getRichStringCellValue());  
                    }  
                      
                }  
                    break;  
                case Cell.CELL_TYPE_BLANK:  
                    cellValue = "";  
                    break;  
                case Cell.CELL_TYPE_ERROR:  
                    cellValue = "";  
                    break;  
                default:  
                    cellValue = cell.toString().trim();  
                    break;  
            }  
        }  
        return cellValue.trim();  
    }  
}
