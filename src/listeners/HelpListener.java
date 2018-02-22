package listeners;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class HelpListener implements MenuListener{
	
	public HelpListener(){
		
	}
	
	@Override
	public void menuSelected(MenuEvent ev) {
		
		/*try {
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			s = s.replace('\\', '/');
			Desktop.getDesktop().browse(new URI(s+"/src/help/GeRuDokHelpENG.html"));
			Desktop.getDesktop().browse(new URI(s+"/src/help/GeRuDokHelpSRP.html"));

		} catch (IOException | URISyntaxException e) {
		
			e.printStackTrace();
		}*/
		
		try {
			Runtime.getRuntime().exec("hh.exe src/help/GeRuDokHelpENG.chm");
			//Runtime.getRuntime().exec("hh.exe src/help/GeRuDokHelpSRP.chm");

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		
	}

}
