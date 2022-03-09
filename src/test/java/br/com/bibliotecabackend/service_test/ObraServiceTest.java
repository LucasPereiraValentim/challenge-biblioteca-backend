package br.com.bibliotecabackend.service_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import br.com.bibliotecabackend.api.input.Pesquisa;
import br.com.bibliotecabackend.exception.TituloException;
import br.com.bibliotecabackend.model.Autor;
import br.com.bibliotecabackend.model.Obra;
import br.com.bibliotecabackend.service.ObraService;

@SpringBootTest
public class ObraServiceTest {

	@Autowired
	private ObraService obraService;
	
	private List<Autor> autores;
	
	@BeforeEach
	public void beforeEach() {
		this.autores = new ArrayList<>();
	}
	
	@Test
	@DisplayName("Salvar obra e autores")
	public void salvarObraAutoresTest() {
		autores.add(new Autor("Robert C. Martin"));
		Obra obra = this.obraService.salvar(new Obra("Clean code", "Prentice Hall PTR", autores));
		assertEquals(obra.getTitulo(), "Clean code");
		assertEquals(obra.getEditora(), "Prentice Hall PTR");
		assertEquals(obra.getAutores().get(0).getNome(), "Robert C. Martin");
	}
	
	@Test
	@DisplayName("Excluir obra")
	public void excluirObraAutoresTest() {
		String msg = this.obraService.excluir(22L);
		assertEquals("Obra excluída com sucesso", msg);
	}
	
	@Test
	@DisplayName("Não pode permitir cadastro de obra com titulo repetido")
	public void naoPodePermitirCadastroDuplicadoDeTituloDeObraTest() {
		autores.add(new Autor("Robert C. Martin"));
		TituloException tituloException = assertThrows(TituloException.class, () -> {
			this.obraService.salvar(new Obra("Clean code", "Prentice Hall PTR", autores));
		});
		assertEquals("Já existe uma obra cadastrada com este título", tituloException.getMessage());
	}
	
	@Test
	@DisplayName("Obter obra e autores")
	public void obterObraAutoresTest() {
		final Long id = 24L;
		Obra obra = this.obraService.obterObra(id);
		assertNotNull(obra);
		assertEquals(id, obra.getId());	
	}
	
	@Test
	@DisplayName("Obter lista de obras e autores")
	public void obterListaObraAutoresTest() {
		Page<Obra> pageObrasAutores = this.obraService.getListaObra(PageRequest.of(0, 5, Direction.ASC, "titulo"));
		assertNotNull(pageObrasAutores);
	}
	
	@Test
	@DisplayName("Pesquisar lista de obras por nome")
	public void pesquisarObrasAutoresTest() {
		Page<Obra> pagePesquisaObras = this.obraService.pesquisar(new Pesquisa("Clean code"),
										PageRequest.of(0, 5, Direction.ASC, "titulo"));		
		assertNotNull(pagePesquisaObras);
		assertEquals("Clean code", pagePesquisaObras.getContent().get(0).getTitulo());
	}
	
	@Test
	@DisplayName("Atualizar obra e autores")
	public void atualizarObraAutoresTest() {
		autores.add(new Autor("Robert C. Martin"));
		Obra obra = this.obraService.atualizar(24L, 
							new Obra("Código limpo: Habilidades práticas do Agile Software", "Altas Books", autores));
		assertEquals("Código limpo: Habilidades práticas do Agile Software", obra.getTitulo());
		assertEquals("Altas Books", obra.getEditora());
		assertEquals("Robert C. Martin", obra.getAutores().get(0).getNome());
	}
}
