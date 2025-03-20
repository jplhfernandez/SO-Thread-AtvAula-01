package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadCaminhada extends Thread {
	private Random aleatorio = new Random();
	private int idCavaleiro;
	private static boolean tocha = false;
	private static boolean pedra = false;
	private boolean monstro = false;
	private Semaphore semaforo;

	public ThreadCaminhada(Semaphore semaforo) {
		// TODO Auto-generated constructor stub
		super();
		this.idCavaleiro = (int)getId();
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		cavaleiroCaminhando();
		try {
			semaforo.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			semaforo.release();
			portaCaminho();
		}
	}

	private void cavaleiroCaminhando() {
		// TODO Auto-generated method stub
		int distanciaTotal = 2000;
		int distanciaPercorrida = 0;
		int distanciaPerAnt = 0;
		int deslocamento = 0;
		int teste = 0;
		while (distanciaPercorrida < distanciaTotal) {
			distanciaPerAnt = deslocamento;
			deslocamento = aleatorio.nextInt(2,5);
			distanciaPercorrida += deslocamento;
			try {
				sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        if(tocha == false & distanciaPercorrida >= 500) {
	        	tocha = true;
	        	deslocamento = aleatorio.nextInt(2,7);
	        	System.out.println("O Cavaleiro " + idCavaleiro + " pegou a tocha "
	        			+ "e recebeu +2 de velocidade");
	        	teste = idCavaleiro;
	        }
	        
	        if(teste != idCavaleiro & pedra == false & distanciaPercorrida >= 1500) {
	        	pedra = true;
	        	deslocamento = aleatorio.nextInt(2,7);
	        	System.out.println("O Cavaleiro " + idCavaleiro + " pegou a pedra "
	        			+ "e recebeu +2 de velocidade");
	        }
		}
		System.out.println("#"+idCavaleiro+" chegou na porta");
	}


	private void portaCaminho() {
		int vet[] = new int[4];
		for(int i = 0; i < 4; i++) {
			vet[i] = i;
		}
		if(monstro == false & vet[2]) {
			System.out.println("");
		}
	}
}
