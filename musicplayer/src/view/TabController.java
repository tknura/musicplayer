package view;

import javafx.scene.control.TableView;

public abstract class TabController<T> implements Controller {
	protected MainSceneController msc;
	
    public void injectMainController(MainSceneController mainSceneController) {
    	this.msc = mainSceneController;
    	init();
    }
    
	public abstract void setDoubleClickBehaviour(TableView<T> tableView);
}
