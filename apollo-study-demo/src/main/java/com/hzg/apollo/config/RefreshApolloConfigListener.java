package com.hzg.apollo.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @Package: com.hzg.apollo.config
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-01 14:40
 */
@Configuration
public class RefreshApolloConfigListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshApolloConfigListener.class);

    @ApolloConfig
    private Config config;


    /**
     * 监听配置中心配置的更新事件
     * <p/>
     *
     * @param configChangeEvent
     * @return void
     * @author HuangZhiGao
     * @date 2022/3/2/002 10:56
     */
    @ApolloConfigChangeListener
    private void configChangeListener(ConfigChangeEvent configChangeEvent) {
        refreshConfigInfo();
    }

    @PostConstruct
    private void refreshConfigInfo() {
        Set<String> keyNames = config.getPropertyNames();
        for (String key : keyNames) {
            LOGGER.info("{}:{}", key, config.getProperty(key, null));
        }
    }

}
