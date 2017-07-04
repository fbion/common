package utils.maimai;

import com.itextpdf.awt.geom.Rectangle2D.Float;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PdfUtil {

    private static int i = 0;
    static List<float[]>  arrays = new ArrayList<float[]> ();
    private static Logger log = LoggerFactory.getLogger(PdfUtil.class);
    /**
     * 数组静态变量清0
     */
    public static void clearArrays(){
    	arrays.clear();
    }
    
    /*
     * 自下而上，从右到左
     * @param filePath pdf文件
     * @param keyWord 在模板里做的关键字
     * 返回值为一个数组 分别为x轴,y轴,坐标在文档中的页数(x,y和创建签名域的一样)  57.832397----283.78784-----3.0  
     */
    public static List<float[]> getKeyWords(String filePath, final String keyWord)
    {
        try {
            PdfReader pdfReader = new PdfReader(filePath);
            int pageNum = pdfReader.getNumberOfPages();
            PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(
                    pdfReader);
            for (i = 1; i < pageNum+1; i++) {
                pdfReaderContentParser.processContent(i, new RenderListener() {
                	 @Override 
                    public void renderText(TextRenderInfo textRenderInfo) {
                        String text = textRenderInfo.getText(); // 整页内容
//                        System.out.println(text);
//                        PdfString s = textRenderInfo.getPdfString();
                        if (null != text && text.contains(keyWord)) {
                            Float boundingRectange = textRenderInfo.getBaseline().getBoundingRectange();
//                            sb = boundingRectange.x+"--"+boundingRectange.y+"---";
//                            a =keyWord;
                            float[] resu = new float[4];
                            resu[0] = boundingRectange.x;
                            resu[1] = boundingRectange.y;
                            resu[2] = i;
                            arrays.add(resu);
                        }
                    }

                    @Override
                    public void renderImage(ImageRenderInfo arg0) {
                       

                    }

                    @Override
                    public void endTextBlock() {
                        
                    }

                    @Override
                    public void beginTextBlock() {
                      
                    }					
                });
            }
           // arrays.add(a);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        log.info(keyWord);
        return arrays;
    }
    /**
     * 在pdf追加一页，在最后一页写上相应的文字
     * @param srcFile
     * @param destFile
     * @param text 写入pdf文字
     * @param textWidth 文字横坐标 
     * @param textHeight 文字纵坐标 
     * @param fontSize 字体大小
     * @throws IOException
     * @throws DocumentException
     */
    public static void addPageAndContent(String srcFile, String destFile, String text,int x,int y,int fontSize)
    		throws IOException, DocumentException
    {
    	// 待加水印的文件  
        PdfReader reader = new PdfReader(srcFile);  
        // 加完水印的文件  
        int total = reader.getNumberOfPages() + 1; 
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(destFile)); 
     // 增加新的一页2
        stamper.insertPage(total+1, PageSize.A4);
//        String fontPath = "E:/office/simsun.ttf";  
        // 设置字体  
        BaseFont font = BaseFont.createFont(PropertiesUtil.getProperties().readValue("pdf.font.path"), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  
        PdfContentByte content = stamper.getOverContent(total);
  
        if (!"".equals(text)) 
        {  
            // 开始  
            content.beginText();  
            // 设置颜色 默认为蓝色  
            content.setColorFill(BaseColor.BLACK);  
            // content.setColorFill(Color.GRAY);  
            // 设置字体及字号  
            content.setFontAndSize(font, fontSize); 
            // 开始写入
            content.showTextAligned(Element.ALIGN_LEFT, text, x, y, 0); 
            content.endText();  
        }
        stamper.close();
    }
    
    /**  
     * @see 【类、类#方法、类#成员】 
     * @param srcFile 
     *            待加水印文件 
     * @param destFile 
     *            加水印后存放地址 
     * @param text 
     *            加水印的文本内容 
     * @param imgFile 
     *            加水印图片文件 
     * @param textWidth 
     *            文字横坐标 
     * @param textHeight 
     *            文字纵坐标 
     * @param imgWidth 
     *            图片横坐标 
     * @param imgHeight 
     *            图片纵坐标 
     * @throws IOException 
     * @throws DocumentException 
     */  
    public static void test(String srcFile, String destFile, String text, String imgFile, int textWidth,  
            int textHeight, int imgWidth, int imgHeight) throws IOException, DocumentException {  
        // 待加水印的文件  
        PdfReader reader = new PdfReader(srcFile);  
        // 加完水印的文件  
        int total = reader.getNumberOfPages() + 1; 
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(destFile)); 
     // 增加新的一页2
        stamper.insertPage(total+1, PageSize.A4);
//        // 写上内容
//        over = stamp.getOverContent(2);
//        over.beginText();
//        over.setFontAndSize(bf, 18);
//        over.showTextAligned(Element.ALIGN_LEFT, "DUPLICATE OF AN EXISTING PDF DOCUMENT", 30, 600, 0);
//        over.endText();
        
      
        PdfContentByte content = null;  
        String fontPath = "E:/office/simsun.ttf";  
        // 设置字体  
        // BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",  
        // BaseFont.EMBEDDED);  
        //加载字库来完成对字体的创建   "MSYH.TTF"
        BaseFont font = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//        Font font1 = new Font(font,10,Font.BOLDITALIC);
//        BaseFont base= BaseFont.createFont(fontfilepath,BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // BaseFont base2 = BaseFont.createFont(BaseFont.HELVETICA,  
        // BaseFont.WINANSI, BaseFont.EMBEDDED);  
        // 水印文字  
        String waterText = text;  
        Image image = null;  
        /*
        if (!"".equals(imgFile)) {  
            image = Image.getInstance(imgFile);  
            image.setAbsolutePosition(imgWidth, imgHeight);  
            // 设置图片的显示大小  
             image.scaleToFit(100, 125);  
        } 
        */ 
        int j = waterText.length(); // 文字长度  
        char c = 0;  
        int high = 0;// 高度  
        // 循环对每页插入水印  
        for (int i = 1; i < total+1; i++) {  
            // 水印的起始  
            high = 50;  
            // 水印在之前文本之上  
            content = stamper.getOverContent(i);
            
//            if (image != null) {  
//                content.addImage(image);  
//            }  
  
            if (!"".equals(text)) {  
                // 开始  
                content.beginText();  
                // 设置颜色 默认为蓝色  
                content.setColorFill(BaseColor.BLACK);  
                // content.setColorFill(Color.GRAY);  
                // 设置字体及字号  
                content.setFontAndSize(font, 20); 

                // 设置起始位置  
//                 content.setTextMatrix(400, 880);  
                content.setTextMatrix(textWidth, textHeight);  
                // 开始写入水印  
                content.showTextAligned(Element.ALIGN_LEFT, text, textWidth, textHeight, 45);  
                 for (int k = 0; k < j; k++) {  
                 content.setTextRise(14);  
                 c = waterText.charAt(k);  
                 // 将char转成字符串  
                 content.showText(c + "");  
                 high -= 5;  
                 }  
                content.endText();  
            }  
        }  
        stamper.close();  
        System.out.println("===" + srcFile + "===添加水印到==" + destFile + "==成功=====");  
    }  
    
    public static void add(String srcFile, String destFile, String text) throws IOException, DocumentException
    {
    	PdfReader reader = new PdfReader(srcFile, "World".getBytes());
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(destFile));
        // 拿到第一页的上部内容部分
        PdfContentByte over = stamp.getOverContent(1);
        // 增加内容
        over.beginText();
        over.setTextMatrix(30, 30);
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
        over.setFontAndSize(bf, 18);
        over.showText("page1");
        over.showTextAligned(Element.ALIGN_LEFT, "DUPLICATE", 230, 430, 45);
        over.endText();
        // 增加新的一页2
//        stamp.insertPage(2, PageSize.A4);
        // 写上内容
        over = stamp.getOverContent(2);
        over.beginText();
        over.setFontAndSize(bf, 18);
        over.showTextAligned(Element.ALIGN_LEFT, "中国云签", 30, 600, 0);
        over.endText();
        // 插入水印
//        Image img = Image.getInstance(new URL(
//            "http://www.java2000.net/images/company/www.java2000.net.gif"));
//        img.setAbsolutePosition(200, 400);
        PdfContentByte under = stamp.getUnderContent(2);
//        under.addImage(img);
        // 增加新的一页
        stamp.insertPage(5, PageSize.A4);
        // 从现有的别的pdf合并过来
        PdfReader reader2 = new PdfReader(srcFile, "中国云签".getBytes());
        under = stamp.getUnderContent(3);
        under.addTemplate(stamp.getImportedPage(reader2, 1), 1, 0, 0, 1, 0, 0);
        // 关闭
        stamp.close();
    }
    
	    public static void main(String[] args) {
	    	
	    	
//	    	List<float[]> ff = getKeyWords("D:/20160912151631772_2.pdf","国家图书馆藏");
//	        for(float[] f : ff){
//	            System.out.println(f[0]+"----"+f[1]+"-----"+f[2]);
//	        }
	    	try {
//	    		test("D:/20160912151631772.pdf", "D:/20160912151631772_2.pdf", "国家图书馆藏", "D:/maimai.png", 200, 400, 100, 400);
	    		addPageAndContent("D:/20160912151631772.pdf", "D:/20160912151631772_2.pdf","国家图书馆藏", 200,400,15);
//	    		 add("D:/20160912151631772.pdf", "D:/20160912151631772_2.pdf","国家图书馆藏");
	    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	    }
}
