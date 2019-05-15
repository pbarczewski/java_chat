package MainWindow;

import javafx.scene.control.TextArea;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class FileChooser {

    private static final JFileChooser jFileChooser = new JFileChooser();
    private static final FileNameExtensionFilter filter =
            new FileNameExtensionFilter("Text files", "txt", "text");
    private static final String defaultFileName = "MyChat.txt";
    private static final String finalExtension = "txt";
    private static File file = null;
    public static int status;


    public static JFileChooser getjFileChooser() {
        return jFileChooser;
    }

    public static FileNameExtensionFilter getFilter() {
        return filter;
    }

    public static String getDefaultFileName() {
        return defaultFileName;
    }

    public static String getFinalExtension() {
        return finalExtension;
    }

    public static File getFile() {
        return file;
    }

    public static void setFile(File file) {
        FileChooser.file = file;
    }

    public static int getStatus() {
        return status;
    }

    public static void setStatus(int status) {
        FileChooser.status = status;
    }

    public FileChooser() {
        jFileChooser.setFileFilter(filter);
        jFileChooser.setSelectedFile(new File(defaultFileName));
        status = jFileChooser.showSaveDialog(null);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e)
        {

        }
    }

    public static void saveChatToFile(TextArea textArea) {
        file = checkExtension(jFileChooser.getSelectedFile());
        if(textArea.getText().length() > 0 ) {
            try( BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                file.createNewFile();
                bufferedWriter.write(textArea.getText().replaceAll("(?!\\r)\\n", "\r\n"));
                Alerts.setAlertType("FILE-SAVE");
            } catch (IOException e) {
                Alerts.setAlertType("FILE-FAILED");
            }
        }
    }

    public static File checkExtension(File file)
    {
        String extension = "";
        int i = file.getName().lastIndexOf('.');
        if(i > 0) {
            extension = file.getName().substring(i + 1);
        }
        if(!extension.equals(finalExtension)) {
            file = new File(jFileChooser.getSelectedFile() + "." + finalExtension);
        }
        return file;
    }
}
