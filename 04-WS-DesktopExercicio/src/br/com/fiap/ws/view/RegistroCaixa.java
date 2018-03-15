package br.com.fiap.ws.view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;

public class RegistroCaixa {

	protected Shell shlRegistroDeCaixa;
	private Text txtCdProduto;
	private Text txtProduto;
	private Text text;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RegistroCaixa window = new RegistroCaixa();
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
		shlRegistroDeCaixa.open();
		shlRegistroDeCaixa.layout();
		while (!shlRegistroDeCaixa.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlRegistroDeCaixa = new Shell();
		shlRegistroDeCaixa.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		shlRegistroDeCaixa.setSize(444, 322);
		shlRegistroDeCaixa.setText("Registro de Caixa");
		
		Label lblCdigoDoProduto = new Label(shlRegistroDeCaixa, SWT.NONE);
		lblCdigoDoProduto.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblCdigoDoProduto.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblCdigoDoProduto.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblCdigoDoProduto.setBounds(31, 10, 138, 20);
		lblCdigoDoProduto.setText("C\u00F3digo do Produto");
		
		Label lblProduto = new Label(shlRegistroDeCaixa, SWT.NONE);
		lblProduto.setText("Produto");
		lblProduto.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblProduto.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblProduto.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblProduto.setBounds(106, 37, 63, 20);
		
		Label lblQuantidade = new Label(shlRegistroDeCaixa, SWT.NONE);
		lblQuantidade.setText("Quantidade");
		lblQuantidade.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblQuantidade.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblQuantidade.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblQuantidade.setBounds(81, 63, 88, 20);
		
		txtCdProduto = new Text(shlRegistroDeCaixa, SWT.BORDER);
		txtCdProduto.setBounds(167, 10, 88, 21);
		
		txtProduto = new Text(shlRegistroDeCaixa, SWT.BORDER);
		txtProduto.setBounds(167, 36, 235, 21);
		
		text = new Text(shlRegistroDeCaixa, SWT.BORDER);
		text.setBounds(167, 64, 88, 21);
		
		Button btnSearch = new Button(shlRegistroDeCaixa, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				int codigo = Integer.parseInt(txtCdProduto.getText());
				
				
				
				
				
				
				
			}
		});
		btnSearch.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnSearch.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		btnSearch.setBounds(327, 8, 75, 25);
		btnSearch.setText("Procurar");
		
		Button btnCorfirm = new Button(shlRegistroDeCaixa, SWT.NONE);
		btnCorfirm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnCorfirm.setText("Corfirmar");
		btnCorfirm.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnCorfirm.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		btnCorfirm.setBounds(43, 114, 75, 25);
		
		Button btnEnd = new Button(shlRegistroDeCaixa, SWT.NONE);
		btnEnd.setText("Fim");
		btnEnd.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnEnd.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		btnEnd.setBounds(124, 114, 75, 25);
		
		table = new Table(shlRegistroDeCaixa, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(43, 143, 347, 109);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(89);
		tblclmnNewColumn.setText("C\u00F3digo");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(209);
		tblclmnNewColumn_1.setText("Nome do Produto");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(42);
		tblclmnNewColumn_2.setText("Qtd");
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		
		TableItem tableItem_3 = new TableItem(table, 0);

	}
}
