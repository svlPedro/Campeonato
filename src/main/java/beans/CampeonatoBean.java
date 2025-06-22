package beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.CampeonatoDAO;
import entidades.Campeonato;

@ManagedBean
public class CampeonatoBean {

	private List<Campeonato> lista;
	private Campeonato campeonato;
	
	public CampeonatoBean() {
		campeonato = new Campeonato();
	}

	public String salvar() {
		CampeonatoDAO.salvar(campeonato);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Campeonato cadastrado com Sucesso!"));
		return null;
	}
	
	public void listarCampeonatosMensagem() {
	    List<Campeonato> campeonatos = CampeonatoDAO.listarTodos();

	    StringBuilder mensagem = new StringBuilder("<br/>");
	    for (Campeonato c : campeonatos) {
	        mensagem.append("Selecione ")
	                .append(c.getId())
	                .append(" - para inserir o jogo no ")
	                .append(c.getNome())
	                .append("<br/>");
	    }
	     
	    FacesContext.getCurrentInstance().addMessage(null,
             new FacesMessage(FacesMessage.SEVERITY_INFO, "Lista de Campeonatos:", mensagem.toString()));
	 }
	
	public List<Campeonato> getLista() {
		if (lista == null) {
			lista = CampeonatoDAO.listarTodos();
		}
		return lista;
	}

	public void setLista(List<Campeonato> lista) {
		this.lista = lista;
	}


	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}
	

}