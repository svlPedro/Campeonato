package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.CampeonatoDAO;
import entidades.Campeonato;

@ManagedBean
public class CampeonatoBean {

	private Campeonato campeonato;
	
	public CampeonatoBean() {
		campeonato = new Campeonato();
	}

	public String salvar() {
		CampeonatoDAO.salvar(campeonato);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Campeonato cadastrado com Sucesso!"));
		return null;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}	
}