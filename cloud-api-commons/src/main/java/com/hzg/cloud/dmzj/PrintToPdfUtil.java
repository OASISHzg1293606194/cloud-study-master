package com.hzg.cloud.dmzj;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Package: com.hzg.cloud.dmzj
 * @Description: 图片组合为PDF工具类
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-02 14:36
 */
public class PrintToPdfUtil {

    /**
     * @param imageFolderPath 图片文件夹地址
     * @param pdfPath         PDF文件保存地址
     * @author HuangZhiGao
     * @date 2021/11/2/002 14:38
     */
    public static void imageToPdf(String imageFolderPath, String pdfPath) {
        try {
            // 输入流
            FileOutputStream fileOutputStream = new FileOutputStream(pdfPath);
            // 创建文档
            Document document = new Document(null, 0, 0, 0, 0);
            // 写入PDF文档
            PdfWriter.getInstance(document, fileOutputStream);

            // 图片路径
            String imagePath = null;
            // 读取图片流
            BufferedImage bufferedImage = null;
            // 实例化图片
            Image image = null;

            // 获取图片文件夹对象
            File file = new File(imageFolderPath);
            File[] files = file.listFiles();
            // 循环获取图片文件夹内的图片
            for (File file1 : files) {
                if (file1.getName().endsWith(".png")
                        || file1.getName().endsWith(".jpg")
                        || file1.getName().endsWith(".gif")
                        || file1.getName().endsWith(".jpeg")
                        || file1.getName().endsWith(".tif")) {
                    try {
                        imagePath = imageFolderPath + file1.getName();
                        System.out.println(file1.getName());
                        // 读取图片流
                        File imageFile = new File(imagePath);
                        imageFile.setReadable(Boolean.TRUE);
                        bufferedImage = ImageIO.read(imageFile);
                        // 根据图片大小设置文档大小
                        document.setPageSize(
                                new Rectangle(bufferedImage.getWidth(), bufferedImage.getHeight())
                        );
                        // 实例化图片
                        image = Image.getInstance(imagePath);
                        // 添加图片到文档
                        document.open();
                        document.add(image);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                }
            }
            // 关闭文档
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        imageToPdf("C:/Users/Administrator/Downloads/动漫之家下载/国王排名/第116话/",
                "C:/Users/Administrator/Downloads/动漫之家下载/国王排名/第116话/《国王排名-116话》.pdf");
        long time2 = System.currentTimeMillis();
        int time = (int) ((time2 - time1) / 1000);
        System.out.println("==========>执行了:" + time + "秒");
    }

}
