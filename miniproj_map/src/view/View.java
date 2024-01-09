package view;

import Controller.DataHandler;
import Controller.Editor;
import Controller.Mapper;
import Controller.SearchHandler;
import exception.InvalidInputException;
import model.FinDataFile;
import model.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class View {

    // field
    private final Editor edt;
    private final Mapper mpr;
    private final DataHandler dh;
    private final SearchHandler sh;

    private final String src; // dir
    private final String dst; // dir
    private final String sw;  // file
    private final String rgx;


    // getter
    public Mapper getMpr() {
        return mpr;
    }

    public DataHandler getDh() {
        return dh;
    }

    public SearchHandler getSh() {
        return sh;
    }

    public String getSrc() {
        return src;
    }

    public String getDst() {
        return dst;
    }

    public String getSw() {
        return sw;
    }

    public String getRgx() {
        return rgx;
    }


    // cons
    public View(int swh) throws IOException {

        src = "file\\in";
        dst = "file\\out";
        sw = "stp_words.txt";
        rgx = "[^a-z]+";

        edt = new Editor(rgx, sw);
        mpr = new Mapper();
        dh = new DataHandler(src, dst, edt, mpr);
        sh = new SearchHandler(mpr);

        init(swh);
    }


    // method
    public void init(int swh)
            throws IOException {

        dh.edit(swh);
        dh.map();
    }

    public void bscSearch(String s)
            throws InvalidInputException {

        Set<FinDataFile> res = sh.bscSearch(s);

        // out
        System.out.println(res.size());
        for (FinDataFile f : res) {
            System.out.println(f.getName());
        }

    }

    public void prtSearch(String s)
            throws InvalidInputException {

        ArrayList<Result> res = sh.prtSearch(s, edt);

        // out
        System.out.println(res.size());
        for (int i=0;i<res.size();i++) {
            System.out.println(res.get(i).toString());
        }
    }
}
