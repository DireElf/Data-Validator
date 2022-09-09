package hexlet.code.schemas;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class BaseSchema {
    private List<Method> checkList = new ArrayList<>();

    public final boolean isValid(Object o) {
        for (Method m : checkList) {
            m.setAccessible(true);
            Object isValid = null;
            try {
                isValid = m.invoke(this, o);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (Objects.equals(isValid, false)) {
                return false;
            }
        }
        return true;
    }

    public final void addCheck(String methodName) {
        try {
            checkList.add(this.getClass().getDeclaredMethod(methodName, Object.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
