package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class FXMLHomePageController implements Initializable {
	
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private MenuBar menuBar;
	@FXML
	private Menu menuGerenciar, menuGrafico;
	@FXML
	private MenuItem menuItemProdutos, menuItemFornecedores, menuItemGrafico;
	
	@FXML
	public void loadHomePageScene() {
		AnchorPane a = null;
		try {
			a = (AnchorPane) FXMLLoader.load(getClass().getResource("/HomePage.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        anchorPane.getChildren().setAll(a);
	}
	@FXML
	public void loadGerenciarProdutosScene() {
		AnchorPane a = null;
		try {
			a = (AnchorPane) FXMLLoader.load(getClass().getResource("/GerenciarProdutos.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        anchorPane.getChildren().setAll(a);
	}
	@FXML
	public void loadGerenciarFornecedoresScene() {
		AnchorPane a = null;
		try {
			a = (AnchorPane) FXMLLoader.load(getClass().getResource("/GerenciarFornecedores.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        anchorPane.getChildren().setAll(a);
	}
	@FXML
	public void loadGraficoProdutoScene() {
		AnchorPane a = null;
		try {
			a = (AnchorPane) FXMLLoader.load(getClass().getResource("/GraficoProdutos.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        anchorPane.getChildren().setAll(a);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
