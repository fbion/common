package utils.maimai;


import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class PictureAndBase64 {  
	private static Logger log = LoggerFactory.getLogger(PictureAndBase64.class);
	public static void main(String[] args) {  
		// 测试从图片文件转换为Base64编码  
//		String strImg1 = GetImageStr("f:/test.jpg");
//		String strImg2 = GetImageStr("f:/test1.jpg");
//		log.info(strImg1);
//		strImg1+=strImg2;
		// 测试从Base64编码转换为图片文件 

//		String result = GetImageStr("f:\\icon-del.png");
//		GenerateImage(result, "f:\\test11.png");

//		String result = GetImageStr("E:\\IDEA\\Yum\\WebContent\\images\\demo2.gif");
//		System.out.println(result);

        String str = "[\"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\"".replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"", "");
        String path = "E:\\IDEA\\Yum\\out\\artifacts\\Yum\\sharefile/template/zhouzh1483445217735.png";
        System.out.println("str : " + str);
        System.out.println("path : " + path);
        GenerateImage(str, path);
	}
	
	
	
	
	public static String GetImageStr(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		if(imgFilePath == null || "".equals(imgFilePath)){
			return "";
		}
		File file = new File(imgFilePath);
		if(!file.exists()){
			return "";
		}
		byte[] data = null;
		// 读取图片字节数组
		try {
			InputStream in = new FileInputStream(imgFilePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}
	
	// 对字节数组字符串进行Base64解码并生成图片
	public static boolean GenerateImage(String imgStr, String imgFilePath) {
		if (imgStr == null || "".equals(imgStr)) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	  /**
     * 将svg字符串转换为png
     * 
     * @param svgCode svg代码
     * @param pngFilePath 保存的路径
     * @throws TranscoderException svg代码异常
     * @throws IOException io错误
     */
    public static void convertToPng(String svgCode, String pngFilePath) throws IOException,
            TranscoderException {

        File file = new File(pngFilePath);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            convertToPng(svgCode, outputStream);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将svgCode转换成png文件，直接输出到流中
     * 
     * @param svgCode svg代码
     * @param outputStream 输出流
     * @throws TranscoderException 异常
     * @throws IOException io异常
     */
    public static void convertToPng(String svgCode, OutputStream outputStream)
            throws TranscoderException, IOException {
        try {
            byte[] bytes = svgCode.getBytes("utf-8");
            PNGTranscoder t = new PNGTranscoder();
            TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(bytes));
            TranscoderOutput output = new TranscoderOutput(outputStream);
            t.transcode(input, output);
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
	
}
