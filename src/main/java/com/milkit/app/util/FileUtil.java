package com.milkit.app.util;

import javax.imageio.ImageIO;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.milkit.app.common.AppCommon;
import com.milkit.app.common.exception.StorageException;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class FileUtil {
	
	public static boolean isWindows() {
		return System.getProperty("os.name").startsWith("Windows");
	}
	
	public static String getExtension(String filename) {
		String extension = "";
		if(filename != null) {
			int index = filename.lastIndexOf('.');
			if(index > 0 && index < (filename.length()-1)) {
				extension = filename.substring(index+1);
			}
		}
		
		return extension;
	}
	
	public static String getExtension(MultipartFile multipartFile) {
		String filePath = multipartFile.getOriginalFilename();
		
		return getExtension(filePath);
	}
	
	public static String getNameWithoutExtension(String filename) {
		String onlyName = filename;
		if(filename != null) {
			int endIndex = filename.lastIndexOf('.');
			if(endIndex > 0 && endIndex < (filename.length()-1)) {
				int startIndex = filename.lastIndexOf(File.separatorChar);
				onlyName = filename.substring(startIndex+1, endIndex);
			}
		}
		
		return onlyName;
	}
	
	public static String getFilename(String filename) {
		String onlyName = filename;
		if(filename != null) {
			char directoryDelim = File.separatorChar;
			
			if(isWindows() == true) {
//				directoryDelim = '\\';
//				directoryDelim = '/';
			}
			
			int startIndex = filename.lastIndexOf(directoryDelim);
			
			if(startIndex > 0 && startIndex < (filename.length()-1)) {
				onlyName = filename.substring(startIndex+1);
			}
		}
		
		return onlyName;
	}
	
	public static String getFilename(String filename, String productCD, String extFileName) {
		String onlyName = filename;
		if(filename != null) {
			char directoryDelim = File.separatorChar;
			
			if(isWindows() == true) {
//				directoryDelim = '\\';
//				directoryDelim = '/';
			}
			
			int startIndex = filename.lastIndexOf(directoryDelim);
			
			if(startIndex > 0 && startIndex < (filename.length()-1)) {
				onlyName = filename.substring(startIndex+1);
			}
		}
		
		if(productCD != null && !productCD.equals("")) {
			onlyName = "product_"+productCD+"."+extFileName;
		}
		
		return onlyName;
	}
	

	
	
    public static String saveImage(String saveRootPath, String subPath, String imgUrl, boolean isRandomFilename) throws Exception {
//        return saveImage(saveRootPath, subPath, new File(imgUrl), isRandomFilename);
    	
    	URL transURL = null;
		if(imgUrl.toString().startsWith("file") == false) {
        	transURL = new URL("file:///"+imgUrl);
        } else {
        	transURL = new URL(imgUrl);
        }
        
        return saveImage(saveRootPath, subPath, transURL, isRandomFilename);
    }
    
    public static String saveImage(String saveRootPath, String subPath, File imgFile, boolean isRandomFilename) throws Exception {
    	return saveImage(saveRootPath, subPath, imgFile.toURI().toURL(), isRandomFilename);
    }
    
    
    public static String saveImage(String saveRootPath, String subPath, URL imgUrl, boolean isRandomFilename) throws Exception {
        String imgPath = null;
        BufferedImage image = null;
        BufferedImage bufferedImage = null; 
        
        try {
            //??옣 ?붾젆?좊━ ?앹꽦
            imgPath = saveRootPath + subPath;
            File imgDir = new File(imgPath);
            if(!imgDir.exists()){
                imgDir.mkdirs();
            }

            String filePath = imgUrl.toString();
            String filename = "";
            //?뚯씪 ?뺤옣??
            String extFileName = getExtension(filePath);
            
            if(isRandomFilename == true) {
            	filename = UUID.randomUUID().toString().replaceAll("[-]", "")+"."+getExtension(filePath);
            } else {
            	filename = getFilename(filePath);
            }
            
            //??옣 ?뚯씪紐??앹꽦
            imgPath = imgPath + filename;

            //?뱀씠誘몄? ??옣
//			File file = new File(new URI(readURL));
//          image = ImageIO.read(new URL(readURL)); //?대?吏?二쇱냼
			image = ImageIO.read(imgUrl); //?대?吏?二쇱냼
            bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_BGR); // ?대?吏??ш린 // ?앹꽦
            ImageIO.write(image, extFileName, new File(imgPath)); // ?대?吏?
        } catch (Exception e) {
            imgPath = null;
            e.printStackTrace();
        } finally {
        	if(image != null) {
        		image.flush();
        		image = null;
        	}
        	if(bufferedImage != null) {
        		bufferedImage.flush();
        		bufferedImage = null;
        	}
        }
        return imgPath;
    }
    
	public static String saveImage(String saveRootPath, String subPath, URL imgUrl, boolean isRandomFilename, String productCD)  {
		 String imgPath = null;
	        BufferedImage image = null;
	        BufferedImage bufferedImage = null; 
	        
	        try {
	            //??옣 ?붾젆?좊━ ?앹꽦
	            imgPath = saveRootPath + subPath;
	            File imgDir = new File(imgPath);
	            if(!imgDir.exists()){
	                imgDir.mkdirs();
	            }

	            String filePath = imgUrl.toString();
	            String filename = "";
	            //?뚯씪 ?뺤옣??
	            String extFileName = getExtension(filePath);
	            
	            if(isRandomFilename == true) {
	            	filename = UUID.randomUUID().toString().replaceAll("[-]", "")+"."+getExtension(filePath);
	            } else {
	            	filename = getFilename(filePath, productCD, extFileName);
	            }
	            
	            //??옣 ?뚯씪紐??앹꽦
	            imgPath = imgPath + filename;

	            //?뱀씠誘몄? ??옣
//				File file = new File(new URI(readURL));
//	          image = ImageIO.read(new URL(readURL)); //?대?吏?二쇱냼
				image = ImageIO.read(imgUrl); //?대?吏?二쇱냼
	            bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_BGR); // ?대?吏??ш린 // ?앹꽦
	            ImageIO.write(image, extFileName, new File(imgPath)); // ?대?吏?
	        } catch (Exception e) {
	            imgPath = null;
	            e.printStackTrace();
	        } finally {
	        	if(image != null) {
	        		image.flush();
	        		image = null;
	        	}
	        	if(bufferedImage != null) {
	        		bufferedImage.flush();
	        		bufferedImage = null;
	        	}
	        }
	        return imgPath;
	}
    
    public static String saveImage(String saveRootPath, String subPath, MultipartFile multipartFile, boolean isRandomFilename) throws Exception {
        String imgPath = null;
        BufferedImage image = null;
        BufferedImage bufferedImage = null; 
        
        try {
            //??옣 ?붾젆?좊━ ?앹꽦
            imgPath = saveRootPath + subPath;
            File imgDir = new File(imgPath);
            if(!imgDir.exists()){
                imgDir.mkdirs();
            }

            String filePath = multipartFile.getOriginalFilename();
            String filename = "";
            //?뚯씪 ?뺤옣??
            String extFileName = getExtension(filePath);
            
            if(isRandomFilename == true) {
            	filename = UUID.randomUUID().toString().replaceAll("[-]", "")+"."+getExtension(filePath);
            } else {
            	//filename = getFilename(filePath);
            	filename = getFilename(filePath);
            }
            
            //??옣 ?뚯씪紐??앹꽦
            imgPath = imgPath + filename;

            //?뱀씠誘몄? ??옣
			image = ImageIO.read(multipartFile.getInputStream()); //?대?吏?二쇱냼
            bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_BGR); // ?대?吏??ш린 // ?앹꽦
            ImageIO.write(image, extFileName, new File(imgPath)); // ?대?吏?
        } catch (Exception e) {
            imgPath = null;
            e.printStackTrace();
        } finally {
        	if(image != null) {
        		image.flush();
        		image = null;
        	}
        	if(bufferedImage != null) {
        		bufferedImage.flush();
        		bufferedImage = null;
        	}
        }
        return imgPath;
    }
    
    public static String saveImage(String saveRootPath, String subPath, MultipartFile multipartFile, boolean isRandomFilename, String productCD) throws Exception {
    	return saveImage(saveRootPath, subPath, multipartFile, AppCommon.getInstance().IMAGE_WIDTH, AppCommon.getInstance().IMAGE_HEIGHT, isRandomFilename, productCD);
    }
    
    public static String saveImage(String saveRootPath, String subPath, MultipartFile multipartFile, int width, int height, boolean isRandomFilename, String productCD) throws Exception {
        String imgPath = null;
        BufferedImage image = null;
        BufferedImage bufferedImage = null; 
        
        try {
            //??옣 ?붾젆?좊━ ?앹꽦
            imgPath = saveRootPath + subPath;
            File imgDir = new File(imgPath);
            if(!imgDir.exists()){
                imgDir.mkdirs();
            }

            String filePath = multipartFile.getOriginalFilename();
            String filename = "";
            //?뚯씪 ?뺤옣??
            String extFileName = getExtension(filePath);
            
            if(isRandomFilename == true) {
            	filename = UUID.randomUUID().toString().replaceAll("[-]", "")+"."+getExtension(filePath);
            } else {
            	//filename = getFilename(filePath);
            	filename = getFilename(filePath, productCD, extFileName);
            }
            
            //??옣 ?뚯씪紐??앹꽦
            imgPath = imgPath + filename;

            //?뱀씠誘몄? ??옣
			image = ImageIO.read(multipartFile.getInputStream()); //?대?吏?二쇱냼

//            bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_BGR); // ?대?吏??ш린 // ?앹꽦
//            ImageIO.write(image, extFileName, new File(imgPath)); // ?대?吏?
			
			Builder<BufferedImage> thBuilder = Thumbnails.of(image);
			if(width > 0 && height > 0) {
//				thBuilder = thBuilder.size(MessageCommon.getInstance().IMAGE_WIDTH, MessageCommon.getInstance().IMAGE_HEIGHT);
				thBuilder = thBuilder.forceSize(width, height);
			}
			thBuilder.toFile(new File(imgPath));
			
        } catch (Exception e) {
            imgPath = null;
            e.printStackTrace();
        } finally {
        	if(image != null) {
        		image.flush();
        		image = null;
        	}
        	if(bufferedImage != null) {
        		bufferedImage.flush();
        		bufferedImage = null;
        	}
        }
        return imgPath;
    }
    
