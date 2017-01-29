package br.edu.ifpb.memoriam.facade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.edu.ifpb.memoriam.dao.ContatoDAO;
import br.edu.ifpb.memoriam.dao.OperadoraDAO;
import br.edu.ifpb.memoriam.dao.PersistenceUtil;
import br.edu.ifpb.memoriam.dao.UsuarioDAO;
import br.edu.ifpb.memoriam.entity.Contato;
import br.edu.ifpb.memoriam.entity.Operadora;
import br.edu.ifpb.memoriam.entity.Usuario;

public class ContatoController {
	private static Contato contato = new Contato();
	private static Resultado resultado = new Resultado();	

	public List<Contato> consultar(Usuario usuario){
		ContatoDAO dao = new ContatoDAO(PersistenceUtil.getCurrentEntityManager());
		List<Contato> contatos = dao.findAllFromUser(usuario);
		return contatos;
	}
	
	public Contato buscar(Map<String,String[]> parametros){
		Contato cttFound = new Contato();
		ContatoDAO dao = new ContatoDAO(PersistenceUtil.getCurrentEntityManager());
		String[] idContato =parametros.get("id");
		cttFound= dao.find(Integer.parseInt(idContato[0]));
		
		return cttFound;
	}
	
	public Resultado excluir(Map<String, String[]> parametros){
		ContatoDAO dao = new ContatoDAO(PersistenceUtil.getCurrentEntityManager());
		String[] ids = parametros.get("delids");
		resultado.getMensagens().removeAll(resultado.getMensagens());
		resultado.setErro(false);
		
		
		dao.beginTransaction();
		for(int i=0;i<ids.length;i++){
			Contato c = dao.find(Integer.parseInt(ids[i]));
			dao.delete(c);
			resultado.setErro(false);
		}
		dao.commit();
		
		
		resultado.addMensagem(new Mensagem("Contato(s) excluídos com sucesso",Categoria.INFO));
		return resultado;
	}
	
	public Resultado cadastrar(Map<String, String[]> parametros){
		resultado.setErro(false);
		resultado.getMensagens().removeAll(resultado.getMensagens());
		

		if (isParametrosValidos(parametros)) {
			
			ContatoDAO dao = new ContatoDAO(PersistenceUtil.getCurrentEntityManager());
			
			dao.beginTransaction();
			if (contato.getId() == null) {
				dao.insert(contato);
			}else {
				dao.update(contato);
			}
			
			dao.commit();
	
		}else{
			Mensagem msg = new Mensagem("Não foi possível cadastrar o contato",Categoria.ERRO);
			resultado.addMensagem(msg);;
			resultado.setErro(true);
		}

		return resultado;
	}
	
	private boolean isParametrosValidos(Map<String,String[]> parametros){
		
		//Nomes dos parâmetros vêm dos atributos 'name' das tags HTML do formulário
		

		String[] nome = parametros.get("nome");
		String[] fone = parametros.get("fone");
		String[] dataAniv = parametros.get("dataaniv");
		String[] idOperadora = parametros.get("operadora");
		String[] idUsuario = parametros.get("idusuario");
			
		resultado.setErro(false);
		
		
		if (nome == null || nome.length == 0 || nome[0].isEmpty()) {
			
			Mensagem msg = new Mensagem("Nome é campo obrigatório!",Categoria.AVISO);
			resultado.addMensagem(msg);;
			resultado.setErro(true);
		} else {
			contato.setNome(nome[0]);
		}
		
		if (fone == null || fone.length == 0 || fone[0].isEmpty()){
			Mensagem msg = new Mensagem("Telefone é campo obrigatório!",Categoria.AVISO);
			resultado.addMensagem(msg);;
			resultado.setErro(true);
		}else{
			contato.setFone(fone[0]);
		}
	
		if (dataAniv == null || dataAniv.length == 0 || dataAniv[0].isEmpty()) {
			Mensagem msg = new Mensagem("Telefone é campo obrigatório!",Categoria.AVISO);
			resultado.addMensagem(msg);;
			resultado.setErro(true);	
		}else {
			if (dataAniv[0].matches(
					"(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d{2,2}")) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					sdf.setLenient(false);
					Date dataIni = sdf.parse(dataAniv[0]);
					contato.setDataAniversario(dataIni);
				} catch (ParseException e) {
					Mensagem msg = new Mensagem("Data inválida para a data de aniversário!",Categoria.ERRO);
					resultado.addMensagem(msg);;
					resultado.setErro(true);
				}
			}else {
				Mensagem msg = new Mensagem("Formato inválido para a data de aniversário	(use dd/mm/aaaa)!",Categoria.AVISO);
				resultado.addMensagem(msg);;
				resultado.setErro(true);
			}
		}
		
		
		if (idOperadora == null || idOperadora.length == 0 || idOperadora[0].isEmpty()) {
			Mensagem msg = new Mensagem("A operadora não foi selecionada",Categoria.AVISO);
			resultado.addMensagem(msg);;
			resultado.setErro(true);
		}else{
			Operadora operadora;
			OperadoraDAO opDao = new OperadoraDAO(
			PersistenceUtil.getCurrentEntityManager());
			operadora = opDao.find(Integer.parseInt(idOperadora[0]));
			contato.setOperadora(operadora);
		}
		
		
		if (idUsuario == null || idUsuario.length == 0 || idUsuario[0].isEmpty()){
			Mensagem msg = new Mensagem("Não há usuário logado!",Categoria.ERRO);
			resultado.addMensagem(msg);;
			resultado.setErro(true);
		}else{
			UsuarioDAO daousuario = new UsuarioDAO(PersistenceUtil.getCurrentEntityManager());
			Usuario usuario = new Usuario(); 
			usuario = daousuario.find(Integer.parseInt(idUsuario[0]));
			contato.setUsuario(usuario);
		}
		
		return !resultado.isErro();
	}
}
