package model;

public class InitDataFile extends DataFile {

    private FinDataFile editedFile;

    public InitDataFile(String name, String path) {
        super(name, path);
    }

    public FinDataFile getEditedFile() {
        return editedFile;
    }

    public void setEditedFile(FinDataFile editedFile) {
        this.editedFile = editedFile;
    }
}
