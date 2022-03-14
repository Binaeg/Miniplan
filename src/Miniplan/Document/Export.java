package Miniplan.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Export {
    private File file;
    private FileWriter fileWriter;

    private static String EXPORT_PATH;

    public Export() throws IOException {
        file = new File(EXPORT_PATH, "Miniplan.txt");
        file.createNewFile();

        fileWriter = new FileWriter(file, false);
    }

    public void printLineToDocument(String s) throws IOException {
        fileWriter.write(s);


    }

    public void finishDocument() throws IOException {
        fileWriter.flush();
        fileWriter.close();
    }

    public static void setExportPath(String s) {
        EXPORT_PATH = s;
    }
}