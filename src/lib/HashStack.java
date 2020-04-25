package lib;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class HashStack<Type> extends Stack<Type> {
    private Set<Type> map = new HashSet<>();

    @Override
    public Type push(Type object) {
        addElement(object);
        map.add(object);
        return object;
    }

    @Override
    public Type pop() {
        Type object;
        int len = size();
        object = peek();
        removeElementAt(len - 1);
        map.remove(object);
        return object;
    }

    @Override
    public boolean contains(Object object) {
        return map.contains(object);
    }
}
