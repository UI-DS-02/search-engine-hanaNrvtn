package model;

import java.util.ArrayList;

public class Query {

    private String[] essWords;
    private String[] optWords;
    private String[] denWords;

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

    public Query(String q) {
        essWords = ess(q);
        optWords = opt(q);
        denWords = den(q);
    }

    public String[] ess(String q) {
        String[] spl = q.split("\s");
        ArrayList<String> ess = new ArrayList<>();
        for (String s : spl) {
            if (s.charAt(0) != '+' && s.charAt(0) != '-') {
                ess.add(s.toLowerCase());
            }
        }
        return ess.toArray(new String[0]);
    }

    public String[] opt(String q) {
        StringBuilder sb;
        String[] spl = q.split("\s");
        ArrayList<String> opt = new ArrayList<>();
        for (String s : spl) {
            if (s.charAt(0) == '+') {
                sb = new StringBuilder(s);
                s = sb.deleteCharAt(0).toString();
                opt.add(s.toLowerCase());
            }
        }
        return opt.toArray(new String[0]);
    }

    public String[] den(String q) {
        StringBuilder sb;
        String[] spl = q.split("\s");
        ArrayList<String> den = new ArrayList<>();
        for (String s : spl) {
            if (s.charAt(0) == '-') {
                sb = new StringBuilder(s);
                s = sb.deleteCharAt(0).toString();
                den.add(s.toLowerCase());
            }
        }
        return den.toArray(new String[0]);
    }

}