/*
    public static String saveImage(String saveRootPath, String subPath, URL imgUrl, boolean isRandomFilename) throws Exception {

    	File tmpFile = null;
    	
        if(imgUrl.toString().startsWith("http") == true) {
        	String tDir = System.getProperty("java.io.tmpdir");
        	String path = tDir + imgUrl.getFile(); 
        	tmpFile = new File(path);
        	tmpFile.deleteOnExit();
        	
        	FileUtils.copyURLToFile(imgUrl, tmpFile);
        } else {
        	tmpFile = new File( imgUrl.toURI() );
        }
    	
        return saveImage(saveRootPath, subPath, tmpFile, isRandomFilename);
    }
    
    public static String saveImage(String saveRootPath, String subPath, File imgFile, boolean isRandomFilename) throws Exception {
        String imgPath = null;
        BufferedImage image = null;
        BufferedImage bufferedImage = null; 
        
        try {
            //??옣 ?붾젆?좊━ ?앹꽦
            imgPath = saveRootPath + subPath;
            File imgDir = new File(imgPath);
            if(!imgDir.exists()){
                imgDir.mkdirs();
            }

            String filePath = imgFile.getAbsolutePath();
            String filename = "";
            //?뚯씪 ?뺤옣??
            String extFileName = getExtension(filePath);
            
            if(isRandomFilename == true) {
            	filename = UUID.randomUUID().toString().replaceAll("[-]", "")+"."+getExtension(filePath);
            } else {
            	filename = getFilename(filePath);
            }
            
            //??옣 ?뚯씪紐??앹꽦
            imgPath = imgPath + filename;

            //?뱀씠誘몄? ??옣
//			File file = new File(new URI(readURL));
//          image = ImageIO.read(new URL(readURL)); //?대?吏?二쇱냼
			image = ImageIO.read(imgFile); //?대?吏?二쇱냼
            bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_BGR); // ?대?吏??ш린 // ?앹꽦
            ImageIO.write(image, extFileName, new File(imgPath)); // ?대?吏?
        } catch (Exception e) {
            imgPath = null;
            e.printStackTrace();
        } finally {
        	if(image != null) {
        		image.flush();
        		image = null;
        	}
        	if(bufferedImage != null) {
        		bufferedImage.flush();
        		bufferedImage = null;
        	}
        }
        return imgPath;
    }
*/
    
    public static String saveWebImage(String saveRootPath, String imgUrl) {
        String imgPath = null;
        try {
            //??옣 ?붾젆?좊━ ?앹꽦
            String subPath = new SimpleDateFormat("/yyyy/MM/dd/").format(System.currentTimeMillis());
            imgPath = saveRootPath + subPath;
            File imgDir = new File(imgPath);
            if(!imgDir.exists()){
                imgDir.mkdirs();
            }

            //??옣 ?뚯씪紐??앹꽦
            imgPath = imgPath + UUID.randomUUID().toString().replaceAll("[-]", "");

            //?뚯씪 ?뺤옣??
            String extFileName = getExtension(imgUrl);

            //?뱀씠誘몄? ??옣
            BufferedImage image = ImageIO.read(new URL(imgUrl)); //?대?吏?二쇱냼
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_INT_BGR); // ?대?吏??ш린 // ?앹꽦
            ImageIO.write(image, extFileName, new File(imgPath)); // ?대?吏?
        } catch (Exception e) {
            imgPath = null;
            e.printStackTrace();
        }
        return imgPath;
    }
    
    
    public static String saveWebImage(String saveRootPath, String subPath, String imgUrl) {
        String imgPath = null;
        try {
            //??옣 ?붾젆?좊━ ?앹꽦
            imgPath = saveRootPath + subPath;
            File imgDir = new File(imgPath);
            if(!imgDir.exists()){
                imgDir.mkdirs();
            }

            //??옣 ?뚯씪紐??앹꽦
            imgPath = imgPath + UUID.randomUUID().toString().replaceAll("[-]", "");

            //?뚯씪 ?뺤옣??
            String extFileName = getExtension(imgUrl);

            //?뱀씠誘몄? ??옣
            BufferedImage image = ImageIO.read(new URL(imgUrl)); //?대?吏?二쇱냼
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_INT_BGR); // ?대?吏??ш린 // ?앹꽦
            ImageIO.write(image, extFileName, new File(imgPath)); // ?대?吏?
        } catch (Exception e) {
            imgPath = null;
            e.printStackTrace();
        }
        return imgPath;
    }

	public static String saveFile(String saveRootPath, String subPath, String imgUrl, boolean isRandomFilename) throws Exception {
    	URL transURL = null;
		if(imgUrl.toString().startsWith("file") == false) {
        	transURL = new URL("file:///"+imgUrl);
        } else {
        	transURL = new URL(imgUrl);
        }
        
        return saveFile(saveRootPath, subPath, transURL, isRandomFilename);
	}

	public static String saveFile(String saveRootPath, String subPath, URL imgUrl, boolean isRandomFilename) throws Exception {
        String imgPath = null;
        
        try {
            //??옣 ?붾젆?좊━ ?앹꽦
            imgPath = saveRootPath + subPath;
            File imgDir = new File(imgPath);
            if(!imgDir.exists()){
                imgDir.mkdirs();
            }

            String filePath = imgUrl.toString();
            String filename = "";
            
            if(isRandomFilename == true) {
            	filename = UUID.randomUUID().toString().replaceAll("[-]", "")+"."+getExtension(filePath);
            } else {
            	filename = getFilename(filePath);
            }
            
            //??옣 ?뚯씪紐??앹꽦
            imgPath = imgPath + filename;

            copyFile(imgUrl.openStream(), imgPath);
            
        } catch (Exception e) {
            imgPath = null;
            e.printStackTrace();
        }
        
        return imgPath;
	}

	
    public static String saveFile(String saveRootPath, String subPath, MultipartFile multipartFile, boolean isRandomFilename) throws Exception {
        String imgPath = null;
        
        try {
            //??옣 ?붾젆?좊━ ?앹꽦
            imgPath = saveRootPath + subPath;
            File imgDir = new File(imgPath);
            if(!imgDir.exists()){
                imgDir.mkdirs();
            }

            String filePath = multipartFile.getOriginalFilename();
            String filename = "";
            
            if(isRandomFilename == true) {
            	filename = UUID.randomUUID().toString().replaceAll("[-]", "")+"."+getExtension(filePath);
            } else {
            	filename = getFilename(filePath);
            }
            
            //??옣 ?뚯씪紐??앹꽦
            imgPath = imgPath + filename;

            copyFile(multipartFile.getInputStream(), imgPath);            
        } catch (Exception e) {
            imgPath = null;
            e.printStackTrace();
        }
        
        return imgPath;
    }
    
	
	public static String copyFile(InputStream inStream, String outputPath) throws Exception {
        FileOutputStream outStream = null;
        
		try {
	        File outpuFile = new File(outputPath);
	        
	        outStream = new FileOutputStream(outpuFile);
	        
	        byte[] buffer = new byte[1024];
	
	        int readLength;
	        while ((readLength = inStream.read(buffer)) > 0) {
	        	outStream.write(buffer, 0, readLength);
	        }
		} finally {
        	if(inStream != null) {
        		inStream.close();
        		inStream = null;
        	}
        	if(outStream != null) {
        		outStream.close();
        		outStream = null;
        	}
        }
		
		return outputPath;
	}

}
