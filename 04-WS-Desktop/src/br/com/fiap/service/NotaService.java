package br.com.fiap.service;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import br.com.fiap.bo.NotaBOStub;
import br.com.fiap.bo.NotaBOStub.CalcularPs;
import br.com.fiap.bo.NotaBOStub.CalcularPsResponse;

public class NotaService {

	private NotaBOStub stub;
	
	public NotaService() throws AxisFault {
		
		stub = new NotaBOStub();
	}
	
	
	public float calcularPs(float am, float nac) throws RemoteException{
		CalcularPs valores = new CalcularPs();
		valores.setAm(am);
		valores.setNac(nac);
		stub.calcularPs(valores);
		CalcularPsResponse resp = stub.calcularPs(valores);
		
		return resp.get_return();
		
		
	}
	
}
