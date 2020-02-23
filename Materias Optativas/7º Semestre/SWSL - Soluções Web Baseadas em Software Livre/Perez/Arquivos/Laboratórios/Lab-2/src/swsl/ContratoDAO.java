package swsl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ContratoDAO {
	private Connection connection;
	
	public ContratoDAO(){
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Contrato c, Profissional p) {
		
		String sql = "insert into contratos (profnumero,dataInicio,dataTermino,valorHora) values (?,?,?,?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.clearParameters();
			stmt.setLong(1, p.getNumeroControle());
			stmt.setDate(2, new Date(c.getDataInicio().getTimeInMillis()));
			stmt.setDate(3, new Date(c.getDataTermino().getTimeInMillis()));
			stmt.setDouble(4, c.getValorHora());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void remove(Contrato contrato){
		try {
			PreparedStatement ps = connection.prepareStatement("delete from contrato where numero=?");
			ps.setLong(1, contrato.getNumero());
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Contrato> getLista(Profissional p) {

		try {
			List<Contrato> contratos = new ArrayList<Contrato>();
			String busca = "select * from contratos where profnumero=?";
			PreparedStatement stmt = this.connection.prepareStatement(busca);
			stmt.clearParameters();
			stmt.setLong(1, p.getNumeroControle());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Contrato c = new Contrato();
				c.setNumero(rs.getLong("numero"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataInicio"));
				c.setDataInicio(data);
				data.setTime(rs.getDate("dataTermino"));
				c.setDataTermino(data);
				c.setValorHora(rs.getDouble("valorHora"));
				contratos.add(c);
			}
			rs.close();
			stmt.close();
			return contratos;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
