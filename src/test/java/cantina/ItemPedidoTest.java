package cantina;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import com.santander.cantina.dao.ItemPedidoDao;
import com.santander.cantina.modelo.TopItemPedido;
import com.santander.cantina.util.JpaUtil;

public class ItemPedidoTest {

	private EntityManager em = JpaUtil.getEntityManager();
	private ItemPedidoDao itemPedidoDao = new ItemPedidoDao(em);

	
	@Test
	void topProdutosPedidos() {

		List<TopItemPedido> relatorio = itemPedidoDao.listaProdutosMaisVendidos();

		relatorio.stream().forEach(
				r -> System.out.println("nome " + r.getNome() + " quantidade " + r.getQuantidade() + " preco" + r.getPreco()));

		assertEquals(3, relatorio.size());
		assertEquals(10, relatorio.get(0).getQuantidade());
	}
	
	
	@Test
	void produtoMaisVendido() {

		assertEquals(10, itemPedidoDao.produtoMaisVendido(1).getQuantidade());
	}
	
}
