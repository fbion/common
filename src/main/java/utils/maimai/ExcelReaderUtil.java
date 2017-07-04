package utils.maimai;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by zzh on 2016/11/9.
 */
public class ExcelReaderUtil {
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        List<List<Object>> list = readExcel(new FileInputStream(new File("f:\\123.xlsx")),"test.xlsx");
        System.out.println(list);
        for (List<Object> objectList : list) {
            for (Object o : objectList) {
                System.out.print(o.toString());
            }
            System.out.println();
        }
    }

    /**
     * 读取excel2007.xlsx文件，其他文件是否兼容待测
     * 每行读取所有格内容保存在List里，所有行的List保存到另一个List返回
     * @param ins
     * @param sSheetName
     * @return
     */
    public static List<List<String>> readExcelFile(InputStream ins, String sSheetName) throws Exception {
        List<List<String>> list = new ArrayList<>();
        XSSFWorkbook wb = new XSSFWorkbook(ins);
        XSSFSheet sheet = wb.getSheet(sSheetName);
        System.out.println(sheet);
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            String cellNovalue = "";
            XSSFRow row = sheet.getRow(i);
            Iterator it = row.cellIterator();
            List<String> tempList = new ArrayList<>();
            while (it.hasNext()) {
                XSSFCell cell = (XSSFCell) it.next();
                try {
                    cellNovalue = cell.getStringCellValue();
                } catch (IllegalStateException e) {
                    try {
                        double dcellNovalue = sheet.getRow(i).getCell(0)
                                .getNumericCellValue();
                        cellNovalue = String.valueOf(dcellNovalue);
                    } catch (IllegalStateException e2) {
                        cellNovalue = "";
                        e.printStackTrace();
                    }
                } catch (Exception e3) {
                    cellNovalue = "";
                    e3.printStackTrace();
                }

//                    System.out.println("Row=" + i + "; Cell="
//                            + cell.getColumnIndex() + "; Value=" + cellNovalue);
                tempList.add(cellNovalue);
            }
            list.add(tempList);
        }
        return list;
    }

    /**
     * 读取Excel2003和Excel2007文件，通过文件名后缀判断类型,读取第一个表格
     * @param ins
     * @param fileName
     * @return
     * @throws IOException
     */
    public static List<List<Object>> readExcel(InputStream ins, String fileName) throws IOException {
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
                .substring(fileName.lastIndexOf(".") + 1);
        System.out.println(extension);
        if ("xls".equals(extension)) {
            return read2003Excel(ins);
        } else if ("xlsx".equals(extension)) {
            return read2007Excel(ins);
        } else {
            throw new IOException("不支持的文件类型");
        }
    }
    
    
    /**
     * 读取 office 2003 excel
     *
     * @throws IOException
     * @throws FileNotFoundException
     */
    private static List<List<Object>> read2003Excel(InputStream ins)
            throws IOException {
        List<List<Object>> list = new LinkedList<List<Object>>();
        HSSFWorkbook hwb = new HSSFWorkbook(ins);
        HSSFSheet sheet = hwb.getSheetAt(0);
        Object value = null;
        HSSFRow row = null;
        HSSFCell cell = null;
        System.out.println("读取office 2003 excel内容如下：");
        for (int i = sheet.getFirstRowNum(); i <= sheet
                .getLastRowNum(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<Object> linked = new LinkedList<Object>();
//            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
            for (int j = 0; j <= row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (cell == null) {
                    linked.add("");
                    continue;
                }
                DecimalFormat df = new DecimalFormat("0");// 格式化 number String
                // 字符
                SimpleDateFormat sdf = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
                DecimalFormat nf = new DecimalFormat("0");// 格式化数字
                switch (cell.getCellType()) {
                    case XSSFCell.CELL_TYPE_STRING:
                        // System.out.println(i + "行" + j + " 列 is String type");
                        value = cell.getStringCellValue();
                        System.out.print("  " + value + "  ");
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        // System.out.println(i + "行" + j
                        // + " 列 is Number type ; DateFormt:"
                        // + cell.getCellStyle().getDataFormatString());
                        if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                            value = df.format(cell.getNumericCellValue());

                        } else if ("General".equals(cell.getCellStyle()
                                .getDataFormatString())) {
                            value = nf.format(cell.getNumericCellValue());
                        } else {
                            value = sdf.format(HSSFDateUtil.getJavaDate(cell
                                    .getNumericCellValue()));
                        }
                        System.out.print("  " + value + "  ");
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        // System.out.println(i + "行" + j + " 列 is Boolean type");
                        value = cell.getBooleanCellValue();
                        System.out.print("  " + value + "  ");
                        break;
                    case XSSFCell.CELL_TYPE_BLANK:
                        // System.out.println(i + "行" + j + " 列 is Blank type");
                        value = "";
                        System.out.print("  " + value + "  ");
                        break;
                    default:
                        // System.out.println(i + "行" + j + " 列 is default type");
                        value = cell.toString();
                        System.out.print("  " + value + "  ");
                }
                if (value == null || "".equals(value)) {
                    continue;
                }
                linked.add(value);
            }
            System.out.println("");
            list.add(linked);
        }

        return list;
    }

    /**
     * 读取Office 2007 excel
     */

    private static List<List<Object>> read2007Excel(InputStream ins)
            throws IOException {

        List<List<Object>> list = new LinkedList<List<Object>>();
        // 构造 XSSFWorkbook 对象
        XSSFWorkbook xwb = new XSSFWorkbook(ins);

        // 读取第一章表格内容
        XSSFSheet sheet = xwb.getSheetAt(0);
        Object value = null;
        XSSFRow row = null;
        XSSFCell cell = null;
        System.out.println("读取office 2007 excel内容如下：");
//        System.out.println(sheet.getLastRowNum());
        for (int i = sheet.getFirstRowNum(); i <= sheet
                .getLastRowNum(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<Object> linked = new LinkedList<Object>();
//            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
            for (int j = 0; j <= row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (cell == null) {
                    linked.add("");
                    continue;
                }
                DecimalFormat df = new DecimalFormat("0");// 格式化 number String
                // 字符
                SimpleDateFormat sdf = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
                DecimalFormat nf = new DecimalFormat("0");// 格式化数字

                switch (cell.getCellType()) {
                    case XSSFCell.CELL_TYPE_STRING:
                        // System.out.println(i + "行" + j + " 列 is String type");
                        value = cell.getStringCellValue();
                        System.out.print("  " + value + "  ");
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        // System.out.println(i + "行" + j
                        // + " 列 is Number type ; DateFormt:"
                        // + cell.getCellStyle().getDataFormatString());
                        if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                            value = df.format(cell.getNumericCellValue());

                        } else if ("General".equals(cell.getCellStyle()
                                .getDataFormatString())) {
                            value = nf.format(cell.getNumericCellValue());
                        } else {
                            value = sdf.format(HSSFDateUtil.getJavaDate(cell
                                    .getNumericCellValue()));
                        }
                        System.out.print("  " + value + "  ");
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        // System.out.println(i + "行" + j + " 列 is Boolean type");
                        value = cell.getBooleanCellValue();
                        System.out.print("  " + value + "  ");
                        break;
                    case XSSFCell.CELL_TYPE_BLANK:
                        // System.out.println(i + "行" + j + " 列 is Blank type");
                        value = "";
                        // System.out.println(value);
                        break;
                    default:
                        // System.out.println(i + "行" + j + " 列 is default type");
                        value = cell.toString();
                        System.out.print("  " + value + "  ");
                }
                if (value == null || "".equals(value)) {
                    continue;
                }
                linked.add(value);
            }
            System.out.println("");
            list.add(linked);
        }
        return list;
    }
    
    public static List<List<Object>> read2007ExcelForSign(InputStream ins, String fileName) throws IOException {
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
                .substring(fileName.lastIndexOf(".") + 1);
        System.out.println(extension);
        if ("xls".equals(extension)) {
            return read2003Excel(ins);
        } else if ("xlsx".equals(extension)) {
            return read2007Excel(ins);
        } else {
            throw new IOException("不支持的文件类型");
        }
    }
    
    /**
     * 读取Excel2003和Excel2007文件，通过文件名后缀判断类型,读取第一个表格
     * @param ins
     * @param fileName
     * @return  
     * @throws IOException
     */
    public static Map<String, Object> readExcelForSign(InputStream ins, String fileName) throws Exception {
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
                .substring(fileName.lastIndexOf(".") + 1);
        System.out.println(extension);
        if ("xls".equals(extension)) {
            return read2003ExcelForSign(ins);
        } else if ("xlsx".equals(extension)) {
            return read2007ExcelForSign(ins);
        } else {
            throw new IOException("不支持的文件类型");
        }
    }
    
    @SuppressWarnings("unused")
	private static Map<String, Object> read2007ExcelForSign(InputStream ins)
            throws  Exception {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<String> listErr = new ArrayList<String>();
        resultMap.put("isError", "N");
        
        // 构造 XSSFWorkbook 对象
        XSSFWorkbook xwb = new XSSFWorkbook(ins);

        // 读取第一章表格内容
        XSSFSheet sheet = xwb.getSheetAt(0);
        Object value = null;
        XSSFRow row = null;
        XSSFCell cell = null;
        System.out.println("读取office 2007 excel内容如下：");
//        System.out.println(sheet.getLastRowNum());
        //从第四行开始读,第一二行为注意事项，第三行为标题
        String head[]={ "contract_code", //合同编号
//		        		"ec_contract_id",//关联合同编号
		        		"legal_person",//百盛签约法人
		        		"organization",//组织类别
		        		"contract_type",//合同类型
		        		"contract_lable",//合同分类标签
		        		"supplier_name",//供应商名称
		        		"supplier_jde_code",////供应商JDE Code
		        		"overdue_reminder",//合同相关联系人
		        		"supplier_contact",//供应商联系人
		        		"contract_name",//合同名称
		        		"contract_attachment",//合同正文附件
		        		"attachment",//合同附件
		        		"deadline"//合同截止日期(yyyy-mm-dd)
//		        		,"mark",//合同描述
        								};
        
        if (sheet.getLastRowNum() == 1)
		{
        	resultMap.put("isError", "Y");
        	String errMsg = "";
            errMsg +="合同创建信息表为空\n";
            listErr.add(errMsg);
            resultMap.put("errMsg", listErr);
            return resultMap;
		}
        
        if((sheet.getLastRowNum()-2) >=100){
        	resultMap.put("isError", "Y");
        	String errMsg = "";
            errMsg +="导入数据不能超过100条\n";
            listErr.add(errMsg);
            resultMap.put("errMsg", listErr);
            return resultMap;
        }
        for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            
            Map<String,Object> excelMap = new HashMap<String, Object>();
            
            //循环列
            for (int j =0; j < 13; j++) {
            	String errMsg = "";
                cell = row.getCell(j);
                DecimalFormat df = new DecimalFormat("0");// 格式化 number String
                // 字符
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
                DecimalFormat nf = new DecimalFormat("0");// 格式化数字
                if (cell == null) {
                	value = "";
                }else{
                	switch (cell.getCellType()) {
                	 case XSSFCell.CELL_TYPE_STRING:
                         value = cell.getStringCellValue().trim();
                         System.out.print("  " + value + "  ");
                         break;
                     case XSSFCell.CELL_TYPE_NUMERIC:
                    	 value = (int)cell.getNumericCellValue()+"";
                    	 System.out.print("  " + value + "  ");
                         break;
                         
                     case XSSFCell.CELL_TYPE_BLANK:
                         value = "";
                         break;
                	}
                	
                	
                }
                //非空检验
                if (value == null || StringUtil.isEmptyString(value.toString())) {
                	value = "";
                	if(j==0){
	            		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列 合同编号不能为空\n";
	                    listErr.add(errMsg);
                	}
                	
                	if(j==1){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列百盛签约法人不能为空\n";
	                    listErr.add(errMsg);
                	}
                	if(j==2){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" +(j+1) + " 列组织类别不能为空\n";
	                    listErr.add(errMsg);
                	}
                	if(j==3){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同类型不能为空\n";
	                    listErr.add(errMsg);
                	}
                	if(j==4){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同分类标签不能为空\n";
	                    listErr.add(errMsg);
                	}
                	if(j==5){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列签约供应商名称不能为空\n";
	                    listErr.add(errMsg);
                	}
                	/*if(j==6){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列供应商JDE Code不能为空\n";
	                    listErr.add(errMsg);
                	}*/
                	
                	if(j==8){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 供应商签约通知邮箱不能为空\n";
	                    listErr.add(errMsg);
                	}
                 
                	if(j==9){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同名称不能为空\n";
	                    listErr.add(errMsg);
                	}
                	if(j==10){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同正文附件不能为空\n";
	                    listErr.add(errMsg);
                	}
                }else{
                	//检验长度
                	if(j==0&&value.toString().length()>100){
	            		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列 合同编号不能大于100字符\n";
	                    listErr.add(errMsg);
                	}
                	
                	if(j==1&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列百盛签约法人不能大于100字符\n";
	                    listErr.add(errMsg);
                	}
                	if(j==2&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" +(j+1) + " 列组织类别不能大于100字符\n";
	                    listErr.add(errMsg);
                	}
                	if(j==3&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同类型不能大于100字符\n";
	                    listErr.add(errMsg);
                	}
                	if(j==4&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同分类标签不能大于100字符\n";
	                    listErr.add(errMsg);
                	}
                	if(j==5&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列签约供应商名称不能大于100字符\n";
	                    listErr.add(errMsg);
                	}
                	if(j==6&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列供应商JDE Code不能大于100字符\n";
	                    listErr.add(errMsg);
                	}
                	if(j==7&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同相关联系人不能大于100字符\n";
	                    listErr.add(errMsg);
                	}else if(j==7) {
                		String emailStr = value.toString();
						String[] emailList = emailStr.split(",");
						for (String emailStrTemp : emailList){
						 boolean b =	StringUtil.checkEmail(emailStrTemp);
						 	if(b == false){
						 		resultMap.put("isError", "Y");
			                    errMsg +=(i-1) + "行" + (j+1) + " 列合同相关联系人邮件格式不正确\n";
			                    listErr.add(errMsg);
			                    break;
						 	}
						}
					}
                	
                	
                	if(j==8&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列供应商签约通知邮箱不能大于100字符\n";
	                    listErr.add(errMsg);
                	}else if(j==8){
                		String emailStr = value.toString();
						String[] emailList = emailStr.split(",");
						for (String emailStrTemp : emailList){
						 boolean b =	StringUtil.checkEmail(emailStrTemp);
						 	if(b == false){
						 		resultMap.put("isError", "Y");
			                    errMsg +=(i-1) + "行" + (j+1) + " 列供应商签约通知邮箱邮件格式不正确\n";
			                    listErr.add(errMsg);
			                    break;
						 	}
						}
                	}
                 
                	if(j==9&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同名称不能大于100字符\n";
	                    listErr.add(errMsg);
                	}
                	
                	if(j==10&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列列合同正文附件不能大于100字符\n";
	                    listErr.add(errMsg);
                	}else if(j==10){
                		String fileStr = value.toString();
                		if(fileStr.indexOf(".")==-1){
                			resultMap.put("isError", "Y");
    	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同正文有且只有一个，格式必须doc,docx,或者pdf格式\n";
    	                    listErr.add(errMsg);
                		}else{
                			String lexi =  fileStr.substring(fileStr.indexOf("."), fileStr.length());
                    		if(!".doc".equals(lexi)&&!".docx".equals(lexi)&&!".pdf".equals(lexi)){
                    			resultMap.put("isError", "Y");
        	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同正文有且只有一个，格式必须doc,docx,或者pdf格式\n";
        	                    listErr.add(errMsg);
                    		}
                		}
                	}
                	
                	if(j==11){
                		String fileStr = value.toString();
                		 String atthName = fileStr.replaceAll("，", ",");
                         String[] atthNameArr = atthName.split(",");
                         if(atthNameArr.length>5){
                        	resultMap.put("isError", "Y");
     	                    errMsg +=(i-1) + "行" + (j+1) + "列附件个数不能超过5个\n";
     	                    listErr.add(errMsg); 
                         }
                	}
                	
                	if(j==12&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列日期不能大于100字符\n";
	                    listErr.add(errMsg);
                	}else if(j==12){
                		String dateStr = value.toString();
                		if(!DateUtil.isValidDate( dateStr)){
                			resultMap.put("isError", "Y");
    	                    errMsg +=(i-1) + "行" + (j+1) + " 列日期格式错误\n";
    	                    listErr.add(errMsg);
                		}else{
                			Date now =new Date();
                			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                			Date date = format.parse(dateStr);
                			Date dateNow = format.parse(format.format(now));
                			
                			if(dateNow.getTime() > date.getTime()){
                				resultMap.put("isError", "Y");
        	                    errMsg +=(i-1) + "行" + (j+1) + "列合同截止日期不能小于当前日期\n";
        	                    listErr.add(errMsg);
                			}
                		}
                	}
                	
                	/*if(j==13&&value.toString().length()>200){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同描述不能大于200字符\n";
	                    listErr.add(errMsg);
                		 
                	}*/
                	
                }
                excelMap.put(head[j], value);
            }
            //拼 ec_contract_id 编号放入map 入库检验 唯一性
            list.add(excelMap);
           
        }
        resultMap.put("excelResut", list);
        resultMap.put("errMsg", listErr);
        return resultMap;
    }
    
  
    
    /**
     * 读取 office 2003 excel
     *
     * @throws IOException
     * @throws FileNotFoundException
     * 
     *  HSSFWorkbook hwb = new HSSFWorkbook(ins);
        HSSFSheet sheet = hwb.getSheetAt(0);
        Object value = null;
        HSSFRow row = null;
        HSSFCell cell = null;
        System.out.println("读取office 2003 excel内容如下：");
     */
    private static Map<String,Object> read2003ExcelForSign(InputStream ins)
            throws Exception {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<String> listErr = new ArrayList<String>();
        resultMap.put("isError", "N");
        
        HSSFWorkbook hwb = new HSSFWorkbook(ins);
        HSSFSheet sheet = hwb.getSheetAt(0);
        Object value = null;
        HSSFRow row = null;
        HSSFCell cell = null;
        System.out.println("读取office 2003 excel内容如下：");
//        System.out.println(sheet.getLastRowNum());
        //从第四行开始读,第一二行为注意事项，第三行为标题
        String head[]={ "contract_code", //合同编号
//		        		"ec_contract_id",//关联合同编号
		        		"legal_person",//百盛签约法人
		        		"organization",//组织类别
		        		"contract_type",//合同类型
		        		"contract_lable",//合同分类标签
		        		"supplier_name",//供应商名称
		        		"supplier_jde_code",////供应商JDE Code
		        		"overdue_reminder",//合同相关联系人
		        		"supplier_contact",//供应商联系人
		        		"contract_name",//合同名称
		        		"contract_attachment",//合同正文附件
		        		"attachment",//合同附件
		        		"deadline",//合同截止日期(yyyy-mm-dd)
//		        		"mark",//合同描述
        								};
        
        if (sheet.getLastRowNum() == 1)
		{
        	resultMap.put("isError", "Y");
        	String errMsg = "";
            errMsg +="合同创建信息表为空\n";
            listErr.add(errMsg);
            resultMap.put("errMsg", listErr);
            return resultMap;
		}
        
        if((sheet.getLastRowNum()-2) >=100){
        	resultMap.put("isError", "Y");
        	String errMsg = "";
            errMsg +="导入数据不能超过100条\n";
            listErr.add(errMsg);
            resultMap.put("errMsg", listErr);
            return resultMap;
        }
        for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            
            Map<String,Object> excelMap = new HashMap<String, Object>();
            
            //循环列
            for (int j =0; j < 14; j++) {
            	String errMsg = "";
                cell = row.getCell(j);
                DecimalFormat df = new DecimalFormat("0");// 格式化 number String
                // 字符
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
                DecimalFormat nf = new DecimalFormat("0");// 格式化数字
                if (cell == null) {
                	value = "";
                }else{
                	switch (cell.getCellType()) {
                	 case XSSFCell.CELL_TYPE_STRING:
                         value = cell.getStringCellValue().trim();
                         System.out.print("  " + value + "  ");
                         break;
                     case XSSFCell.CELL_TYPE_NUMERIC:
                    	 value = (int)cell.getNumericCellValue()+"";
                    	 System.out.print("  " + value + "  ");
                         break;
                         
                     case XSSFCell.CELL_TYPE_BLANK:
                         value = "";
                         break;
                	}
                	
                	
                }
                //非空检验
                if (value == null || StringUtil.isEmptyString(value.toString())) {
                	value = "";
                	if(j==0){
	            		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列 合同编号不能为空\n";
	                    listErr.add(errMsg);
                	}
                	
                	if(j==1){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列百盛签约法人不能为空\n";
	                    listErr.add(errMsg);
                	}
                	if(j==2){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" +(j+1) + " 列组织类别不能为空\n";
	                    listErr.add(errMsg);
                	}
                	if(j==3){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同类型不能为空\n";
	                    listErr.add(errMsg);
                	}
                	if(j==4){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同分类标签不能为空\n";
	                    listErr.add(errMsg);
                	}
                	if(j==5){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列签约供应商名称不能为空\n";
	                    listErr.add(errMsg);
                	}
                	/*if(j==6){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列供应商JDE Code不能为空\n";
	                    listErr.add(errMsg);
                	}*/
                	
                	if(j==8){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 供应商签约通知邮箱不能为空\n";
	                    listErr.add(errMsg);
                	}
                 
                	if(j==9){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同名称不能为空\n";
	                    listErr.add(errMsg);
                	}
                	if(j==10){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同正文附件不能为空\n";
	                    listErr.add(errMsg);
                	}
                }else{
                	//检验长度
                	if(j==0&&value.toString().length()>100){
	            		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列 合同编号不能大于100字符\n";
	                    listErr.add(errMsg);
                	}
                	
                	if(j==1&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列百盛签约法人不能大于100字符\n";
	                    listErr.add(errMsg);
                	}
                	if(j==2&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" +(j+1) + " 列组织类别不能大于100字符\n";
	                    listErr.add(errMsg);
                	}
                	if(j==3&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同类型不能大于100字符\n";
	                    listErr.add(errMsg);
                	}
                	if(j==4&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同分类标签不能大于100字符\n";
	                    listErr.add(errMsg);
                	}
                	if(j==5&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列签约供应商名称不能大于100字符\n";
	                    listErr.add(errMsg);
                	}
                	if(j==6&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列供应商JDE Code不能大于100字符\n";
	                    listErr.add(errMsg);
                	}
                	if(j==7&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同相关联系人不能大于100字符\n";
	                    listErr.add(errMsg);
                	}else if(j==7) {
                		String emailStr = value.toString();
						String[] emailList = emailStr.split(",");
						for (String emailStrTemp : emailList){
						 boolean b =	StringUtil.checkEmail(emailStrTemp);
						 	if(b == false){
						 		resultMap.put("isError", "Y");
			                    errMsg +=(i-1) + "行" + (j+1) + " 列合同相关联系人邮件格式不正确\n";
			                    listErr.add(errMsg);
			                    break;
						 	}
						}
					}
                	
                	
                	if(j==8&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列供应商签约通知邮箱不能大于100字符\n";
	                    listErr.add(errMsg);
                	}else if(j==8){
                		String emailStr = value.toString();
						String[] emailList = emailStr.split(",");
						for (String emailStrTemp : emailList){
						 boolean b =	StringUtil.checkEmail(emailStrTemp);
						 	if(b == false){
						 		resultMap.put("isError", "Y");
			                    errMsg +=(i-1) + "行" + (j+1) + " 列供应商签约通知邮箱邮件格式不正确\n";
			                    listErr.add(errMsg);
			                    break;
						 	}
						}
                	}
                 
                	if(j==9&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同名称不能大于100字符\n";
	                    listErr.add(errMsg);
                	}
                	
                	if(j==10&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列列合同正文附件不能大于100字符\n";
	                    listErr.add(errMsg);
                	}else if(j==10){
                		String fileStr = value.toString();
                		String lexi =  fileStr.substring(fileStr.indexOf("."), fileStr.length());
                		if(!".doc".equals(lexi)&&!".docx".equals(lexi)&&!".pdf".equals(lexi)){
                			resultMap.put("isError", "Y");
    	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同正文有且只有一个，格式必须doc,docx,或者pdf格式\n";
    	                    listErr.add(errMsg);
                		}
                	}
                	
                	if(j==11){
                		String fileStr = value.toString();
                		 String atthName = fileStr.replaceAll("，", ",");
                         String[] atthNameArr = atthName.split(",");
                         if(atthNameArr.length>5){
                        	resultMap.put("isError", "Y");
     	                    errMsg +=(i-1) + "行" + (j+1) + "列附件个数不能超过5个\n";
     	                    listErr.add(errMsg); 
                         }
                	}
                	
                	if(j==12&&value.toString().length()>100){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列日期不能大于100字符\n";
	                    listErr.add(errMsg);
                	}else if(j==12){
                		String dateStr = value.toString();
                		if(!DateUtil.isValidDate( dateStr)){
                			resultMap.put("isError", "Y");
    	                    errMsg +=(i-1) + "行" + (j+1) + " 列日期格式错误\n";
    	                    listErr.add(errMsg);
                		}else{
                			Date now =new Date();
                			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                			Date date = format.parse(dateStr);
                			Date dateNow = format.parse(format.format(now));
                			
                			if(dateNow.getTime() > date.getTime()){
                				resultMap.put("isError", "Y");
        	                    errMsg +=(i-1) + "行" + (j+1) + "列合同截止日期不能小于当前日期\n";
        	                    listErr.add(errMsg);
                			}
                		}
                	}
                	
                	/*if(j==13&&value.toString().length()>200){
                		resultMap.put("isError", "Y");
	                    errMsg +=(i-1) + "行" + (j+1) + " 列合同描述不能大于200字符\n";
	                    listErr.add(errMsg);
                		 
                	}*/
                	
                }
                excelMap.put(head[j], value);
            }
            //拼 ec_contract_id 编号放入map 入库检验 唯一性
            list.add(excelMap);
           
        }
        resultMap.put("excelResut", list);
        resultMap.put("errMsg", listErr);
        return resultMap;
    }
    
    //解析cvs
    public static Map<String, Object> readCvs(InputStream ins, String fileName) throws IOException {
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
                .substring(fileName.lastIndexOf(".") + 1);
        System.out.println(extension);
        if ("xls".equals(extension)) {
            return readCsv(ins);
        } /*else if ("xlsx".equals(extension)) {
            return read2007ExcelForSign(ins);
        } */else {
            throw new IOException("不支持的文件类型");
        }
    }

	private static Map<String, Object> readCsv(InputStream ins)
	{
		 
		return null;
	}
}
