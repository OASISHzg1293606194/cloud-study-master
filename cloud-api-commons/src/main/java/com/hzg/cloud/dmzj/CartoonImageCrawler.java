package com.hzg.cloud.dmzj;

import com.google.common.collect.Maps;
import lombok.Setter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.hzg.cloud.dmzj
 * @Description: 动漫之家[https://m.dmzj.com/]-爬取《国王排名》漫画图片
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-02 14:20
 */
public class CartoonImageCrawler implements Runnable {

    @Setter
    private Map<String, Object> cartoonInfo;

    public static final String ROUND_KEY = "round_key";
    public static final String CHAPTER_COUNT_KEY = "chapter_count_key";
    private static final String IMAGE_URL_ROUND = "https://images.dmzj.com/g/国王排名/" + ROUND_KEY + "/" + CHAPTER_COUNT_KEY + ".jpg";
    private static final String IMAGE_URL_SUB_ROUND = "https://images.dmzj.com/g/国王排名/" + ROUND_KEY + "/116%20(" + CHAPTER_COUNT_KEY + ")%20%E6%8B%B7%E8%B4%9D.jpg";
    private static final String BASE_LOCAL_PATH = "C:/Users/Administrator/Downloads/动漫之家下载/";

    private static Map<String, String> headers;

    static {
        headers = Maps.newHashMap();
        headers.put("Accept", "image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9");
        headers.put("Cache-Control", "no-cache");
        headers.put("Connection", "keep-alive");
        headers.put("Cookie", "UM_distinctid=17cdf3874b6265-035e44bce69307-b7a1438-100200-17cdf3874b71ba");
        headers.put("Host", "images.dmzj.com");
        headers.put("Pragma", "no-cache");
        headers.put("Referer", "https://m.dmzj.com/");
        headers.put("Sec-Fetch-Dest", "image");
        headers.put("Sec-Fetch-Mode", "no-cors");
        headers.put("Sec-Fetch-Site", "same-site");
        headers.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");
    }

    @Override
    public void run() {
        String round = this.cartoonInfo.get(ROUND_KEY).toString();
        Integer chapterCount = Integer.parseInt(this.cartoonInfo.get(CHAPTER_COUNT_KEY).toString());
        String imageUrl = IMAGE_URL_SUB_ROUND.replace(ROUND_KEY, round);
        for (int i = 1; i <= chapterCount; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // String chapterCountStr = String.format("%04d", i);
            // String chapterCountStr = String.format("%02d", i);
            String chapterCountStr = String.valueOf(i);
            downloadImage(imageUrl.replace(CHAPTER_COUNT_KEY, chapterCountStr), BASE_LOCAL_PATH);
        }
    }

    public void downloadImage(String imageUrl, String filePath) {
        String filePathSuffix = imageUrl.substring(imageUrl.indexOf("g/") + 2, imageUrl.lastIndexOf("/") + 1);
        String fileSuffix = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.length());

        try {
            URL url = new URL(imageUrl);
            URLConnection connection = url.openConnection();
            if (headers != null && !headers.isEmpty()) {
                //设置头信息
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            connection.setDoInput(true);
            InputStream inputStream = connection.getInputStream();
            // 获取输入流
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            // 创建文件流
            File file = new File(filePath + filePathSuffix);
            if (!file.exists()) {
                file.mkdirs();
            }
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath + filePathSuffix + fileSuffix));
            // 缓冲字节数组
            byte[] data = new byte[2048];
            int length;
            while ((length = bufferedInputStream.read(data)) != -1) {
                bufferedOutputStream.write(data, 0, length);
                bufferedOutputStream.flush();
            }
            System.out.println("正在执行下载任务：当前正在下载图片====> " + fileSuffix);
            bufferedOutputStream.close();
            bufferedInputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将URL编码转换成中文
     * <p/>
     *
     * @param urlStr
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2021/11/3/003 12:43
     */
    public static String decode(String urlStr) {
        try {
            urlStr = URLDecoder.decode(urlStr, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlStr;
    }

    public static void main(String[] args) {
        String decodeUrl = decode("https://images.dmzj.com/g/%E5%9B%BD%E7%8E%8B%E6%8E%92%E5%90%8D/%E7%AC%AC116%E8%AF%9D/116%20(2)%20%E6%8B%B7%E8%B4%9D.jpg");
        System.out.println(decodeUrl);

        // 卷
        // System.out.println(decodeUrl.substring(decodeUrl.indexOf("名/") + 2, decodeUrl.lastIndexOf("/")));
        // String substring = decodeUrl.substring(decodeUrl.lastIndexOf("/") + 1, decodeUrl.lastIndexOf("."));
        // System.out.println(substring.replaceAll("^(0+)", ""));

        // 话
        System.out.println(decodeUrl.substring(decodeUrl.indexOf("名/") + 2, decodeUrl.lastIndexOf("/")));
        String substring = decodeUrl.substring(decodeUrl.lastIndexOf("(") + 1, decodeUrl.lastIndexOf(")"));
        System.out.println(substring);
    }

}
