package com.ws.tool.mybatisplus.config;

/**
 * @author willis<songkai01>
 * @chapter 数据库配置
 * @section
 * @since 2019年11月07日 22:23
 */
public class DataSource {
    private String ip;
    private int port = 3306;
    private String db;
    private String user;
    private String pwd;
    /**
     * 默认mysql
     */
    private String type = "mysql";

    /*public static class Builder{
        private DataSource dataSource;
        private Builder() {
            this.dataSource = new DataSource();
        }
        public DataSource build() {
            if (this.dataSource.ip == null) {
                throw new GeneratorException(1001, "缺少数据库ip或host。配置路径：configuration>dataSource>ip");
            }
            if (this.dataSource.db == null) {
                throw new GeneratorException(1002, "缺少数据库名称。配置路径：configuration>dataSource>db");
            }
            if (this.dataSource.user == null) {
                throw new GeneratorException(1002, "缺少数据库名称。配置路径：configuration>dataSource>user");
            }
            if (this.dataSource.pwd == null) {
                throw new GeneratorException(1002, "缺少数据库名称。配置路径：configuration>dataSource>pwd");
            }
            return this.dataSource;
        }
        public static Builder newBuilder() {
            return new Builder();
        }
        public Builder ip(String ip) {
            this.dataSource.ip = ip;
            return this;
        }
        public Builder port(int port) {
            this.dataSource.port = port;
            return this;
        }
        public Builder db(String db) {
            this.dataSource.db = db;
            return this;
        }
        public Builder user(String user) {
            this.dataSource.user = user;
            return this;
        }
        public Builder pwd(String pwd) {
            this.dataSource.pwd = pwd;
            return this;
        }
    }*/

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}