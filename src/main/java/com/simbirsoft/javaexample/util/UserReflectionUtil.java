package com.simbirsoft.javaexample.util;

import com.simbirsoft.javaexample.dto.PersonDTO;

import java.lang.reflect.Field;

/**
 * Действия с юзером с помощью рефлексии
 */
public class UserReflectionUtil {
    private Field[] fields;

    public UserReflectionUtil() {
        this.fields = PersonDTO.class.getDeclaredFields();
    }

    /**
     * Обновление юзера с помощью рефлексии
     * @param userDTO новый ДТО юзера
     * @param currentUser юзер из БД, данные котого нужно обновить
     * @return true если обновление успешно,false если неуспешно
     */
    public boolean updateUserReflection(PersonDTO userDTO, PersonDTO currentUser) {
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                fieldValue = field.get(userDTO);
                if (fieldValue != null) field.set(currentUser, fieldValue);
            } catch (IllegalAccessException e) {
                return false;
            }
        }
        return true;
    }
}
