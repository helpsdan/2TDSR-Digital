package br.com.fiap.jsf.bean;

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

	//MÉTODO DE INICIALIZAÇÃO
	@PostConstruct
	private void init() {
		veiculo = new Veiculo();
		service = new VeiculoService();
	}
	
	//Método do clique do botão
	public String cadastrar() {
		FacesMessage msg;
		try {
			service.cadastrar(veiculo);
			msg = new FacesMessage("Sucesso!");
		} catch (Exception e) {
			msg = new FacesMessage("Erro!");
			e.printStackTrace();
		}
		// A mensagem para ser exibida na tela.
		FacesContext.getCurrentInstance().addMessage(null, msg);
		//Manter a mensagem após o redirect		
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
