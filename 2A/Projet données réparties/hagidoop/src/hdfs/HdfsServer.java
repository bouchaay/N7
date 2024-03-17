package hdfs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;

import ReadderWriter.FileReaderWriter;
import ReadderWriter.KV;
import hdfs.HdfsClient.Command;

public class HdfsServer extends Thread {

    public static String path = "data";





    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);
            ServerSocket serverSocket = new ServerSocket(port);
            //System.out.println("Server started on the node " + port);
            //System.out.println("Current working directory: " + new File(".").getAbsolutePath());


                
            
            while(true) {
                Socket socket = serverSocket.accept();
                //System.out.println("Client connected");
                //System.out.println("Server started on the node + nbNode");

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Command cmd = (Command) ois.readObject();
               //System.out.println("Command received: " + cmd);
                String fname = (String) ois.readObject();
                //System.out.println("File name received: " + fname);

                if (cmd.equals(Command.DELETE)) {
                    String fnameCount = fname + "_count";
                    //System.out.println("Deleting file " + fname + "...");
                    File file = new File(path+"/"+fname);
                    File fileCount = new File(path+"/"+fnameCount);
                    fileCount.delete();
                    file.delete();
                    ois.close();

                    socket.close();
                    serverSocket.close();
                    
                } else if (cmd.equals(Command.WRITE)) {
                    //System.out.println("Writing file " + fname + "...");

                    int nbLine = (int) ois.readObject();
                    int fmt = (int) ois.readObject();
                    // print the absolute path to the file
                    //System.out.println("Current working directory: " + new File(".").getAbsolutePath());
                    File toWrite = new File(path+"/"+fname);
                    //System.out.println("Absolute path: " + toWrite.getAbsolutePath());

                    if(!toWrite.exists()) {
                        toWrite.createNewFile();
                    }


                    FileReaderWriter frw = HdfsClient.instanciateFile(fmt, path+"/"+fname);

                    frw.open("w");

                    for (int i = 0; i < nbLine; i++) {
                        KV record = (KV) ois.readObject();
                        frw.write( new KV(record.getKey(), record.getValue()));
                    }

                    frw.close();
                    


                } else if (cmd.equals(Command.READ)) {
                    //System.out.println("Reading file " + fname + "...");
                    int fmt =(int) ois.readObject();

                    FileReaderWriter frw = HdfsClient.instanciateFile(fmt, path+"/"+fname);
                    frw.open("r");

                    int nbLine = frw.getLineCount();

                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(nbLine);

                    for (int i = 0; i < nbLine; i++) {
                        KV record = frw.read();
                        oos.writeObject(new KV(record.getKey(), record.getValue()));
                    }

                    frw.close();
                    oos.close();
                    

                }
                
                
            }
            
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
    

