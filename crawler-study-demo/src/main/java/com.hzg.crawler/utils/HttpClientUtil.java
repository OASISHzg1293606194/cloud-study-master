package com.hzg.crawler.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.CharEncoding;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.helper.StringUtil;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * HttpClient工具类<br/>
 * 注：异常统一向上抛出由最终调用方捕获处理<br/>
 *
 * @author HuangZhiGao
 * @date 2020-09-11 17:19
 */
public class HttpClientUtil {

    /**
     * 空JSON
     */
    private static final String EMPTY_JSON = "{}";

    /**
     * Java class文件后缀(不带.)
     */
    private static final String CLASS_SUFFIX = "class";

    /**
     * HTTP请求成功的状态码(2xx)
     */
    private static List<Integer> SUCCESS_HTTP_STATUS_CODE;

    /**
     * 默认RequestConfig
     */
    private static RequestConfig defaultRequestConfig;

    static {
        // 默认设置连接超时时间10秒
        defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .setSocketTimeout(10000)
                .build();

        SUCCESS_HTTP_STATUS_CODE = new ArrayList<>();
        SUCCESS_HTTP_STATUS_CODE.add(HttpStatus.SC_OK);
        SUCCESS_HTTP_STATUS_CODE.add(HttpStatus.SC_CREATED);
        SUCCESS_HTTP_STATUS_CODE.add(HttpStatus.SC_ACCEPTED);
        SUCCESS_HTTP_STATUS_CODE.add(HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION);
        SUCCESS_HTTP_STATUS_CODE.add(HttpStatus.SC_NO_CONTENT);
        SUCCESS_HTTP_STATUS_CODE.add(HttpStatus.SC_RESET_CONTENT);
        SUCCESS_HTTP_STATUS_CODE.add(HttpStatus.SC_PARTIAL_CONTENT);
        SUCCESS_HTTP_STATUS_CODE.add(HttpStatus.SC_MULTI_STATUS);
    }

    /**
     * 获取默认的RequestConfig<br/>
     * 默认设置连接超时时间10秒<br/>
     *
     * @return org.apache.http.client.config.RequestConfig
     * @author HuangZhiGao
     * @date 2021/7/29/029 10:42
     */
    public static RequestConfig getDefaultRequestConfig() {
        return defaultRequestConfig;
    }

    /**
     * 校验HTTP状态判断请求是否成功(2xx)
     * <p/>
     *
     * @param httpStatusCode HTTP状态码
     * @return boolean [true:HTTP请求成功; false:HTTP请求失败]
     * @author HuangZhiGao
     * @date 2021/7/29/029 11:15
     */
    public static boolean chkHttpStatusCodeIsOK(int httpStatusCode) {
        if (SUCCESS_HTTP_STATUS_CODE.contains(httpStatusCode)) {
            return Boolean.TRUE.booleanValue();
        }
        return Boolean.FALSE.booleanValue();
    }

    /**
     * 设置地址栏参数<br/>
     *
     * @param uriBuilder
     * @param urlParamsMap 地址栏参数map
     * @return org.apache.http.client.utils.URIBuilder
     * @author HuangZhiGao
     * @date 2020/9/14 11:24
     */
    public static URIBuilder setUriBuilderParameters(URIBuilder uriBuilder, TreeMap<String, String> urlParamsMap) {
        if (!CollectionUtils.isEmpty(urlParamsMap)) {
            Set<Map.Entry<String, String>> entrySet = urlParamsMap.entrySet();
            for (Map.Entry entry : entrySet) {
                if (entry != null) {
                    if (entry.getValue() == null) {
                        continue;
                    }
                    String tempKey = (String) entry.getKey();
                    String tempValue = (String) entry.getValue();
                    uriBuilder.addParameter(tempKey, tempValue);
                }
            }
        }
        return uriBuilder;
    }

