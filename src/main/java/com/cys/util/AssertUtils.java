package com.cys.util;

import com.cys.constants.ErrorCode;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by liyuan on 2018/1/24.
 */
public class AssertUtils {

    public static void isTrue(boolean expression, String parameterName) {
        if (!expression) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.paramIsTrue, new Object[]{parameterName})); }
    }

    public static void isTrue(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidIsTrue));
        }
    }

    public static void isNull(Object object, String parameterName) {
        if (object != null) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.paramIsNull, new Object[]{parameterName}));
        }
    }

    public static void isNull(Object object) {
        if (object != null) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidIsNull));
        }
    }

    public static void notNull(Object object, String parameterName) {
        if (object == null) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.paramNotNull, new Object[]{parameterName}));
        }
    }

    public static void notNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidNotNull));
        }
    }

    public static void hasLength(String text, String parameterName) {
        if (!StringUtils.hasLength(text)) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.paramHasLength, new Object[]{parameterName}));
        }
    }

    public static void hasLength(String text) {
        if (!StringUtils.hasLength(text)) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidHasLength));
        }
    }

    public static void hasText(String text, String parameterName) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.paramHasText, new Object[]{parameterName}));
        }
    }

    public static void hasText(String text) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidHasText));
        }
    }

    public static void doesNotContain(String textToSearch, String substring, String searchTextName,String subStringName) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.contains(substring)) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidNotContainArgs, new Object[]{searchTextName, subStringName}));
        }
    }

    public static void doesNotContain(String textToSearch, String substring) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.contains(substring)) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidNotContain));
        }    }

    public static void notEmpty(Object[] array, String parameterName) {
        if (ObjectUtils.isEmpty(array)) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidArrayNotEmptyArgs, new Object[]{parameterName}));
        }
    }

    public static void notEmpty(Object[] array) {
        if (ObjectUtils.isEmpty(array)) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidArrayNotEmpty));
        }
    }

    public static void noNullElements(Object[] array, String parameterName) {
        if (array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if (element == null) {
                    throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidElementsNotNullArgs, new Object[]{parameterName}));
                }
            }
        }

    }

    public static void noNullElements(Object[] array) {
        if (array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if (element == null) {
                    throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidElementsNotNull));
                }
            }
        }
    }

    public static void notEmpty(Collection<?> collection, String parameterName) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidCollectionNotEmptyArgs, new Object[]{parameterName}));
        }
    }

    public static void notEmpty(Collection<?> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidCollectionNotEmpty));
        }
    }

    public static void notEmpty(Map<?, ?> map, String parameterName) {
        if (CollectionUtils.isEmpty(map)) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidMapNotEmptyArgs, new Object[]{parameterName}));
        }
    }

    public static void notEmpty(Map<?, ?> map) {
        if (CollectionUtils.isEmpty(map)) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidMapNotEmpty));
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj) {
        notNull(type);
        if (!type.isInstance(obj)) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidIsInstanceOf));
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, String typeName, String objName) {
        notNull(type, typeName);
        if (!type.isInstance(obj)) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidIsInstanceOfArgs, new Object[]{typeName, objName}));
        }
    }

    public static void isAssignable(Class<?> superType, Class<?> subType) {
        notNull(superType);
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidIsAssignable));
        }
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, String superTypeName, String subTypeName) {
        notNull(superType, superTypeName);
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new IllegalArgumentException(I18nUtils.getMessage(ErrorCode.Common.invalidIsAssignableArgs, new Object[]{superTypeName, subTypeName}));
        }
    }


    public static void notEqual(Object object1, Object object2, String message) {
        notNull(object1);
        notNull(object2);
        if (object1.equals(object2)) {
            throw new IllegalArgumentException(message);
        }
    }
}
