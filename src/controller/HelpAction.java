package controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.AbstractAction;

public class HelpAction extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent ev) {
		try {
			Runtime.getRuntime().exec("hh.exe src/help/GeRuDokHelpENG.chm");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
