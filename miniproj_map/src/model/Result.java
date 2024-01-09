package model;

import java.util.*;

public class Result implements Comparable {

    // filed
    private Query q;
    private FinDataFile fdf;
    private final Map<String, Integer> opt;
    private final Map<String, Integer> ess;

    // get & set

    public Query getQ() {
        return q;
    }

    public void setQ(Query q) {
        this.q = q;
    }

    public FinDataFile getFdf() {
        return fdf;
    }

    public void setFdf(FinDataFile fdf) {
        this.fdf = fdf;
    }

    public Map<String, Integer> getOpt() {
        return opt;
    }

    public Map<String, Integer> getEss() {
        return ess;
    }

    // cons
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
            if (count != null)
                this.opt.put(s, count);
        }

    }

    private void ess(String[] ess) {
        for (String s : ess) {
            Integer count = fdf.getWords().get(s);
            if (count != null)
                this.ess.put(s, count);
        }
    }

    @Override
    public int compareTo(Object o) {

        // prt 1
        if (this.opt.size() >
                ((Result) o).opt.size())
            return 1;
        else if (this.opt.size() <
                ((Result) o).opt.size())
            return -1;

        else {

            // prt 2
            if (this.count() >
                    ((Result) o).count())
                return 1;
            else if (this.count() <
                    ((Result) o).count())
                return -1;

            else {

                // prt 3
                if (((Result) o).getOpt().size() != 0
                        && this.getOpt().size() != 0)
                    return ((Result) o).getOpt().keySet().stream().toList().get(0).
                            compareTo(this.getOpt().keySet().stream().toList().get(0));
                return 0;
            }
        }
    }

    private int count() {

        int count = 0;
        for (Integer i : ess.values())
            count += i;

        for (Integer i : opt.values())
            count += i;
        return count;
    }

    @Override
    public String toString() {
        return "file name: " + this.getFdf().getName() + "\nopt words: " + this.opt.toString() + "\ness words: " + this.ess.toString() + "\n";
    }
}
