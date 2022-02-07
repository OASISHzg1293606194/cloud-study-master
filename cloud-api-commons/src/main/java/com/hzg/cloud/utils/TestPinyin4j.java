package com.hzg.cloud.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @Package: com.hzg.cloud.utils
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-01-22 15:16
 */
public class TestPinyin4j {
    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        /**
         * HanyuPinyinCaseType.LOWERCASE 全拼音小写
         * HanyuPinyinCaseType.UPPERCASE 全拼音大写
         */
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);

        /**
         * HanyuPinyinToneType.WITHOUT_TONE     不带音号和音标
         * HanyuPinyinToneType.WITH_TONE_NUMBER 带音号
         * HanyuPinyinToneType.WITH_TONE_MARK   带音标
         */
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        /**
         * HanyuPinyinVCharType.WITH_U_AND_COLON    表示'ü'的输出是“u:”
         * HanyuPinyinVCharType.WITH_V              表示'ü'的输出是“v”
         * HanyuPinyinVCharType.WITH_U_UNICODE      表示'ü'的输出是Unicode形式的“ü”
         */
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        /**
         * str          需要转换为拼音的目标字符串
         * outputFormat 转换格式
         * separate     每个汉字拼音之间的分隔符
         * retain       保留不能转换成拼音的字符
         */
        System.out.println(PinyinHelper.toHanYuPinyinString("黄志高", format, "", true));
    }
}
