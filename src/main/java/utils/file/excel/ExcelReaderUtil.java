package utils.file.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.DocumentFactoryHelper;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * description: <br>
 * createTime: 2018/5/299:54 <br>
 *
 * @author zzh
 */
public class ExcelReaderUtil {
    public static final SheetSelector SHEET_SELECTOR_DEFAULT = new SheetSelector() {
        @Override
        public Sheet select(Workbook workbook) {
            return workbook.getSheetAt(0);
        }
    };


    /**
     * 读取Excel2003和Excel2007文件，通过文件名后缀判断类型,读取第一个表格
     *
     * @param selector    表格选取器
     * @param sheetReader 表格解析对象
     * @param filePath    文件路径
     * @return
     * @throws IOException
     */
    public static <I> I readExcel(SheetSelector selector, String filePath, SheetReader<I> sheetReader) throws IOException, InvalidFormatException {
        try (FileInputStream ins = new FileInputStream(filePath)) {
            return readExcel(selector, getWorkBook(ins), sheetReader);
        }
    }


    /**
     * 读取Excel2003和Excel2007文件，通过文件名后缀判断类型,读取第一个表格
     *
     * @param selector  表格选取器
     * @param rowReader 表格行解析对象
     * @param filePath  文件路径
     * @return
     * @throws IOException
     */
    public static <I> List<I> readExcel(SheetSelector selector, String filePath, RowReader<I> rowReader) throws IOException, InvalidFormatException {
        try (FileInputStream ins = new FileInputStream(filePath)) {
            return readExcel(selector, getWorkBook(ins), rowReader);
        }
    }

    public static Workbook getWorkBook(InputStream inputSteam) throws IOException,InvalidFormatException {
        if (!inputSteam.markSupported()) {
            inputSteam = new PushbackInputStream(inputSteam, 8);
        }

        if (POIFSFileSystem.hasPOIFSHeader(inputSteam)) {
            return new HSSFWorkbook(inputSteam);
        }
        if ( DocumentFactoryHelper.hasOOXMLHeader(inputSteam)) {//FileMagic.valueOf(inputSteam) == FileMagic.OOXML
            return new XSSFWorkbook(OPCPackage.open(inputSteam));
        }
        throw new IllegalArgumentException("你的excel版本目前poi解析不了");
    }


    private static Workbook getWorkBook(InputStream ins, String extension) throws IOException {
        if ("xls".equals(extension)) {
            return new HSSFWorkbook(ins);
        } else if ("xlsx".equals(extension)) {
            return new XSSFWorkbook(ins);
        }
        return null;
    }


    private static String getFileExtension(String filePath) throws IOException {
        String extension = filePath.replaceAll(".*\\.([^.]+)", "$1");
        if (!"xls".equals(extension) && !"xlsx".equals(extension)) {
            throw new IOException("不支持的文件类型");
        }
        return extension;
    }


    /**
     * 读取Office 2007 excel
     */
    private static <I> I readExcel(SheetSelector selector, Workbook workBook, SheetReader<I> reader)
            throws IOException {

        List<List<Object>> list = new LinkedList<List<Object>>();

        // 读取第一章表格内容
        Sheet sheet = selector.select(workBook);
        return reader.read(sheet);
    }


    /**
     * 读取Office 2007 excel
     */
    private static <I> List<I> readExcel(SheetSelector selector, Workbook workbook, RowReader<I> reader) {

        List<I> result = new ArrayList<>();
        Sheet sheet = selector.select(workbook);
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            result.add(reader.read(rowIterator.next()));
        }
        return result;
    }


    public interface SheetReader<I> {
        I read(Sheet sheet);
    }

    public interface RowReader<I> {
        I read(Row row);
    }

    public interface SheetSelector {
        Sheet select(Workbook workbook);
    }

    public static class SheetNameSelector implements SheetSelector {
        private String sheetName;


        public SheetNameSelector(String sheetName) {
            this.sheetName = sheetName;
        }


        @Override
        public Sheet select(Workbook workbook) {
            return workbook.getSheet(sheetName);
        }
    }

    ;

    public static class SheetIndexSelector implements SheetSelector {
        private int index;


        public SheetIndexSelector(int index) {
            this.index = index;
        }


        @Override
        public Sheet select(Workbook workbook) {
            return workbook.getSheetAt(index);
        }
    }


    public static void main(String[] args) throws IOException, InvalidFormatException {
        String[] array = {"e:\\2.xlsx", "e:\\3.xls",};
        for (String s : array) {
            List<String> list = ExcelReaderUtil.readExcel(ExcelReaderUtil.SHEET_SELECTOR_DEFAULT,
                    s,
                    new ExcelReaderUtil.RowReader() {
                        @Override
                        public String read(Row row) {
                            Cell cell = row.getCell(0);
                            return cell.toString().replaceAll("'", "");
                        }
                    });
            System.out.println(list);
        }
    }
}
