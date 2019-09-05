package com.viking.MySpringBoot.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Viking on 2019/9/5
 * 多文件打包为zip
 */
public class Files2ZipUtil {
    /**
     * 将指定链接下的文件保存到zip压缩包
     * @param urls 文件链接
     * @param response 响应体对象
     * @throws IOException 异常
     */
    public void exportImgZip(List<String> urls, HttpServletResponse response) throws IOException {
        //1.拿到对应图片地址的url数组
        //2.开始批量下载功能
        String downloadFilename = "图片打包导出_"+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))+".zip";//文件的名称
        downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");//转换中文否则可能会产生乱码
        response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
        response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);// 设置压缩包默认显示的文件名
        ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
        for (String fileUrl : urls){
            try {
                URL url = new URL(fileUrl);
                InputStream fis = url.openConnection().getInputStream();
                zos.putNextEntry(new ZipEntry(UUID.randomUUID().toString()+".jpg"));
                byte[] buffer = new byte[1024];
                int r = 0;
                while ((r = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, r);
                }
                fis.close();
            }catch (FileNotFoundException e){
                System.out.println("图片未找到");
            }

        }
        zos.flush();
        zos.close();
    }

    /**
     @param files 需要被压缩的文件
     @param zipFile 压缩后的目标zip文件
     */
    public static void zip(Path[] files, Path zipFile) throws IOException {
        OutputStream outputStream = Files.newOutputStream(zipFile, StandardOpenOption.CREATE_NEW);
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        for (Path file : files) {
            InputStream inputStream = Files.newInputStream(file);
            zipOutputStream.putNextEntry(new ZipEntry(file.getFileName().toString()));
            int len;
            byte[] buffer = new byte[1024 * 10];
            while ((len = inputStream.read(buffer)) > 0) {
                zipOutputStream.write(buffer, 0, len);
            }
            inputStream.close();
        }
        zipOutputStream.closeEntry();
        zipOutputStream.close();
        outputStream.close();
    }

    public static void main(String[] args) {
        String first = "E://FileTest";
        Path[] paths = {Paths.get(first,"testFile01.txt"),Paths.get(first+"//music","Please Don't Go.mp3")
                ,Paths.get(first,"testFile03.txt"),Paths.get(first,"testFile04.txt")};
        Path zipFile = Paths.get(first,"zipTest01.zip");
        try {
            zip(paths,zipFile);
            System.out.println("操作成功");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("操作失败");
        }
    }

}
