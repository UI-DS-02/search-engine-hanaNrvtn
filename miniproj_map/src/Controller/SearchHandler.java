package Controller;

import exception.InvalidInputException;
import model.*;

import java.util.*;

public class SearchHandler {

    // filed
    private Mapper mpr;

    // cons
    public SearchHandler(Mapper mpr) {
        this.mpr = mpr;
    }

    // get & set
    public Mapper getMpr() {
        return mpr;
    }

    public void setMpr(Mapper mpr) {
        this.mpr = mpr;
    }

    public Set<FinDataFile> bscRes (
             String[] ess,
             String[] opt,
             String[] den)
    {

        // match
        Set<FinDataFile> essSet
                = mpr.interaction(ess);
        Set<FinDataFile> optSet
                = mpr.union(opt);
        Set<FinDataFile> denSet
                = mpr.union(den);

        // fill
        if (ess.length == 0)
            essSet = mpr.univ();
        if (opt.length == 0)
            optSet = mpr.univ();

        // res
        essSet.removeAll(denSet);
        essSet.retainAll(optSet);

        return essSet;
    }

    public Set<FinDataFile> bscSearch (String s)
            throws InvalidInputException {

        Query q = new Query(s);
        return bscRes(
                q.getEssWords(),
                q.getOptWords(),
                q.getDenWords());
    }

    public ArrayList<Result> prtSearch
            (String s, Editor edt)
            throws InvalidInputException {

        Query q = new Query(s, edt);
        Set<FinDataFile> fdf = bscRes(
                q.getEssWords(),
                q.getOptWords(),
                q.getDenWords());

        // analysis results
        Search sr = new Search(q, fdf);
        return sr.sort();
    }

}
