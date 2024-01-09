package Controller;

import model.SideDataFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Editor {

    // field
    private String rgx;
    private String[] sw;

    // cons
    public Editor (String rgx, String sw)
            throws IOException
    {
        this.rgx = rgx;
        SideDataFile sdf =
                new SideDataFile(sw, "file\\sw\\" + sw);
        this.sw = sdf.read().split("\s");
    }

    // get & set
    public String getRgx() {
        return rgx;
    }

    public void setRgx(String rgx) {
        this.rgx = rgx;
    }

    public String[] getSw() {
        return sw;
    }

    public void setSw(String[] sw) {
        this.sw = sw;
    }

    // mth
    public String[] split(String str) {
        return str.toLowerCase().split(rgx);
    }

    public String[] clear(String[] arr) {

            ArrayList<String> allWords = Stream.of(arr).
                collect(Collectors.toCollection(ArrayList<String>::new));
        ArrayList<String> stpWords = Stream.of(this.sw).
                collect(Collectors.toCollection(ArrayList<String>::new));
        allWords.removeAll(stpWords);

        String[] tmp = new String[allWords.size()];
        return allWords.toArray(tmp);

    }
}
