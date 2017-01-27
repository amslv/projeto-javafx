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

public class FXMLCadastroFornecedorController implements Initializable {

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
	
	private Fornecedor fornecedor = new Fornecedor();
	
	private ProdutoService produtoService = new ProdutoService();
	
	private FornecedorService fService = new FornecedorService();
	
	private void loadComboBoxProdutos() {
		comboBoxProduto.getItems().addAll(produtoService.getAll());
	}
	
	public void handleSalvarFornecedor() {
		fornecedor.setNomeFantasia(tfNomeFantasia.getText());
		fornecedor.setCnpj(tfCnpj.getText());
		fornecedor.setEmail(tfEmail.getText());
		String estado = comboBoxEstados.getSelectionModel().getSelectedItem();
		Endereco endereco = new Endereco(tfNomeDaRua.getText(), Integer.parseInt(tfNumero.getText()),
				tfBairro.getText(), tfCidade.getText(), estado);
		fornecedor.setEndereco(endereco);
		try {
			fService.save(fornecedor);
			loadGerenciarFornecedorScene();
		} catch (EasyStockException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Ocorreu um erro ao salvar o fornecedor");
            alert.setContentText(e.getMessage());
            alert.show();
		}
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
	@FXML
	public void handleAdicionarProduto() {
		Produto p = comboBoxProduto.getSelectionModel().getSelectedItem();
		if (p != null) {
			fornecedor.getProdutosFornecidos().add(p);
			tableViewProdutos.getItems().clear();
			tableViewProdutos.getItems().addAll(fornecedor.getProdutosFornecidos());
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Ocorreu um erro ao processar a operação");
            alert.setContentText("Por favor, selecione um produto!");
            alert.show();
		}
	}
	
	private void setDataComboBox() {
		List<String> estados = new ArrayList<>();
		estados.add("Acre");
		estados.add("Alagoas");
		estados.add("Amazonas");
		estados.add("Bahia");
		estados.add("Ceará");
		estados.add("Distrito Federal");
		estados.add("Espírito Santo");
		estados.add("Goiás");
		estados.add("Maranhão");
		estados.add("Mato Grosso");
		estados.add("Mato Grosso do Sul");
		estados.add("Minas Gerais");
		estados.add("Pará");
		estados.add("Paraíba");
		estados.add("Paraná");
		estados.add("Pernambuco");
		estados.add("Piauí");
		estados.add("Rondônia");
		estados.add("Roraima");
		estados.add("Rio de Janeiro");
		estados.add("Rio Grande do Norte");
		estados.add("Rio Grande do Sul");
		estados.add("Santa Catarina");
		estados.add("Sergipe");
		estados.add("São Paulo");
		estados.add("Tocantins");
		
		comboBoxEstados.getItems().addAll(estados);
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colNomeProduto.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
		colDescricaoProduto.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricao"));
		setDataComboBox();
		loadComboBoxProdutos();
	}

}
