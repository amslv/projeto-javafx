package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import exception.EasyStockException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Endereco;
import model.Fornecedor;
import model.Produto;
import service.FornecedorService;
import service.ProdutoService;

public class FXMLEditarFornecedorController implements Initializable {

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private GridPane gridPane;
	@FXML
	private Label lblNomeFantasia, lblCnpj, lblEmail, lblNomeDaRua, lblNumero, lblBairro, lblCidade, lblEstado, lblProdutosFornecidos;
	@FXML
	private TextField tfNomeFantasia, tfCnpj, tfEmail, tfNomeDaRua, tfNumero, tfBairro, tfCidade;
	@FXML
	private Button btnCancelar, btnSalvarFornecedor, btnAdicionarProdutos;
	@FXML
	private TableView<Produto> tableViewProdutos;
	@FXML
	private TableColumn<Produto, String> colNomeProduto;
	@FXML
	private TableColumn<Produto, String> colDescricaoProduto;
	@FXML
	private ComboBox<String> comboBoxEstados;
	@FXML
	private ComboBox<Produto> comboBoxProduto;
	
	private static Fornecedor fornecedorSelecionado = new Fornecedor();
	
	private ProdutoService produtoService = new ProdutoService();
	
	private FornecedorService fService = new FornecedorService();
	
	private void setDataComboBox() {
		List<String> estados = new ArrayList<>();
		estados.add("Acre");
		estados.add("Alagoas");
		estados.add("Amazonas");
		estados.add("Bahia");
		estados.add("Cear�");
		estados.add("Distrito Federal");
		estados.add("Esp�rito Santo");
		estados.add("Goi�s");
		estados.add("Maranh�o");
		estados.add("Mato Grosso");
		estados.add("Mato Grosso do Sul");
		estados.add("Minas Gerais");
		estados.add("Par�");
		estados.add("Para�ba");
		estados.add("Paran�");
		estados.add("Pernambuco");
		estados.add("Piau�");
		estados.add("Rond�nia");
		estados.add("Roraima");
		estados.add("Rio de Janeiro");
		estados.add("Rio Grande do Norte");
		estados.add("Rio Grande do Sul");
		estados.add("Santa Catarina");
		estados.add("Sergipe");
		estados.add("S�o Paulo");
		estados.add("Tocantins");
		
		comboBoxEstados.getItems().addAll(estados);
		
	}
	
	private void loadComboBoxProdutos() {
		comboBoxProduto.getItems().addAll(produtoService.getAll());
	}
	
	public static void getFornecedorSelecionado(Fornecedor fornecedor) {
		fornecedorSelecionado = fornecedor;
	}
	
	private void loadGerenciarFornecedorScene() {
		AnchorPane a = null;
		try {
			a = (AnchorPane) FXMLLoader.load(getClass().getResource("/GerenciarFornecedores.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        anchorPane.getChildren().setAll(a);
	}
	
	@FXML
	public void handleCancelar() {
		loadGerenciarFornecedorScene();
	}
	
	public void handleAtualizarFornecedor() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(fornecedorSelecionado.getId());
		fornecedor.setNomeFantasia(tfNomeFantasia.getText());
		fornecedor.setCnpj(tfCnpj.getText());
		fornecedor.setEmail(tfEmail.getText());
		String estado = comboBoxEstados.getSelectionModel().getSelectedItem();
		Endereco endereco = new Endereco(tfNomeDaRua.getText(), Integer.parseInt(tfNumero.getText()),
				tfBairro.getText(), tfCidade.getText(), estado);
		fornecedor.setEndereco(endereco);
		fornecedor.setProdutosFornecidos(fornecedorSelecionado.getProdutosFornecidos());
		try {
			fService.update(fornecedor);
			loadGerenciarFornecedorScene();
		} catch (EasyStockException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Ocorreu um erro ao atualizar o fornecedor");
            alert.setContentText(e.getMessage());
            alert.show();
		}
	}
	
	@FXML
	public void handleAdicionarProduto() {
		Produto p = comboBoxProduto.getSelectionModel().getSelectedItem();
		if (p != null) {
			fornecedorSelecionado.getProdutosFornecidos().add(p);
			tableViewProdutos.getItems().clear();
			tableViewProdutos.getItems().addAll(fornecedorSelecionado.getProdutosFornecidos());
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Ocorreu um erro ao processar a opera�ao");
            alert.setContentText("Por favor, selecione um produto!");
            alert.show();
		}
	}
	
	private void loadPreviousData() {
		tfNomeFantasia.setText(fornecedorSelecionado.getNomeFantasia());
		tfCnpj.setText(fornecedorSelecionado.getCnpj());
		tfEmail.setText(fornecedorSelecionado.getEmail());
		tfNomeDaRua.setText(fornecedorSelecionado.getEndereco().getRua());
		tfNumero.setText(String.valueOf(fornecedorSelecionado.getEndereco().getNumero()));
		tfBairro.setText(fornecedorSelecionado.getEndereco().getBairro());
		tfCidade.setText(fornecedorSelecionado.getEndereco().getCidade());
		comboBoxEstados.getSelectionModel().select(fornecedorSelecionado.getEndereco().getUf());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colNomeProduto.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
		colDescricaoProduto.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricao"));
		loadPreviousData();
		setDataComboBox();
		loadComboBoxProdutos();
		tableViewProdutos.getItems().addAll(fornecedorSelecionado.getProdutosFornecidos());
	}

}
