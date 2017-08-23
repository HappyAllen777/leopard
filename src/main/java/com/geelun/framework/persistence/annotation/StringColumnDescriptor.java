package com.geelun.framework.persistence.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.geelun.framework.persistence.column.StringColumnType;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StringColumnDescriptor {
	String name();

	StringColumnType[] type();

	int maxLen() default -1;

	int minLen() default -1;
}
