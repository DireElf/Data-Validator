package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private List<Predicate<Object>> checkList = new ArrayList<>();

    public final boolean isValid(Object o) {
        for (Predicate<Object> check : checkList) {
            if (!check.test(o)) {
                return false;
            }
        }
        return true;
    }

    public final void addCheck(Predicate<Object> check) {
        checkList.add(check);
    }
}
