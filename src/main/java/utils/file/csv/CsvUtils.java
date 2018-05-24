package utils.file.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description: <br>
 * createTime: 2018/5/248:29 <br>
 *
 * @author zzh
 */
public class CsvUtils {
    public static final String DEFAULT_SEPARATE = ",";

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvUtils.class);

    public static  List<List<String>> readCsv(String filePath) throws IOException {
        return readCsv(filePath, DEFAULT_SEPARATE);
    }
    
    public static List<List<String>> readCsv(String filePath, String separate) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            List<List<String>> result = new ArrayList<>();
            String line = null;
            String[] array = null;
            while ((line = br.readLine()) != null) {
                array = line.split(separate);
                List<String> temp = new ArrayList<>(array.length);
                temp.addAll(Arrays.asList(array));
                result.add(temp);
            }
            return result;
        } catch (FileNotFoundException e) {
            LOGGER.error("文件没找到。", e);
            throw e;
        } catch (IOException e) {
            LOGGER.error("文件读取异常。", e);
            throw e;
        }
    }
}
