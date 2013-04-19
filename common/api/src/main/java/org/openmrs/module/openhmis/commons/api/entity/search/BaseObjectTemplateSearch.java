package org.openmrs.module.openhmis.commons.api.entity.search;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openmrs.OpenmrsObject;

import java.util.Date;

public class BaseObjectTemplateSearch<T extends OpenmrsObject> {
	public static final long serialVersionUID = 0L;

	public enum ComparisonType {
		EQUAL, IS_NULL, IS_NOT_NULL
	}

	public enum StringComparisonType {
		EQUAL, IS_NULL, IS_NOT_NULL, IS_EMPTY, IS_NOT_EMPTY, LIKE
	}

	public enum DateComparisonType {
		EQUAL, IS_NULL, IS_NOT_NULL, GREATER_THAN, GREATER_THAN_EQUAL, LESS_THAN, LESS_THAN_EQUAL,
	}

	public BaseObjectTemplateSearch(T template) {
		this.template = template;
	}

	private T template;

	public T getTemplate() {
		return template;
	}

	public void setTemplate(T template) {
		this.template = template;
	}

	public void updateCriteria(Criteria criteria) {
	}

	protected Criterion createCriterion(String field, Object value, ComparisonType comparisonType) {
		comparisonType = comparisonType == null ? ComparisonType.EQUAL : comparisonType;

		Criterion result;
		switch (comparisonType) {
			case EQUAL:
				result = Restrictions.eq(field, value);
				break;
			case IS_NULL:
				result = Restrictions.isNull(field);
				break;
			case IS_NOT_NULL:
				result = Restrictions.isNotNull(field);
				break;
			default:
				throw new IllegalArgumentException();
		}

		return result;
	}

	protected Criterion createCriterion(String field, String value, StringComparisonType comparisonType) {
		comparisonType = comparisonType == null ? StringComparisonType.EQUAL : comparisonType;

		Criterion result;
		switch (comparisonType) {
			case EQUAL:
				result = Restrictions.eq(field, value);
				break;
			case IS_NULL:
				result = Restrictions.isNull(field);
				break;
			case IS_NOT_NULL:
				result = Restrictions.isNotNull(field);
				break;
			case IS_EMPTY:
				result = Restrictions.isEmpty(field);
				break;
			case IS_NOT_EMPTY:
				result = Restrictions.isNotEmpty(field);
				break;
			case LIKE:
				result = Restrictions.ilike(field, value);
				break;
			default:
				throw new IllegalArgumentException();
		}

		return  result;
	}

	protected Criterion createCriterion(String field, Date value, DateComparisonType comparisonType) {
		comparisonType = comparisonType == null ? DateComparisonType.EQUAL : comparisonType;

		Criterion result;
		switch (comparisonType) {
			case EQUAL:
				result = Restrictions.eq(field, value);
				break;
			case IS_NULL:
				result = Restrictions.isNull(field);
				break;
			case IS_NOT_NULL:
				result = Restrictions.isNotNull(field);
				break;
			case GREATER_THAN:
				result = Restrictions.gt(field, value);
				break;
			case GREATER_THAN_EQUAL:
				result = Restrictions.ge(field, value);
				break;
			case LESS_THAN:
				result = Restrictions.lt(field, value);
				break;
			case LESS_THAN_EQUAL:
				result = Restrictions.le(field, value);
				break;
			default:
				throw new IllegalArgumentException();
		}

		return result;
	}
}

