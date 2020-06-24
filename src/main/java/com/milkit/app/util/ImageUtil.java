package com.milkit.app.util;

import java.io.File;
import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.coobird.thumbnailator.Thumbnails;

public class ImageUtil {

	private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);
	
	public static String saveThumbnail(String filepath, int width, int height) throws IOException {
		File orgFile = new File(filepath);
		File fileDir = orgFile.getParentFile();

/*
		String filenameWithoutExtension = FileUtil.getNameWithoutExtension(orgFile.getName());
		String fileExtension = FileUtil.getExtension(orgFile.getName());
		
		String prefixMark = "tmb_";
		String thumbnailName = prefixMark+filenameWithoutExtension+"."+fileExtension;
		String destinationFilename = fileDir.getAbsolutePath()+File.separatorChar+thumbnailName;
*/
		
		String filename = FileUtil.getFilename(orgFile.getName());
		String prefixMark = "tmb_";
		String thumbnailName = prefixMark+filename;
		String destinationFilename = fileDir.getAbsolutePath()+File.separatorChar+thumbnailName;
		
logger.debug("AbsolutePath:"+destinationFilename);
		
		Thumbnails.of(filepath)
        .size(width, height)
//        .toFiles((fileDir == null ? File.createTempFile("", "").getParentFile() : fileDir), Rename.SUFFIX_HYPHEN_THUMBNAIL);
		.toFile(new File(destinationFilename));
		
		return getParentPath(filepath)+File.separatorChar+thumbnailName;
	}
	
	private static String getParentPath(String filepath) {
		String rarentPath = filepath;
		if(filepath != null) {
			char directoryDelim = File.separatorChar;
			
			int endIndex = filepath.lastIndexOf(directoryDelim);
			
			if(endIndex > 0 && endIndex < (filepath.length()-1)) {
				rarentPath = filepath.substring(0, endIndex);
			}
		}
		
		return rarentPath;
	}
	
}
