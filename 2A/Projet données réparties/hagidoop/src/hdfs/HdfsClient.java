package hdfs;

import java.io.*;

import java.net.Socket;

import javax.swing.Spring;

import ReadderWriter.*;



public class HdfsClient {

	private static  int portNumber[]= { 8080, 8081, 8082 }; // à modifier*
	private static String serverName[] = { "localhost", "localhost", "localhost" }; // For local testing

	private static int nbNode = 3; // à modifier*
	
	/*ToDo :(*)
		mettre ces variables en fichier de config et ajout des getter
		*/

	public static String path = "data"; // à modifier*


	enum Command {
		READ, WRITE, DELETE,
	}




	private static void usage() {
		System.out.println("Usage: java HdfsClient read <file>");
		System.out.println("Usage: java HdfsClient write <txt|kv> <file>");
		System.out.println("Usage: java HdfsClient delete <file>");
	}
	
	public static void HdfsDelete(String fname) throws IOException {
		int fmt = Hdfsdata.getFormat(fname);
		if(fmt != -1) {

			for (int i = 0; i < nbNode; i++) {
				Socket socket = new Socket(serverName[i], portNumber[i]);
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(Command.DELETE);
				oos.writeObject(fname +"_"+ i);
				oos.close();
				socket.close();
			}
			Hdfsdata.deleteMetadata(fname);
		}else {
			System.out.println("Le fichier n'existe pas");
		}
	}
	
	public static void HdfsWrite(int fmt, String fname) throws IOException {
		
		//System.out.println("Actual path : " + new File(".").getAbsolutePath());
		FileReaderWriter frw = instanciateFile(fmt, path+"/"+fname);
	
		//System.out.println("Writing file " + fname + "...");

		frw.open("r");

		int nbLine = frw.getLineCount();
		int nbLinePerServer = nbLine / nbNode;
		int nbLineRest = nbLine % nbNode;
		
		for (int i = 0; i < nbNode; i++) {
						if (i == nbNode - 1) {
							nbLinePerServer += nbLineRest;
						}
						Socket socket = new Socket(serverName[i], portNumber[i]);
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

						oos.writeObject(Command.WRITE);
						oos.writeObject( fname +"_"+ i); // on envoie le nom du fichier avec le numéro du fragment
						oos.writeObject(nbLinePerServer);
						oos.writeObject(fmt);

			

			for (int j = 0; j < nbLinePerServer; j++) {
				KV record = frw.read();
				oos.writeObject(record);
			}
			oos.close();
			socket.close();
		}
		
		frw.close();

		Hdfsdata.updatedata(fname, fmt);
	}

	public static void HdfsRead(String fname) throws IOException, ClassNotFoundException {
		int fmt = Hdfsdata.getFormat(fname);
		if(fmt != -1) {
			fmt=1;
			int nbLinePerServer;
			File countMerged = new File(path+"/"+fname+"_count"+"_Merged");
			if(!countMerged.exists()) {
				countMerged.createNewFile();
			}
			FileReaderWriter frw = new KvFileReaderWriter(path+"/"+fname+"_count"+"_Merged");
			frw.open("w");
			for (int i = 0; i < nbNode; i++) {
				Socket socket = new Socket(serverName[i], portNumber[i]);
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

				oos.writeObject(Command.READ);
				oos.writeObject(fname +"_"+ i + "_count");
				oos.writeObject(fmt);
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				nbLinePerServer = (int) ois.readObject();
				for (int j = 0; j < nbLinePerServer; j++) {
					
					KV record = (KV) ois.readObject();
					frw.write(record);
				}
				oos.close();
				ois.close();
				socket.close();	
			}
			frw.close();

		}else {
			System.out.println("Le fichier n'existe pas");
		}

		

	}

	public static FileReaderWriter instanciateFile(int fmt, String fname) {
		FileReaderWriter frw;
		switch (fmt) {
			// format txt
			case FileReaderWriter.FMT_TXT:
				frw = new TxFileReaderWriter(fname);
				break;
			// format kv
			case FileReaderWriter.FMT_KV:
				frw = new KvFileReaderWriter(fname);
				break;
			default:
				frw = null;
				break;
		}
		return frw;
	}

	
	
}