    /**
     * 追加设置GET请求 请求头组<br/>
     *
     * @param httpGet
     * @param headersMap 请求头组map
     * @return org.apache.http.client.methods.HttpGet
     * @author HuangZhiGao
     * @date 2020/9/14 11:25
     */
    public static HttpGet addAllHeadersOfGet(HttpGet httpGet, TreeMap<String, String> headersMap) {
        if (!CollectionUtils.isEmpty(headersMap)) {
            Set<Map.Entry<String, String>> entrySet = headersMap.entrySet();
            for (Map.Entry entry : entrySet) {
                if (entry != null) {
                    if (entry.getValue() == null) {
                        continue;
                    }
                    String tempKey = (String) entry.getKey();
                    String tempValue = (String) entry.getValue();
                    httpGet.addHeader(tempKey, tempValue);
                }
            }
        }
        return httpGet;
    }

    /**
     * 追加设置POST请求 请求头组<br/>
     *
     * @param httpPost
     * @param headersMap 请求头组map
     * @return org.apache.http.client.methods.HttpGet
     * @author HuangZhiGao
     * @date 2020/9/14 11:25
     */
    public static HttpPost addAllHeadersOfPost(HttpPost httpPost, TreeMap<String, String> headersMap) {
        if (!CollectionUtils.isEmpty(headersMap)) {
            Set<Map.Entry<String, String>> entrySet = headersMap.entrySet();
            for (Map.Entry entry : entrySet) {
                if (entry != null) {
                    if (entry.getValue() == null) {
                        continue;
                    }
                    String tempKey = (String) entry.getKey();
                    String tempValue = (String) entry.getValue();
                    httpPost.addHeader(tempKey, tempValue);
                }
            }
        }
        return httpPost;
    }

    /**
     * 追加设置PATCH请求 请求头组<br/>
     *
     * @param httpPatch
     * @param headersMap 请求头组map
     * @return org.apache.http.client.methods.HttpPatch
     * @author HuangZhiGao
     * @date 2021/7/29/029 10:34
     */
    public static HttpPatch addAllHeadersOfPatch(HttpPatch httpPatch, TreeMap<String, String> headersMap) {
        if (!CollectionUtils.isEmpty(headersMap)) {
            Set<Map.Entry<String, String>> entrySet = headersMap.entrySet();
            for (Map.Entry entry : entrySet) {
                if (entry != null) {
                    if (entry.getValue() == null) {
                        continue;
                    }
                    String tempKey = (String) entry.getKey();
                    String tempValue = (String) entry.getValue();
                    httpPatch.addHeader(tempKey, tempValue);
                }
            }
        }
        return httpPatch;
    }

    /**
     * 追加设置DELETE请求 请求头组<br/>
     *
     * @param httpDelete
     * @param headersMap 请求头组map
     * @return org.apache.http.client.methods.HttpDelete
     * @author HuangZhiGao
     * @date 2021/7/29/029 12:04
     */
    public static HttpDelete addAllHeadersOfDelete(HttpDelete httpDelete, TreeMap<String, String> headersMap) {
        if (!CollectionUtils.isEmpty(headersMap)) {
            Set<Map.Entry<String, String>> entrySet = headersMap.entrySet();
            for (Map.Entry entry : entrySet) {
                if (entry != null) {
                    if (entry.getValue() == null) {
                        continue;
                    }
                    String tempKey = (String) entry.getKey();
                    String tempValue = (String) entry.getValue();
                    httpDelete.addHeader(tempKey, tempValue);
                }
            }
        }
        return httpDelete;
    }

