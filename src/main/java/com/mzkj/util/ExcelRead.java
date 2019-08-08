package com.mzkj.util;

import com.fh.util.PageData;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明：ExcelRead
 * 创建人：DZW
 * 创建时间：2019/8/6
 */
public class ExcelRead {

    /**用于读取Excel
     * @param filepath //文件路径
     * @param filename //文件名
     * @param startrow //开始行号
     * @param startcol //开始列号
     * @param sheetnum //sheet
     * @return list
     */
    @SuppressWarnings("unlikely-arg-type")
    public static List<Object> readExcel(String filepath, String filename, int startrow, int startcol, int sheetnum) {
        List<Object> varList = new ArrayList<Object>();

        try {
            File target = new File(filepath, filename);
            FileInputStream fi = new FileInputStream(target);
            HSSFWorkbook wb = new HSSFWorkbook(fi);
            HSSFSheet sheet = wb.getSheetAt(sheetnum); 					//sheet 从0开始
            int rowNum = sheet.getLastRowNum() + 1; 					//取得最后一行的行号

            for (int i = startrow; i < rowNum; i++) {					//行循环开始

                PageData varpd = new PageData();
                HSSFRow row = sheet.getRow(i); 							//行
                int cellNum = row.getLastCellNum(); 					//每行的最后一个单元格位置

                for (int j = startcol; j < cellNum; j++) {				//列循环开始

                    HSSFCell cell = row.getCell(Short.parseShort(j + ""));
                    String cellValue = null;
                    if (null != cell) {
                        switch (cell.getCellType()) { 					// 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
                            case 0:
                                cell.setCellType(1);
                                //cellValue = String.valueOf((int) cell.getNumericCellValue());
                                cellValue =cell.getStringCellValue();
                                break;
                            case 1:
                                cellValue = cell.getStringCellValue();
                                break;
                            case 2:
                                cellValue = cell.getNumericCellValue() + "";
                                // cellValue = String.valueOf(cell.getDateCellValue());
                                break;
                            case 3:
                                cellValue = "";
                                break;
                            case 4:
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case 5:
                                cellValue = String.valueOf(cell.getErrorCellValue());
                                break;
                        }
                    } else {
                        cellValue = "";
                    }

                    varpd.put("var"+j, cellValue);

                }
                varList.add(varpd);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return varList;
    }

    /**上传文件
     * @param file 			//文件对象
     * @param filePath		//上传路径
     * @param fileName		//文件名
     * @return  文件名
     */
    public static String fileUp(MultipartFile file, String filePath, String fileName){
        String extName = ""; // 扩展名格式：
        try {
            if (file.getOriginalFilename().lastIndexOf(".") >= 0){
                extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            }
            copyFile(file.getInputStream(), filePath, fileName+extName).replaceAll("-", "");
        } catch (IOException e) {
            System.out.println(e);
        }
        return fileName+extName;
    }

    /**
     * 写文件到当前目录的upload目录中
     * @param in
     * @param realName
     * @throws IOException
     */
    public static String copyFile(InputStream in, String dir, String realName)
            throws IOException {
        File file = mkdirsmy(dir,realName);
        FileUtils.copyInputStreamToFile(in, file);
        in.close();
        return realName;
    }

    /**判断路径是否存在，否：创建此路径
     * @param dir  文件路径
     * @param realName  文件名
     * @throws IOException
     */
    public static File mkdirsmy(String dir, String realName) throws IOException{
        File file = new File(dir, realName);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        return file;
    }

    /**获取classpath1
     * @return
     */
    public static String getClasspath(){
        String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();
        if(path.indexOf(":") != 1){
            path = File.separator + path;
        }
        return path;
    }
}
