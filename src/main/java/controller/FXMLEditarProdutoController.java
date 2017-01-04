package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exception.EasyStockException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Produto;
import service.ProdutoService;

public class FXMLEditarProdutoController implements Initializable {

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
	
	private static Produto produtoSelecionado = new Produto();
	
	private ProdutoService service = new ProdutoService();
	
	public static void getProdutoSelecionado(Produto produto) {
		produtoSelecionado = produto;
	}
	
	
	
	@FXML
	public void handleAtualizarProduto() {
		Produto novoProduto = new Produto(tfNomeProduto.getText(), taDescricaoProduto.getText(),
				Integer.parseInt(tfQuantidadeItens.getText()), Double.parseDouble(tfValorCompra.getText()), Double.parseDouble(tfValorVenda.getText()));
		novoProduto.setId(produtoSelecionado.getId());
		try {
			service.update(novoProduto);
			loadGerenciarProdutosScene();
		} catch (EasyStockException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Ocorreu um erro ao atualizar o produto");
            alert.setContentText(e.getMessage());
            alert.show();
            alert.setOnCloseRequest(handleCloseEventDialog());
		}
		
	}
	
	public void loadGerenciarProdutosScene() {
		AnchorPane a = null;
		try {
			a = (AnchorPane) FXMLLoader.load(getClass().getResource("/GerenciarProdutos.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		anchorPane.getChildren().setAll(a);
	}
	
	private EventHandler<DialogEvent> handleCloseEventDialog() {
		EventHandler<DialogEvent> closeDialogEvent = event-> {
			loadGerenciarProdutosScene();
		};
		return closeDialogEvent;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfNomeProduto.setText(produtoSelecionado.getNome());
		taDescricaoProduto.setText(produtoSelecionado.getDescricao());
		tfQuantidadeItens.setText(String.valueOf(produtoSelecionado.getQuantidade()));
		tfValorCompra.setText(String.valueOf(produtoSelecionado.getValorCompra()));
		tfValorVenda.setText(String.valueOf(produtoSelecionado.getValorVenda()));
	}

}
