package br.edu.ifpb.memoriam.facade;

import java.util.List;
import java.util.Map;

import br.edu.ifpb.memoriam.dao.OperadoraDAO;
import br.edu.ifpb.memoriam.dao.PersistenceUtil;
import br.edu.ifpb.memoriam.entity.Operadora;

public class OperadoraController {
	private static Operadora operadora;
	private static Resultado resultado = new Resultado();

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
		resultado.setErro(false);
		resultado.getMensagens().removeAll(resultado.getMensagens());
		
		if (isParametrosValidos(parametros)){
			OperadoraDAO dao = new OperadoraDAO(PersistenceUtil.getCurrentEntityManager());
			
			dao.beginTransaction();
	
			if (operadora.getId() == null){
				dao.insert(operadora);
			}else{
				dao.update(operadora);
			}
			
			dao.commit();
		}else{
			resultado.setEntidade(operadora);
			Mensagem msg = new Mensagem("Não foi possível cadastrar a operadora",Categoria.ERRO);
			resultado.addMensagem(msg);;
			resultado.setErro(true);
		}
		return resultado;
	}
	
	private boolean isParametrosValidos(Map<String,String[]> parametros){
			
		String[] nome = parametros.get("nome");
		String[] prefixo = parametros.get("prefixo");
	
		resultado.setErro(false);
		
		operadora = new Operadora();
			
		if (nome == null || nome.length == 0 || nome[0].isEmpty()){
			Mensagem msg = new Mensagem("Nome é campo obrigatório",Categoria.AVISO);
			resultado.addMensagem(msg);;
			resultado.setErro(true);
		}else{
			operadora.setNome(nome[0]);
		}
		
		if (prefixo == null || prefixo.length == 0 || prefixo[0].isEmpty()){
			Mensagem msg = new Mensagem("Prefixo é campo obrigatório!",Categoria.AVISO);
			resultado.addMensagem(msg);;
			resultado.setErro(true);
		}else{
			operadora.setPrefixo(Integer.parseInt(prefixo[0]));
		}

		return !resultado.isErro();
	}
	
}
