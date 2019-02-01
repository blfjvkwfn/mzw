package com.mzw.common.util.query;

import com.mzw.common.util.EmptyUtil;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author mengzhaowei@boce.cn
 * @date 2018/12/6 13:40
 */
@Setter
public class QueryUtil<T> {

    private Class<T> entityClass;
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN);
    private static Map<String, QueryUtil> cache = new HashMap<>();
    public QueryUtil(Class entityClass) {
        setEntityClass(entityClass);
    }

    public static QueryUtil getInstance(Class entityClass){
        QueryUtil instance = null;
        String name = entityClass.getName();
        if (cache.containsKey(name)) {
            instance = cache.get(name);
        } else {
            instance = new QueryUtil(entityClass);
            cache.put(name, instance);
        }
        return instance;
    }

    public Specification<T> getSpecification(QueryParam param){
        return getSpecification(param, null);
    }

    public Specification<T> getSpecification(QueryParam param, List<Predicate> predicateList){
        return (Specification<T>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>(16);
            if (!EmptyUtil.isObjEmpty(predicateList)) {
                predicates.addAll(predicateList);
            }
            param.getConditions().forEach((condition)-> {
                try {
                    addCondition(root, cb, condition, predicates);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }

    private void addCondition(Root<T> root, CriteriaBuilder cb, Condition condition, List<Predicate> predicates) {
        String queryType = condition.getQueryType();
        if (EmptyUtil.isFieldEmpty(queryType)) {
            return;
        }

        QueryType type = QueryType.getByType(condition.getQueryType());
        if (EmptyUtil.isObjEmpty(type)) {
            return;
        }

        if (EmptyUtil.isObjEmpty(condition.getValue())) {
            return;
        }

        Method targetMethod;
        String fieldName = condition.getField();
        Object value = condition.getValue();
        try {
            switch (type) {
                case EQUAL:
                    targetMethod = cb.getClass().getMethod(type.getMethod(), Expression.class, Object.class);
                    addPredicate(getPredicate(root,cb,fieldName,value,targetMethod), predicates);
                    break;
                case LESS_THAN_OR_EQUAL_TO:
                    targetMethod = cb.getClass().getMethod(type.getMethod(), Expression.class, Comparable.class);
                    addPredicate(getPredicate(root,cb,fieldName,value,targetMethod), predicates);
                    break;
                case GREATER_THAN_OR_EQUAL_TO:
                    targetMethod = cb.getClass().getMethod(type.getMethod(), Expression.class, Comparable.class);
                    addPredicate(getPredicate(root,cb,fieldName,value,targetMethod), predicates);
                    break;
                case LIKE:
                    targetMethod = cb.getClass().getMethod(type.getMethod(), Expression.class, String.class);
                    addPredicate(getPredicate(root,cb,fieldName,"%" + value.toString() + "%",targetMethod), predicates);
                    break;
                case IN:
                    addPredicate(getInPredicate(root,cb,condition.getField(),condition.getValue()), predicates);
                    break;
                default:
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Predicate getInPredicate(Root<T> root, CriteriaBuilder cb, String fieldName, Object value) {
        CriteriaBuilder.In<Object> builder = cb.in(getStrPath(root, fieldName));
        if (value instanceof List) {
            ((List) value).forEach(obj -> builder.value(obj));
        }
        return builder;
    }

    private void addPredicate(Predicate predicate, List<Predicate> predicates) {
        if (!EmptyUtil.isObjEmpty(predicate)) {
            predicates.add(predicate);
        }
    }

    @Deprecated
    private Predicate getLiPredicate(Root<T> root, CriteriaBuilder cb, String fieldName, Object value) {
        return cb.like(root.<String>get(fieldName), value.toString());
    }

    @Deprecated
    private Predicate getGtPredicate(Root<T> root, CriteriaBuilder cb, String fieldName, Object value) {
        Predicate predicate = null;
        try {
            Field field = entityClass.getDeclaredField(fieldName);
            Class fieldType = field.getType();
            if (fieldType.equals(String.class)) {
                predicate = cb.greaterThanOrEqualTo(root.<String>get(fieldName), String.valueOf(value));
            } else if (fieldType.equals(Long.class)) {
                predicate = cb.greaterThanOrEqualTo(root.<Long>get(fieldName), Long.parseLong(value.toString()));
            } else if (fieldType.equals(Integer.class)) {
                predicate = cb.greaterThanOrEqualTo(root.<Integer>get(fieldName), Integer.parseInt(value.toString()));
            } else if (fieldType.equals(Date.class)) {
                predicate = cb.greaterThanOrEqualTo(root.<Date>get(fieldName), df.parse((String) value));
            } else if (fieldType.equals(Double.class)) {
                predicate = cb.greaterThanOrEqualTo(root.<Double>get(fieldName), Double.parseDouble(value.toString()));
            }
        } catch (Exception e) {
            predicate = cb.greaterThanOrEqualTo(getStrPath(root, fieldName), String.valueOf(value));
        }
        return predicate;
    }

    @Deprecated
    private Predicate getLtPredicate(Root<T> root, CriteriaBuilder cb, String fieldName, Object value) {
        Predicate predicate = null;
        try {
            Field field = entityClass.getDeclaredField(fieldName);
            Class fieldType = field.getType();
            if (fieldType.equals(String.class)) {
                predicate = cb.lessThanOrEqualTo(root.<String>get(fieldName), String.valueOf(value));
            } else if (fieldType.equals(Long.class)) {
                predicate = cb.lessThanOrEqualTo(root.<Long>get(fieldName), Long.parseLong(value.toString()));
            } else if (fieldType.equals(Integer.class)) {
                predicate = cb.lessThanOrEqualTo(root.<Integer>get(fieldName), Integer.parseInt(value.toString()));
            } else if (fieldType.equals(Date.class)) {
                predicate = cb.lessThanOrEqualTo(root.<Date>get(fieldName), df.parse(value.toString()));
            } else if (fieldType.equals(Double.class)) {
                predicate = cb.lessThanOrEqualTo(root.<Double>get(fieldName), Double.parseDouble(value.toString()));
            }
        } catch (Exception e) {
            predicate = cb.lessThanOrEqualTo(getStrPath(root, fieldName), String.valueOf(value));
        }
        return predicate;
    }

    @Deprecated
    private Predicate getEqPredicate(Root<T> root, CriteriaBuilder cb, String fieldName, Object value) {
        Predicate predicate = null;
        try {
            Field field = entityClass.getDeclaredField(fieldName);
            Class fieldType = field.getType();
            if (fieldType.equals(String.class)) {
                predicate = cb.equal(root.get(fieldName).as(String.class), String.valueOf(value));
            } else if (fieldType.equals(Long.class)) {
                predicate = cb.equal(root.get(fieldName).as(Long.class), Long.parseLong(value.toString()));
            } else if (fieldType.equals(Integer.class)) {
                predicate = cb.equal(root.get(fieldName).as(Integer.class), Integer.parseInt(value.toString()));
            } else if (fieldType.equals(Date.class)) {
                predicate = cb.equal(root.get(fieldName).as(Date.class), df.parse(value.toString()));
            } else if (fieldType.equals(Double.class)) {
                predicate = cb.equal(root.get(fieldName).as(Double.class), Double.parseDouble(value.toString()));
            }

        } catch (Exception e) {
            predicate = cb.equal(getStrPath(root, fieldName), String.valueOf(value));
        }
        return predicate;
    }

    private Path getStrPath(Root<T> root, String fieldName) {
        Path path = null;
        if (fieldName.contains(".")) {
            String[] fieldNames = fieldName.split("\\.");
            for (int i = 0; i < fieldNames.length; i++) {
                if (EmptyUtil.isObjEmpty(path)) {
                    path = root.get(fieldNames[i]);
                } else {
                    path = path.get(fieldNames[i]);
                }
            }
        } else {
            path = root.<String>get(fieldName);
        }
        return path;
    }

    private Predicate getPredicate(Root<T> root, CriteriaBuilder cb, String fieldName, Object value, Method targetMethod) {
        Predicate predicate = null;
        try {
            Class targetClass = entityClass;
            Path path = root;
            if (fieldName.contains(".")) {
                String[] fieldNames = fieldName.split("\\.");
                for (int i = 0; i < fieldNames.length; i++) {
                    targetClass = targetClass.getMethod(getMethod(fieldNames[i])).getReturnType();
                    path = path.get(fieldNames[i]);
                }
            } else {
                targetClass = targetClass.getMethod(getMethod(fieldName)).getReturnType();
                path = path.get(fieldName);
            }

            if (targetClass.equals(String.class)) {
                value = String.valueOf(value);
                predicate = (Predicate) targetMethod.invoke(cb, path.as(String.class), value);
            } else if (targetClass.equals(Long.class)) {
                value = Long.parseLong(value.toString());
                predicate = (Predicate) targetMethod.invoke(cb, path.as(Long.class), value);
            } else if (targetClass.equals(Integer.class)) {
                value = Integer.parseInt(value.toString());
                predicate = (Predicate) targetMethod.invoke(cb, path.as(Integer.class), value);
            } else if (targetClass.equals(Date.class)) {

                value = df.parse(value.toString());
                predicate = (Predicate) targetMethod.invoke(cb, path.as(Date.class), value);
            } else if (targetClass.equals(Double.class)) {
                value = Double.parseDouble(value.toString());
                predicate = (Predicate) targetMethod.invoke(cb, path.as(Double.class), value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return predicate;
    }

    private String getMethod(String fieldName) {
        return "get" + fieldName.substring(0,1).toUpperCase()+ fieldName.substring(1);
    }

    public Pageable pageable(QueryParam queryParam) {
        List<Sort.Order> orders = new ArrayList<>();
        updateOrders(queryParam, orders);
        return PageRequest.of(queryParam.getPage() > 1 ? queryParam.getPage() -1 : 0, queryParam.getRows() <= 0 ? 1 : queryParam.getRows(), Sort.by(orders));
    }

    private void updateOrders(QueryParam param, List<Sort.Order> orders) {
        List<SortField> sortFields = param.getSortFields();
        if (EmptyUtil.isObjEmpty(sortFields) || !param.isPagination()) {
            return;
        }

        sortFields.forEach((sortField -> {
            if (SortField.SORT_TYPE_ASC.equals(sortField.getType())) {
                orders.add(new Sort.Order(Sort.Direction.ASC, sortField.getField()));
            } else if (SortField.SORT_TYPE_DESC.equals(sortField.getType())) {
                orders.add(new Sort.Order(Sort.Direction.DESC, sortField.getField()));
            }
        }));
    }

    public Pager getPager(Page<T> page) {
        Pager pager = new Pager<T>();
        pager.setContent(page.getContent());
        pager.setTotalElements(page.getTotalElements());
        pager.setTotalPages(page.getTotalPages());
        return pager;
    }

    public Pager getPager(List<T> all) {
        Pager pager = new Pager<T>();
        pager.setContent(all);
        return pager;
    }
}

