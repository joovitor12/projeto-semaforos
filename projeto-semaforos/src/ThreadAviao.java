import java.util.concurrent.Semaphore;
public class ThreadAviao extends Thread{
	private Semaphore[] pistas;
	private Integer numeroAviao;
	private static int RANKING_SAIDA;
	public ThreadAviao(Semaphore[] pistas, Integer numeroAviao) {
		super();
		this.pistas = pistas;
		this.numeroAviao = numeroAviao;
	}
	
	@Override
	public void run() {
		//pista selecionada
		int pistaSelecionada = 0;
		try {
			//diferen�a de inicio de threads
			sleep((int) (Math.random() * 1000));
			//manobra
			manobra();
			//taxiar
			taxi();
			
			pistaSelecionada = escolhePista();
			//adiciona a pista selecionada utilizado
			//o acquire (se o semaforo estiver vazio, 
			//ele come�a a rodar, se estiver cheio 
			//ele espera para rodar no pr�ximo ciclo
			pistas[pistaSelecionada].acquire();
			//inicia o processo de decolagem
			decolar(pistaSelecionada);
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			//assim que um aviao decola, o proximo tem que ser chamado
			//com isso utiliza-se o release que chama o proximo da fila de threads 
			pistas[pistaSelecionada].release();
			//e por fim o voo, que � o movimento em que o aviao est� no ar
			voar();
		}
	}
	
	private int escolhePista() {
		//gera o numero de 1 a 10, se for 0 ele vai na pista sul, se for 1 vai na pista norte
		//se a pista sul ou norte estiver com espa�o livre, o aviao voa na pista que possui espa�o livre
		//se nao, se a fila de threads na pista sul for menor que a da pista norte,
		//o aviao vai para a pista sul, caso o contrario ele ira para a pista norte
		int pistaSelecionada = (int) Math.round(Math.random());
		if(pistas[pistaSelecionada].availablePermits() != 0) {
			return pistaSelecionada;
		} else {
			if(pistas[0].getQueueLength() <= pistas[1].getQueueLength()) {
				return 0;
			} else {
				return 1;
			}
		}
	}
	
	public void manobra() {
		System.out.println("Avi�o " + numeroAviao + " est� manobrando...");
		//randomizador de tempo de manobras do aviao 1-12
		int time = (int) ((Math.random() * 4000) + 3000);
		try {
			sleep((int) ((Math.random() * 4000) + 3000));
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Avi�o " + numeroAviao + " manobrou em " + time +"s.");
	}
	
	public void taxi() {
		//randomizador de tempo para o movimento de taxiamento do aviao
		System.out.println("Avi�o " + numeroAviao + " est� taxiando...");
		int time = (int) ((Math.random() * 5000) + 5000);
		try {
			sleep(time);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void decolar(int pistaSelecionada) {
		//utiliza um if tern�rio para decidir a pista do avi�o em quest�o
		String nomePista = pistaSelecionada == 0 ? "sul" : "norte";
		System.out.println("Avi�o " + numeroAviao + " est� decolando na pista " + nomePista);
		int time = (int) ((Math.random() * 3000) + 1000);
		try {
			sleep(time);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Avi�o " + numeroAviao + " decolou na pista " + nomePista);
	}
	
	public void voar() {
		//randomizador de tempo para o movimento de voo do aviao
		System.out.println("Avi�o " + numeroAviao + " est� se afastando...");
		int time = (int) ((Math.random() * 5000) + 3000);
		try {
			sleep(time);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//contador para retornar a ordem de voo dos avioes
		RANKING_SAIDA++;
		System.out.println("Avi�o " + numeroAviao + " foi o " + RANKING_SAIDA + "th a ficar longe em " + time + "s.");
		
		
	}
} 
