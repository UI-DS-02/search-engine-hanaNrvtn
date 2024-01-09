package Controller;

import model.FinDataFile;

import java.util.*;

public class Mapper {

    // filed
    private final Map<String, Set<FinDataFile>> map;

    // cons
    public Mapper() {
        map = new HashMap<>();
    }

    // get
    public Map<String, Set<FinDataFile>> getMap() {
        return map;
    }

    // mth
    public void map (
            String[] words,
            FinDataFile fdf)
    {
        for (String w : words) {

            fdf.add(w);

            if (map.containsKey(w))
                map.get(w).add(fdf);
            else {
                map.put(w, new HashSet<>());
                map.get(w).add(fdf);
            }
        }
    }

    public Set<FinDataFile> union
            (String[] words)
    {
        Set<FinDataFile> s = new HashSet<>();

        for (String word : words)
            if (map.containsKey(word))
                s.addAll(map.get(word));

        return s;
    }

    public Set<FinDataFile> interaction
            (String[] words)
    {
        Set<FinDataFile> s = new HashSet<>();

        for (String word : words) {

            if(word.equals(words[0])) {
                if(map.containsKey(word)) {
                    s.addAll(map.get(word));
                    continue;
                } else {
                    return s;
                }
            }
            if (map.containsKey(word))
                s.retainAll(map.get(word));
            else {
                s.clear();
                return s;
            }
        } return s;
    }

    public Set<FinDataFile> univ() {

        Set<FinDataFile> s = new HashSet<>();

        for (Set<FinDataFile> item : map.values())
            s.addAll(item);

        return s;
    }

}
