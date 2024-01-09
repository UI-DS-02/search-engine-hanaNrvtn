package model;

import Controller.Editor;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Query {

    String q;
    private String[] allWords;
    private String[] essWords;
    private String[] optWords;
    private String[] denWords;
    private String[] stpWords;
    Editor edt;

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
        this.q=q;
        allWords = all(q);
        essWords = ess(q);
        optWords = opt(q);
        denWords = den(q);
    }

    public Query(String q, Editor edt) {
        this.q = q;
        this.edt = edt;
        allWords = all(q);
        essWords = edt.clear(ess(q));
        optWords = edt.clear(opt(q));
        denWords = edt.clear(den(q));
        stpWords = stp();
        System.out.println("ess: ");
        for(int i=0;i<essWords.length;i++) {
            System.out.println(essWords[i]);
        }

        System.out.println("opt: ");
        for(int i=0;i<optWords.length;i++) {
            System.out.println(optWords[i]);
        }

        System.out.println("den: ");
        for(int i=0;i<denWords.length;i++) {
            System.out.println(denWords[i]);
        }
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
