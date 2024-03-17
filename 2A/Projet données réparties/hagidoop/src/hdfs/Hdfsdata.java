package hdfs;

import java.util.Properties;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Hdfsdata {

    private static final String NodeA_FILE = "src/hdfs/hdfs_Nodea.properties";

    public static void updatedata(String filename, int fragmentCount) throws IOException {
        Properties properties = new Properties();
        File file = new File(NodeA_FILE);
        //print the actual path
        //System.out.println("Actual path : " + new File(".").getAbsolutePath());

        // Create the file if it does not exist
        if (!file.exists()) {
            file.createNewFile();
        }
        // Load existing properties
        try (FileInputStream in = new FileInputStream(file)) {
            properties.load(in);
        }

        // Update with new value
        properties.setProperty(filename, Integer.toString(fragmentCount));

        // Store properties back to the file
        try (FileOutputStream out = new FileOutputStream(NodeA_FILE)) {
            properties.store(out, null);
        }
    }

    public static int getFormat(String filename) throws IOException {
        Properties properties = new Properties();
        // Load properties
        try (FileInputStream in = new FileInputStream(NodeA_FILE)) {
            properties.load(in);
        }

        // Retrieve the fragment count
        String value = properties.getProperty(filename);
        return value != null ? Integer.parseInt(value) : -1; // return -1 if file not found
    }
    public static void deleteMetadata(String filename) throws IOException {
        Properties properties = new Properties();
        File file = new File(NodeA_FILE);

        // Load existing properties
        try (FileInputStream in = new FileInputStream(file)) {
            properties.load(in);
        }
        

        // Remove the specified entry
        if (properties.containsKey(filename)) {
            properties.remove(filename);

            // Store properties back to the file
            try (FileOutputStream out = new FileOutputStream(NodeA_FILE)) {
                properties.store(out, null);
            }
        }
    }
}

