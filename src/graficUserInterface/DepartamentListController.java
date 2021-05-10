package graficUserInterface;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Departament;
import model.services.DepartamentService;

public class DepartamentListController implements Initializable {

	private DepartamentService service;

	@FXML
	private TableView<Departament> tableViewDepartament;
	
	@FXML 
	private TableColumn<Departament, Integer> TableColumnId;
	
	@FXML 
	private TableColumn<Departament, String> TableColumnName;
	
	@FXML
	private Button btnew;
	
	private ObservableList<Departament> obsList;
	
	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		/*createDialogForm("/graficUserInterface/About.fxml", parentStage);*/
		createDialogForm("/graficUserInterface/DepartmentForm.fxml", parentStage);
	}
	
	public void setDepartamentService (DepartamentService service) {
		this.service = service;
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}

	private void initializeNodes() {
		TableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		TableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartament.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void  updateTableView() {
		 if (service == null) {
			 throw new IllegalStateException("Service was null");
		 }
		 
		 List<Departament> list = service.findall();
		 obsList = FXCollections.observableArrayList(list);
		 tableViewDepartament.setItems(obsList);
	}
	
	private void createDialogForm( String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader  = new FXMLLoader (getClass().getResource(absoluteName));
			Pane pane = loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Department data");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}
		catch (IOException e){
			Alerts.showAlert("IOEXception", "Error loading View", e.getMessage(), AlertType.ERROR);
			
		}
	}
}
