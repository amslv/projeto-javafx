package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Produto;
import service.ProdutoService;

public class FXMLCadastroProdutoController implements Initializable {

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private GridPane gridPane;
	@FXML
	private Label lblNomeProduto, lblDescricaoProduto, lblQuantidadeItens, lblValorCompra, lblValorVenda;
	@FXML
	private TextField tfNomeProduto, tfQuantidadeItens, tfValorCompra, tfValorVenda;
	@FXML
	private TextArea taDescricaoProduto;
	@FXML
	private Button btnSalvarProduto, btnCancelar;

	private ProdutoService service = new ProdutoService();

	public void handleSalvarProduto() {
		Produto produto = new Produto(tfNomeProduto.getText(), taDescricaoProduto.getText(), Integer.parseInt(tfQuantidadeItens.getText()),
				Double.parseDouble(tfValorCompra.getText()), Double.parseDouble(tfValorVenda.getText()));
		try {
			service.save(produto);
			loadListagemDeProdutosScene();
		} catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Ocorreu um erro ao cadastrar o produto");
            alert.setContentText(e.getMessage());
            alert.show();
		}
	}
	
	public void loadListagemDeProdutosScene() {
		AnchorPane a = null;
		try {
			a = (AnchorPane) FXMLLoader.load(getClass().getResource("/GerenciarProdutos.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        anchorPane.getChildren().setAll(a);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
