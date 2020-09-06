import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ElectionClient {
	public static Election election = null;
	
	
	public static void execute(String args[]) {

		synchronized (election) {
				
			String name = "Sem nome";
			String candidate = "Nulo";

			System.out.println("argslen: " + args.length);
			if (args.length > 0)
				name = args[0]; // especifica o nome da pessoa
			if (args.length > 1)
				candidate = args[1];// especifica o candidato.
			
			System.out.println("Nome: " + name + " // Candidato: " + candidate);

			/*if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			} else
				System.out.println("Já possui um Security Manager.");
	*/
				try {
					election.Vote(candidate, name);
					System.out.println("Finalizada Chamada de Método Vote em Cliente");
					System.out.println(candidate + " teve " + election.Result(candidate) + " votos." +election);
					
				} catch (RemoteException e) {
					System.out.println("Método erro: " + e.getMessage());
				} catch (Exception e) {
					System.out.println("Lookup: " + e.getMessage());
				}
				
			}	
	}

	public static void main(String[] args) {
			
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			election = (Election) registry.lookup("Election");
			System.out.println("Election Encontrado.");
		} catch (RemoteException e) {
			System.out.println("Método erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Lookup: " + e.getMessage());
		}
		
		String[] str1 = new String [2];
		str1[0]="Gustavo";
		str1[1]="Roberto";
		String[] str2 = new String [2];
		str2[0]="Gustavo";
		str2[1]="Roberto";
		String[] str3 = new String [2];
		str3[0]="Maria";
		str3[1]="Paulo";
		String[] str4 = new String [2];
		str4[0]="Paulo";
		str4[1]="Roberto";

		new Thread(() -> execute(str1)).start();
		new Thread(() -> execute(str2)).start();
		new Thread(() -> execute(str3)).start();
		new Thread(() -> execute(str4)).start();
		// TODO Auto-generated method stub
		
	}

}
