package com.hzg.cloud.lmts;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hzg.cloud.dmzj.PrintToPdfUtil;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Package: com.hzg.cloud.lmts
 * @Description: [https://manhua.dmzj.com/lmts/]-爬取《冷面天使》漫画图片
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-04 13:54
 */
public class LmtsImageCrawler {

    private static final String LMTS_HOME_PAGE_URL = "https://manhua.dmzj.com/lmts/";
    private static final String BASE_LOCAL_PATH = "C:/Users/Administrator/Downloads/冷面天使/";
    private static Map<String, String> headers;

    static {
        headers = Maps.newHashMap();
    }

    /**
     * 冷面天使-所有章节子页面地址集合
     */
    private static List<String> htmlPageUrlList = Lists.newArrayList();

    /**
     * 冷面天使-所有章节子页面地址与章节名称映射
     */
    private static Map<String, String> htmlPageUrlNameMapping = Maps.newLinkedHashMap();

    /**
     * 冷面天使-所有章节子页面地址与章节所有图片集合映射
     */
    private static Map<String, List<String>> htmlPageUrlChapterImageListMapping = Maps.newHashMap();


    public static void main(String[] args) {
        // obtainAllHtmlPageUrl();
        // System.out.println("Part One successful.");
        // obtainAllHtmlPageChapterImageUrl();
        // System.out.println("Part Two successful.");
        //
        // for (String pageUrl : htmlPageUrlList) {
        //     String chapterName = htmlPageUrlNameMapping.get(pageUrl);
        //     List<String> imageList = htmlPageUrlChapterImageListMapping.get(pageUrl);
        //     System.out.println("=============================" + pageUrl + " 图片下载开始=============================");
        //     try {
        //         for (String imageUrl : imageList) {
        //             downloadImage(imageUrl, BASE_LOCAL_PATH, chapterName);
        //         }
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }
        //     System.out.println("=============================" + pageUrl + " 图片下载结束=============================");
        // }
        // System.out.println("Part Three successful.");

        batchMakeUpImageToPdf(BASE_LOCAL_PATH);
    }

    public static void obtainAllHtmlPageUrl() {
        try {
            Connection connection = Jsoup.connect(LMTS_HOME_PAGE_URL);
            Document document = connection.get();
            Elements elements = document.getElementsByClass("cartoon_online_border");
            Element element = elements.get(0);
            System.out.println("================================================================");
            System.out.println(element.outerHtml());

            Node ulNode = null;
            List<Node> divChildNodesList = element.childNodes();
            for (int i = 0; i < divChildNodesList.size(); i++) {
                Node node = divChildNodesList.get(i);
                if (node.outerHtml().contains("<ul>")) {
                    ulNode = node;
                    System.out.println("================================================================");
                    System.out.println(node.outerHtml());
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void obtainAllHtmlPageChapterImageUrl() {
        if (MapUtils.isNotEmpty(htmlPageUrlNameMapping)) {
            List<String> htmlPageUrlList = htmlPageUrlNameMapping.keySet().stream().collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(htmlPageUrlList)) {
                for (String pageSuffix : htmlPageUrlList) {
                    try {
                        String tempUrl = LMTS_HOME_PAGE_URL + pageSuffix + "#@page=1";
                        Connection connect = Jsoup.connect(tempUrl);
                        Document document = connect.get();
                        Elements elements = document.getElementsByTag("script");
                        String js = "";
                        for (Element element : elements) {
                            if (element.outerHtml().contains("eval(function(")) {
                                js = element.outerHtml()
                                        .replace("<script type=\"text/javascript\">", "")
                                        .replace("</script>", "");
                            }
                        }

                        List<String> resultList = executeJavaScriptMethod(js);
                        htmlPageUrlChapterImageListMapping.put(pageSuffix, resultList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
                    String pdfName = "《" + tempFilePath.substring(tempFilePath.lastIndexOf("\\") + 1) + "》.pdf";
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
     * Java中执行JavaScript代码
     * <p/>
     *
     * @param js JavaScript代码
     * @return java.util.List<java.lang.String>
     * @author HuangZhiGao
     * @date 2022/3/4/004 17:06
     */
    private static List<String> executeJavaScriptMethod(String js) {
        List<String> imgUrlList = Lists.newArrayList();
        try {
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
            scriptEngine.eval(js);
            Bindings bindings = scriptEngine.getBindings(ScriptContext.ENGINE_SCOPE);
            ScriptObjectMirror scriptObjectMirror = (ScriptObjectMirror) scriptEngine.eval("eval(pages)");
            String[] resultArray = scriptObjectMirror.to(String[].class);
            List<String> resultList = new ArrayList(Arrays.asList(resultArray));
            for (String imgUrlSuffix : resultList) {
                String imgUrl = "https://images.dmzj.com/" + imgUrlSuffix;
                imgUrlList.add(imgUrl);
            }
            System.out.println("================================================================");
            System.out.println(imgUrlList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgUrlList;
    }


}
