import org.junit.Test;

import com.tcc.flyk.entity.Administrador;
import com.tcc.flyk.persistence.AdministradorDAO;
import com.tcc.flyk.persistence.impl.AdministradorDAOImpl;
import com.tcc.flyk.service.CadastroAdminstradorService;

import junit.framework.TestCase;


public class CadastroAdminstradorTest extends TestCase {
	
	private static final CadastroAdminstradorService service = new CadastroAdminstradorService();
	private AdministradorDAO admDAO = new AdministradorDAOImpl();

	@Test
	public void testarCadastroNovoAdministrador(){
		boolean cadastrado = false;
		Administrador adm = new Administrador();
		
		adm.setNome("Nome Teste");
		adm.setUsuario("usuarioTeste");
		adm.setSenha("senha");
		
		cadastrado = service.CadastrarNovoAdministrador(adm);
		
		assertEquals(true, cadastrado);
		
	}
	
	@Test
	public void consultarAdministrador(){
		admDAO.consulta();

		assertTrue(true);
	}
}
