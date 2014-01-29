package com.orange.ru.core;

import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.PropertyUtilsBean;

public class Utils {
  public static boolean compareObjects(Object oldObject, Object newObject) {
    BeanMap map = new BeanMap(oldObject);
    PropertyUtilsBean propUtils = new PropertyUtilsBean();

    try {
      for (Object propNameObject : map.keySet()) {
        String propertyName = (String) propNameObject;
        Object property1 = propUtils.getProperty(oldObject, propertyName);
        Object property2 = propUtils.getProperty(newObject, propertyName);
        if (!property1.equals(property2)) return false;
      }
      return true;
    } catch (IllegalAccessException |InvocationTargetException | NoSuchMethodException exception) {
      return false;
    }
  }
}