    /**
     * 设置POST请求 请求体json格式参数<br/>
     *
     * @param httpPost
     * @param jsonParamsMap 请求体参数map
     * @return org.apache.http.client.methods.HttpPost
     * @author HuangZhiGao
     * @date 2020/9/14 11:26
     */
    public static HttpPost setPostJsonEntity(HttpPost httpPost, LinkedHashMap<String, Object> jsonParamsMap) throws UnsupportedEncodingException {
        if (!CollectionUtils.isEmpty(jsonParamsMap)) {
            StringEntity stringEntity = new StringEntity(JSON.toJSONString(jsonParamsMap), Charset.forName(CharEncoding.UTF_8));
            stringEntity.setContentType(MediaType.APPLICATION_JSON_VALUE);
            stringEntity.setContentEncoding(CharEncoding.UTF_8);
            httpPost.setEntity(stringEntity);
        }
        return httpPost;
    }

    /**
     * 设置POST请求 请求体json格式参数<br/>
     *
     * @param httpPost
     * @param jsonStr  请求体参数json字符串
     * @return org.apache.http.client.methods.HttpPost
     * @author HuangZhiGao
     * @date 2020-11-18 13:40:58
     */
    public static HttpPost setPostJsonStrEntity(HttpPost httpPost, String jsonStr) throws UnsupportedEncodingException {
        if (jsonStr != null && !"".equals(jsonStr)) {
            StringEntity stringEntity = new StringEntity(jsonStr, Charset.forName(CharEncoding.UTF_8));
            stringEntity.setContentType(MediaType.APPLICATION_JSON_VALUE);
            stringEntity.setContentEncoding(CharEncoding.UTF_8);
            httpPost.setEntity(stringEntity);
        }
        return httpPost;
    }

