package com.ws.tool.mybatisplus.constants;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2019年11月09日 10:22
 */
public enum DbTypeEnum {
    UNKNOWN("unknown"),
    MYSQL("mysql"),
//    ORACLE("oracle"),
    ;
    DbTypeEnum(String name) {
        this.name = name;
    }

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static DbTypeEnum getByName(String name) {
        if (name == null) {
            return UNKNOWN;
        }
        DbTypeEnum[] values = DbTypeEnum.values();
        for (DbTypeEnum value : values) {
            if (value.name.equalsIgnoreCase(name)) {
                return value;
            }
        }
        return UNKNOWN;
    }
}

