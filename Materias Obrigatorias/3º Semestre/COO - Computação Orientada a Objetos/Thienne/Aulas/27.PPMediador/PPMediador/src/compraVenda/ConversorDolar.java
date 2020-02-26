package compraVenda;

public class ConversorDolar {

	Mediator mediator;

	public static final float DOLAR_UNID = 1.0f;
	public static final float EURO_UNID = 0.7f;
	public static final float REAL_UNID = 0.3f;

	public ConversorDolar(Mediator mediator) {
		this.mediator = mediator;
		mediator.registreConversorDolar(this);
	}

	private float converteEurosParaDolar(float euros) {
		float dolares = euros * (DOLAR_UNID / EURO_UNID);
		System.out.println("Convertendo " + euros + " euros para " + dolares + " dolares");
		return dolares;
	}

	private float converteRealParaDolar(float reais) {
		float dolares = reais * (DOLAR_UNID / REAL_UNID);
		System.out.println("Convertendo " + reais + " reais para " + dolares + " dolares");
		return dolares;
	}

	public float converteMoedaParaDolar(float amount, String unidadeMoeda) {
		if ("real".equalsIgnoreCase(unidadeMoeda)) {
			return converteRealParaDolar(amount);
		} else {
			return converteEurosParaDolar(amount);
		}
	}
}
