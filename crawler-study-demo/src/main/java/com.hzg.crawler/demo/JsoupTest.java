package com.hzg.crawler.demo;

import org.apache.commons.codec.CharEncoding;
import org.apache.http.HttpStatus;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Package: com.hzg.crawler.demo
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-15 14:27
 */
public class JsoupTest {

    /**
     * 一般可以认为"/"的作用等同于"\\"
     * 在java中路径一般用"/"
     * 因为java是跨平台的，"\"(在java代码里应该是\\)是Windows环境下的路径分隔符，Linux和Unix下都是用“/”
     * 而Windows也能识别“/”，所以最好用“/”
     */
    /**
     * 《春江花月夜》
     */
    private static final String DEFAULT_URL = "https://so.gushiwen.cn/search.aspx?value=%E6%84%BF%E9%80%90%E6%9C%88%E5%8D%8E%E6%B5%81%E7%85%A7%E5%90%9B";


    public static void main(String[] args) throws IOException {
        /*demo1();*/
        demo2();
    }

    private static void demo1() throws IOException {
        Connection connect = Jsoup.connect(DEFAULT_URL);
        Document document = connect.get();
        System.out.println(document.html());
        System.out.println("================================================================");
        Elements elements = document.getElementsByClass("contson");
        System.out.println(elements);
    }

    private static void demo2() throws IOException {
        Response response = Jsoup.connect(DEFAULT_URL).method(Method.GET).execute();
        System.out.println("请求的URL：" + response.url());
        System.out.println("响应结果码：" + response.statusCode());
        System.out.println("响应类型为：" + response.contentType());
        System.out.println("响应信息为：" + response.statusMessage());
        if (HttpStatus.SC_OK == response.statusCode()) {
            System.out.println("================================================================");
            Document document = response.parse();
            /** System.out.println(document.html()); */
            // 这里的html内容和Document数据是一样的，但Document是经过格式化缩进的
            String html = new String(response.bodyAsBytes(), CharEncoding.UTF_8);
            System.out.println(html);
        }
    }

    private static void demo3() {
    }

    private static void demo4() {
    }

}
