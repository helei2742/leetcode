package 线段树;

public class Model {
    private final int[] tree;
    private final int[] mark;
    private final int[] num;
    Model(int[] n) {
        num = n;
        int size = num.length;
        tree = new int[size * 4];
        mark = new int[size * 4];

        build(1, size, 1);
    }
    private void build(int l, int r, int p) {
        if(l == r)
            tree[p] = num[l];
        else{
            int mid = (l + r) / 2;
            build(l, mid, p * 2);
            build(mid, r, p * 2 + 1);
            tree[p] = tree[p * 2] + tree[p * 2 + 1];
        }
    }

    private void push_down(int p, int len) {
        mark[2 * p] += mark[p];
        mark[2 * p + 1] += mark[p];
        tree[2 * p] += mark[p] * (len - len/2);
        tree[2 * p + 1] += mark[p] * (len/2);
        mark[p] = 0;
    }
    public void update(int l, int r, int p, int add, int cL, int cR) {
        if(cL > r || cR < l)
            return;
        else if (cL >= l && cR <= r) {
            tree[p] += (cR - cL + 1) * add;
            if(cR > cL)
                mark[p] += add;
        }
        else {
            int mid = (cL + cR) / 2;
            push_down(p, cR - cL + 1);
            update(l, r, 2 * p, add, cL, mid);
            update(l, r, 2 * p + 1, add, mid + 1, cR);
            tree[p] = tree[2 * p] + tree[2 * p + 1];
        }
    }
    public int query(int l, int r, int p, int cL, int cR) {
        if(cL > r || cR < l)
            return 0;
        else if (cL >= l && cR <= r)
            return tree[p];
        else {
            int mid = (cL + cR)/2;
            push_down(p, cR - cL + 1);
            return query(l, r, p * 2, cL, mid)
                    + query(l, r, p * 2 + 1, mid + 1, cR);
        }
    }
}
