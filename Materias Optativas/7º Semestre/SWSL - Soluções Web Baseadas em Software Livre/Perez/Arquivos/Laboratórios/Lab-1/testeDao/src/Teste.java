import java.util.List;

public class Teste {
	public static void main(String[] Args) {

		PontoGeograficoDAO dao = new PontoGeograficoDAO();

		PontoGeografico ponto = new PontoGeografico();
		ponto.setIdPonto(02);
		ponto.setNomePonto("segundo");
		ponto.setGraus(5.00);
		ponto.setMinutos(2.0);
		ponto.setSegundos(1.00);
		dao.adiciona(ponto);
		//dao.remove(ponto);
		
		List<PontoGeografico> pontos = dao.getLista();
		for (PontoGeografico pt : pontos) {

			System.out.println(pt.getNomePonto());
			System.out.println(pt.getIdPonto());
			System.out.println(pt.getGraus());
			System.out.println(pt.getMinutos());
			System.out.println(pt.getSegundos());
			
			
		}
	}
}
