package com.cys.util;

import com.cys.constants.IMessage;
import org.springframework.context.MessageSource;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * Created by liyuan on 2018/1/24.
 */
public class I18nUtils {
    public static String getMessage(IMessage message) {
        return getMessageByResource(message.getCategory()+"."+message.toString(), null, getDefaultLocale());
    }

    public static String getMessage(IMessage message, Object[] arguments) {
        return getMessageByResource(message.getCategory()+"."+message.toString(), arguments, getDefaultLocale());
    }

    public static String getMessage(String name, Object[] arguments) {
        return getMessageByResource(name, arguments, getDefaultLocale());
    }

    public static String getMessage(String name) {
        return getMessageByResource(name, null,getDefaultLocale());
    }

    /**
     * 根据locale，信息资源文件取得信息
     * @param name
     *            信息key
     * @param arguments
     *            信息体包含的变量值
     * @param locale
     *            如：zh_CN等对应的Locale
     * @return 信息值
     */
    protected static String getMessageByResource(String name,
                                                 Object[] arguments, Locale locale) {

        MessageSource messageSource = (MessageSource)ApplicationContextUtils.getApplicationContext().getBean("messageSource");
        String value = messageSource.getMessage(name, arguments, locale);
        if (value == null) {
            return "";
        } else if (value.trim().length() == 0) {
            return value;
        } else {
            if (arguments != null && arguments.length > 0) {
                value = MessageFormat.format(value, arguments);
            }
            return value;
        }
    }

    private static Locale defaultLocale = Locale.SIMPLIFIED_CHINESE;

    private static Locale getDefaultLocale() {
        Locale locale = defaultLocale;
        //TODO  部署时候en_US莫名问题
//		try{
//			locale = LocaleContextHolder.getLocale();
//		}
//		catch (Exception ex){
//			throw new RuntimeException("i18n configuration error");
//		}
        return locale;
    }

    /**
     * <B>仅初始化时调用。</B>
     *
     * @param locale
     */
    public static void setDefaultLocale(Locale locale) {
        if (locale != null) {
            defaultLocale = locale;
        }
    }
}
