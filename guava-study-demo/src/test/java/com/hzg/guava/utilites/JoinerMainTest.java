package com.hzg.guava.utilites;

import com.google.common.base.Joiner;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @Package: com.hzg.guava.utilites
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-09-23 18:39
 */
public class JoinerMainTest {

    private final List<String> strList = Arrays.asList(
            "Google", "Guava", "Java", "Redis", "MySQL", "Zookeeper", "RabbitMQ"
    );

    private final List<String> strListWithNullValue = Arrays.asList(
            "Google", "Guava", "Java", "Redis", "MySQL", "Zookeeper", null
    );

    // private final Map<String, String> strMap = ImmutableMap.of("Hello", "Java", "Hi", "Guava");
    private final Map<String, String> strMap = of("Hello", "Java", "Hi", "Guava");

    private final String targetFileName = "D:\\ideaIU2018.2.5ForDev\\cloud-study-master\\guava-study-demo\\src\\main\\resources\\guava-joiner.txt";
    private final String targetFileNameForMap = "D:\\ideaIU2018.2.5ForDev\\cloud-study-master\\guava-study-demo\\src\\main\\resources\\guava-immutable-map.txt";


    @Test
    public void testJoinOnJoin() {
        String joinRslt = Joiner.on("#").join(strList);
        assertThat(joinRslt, equalTo("Google#Guava#Java#Redis#MySQL#Zookeeper#RabbitMQ"));
        System.out.println(joinRslt);
    }

    @Test(expected = NullPointerException.class)
    public void testJoinOnJoin_WithNullValue() {
        String joinRslt = Joiner.on("#").join(strListWithNullValue);
        assertThat(joinRslt, equalTo("Google#Guava#Java#Redis#MySQL#Zookeeper"));
        System.out.println(joinRslt);
    }

    @Test
    public void testJoinOnJoin_WithNullValueButSkip() {
        String joinRslt = Joiner.on("#").skipNulls().join(strListWithNullValue);
        assertThat(joinRslt, equalTo("Google#Guava#Java#Redis#MySQL#Zookeeper"));
        System.out.println(joinRslt);
    }

    @Test
    public void testJoinOnJoin_WithNullValue_UseDefaultValue() {
        String joinRslt = Joiner.on("#").useForNull("DEFAULT").join(strListWithNullValue);
        assertThat(joinRslt, equalTo("Google#Guava#Java#Redis#MySQL#Zookeeper#DEFAULT"));
        System.out.println(joinRslt);
    }

    @Test
    public void testJoinOnAppend_To_StringBuffer() {
        try {
            final StringBuffer buffer = new StringBuffer();
            StringBuffer bufferRslt = Joiner.on("#").useForNull("DEFAULT").appendTo(buffer, strListWithNullValue);
            assertThat(bufferRslt, sameInstance(buffer));
            System.out.println(buffer.toString());
            System.out.println(bufferRslt.toString());
        } catch (IOException e) {
            fail(e.getStackTrace().toString());
        }
    }

    @Test
    public void testJoinOnAppend_To_Writer() {
        try (FileWriter fileWriter = new FileWriter(new File(targetFileName))) {
            Joiner.on("#").useForNull("DEFAULT").appendTo(fileWriter, strListWithNullValue);
            assertThat(Files.isFile().test(new File(targetFileName)), equalTo(Boolean.TRUE.booleanValue()));
        } catch (IOException e) {
            fail("append to the fileWriter occur fetal error.");
        }
    }

    @Test
    public void testJoinByJava8Stream_WithNullValueButSkip() {
        if (!strListWithNullValue.isEmpty()) {
            String result = strListWithNullValue.stream().filter(item -> item != null && !item.isEmpty()).collect(joining("#"));
            assertThat(result, equalTo("Google#Guava#Java#Redis#MySQL#Zookeeper"));
            System.out.println(result);
        }
    }

    @Test
    public void testJoinByJava8Stream_WithNullValue_UseDefaultValue() {
        if (!strListWithNullValue.isEmpty()) {
            String result = strListWithNullValue.stream().map(item -> item == null || item.isEmpty() ? "DEFAULT" : item).collect(joining("#"));
            assertThat(result, equalTo("Google#Guava#Java#Redis#MySQL#Zookeeper#DEFAULT"));
            System.out.println(result);
        }
    }

    @Test
    public void testJoinOnWithMap() {
        String result = Joiner.on("\n").withKeyValueSeparator("=").join(strMap);
        assertThat(result, equalTo("Hello=Java\nHi=Guava"));
        System.out.println(result);
    }

    @Test
    public void testJoinOnWithMap_To_Writer() {
        try (FileWriter fileWriter = new FileWriter(new File(targetFileNameForMap))) {
            Joiner.on("\n").withKeyValueSeparator("=").appendTo(fileWriter, strMap);
            assertThat(Files.isFile().test(new File(targetFileNameForMap)), equalTo(Boolean.TRUE.booleanValue()));
        } catch (IOException e) {
            fail("append to the fileWriter occur fetal error.");
        }
    }

}