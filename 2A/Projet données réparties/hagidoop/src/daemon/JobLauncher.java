package daemon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import ReadderWriter.FileReaderWriter;
import ReadderWriter.KvFileReaderWriter;
import ReadderWriter.NetworkReaderWriter;
import ReadderWriter.NetworkReaderWriterImp;
import ReadderWriter.TxFileReaderWriter;
import hdfs.HdfsClient;
import interfaces.MapReduce;


public class JobLauncher {

	public static String path_config = "src/config";
	public static String path_data = "data";
	public static int nbNode;

	public static void startJob (MapReduce mr, int format, String fname) throws IOException {
		HdfsClient.HdfsWrite(format, fname);
		
		List<Worker> workers = getWorker();
		nbNode = workers.size();
		NetworkReaderWriter server = new NetworkReaderWriterImp();
		for (int i = 0; i < workers.size(); i++) {
			NetworkReaderWriter writer = new NetworkReaderWriterImp(path_data + "/" + fname + "_" + i+ "_count");
			FileReaderWriter reader = new TxFileReaderWriter( path_data + "/" + fname + "_" + i);
			workers.get(i).runMap(mr, reader, writer);
		}

		//wait for all the workers to finish
		int fileCount = 0;
		while (fileCount < nbNode) {
			server.accept();
			fileCount++;
		}

		// merge the files
		try {
			HdfsClient.HdfsRead(fname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// reduce
		File towrFile = new File(path_data + "/" + fname + "_count"+"_Merged" + "_Reduced");
		if(!towrFile.exists()) {
			towrFile.createNewFile();
		}
		FileReaderWriter writer = new KvFileReaderWriter(path_data + "/" + fname + "_count"+"_Merged" + "_Reduced");
		FileReaderWriter reader = new KvFileReaderWriter(path_data + "/" + fname + "_count"+"_Merged");
		writer.open("w");
		reader.open("r");
		mr.reduce(reader, writer);
		reader.close();
		writer.close();

		//HdfsClient.HdfsDelete(fname);
		


			
	}
	public static List<Worker> getWorker() {
		List<Worker> workers = new ArrayList<Worker>();
		try {
			//print the local directory
			File file = new File(path_config + "/configWorkers.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line;
			while ((line = br.readLine() )!= null) {

				workers.add((Worker) Naming.lookup(line));
				
			}
			br.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workers;
	}
}
