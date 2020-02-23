package swsl;

import java.sql.SQLException;
import java.util.List;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SQLException {

		//inserir registros de profissionais
		/*
		Profissional p1 = new Profissional();
		p1.setNome("Shirata");
		p1.setEndereco("Freguesia do Ó");
		p1.setProfissao("Estudante");
		p1.setDataNascimento(Calendar.getInstance());
		*/
		//gravar na conexao
		/*
		ProfissionalDAO dao = new ProfissionalDAO();
		Profissional x = dao.recupera((long) 3);
		*/
		/*
		Contrato c1 = new Contrato();
		c1.setDataInicio(Calendar.getInstance());
		c1.setDataTermino(Calendar.getInstance());
		c1.setValorHora((double) 24.5);
		
		ContratoDAO cdao = new ContratoDAO();
		cdao.adiciona(c1, x);
		*/
		
		//ProfissionalDAO dao = new ProfissionalDAO();
		//listar profissionais
		/*
		List<Profissional> prof = dao.getLista();

		for (Profissional p : prof) {
			System.out.println("Nome: " +p.getNome());
			System.out.println("Endereço: " + p.getEndereco());
			System.out.println("Profissão: " + p.getProfissao());
			System.out.println("Data de Nascimento: " + p.getDataNascimento().getTime() + "\n");
		}
		*/

		/*
		 * Faça um programa que liste os contratos 
		 * associados a um determinado profissional.
		 */
		
		ProfissionalDAO pdao = new ProfissionalDAO();
		Profissional p = pdao.recupera((long) 1);
		ContratoDAO cdao = new ContratoDAO();
		List<Contrato> contratos = cdao.getLista(p);
		
		System.out.println("Contratos de " + p.getNome() + ":");
		
		for (Contrato c : contratos) {
			System.out.println("Numero: " +c.getNumero());
			System.out.println("Data de Início: " + c.getDataInicio().getTime());
			System.out.println("Data de Início: " + c.getDataTermino().getTime());
			System.out.println("Valor-Hora: " + c.getValorHora() + "\n");
		}
	}

}
