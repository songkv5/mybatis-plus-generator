package com.ws.tool.mybatisplus.config;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2019年11月07日 22:26
 */
public class OutputConfig {
    /**
     * 包路径
     */
    private String pkg;
    /**
     * mapper.xml的输出路径
     */
    private String xmlPath;
    /**
     * module
     */
    private String module;
    /**
     * java code 路径
     */
    private String jcPath;
    /**
     * 是否生成BaseResult
     */
    private boolean resultMap = false;
    /**
     * 是否添加restController
     */
    private boolean rest = false;
    /**
     * 是否创建controller
     */
    private boolean createController = true;
    /**
     * 是否创建service。默认创建
     */
    private boolean createService = true;
    /**
     * controller的路径
     */
    private String controller;
    /**
     * service的路径
     */
    private String service;
    /**
     * 表前缀
     */
    private String tPrefix;

    /**
     * 是否覆盖原有的文件
     */
    private boolean override = true;


    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getJcPath() {
        return jcPath;
    }

    public void setJcPath(String jcPath) {
        this.jcPath = jcPath;
    }

    public boolean getRest() {
        return rest;
    }

    public void setRest(boolean rest) {
        this.rest = rest;
    }

    public boolean getCreateController() {
        return createController;
    }

    public void setCreateController(boolean createController) {
        this.createController = createController;
    }

    public boolean getCreateService() {
        return createService;
    }

    public void setCreateService(boolean createService) {
        this.createService = createService;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public boolean getOverride() {
        return override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }

    public boolean getResultMap() {
        return resultMap;
    }

    public void setResultMap(boolean resultMap) {
        this.resultMap = resultMap;
    }

    public String gettPrefix() {
        return tPrefix;
    }

    public void settPrefix(String tPrefix) {
        this.tPrefix = tPrefix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.pkg != null) {
            sb.append("\"pkg\":").append("\"").append(this.pkg).append("\"").append(",");
        }
        if (this.xmlPath != null) {
            sb.append("\"xmlPath\":").append("\"").append(this.xmlPath).append("\"").append(",");
        }
        if (this.module != null) {
            sb.append("\"module\":").append("\"").append(this.module).append("\"").append(",");
        }
        if (this.jcPath != null) {
            sb.append("\"jcPath\":").append("\"").append(this.jcPath).append("\"").append(",");
        }
        sb.append("\"resultMap\":").append(this.resultMap).append(",");
        sb.append("\"rest\":").append(this.rest).append(",");
        sb.append("\"createController\":").append(this.createController).append(",");
        sb.append("\"createService\":").append(this.createService).append(",");
        if (this.controller != null) {
            sb.append("\"controller\":").append("\"").append(this.controller).append("\"").append(",");
        }
        if (this.service != null) {
            sb.append("\"service\":").append("\"").append(this.service).append("\"").append(",");
        }
        if (this.tPrefix != null) {
            sb.append("\"tPrefix\":").append("\"").append(this.tPrefix).append("\"").append(",");
        }
        String s = sb.toString();

        if (s.endsWith(",")) {
            s = s.substring(0, s.length() - 1);
        }
        return s + "}";
    }
}