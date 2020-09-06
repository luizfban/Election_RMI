import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Vector;

public class ElectionServant implements Election {
	
	private Vector<String> candidateList;
	private Vector<String> hashList;
	
	public ElectionServant() throws RemoteException {
		candidateList = new Vector<String>();
		hashList = new Vector<String>();
	}
	
	@Override
	public synchronized Vector<String> Vote(String nameCandidate, String electorName) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Método VOTE");
		String hashMD5 = getHashMD5(electorName);
		System.out.println("Hash Coletada");
		if(hashList.contains(hashMD5)) {
			System.out.println("Eleitor Repetido - Já Votou");
		}else {	
			hashList.addElement(hashMD5);
			candidateList.addElement(nameCandidate);	
			System.out.println("Eleitor Novo - Voto Realizado");
			return candidateList;
		}
		return null;
	}

	@Override
	public synchronized int Result(String nameCandidate) throws RemoteException {
		// TODO Auto-generated method stub
		int numVotes = Collections.frequency(candidateList, nameCandidate);
		return numVotes;
	}
	
	private synchronized String getHashMD5(String name) throws RemoteException {
		System.out.println("Método HASH");
		String nomeEleitor = name;
		String hashMd5 = null;

	      try {
	         // Gera o hash MD5 baseado no nome do eleitor
	         MessageDigest md = MessageDigest.getInstance("MD5");
	         md.update(nomeEleitor.getBytes());
	         byte[] md5 = md.digest();

	         // Formata em Hexadecimal minúsculo para exibir na tela
	         BigInteger numMd5 = new BigInteger(1, md5);
	         hashMd5 = String.format("%022x", numMd5); 
	               
	         System.out.println("Nome: " + nomeEleitor + "\nMD5: " + hashMd5);
	         return hashMd5;
	      } catch (NoSuchAlgorithmException e) {
	         throw new RuntimeException(e);
	      }
	}
	

}
