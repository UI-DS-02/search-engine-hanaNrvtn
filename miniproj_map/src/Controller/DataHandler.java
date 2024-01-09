package Controller;

import model.FinDataFile;
import model.InitDataFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataHandler {

    // filed
    private String src;
    private String dst;
    private Editor edt;
    private final ArrayList<InitDataFile> initDf;
    private final ArrayList<FinDataFile> finDf;
    private Mapper mpr;

    // cons
    public DataHandler(
            String src,
            String dst,
            Editor edt,
            Mapper mpr)
    {
        this.src = src;  // dir
        this.dst = dst;  // dir
        this.edt = edt;
        initDf = new ArrayList<>();
        finDf = new ArrayList<>();
        this.mpr = mpr;
    }

    // get & set

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public Editor getEdt() {
        return edt;
    }

    public void setEdt(Editor edt) {
        this.edt = edt;
    }

    public Mapper getMpr() {
        return mpr;
    }

    public void setMpr(Mapper mpr) {
        this.mpr = mpr;
    }

    public ArrayList<FinDataFile> getFinDf() {
        return finDf;
    }

    public ArrayList<InitDataFile> getInitDf() {
        return initDf;
    }

    // mtd
    public void edit(int swh)
            throws IOException
    {
        File dir = new File(src);
        File[] files = dir.listFiles();
        InitDataFile idf;
        FinDataFile fdf;
        StringBuilder sb;

        // through all files in dir
        for (File file : files) {
            if (file.isFile()) {

                // fetch
                initDf.add(idf = new InitDataFile
                        (file.getName(), src +
                        "\\" + file.getName()));
                String txt = idf.read();

                // edit
                String[] arr = edt.split(txt);
                if (swh == 2){
                    arr = edt.clear(arr);

                }

                sb = new StringBuilder();
                for (String s : arr)
                    sb.append(s).append("\s");

                // store
                finDf.add(fdf = new FinDataFile
                        (idf, file.getName(), dst +
                        "\\" + file.getName()));
                fdf.write(sb.toString());

                // sync
                idf.setEditedFile(fdf);
                fdf.setInitFile(idf);
            }
        }

    }

    public void map()
            throws IOException
    {
        for (FinDataFile fdf : finDf) {
            String txt = fdf.read();
            mpr.map(txt.split("\s"), fdf);
        }
    }

}
