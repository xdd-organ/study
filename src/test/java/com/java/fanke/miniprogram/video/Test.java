package com.java.fanke.miniprogram.video;

import com.alibaba.fastjson.JSONObject;
import com.java.fanke.common.video.VideoScreenshot;
import com.tls.tls_sigature.tls_sigature;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import com.tls.tls_sigature.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Test {
 
	/**
	 * 获取指定视频的帧并保存为图片至指定目录
	 * @param videofile  源视频文件路径
	 * @param framefile  截取帧的图片存放路径
	 * @throws Exception
	 */
	/*public static void fetchFrame(String videofile, String framefile)
	        throws Exception {
	    long start = System.currentTimeMillis();
	    File targetFile = new File(framefile);
	    FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile); 
	    ff.start();
	    int lenght = ff.getLengthInFrames();
	    int i = 0;
	    Frame f = null;
	    while (i < lenght) {
	        // 过滤前5帧，避免出现全黑的图片，依自己情况而定
	        f = ff.grabFrame();
	        if ((i > 5) && (f.image != null)) {
	            break;
	        }
	        i++;
	    }
	    IplImage img = f.image;
	    int owidth = img.width();
	    int oheight = img.height();
	    // 对截取的帧进行等比例缩放
	    int width = 800;
	    int height = (int) (((double) width / owidth) * oheight);
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
	    bi.getGraphics().drawImage(f.image.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH),
	            0, 0, null);
	    ImageIO.write(bi, "jpg", targetFile);
	    //ff.flush();
	    ff.stop();
	    System.out.println(System.currentTimeMillis() - start);
	}*/
 
	/*public static void main(String[] args) {
		boolean blank = StrUtil.isBlank("");
		String a = new String("1");
		System.out.println(a.hashCode());
		a = new String("2");
		System.out.println(a.hashCode());
		a = new String("3");
		System.out.println(a.hashCode());
		a = new String("2");
		System.out.println(a.hashCode());


		ExcelReader reader = ExcelUtil.getReader(new File(""));


		*//*try {
	        Test.fetchFrame("F:\\aa.mp4", "F:\\test5.jpg");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }*//*
	}*/

	public static void fetchFrame(String videofile, String framefile)
			throws Exception {
		long start = System.currentTimeMillis();
		File targetFile = new File(framefile);
		FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);
		ff.start();
		int lenght = ff.getLengthInFrames();
		int i = 0;
		Frame f = null;
		while (i < lenght) {
			// 过滤前5帧，避免出现全黑的图片，依自己情况而定
			f = ff.grabFrame();
			if ((i > 5) && (f.image != null)) {
				break;
			}
			i++;
		}
//        IplImage img = f.image;
		int owidth = f.imageWidth;
		int oheight = f.imageHeight;
		// 对截取的帧进行等比例缩放
		int width = owidth;
//		int height = (int) (((double) width / owidth) * oheight);
		int height = oheight;
		Java2DFrameConverter converter = new Java2DFrameConverter();
		BufferedImage fecthedImage = converter.getBufferedImage(f);
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		bi.getGraphics().drawImage(fecthedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH),
				0, 0, null);
		ImageIO.write(bi, "jpg", targetFile);
		ff.stop();
		System.out.println(System.currentTimeMillis() - start);
	}

	@org.junit.Test
	public void aa() throws Exception {
        InputStream in = new FileInputStream("E:\\aa.mp4");
        VideoScreenshot.fetchFrame("E:\\bb.mp4", "E:\\cc.jpg");
    }

	@org.junit.Test
	public void test2() throws Exception{
		String priKeyContent = "";
		String puiKeyContent = "";
		tls_sigature.GenTLSSignatureResult result = tls_sigature.GenTLSSignatureEx(1, "admin", priKeyContent);
		System.out.println(result.urlSig);

		tls_sigature.CheckTLSSignatureResult admin = tls_sigature.CheckTLSSignatureEx(result.urlSig, 1, "admin", puiKeyContent);
		System.out.println(JSONObject.toJSONString(admin));
	}
}