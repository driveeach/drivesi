public class AtividadeGuloso {

	Atividade[] SelecionaAtividade(Atividade[] atividades) {
		Atividade[] tmp = new Atividade[atividades.length];
		tmp[0] = atividades[0];
		int contador = 1;
		int last = 0;

		for (int i = 1; i < atividades.length; i++) {
			if (atividades[i].getStart() >= atividades[last].getFinish()) {
				tmp[contador++] = atividades[i];
				last = i;
			}
		}

		Atividade[] max = new Atividade[contador];
		for (int i = 0; i < contador; i++) {
			max[i] = tmp[i];
		}
		return max;
	}

	public static void main(String[] args) {

		Atividade[] a = new Atividade[11];
		a[0] = new Atividade(1, 4);
		a[1] = new Atividade(3, 5);
		a[2] = new Atividade(0, 6);
		a[3] = new Atividade(5, 7);
		a[4] = new Atividade(3, 8);
		a[5] = new Atividade(5, 9);
		a[6] = new Atividade(6, 10);
		a[7] = new Atividade(8, 11);
		a[8] = new Atividade(8, 12);
		a[9] = new Atividade(2, 13);
		a[10] = new Atividade(12, 14);
		
		AtividadeGuloso guloso = new AtividadeGuloso();
		
		Atividade[] max = guloso.SelecionaAtividade(a);
		
		for (int i = 0; i < max.length; i++) {
			System.out.println(max[i]);
		}
	}
}

class Atividade {
	private int start;
	private int finish;

	public Atividade(int start, int finish) {
		this.start = start;
		this.finish = finish;
	}

	public int getStart() {
		return start;
	}

	public int getFinish() {
		return finish;
	}

	public String toString() {
		return "(" + start + "," + finish + ")";
	}

}
