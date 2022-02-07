package com.hzg.cloud.dmzj;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Package: com.hzg.cloud.dmzj
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-03 13:42
 */
public class CartoonImageCrawlerMain {

    public static void main(String[] args) {
        Map<String, Object> cartoonInfo = Maps.newHashMap();
        cartoonInfo.put(CartoonImageCrawler.ROUND_KEY, "第116话");
        cartoonInfo.put(CartoonImageCrawler.CHAPTER_COUNT_KEY, "16");

        CartoonImageCrawler crawler = new CartoonImageCrawler();
        crawler.setCartoonInfo(cartoonInfo);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(crawler);
        executorService.shutdown();
    }

}
