package com.cys.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysShopExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysShopExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("shop_name is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("shop_name <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("shop_name >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("shop_name <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("shop_name <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("shop_name not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("shop_name in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("shop_name not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("shop_name between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("shop_name not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopAdressIsNull() {
            addCriterion("shop_adress is null");
            return (Criteria) this;
        }

        public Criteria andShopAdressIsNotNull() {
            addCriterion("shop_adress is not null");
            return (Criteria) this;
        }

        public Criteria andShopAdressEqualTo(String value) {
            addCriterion("shop_adress =", value, "shopAdress");
            return (Criteria) this;
        }

        public Criteria andShopAdressNotEqualTo(String value) {
            addCriterion("shop_adress <>", value, "shopAdress");
            return (Criteria) this;
        }

        public Criteria andShopAdressGreaterThan(String value) {
            addCriterion("shop_adress >", value, "shopAdress");
            return (Criteria) this;
        }

        public Criteria andShopAdressGreaterThanOrEqualTo(String value) {
            addCriterion("shop_adress >=", value, "shopAdress");
            return (Criteria) this;
        }

        public Criteria andShopAdressLessThan(String value) {
            addCriterion("shop_adress <", value, "shopAdress");
            return (Criteria) this;
        }

        public Criteria andShopAdressLessThanOrEqualTo(String value) {
            addCriterion("shop_adress <=", value, "shopAdress");
            return (Criteria) this;
        }

        public Criteria andShopAdressLike(String value) {
            addCriterion("shop_adress like", value, "shopAdress");
            return (Criteria) this;
        }

        public Criteria andShopAdressNotLike(String value) {
            addCriterion("shop_adress not like", value, "shopAdress");
            return (Criteria) this;
        }

        public Criteria andShopAdressIn(List<String> values) {
            addCriterion("shop_adress in", values, "shopAdress");
            return (Criteria) this;
        }

        public Criteria andShopAdressNotIn(List<String> values) {
            addCriterion("shop_adress not in", values, "shopAdress");
            return (Criteria) this;
        }

        public Criteria andShopAdressBetween(String value1, String value2) {
            addCriterion("shop_adress between", value1, value2, "shopAdress");
            return (Criteria) this;
        }

        public Criteria andShopAdressNotBetween(String value1, String value2) {
            addCriterion("shop_adress not between", value1, value2, "shopAdress");
            return (Criteria) this;
        }

        public Criteria andShopZbYIsNull() {
            addCriterion("shop_zb_y is null");
            return (Criteria) this;
        }

        public Criteria andShopZbYIsNotNull() {
            addCriterion("shop_zb_y is not null");
            return (Criteria) this;
        }

        public Criteria andShopZbYEqualTo(Float value) {
            addCriterion("shop_zb_y =", value, "shopZbY");
            return (Criteria) this;
        }

        public Criteria andShopZbYNotEqualTo(Float value) {
            addCriterion("shop_zb_y <>", value, "shopZbY");
            return (Criteria) this;
        }

        public Criteria andShopZbYGreaterThan(Float value) {
            addCriterion("shop_zb_y >", value, "shopZbY");
            return (Criteria) this;
        }

        public Criteria andShopZbYGreaterThanOrEqualTo(Float value) {
            addCriterion("shop_zb_y >=", value, "shopZbY");
            return (Criteria) this;
        }

        public Criteria andShopZbYLessThan(Float value) {
            addCriterion("shop_zb_y <", value, "shopZbY");
            return (Criteria) this;
        }

        public Criteria andShopZbYLessThanOrEqualTo(Float value) {
            addCriterion("shop_zb_y <=", value, "shopZbY");
            return (Criteria) this;
        }

        public Criteria andShopZbYIn(List<Float> values) {
            addCriterion("shop_zb_y in", values, "shopZbY");
            return (Criteria) this;
        }

        public Criteria andShopZbYNotIn(List<Float> values) {
            addCriterion("shop_zb_y not in", values, "shopZbY");
            return (Criteria) this;
        }

        public Criteria andShopZbYBetween(Float value1, Float value2) {
            addCriterion("shop_zb_y between", value1, value2, "shopZbY");
            return (Criteria) this;
        }

        public Criteria andShopZbYNotBetween(Float value1, Float value2) {
            addCriterion("shop_zb_y not between", value1, value2, "shopZbY");
            return (Criteria) this;
        }

        public Criteria andShopZbXIsNull() {
            addCriterion("shop_zb_x is null");
            return (Criteria) this;
        }

        public Criteria andShopZbXIsNotNull() {
            addCriterion("shop_zb_x is not null");
            return (Criteria) this;
        }

        public Criteria andShopZbXEqualTo(Float value) {
            addCriterion("shop_zb_x =", value, "shopZbX");
            return (Criteria) this;
        }

        public Criteria andShopZbXNotEqualTo(Float value) {
            addCriterion("shop_zb_x <>", value, "shopZbX");
            return (Criteria) this;
        }

        public Criteria andShopZbXGreaterThan(Float value) {
            addCriterion("shop_zb_x >", value, "shopZbX");
            return (Criteria) this;
        }

        public Criteria andShopZbXGreaterThanOrEqualTo(Float value) {
            addCriterion("shop_zb_x >=", value, "shopZbX");
            return (Criteria) this;
        }

        public Criteria andShopZbXLessThan(Float value) {
            addCriterion("shop_zb_x <", value, "shopZbX");
            return (Criteria) this;
        }

        public Criteria andShopZbXLessThanOrEqualTo(Float value) {
            addCriterion("shop_zb_x <=", value, "shopZbX");
            return (Criteria) this;
        }

        public Criteria andShopZbXIn(List<Float> values) {
            addCriterion("shop_zb_x in", values, "shopZbX");
            return (Criteria) this;
        }

        public Criteria andShopZbXNotIn(List<Float> values) {
            addCriterion("shop_zb_x not in", values, "shopZbX");
            return (Criteria) this;
        }

        public Criteria andShopZbXBetween(Float value1, Float value2) {
            addCriterion("shop_zb_x between", value1, value2, "shopZbX");
            return (Criteria) this;
        }

        public Criteria andShopZbXNotBetween(Float value1, Float value2) {
            addCriterion("shop_zb_x not between", value1, value2, "shopZbX");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIsNull() {
            addCriterion("service_type is null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIsNotNull() {
            addCriterion("service_type is not null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeEqualTo(String value) {
            addCriterion("service_type =", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotEqualTo(String value) {
            addCriterion("service_type <>", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeGreaterThan(String value) {
            addCriterion("service_type >", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("service_type >=", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLessThan(String value) {
            addCriterion("service_type <", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLessThanOrEqualTo(String value) {
            addCriterion("service_type <=", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLike(String value) {
            addCriterion("service_type like", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotLike(String value) {
            addCriterion("service_type not like", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIn(List<String> values) {
            addCriterion("service_type in", values, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotIn(List<String> values) {
            addCriterion("service_type not in", values, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeBetween(String value1, String value2) {
            addCriterion("service_type between", value1, value2, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotBetween(String value1, String value2) {
            addCriterion("service_type not between", value1, value2, "serviceType");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Short value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Short value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Short value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Short value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Short value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Short> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Short> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Short value1, Short value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Short value1, Short value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andShopImgUrlIsNull() {
            addCriterion("shop_img_url is null");
            return (Criteria) this;
        }

        public Criteria andShopImgUrlIsNotNull() {
            addCriterion("shop_img_url is not null");
            return (Criteria) this;
        }

        public Criteria andShopImgUrlEqualTo(String value) {
            addCriterion("shop_img_url =", value, "shopImgUrl");
            return (Criteria) this;
        }

        public Criteria andShopImgUrlNotEqualTo(String value) {
            addCriterion("shop_img_url <>", value, "shopImgUrl");
            return (Criteria) this;
        }

        public Criteria andShopImgUrlGreaterThan(String value) {
            addCriterion("shop_img_url >", value, "shopImgUrl");
            return (Criteria) this;
        }

        public Criteria andShopImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("shop_img_url >=", value, "shopImgUrl");
            return (Criteria) this;
        }

        public Criteria andShopImgUrlLessThan(String value) {
            addCriterion("shop_img_url <", value, "shopImgUrl");
            return (Criteria) this;
        }

        public Criteria andShopImgUrlLessThanOrEqualTo(String value) {
            addCriterion("shop_img_url <=", value, "shopImgUrl");
            return (Criteria) this;
        }

        public Criteria andShopImgUrlLike(String value) {
            addCriterion("shop_img_url like", value, "shopImgUrl");
            return (Criteria) this;
        }

        public Criteria andShopImgUrlNotLike(String value) {
            addCriterion("shop_img_url not like", value, "shopImgUrl");
            return (Criteria) this;
        }

        public Criteria andShopImgUrlIn(List<String> values) {
            addCriterion("shop_img_url in", values, "shopImgUrl");
            return (Criteria) this;
        }

        public Criteria andShopImgUrlNotIn(List<String> values) {
            addCriterion("shop_img_url not in", values, "shopImgUrl");
            return (Criteria) this;
        }

        public Criteria andShopImgUrlBetween(String value1, String value2) {
            addCriterion("shop_img_url between", value1, value2, "shopImgUrl");
            return (Criteria) this;
        }

        public Criteria andShopImgUrlNotBetween(String value1, String value2) {
            addCriterion("shop_img_url not between", value1, value2, "shopImgUrl");
            return (Criteria) this;
        }

        public Criteria andYyImgIsNull() {
            addCriterion("yy_img is null");
            return (Criteria) this;
        }

        public Criteria andYyImgIsNotNull() {
            addCriterion("yy_img is not null");
            return (Criteria) this;
        }

        public Criteria andYyImgEqualTo(String value) {
            addCriterion("yy_img =", value, "yyImg");
            return (Criteria) this;
        }

        public Criteria andYyImgNotEqualTo(String value) {
            addCriterion("yy_img <>", value, "yyImg");
            return (Criteria) this;
        }

        public Criteria andYyImgGreaterThan(String value) {
            addCriterion("yy_img >", value, "yyImg");
            return (Criteria) this;
        }

        public Criteria andYyImgGreaterThanOrEqualTo(String value) {
            addCriterion("yy_img >=", value, "yyImg");
            return (Criteria) this;
        }

        public Criteria andYyImgLessThan(String value) {
            addCriterion("yy_img <", value, "yyImg");
            return (Criteria) this;
        }

        public Criteria andYyImgLessThanOrEqualTo(String value) {
            addCriterion("yy_img <=", value, "yyImg");
            return (Criteria) this;
        }

        public Criteria andYyImgLike(String value) {
            addCriterion("yy_img like", value, "yyImg");
            return (Criteria) this;
        }

        public Criteria andYyImgNotLike(String value) {
            addCriterion("yy_img not like", value, "yyImg");
            return (Criteria) this;
        }

        public Criteria andYyImgIn(List<String> values) {
            addCriterion("yy_img in", values, "yyImg");
            return (Criteria) this;
        }

        public Criteria andYyImgNotIn(List<String> values) {
            addCriterion("yy_img not in", values, "yyImg");
            return (Criteria) this;
        }

        public Criteria andYyImgBetween(String value1, String value2) {
            addCriterion("yy_img between", value1, value2, "yyImg");
            return (Criteria) this;
        }

        public Criteria andYyImgNotBetween(String value1, String value2) {
            addCriterion("yy_img not between", value1, value2, "yyImg");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNull() {
            addCriterion("last_update_time is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNotNull() {
            addCriterion("last_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeEqualTo(Date value) {
            addCriterion("last_update_time =", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotEqualTo(Date value) {
            addCriterion("last_update_time <>", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThan(Date value) {
            addCriterion("last_update_time >", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_update_time >=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThan(Date value) {
            addCriterion("last_update_time <", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_update_time <=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIn(List<Date> values) {
            addCriterion("last_update_time in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotIn(List<Date> values) {
            addCriterion("last_update_time not in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("last_update_time between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_update_time not between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andOwerUserIdIsNull() {
            addCriterion("ower_user_id is null");
            return (Criteria) this;
        }

        public Criteria andOwerUserIdIsNotNull() {
            addCriterion("ower_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andOwerUserIdEqualTo(Integer value) {
            addCriterion("ower_user_id =", value, "owerUserId");
            return (Criteria) this;
        }

        public Criteria andOwerUserIdNotEqualTo(Integer value) {
            addCriterion("ower_user_id <>", value, "owerUserId");
            return (Criteria) this;
        }

        public Criteria andOwerUserIdGreaterThan(Integer value) {
            addCriterion("ower_user_id >", value, "owerUserId");
            return (Criteria) this;
        }

        public Criteria andOwerUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ower_user_id >=", value, "owerUserId");
            return (Criteria) this;
        }

        public Criteria andOwerUserIdLessThan(Integer value) {
            addCriterion("ower_user_id <", value, "owerUserId");
            return (Criteria) this;
        }

        public Criteria andOwerUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("ower_user_id <=", value, "owerUserId");
            return (Criteria) this;
        }

        public Criteria andOwerUserIdIn(List<Integer> values) {
            addCriterion("ower_user_id in", values, "owerUserId");
            return (Criteria) this;
        }

        public Criteria andOwerUserIdNotIn(List<Integer> values) {
            addCriterion("ower_user_id not in", values, "owerUserId");
            return (Criteria) this;
        }

        public Criteria andOwerUserIdBetween(Integer value1, Integer value2) {
            addCriterion("ower_user_id between", value1, value2, "owerUserId");
            return (Criteria) this;
        }

        public Criteria andOwerUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ower_user_id not between", value1, value2, "owerUserId");
            return (Criteria) this;
        }

        public Criteria andDescIsNull() {
            addCriterion("desc is null");
            return (Criteria) this;
        }

        public Criteria andDescIsNotNull() {
            addCriterion("desc is not null");
            return (Criteria) this;
        }

        public Criteria andDescEqualTo(String value) {
            addCriterion("desc =", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotEqualTo(String value) {
            addCriterion("desc <>", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThan(String value) {
            addCriterion("desc >", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThanOrEqualTo(String value) {
            addCriterion("desc >=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThan(String value) {
            addCriterion("desc <", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThanOrEqualTo(String value) {
            addCriterion("desc <=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLike(String value) {
            addCriterion("desc like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotLike(String value) {
            addCriterion("desc not like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescIn(List<String> values) {
            addCriterion("desc in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotIn(List<String> values) {
            addCriterion("desc not in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescBetween(String value1, String value2) {
            addCriterion("desc between", value1, value2, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotBetween(String value1, String value2) {
            addCriterion("desc not between", value1, value2, "desc");
            return (Criteria) this;
        }

        public Criteria andShopLevelIsNull() {
            addCriterion("shop_level is null");
            return (Criteria) this;
        }

        public Criteria andShopLevelIsNotNull() {
            addCriterion("shop_level is not null");
            return (Criteria) this;
        }

        public Criteria andShopLevelEqualTo(Short value) {
            addCriterion("shop_level =", value, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelNotEqualTo(Short value) {
            addCriterion("shop_level <>", value, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelGreaterThan(Short value) {
            addCriterion("shop_level >", value, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelGreaterThanOrEqualTo(Short value) {
            addCriterion("shop_level >=", value, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelLessThan(Short value) {
            addCriterion("shop_level <", value, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelLessThanOrEqualTo(Short value) {
            addCriterion("shop_level <=", value, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelIn(List<Short> values) {
            addCriterion("shop_level in", values, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelNotIn(List<Short> values) {
            addCriterion("shop_level not in", values, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelBetween(Short value1, Short value2) {
            addCriterion("shop_level between", value1, value2, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelNotBetween(Short value1, Short value2) {
            addCriterion("shop_level not between", value1, value2, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andYl1IsNull() {
            addCriterion("yl1 is null");
            return (Criteria) this;
        }

        public Criteria andYl1IsNotNull() {
            addCriterion("yl1 is not null");
            return (Criteria) this;
        }

        public Criteria andYl1EqualTo(String value) {
            addCriterion("yl1 =", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1NotEqualTo(String value) {
            addCriterion("yl1 <>", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1GreaterThan(String value) {
            addCriterion("yl1 >", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1GreaterThanOrEqualTo(String value) {
            addCriterion("yl1 >=", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1LessThan(String value) {
            addCriterion("yl1 <", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1LessThanOrEqualTo(String value) {
            addCriterion("yl1 <=", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1Like(String value) {
            addCriterion("yl1 like", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1NotLike(String value) {
            addCriterion("yl1 not like", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1In(List<String> values) {
            addCriterion("yl1 in", values, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1NotIn(List<String> values) {
            addCriterion("yl1 not in", values, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1Between(String value1, String value2) {
            addCriterion("yl1 between", value1, value2, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1NotBetween(String value1, String value2) {
            addCriterion("yl1 not between", value1, value2, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl2IsNull() {
            addCriterion("yl2 is null");
            return (Criteria) this;
        }

        public Criteria andYl2IsNotNull() {
            addCriterion("yl2 is not null");
            return (Criteria) this;
        }

        public Criteria andYl2EqualTo(String value) {
            addCriterion("yl2 =", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2NotEqualTo(String value) {
            addCriterion("yl2 <>", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2GreaterThan(String value) {
            addCriterion("yl2 >", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2GreaterThanOrEqualTo(String value) {
            addCriterion("yl2 >=", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2LessThan(String value) {
            addCriterion("yl2 <", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2LessThanOrEqualTo(String value) {
            addCriterion("yl2 <=", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2Like(String value) {
            addCriterion("yl2 like", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2NotLike(String value) {
            addCriterion("yl2 not like", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2In(List<String> values) {
            addCriterion("yl2 in", values, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2NotIn(List<String> values) {
            addCriterion("yl2 not in", values, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2Between(String value1, String value2) {
            addCriterion("yl2 between", value1, value2, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2NotBetween(String value1, String value2) {
            addCriterion("yl2 not between", value1, value2, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl3IsNull() {
            addCriterion("yl3 is null");
            return (Criteria) this;
        }

        public Criteria andYl3IsNotNull() {
            addCriterion("yl3 is not null");
            return (Criteria) this;
        }

        public Criteria andYl3EqualTo(String value) {
            addCriterion("yl3 =", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3NotEqualTo(String value) {
            addCriterion("yl3 <>", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3GreaterThan(String value) {
            addCriterion("yl3 >", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3GreaterThanOrEqualTo(String value) {
            addCriterion("yl3 >=", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3LessThan(String value) {
            addCriterion("yl3 <", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3LessThanOrEqualTo(String value) {
            addCriterion("yl3 <=", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3Like(String value) {
            addCriterion("yl3 like", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3NotLike(String value) {
            addCriterion("yl3 not like", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3In(List<String> values) {
            addCriterion("yl3 in", values, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3NotIn(List<String> values) {
            addCriterion("yl3 not in", values, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3Between(String value1, String value2) {
            addCriterion("yl3 between", value1, value2, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3NotBetween(String value1, String value2) {
            addCriterion("yl3 not between", value1, value2, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl4IsNull() {
            addCriterion("yl4 is null");
            return (Criteria) this;
        }

        public Criteria andYl4IsNotNull() {
            addCriterion("yl4 is not null");
            return (Criteria) this;
        }

        public Criteria andYl4EqualTo(String value) {
            addCriterion("yl4 =", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4NotEqualTo(String value) {
            addCriterion("yl4 <>", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4GreaterThan(String value) {
            addCriterion("yl4 >", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4GreaterThanOrEqualTo(String value) {
            addCriterion("yl4 >=", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4LessThan(String value) {
            addCriterion("yl4 <", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4LessThanOrEqualTo(String value) {
            addCriterion("yl4 <=", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4Like(String value) {
            addCriterion("yl4 like", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4NotLike(String value) {
            addCriterion("yl4 not like", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4In(List<String> values) {
            addCriterion("yl4 in", values, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4NotIn(List<String> values) {
            addCriterion("yl4 not in", values, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4Between(String value1, String value2) {
            addCriterion("yl4 between", value1, value2, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4NotBetween(String value1, String value2) {
            addCriterion("yl4 not between", value1, value2, "yl4");
            return (Criteria) this;
        }

        public Criteria andShopNameLikeInsensitive(String value) {
            addCriterion("upper(shop_name) like", value.toUpperCase(), "shopName");
            return (Criteria) this;
        }

        public Criteria andShopAdressLikeInsensitive(String value) {
            addCriterion("upper(shop_adress) like", value.toUpperCase(), "shopAdress");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLikeInsensitive(String value) {
            addCriterion("upper(service_type) like", value.toUpperCase(), "serviceType");
            return (Criteria) this;
        }

        public Criteria andShopImgUrlLikeInsensitive(String value) {
            addCriterion("upper(shop_img_url) like", value.toUpperCase(), "shopImgUrl");
            return (Criteria) this;
        }

        public Criteria andYyImgLikeInsensitive(String value) {
            addCriterion("upper(yy_img) like", value.toUpperCase(), "yyImg");
            return (Criteria) this;
        }

        public Criteria andDescLikeInsensitive(String value) {
            addCriterion("upper(desc) like", value.toUpperCase(), "desc");
            return (Criteria) this;
        }

        public Criteria andYl1LikeInsensitive(String value) {
            addCriterion("upper(yl1) like", value.toUpperCase(), "yl1");
            return (Criteria) this;
        }

        public Criteria andYl2LikeInsensitive(String value) {
            addCriterion("upper(yl2) like", value.toUpperCase(), "yl2");
            return (Criteria) this;
        }

        public Criteria andYl3LikeInsensitive(String value) {
            addCriterion("upper(yl3) like", value.toUpperCase(), "yl3");
            return (Criteria) this;
        }

        public Criteria andYl4LikeInsensitive(String value) {
            addCriterion("upper(yl4) like", value.toUpperCase(), "yl4");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}