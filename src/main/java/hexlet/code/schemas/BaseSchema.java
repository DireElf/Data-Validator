package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private List<Predicate> checkList = new ArrayList<>();

    public final boolean isValid(Object o) {
        for (Predicate check : checkList) {
            if (!check.test(o)) {
                return false;
            }
        }
        return true;
    }

    public final void addCheck(Predicate check) {
        checkList.add(check);
    }
}
