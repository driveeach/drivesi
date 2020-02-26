abstract class Janela {

	public abstract void draw();

}

class JanelaSimples extends Janela {
	
	public void draw() {
		System.out.println("desenha uma janela");
	}
}

abstract class JanelaDecorator extends Janela {
	
	protected Janela janelaDecorada;

	public JanelaDecorator(Janela janelaDecorada) {
		this.janelaDecorada = janelaDecorada;
	}

}
