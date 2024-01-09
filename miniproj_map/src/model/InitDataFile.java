package model;

public class InitDataFile extends DataFile {

    // field
    private FinDataFile editedFile;

    // get & set
    public FinDataFile getEditedFile() {
        return editedFile;
    }
    public void setEditedFile(FinDataFile editedFile) {
        this.editedFile = editedFile;
    }
    
    // cons
    public InitDataFile(String name, String path) {
        super(name, path);
    }
}
