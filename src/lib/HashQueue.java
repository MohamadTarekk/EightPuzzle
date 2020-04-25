package lib;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class HashQueue<Type> extends LinkedList<Type> {
    private Set<Type> map = new HashSet<>();

    public void enqueue(Type object) {
        this.add(object);
        map.add(object);
    }

    public Type dequeue() {
        Type object;
        object= this.poll();
        map.remove(object);
        return object;
    }

    @Override
    public boolean contains(Object object) {
        return map.contains(object);
    }
}
