import java.util.concurrent.Semaphore;
public class Program {

	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore pistaSul = new Semaphore(permissoes);
		Semaphore pistaNorte = new Semaphore(permissoes);
		Semaphore[] pistas = new Semaphore[] {pistaSul, pistaNorte};
		
		for (int i = 0; i < 12; i++) {
			Thread threadAviao = new ThreadAviao(pistas,i);
			threadAviao.start();
		}
	}

}
