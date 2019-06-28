package com.viking.MySpringBoot.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Random;

/**
 * Created by Viking on 2019/6/28
 * 二维码工具类
 */
public class QrCodeUtils {
    private static Logger logger = LoggerFactory.getLogger(QrCodeUtils.class);
    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    private static final int QRCODE_SIZE = 300;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;

    public static void encoderJpgQrCoder(String content,String filePath) throws Exception{
        File file = new File(filePath);
        if(file.exists()){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyMMddhhmmss");
            String bakName = sdf.format(new Date());
            file.renameTo(new File(filePath + "."+bakName+".bak"));
        }
        if(!file.exists()){
            file.mkdir();
        }
        FileOutputStream os = new FileOutputStream(file);
        encoderJpgQrCoder(content,os);
    }
    public static void encoderJpgQrCoder(String content, HttpServletResponse response) throws Exception{
        encoderJpgQrCoder(content,response.getOutputStream());
    }

    /**
     * 生成二维码
     * @param content 内容
     * @param os    输出流
     * @throws Exception 异常
     */
    public static void encoderJpgQrCoder(String content, OutputStream os) throws Exception{
        try {
            String logoPath = getLogoPath();
            boolean flag = false;
            if(logoPath != null && logoPath.length() > 0 ){
                flag = true;
            }
            logger.info("QrContent="+content);
            BufferedImage bufferedImage = QrCodeUtils.createImage(content,logoPath,flag);
            ImageIO.write(bufferedImage,"jpg",os);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static BufferedImage createImage(String content,String imgPath,boolean needCompress) throws  Exception{
        Hashtable<EncodeHintType,Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET,CHARSET);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,QRCODE_SIZE,QRCODE_SIZE,hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        for(int x = 0;x < width; x++) {
            for(int y = 0;y < height; y++){
                image.setRGB(x,y,bitMatrix.get(x,y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if(imgPath == null || "".equals(imgPath)){
            return image;
        }
        insertImage(image,imgPath,needCompress);
        return image;
    }
    private static void insertImage(BufferedImage source,String imgPath,boolean needCompress) throws Exception{
        File file = new File(imgPath);
        if(!file.exists()){
            logger.info(" "+imgPath + " 该文件不存在!");
            return ;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if(needCompress){
            if(width > WIDTH){
                width = WIDTH;
            }
            if(height > HEIGHT){
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        Graphics2D graphics2D = source.createGraphics();
        int x = (QRCODE_SIZE - width) /2 ;
        int y = (QRCODE_SIZE - height) /2;
        graphics2D.drawImage(src,x,y,width,height,null);
        Shape shape = new RoundRectangle2D.Float(x,y,width,height,6,6);
        graphics2D.setStroke(new BasicStroke(3f));
        graphics2D.draw(shape);
        graphics2D.dispose();
    }
    public static String encode(String content,String imgPath,String destPath,boolean needCompress) throws  Exception{
        BufferedImage image = createImage(content, imgPath, needCompress);
        mkdirs(destPath);
        String file = new Random().nextInt(99999999) + ".jpg";
        ImageIO.write(image, FORMAT_NAME, new File(destPath + "/" + file));
        return file;
    }

    /**
     *
     * @param content  url内容
     * @param logoImgPath 二维码logo图片地址
     * @param destPath   生成的二维码位置
     * @param fileName   二维码文件名
     * @param needCompress 是否需要压缩
     * @return
     * @throws Exception
     */
    public static File encodeAndGetFile(String content, String logoImgPath, String destPath, String fileName,
                                        boolean needCompress) throws Exception {
        BufferedImage image = createImage(content, logoImgPath, needCompress);
        mkdirs(destPath);
        if (null == fileName || fileName.length() == 0) {
            fileName = new Random().nextInt(99999999) + ".jpg";
        }
        File file = new File(destPath + "/" + fileName);
        ImageIO.write(image, FORMAT_NAME, file);
        return file;
    }

    /**
     * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
     * @param destPath 存放目录
     */
    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }
    /**
     * 生成二维码(内嵌LOGO)
     * @throws Exception
     */
    public static void encode(String content, String imgPath, String destPath) throws Exception {
        encode(content, imgPath, destPath, false);
    }

    /**
     * 生成二维码
     * @throws Exception
     */
    public static void encode(String content, String destPath, boolean needCompress) throws Exception {
        encode(content, null, destPath, needCompress);
    }

    /**
     * 生成二维码
     * @throws Exception
     */
    public static void encode(String content, String destPath) throws Exception {
        encode(content, null, destPath, false);
    }

    /**
     * 生成二维码(内嵌LOGO)
     * @throws Exception
     */
    public static void encode(String content, String imgPath, OutputStream output, boolean needCompress)
            throws Exception {
        BufferedImage image = createImage(content, imgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    /**
     * 生成二维码
     * @throws Exception
     */
    public static void encode(String content, OutputStream output) throws Exception {
        encode(content, null, output, false);
    }

    /**
     * 解析二维码
     * @throws Exception
     */
//    public static String decode(File file) throws Exception {
//        BufferedImage image;
//        image = ImageIO.read(file);
//        if (image == null) {
//            return null;
//        }
//        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
//        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//        Result result;
//        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
//        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
//        result = new MultiFormatReader().decode(bitmap, hints);
//        String resultStr = result.getText();
//        System.out.println(resultStr);
//        return resultStr;
//    }

    /**
     * 解析二维码
     * @throws Exception
     */
//    public static String decode(String path) throws Exception {
//        return decode(new File(path));
//    }

    /**
     * 获取验证码url
     *
     * @return
     */
    public static String getLogoPath() {
        String codeUrl = "";
        try {
            //codeUrl = PropertiesUtil.getContentByName("qrcode_logo_path");// 二维码logo地址
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codeUrl;
    }
    public static void main(String[] args){
        String url = "http://2539455mx2.qicp.vip/dlbz/search?name=%E8%8B%B9%E6%9E%9C";
        try{
            encodeAndGetFile(url,"E:\\FileTest\\img\\url201.jpg","E:\\FileTest\\img\\","url"+new Random().nextInt(100)+1+".jpg",true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
