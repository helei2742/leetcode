package 剑指Offer二.leetcode_剑指OfferII058_日程表;

import java.util.TreeSet;

class MyCalendar {

    private TreeSet<int[]> treeSet;
    public MyCalendar() {
        System.out.println();
        treeSet = new TreeSet<int[]>((a,b)->a[0]-b[0]);
    }

    public boolean book(int start, int end) {
        if(treeSet.isEmpty()) {
            treeSet.add(new int[]{start, end});
            return true;
        }
        int[] t = new int[]{start, 0};
        int[] floor = treeSet.floor(t);
        int[] ceiling = treeSet.ceiling(t);
        if(floor != null && floor[1] > start){
            return false;
        }
        if(ceiling != null && ceiling[0] <= end){
            return false;
        }
        treeSet.add(new int[]{start, end});
        return true;
    }


}
