package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Fornecedor;
import model.Produto;

public class FXMLDetalhesFornecedorController implements Initializable {

	@FXML
	private AnchorPane anchorPane, anchorPaneInfoBasic, anchorPaneEndereco;
	@FXML
	private SplitPane splitPane;
	@FXML
	private Label lblNf, lblCn, lblEm, lblNdR, lblN, lblBr, lblC, lblE, lblDetalhesFornecedor;
	@FXML
	private Label labelNome, labelCnpj, labelEmail, labelNomeDaRua, labelNumero, labelBairro, labelCidade, labelEstado;
	@FXML
	private TableView<Produto> tableViewProdutosFornecidos;
	@FXML
	private TableColumn<Produto, String> colId, colNome, colDescricao;
	@FXML
	private Button btnVoltar;
	
	private static Fornecedor fornecedorSelecionado;
	
	public static void getFornecedorSelecionado(Fornecedor f) {
		fornecedorSelecionado = f;
	}
	
	public void handleRetornarGerenciarFornecedor() {
		AnchorPane a = null;
		try {
			a = (AnchorPane) FXMLLoader.load(getClass().getResource("/GerenciarFornecedores.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        anchorPane.getChildren().setAll(a);
	}
	
	private void loadPreviousData() {
		labelNome.setText(fornecedorSelecionado.getNomeFantasia());
		labelCnpj.setText(fornecedorSelecionado.getCnpj());
		labelEmail.setText(fornecedorSelecionado.getEmail());
		labelNomeDaRua.setText(fornecedorSelecionado.getEndereco().getRua());
		labelNumero.setText(fornecedorSelecionado.getEndereco().getRua());
		labelNomeDaRua.setText(String.valueOf(fornecedorSelecionado.getEndereco().getNumero()));
		labelBairro.setText(fornecedorSelecionado.getEndereco().getBairro());
		labelCidade.setText(fornecedorSelecionado.getEndereco().getCidade());
		labelEstado.setText(fornecedorSelecionado.getEndereco().getUf());
		
		tableViewProdutosFornecidos.getItems().addAll(fornecedorSelecionado.getProdutosFornecidos());
		
		colId.setCellValueFactory(new PropertyValueFactory<Produto, String>("id"));
		colNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
		colDescricao.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricao"));
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadPreviousData();
	}

}
