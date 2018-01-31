package com.cys.util;

/**
 * Class相关的功能
 * Created by liyuan on 2018/1/31.
 */
public class ClassUtils {
    /** DTO后缀 */
    private static final String DTO_FLAG = "DTO";
    /** Dto后缀 */
    private static final String DTO_FLAG2 = "Dto";
    /** _DTO后缀 */
    private static final String UNDERLINE_DTO_FLAG = "_DTO";
    /** _Dto后缀 */
    private static final String UNDERLINE_DTO_FLAG2 = "_Dto";
    /** _dto后缀 */
    private static final String UNDERLINE_DTO_FLAG3 = "_dto";

    /** 短名 */
    protected static final Inflector INFLECTOR = Inflector.getInstance();

    /**
     * 获得实体名，去除DTO、Dto、_DTO、_Dto、_dto后缀
     * @param name   实体名称
     * @return
     */
    public static String getEntityNameWithoutDto(String name) {
        if (name.endsWith(UNDERLINE_DTO_FLAG) || name.endsWith(UNDERLINE_DTO_FLAG2) || name.endsWith(UNDERLINE_DTO_FLAG3)) {
            return name.substring(0, name.length() - UNDERLINE_DTO_FLAG.length());
        }
        if (name.endsWith(DTO_FLAG) || name.endsWith(DTO_FLAG2)) {
            return name.substring(0, name.length() - DTO_FLAG.length());
        }
        return name;
    }

    /**
     * 获得实体名，去除DTO或Dto后缀
     * @param entityClass   实体Class
     * @return
     */
    public static String getEntityNameWithoutDto(Class entityClass) {
        return getEntityNameWithoutDto(entityClass.getSimpleName());
    }

    /** 获得object的单数和小写驼峰形式名称 */
    public static String getLowerCamelAndSingularize(String name){
        return INFLECTOR.lowerCamelCase(
                INFLECTOR.singularize(
                        ClassUtils.getEntityNameWithoutDto(name)
                )
        );
    }

    /** 获得object的复数数和小写驼峰形式名称 */
    public static String getLowerCamelAndPluralize(String name){
        return INFLECTOR.lowerCamelCase(
                INFLECTOR.pluralize(
                        ClassUtils.getEntityNameWithoutDto(name)
                )
        );
    }
}
