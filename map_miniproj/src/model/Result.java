package model;

import java.util.HashMap;
import java.util.Map;

public class Result implements Comparable {

    private Query q;
    private FinDataFile fdf;
    private Map<String, Integer> opt;
    private Map<String, Integer> ess;

    public FinDataFile getFdf() {
        return fdf;
    }

    public Map<String, Integer> getOpt() {
        return opt;
    }

    public Map<String, Integer> getEss() {
        return ess;
    }

    public Result(Query q, FinDataFile fdf) {
        this.q = q;
        this.fdf = fdf;
        ess = new HashMap<>();
        opt = new HashMap<>();
        opt(q.getOptWords());
        ess(q.getEssWords());
    }

    private void opt(String[] opt) {
        for (String s : opt) {
            Integer count = fdf.getWords().get(s);
            if (count != null) {
                this.opt.put(s, count);
            }
        }
    }

    private void ess(String[] ess) {
        for (String s : ess) {
            Integer count = fdf.getWords().get(s);
            if (count != null) {
                this.ess.put(s, count);
            }
        }
    }

    @Override
    public int compareTo(Object o) {  //
        if(this.opt.size()>((Result)o).opt.size()) {
            return 1;
        } else if(this.opt.size()<((Result)o).opt.size()) {
            return -1;
        } else {
            return Integer.compare(this.countEss(), ((Result) o).countEss());
        }
    }

    private int countEss() {
        int count =0;
        for(Integer i: ess.values()) {
            count++;
        } return count;
    }
}
