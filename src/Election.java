import java.rmi.RemoteException;
import java.util.Vector;

public interface Election extends java.rmi.Remote {
	Vector<String>  Vote(String nameCandidate, String hashMD5 ) throws RemoteException;
	int Result(String nameCandidate) throws RemoteException;
}
