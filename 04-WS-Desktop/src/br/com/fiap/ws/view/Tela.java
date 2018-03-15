package br.com.fiap.ws.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.com.fiap.service.NotaService;

public class Tela {

	protected Shell shlCalcularNotaPara;
	private Text txtNac;
	private Text txtAm;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Tela window = new Tela();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCalcularNotaPara.open();
		shlCalcularNotaPara.layout();
		while (!shlCalcularNotaPara.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCalcularNotaPara = new Shell();
		shlCalcularNotaPara.setSize(220, 225);
		shlCalcularNotaPara.setText("Calcular nota para PS");
		
		Label lblNac = new Label(shlCalcularNotaPara, SWT.NONE);
		lblNac.setBounds(29, 25, 34, 15);
		lblNac.setText("NAC");
		
		Label lblAm = new Label(shlCalcularNotaPara, SWT.NONE);
		lblAm.setBounds(29, 54, 34, 15);
		lblAm.setText("AM");
		
		txtNac = new Text(shlCalcularNotaPara, SWT.BORDER);
		txtNac.setBounds(63, 22, 76, 21);
		
		txtAm = new Text(shlCalcularNotaPara, SWT.BORDER);
		txtAm.setText("");
		txtAm.setBounds(63, 51, 76, 21);
			
		Label lblResultado = new Label(shlCalcularNotaPara, SWT.NONE);
		lblResultado.setBounds(75, 126, 55, 15);
		
		Button btnCalc = new Button(shlCalcularNotaPara, SWT.NONE);
		btnCalc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			//clique do botão
				float am = Float.parseFloat(txtAm.getText());	
				float nac = Float.parseFloat(txtNac.getText());
				try {
					NotaService service = new NotaService();
					float ps = service.calcularPs(am, nac);
					lblResultado.setText(String.valueOf(ps));	
				} catch (Exception e1) {
					lblResultado.setText(e1.getMessage());
				}
				
			}
			
		});
		btnCalc.setBounds(63, 83, 75, 25);
		btnCalc.setText("Calc");


	}
}
