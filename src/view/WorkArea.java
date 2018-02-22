package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.text.AbstractDocument.Content;

public class WorkArea extends JTabbedPane {
	private static WorkArea workArea = null;
	private WorkArea(){
		
	}
	public static WorkArea getInstance(){
		if(workArea == null){
			workArea = new WorkArea();			
			JTabbedPane tab = new JTabbedPane();
			JPanel pan = new JPanel();
			pan.setName("orengvoiseur823ru");
			workArea.addTab("orengvoiseur823ru", pan);
		}
	      return workArea;
	}
	

}
