package com.cys.support.jpa;

/**
 * springside的动态查询
 * Created by liyuan on 2018/1/28.
 */

import com.cys.common.enums.LogicType;
import com.cys.util.ParamUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class DynamicSpecifications {

    public static <T> Specification<T> bySearchFilter(final List<SearchFilter> filters) {
        return bySearchFilter(filters, null);
    }

    public static <T> Specification<T> bySearchFilter(final List <SearchFilter> filters, final Class<T> clazz) {
        return new Specification<T>() {
            @SuppressWarnings({ "unchecked", "rawtypes" })
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if(!CollectionUtils.isEmpty(filters)) {
                    List<Predicate> predicates =new ArrayList();
                    List<LogicType> logicTypes = new ArrayList<LogicType>();
                    for (SearchFilter filter : filters) {
                        // nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
                        String[] names = StringUtils.split(filter.getFieldName(), ".");
                        Path expression = root.get(names[0]);
                        for (int i = 1; i < names.length; i++) {
                            expression = expression.get(names[i]);
                        }

                        // logic operator
                        switch (filter.getMatchType()) {
                            case EQ:
                                predicates.add(builder.equal(expression, filter.getValue()));
                                break;
                            case NEQ:
                                predicates.add(builder.notEqual(expression, filter.getValue()));
                                break;
                            case LIKE:
                                predicates.add(builder.like(builder.upper(expression), "%" + filter.getValue().toString().toUpperCase() + "%"));
                                break;
                            case LLIKE:
                                predicates.add(builder.like(builder.upper(expression), "%" + filter.getValue().toString().toUpperCase()));
                                break;
                            case RLIKE:
                                predicates.add(builder.like(builder.upper(expression), filter.getValue().toString().toUpperCase() + "%"));
                                break;
                            case NLIKE:
                                predicates.add(builder.notLike(builder.upper(expression), "%" + filter.getValue().toString().toUpperCase() + "%"));
                                break;
                            case GT:
                                predicates.add(builder.greaterThan(expression, (Comparable) filter.getValue()));
                                break;
                            case LT:
                                predicates.add(builder.lessThan(expression, (Comparable) filter.getValue()));
                                break;
                            case GTE:
                                predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.getValue()));
                                break;
                            case LTE:
                                predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.getValue()));
                                break;
                            case BETWEEN:
                                predicates.add(builder.between(expression, (Comparable) (filter.getValues()[0]), (Comparable) (filter.getValues()[1])));
                                break;
                            case NNULL:
                                predicates.add(builder.isNotNull(expression));
                                break;
                            case NULL:
                                predicates.add(builder.isNull(expression));
                                break;
                            case INQ:
                                if (filter.getValue() != null || filter.getValues() != null) {
                                    CriteriaBuilder.In in = builder.in(expression);
                                    if (filter.getValue() instanceof Collection) {
                                        Iterator iterator = ((Collection) filter.getValue()).iterator();
                                        while (iterator.hasNext()) {
                                            in.value(iterator.next());
                                        }
                                    } else if (filter.getValue() != null) {
                                        if (filter.getValue().getClass().isArray()) {
                                            for (int i = 0; i < Array.getLength(filter.getValue()); i++) {
                                                in.value(Array.get(filter.getValue(), i));
                                            }
                                        } else {
                                            in.value(filter.getValue());
                                        }
                                    } else {
                                        for (Object object : filter.getValues()) {
                                            in.value(object);
                                        }
                                    }
                                    predicates.add(builder.and(in));
                                }
                                break;
                            case NIN:
                                if (filter.getValue() != null || filter.getValues() != null) {
                                    CriteriaBuilder.In in = builder.in(expression);
                                    if (filter.getValue() instanceof Collection) {
                                        Iterator iterator = ((Collection) filter.getValue()).iterator();
                                        while (iterator.hasNext()) {
                                            in.value(iterator.next());
                                        }
                                    } else if (filter.getValue() != null) {
                                        if (filter.getValue().getClass().isArray()) {
                                            for (int i = 0; i < Array.getLength(filter.getValue()); i++) {
                                                in.value(Array.get(filter.getValue(), i));
                                            }
                                        } else {
                                            in.value(filter.getValue());
                                        }
                                    } else {
                                        for (Object object : filter.getValues()) {
                                            in.value(object);
                                        }
                                    }
                                    predicates.add(builder.not(in));
                                }
                                break;
                            default:
                                break;
                        }

                        logicTypes.add(filter.getLogicType());

                        // 递归处理joinfilter
                        if(!ParamUtils.isEmpty(filter.getJoinFilters())) {
                            predicates.add(DynamicSpecifications.bySearchFilter(filter.getJoinFilters(), clazz).toPredicate(root, query, builder));
                            logicTypes.add(filter.getRoundLogicType());
                        }
                    }

                    // 将所有条件用 and 联合起来
                    if (!predicates.isEmpty()) {
                        Predicate predicate = null;
                        for(int i=0;i<predicates.size();i++) {
                            // 第一个过滤条件默认是and
                            if(i == 0) {
                                predicate = builder.and(predicates.get(i));
                            } else {
                                switch(logicTypes.get(i)) {
                                    case AND:predicate = builder.and(predicate, predicates.get(i));break;
                                    case OR:predicate = builder.or(predicate, predicates.get(i));break;
                                    default:break;
                                }
                            }
                        }
                        return predicate;
                    }
                }

                return builder.conjunction();
            }
        };
    }
}

