package com.hzg.guava.utilites;

import com.google.common.base.Preconditions;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @Package: com.hzg.guava.utilites
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-09-27 11:00
 */
public class PreconditionsMainTest {


    @Test(expected = NullPointerException.class)
    public void testCheckNotNull() {
        checkNotNull(null);
    }

    @Test
    public void testCheckNotNullWithMessage() {
        try {
            checkNotNullWithMessage(null);
        } catch (Exception e) {
            assertThat(e, is(NullPointerException.class));
            assertThat(e.getMessage(), equalTo("The list should not be null"));
        }
    }

    @Test
    public void testCheckNotNullWithFormatMessage() {
        try {
            checkNotNullWithFormatMessage(null);
        } catch (Exception e) {
            assertThat(e, is(NullPointerException.class));
            assertThat(e.getMessage(), equalTo("The list should not be null and the size must be 9"));
        }
    }

    @Test
    public void testCheckArgument() {
        checkArgument("DEFAULT_ADDRESS");
    }

    @Test
    public void testCheckState() {
        checkState(1);
    }

    private void checkNotNull(List<String> list) {
        Preconditions.checkNotNull(list);
    }

    private void checkNotNullWithMessage(List<String> list) {
        Preconditions.checkNotNull(list, "The list should not be null");
    }

    private void checkNotNullWithFormatMessage(List<String> list) {
        Preconditions.checkNotNull(list, "The list should not be null and the size must be %s", 9);
    }

    private void checkArgument(final String arg) {
        Preconditions.checkArgument("DEFAULT_NAME".equals(arg));
    }

    /**
     * 常用于线程状态的校验
     */
    private void checkState(final Integer state) {
        final Integer UNLOAD_STATE = 0;
        Preconditions.checkState(UNLOAD_STATE.equals(state));
    }

}
