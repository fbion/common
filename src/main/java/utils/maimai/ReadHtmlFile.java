package utils.maimai;

import java.io.File;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;
import com.yunsign.exception.ServiceException;

public class ReadHtmlFile
{
    private static Logger log = Logger.getLogger(ReadHtmlFile.class);
    public static String read(String jsonData , String src)
    {
        String content = "";
        
        log.info("jsonData======" + jsonData);
        if (!jsonData.isEmpty())
        {
            try
            {
                Gson gson = new Gson();
                Map<String, String> map = null;
                try
                {
                    map = gson.fromJson(jsonData, Map.class);
                }
                catch (Exception e)
                {
                    log.info("普通数据装载异常");
                    log.info(FileUtil.getStackTrace(e));
                    throw new ServiceException(ConstantUtil.TEMPLATE_DATA_ERROR[0], ConstantUtil.TEMPLATE_DATA_ERROR[1],
                        ConstantUtil.TEMPLATE_DATA_ERROR[2], FileUtil.getStackTrace(e));
                }
                File input = new File(src);
                Document doc = Jsoup.parse(input, "UTF-8", "");
                for (String key : map.keySet())
                {
                    if (null != key && !"".equals(StringUtil.nullToString(key)))
                    {
                        log.info("通用外围key:" + key);
                        Element ele = doc.getElementById(key);
                        if (null != ele)
                        {
                            // 创建表单元素
                            if (!"".equals(StringUtil.nullToString(map.get(key))))
                            {
                                ele.html(StringUtil.nullToString(map.get(key)));
                            }
                        }
                        else
                        {
                            throw new ServiceException(ConstantUtil.TEMPLATE_DATA_IS_NOT_EXIST[0], "模板中" + key + "不存在",
                                key + " does not exist.");
                        }
                    }
                    else
                    {
                        throw new ServiceException(ConstantUtil.TEMPLATE_DATA_IS_NOT_EXIST[0], "模板中" + key + "不存在",
                            key + " does not exist.");
                    }
                }
               content = doc.toString();
              
            }
            catch (ServiceException e)
            {
                log.info("普通数据装载异常");
                e.printStackTrace();
            }
            catch (Exception e)
            {
                log.info("普通数据装载异常");
 
                e.printStackTrace();
            }
        }
        return content;
    }
    
}
