package lib;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class HashPriorityQueue<Type> extends PriorityQueue<Type> {
    private Set<String> map = new HashSet<>();

    @Override
    public boolean add(Type e) {
        map.add( ((MinHeapEntry)e).getState() );
        return offer(e);
    }

    public Type extract() {
        Type target = this.poll();
        while ( (target != null) && (!map.contains(((MinHeapEntry)target).getState())) ) {
            target = this.poll();
        }
        if (target != null) {
            map.remove( ((MinHeapEntry)target).getState() );
        }
        return target;
    }

    @Override
    public boolean contains(Object obj) {
        if (!(obj instanceof MinHeapEntry)) {
            return false;
        }
        return map.contains( ((MinHeapEntry) obj).getState() );
    }
}
