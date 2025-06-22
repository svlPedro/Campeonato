package beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import entidades.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioBean {

	private Usuario user;
	private List<Usuario> lista;
	
	public UsuarioBean() {
		user = new Usuario();
	}

	public String cadastrar() {
		UsuarioDAO.salvar(user);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário cadastrado com Sucesso!"));
		return "opcoes";
	}
	
	public String login() {
		Usuario usuarioEncontrado = UsuarioDAO.login(user.getLogin());

		if (usuarioEncontrado == null) {
			FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado", null));
			return null;
		}

		if (!usuarioEncontrado.getSenha().equals(user.getSenha())) {
			FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha incorreta", null));
			return null;
		}
		
		user = usuarioEncontrado;
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Login efetuado com sucesso!", null));
		return "opcoes";
	}
	
	public List<Usuario> getLista() {
		if (lista == null) {
			lista = UsuarioDAO.listarTodos();
		}
		return lista;
	}
	
	public String sair() {
		user = new Usuario();
		return "login";
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}	
}