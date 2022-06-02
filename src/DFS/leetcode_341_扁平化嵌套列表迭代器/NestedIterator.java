package DFS.leetcode_341_扁平化嵌套列表迭代器;

import java.util.Iterator;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {
    private List<Integer> vals;
    private Iterator<Integer> cur;
    public NestedIterator(List<NestedIterator> nestedList) {

    }

    private void dfs(List<NestedIterator> nestedList) {
        for (NestedIterator nested : nestedList) {
            if (nested.isInteger()){
                vals.add(nested.getInteger());
            }else {
                dfs(nested.getList());
            }
        }
    }

    private List<NestedIterator> getList() {
        return null;
    }

    private boolean isInteger(){
        return vals.size() == 1;
    }

    private int getInteger() {
        return 0;
    }

    @Override
    public Integer next() {
        return 0;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

}

