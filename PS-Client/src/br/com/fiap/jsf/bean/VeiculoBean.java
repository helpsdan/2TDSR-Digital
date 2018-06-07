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
	
	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	private VeiculoService service;
	
	//MÉTODO DE INICIALIZAÇÃO
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
	
	// MÉTODO PARA O CLIQUE DO BOTÃO REMOVER
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
	
	//MÉTODO DO CLIQUE DO BOTAO
	public String cadastrar() {
		FacesMessage msg;
		try {

			if (veiculo.getCodigo() == 0) {
				service.cadastar(veiculo);
				msg = new FacesMessage("Sucesso!");
			}else {
				service.atualizar(veiculo);
				msg = new FacesMessage("Atualizado!");
			}	
		} catch (Exception e) {
			msg = new FacesMessage("Erro");
			e.printStackTrace();
		}
		return "veiculo?faces-redirect=true";
		
	}
	
	
}
