package model;

import java.io.*;

abstract class DataFile {

    // field
    private String name;
    private String path;

    // cons
    public DataFile(String name, String path) {
        this.name = name;
        this.path = path;
    }

    // get & set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    // method
    public String read() throws IOException {

        BufferedReader br =
                new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        br.close();
        return sb.toString();
    }

    public void write(String txt) throws IOException {

        BufferedWriter bw =
                new BufferedWriter(new PrintWriter(path));
        bw.write(String.valueOf(txt));

        bw.flush();
        bw.close();
    }

}
