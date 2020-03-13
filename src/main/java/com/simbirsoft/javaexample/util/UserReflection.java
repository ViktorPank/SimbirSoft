package com.simbirsoft.javaexample.util;

import com.simbirsoft.javaexample.dto.UserDTO;

import java.lang.reflect.Field;

/**
 * Действия с юзером с помощью рефлексии
 */
// TODO: Обычно такие классы называют UserReflectionUtil с Util на конце.
public class UserReflection {
    private Field[] fields;

    public UserReflection() {
        this.fields = UserDTO.class.getDeclaredFields();
    }

    /**
     * Обновление юзера с помощью рефлексии
     * @param userDTO новый ДТО юзера
     * @param currentUser юзер из БД, данные котого нужно обновить
     * @return true если обновление успешно,false если неуспешно
     */
    public boolean updateUserReflection(UserDTO userDTO, UserDTO currentUser) {
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
