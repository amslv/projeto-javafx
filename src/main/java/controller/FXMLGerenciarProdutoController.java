package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import exception.EasyStockException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Produto;
import service.ProdutoService;

public class FXMLGerenciarProdutoController implements Initializable {
	
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private TableView<Produto> tableViewProdutos;
	@FXML
	private TableColumn<Produto, String> colIdProduto;
	@FXML
	private TableColumn<Produto, String> colNomeProduto;
	@FXML
	private TableColumn<Produto, String> colDescricaoProduto;
	@FXML
	private TableColumn<Produto, Integer> colQuantidadeProduto;
	@FXML
	private TableColumn<Produto, Double> colValorCompra;
	@FXML
	private TableColumn<Produto, Double> colValorVenda;
	@FXML
	private Button btnNovoProduto, btnAlterarProduto, btnRemoverProduto;
	
	private ProdutoService service = new ProdutoService();

	@FXML
	public void loadCadastroProdutoScene() {
		AnchorPane a = null;
		try {
			a = (AnchorPane) FXMLLoader.load(getClass().getResource("/CadastroProduto.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        anchorPane.getChildren().setAll(a);
	}
	
	@FXML
	public void loadAlterarProdutoScene() {
		Produto produto = tableViewProdutos.getSelectionModel().getSelectedItem();
		if (produto != null) {
			FXMLEditarProdutoController.getProdutoSelecionado(produto);
			AnchorPane a = null;
			try {
				a = (AnchorPane) FXMLLoader.load(getClass().getResource("/EditarProduto.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
	        anchorPane.getChildren().setAll(a);
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Ocorreu uma falha ao processar a operação");
            alert.setContentText("Por favor, selecione um produto na tabela!");
            alert.show();
		}
	}
	
	@FXML
	public void handleDeleteProduto() {
		Produto produto = tableViewProdutos.getSelectionModel().getSelectedItem();
		try {
			if (produto != null) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	            alert.setTitle("Confirmação");
	            alert.setHeaderText("Confirmação de remoção de produto");
	            alert.setContentText("Tem certeza que deseja remover este produto?");
	            Optional<ButtonType> result = alert.showAndWait();
	            if (result.isPresent() && result.get() == ButtonType.OK) {
	                service.delete(produto);
	            }
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Erro!");
	            alert.setHeaderText("Ocorreu um erro ao executar a operação");
	            alert.setContentText("Por favor, selecione um produto na tabela!");
	            alert.show();
			}
		} catch (EasyStockException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Ocorreu um erro ao remover o produto");
            alert.setContentText(e.getMessage());
            alert.show();
		} finally {
			tableViewProdutos.getItems().clear();
			tableViewProdutos.getItems().addAll(service.getAll());
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colIdProduto.setCellValueFactory(new PropertyValueFactory<Produto, String>("id"));
		colNomeProduto.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
		colDescricaoProduto.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricao"));
		colQuantidadeProduto.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("quantidade"));
		colValorCompra.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valorCompra"));
		colValorVenda.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valorVenda"));
		
		tableViewProdutos.getItems().addAll(service.getAll());
		
	}

}
