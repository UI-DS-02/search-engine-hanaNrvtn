package model;

import ds.heap.HeapPriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Search {
    Query q;
    Set<Result> rs;
    ArrayList<String> sml;

    public Search(Query q, Set<FinDataFile> res) {
        this.q = q;
        this.rs = set(res);
    }

    public Query getQ() {
        return q;
    }

    public void setQ(Query q) {
        this.q = q;
    }

    public Set<Result> getRs() {
        return rs;
    }

    public void setSml(ArrayList<String> sml) {
        this.sml = sml;
    }

    public ArrayList<String> getSml() {
        return sml;
    }

    private Set<Result> set(Set<FinDataFile> fdf) {
        Set<Result> s = new HashSet<>();
        for (FinDataFile item : fdf) {
            s.add(new Result(q, item));
        }
        return s;
    }

    public ArrayList<Result> sort() {
        HeapPriorityQueue<Result, Result> hp = new HeapPriorityQueue<>(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return o2.compareTo(o1);
            }
        });
        ArrayList<Result> tmp = new ArrayList<>();
        for(Result r: rs) {
            hp.insert(r, r);
        }
        while (!hp.isEmpty()) {
            tmp.add(hp.pop().getKey());
        } return tmp;
    }

}
