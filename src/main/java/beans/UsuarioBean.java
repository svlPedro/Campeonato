package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import entidades.Usuario;

@ManagedBean
public class UsuarioBean {

	private Usuario user;
	
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
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Login efetuado com sucesso!", null));
		return "opcoes";
	}
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}	
}