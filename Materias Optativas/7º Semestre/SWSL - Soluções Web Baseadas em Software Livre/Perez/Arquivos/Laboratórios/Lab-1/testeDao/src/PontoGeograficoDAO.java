import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PontoGeograficoDAO {
	private Connection connection;

	public PontoGeograficoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(PontoGeografico ponto) {

		try {
			String sql = "insert into ponto_geografico(IdPonto, NomePonto, graus, minutos,segundos) values(?,?,?,?,?)";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.clearParameters();
			stmt.setInt(1, ponto.getIdPonto());
			stmt.setString(2, ponto.getNomePonto());
			stmt.setDouble(3, ponto.getGraus());
			stmt.setDouble(4, ponto.getMinutos());
			stmt.setDouble(5, ponto.getSegundos());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<PontoGeografico> getLista() {

		try {
			String sql = "select * from ponto_geografico";
			List<PontoGeografico> pontos = new ArrayList<PontoGeografico>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PontoGeografico ponto = new PontoGeografico();
				ponto.setNomePonto(rs.getString("NomePonto"));
				ponto.setIdPonto(rs.getInt("idPonto"));
				ponto.setGraus(rs.getDouble("graus"));
				ponto.setMinutos(rs.getDouble("minutos"));
				ponto.setSegundos(rs.getDouble("segundos"));
				pontos.add(ponto);
			}
			rs.close();
			stmt.close();
			return pontos;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void remove(PontoGeografico ponto){
		
		try {
			String sql = "delete from ponto_geografico where IdPonto=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, ponto.getIdPonto());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
