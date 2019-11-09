package com.ws.tool.mybatisplus.helper;

import com.ws.tool.mybatisplus.constants.DbTypeEnum;
import com.ws.tool.mybatisplus.constants.ErrorCodeConstant;
import com.ws.tool.mybatisplus.exception.DbConfigException;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2019年11月09日 10:31
 */
public class GeneratorHelper {
    public static String transferDriverClass(String dbType) {
        DbTypeEnum type = DbTypeEnum.getByName(dbType);
        switch (type) {
            case MYSQL:{
                return "com.mysql.cj.jdbc.Driver";
            }
            default:{
                throw new DbConfigException(ErrorCodeConstant.DB_TYPE_NOT_SUPPORT_ERROR_CODE, "暂不支持数据库类型：" + dbType);
            }
        }
    }
}