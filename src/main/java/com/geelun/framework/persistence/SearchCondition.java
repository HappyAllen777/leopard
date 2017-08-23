package com.geelun.framework.persistence;

import java.io.Serializable;

public class SearchCondition implements Serializable {

	private static final long serialVersionUID = -5298414303312294386L;

	private SearchConditionType type;
	
	private String sql;
	
	private String fieldName;
	
	private String logicOperator;
	
}
