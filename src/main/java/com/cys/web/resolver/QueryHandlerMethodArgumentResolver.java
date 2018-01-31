package com.cys.web.resolver;

import com.cys.common.domain.Query;
import com.cys.support.querry.FilterParser;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 控制器query参数resolver
 * Created by liyuan on 2018/1/31.
 */
public class QueryHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String DEFAULT_FILTER_PARAMETER = "filter";
    private static final String DEFAULT_PAGE_PARAMETER = "page";
    private static final String DEFAULT_SIZE_PARAMETER = "limit";
    private static final String DEFAULT_ORDER_PARAMETER = "order";

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 10;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Query.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        String filter = webRequest.getParameter(DEFAULT_FILTER_PARAMETER);
        String order = webRequest.getParameter(DEFAULT_ORDER_PARAMETER);
        String pageString = webRequest.getParameter(DEFAULT_PAGE_PARAMETER);
        String sizeString = webRequest.getParameter(DEFAULT_SIZE_PARAMETER);
        int page = StringUtils.hasText(pageString)?(Integer.parseInt(pageString)>=DEFAULT_PAGE?Integer.parseInt(pageString):DEFAULT_PAGE):DEFAULT_PAGE;
        int size = StringUtils.hasText(sizeString)?(Integer.parseInt(sizeString)>0?Integer.parseInt(sizeString):DEFAULT_SIZE):DEFAULT_SIZE;
        Query query = new Query();
        FilterParser filterParser = new FilterParser(filter,order);
        query.setFilters(filterParser.toSearchFilters());
        query.setPageable(new PageRequest(page, size, filterParser.getSort()));
        return query;
    }

}