    /**
     * 设置POST请求 请求体form表单格式参数<br/>
     *
     * @param httpPost
     * @param formParamsMap 请求体form表单参数map
     * @return org.apache.http.client.methods.HttpPost
     * @author HuangZhiGao
     * @date 2020/9/14 11:26
     */
    public static HttpPost setPostFormEntity(HttpPost httpPost, LinkedHashMap<String, String> formParamsMap) throws UnsupportedEncodingException {
        if (!CollectionUtils.isEmpty(formParamsMap)) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : formParamsMap.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            StringEntity stringEntity = new UrlEncodedFormEntity(list, CharEncoding.UTF_8);
            stringEntity.setContentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            stringEntity.setContentEncoding(CharEncoding.UTF_8);
            httpPost.setEntity(stringEntity);
        }
        return httpPost;
    }

    /**
     * 设置PATCH请求 请求体json格式参数<br/>
     *
     * @param httpPatch
     * @param jsonStr   请求体参数json字符串
     * @return org.apache.http.client.methods.HttpPatch
     * @author HuangZhiGao
     * @date 2021/7/29/029 11:46
     */
    public static HttpPatch setPatchJsonStrEntity(HttpPatch httpPatch, String jsonStr) throws UnsupportedEncodingException {
        if (jsonStr != null && !"".equals(jsonStr)) {
            StringEntity stringEntity = new StringEntity(jsonStr, Charset.forName(CharEncoding.UTF_8));
            stringEntity.setContentType(MediaType.APPLICATION_JSON_VALUE);
            stringEntity.setContentEncoding(CharEncoding.UTF_8);
            httpPatch.setEntity(stringEntity);
        }
        return httpPatch;
    }

    /**
     * 发起GET请求<br/>
     *
     * @param url 请求地址
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2020/9/14 11:27
     */
    public static String sendGet(String url) throws URISyntaxException, IOException {
        return sendGet(url, null, null, null);
    }

    /**
     * 发起GET请求，需要额外设置地址栏参数<br/>
     *
     * @param url          请求地址
     * @param urlParamsMap 地址栏参数map
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2020/9/14 11:27
     */
    public static String sendGet(String url, TreeMap<String, String> urlParamsMap) throws URISyntaxException, IOException {
        return sendGet(url, urlParamsMap, null, null);
    }

    /**
     * 发起GET请求，需要额外设置地址栏参数和请求头组以及RequestConfig<br/>
     *
     * @param url                 请求地址
     * @param urlParamsMap        地址栏参数map
     * @param headersMap          请求头组map
     * @param defineRequestConfig 自定义RequestConfig
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2020/9/14 11:27
     */
    public static String sendGet(String url, TreeMap<String, String> urlParamsMap, TreeMap<String, String> headersMap, RequestConfig defineRequestConfig) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String result = EMPTY_JSON;

        URIBuilder uriBuilder = new URIBuilder(url);
        if (!CollectionUtils.isEmpty(urlParamsMap)) {
            // 设置地址栏请求参数
            uriBuilder = setUriBuilderParameters(uriBuilder, urlParamsMap);
        }
        URI uri = uriBuilder.build();

        HttpGet httpGet = new HttpGet(uri);
        if (!CollectionUtils.isEmpty(headersMap)) {
            // 设置请求头组
            httpGet = addAllHeadersOfGet(httpGet, headersMap);
        }

        // 设置RequestConfig
        if (defineRequestConfig != null) {
            httpGet.setConfig(defineRequestConfig);
        } else {
            httpGet.setConfig(defaultRequestConfig);
        }

        httpResponse = httpClient.execute(httpGet);
        if (httpResponse.getEntity() != null) {
            result = EntityUtils.toString(httpResponse.getEntity(), CharEncoding.UTF_8);
        }
        doClose(httpClient, httpResponse);
        return result;
    }

    /**
     * 发起POST请求，请求体参数格式为JSON格式<br/>
     * 并且需要额外设置地址栏参数和请求头组<br/>
     *
     * @param url           请求地址
     * @param urlParamsMap  地址栏参数map
     * @param headersMap    请求头组map
     * @param jsonParamsMap 请求体参数map
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2020/9/14 11:43
     */
    public static String sendJsonPost(String url, TreeMap<String, String> urlParamsMap, TreeMap<String, String> headersMap, LinkedHashMap<String, Object> jsonParamsMap) throws URISyntaxException, IOException {
        return sendJsonPost(url, urlParamsMap, headersMap, jsonParamsMap, null);
    }

    /**
     * 发起POST请求，请求体参数格式为JSON格式<br/>
     * 并且需要额外设置地址栏参数和请求头组以及RequestConfig<br/>
     *
     * @param url                 请求地址
     * @param urlParamsMap        地址栏参数map
     * @param headersMap          请求头组map
     * @param jsonParamsMap       请求体参数map
     * @param defineRequestConfig 自定义RequestConfig
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2020/9/14 11:43
     */
    public static String sendJsonPost(String url, TreeMap<String, String> urlParamsMap, TreeMap<String, String> headersMap, LinkedHashMap<String, Object> jsonParamsMap, RequestConfig defineRequestConfig) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String result = EMPTY_JSON;

        URIBuilder uriBuilder = new URIBuilder(url);
        if (!CollectionUtils.isEmpty(urlParamsMap)) {
            // 设置地址栏请求参数
            uriBuilder = setUriBuilderParameters(uriBuilder, urlParamsMap);
        }
        URI uri = uriBuilder.build();

        HttpPost httpPost = new HttpPost(uri);
        if (!CollectionUtils.isEmpty(headersMap)) {
            // 设置请求头组
            httpPost = addAllHeadersOfPost(httpPost, headersMap);
        }

        // 设置RequestConfig
        if (defineRequestConfig != null) {
            httpPost.setConfig(defineRequestConfig);
        } else {
            httpPost.setConfig(defaultRequestConfig);
        }

        if (!CollectionUtils.isEmpty(jsonParamsMap)) {
            // 设置请求体json参数
            httpPost = setPostJsonEntity(httpPost, jsonParamsMap);
        }

        httpResponse = httpClient.execute(httpPost);
        if (httpResponse.getEntity() != null) {
            result = EntityUtils.toString(httpResponse.getEntity(), CharEncoding.UTF_8);
        }
        doClose(httpClient, httpResponse);
        return result;
    }

    /**
     * 发起POST请求，请求体参数格式为JSON格式<br/>
     * 并且需要额外设置地址栏参数和请求头组<br/>
     *
     * @param url          请求地址
     * @param urlParamsMap 地址栏参数map
     * @param headersMap   请求头组map
     * @param jsonStr      请求体参数json字符串
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2020/9/14 11:43
     */
    public static String sendJsonPost(String url, TreeMap<String, String> urlParamsMap, TreeMap<String, String> headersMap, String jsonStr) throws URISyntaxException, IOException {
        return sendJsonPost(url, urlParamsMap, headersMap, jsonStr, null);
    }

    /**
     * 发起POST请求，请求体参数格式为JSON格式<br/>
     * 并且需要额外设置地址栏参数和请求头组以及RequestConfig<br/>
     *
     * @param url                 请求地址
     * @param urlParamsMap        地址栏参数map
     * @param headersMap          请求头组map
     * @param jsonStr             请求体参数json字符串
     * @param defineRequestConfig 自定义RequestConfig
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2020/9/14 11:43
     */
    public static String sendJsonPost(String url, TreeMap<String, String> urlParamsMap, TreeMap<String, String> headersMap, String jsonStr, RequestConfig defineRequestConfig) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String result = EMPTY_JSON;

        URIBuilder uriBuilder = new URIBuilder(url);
        if (!CollectionUtils.isEmpty(urlParamsMap)) {
            // 设置地址栏请求参数
            uriBuilder = setUriBuilderParameters(uriBuilder, urlParamsMap);
        }
        URI uri = uriBuilder.build();

        HttpPost httpPost = new HttpPost(uri);
        if (!CollectionUtils.isEmpty(headersMap)) {
            // 设置请求头组
            httpPost = addAllHeadersOfPost(httpPost, headersMap);
        }

        // 设置RequestConfig
        if (defineRequestConfig != null) {
            httpPost.setConfig(defineRequestConfig);
        } else {
            httpPost.setConfig(defaultRequestConfig);
        }

        if (jsonStr != null && !"".equals(jsonStr)) {
            // 设置请求体json参数
            httpPost = setPostJsonStrEntity(httpPost, jsonStr);
        }

        httpResponse = httpClient.execute(httpPost);
        if (httpResponse.getEntity() != null) {
            result = EntityUtils.toString(httpResponse.getEntity(), CharEncoding.UTF_8);
        }
        doClose(httpClient, httpResponse);
        return result;
    }

    /**
     * 发起POST请求，请求体参数格式为FORM表单格式<br/>
     * 并且需要额外设置地址栏参数和请求头组<br/>
     *
     * @param url           请求地址
     * @param urlParamsMap  地址栏参数map
     * @param headersMap    请求头组map
     * @param formParamsMap 请求体form表单参数map
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2020-09-19 14:06:49
     */
    public static String sendFormPost(String url, TreeMap<String, String> urlParamsMap, TreeMap<String, String> headersMap, LinkedHashMap<String, String> formParamsMap) throws URISyntaxException, IOException {
        return sendFormPost(url, urlParamsMap, headersMap, formParamsMap, null);
    }

    /**
     * 发起POST请求，请求体参数格式为FORM表单格式<br/>
     * 并且需要额外设置地址栏参数和请求头组以及RequestConfig<br/>
     *
     * @param url                 请求地址
     * @param urlParamsMap        地址栏参数map
     * @param headersMap          请求头组map
     * @param formParamsMap       请求体form表单参数map
     * @param defineRequestConfig 自定义RequestConfig
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2020-09-19 14:06:49
     */
    public static String sendFormPost(String url, TreeMap<String, String> urlParamsMap, TreeMap<String, String> headersMap, LinkedHashMap<String, String> formParamsMap, RequestConfig defineRequestConfig) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String result = EMPTY_JSON;

        URIBuilder uriBuilder = new URIBuilder(url);
        if (!CollectionUtils.isEmpty(urlParamsMap)) {
            // 设置地址栏请求参数
            uriBuilder = setUriBuilderParameters(uriBuilder, urlParamsMap);
        }
        URI uri = uriBuilder.build();

        HttpPost httpPost = new HttpPost(uri);
        if (!CollectionUtils.isEmpty(headersMap)) {
            // 设置请求头组
            httpPost = addAllHeadersOfPost(httpPost, headersMap);
        }

        // 设置RequestConfig
        if (defineRequestConfig != null) {
            httpPost.setConfig(defineRequestConfig);
        } else {
            httpPost.setConfig(defaultRequestConfig);
        }

        if (!CollectionUtils.isEmpty(formParamsMap)) {
            // 设置请求体form表单参数
            httpPost = setPostFormEntity(httpPost, formParamsMap);
        }

        httpResponse = httpClient.execute(httpPost);
        if (httpResponse.getEntity() != null) {
            result = EntityUtils.toString(httpResponse.getEntity(), CharEncoding.UTF_8);
        }
        doClose(httpClient, httpResponse);
        return result;
    }

    /**
     * 发起PATCH请求，请求体参数格式为JSON格式<br/>
     * 需要额外设置地址栏参数和请求头组<br/>
     *
     * @param url          请求地址
     * @param urlParamsMap 地址栏参数map
     * @param headersMap   请求头组map
     * @param jsonStr      请求体参数json字符串
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2021/8/2/002 10:41
     * @description
     */
    public static String sendJsonPatch(String url, TreeMap<String, String> urlParamsMap, TreeMap<String, String> headersMap, String jsonStr) throws URISyntaxException, IOException {
        return sendJsonPatch(url, urlParamsMap, headersMap, jsonStr, null);
    }

    /**
     * 发起PATCH请求，请求体参数格式为JSON格式<br/>
     * 需要额外设置地址栏参数和请求头组以及RequestConfig<br/>
     *
     * @param url                 请求地址
     * @param urlParamsMap        地址栏参数map
     * @param headersMap          请求头组map
     * @param jsonStr             请求体参数json字符串
     * @param defineRequestConfig 自定义RequestConfig
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2021/7/29/029 11:19
     */
    public static String sendJsonPatch(String url, TreeMap<String, String> urlParamsMap, TreeMap<String, String> headersMap, String jsonStr, RequestConfig defineRequestConfig) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String result = EMPTY_JSON;

        URIBuilder uriBuilder = new URIBuilder(url);
        if (!CollectionUtils.isEmpty(urlParamsMap)) {
            // 设置地址栏请求参数
            uriBuilder = setUriBuilderParameters(uriBuilder, urlParamsMap);
        }
        URI uri = uriBuilder.build();

        HttpPatch httpPatch = new HttpPatch(uri);
        if (!CollectionUtils.isEmpty(headersMap)) {
            // 设置请求头组
            httpPatch = addAllHeadersOfPatch(httpPatch, headersMap);
        }

        // 设置RequestConfig
        if (defineRequestConfig != null) {
            httpPatch.setConfig(defineRequestConfig);
        } else {
            httpPatch.setConfig(getDefaultRequestConfig());
        }

        if (jsonStr != null && !"".equals(jsonStr)) {
            // 设置请求体json参数
            httpPatch = setPatchJsonStrEntity(httpPatch, jsonStr);
        }

        httpResponse = httpClient.execute(httpPatch);
        if (httpResponse.getEntity() != null) {
            result = EntityUtils.toString(httpResponse.getEntity(), CharEncoding.UTF_8);
        }
        doClose(httpClient, httpResponse);
        return result;
    }

    /**
     * 发起DELETE请求，需要额外设置地址栏参数和请求头组<br/>
     *
     * @param url          请求地址
     * @param urlParamsMap 地址栏参数map
     * @param headersMap   请求头组map
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2021/7/29/029 12:00
     */
    public static String sendDelete(String url, TreeMap<String, String> urlParamsMap, TreeMap<String, String> headersMap) throws URISyntaxException, IOException {
        return sendDelete(url, urlParamsMap, headersMap, null);
    }

    /**
     * 发起DELETE请求，需要额外设置地址栏参数和请求头组以及RequestConfig<br/>
     *
     * @param url                 请求地址
     * @param urlParamsMap        地址栏参数map
     * @param headersMap          请求头组map
     * @param defineRequestConfig 自定义RequestConfig
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2021/7/29/029 12:00
     */
    public static String sendDelete(String url, TreeMap<String, String> urlParamsMap, TreeMap<String, String> headersMap, RequestConfig defineRequestConfig) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String result = EMPTY_JSON;

        URIBuilder uriBuilder = new URIBuilder(url);
        if (!CollectionUtils.isEmpty(urlParamsMap)) {
            // 设置地址栏请求参数
            uriBuilder = setUriBuilderParameters(uriBuilder, urlParamsMap);
        }
        URI uri = uriBuilder.build();

        HttpDelete httpDelete = new HttpDelete(uri);
        if (!CollectionUtils.isEmpty(headersMap)) {
            // 设置请求头组
            httpDelete = addAllHeadersOfDelete(httpDelete, headersMap);
        }

        // 设置RequestConfig
        if (defineRequestConfig != null) {
            httpDelete.setConfig(defineRequestConfig);
        } else {
            httpDelete.setConfig(getDefaultRequestConfig());
        }

        httpResponse = httpClient.execute(httpDelete);
        if (httpResponse.getEntity() != null) {
            result = EntityUtils.toString(httpResponse.getEntity(), CharEncoding.UTF_8);
        }
        doClose(httpClient, httpResponse);
        return result;
    }

    /**
     * 统一关闭CloseableHttpClient和CloseableHttpResponse<br/>
     *
     * @param httpClient
     * @param httpResponse
     * @return void
     * @author HuangZhiGao
     * @date 2020/9/14 11:46
     */
    public static void doClose(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse) throws IOException {
        if (httpClient != null) {
            httpClient.close();
        }
        if (httpResponse != null) {
            httpResponse.close();
        }
    }

    /**
     * java对象转map<br/>
     *
     * @param object java对象
     * @return java.util.Map
     * @author HuangZhiGao
     * @date 2021/8/1/001 21:51
     */
    public static TreeMap<String, String> objectToMap(Object object) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (object != null) {
            if (object instanceof Map) {
                return (TreeMap) object;
            }
            TreeMap<String, String> treeMap = new TreeMap<>(BeanUtils.describe(object));
            treeMap.remove(CLASS_SUFFIX);
            return treeMap;
        }
        return new TreeMap<>();
    }

    /**
     * URL编码
     * <p/>
     * 默认使用 UTF_8 编码
     * <p/>
     *
     * @param keyword 一般字符串
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2021/11/15/015 14:00
     */
    public static String encode(String keyword, String charEncoding) throws UnsupportedEncodingException {
        if (StringUtil.isBlank(charEncoding)) {
            charEncoding = CharEncoding.UTF_8;
        }
        String encode = URLEncoder.encode(keyword, charEncoding);
        return encode;
    }

    /**
     * URL解码
     * <p/>
     * 默认使用 UTF_8 解码
     * <p/>
     *
     * @param encodeKeywordStr URL编码字符串
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2021/11/15/015 14:00
     */
    public static String decode(String encodeKeywordStr, String charEncoding) throws UnsupportedEncodingException {
        if (StringUtil.isBlank(charEncoding)) {
            charEncoding = CharEncoding.UTF_8;
        }
        String decode = URLDecoder.decode(encodeKeywordStr, charEncoding);
        return decode;
    }

}
