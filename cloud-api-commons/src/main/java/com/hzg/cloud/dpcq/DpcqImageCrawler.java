package com.hzg.cloud.dpcq;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hzg.cloud.dmzj.PrintToPdfUtil;
import org.apache.commons.collections.CollectionUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.hzg.cloud.dmzj
 * @Description: [https://www.doupocangq.com/]-爬取《斗破苍穹》漫画图片
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-02 14:20
 */
public class DpcqImageCrawler implements Runnable {

    private static final String DPCQ_HOME_PAGE_URL = "https://www.doupocangq.com/manhua/doupocangqiong/";
    private static final String DPCQ_BASE_IMG_URL = "https://img.dubuxiaoyao.com:30020";
    private static final String BASE_LOCAL_PATH = "C:/Users/Administrator/Downloads/斗破苍穹下载-新/";
    private static Map<String, String> headers;

    static {
        headers = Maps.newHashMap();
    }

    /**
     * 斗破苍穹-所有章节子页面地址集合
     */
    private static List<String> htmlPageUrlList = Lists.newArrayList();

    /**
     * 斗破苍穹-所有章节子页面地址与章节名称映射
     */
    private static Map<String, String> htmlPageUrlNameMapping = Maps.newHashMap();

    /**
     * 斗破苍穹-所有章节子页面地址与章节页数映射
     */
    private static Map<String, Integer> htmlPageUrlChapterCountMapping = Maps.newHashMap();

    /**
     * 斗破苍穹-所有章节子页面地址与章节所有图片集合映射
     */
    private static Map<String, List<String>> htmlPageUrlChapterImageListMapping = Maps.newHashMap();


    @Override
    public void run() {

    }

    public static void main(String[] args) throws IOException {
        // obtainAllHtmlPageUrl();
        // System.out.println("Part One successful.");
        // obtainAllHtmlPageChapterImageUrl();
        // System.out.println("Part Two successful.");
        //
        // for (String pageUrl : htmlPageUrlList) {
        //     String chapterName = htmlPageUrlNameMapping.get(pageUrl);
        //     List<String> imageList = htmlPageUrlChapterImageListMapping.get(pageUrl);
        //     System.out.println("=============================" + pageUrl + " 图片下载开始=============================");
        //     for (String imageUrl : imageList) {
        //         downloadImage(imageUrl, BASE_LOCAL_PATH, chapterName);
        //     }
        //     System.out.println("=============================" + pageUrl + " 图片下载结束=============================");
        // }
        // System.out.println("Part Three successful.");

        batchMakeUpImageToPdf(BASE_LOCAL_PATH);
    }

    /**
     * 根据父级目录路径批量组装图片为PDF
     * <p/>
     *
     * @param baseLocalPath 父级目录路径
     * @return void
     * @author HuangZhiGao
     * @date 2021/11/18/018 9:57
     */
    private static void batchMakeUpImageToPdf(String baseLocalPath) {
        File file = new File(baseLocalPath);
        File[] files = file.listFiles();
        System.out.println("当前目录[" + baseLocalPath + "]下的子目录个数：" + files.length);
        for (int i = 0; i < files.length; i++) {
            File tempFile = files[i];
            if (tempFile.isDirectory()) {
                boolean continueFlag = false;
                List<File> listFiles = Arrays.asList(tempFile.listFiles());
                Collections.reverse(listFiles);
                for (File factFile : listFiles) {
                    if (factFile.getName().contains(".pdf")) {
                        continueFlag = true;
                        break;
                    }
                }
                if (!continueFlag) {
                    String tempFilePath = tempFile.getPath();
                    String tempPath = tempFilePath.replace("\\", "/") + "/";
                    String pdfName = "《斗破苍穹-" + tempFilePath.substring(tempFilePath.lastIndexOf("\\") + 1) + "》.pdf";
                    System.out.println("=============================>子目录[" + tempPath + "]图片转换PDF开始=============================");
                    long time1 = System.currentTimeMillis();
                    PrintToPdfUtil.imageToPdf(tempPath, tempPath + pdfName);
                    long time2 = System.currentTimeMillis();
                    int time = (int) ((time2 - time1) / 1000);
                    System.out.println("=============================>" + tempPath + pdfName);
                    System.out.println("=============================>执行了:" + time + "秒");
                    System.out.println("=============================>子目录[" + tempPath + "]图片转换PDF结束=============================");
                }
            }
        }
    }

    /**
     * 初始化获取所有章节子页面地址集合及与章节名称映射
     * <p/>
     * 初始化htmlPageUrlList、htmlPageUrlNameMapping
     * <p/>
     *
     * @return void
     * @author HuangZhiGao
     * @date 2021/11/17/017 15:38
     */
    private static void obtainAllHtmlPageUrl() {
        try {
            Connection connect = Jsoup.connect(DPCQ_HOME_PAGE_URL);
            Document document = connect.get();
            Element element = document.getElementById("play_0");

            Node ulNode = null;
            List<Node> divChildNodesList = element.childNodes();
            for (int i = 0; i < divChildNodesList.size(); i++) {
                Node node = divChildNodesList.get(i);
                if (node.outerHtml().contains("<ul>")) {
                    ulNode = node;
                    System.out.println("================================================================");
                    System.out.println(node);
                }
            }

            List<Node> ulChildNodes = ulNode.childNodes();
            for (Node liNode : ulChildNodes) {
                if (liNode.outerHtml().contains("</li>")) {
                    List<Node> liChildNodes = liNode.childNodes();
                    for (Node aNode : liChildNodes) {
                        if (aNode.outerHtml().contains("</a>")) {
                            if (aNode.hasAttr("href") && aNode.hasAttr("title")) {
                                String href = aNode.attr("href");
                                String title = aNode.attr("title").trim();
                                int index = href.lastIndexOf("/") + 1;
                                String urlNumber = href.substring(index);
                                System.out.println("================================================================");
                                System.out.println(urlNumber + "\t\t" + title);
                                htmlPageUrlList.add(urlNumber);
                                htmlPageUrlNameMapping.put(urlNumber, title);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化获取所有章节子页面地址与章节页数映射、与章节所有图片集合映射
     * <p/>
     * 初始化htmlPageUrlChapterCountMapping、htmlPageUrlChapterImageListMapping
     * <p/>
     *
     * @return void
     * @author HuangZhiGao
     * @date 2021/11/17/017 15:42
     */
    private static void obtainAllHtmlPageChapterImageUrl() {
        if (!CollectionUtils.isEmpty(htmlPageUrlList)) {
            for (String pageSuffix : htmlPageUrlList) {
                try {
                    String tempUrl = DPCQ_HOME_PAGE_URL + pageSuffix;
                    Connection connect = Jsoup.connect(tempUrl);
                    Document document = connect.get();
                    Elements elements = document.getElementsByClass("manga-page");
                    for (Element element : elements) {
                        // 获取章节子页面的章节页数
                        List<Node> childNodesList = element.childNodes();
                        if (!CollectionUtils.isEmpty(childNodesList)) {
                            for (Node node : childNodesList) {
                                if (node.outerHtml().contains("P")) {
                                    String outerHtml = node.outerHtml();
                                    Integer index1 = outerHtml.lastIndexOf("/") + 1;
                                    Integer index2 = outerHtml.lastIndexOf("P");
                                    String count = outerHtml.substring(index1, index2);
                                    htmlPageUrlChapterCountMapping.put(pageSuffix, Integer.parseInt(count));
                                }
                            }
                        }
                    }

                    // 获取章节子页面的所有章节图片集合
                    Elements scriptElements = document.getElementsByTag("script");
                    for (Element element : scriptElements) {
                        if (element.outerHtml().contains("<script>")) {
                            List<Node> childNodes = element.childNodes();
                            for (Node node : childNodes) {
                                if (node.outerHtml().contains("parseJSON")) {
                                    String outerHtml = node.outerHtml();
                                    int index1 = outerHtml.lastIndexOf("[");
                                    int index2 = outerHtml.lastIndexOf("]") + 1;
                                    String array = outerHtml.substring(index1, index2);
                                    List<String> originalImageList = JSONArray.parseArray(array, String.class);
                                    if (!CollectionUtils.isEmpty(originalImageList)) {
                                        List<String> chapterImageList = Lists.newArrayList();
                                        originalImageList.forEach(imageUrl -> {
                                            chapterImageList.add(DPCQ_BASE_IMG_URL + imageUrl);
                                        });
                                        htmlPageUrlChapterImageListMapping.put(pageSuffix, chapterImageList);
                                        System.out.println(chapterImageList);
                                    }
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 下载网络图片到指定文件夹目录
     * <p/>
     *
     * @param imageUrl    网络图片地址
     * @param filePath    文件路径
     * @param chapterName 章节名称
     * @return void
     * @author HuangZhiGao
     * @date 2021/11/17/017 16:15
     */
    public static void downloadImage(String imageUrl, String filePath, String chapterName) {
        String filePathSuffix = chapterName;
        String fileSuffix = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.length());

        try {
            URL url = new URL(imageUrl);
            URLConnection connection = url.openConnection();
            if (headers != null && !headers.isEmpty()) {
                // 设置头信息
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
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath + filePathSuffix + "/" + fileSuffix));
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

}
