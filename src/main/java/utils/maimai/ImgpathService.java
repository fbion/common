package utils.maimai;

//import com.thrift.service.ImgResult;
import com.yunsign.util.ImgResult;
import com.yunsign.util.Imgpath;

/**
 * PDF转图片服务
 * 
 * @author Administrator
 * 
 */
public interface ImgpathService {
	/**
	 * pdf转图片，并返回图片存放路径
	 * @return
	 */
	Imgpath getPathForPdfToImg(String filepath, float zoom);
	
	/**
	 * 图片去背景，并返回图片存放路径
	 * @return
	 */
	ImgResult clearImgbg(String srcPath, String targetPath);

}
