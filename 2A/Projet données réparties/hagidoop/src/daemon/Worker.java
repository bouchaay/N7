package daemon;

import java.rmi.Remote;
import java.rmi.RemoteException;

import ReadderWriter.FileReaderWriter;
import ReadderWriter.NetworkReaderWriter;
import interfaces.Map;

public interface Worker extends Remote {
	public void runMap (Map m, FileReaderWriter reader, NetworkReaderWriter writer) throws RemoteException;
}
