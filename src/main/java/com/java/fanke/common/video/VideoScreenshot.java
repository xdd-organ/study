package com.java.fanke.common.video;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

public class VideoScreenshot {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoScreenshot.class);

    /**
     * 获取指定视频的帧并保存为图片至指定目录
     * @param videoFile  源视频文件路径
     * @param frameFile  截取帧的图片存放路径
     * @throws Exception
     */
    /*public static void fetchFrame(String videoFile, String frameFile) {
        try {
            long start = System.currentTimeMillis();
            File targetFile = new File(frameFile);
            FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videoFile);
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
            opencv_core.IplImage img = f.image;
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
            LOGGER.info("耗时：{}", System.currentTimeMillis() - start);
        } catch (Exception e) {
            LOGGER.error("保存视频封面图片报错", e);
        }

    }*/

    public static void fetchFrame(String videofile, String framefile) {
        FFmpegFrameGrabber ff = null;
          try {
              LOGGER.info("截图开始");
              long start = System.currentTimeMillis();
              File targetFile = new File(framefile);
              ff = new FFmpegFrameGrabber(videofile);
              ff.start();
              int lenght = ff.getLengthInFrames();
              int i = 0;
              Frame f = null;
              LOGGER.info("一共{}帧", lenght);
              while (i < lenght) {
                  // 过滤前5帧，避免出现全黑的图片，依自己情况而定
//                  f = ff.grabFrame();
                  f = ff.grabFrame();
                  if ((i > 5) && (f.image != null)) {
                      break;
                  }
                  i++;

              }
              if (f == null || f.image == null) {
                  LOGGER.info("读取关键帧");
                  f = ff.grabKeyFrame();
              }
                //IplImage img = f.image;
//              int owidth = f.imageWidth;
//              int oheight = f.imageHeight;
              int owidth = ff.getImageWidth();
              int oheight = ff.getImageHeight();
              if (owidth <= 0) {
                  owidth = f.imageWidth;
                  oheight = f.imageHeight;
              }
              if (owidth <= 0) {
                  owidth = 1400;
                  oheight = 680;
              }
              LOGGER.info("封面宽：{}，高：{}", owidth, oheight);
              // 对截取的帧进行等比例缩放
              int width = owidth;
//              int height = (int) (((double) width / owidth) * oheight);
              int height = oheight;
              Java2DFrameConverter converter = new Java2DFrameConverter();
              BufferedImage fecthedImage = converter.getBufferedImage(f);
              BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
              bi.getGraphics().drawImage(fecthedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH),
                      0, 0, null);
              ImageIO.write(bi, "jpg", targetFile);
              ff.stop();
              LOGGER.info("耗時：{}", System.currentTimeMillis() - start);
          } catch (Exception e) {
              LOGGER.error("保存视频封面图片报错", e);
          } finally {
              if (ff != null) {
                  try {
                      ff.close();
                  } catch (FrameGrabber.Exception e) {
                      LOGGER.error("保存视频封面图片报错", e);
                  }
              }
          }
    }
}
