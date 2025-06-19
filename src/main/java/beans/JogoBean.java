	package beans;
	
	import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.CampeonatoDAO;
import dao.JogoDAO;
import entidades.Campeonato;
import entidades.Jogo;
	
	@ManagedBean
	public class JogoBean {
	
		private Jogo jogo;
		private Integer idCampeonato;
		
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
			return null;
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
	}