package com.oa.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Clear;
import com.jfinal.core.JFinal;
import com.jfinal.upload.UploadFile;
import com.oa.controller.BaseAssociationController;
import com.zhilian.config.Constant;
import com.zhilian.util.FileUtils;

/**
 * kindediter文件上传
 * 
 * @author ling
 *
 */
@com.zhilian.annotation.RouteBind(path = "Main/Kinder", viewPath = "Common")
public class Kinder extends BaseAssociationController {

	/**
	 * 文件上传
	 * 
	 * @throws Exception
	 */
	@Clear
	public void upload() throws Exception {
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		Map<String, Object> result = new HashMap<String, Object>();
		String dirName = getPara("dir") == null ? "image" : getPara("dir");
		String realpath = Constant.PATH_WEBROOT + "/" +Constant.PATH_FILE_UPLOAD;
		File file = new File(realpath);

		// 判断文件夹是否存在,如果不存在则创建文件夹
		if (!file.exists()) {
			file.mkdir();
		}
		try {
			UploadFile uf = getFile();

			// String affix_id = "";
			String affix_name = "";
			if (uf != null) {
				affix_name = uf.getFile().getName();
				file = uf.getFile();
				// 检查扩展名
				String fileExt = affix_name.substring(affix_name.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
					result.put("error", 1);
					result.put("message", "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
					file.delete();
				} else {
					result.put("error", 0);
					if (dirName.equals("file")) {
						result.put("url", "/File" + affix_name);
					} else {
						result.put("url", Constant.PATH_FILE_UPLOAD + affix_name);
					}

				}
			} else {
				result.put("error", 1);
				result.put("message", "请选择文件");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		renderJson(result);
	}

	/**
	 * 文件管理
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@Clear
	public void manageImg() {
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };

		String clientPath = "".equals(getPara("path")) ? "" : "/" + getPara("path");// 客户端传过来的文件路径

		String currentPath = getRequest().getRealPath("/file") + clientPath;

		File currentPathFile = new File(currentPath);
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if (currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if (file.isFile()) {
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}
		// 排序形式，name or size or type
		// String order = getPara("order") != null ?
		// getPara("order").toLowerCase() : "name";
		// if ("size".equals(order)) {
		// Collections.sort(fileList, new SizeComparator());
		// } else if ("type".equals(order)) {
		// Collections.sort(fileList, new TypeComparator());
		// } else {
		// Collections.sort(fileList, new NameComparator());
		// }

		Map result = new HashMap();
		result.put("moveup_dir_path", "");
		result.put("current_dir_path", "");
		result.put("current_url", JFinal.me().getContextPath() + "/file/" + clientPath);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);
		// System.out.println(JSON.toJSONString(result));
		renderJson(result);
	}

	@SuppressWarnings({ "deprecation" })
	@Clear
	public void removeFiles() {
		String isDir = getPara("isDir");
		String url = getPara("url");

		String currentPath = getRequest().getRealPath("/file") + url.replace("/file", "");

		if ("true".equals(isDir)) {// 删除目录
			FileUtils.delFolder(currentPath);
		} else {// 删除文件
			FileUtils.delFile(currentPath);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "success");
		result.put("isDir", isDir);

		renderJson(result);
	}
}
