package br.edu.ifpb.memoriam.facade;

import java.util.List;

public class Resultado {
	private Object entidade;
	private boolean erro;
	private List<String> mensagensErro;
	private List<String> mensagensSucesso;
	public Object getEntidade() {
		return entidade;
	}
	public void setEntidade(Object entidade) {
		this.entidade = entidade;
	}
	public boolean isErro() {
		return erro;
	}
	public void setErro(boolean erro) {
		this.erro = erro;
	}
	public List<String> getMensagensErro() {
		return mensagensErro;
	}
	public void setMensagensErro(List<String> mensagensErro) {
		this.mensagensErro = mensagensErro;
	}
	public List<String> getMensagensSucesso() {
		return mensagensSucesso;
	}
	public void setMensagensSucesso(List<String> mensagensSucesso) {
		this.mensagensSucesso = mensagensSucesso;
	}

		
}
