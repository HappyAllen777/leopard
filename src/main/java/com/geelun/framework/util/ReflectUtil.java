package com.geelun.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.geelun.framework.base.superclass.model.BaseModel;
import com.geelun.framework.cache.BaseModelPropertiesCache;
import com.geelun.framework.cache.PersistentFieldsCache;
import com.geelun.framework.constant.ExceptionType;
import com.geelun.framework.exception.ReflectException;

public class ReflectUtil {

	/**
	 * 获取所有属性
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<Field> getDeclaredFields(Class clazz) {
		Field[] currentClassfields = clazz.getDeclaredFields();
		if (currentClassfields != null && currentClassfields.length > 0) {
			List<Field> fields = new ArrayList<Field>(currentClassfields.length);
			for (Field field : currentClassfields) {
				fields.add(field);
			}
			return fields;
		}
		return null;
	}

	/**
	 * 获取包含父类的所有属性
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<Field> getDeclaredFieldIncludeSuperClass(Class clazz) {
		List<Field> fields = new ArrayList<Field>(40);
		while (clazz != null) {
			List<Field> currentClassFields = getDeclaredFields(clazz);
			if (currentClassFields != null) {
				fields.addAll(currentClassFields);
			}
			clazz = clazz.getSuperclass();
		}

		if (ArrayUtil.isNotEmpty(fields)) {
			return fields;
		}

		return null;
	}

	/**
	 * <pre>
	 * 获取一个类或其父类中的字段
	 * @param clazz
	 * @param fieldName
	 * @return
	 * 
	 * <pre>
	 */
	public static Field getDeclaredField(Class clazz, String fieldName) {
		Field field = null;
		Class currentClass = clazz;
		while (field == null && currentClass != Object.class) {
			try {
				field = currentClass.getDeclaredField(fieldName);
			} catch (SecurityException e) {
			} catch (NoSuchFieldException e) {
				currentClass = clazz.getSuperclass();
			}
		}
		return field;
	}

	/**
	 * 获取类中的持久化属性，即除了static,有@Transient修饰的属性
	 * 
	 * @param baseModel
	 * @return
	 */
	public static List<Field> getPersistentFields(BaseModel baseModel) {
		String tableName = baseModel.getTableName();

		List<Field> persistentFields = PersistentFieldsCache.getFieldsByTableName(tableName);
		if (persistentFields != null) {
			System.out.println("PersistentFieldsCache hit: " + tableName);
			return persistentFields;
		}

		Class clazz = baseModel.getClass();
		persistentFields = getPersistentFields(clazz);
		PersistentFieldsCache.cachePersistentFields(tableName, persistentFields);

		return persistentFields;
	}

