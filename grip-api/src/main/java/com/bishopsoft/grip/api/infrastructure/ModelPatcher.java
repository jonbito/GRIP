package com.bishopsoft.grip.api.infrastructure;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

public class ModelPatcher {
    public static void patch(Object model, Object updates) {
        for (Field field : updates.getClass().getDeclaredFields()) {
            try {
                String getMethodName = "get" + StringUtils.capitalize(field.getName());
                Optional<?> fieldValue = (Optional<?>)updates.getClass().getDeclaredMethod(getMethodName).invoke(updates);
                // if the value isn't included in json, then the optional will be null
                if(fieldValue != null) {
                    String setMethodName = "set" + StringUtils.capitalize(field.getName());
                    for (Method method : model.getClass().getDeclaredMethods()) {
                        if(method.getName().equals(setMethodName)) {
                            // if the value in json is null, then it won't be present in the optional
                            if(fieldValue.isPresent()) {
                                method.invoke(model, fieldValue.get());
                            } else {
                                method.invoke(model, new Object[] {null});
                            }
                        }
                    }
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                // ignored
            }
        }
    }
}
