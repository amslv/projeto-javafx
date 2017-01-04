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
import model.Fornecedor;
import service.FornecedorService;

public class FXMLGerenciarFornecedorController implements Initializable {

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private TableView<Fornecedor> tableViewFornecedores;
	@FXML
	private TableColumn<Fornecedor, String> colIdFornecedor;
	@FXML
	private TableColumn<Fornecedor, String> colNomeFantasia;
	@FXML
	private TableColumn<Fornecedor, String> colCnpjFornecedor;
	@FXML
	private TableColumn<Fornecedor, String> colEmailFornecedor;
	@FXML
	private Button btnNovoFornecedor, btnDetalhesFornecedor, btnAlterarFornecedor, btnRemoverFornecedor;
	
	private FornecedorService service = new FornecedorService();
	
	@FXML
	public void loadNovoFornecedorScene() {
		AnchorPane a = null;
		try {
			a = (AnchorPane) FXMLLoader.load(getClass().getResource("/CadastroFornecedor.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        anchorPane.getChildren().setAll(a);
	}
	
	@FXML
	public void loadAlterarFornecedorScene() {
		
		Fornecedor f = tableViewFornecedores.getSelectionModel().getSelectedItem();
		
		FXMLEditarFornecedorController.getFornecedorSelecionado(f);
		if (f != null) {
			AnchorPane a = null;
			try {
				a = (AnchorPane) FXMLLoader.load(getClass().getResource("/EditarFornecedor.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
	        anchorPane.getChildren().setAll(a);
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Ocorreu um erro ao tentar executar a operação");
            alert.setContentText("Por favor, selecione um item na tabela!");
            alert.show();
		}
	}
	
	@FXML
	public void handleRemoverFornecedor() {
		Fornecedor fornecedor = tableViewFornecedores.getSelectionModel().getSelectedItem();
		try {
			if (fornecedor != null) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	            alert.setTitle("Confirmação");
	            alert.setHeaderText("Confirmação de remoção de fornecedor");
	            alert.setContentText("Tem certeza que deseja remover este fornecedor?");
	            Optional<ButtonType> result = alert.showAndWait();
	            if (result.isPresent() && result.get() == ButtonType.OK) {
	                service.delete(fornecedor);
	            }
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Erro!");
	            alert.setHeaderText("Ocorreu um erro ao executar a operação");
	            alert.setContentText("Por favor, selecione um fornecedor na tabela!");
	            alert.show();
			}
		} catch (EasyStockException e) {
	         Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Erro!");
	            alert.setHeaderText("Ocorreu um erro ao remover o fornecedor");
	            alert.setContentText(e.getMessage());
	            alert.show();
		} finally {
			tableViewFornecedores.getItems().clear();
			tableViewFornecedores.getItems().addAll(service.getAll());
		}
	}
	
	public void loadDetalhesFornecedorScene() {
		Fornecedor fornecedor = tableViewFornecedores.getSelectionModel().getSelectedItem();
		FXMLDetalhesFornecedorController.getFornecedorSelecionado(fornecedor);
		if (fornecedor != null) {
			AnchorPane a = null;
			try {
				a = (AnchorPane) FXMLLoader.load(getClass().getResource("/DetalhesFornecedor.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
	        anchorPane.getChildren().setAll(a);
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Ocorreu um erro ao tentar executar a operação");
            alert.setContentText("Por favor, selecione um item na tabela!");
            alert.show();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colIdFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("id"));
		colNomeFantasia.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("nomeFantasia"));
		colCnpjFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("cnpj"));
		colEmailFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("email"));
		
		tableViewFornecedores.getItems().addAll(service.getAll());
		
	}

}