	/**
	 * 获取类中的持久化属性，即除了static,有@Transient修饰的属性
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<Field> getPersistentFields(Class clazz) {
		List<Field> allFields = getDeclaredFieldIncludeSuperClass(clazz);
		if (ArrayUtil.isNotEmpty(allFields)) {
			List<Field> persistentFields = new ArrayList<Field>(allFields.size());
			for (Field field : allFields) {
				int modifiers = field.getModifiers();
				if (Modifier.isStatic(modifiers)) {
					continue;
				}
				if (Modifier.isTransient(modifiers)) {
					continue;
				}
				persistentFields.add(field);
			}
			return persistentFields;
		}
		return null;
	}

	public static List<Method> getDeclaredMethods(Class clazz) {
		Method[] properties = clazz.getDeclaredMethods();
		if (properties != null && properties.length > 0) {
			List<Method> fields = new ArrayList<Method>(properties.length);
			for (Method method : properties) {
				fields.add(method);
			}
			return fields;
		}
		return null;
	}

	public static List<Method> getDeclaredMethodIncludeSuperClass(Class clazz) {
		List<Method> fields = new ArrayList<Method>(40);
		while (clazz != null) {
			List<Method> currentClassFields = getDeclaredMethods(clazz);
			if (currentClassFields != null) {
				fields.addAll(currentClassFields);
			}
			clazz = clazz.getSuperclass();
		}

		if (ArrayUtil.isNotEmpty(fields)) {
			return fields;
		}

		return null;
	}

	private static List<Method> getPropertiesMethods(Class clazz) {
		Method[] properties = clazz.getDeclaredMethods();
		if (properties != null && properties.length > 0) {
			List<Method> fields = new ArrayList<Method>(properties.length);
			for (Method method : properties) {
				String methodName = method.getName();
				if (methodName.startsWith("is") || methodName.startsWith("get")) {
					fields.add(method);
				}
			}
			return fields;
		}
		return null;
	}

	private static List<Method> getPropertiesMethodIncludeSuperClass(Class clazz) {
		List<Method> fields = new ArrayList<Method>(40);
		while (clazz != null && clazz != Object.class) {
			List<Method> currentClassFields = getPropertiesMethods(clazz);
			if (currentClassFields != null) {
				fields.addAll(currentClassFields);
			}
			clazz = clazz.getSuperclass();
		}

		if (ArrayUtil.isNotEmpty(fields)) {
			return fields;
		}

		return null;
	}

	public static List<Method> ensurePropertiesMethodIncludeSuperClass(Class clazz) {
		String className = clazz.getSimpleName();
		List<Method> properties = BaseModelPropertiesCache.getPropertiesByClassName(className);

		if (properties == null) {
			properties = ReflectUtil.getPropertiesMethodIncludeSuperClass(clazz);
			BaseModelPropertiesCache.cacheProperties(className, properties);
		}

		return properties;
	}

	public static Object getFieldValue(BaseModel baseModel, Field field) {
		try {
			field.setAccessible(true);
			return field.get(baseModel);
		} catch (IllegalArgumentException e) {
			String className = baseModel.getClass().getSimpleName();
			String filedName = field.getName();
			System.out.println("反射获取属性失败：请检查" + className + "类的" + filedName + "属性及其get/set方法");
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			String className = baseModel.getClass().getSimpleName();
			String filedName = field.getName();
			System.out.println("反射获取属性失败：请检查" + className + "类的" + filedName + "属性及其get/set方法");
			e.printStackTrace();
			return null;
		}
	}

	public static void setFieldValue(BaseModel baseModel, Field field, Object fieldValue) {
		field.setAccessible(true);
		try {
			field.set(baseModel, fieldValue);
		} catch (IllegalArgumentException e) {
			String className = baseModel.getClass().getSimpleName();
			String filedName = field.getName();
			System.out.println("反射获取属性失败：请检查" + className + "类的" + filedName + "属性及其get/set方法");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			String className = baseModel.getClass().getSimpleName();
			String filedName = field.getName();
			System.out.println("反射获取属性失败：请检查" + className + "类的" + filedName + "属性及其get/set方法");
			e.printStackTrace();
		}
	}

	public static Object newInstance(Class clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			System.out.println("反射创建对象失败 ：请检查" + clazz.getSimpleName() + "类是否可以实例化");
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.out.println("反射创建对象失败 ：请检查" + clazz.getSimpleName() + "类构造方法");
			return null;
		}
	}

	public static String getPropertyNameByGetter(Method getter) {
		String methodName = getter.getName();
		String propertyName = methodName;
		// 此处认为只有is和get开头的方法才是获取属性的方法
		if (methodName.startsWith("is")) {
			propertyName = methodName.substring(2, methodName.length());
		} else if (methodName.startsWith("get")) {
			propertyName = methodName.substring(3, methodName.length());
		} else {
			return null;
		}

		// 默认属性名为方法名去掉is或get后将首字母变为小写
		propertyName = propertyName.substring(0, 1).toLowerCase() + propertyName.substring(1, propertyName.length());
		return propertyName;
	}

	public static Method getGetterByPropertyName(Class clazz, String propertyName) throws ReflectException {
		String getterName = "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1, propertyName.length());
		try {
			return clazz.getMethod(getterName, new Class[] {});
		} catch (SecurityException e) {
			throw new ReflectException(ExceptionType.ReflectGetMethodFailedCode, "get" + propertyName
					+ ExceptionType.ReflectGetMethodFailedMsg);
		} catch (NoSuchMethodException e) {
			throw new ReflectException(ExceptionType.ReflectGetMethodFailedCode, "get" + propertyName
					+ ExceptionType.ReflectGetMethodFailedMsg);
		}
	}

	public static Object invoke(Method method, Object invoker, Object[] parameters) throws ReflectException {
		Object result = null;
		try {
			result = method.invoke(invoker, parameters);
		} catch (IllegalArgumentException e) {
			throw new ReflectException(ExceptionType.ReflectInvokeMethodFailedCode, ExceptionType.ReflectInvokeMethodFailedMsg);
		} catch (IllegalAccessException e) {
			throw new ReflectException(ExceptionType.ReflectInvokeMethodFailedCode, ExceptionType.ReflectInvokeMethodFailedMsg);
		} catch (InvocationTargetException e) {
			throw new ReflectException(ExceptionType.ReflectInvokeMethodFailedCode, ExceptionType.ReflectInvokeMethodFailedMsg);
		}
		return result;
	}
}
