package utils.maimai;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ImageUtil {
	private static Logger log = LoggerFactory.getLogger(ImageUtil.class);
	
	private static ImageUtil instance;
	 
    private ImageUtil() {
        instance = this;
    }
 
    public static ImageUtil getInstance() {
        if (instance == null) {
            instance = new ImageUtil();
        }
        return instance;
    }
 
    /**
     * 将jpg格式图片转换成png格式图片
     * 缩小并转换格式
     * 
     * @param srcPath源路径
     * @param destPath目标路径
     * @param height目标高
     * @param width
     *            目标宽
     * @param formate
     *            文件格式
     * @return
     */
    public static boolean narrowAndFormateTransfer(String srcPath, String destPath, int height, int width, String formate) {
        boolean flag = false;
        try {
            File file = new File(srcPath);
            File destFile = new File(destPath);
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdir();
            }
            BufferedImage src = ImageIO.read(file); // 读入文件
            Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            flag = ImageIO.write(tag, formate, new FileOutputStream(destFile));// 输出到文件流
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    /**
     * 将png格式图片转换成jpg格式图片
     * @param srcPath源路径
     * @param destPath目标路径
     * @return
     */
    public void pngTojpg(String srcPath,String destPath)
    {
    	BufferedImage bufferedImage;
	    try {
	      //read image file
//	      bufferedImage = ImageIO.read(new File("E:\\office\\201603\\CP3038327756983417\\img\\20160303133437731\\0.png"));
	     bufferedImage = ImageIO.read(new File(srcPath));
	      // create a blank, RGB, same width and height, and a white background
	      BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
	            bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
	      newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
	      // write to jpeg file
//	      ImageIO.write(newBufferedImage, "jpg", new File("E:\\office\\201603\\CP3038327756983417\\img\\20160303133437731\\0.jpg"));
	      ImageIO.write(newBufferedImage, "jpg", new File(destPath));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
    }
//    public static void main(String[] args) {
//        try {
//            ImageEncoderService service = new ImageEncoderService();
//            boolean flag = service.narrowAndFormateTransfer("E:\\我的图片/雨伞.jpg", "E:\\我的图片/雨伞.png", 400, 400, "png");
//            System.out.println(flag);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
	
	/**
	 * PNG相对不失真
	 *  图二的位置　从左上角开始
	 * @param filesrc
	 * @param logosrc
	 * @param outsrc
	 * @param x
	 * @param y
	 */
	public static void mergeImg(String filesrc, String logosrc, String outsrc,int x, int y) throws Exception
	{
		log.info("filesrc="+filesrc+",logosrc="+logosrc+",outsrc="+outsrc+",x="+x+",y="+y);
		try {
			// 读取第一张图片
			File fileOne = new File(filesrc);
			BufferedImage ImageOne = ImageIO.read(fileOne);
			int width = ImageOne.getWidth();// 图片宽度
			int height = ImageOne.getHeight();// 图片高度
			System.out.println("width="+width+",height="+height);
			// 从图片中读取RGB
			int[] ImageArrayOne = new int[width * height];
			ImageArrayOne = ImageOne.getRGB(0, 0, width, height, ImageArrayOne,
					0, width);
			// 对第二张图片做相同的处理
			File fileTwo = new File(logosrc);
			BufferedImage ImageTwo = ImageIO.read(fileTwo);
			
			
			
			int widthTwo = ImageTwo.getWidth();// 图片宽度
			int heightTwo = ImageTwo.getHeight();// 图片高度
			int[] ImageArrayTwo = new int[widthTwo * heightTwo];
			ImageArrayTwo = ImageTwo.getRGB(0, 0, widthTwo, heightTwo,
					ImageArrayTwo, 0, widthTwo);

			// 生成新图片
//			BufferedImage ImageNew = new BufferedImage(width * 2, height,
//					BufferedImage.TYPE_INT_RGB);
			BufferedImage ImageNew = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			
			Graphics2D g2d = ImageNew.createGraphics();
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1.0f)); // 透明度设置开始
			
			
			ImageNew.setRGB(0, 0, width, height, ImageArrayOne, 0, width);// 设置左半部分的RGB
			ImageNew.setRGB(x, y, widthTwo, heightTwo, ImageArrayTwo, 0,widthTwo);// 设置右半部分的RGB
			File outFile = new File(outsrc);
			ImageIO.write(ImageNew, "png", outFile);// 写图片

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("图片合成失败");
		}
	}
	
	public static void mergeImg(CustomImages imgobject[], String outsrc)
            throws Exception {
        try {
            File imgfile[] = new File[imgobject.length];
            BufferedImage imgbuffered[] = new BufferedImage[imgobject.length];
            BufferedImage tag = new BufferedImage(893, 1263,
                    BufferedImage.TYPE_4BYTE_ABGR_PRE);
            Graphics2D g2d[] = new Graphics2D[imgobject.length];
            for (int z = 0; z < imgobject.length; z++) {
                imgfile[z] = new File(imgobject[z].getSrc());
                System.out.println("读取第" + z + "个文件！");
                imgbuffered[z] = ImageIO.read(imgfile[z]);
                System.out.println("把第" + z + "个文件读取成buffered！");
                g2d[z] = tag.createGraphics();
                System.out.println("创建第" + z + "个画笔！");
                g2d[z].drawImage(imgbuffered[z], imgobject[z].getX(),
                        imgobject[z].getY(), imgbuffered[z].getWidth(),
                        imgbuffered[z].getHeight(), null);
            }
            System.out.println("合成完毕，准备输出，请稍候~~~~~~");
            ImageIO.write(tag, "png", new File(outsrc));
            System.out.println("恭喜！！图片输出完毕！");
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("png合并失败");
        }
    }
	
	/**
	 * 
	 * @param filesrc
	 * @param logosrc
	 * @param outsrc
	 * @param x
	 *            位置
	 * @param y
	 *            位置
	 */
	public static void composePic(String filesrc, String logosrc, String outsrc,int x, int y) throws Exception {
		log.info("filesrc="+filesrc+",logosrc="+logosrc+",outsrc="+outsrc+",x="+x+",y="+y);
		try {
			File bgfile = new File(filesrc);
			Image bg_src = ImageIO.read(bgfile);
			File logofile = new File(logosrc);
			Image logo_src = ImageIO.read(logofile);
			int bg_width = bg_src.getWidth(null);
			int bg_height = bg_src.getHeight(null);
			int logo_width = logo_src.getWidth(null);
			int logo_height = logo_src.getHeight(null);
			BufferedImage tag = new BufferedImage(bg_width, bg_height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = tag.createGraphics();
			g2d.drawImage(bg_src, 0, 0, bg_width, bg_height, null);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1.0f)); // 透明度设置开始
			g2d.drawImage(logo_src, x, y, logo_width, logo_height, null);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); // 透明度设置 结束
			FileOutputStream out = new FileOutputStream(outsrc);  
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//			JPEGCodec.createJPEGEncoder(dest, jep)
			encoder.encode(tag);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("图片合成失败");
		}
	}

	public static void changeSvgToJpg(String imgStr, String imgPath,String logSvgPath) throws Exception{
		log.info("imgPath="+imgPath);
		OutputStream out = null;
		OutputStream ostream = null;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String imgFilePath = logSvgPath + System.currentTimeMillis() + ".svg";// 新生成的图片
//			log.info("logSvgPath==="+imgFilePath);
			out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			// svg文件路径
			String strSvgURI;
			// 根据路径获得文件夹
			File fileSvg = new File(imgFilePath);
			// 构造一个表示此抽象路径名的 file:URI
			URI uri = fileSvg.toURI();
			// 根据此 URI 构造一个 URL
			URL url = uri.toURL();
			// 构造此 URL 的字符串表示形式
			strSvgURI = url.toString();
			// 定义一个通用的转码器的输入
			TranscoderInput input = new TranscoderInput(strSvgURI);
			// 定义图片路径
//			String strPngPath = desPath;
			// 输入流
			ostream = new FileOutputStream(imgPath);
			// 定义单路输出的转码器
			TranscoderOutput output = new TranscoderOutput(ostream);
			// 构造一个新的转码器，产生JPEG图像
//			JPEGTranscoder transcoder = new JPEGTranscoder();
			PNGTranscoder transcoder = new PNGTranscoder();
			// 设置一个转码过程，JPEGTranscoder.KEY_QUALITY设置输出png的画质精度，0-1之间
			transcoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,
					new Float(1));
			// 转换svg文件为png
			transcoder.transcode(input, output);
			fileSvg.deleteOnExit();
		} catch (Exception e) {			
			e.printStackTrace();
			throw new Exception("SVG转png失败");
		} finally {
			try {
				ostream.flush();
				// 关闭输入流
				ostream.close();
			} catch (IOException e) {
				throw new Exception("SVG转png时关闭流失败");
			}
		}
	}	

    public static void composeImg(String imageData,String serialNum,String attrName) throws Exception
	{
		Map dataMap = JSON.parseObject(imageData, Map.class);
		JSONObject json = (JSONObject) dataMap.get("data");
		Map<String, Object> info = JSON.parseObject(json.toString(), Map.class);
    	Iterator<String> iterator = info.keySet().iterator();
//		int length = (int) dataMap.get("length");
		double h =  (int)dataMap.get("h");		
		double nh = (int)dataMap.get("nh");//合同真实高度
		double w = (int) dataMap.get("w");
		double nw = (int) dataMap.get("nw");
		DecimalFormat df = new DecimalFormat("###.000");
		double zoom = Double.valueOf(df.format(h/nh)); //合同图片缩放比例,保留小数点后三位
 		int x = 0;
		int y = 0;
		while(iterator.hasNext())
		{
			String key = iterator.next();
			String value = info.get(key).toString();
			Map imgMap = JSON.parseObject(value, Map.class);
			//如果非svg格式,imhData就是图章路径
			String imgData = (String) imgMap.get("img");
			int img_x = (int) imgMap.get("x");//页面传来的x值
			int img_y = (int) imgMap.get("y");//页面传来的y值
			
//			int snh = (int) imgMap.get("snh");//图章原始高度
//			int snw = (int) imgMap.get("snw");//图章原始宽度
//			snh = (int) (snh * zoom);
//			snw = (int) (snw * zoom);
			
			int sh = (int) imgMap.get("sh");//图章的缩放后的高度
			int sw = (int) imgMap.get("sw");//图章的缩放后的宽度
			sh = (int) (sh * zoom);
			sw = (int) (sw * zoom);
			int index = 0; //表示在第几张合同图片上盖章
			if(h == nh)
			{
				index = (int) (img_y/nh);
				x = img_x;
				y = (int) (img_y%nh);				
			}
			else
			{	
//				x = (int) (img_x*zoom);
//				y = (int) ((img_y*zoom)%h);
//				index = (int) (y/h);
				x = (int) (img_x * zoom);
				y = (int) ((img_y % h) * zoom);
				index = (int) (img_y / h);
			}
			log.info("index="+index+",x="+x+",y="+y);
//			String svgData= "E:\\office\\test\\";
			String svgData= "/home/core/centerService/logs/";
			//服务器环境路径
//			String folder = FileUtil.createContractFolder(serialNum);
//			String fileSrc = folder+"img"+File.separator+attrName+File.separator+index+".jpg";		
						
			String folder = "E:\\office\\test\\";
//			String folder = "/home/core/centerService/logs/";
//			String fileSrc = "/home/core/centerService/logs/"+index+".png";		
			String fileSrc = "E:\\office\\test\\"+index+".png";						
			String logoSrc = "";
			if(null != imgData && imgData.contains("svg"))
			{
				logoSrc = folder+"log"+System.currentTimeMillis()+".png";
				svgData = imgData.split(",")[1];		
				changeSvgToJpg(svgData, logoSrc,folder);//svg转png
				clearImgbg(logoSrc, logoSrc);//去背景
				composePic(fileSrc, logoSrc, fileSrc, x, y);//图片合成
			}
			else
			{
				//直接合成图片,获取图章路径
				logoSrc = imgData;
				composePic(fileSrc, logoSrc, fileSrc, x, y);
			}
		}
	}
    
    /**
	 * 获取图片的宽和高
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Integer> getImgWidthAndHeight(String src) throws Exception
	{
		Map<String,Integer> map = new HashMap<String, Integer>();
		InputStream is = null;
		//查询图章
		try{
			is = new FileInputStream(src);//通过文件名称读取
			BufferedImage buff = ImageIO.read(is);
			if(null != buff)
			{
				map.put("width", buff.getWidth());//得到图片的宽度
				map.put("height",buff.getHeight());//得到图片的高度
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(ConstantUtil.RETURN_SEAL_NOT_EXIST[1]);
		}
		finally{
			if(null != is)
			{
				try {
					is.close();//关闭Stream
				} catch (IOException e) {									
					e.printStackTrace();
					throw new Exception(ConstantUtil.FILE_READ_EXCEPTION[1]);
				} 
			}
		}
		return map;
	}
	
    /**
     * pdf默认的宽度和高度为595.0F, 842.0F
	 * pdf自定义加水印
	 * 客户端入参/抑或服务端的自定义参数
	 * @param srcpath 源文件地址
	 * @param destpath 目的文件地址
	 * @param list 图片/文字插入pdf的信息
	 * 传图片时  type=img path=图片路径 width=图片宽度  height=图片高度  x=图片的x坐标  y=图片的y坐标 page=图片所在页码
	 * 传文字时  type=font fontsize=自定义字体大小(不传的话为10) rotation=字体的旋转度(不传值为0,水平) x=字体的x坐标 y=字体的y坐标 page=图片所在页码
	 */
	public static void addWaterMark(Map<String, String> para_map)
			throws Exception {
		try {
			float f = 1.5f;//坐标转换系数
			log.info("addWaterMark的人参为:"+para_map.toString());
			String srcpath = para_map.get("src");
			String destPath = para_map.get("dest");
			PdfReader reader = new PdfReader(srcpath);
			
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(destPath));
			
			com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(para_map.get("logPath"));
			
			float width = Float.valueOf(para_map.get("width"))/f;
			
			float height = Float.valueOf(para_map.get("height"))/f;
			
			img.scaleAbsolute(width,height);			
			
			img.setAbsolutePosition(Float.valueOf(para_map.get("x"))/f,842 - height -  Float.valueOf(para_map.get("y"))/f);
//			img.setAbsolutePosition(100,100);

			System.out.println(img.getAbsoluteY());
			
			PdfContentByte over = stamper.getOverContent(Integer.valueOf(para_map.get("page")));
			
			over.addImage(img);
			
			stamper.close();// 关闭
//			File tempfile = new File(srcpath);
//			if (tempfile.exists()) {
//				tempfile.delete();
//			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("水印合成异常");
		}
	}
	
	public static void clearImgbg(String srcPath, String targetPath) {
		//初始化结果
		log.info(srcPath);

		FileOutputStream os = null ; 
    	try {
			Image srcImg = ImageIO.read(new File(srcPath));
			byte[] b = ImgAlpha.transferAlpha(srcImg);
			os = new FileOutputStream(targetPath);
			os.write(b);
			
		} catch (Exception e) {
			log.error("图片去背景失败;", e);
		}finally{
			 if(null!=os){
				 try {
					os.close();
				} catch (Exception e2) {
				}
			 }
		}  	
	}
	public static void main(String args[]) throws Exception {

	}
}