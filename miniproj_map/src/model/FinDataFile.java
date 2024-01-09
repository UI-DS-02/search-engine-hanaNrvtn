package model;

import java.util.HashMap;
import java.util.Map;

public class FinDataFile
        extends DataFile {

    // field
    private InitDataFile initFile;
    private final Map<String, Integer> words;

    // get & set
    public InitDataFile getInitFile() {
        return initFile;
    }

    public Map<String, Integer> getWords() {
        return words;
    }

    public void setInitFile(InitDataFile initFile) {
        this.initFile = initFile;
    }

    // cons
    public FinDataFile (
            InitDataFile init,
            String name,
            String path)
    {
        super(name, path);
        initFile = init;
        words = new HashMap<>();
    }

    //
    public void add(String word) {

        if (words.containsKey(word))
            words.put(word,
                    words.get(word) + 1);
        else
            words.put(word, 1);
    }
}
