package ReadderWriter;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class  NetworkReaderWriterImp implements NetworkReaderWriter {

    private FileReaderWriter frw;

    private static final int PORT = 9090;
    private ServerSocket serverSocket;
    private Socket clientSocket;


    public NetworkReaderWriterImp(String fname) {
		try {
            File file = new File(fname);
            if (!file.exists()) {
                file.createNewFile();
            }
            frw = new KvFileReaderWriter(fname);
        } catch (Exception e) {
            //System.out.println("Error creating file");
        }

    }
    public NetworkReaderWriterImp() {

        this.openServer();
    }
   
    
    public void openServer() {
        try {
            serverSocket = new ServerSocket(PORT );

        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }
    public void openClient() {
        try {
            clientSocket = new Socket("localhost", PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public NetworkReaderWriter accept() {
        try {
            serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public void closeServer() {
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void closeClient() {
        frw.close();
        if (clientSocket != null && !clientSocket.isClosed()) {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    public KV read() {
        frw.open("r");
        KV kv = frw.read();
        frw.close();
        return kv;
        
    }
    @Override
    public void write(KV record) {
 
        frw.write(record);
    }
    @Override
    public void openFile() {
        frw.open("w");
        
    }


 

    
    
}