package br.edu.ifpb.memoriam.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.edu.ifpb.memoriam.dao.ContatoDAO;
import br.edu.ifpb.memoriam.dao.ManagedEMContext;
import br.edu.ifpb.memoriam.dao.OperadoraDAO;
import br.edu.ifpb.memoriam.dao.PersistenceUtil;
import br.edu.ifpb.memoriam.dao.UsuarioDAO;
import br.edu.ifpb.memoriam.entity.Contato;
import br.edu.ifpb.memoriam.entity.Operadora;
import br.edu.ifpb.memoriam.entity.Perfil;
import br.edu.ifpb.memoriam.entity.Usuario;
import br.edu.ifpb.memoriam.util.PasswordUtil;

/**
 * @author fred
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InsereDadosBanco {
	private static EntityManagerFactory emf;
	private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	private EntityManager em;

	@BeforeClass
	public static void init() {
		PersistenceUtil.createEntityManagerFactory("memoriam");
		emf = PersistenceUtil.getEntityManagerFactory();
		ManagedEMContext.bind(emf, emf.createEntityManager());
		System.out.println("init()");
	}

	@AfterClass
	public static void destroy() {
		if (emf != null) {
			emf.close();
			System.out.println("destroy()");
		}
	}

	@Before
	public void initEM() {
		em = emf.createEntityManager();
	}
	
	/**
	 * Insere Assuntos
	 */
	@Test
	public void test01InsereOperadoras() {
		try {
			OperadoraDAO dao = new OperadoraDAO(em);
			dao.beginTransaction();
			Operadora a = new Operadora();
			a.setNome("Oi");
			a.setPrefixo(31);
			dao.insert(a);
			a = new Operadora();
			a.setNome("Vivo");
			a.setPrefixo(53);
			dao.insert(a);
			a = new Operadora();
			a.setNome("Claro");
			a.setPrefixo(41);
			dao.insert(a);
			dao.commit();
		} catch (Exception e) {
			Assert.fail("Erro de BD" + e.getMessage());
		}
	}


	@Test
	public void testInsereUsuarios() {
		Usuario u1 = new Usuario();
		u1.setNome("Carl Sagan");
		u1.setEmail("sagan@ifpb.edu.br");
		u1.setSenha(PasswordUtil.encryptMD5("cosmos"));
		u1.setAtivo(true);
		u1.setPerfil(Perfil.BASIC);
		
		Usuario u2 = new Usuario();
		u2.setNome("Alan Turing");
		u2.setEmail("turing@ifpb.edu.br");
		u2.setSenha(PasswordUtil.encryptMD5("enigma"));
		u2.setAtivo(true);
		u2.setPerfil(Perfil.BASIC);
		
		Usuario u3 = new Usuario();
		u3.setNome("Administrador");
		u3.setEmail("admin@ifpb.edu.br");
		u3.setSenha(PasswordUtil.encryptMD5("root123"));
		u3.setAtivo(true);
		u3.setPerfil(Perfil.ADMIN);
		
		UsuarioDAO udao = new UsuarioDAO(em);
		udao.beginTransaction();
		udao.insert(u1);
		udao.insert(u2);
		udao.insert(u3);
		udao.commit();
	}

	@Test
	public void testInsereContatos() {
		try {
			OperadoraDAO odao = new OperadoraDAO(em);
			Operadora o1 = odao.find(1);
			Operadora o2 = odao.find(2);
			Operadora o3 = odao.find(3);
			
			UsuarioDAO udao = new UsuarioDAO(em);
			Usuario sagan = udao.find(1);
			Usuario turing = udao.find(2);
			
			
			ContatoDAO dao = new ContatoDAO(em);
			dao.beginTransaction();
			Contato a = new Contato();
			a.setNome("Jose Carlos da Silva");
			a.setFone("3422-9900");
			a.setDataAniversario(new Date());
			a.setOperadora(o1);
			a.setUsuario(sagan);
			dao.insert(a);
			a = new Contato();
			a.setNome("Maria Clara dos Santos");
			a.setFone("3662-5536");
			a.setDataAniversario(new Date());
			a.setOperadora(o2);
			a.setUsuario(sagan);
			dao.insert(a);
			a = new Contato();
			a.setNome("Joao Firmino da Costa");
			a.setFone("3556-8433");
			a.setDataAniversario(new Date());
			a.setOperadora(o1);
			a.setUsuario(sagan);
			dao.insert(a);
			a = new Contato();
			a.setNome("Priscila Almeida Pontes");
			a.setFone("3417-4237");
			a.setDataAniversario(new Date());
			a.setOperadora(o3);
			a.setUsuario(sagan);
			dao.insert(a);
			a = new Contato();
			a.setNome("Walter Pontes Fontes");
			a.setFone("3417-4645");
			a.setDataAniversario(new Date());
			a.setOperadora(o2);
			a.setUsuario(sagan);
			dao.insert(a);
			a = new Contato();
			a.setNome("Amanda Correia Lima");
			a.setFone("9888-4099");
			a.setDataAniversario(new Date());
			a.setOperadora(o2);
			a.setUsuario(turing);
			dao.insert(a);	
			a = new Contato();
			a.setNome("Rogerio Nunes");
			a.setFone("98388-4787");
			a.setOperadora(o1);
			a.setDataAniversario(new Date());
			a.setUsuario(turing);
			dao.insert(a);
			a = new Contato();
			a.setNome("Carol Soares Barbosa");
			a.setFone("98747-4849");
			a.setDataAniversario(new Date());
			a.setOperadora(o3);
			a.setUsuario(turing);
			dao.insert(a);
			a = new Contato();
			a.setNome("Cesar Ferreira da Silva");
			a.setFone("98821-4899");
			a.setDataAniversario(new Date());
			a.setOperadora(o1);
			a.setUsuario(turing);
			dao.insert(a);
			a = new Contato();
			a.setNome("Natalia Seixas Gomes");
			a.setFone("94432-0199");
			a.setDataAniversario(new Date());
			a.setOperadora(o2);	
			a.setUsuario(turing);
			dao.insert(a);
			dao.commit();
		} catch (Exception e) {
			Assert.fail("Erro de BD: " + e);
		}
	}



}
