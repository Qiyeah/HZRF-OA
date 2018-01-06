import java.io.File;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;

/**
 * 服务器启动管理 （该类仅做开发使用）
 */
public class HZRF_OA_Server {
	public static void main(String[] args) {
		String webAppDir = PathKit.getWebRootPath().replace(File.separator, "/");
		try {
			JFinal.start(webAppDir, 8083, "/", 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}