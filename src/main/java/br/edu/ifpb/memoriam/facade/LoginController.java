package br.edu.ifpb.memoriam.facade;

import java.util.Map;

import br.edu.ifpb.memoriam.dao.UsuarioDAO;
import br.edu.ifpb.memoriam.entity.Usuario;
import br.edu.ifpb.memoriam.util.PasswordUtil;

public class LoginController {

	public Resultado isValido(Map<String, String[]> parametros) {
		Resultado resultado = new Resultado();
		resultado.setErro(false);
		
		String login = parametros.get("login")[0];
		String passwd = parametros.get("senha")[0];
		
		UsuarioDAO udao = new UsuarioDAO();
		Usuario user = udao.findByLogin(login);
		
		if (user != null){
			if (user.getSenha().equals(PasswordUtil.encryptMD5(passwd))){
				resultado.setEntidade(user);
			}
			else{
				resultado.setErro(true);
				resultado.addMensagem(new Mensagem("Usuário ou senha inválido(a).",Categoria.ERRO));
			}
		}else{
			resultado.setErro(true);
			resultado.addMensagem(new Mensagem("Usuário ou senha inválido(a).",Categoria.ERRO));
		}
		
		return resultado;
	}

}
