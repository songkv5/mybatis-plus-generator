package com.ws.tool.mybatisplus.engine;

import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.maven.plugin.logging.Log;

import java.util.Map;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2019年11月13日 18:26
 */
public class CustomerFreemarkerTemplateEngine extends FreemarkerTemplateEngine {
    private Log log;
    public CustomerFreemarkerTemplateEngine(Log log) {
        this.log = log;
    }

    @Override
    public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {
        super.writer(objectMap, templatePath, outputFile);
        log.info("生成文件:" + outputFile);
    }
}