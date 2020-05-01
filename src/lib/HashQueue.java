package lib;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class HashQueue<Type> extends LinkedList<Type> {
    private Set<String> map = new HashSet<>();

    public void enqueue(Type object) {
        this.add(object);
        map.add(((FrontierEntry)object).getState());
    }

    public Type dequeue() {
        Type object;
        object= this.poll();
        if (object != null)
            map.remove(((FrontierEntry)object).getState());
        return object;
    }

    @Override
    public boolean contains(Object object) {
        if (!(object instanceof FrontierEntry)) {
            return false;
        }
        return map.contains(((FrontierEntry)object).getState());
    }
}
