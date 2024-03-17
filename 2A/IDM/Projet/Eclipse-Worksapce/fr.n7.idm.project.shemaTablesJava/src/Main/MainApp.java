package Main;
import java.util.ArrayList;
import java.util.List;

import Frame.TableToFrame;
import ShemaTableElements.ColumnA;
import ShemaTableElements.ShemaTableA;
public class MainApp {

	public static List<ShemaTableA> shTables;
	public static void main(String[] args) {
		UserTableModel tabModel = new UserTableModel();
		shTables = tabModel.getTables();
		//ShemaTableA premierShema = shTables.get(1);
		//for (ShemaTableA shemaTable : shTables) {
		new TableToFrame(shTables.get(1), shTables);
		//}
		//new TableToFrame(premierShema);

	}
}
