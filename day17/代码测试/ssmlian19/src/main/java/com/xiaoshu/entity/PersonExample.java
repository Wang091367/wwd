package com.xiaoshu.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PersonExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PersonExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPNameIsNull() {
            addCriterion("p_name is null");
            return (Criteria) this;
        }

        public Criteria andPNameIsNotNull() {
            addCriterion("p_name is not null");
            return (Criteria) this;
        }

        public Criteria andPNameEqualTo(String value) {
            addCriterion("p_name =", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameNotEqualTo(String value) {
            addCriterion("p_name <>", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameGreaterThan(String value) {
            addCriterion("p_name >", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameGreaterThanOrEqualTo(String value) {
            addCriterion("p_name >=", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameLessThan(String value) {
            addCriterion("p_name <", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameLessThanOrEqualTo(String value) {
            addCriterion("p_name <=", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameLike(String value) {
            addCriterion("p_name like", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameNotLike(String value) {
            addCriterion("p_name not like", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameIn(List<String> values) {
            addCriterion("p_name in", values, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameNotIn(List<String> values) {
            addCriterion("p_name not in", values, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameBetween(String value1, String value2) {
            addCriterion("p_name between", value1, value2, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameNotBetween(String value1, String value2) {
            addCriterion("p_name not between", value1, value2, "pName");
            return (Criteria) this;
        }

        public Criteria andPGenderIsNull() {
            addCriterion("p_gender is null");
            return (Criteria) this;
        }

        public Criteria andPGenderIsNotNull() {
            addCriterion("p_gender is not null");
            return (Criteria) this;
        }

        public Criteria andPGenderEqualTo(String value) {
            addCriterion("p_gender =", value, "pGender");
            return (Criteria) this;
        }

        public Criteria andPGenderNotEqualTo(String value) {
            addCriterion("p_gender <>", value, "pGender");
            return (Criteria) this;
        }

        public Criteria andPGenderGreaterThan(String value) {
            addCriterion("p_gender >", value, "pGender");
            return (Criteria) this;
        }

        public Criteria andPGenderGreaterThanOrEqualTo(String value) {
            addCriterion("p_gender >=", value, "pGender");
            return (Criteria) this;
        }

        public Criteria andPGenderLessThan(String value) {
            addCriterion("p_gender <", value, "pGender");
            return (Criteria) this;
        }

        public Criteria andPGenderLessThanOrEqualTo(String value) {
            addCriterion("p_gender <=", value, "pGender");
            return (Criteria) this;
        }

        public Criteria andPGenderLike(String value) {
            addCriterion("p_gender like", value, "pGender");
            return (Criteria) this;
        }

        public Criteria andPGenderNotLike(String value) {
            addCriterion("p_gender not like", value, "pGender");
            return (Criteria) this;
        }

        public Criteria andPGenderIn(List<String> values) {
            addCriterion("p_gender in", values, "pGender");
            return (Criteria) this;
        }

        public Criteria andPGenderNotIn(List<String> values) {
            addCriterion("p_gender not in", values, "pGender");
            return (Criteria) this;
        }

        public Criteria andPGenderBetween(String value1, String value2) {
            addCriterion("p_gender between", value1, value2, "pGender");
            return (Criteria) this;
        }

        public Criteria andPGenderNotBetween(String value1, String value2) {
            addCriterion("p_gender not between", value1, value2, "pGender");
            return (Criteria) this;
        }

        public Criteria andPCidIsNull() {
            addCriterion("p_cid is null");
            return (Criteria) this;
        }

        public Criteria andPCidIsNotNull() {
            addCriterion("p_cid is not null");
            return (Criteria) this;
        }

        public Criteria andPCidEqualTo(Integer value) {
            addCriterion("p_cid =", value, "pCid");
            return (Criteria) this;
        }

        public Criteria andPCidNotEqualTo(Integer value) {
            addCriterion("p_cid <>", value, "pCid");
            return (Criteria) this;
        }

        public Criteria andPCidGreaterThan(Integer value) {
            addCriterion("p_cid >", value, "pCid");
            return (Criteria) this;
        }

        public Criteria andPCidGreaterThanOrEqualTo(Integer value) {
            addCriterion("p_cid >=", value, "pCid");
            return (Criteria) this;
        }

        public Criteria andPCidLessThan(Integer value) {
            addCriterion("p_cid <", value, "pCid");
            return (Criteria) this;
        }

        public Criteria andPCidLessThanOrEqualTo(Integer value) {
            addCriterion("p_cid <=", value, "pCid");
            return (Criteria) this;
        }

        public Criteria andPCidIn(List<Integer> values) {
            addCriterion("p_cid in", values, "pCid");
            return (Criteria) this;
        }

        public Criteria andPCidNotIn(List<Integer> values) {
            addCriterion("p_cid not in", values, "pCid");
            return (Criteria) this;
        }

        public Criteria andPCidBetween(Integer value1, Integer value2) {
            addCriterion("p_cid between", value1, value2, "pCid");
            return (Criteria) this;
        }

        public Criteria andPCidNotBetween(Integer value1, Integer value2) {
            addCriterion("p_cid not between", value1, value2, "pCid");
            return (Criteria) this;
        }

        public Criteria andPSalaryIsNull() {
            addCriterion("p_salary is null");
            return (Criteria) this;
        }

        public Criteria andPSalaryIsNotNull() {
            addCriterion("p_salary is not null");
            return (Criteria) this;
        }

        public Criteria andPSalaryEqualTo(String value) {
            addCriterion("p_salary =", value, "pSalary");
            return (Criteria) this;
        }

        public Criteria andPSalaryNotEqualTo(String value) {
            addCriterion("p_salary <>", value, "pSalary");
            return (Criteria) this;
        }

        public Criteria andPSalaryGreaterThan(String value) {
            addCriterion("p_salary >", value, "pSalary");
            return (Criteria) this;
        }

        public Criteria andPSalaryGreaterThanOrEqualTo(String value) {
            addCriterion("p_salary >=", value, "pSalary");
            return (Criteria) this;
        }

        public Criteria andPSalaryLessThan(String value) {
            addCriterion("p_salary <", value, "pSalary");
            return (Criteria) this;
        }

        public Criteria andPSalaryLessThanOrEqualTo(String value) {
            addCriterion("p_salary <=", value, "pSalary");
            return (Criteria) this;
        }

        public Criteria andPSalaryLike(String value) {
            addCriterion("p_salary like", value, "pSalary");
            return (Criteria) this;
        }

        public Criteria andPSalaryNotLike(String value) {
            addCriterion("p_salary not like", value, "pSalary");
            return (Criteria) this;
        }

        public Criteria andPSalaryIn(List<String> values) {
            addCriterion("p_salary in", values, "pSalary");
            return (Criteria) this;
        }

        public Criteria andPSalaryNotIn(List<String> values) {
            addCriterion("p_salary not in", values, "pSalary");
            return (Criteria) this;
        }

        public Criteria andPSalaryBetween(String value1, String value2) {
            addCriterion("p_salary between", value1, value2, "pSalary");
            return (Criteria) this;
        }

        public Criteria andPSalaryNotBetween(String value1, String value2) {
            addCriterion("p_salary not between", value1, value2, "pSalary");
            return (Criteria) this;
        }

        public Criteria andPAgeIsNull() {
            addCriterion("p_age is null");
            return (Criteria) this;
        }

        public Criteria andPAgeIsNotNull() {
            addCriterion("p_age is not null");
            return (Criteria) this;
        }

        public Criteria andPAgeEqualTo(Integer value) {
            addCriterion("p_age =", value, "pAge");
            return (Criteria) this;
        }

        public Criteria andPAgeNotEqualTo(Integer value) {
            addCriterion("p_age <>", value, "pAge");
            return (Criteria) this;
        }

        public Criteria andPAgeGreaterThan(Integer value) {
            addCriterion("p_age >", value, "pAge");
            return (Criteria) this;
        }

        public Criteria andPAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("p_age >=", value, "pAge");
            return (Criteria) this;
        }

        public Criteria andPAgeLessThan(Integer value) {
            addCriterion("p_age <", value, "pAge");
            return (Criteria) this;
        }

        public Criteria andPAgeLessThanOrEqualTo(Integer value) {
            addCriterion("p_age <=", value, "pAge");
            return (Criteria) this;
        }

        public Criteria andPAgeIn(List<Integer> values) {
            addCriterion("p_age in", values, "pAge");
            return (Criteria) this;
        }

        public Criteria andPAgeNotIn(List<Integer> values) {
            addCriterion("p_age not in", values, "pAge");
            return (Criteria) this;
        }

        public Criteria andPAgeBetween(Integer value1, Integer value2) {
            addCriterion("p_age between", value1, value2, "pAge");
            return (Criteria) this;
        }

        public Criteria andPAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("p_age not between", value1, value2, "pAge");
            return (Criteria) this;
        }

        public Criteria andPEntrytimeIsNull() {
            addCriterion("p_entrytime is null");
            return (Criteria) this;
        }

        public Criteria andPEntrytimeIsNotNull() {
            addCriterion("p_entrytime is not null");
            return (Criteria) this;
        }

        public Criteria andPEntrytimeEqualTo(Date value) {
            addCriterionForJDBCDate("p_entrytime =", value, "pEntrytime");
            return (Criteria) this;
        }

        public Criteria andPEntrytimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("p_entrytime <>", value, "pEntrytime");
            return (Criteria) this;
        }

        public Criteria andPEntrytimeGreaterThan(Date value) {
            addCriterionForJDBCDate("p_entrytime >", value, "pEntrytime");
            return (Criteria) this;
        }

        public Criteria andPEntrytimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("p_entrytime >=", value, "pEntrytime");
            return (Criteria) this;
        }

        public Criteria andPEntrytimeLessThan(Date value) {
            addCriterionForJDBCDate("p_entrytime <", value, "pEntrytime");
            return (Criteria) this;
        }

        public Criteria andPEntrytimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("p_entrytime <=", value, "pEntrytime");
            return (Criteria) this;
        }

        public Criteria andPEntrytimeIn(List<Date> values) {
            addCriterionForJDBCDate("p_entrytime in", values, "pEntrytime");
            return (Criteria) this;
        }

        public Criteria andPEntrytimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("p_entrytime not in", values, "pEntrytime");
            return (Criteria) this;
        }

        public Criteria andPEntrytimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("p_entrytime between", value1, value2, "pEntrytime");
            return (Criteria) this;
        }

        public Criteria andPEntrytimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("p_entrytime not between", value1, value2, "pEntrytime");
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