package daemon;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import ReadderWriter.FileReaderWriter;
import ReadderWriter.NetworkReaderWriter;
import interfaces.Map;

public class WorkerImp extends UnicastRemoteObject implements Worker {
   
    private String urlServer;

    protected WorkerImp(String urlServer) throws RemoteException {
        super();
        this.urlServer = urlServer;
    }
    public String getUrlServer() {
        return urlServer;
    }


    @Override
    public void runMap(Map m, FileReaderWriter reader, NetworkReaderWriter writer) throws RemoteException {
        //System.out.println("Running map");
        reader.open("r");
        writer.openFile();
        m.map(reader, writer);
        writer.openClient();
        writer.closeClient();
        reader.close();
    }


    public static void main(String[] args) {
        try{
            int port = Integer.parseInt(args[0]);
            String host = "localhost";
            String urlServer = "//"+host+":"+port+"/Worker";
            WorkerImp worker = new WorkerImp(urlServer);
            Registry registry = LocateRegistry.createRegistry(port);
            Naming.rebind(urlServer, worker);
           
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    
}
