package hdfs;

public class HdfsdataMTest {

    public static void main(String[] args) {
        try {
            // Test adding metadata for two files
            String testFileName1 = "testFile1.txt";
            String testFileName2 = "testFile2.txt";
            int fragmentCount1 = 4;
            int fragmentCount2 = 5;


            System.out.println("Testing metadata update for two files...");
            Hdfsdata.updatedata(testFileName1, fragmentCount1);
            Hdfsdata.updatedata(testFileName2, fragmentCount2);
            Hdfsdata.updatedata("existing.txt", 1);

            // Test deleting metadata for one file
            System.out.println("Testing metadata deletion for " + testFileName1);
            Hdfsdata.deleteMetadata(testFileName1);

            // Test retrieving metadata to verify deletion
            System.out.println("Verifying deletion...");
            int retrievedCount1 = Hdfsdata.getFormat(testFileName1);
            int retrievedCount2 = Hdfsdata.getFormat(testFileName2);


            if (retrievedCount1 == -1 && retrievedCount2 == fragmentCount2) {
                System.out.println("Test successful. Metadata for " + testFileName1 + " deleted, and metadata for " + testFileName2 + " is intact.");
            } else {
                System.out.println("Test failed. Unexpected retrieval results.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
