package com.geelun.framework.persistence.exception;

import com.geelun.framework.exception.LeopardException;

public class FieldTypeException extends LeopardException {
	private static final long serialVersionUID = 7474880888741488609L;

	public FieldTypeException(int fieldtypeuncorrectcode, String errMsg) {
		super(fieldtypeuncorrectcode, errMsg);
	}

}
