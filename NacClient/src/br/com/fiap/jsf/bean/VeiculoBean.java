package br.com.fiap.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.fiap.ws.service.VeiculoService;
import br.com.fiap.ws.to.Veiculo;

@ManagedBean
public class VeiculoBean {

	private Veiculo veiculo;
	
	private VeiculoService service;

	//M�TODO DE INICIALIZA��O
	@PostConstruct
	private void init() {
		veiculo = new Veiculo();
		service = new VeiculoService();
	}
	
	public List<Veiculo> listar(){
		try {
			return service.listar();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//M�todo para o clique do bot�o remover
	public String remover(int codigo) {
		FacesMessage msg;
		try {
			service.remover(codigo);
			msg = new FacesMessage("Removido");
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "lista-veiculo?faces-redirect=true";
	}
	
	//M�todo do clique do bot�o
	public String cadastrar() {
		FacesMessage msg;
		try {
			if (veiculo.getCodigo()==0) {
				service.cadastrar(veiculo);
				msg = new FacesMessage("Sucesso!");
			} else {
				service.atualizar(veiculo);
				msg = new FacesMessage("Atualizado!");
			}
		} catch (Exception e) {
			msg = new FacesMessage("Erro!");
			e.printStackTrace();
		}
		// A mensagem para ser exibida na tela.
		FacesContext.getCurrentInstance().addMessage(null, msg);
		//Manter a mensagem ap�s o redirect		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		// Redirect para nao cadastar outros veiculos no refresh da pagina
		return "veiculo?faces-redirect=true";
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	
	
}
