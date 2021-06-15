package cantina;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import com.santander.cantina.dao.ProdutoDao;
import com.santander.cantina.modelo.Produto;
import com.santander.cantina.util.JpaUtil;

public class ProdutoTest {

	private EntityManager em = JpaUtil.getEntityManager();
	private ProdutoDao produtoDao = new ProdutoDao(em);

	@Test
	void buscarPorNome() {	
		
		List<Produto> buscaProdutoPorNome = produtoDao.buscaProdutoPorNome(null);
		
		buscaProdutoPorNome.stream().forEach(
				r -> System.out.println("nome " + r.getNome()));
	}
	
}
