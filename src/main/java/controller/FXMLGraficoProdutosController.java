package controller;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.layout.AnchorPane;
import util.GenerateBarChartData;

public class FXMLGraficoProdutosController implements Initializable {

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private PieChart chart;
	
	public ObservableList<Data> getChartData() {
		ObservableList<Data> produtos = FXCollections.observableArrayList();
		
		Map<String, Integer> produtosFornecidos = GenerateBarChartData.generateData();
		
		Iterator<Entry<String, Integer>> it = produtosFornecidos.entrySet().iterator();
		
		while(it.hasNext()) {
			Map.Entry<String, Integer> par = it.next();
			produtos.addAll(new PieChart.Data(par.getKey(), par.getValue()));
		}
		return produtos;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		chart.setData(getChartData());
		chart.setLegendSide(Side.BOTTOM);
		chart.setAnimated(true);
		chart.setLabelsVisible(true);
	}

}
