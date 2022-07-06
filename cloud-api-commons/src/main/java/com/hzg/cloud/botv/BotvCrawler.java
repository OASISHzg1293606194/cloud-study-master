package com.hzg.cloud.botv;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.hzg.cloud.botv
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-19 17:24
 */
public class BotvCrawler {

    private static final String BOTV_HOME_PAGE_PREFIX_URL = "https://bo99.tv/";

    private static Map<String, String> headers;

    static {
        headers = Maps.newHashMap();
    }

    public static void main(String[] args) {
        try {
            String tempSuffix = "forum-89-1.html";
            Connection connection = Jsoup.connect(BOTV_HOME_PAGE_PREFIX_URL + tempSuffix);
            Document document = connection.get();
            Elements elements = document.getElementsByClass("xst");
            for (int i = 0; i < elements.size(); i++) {
                System.out.println("================================================================");
                Element element = elements.get(i);
                System.out.println(element.outerHtml());
                String href = BOTV_HOME_PAGE_PREFIX_URL + element.attr("href");
                System.out.println(href);
                String text = element.text();
                if (StringUtils.isNotBlank(text)) {
                    String trimText = text.trim();
                    List<String> list = new LinkedList<>(Splitter.on(" ").splitToList(trimText));
                    System.out.println(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
