package br.edu.ifpb.memoriam.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import br.edu.ifpb.memoriam.dao.OperadoraDAO;
import br.edu.ifpb.memoriam.dao.PersistenceUtil;
import br.edu.ifpb.memoriam.entity.Operadora;

public class OperadoraController {
	private Operadora operadora;
	private ArrayList<String> mensagensErro;

	public List<Operadora> consultar(){
		OperadoraDAO dao = new OperadoraDAO(PersistenceUtil.getCurrentEntityManager());
		List<Operadora> operadoras = dao.findAll();
		return operadoras;
	}
	
	public Operadora buscar(Map<String,String[]> parametros){
		Operadora oprFound = new Operadora();
		OperadoraDAO dao = new OperadoraDAO(PersistenceUtil.getCurrentEntityManager());
		String[] idOperadora = parametros.get("id");
		oprFound= dao.find(Integer.parseInt(idOperadora[0]));
		
		return oprFound;
	}
	
	public Resultado cadastrar(Map<String, String[]> parametros){
		Resultado resultado = new Resultado();
		
		if (isParametrosValidos(parametros)){
			OperadoraDAO dao = new OperadoraDAO(PersistenceUtil.getCurrentEntityManager());
			dao.beginTransaction();
	
			if (this.operadora.getId() == null){
				dao.insert(this.operadora);
			}else{
				dao.update(this.operadora);
			}
			
			dao.commit();
			resultado.setErro(false);
			resultado.setMensagensErro(Collections.singletonList("Operadora criado com sucesso"));
		}else{
			resultado.setEntidade(this.operadora);
			resultado.setErro(true);
			resultado.setMensagensErro(this.mensagensErro);
		}
		return resultado;
	}
	
	private boolean isParametrosValidos(Map<String,String[]> parametros){
			
		String[] id = parametros.get("id");
		String[] nome = parametros.get("nome");
		String[] prefixo = parametros.get("prefixo");

		
		this.operadora = new Operadora();
		this.mensagensErro = new ArrayList<String>();
	
		if (id != null && id.length > 0 && !id[0].isEmpty()){
			operadora.setId(Integer.parseInt(id[0]));
		}
		
		if (nome == null || nome.length == 0 || nome[0].isEmpty()){
			this.mensagensErro.add("Nome é campo obrigatório");
		}else{
			operadora.setNome(nome[0]);
		}
		
		if (prefixo == null || prefixo.length == 0 || prefixo[0].isEmpty()){
			this.mensagensErro.add("Prefixo é campo obrigatório!");
		}else{
			operadora.setPrefixo(Integer.parseInt(prefixo[0]));
		}

		return this.mensagensErro.isEmpty();
	}
	
}
