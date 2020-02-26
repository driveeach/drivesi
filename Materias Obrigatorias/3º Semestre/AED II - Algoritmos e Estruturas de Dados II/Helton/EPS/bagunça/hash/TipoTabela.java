package hash;

public interface TipoTabela {
	
	public long insere(int registro, Hash h, Rehash rh);
	
	public long busca(int registro, Hash h, Rehash rh, boolean confirma);
	
	public long remove(int registro, Hash h, Rehash rh, boolean confirma);
}
