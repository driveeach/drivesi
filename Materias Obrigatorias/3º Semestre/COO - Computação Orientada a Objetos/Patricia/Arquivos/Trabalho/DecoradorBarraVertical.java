class DecoradorBarraVertical extends JanelaDecorator {

	public DecoradorBarraVertical(Janela janelaDecorada) {
		super(janelaDecorada);
	}

	public void draw() {
		drawBarraVertical();
		janelaDecorada.draw();
	}

	private void drawBarraVertical() {
		System.out.println("desenha uma barra vertical na janela");
	}

}
