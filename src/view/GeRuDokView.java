package view;

import controller.GeRuDokController;
import login.LogInFrame;
import model.GeRuDokModel;

public class GeRuDokView {
	
	private static LogInFrame loginFrame  = null;
	public static LogInFrame getLoginFrame() {
		return loginFrame;
	}
	public static void setLoginFrame(LogInFrame loginFrame) {
		GeRuDokView.loginFrame = loginFrame;
	}
	private static GeRuDokView gerudok = null;
	
	public GeRuDokView(){
		
	}
	//instanciranje gerudokviewa
	public static GeRuDokView getInstance(){
		if(gerudok == null){
			gerudok = new GeRuDokView();
			loginFrame = LogInFrame.getInstance();
		}
		return gerudok;
	}
	
}
