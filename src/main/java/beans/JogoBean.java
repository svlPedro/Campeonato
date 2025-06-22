package beans;
	
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.CampeonatoDAO;
import dao.JogoDAO;
import entidades.Campeonato;
import entidades.Jogo;


@ManagedBean
@SessionScoped
public class JogoBean{
	
	private Jogo jogo;
	private Jogo jogoSelecionado = new Jogo();
	private List<Jogo> lista;
	private Integer idCampeonato;
	private String nomeTimeFiltro;
	private List<Jogo> jogosFiltrados;
		
	public JogoBean() {
		jogo = new Jogo();
	}
	
	public String salvar() {	
		Campeonato campSelecionado = CampeonatoDAO.buscarPorId(idCampeonato);
		jogo.setCampeonato(campSelecionado);
		jogo.setDataCadastro(new Date());
			
		if(jogo.getTime1().equals(jogo.getTime2())){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Os times não podem ser iguais."));
			return null;
		}
			
		JogoDAO.salvar(jogo);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Partida cadastrada com Sucesso!"));
		campSelecionado = null;
		jogo = new Jogo();
		return null;
		}
		
		public void salvarEdicao(org.primefaces.event.RowEditEvent<Jogo> event) {
			Jogo jogoEditado = event.getObject();
		    JogoDAO.salvar(jogoEditado);
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Edição realizada com sucesso!"));
		}

		public void cancelarEdicao(org.primefaces.event.RowEditEvent<Jogo> event) {
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Edição cancelada."));
		}
		
		public String deletar() {
			lista.remove(jogoSelecionado);
		    JogoDAO.deletar(jogoSelecionado);
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Partida removida com Sucesso."));
		    return null;
		}
		
		public String filtrar() {
		    jogosFiltrados = JogoDAO.buscarPorTime(nomeTimeFiltro);
		    return "filtrar";
		}

		
		public List<Jogo> getLista() {
			if (lista == null) {
				lista = JogoDAO.listarTodos();
			}
			return lista;
		}

		public void setLista(List<Jogo> lista) {
			this.lista = lista;
		}
		
		
		public Jogo getJogo() {
			return jogo;
		}
	
		public void setJogo(Jogo jg) {
			this.jogo = jg;
		}

		public Integer getIdCampeonato() {
			return idCampeonato;
		}

		public void setIdCampeonato(Integer idCampeonato) {
			this.idCampeonato = idCampeonato;
		}

		public Jogo getJogoSelecionado() {
			return jogoSelecionado;
		}

		public void setJogoSelecionado(Jogo jogoSelecionado) {
			this.jogoSelecionado = jogoSelecionado;
		}
		
		public void filtrarPorTime() {
		    jogosFiltrados = JogoDAO.buscarPorTime(nomeTimeFiltro);
		}

		public String getNomeTimeFiltro() {
		    return nomeTimeFiltro;
		}

		public void setNomeTimeFiltro(String nomeTimeFiltro) {
		    this.nomeTimeFiltro = nomeTimeFiltro;
		}

		public List<Jogo> getJogosFiltrados() {
		    return jogosFiltrados;
		}
	}