import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ElectionServer {

	public static void main(String args[]) {
		// TODO Auto-generated method stub	
		try {
			System.out.println("////////////////////");
			Election electionServant = new ElectionServant();
			Election stub = (Election) UnicastRemoteObject.exportObject(electionServant, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("Election", stub);
			System.out.println("Servidor Election pronto.");
		} catch (Exception e) {
			System.err.println("ElectionServer: método main " + e.getMessage());
			e.printStackTrace();
		}

	}

}
