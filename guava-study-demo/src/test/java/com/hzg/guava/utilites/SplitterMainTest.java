package com.hzg.guava.utilites;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @Package: com.hzg.guava.utilites
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-09-26 19:10
 */
public class SplitterMainTest {

    @Test
    public void testSplitOnSplit() {
        List<String> list = Splitter.on(",").splitToList("Hello,World");
        System.out.println(list);
        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(2));
        assertThat(list.get(0), equalTo("Hello"));
        assertThat(list.get(1), equalTo("World"));
    }

    @Test
    public void testSplitOnSplit_OmitEmpty() {
        List<String> list = Splitter.on(",").omitEmptyStrings().splitToList("Hello,World,,,,");
        System.out.println(list);
        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(2));
        assertThat(list.get(0), equalTo("Hello"));
        assertThat(list.get(1), equalTo("World"));
    }

    @Test
    public void testSplitOnSplit_Trim() {
        List<String> list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList("Hello , World , ,, ,");
        System.out.println(list);
        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(2));
        assertThat(list.get(0), equalTo("Hello"));
        assertThat(list.get(1), equalTo("World"));
    }

    @Test
    public void testSplitFixLength() {
        List<String> list = Splitter.fixedLength(4).omitEmptyStrings().trimResults().splitToList("aaaabbbbcccc");
        System.out.println(list);
        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(3));
        assertThat(list.get(0), equalTo("aaaa"));
        assertThat(list.get(1), equalTo("bbbb"));
        assertThat(list.get(2), equalTo("cccc"));

        List<String> rsltList = Splitter.fixedLength(4).omitEmptyStrings().trimResults().splitToList("aaaabbbbcc");
        System.out.println(rsltList);
    }

    @Test
    public void testSplitLimit() {
        List<String> list = Splitter.on("#").limit(2).splitToList("Redisson#ActiveMQ#Kafka#RacketMQ");
        System.out.println(list);
        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(2));
        assertThat(list.get(1), equalTo("ActiveMQ#Kafka#RacketMQ"));
    }

    @Test
    public void testSplit_PatternString() {
        List<String> list = Splitter.onPattern("\\D|\\s").omitEmptyStrings().trimResults().splitToList("2 仪生 4 象，4 象生 8 卦");
        System.out.println(list);
        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(4));
    }

    @Test
    public void testSplitToMap() {
        Map<String, String> map = Splitter.on(Pattern.compile("\\|")).omitEmptyStrings().trimResults().withKeyValueSeparator("=").split("hello=HELLO||||  world=WORLD | | ||");
        System.out.println(map);
        assertThat(map, notNullValue());
        assertThat(map.size(), equalTo(2));
        assertThat(map.get("hello"), equalTo("HELLO"));
        assertThat(map.get("world"), equalTo("WORLD"));
    }

}
