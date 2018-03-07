package com.chenyingjun.sp.freemarker;

import com.chenyingjun.sp.shiro.tool.TokenTool;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 界面使用标签信息
 */
@Component
public class FreeMarkerConfig {
    /** x */
    @Autowired
    private freemarker.template.Configuration freeMarkerConfiguration;

    /**
     * 设置界面使用的标签信息
     */
    @PostConstruct
    public void freemarkerConfig() {
        try {
            freeMarkerConfiguration.setSharedVariable("token", new TokenTool());
        } catch (TemplateModelException e) {

        }
    }

}
