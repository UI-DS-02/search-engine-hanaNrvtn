package model;

import Controller.Editor;
import exception.InvalidInputException;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Query {

    // filed
    private String q;
    private String[] allWords;
    private String[] essWords;
    private String[] optWords;
    private String[] denWords;
    private String[] stpWords;
    private Editor edt;

    // get & set

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String[] getAllWords() {
        return allWords;
    }

    public void setAllWords(String[] allWords) {
        this.allWords = allWords;
    }

    public String[] getEssWords() {
        return essWords;
    }

    public void setEssWords(String[] essWords) {
        this.essWords = essWords;
    }

    public String[] getOptWords() {
        return optWords;
    }

    public void setOptWords(String[] optWords) {
        this.optWords = optWords;
    }

    public String[] getDenWords() {
        return denWords;
    }

    public void setDenWords(String[] denWords) {
        this.denWords = denWords;
    }

    public String[] getStpWords() {
        return stpWords;
    }

    public void setStpWords(String[] stpWords) {
        this.stpWords = stpWords;
    }

    public Editor getEdt() {
        return edt;
    }

    public void setEdt(Editor edt) {
        this.edt = edt;
    }

    // cons 1
    public Query(String q)
            throws InvalidInputException {

        this.q=q;
        allWords = all(q);
        essWords = ess(q);
        optWords = opt(q);
        denWords = den(q);

    }

    // cons 2
    public Query(String q, Editor edt)
            throws InvalidInputException {

        this.q = q;
        this.edt = edt;
        allWords = all(q);
        essWords = edt.clear(ess(q));
        optWords = edt.clear(opt(q));
        denWords = edt.clear(den(q));
        stpWords = stp();

    }

    // mtd
    public String[] ess(String q) {

        String[] spl = q.split("\s");
        ArrayList<String> ess = new ArrayList<>();

        for (String s : spl)
            if (s.charAt(0) != '+' && s.charAt(0) != '-')
                ess.add(s.toLowerCase());

        return ess.toArray(new String[0]);
    }

    public String[] opt(String q)
            throws InvalidInputException {

        StringBuilder sb;
        String[] spl = q.split("\s");
        ArrayList<String> opt = new ArrayList<>();

        for (String s : spl)
            if (s.charAt(0) == '+') {

                if(s.length()==1)
                    throw new InvalidInputException();

                sb = new StringBuilder(s);
                s = sb.deleteCharAt(0).toString();
                opt.add(s.toLowerCase());
            }

        return opt.toArray(new String[0]);
    }

    public String[] den(String q)
            throws InvalidInputException {

        StringBuilder sb;
        String[] spl = q.split("\s");
        ArrayList<String> den = new ArrayList<>();

        for (String s : spl)
            if (s.charAt(0) == '-') {

                if(s.length()==1)
                    throw new InvalidInputException();

                sb = new StringBuilder(s);
                s = sb.deleteCharAt(0).toString();
                den.add(s.toLowerCase());
            }

        return den.toArray(new String[0]);
    }

    public String[] all(String q) {
        StringBuilder sb;
        String[] spl = q.split("\s");
        ArrayList<String> all = new ArrayList<>();
        for (String s : spl) {
            all.add(s.toLowerCase());
        }
        return all.toArray(new String[0]);
    }

    public String[] stp() {
        ArrayList<String> allWords = Stream.of(this.allWords).collect(Collectors.toCollection(ArrayList<String>::new));
        ArrayList<String> essWords = Stream.of(this.essWords).collect(Collectors.toCollection(ArrayList<String>::new));
        ArrayList<String> optWords = Stream.of(this.optWords).collect(Collectors.toCollection(ArrayList<String>::new));
        ArrayList<String> denWords = Stream.of(this.denWords).collect(Collectors.toCollection(ArrayList<String>::new));
        allWords.removeAll(essWords);
        allWords.removeAll(optWords);
        allWords.removeAll(denWords);
        return allWords.toArray(new String[0]); //
    }

}