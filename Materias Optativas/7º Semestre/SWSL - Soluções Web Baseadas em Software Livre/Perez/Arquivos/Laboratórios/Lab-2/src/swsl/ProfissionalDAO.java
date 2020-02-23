package swsl;

import java.sql.Connection;
import java.sql.Date; //importante
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProfissionalDAO {
	private Connection conexao;
	
	public ProfissionalDAO() {
		this.conexao = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Profissional p) {
		String sql = "insert into profissionais (nome,endereco,profissao,dataNascimento) values (?,?,?,?)";
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.clearParameters();
			stmt.setString(1, p.getNome());
			stmt.setString(2, p.getEndereco());
			stmt.setString(3, p.getProfissao());
			stmt.setDate(4, new Date(p.getDataNascimento().getTimeInMillis()));
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Profissional> getLista() {

		try {
			List<Profissional> profissionais = new ArrayList<Profissional>();
			
			PreparedStatement stmt = this.conexao.prepareStatement("select * from profissionais");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Profissional p = new Profissional();
				p.setNumeroControle(rs.getLong("numeroControle"));
				p.setNome(rs.getString("nome"));
				p.setEndereco(rs.getString("endereco"));
				p.setProfissao(rs.getString("profissao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				p.setDataNascimento(data);
				profissionais.add(p);
			}
			rs.close();
			stmt.close();
			return profissionais;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public Profissional recupera(Long numero)
	{
		Profissional p = new Profissional();
		try {
			String busca = "select * from profissionais where numeroControle=?";
			PreparedStatement stmt = this.conexao.prepareStatement(busca);
			stmt.clearParameters();
			stmt.setLong(1, numero);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				p.setNumeroControle(rs.getLong("numeroControle"));
				p.setNome(rs.getString("nome"));
				p.setEndereco(rs.getString("endereco"));
				p.setProfissao(rs.getString("profissao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				p.setDataNascimento(data);
			}
			rs.close();
			stmt.close();
			return p;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void remove(Profissional profissional){
		try {
			PreparedStatement ps = conexao.prepareStatement("delete from profissional where id=?");
			ps.setLong(1, profissional.getNumeroControle());
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}