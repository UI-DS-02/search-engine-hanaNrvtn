package model;

import java.util.*;

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
        ess = new TreeMap<>();
        opt = new TreeMap<>();
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
            if(this.count() > ((Result) o).count()) {
                return 1;
            } else if(this.count() < ((Result) o).count()) {
                return -1;
            } else {
                if(((Result)o).getOpt().size() != 0 && this.getOpt().size() != 0) {
                    return ((Result)o).getOpt().keySet().stream().toList().get(0).compareTo(this.getOpt().keySet().stream().toList().get(0));
                } return 0;
            }
        }
    }

    private int count() {
        int count =0;
        for(Integer i: ess.values()) {
            count+=i;
        }
        for(Integer i: opt.values()){
            count+=i;
        } return count;
    }

}
