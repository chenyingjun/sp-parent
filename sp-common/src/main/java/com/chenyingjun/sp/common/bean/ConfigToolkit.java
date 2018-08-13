package com.chenyingjun.sp.common.bean;

import com.dangdang.config.service.GeneralConfigGroup;
import com.dangdang.config.service.zookeeper.ZookeeperConfigGroup;
import com.dangdang.config.service.zookeeper.ZookeeperConfigProfile;
import org.springframework.stereotype.Component;

@Component
public class ConfigToolkit {

    private static GeneralConfigGroup group;

    public ConfigToolkit() {
        String connectString = System.getProperty("config.zookeeper.connectString");
        String rootNode = System.getProperty("config.rootNode");
        String version = System.getProperty("config.version");
        String groupName = System.getProperty("config.groupName");
        ZookeeperConfigProfile configProfile = new ZookeeperConfigProfile(connectString, rootNode, version);
        group = new ZookeeperConfigGroup(configProfile, groupName);
    }

    public static String getConfigString(String key) {
        return getConfigString(key, null);
    }

    public static String getConfigString(String key, String defaultValue) {
        String val = group.get(key);
        return val == null ? defaultValue: val;
    }
}
