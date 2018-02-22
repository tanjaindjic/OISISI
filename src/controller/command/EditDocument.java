package controller.command;

import model.Document;
import model.GeRuDokument;

public class EditDocument extends AbstractCommand {

	private String name;

	private GeRuDokument document;

	public boolean doCommand() {

		if (!name.trim().isEmpty()) {
			String temp = document.getName();
			document.setName(name);
			return true;
		}
		return false;

	}

	public boolean undoCommand() {

		if (!name.equals(document.getName())) {

			String temp = document.getName();
			document.setName(name);
			name=temp;
			return true;

		}
		return false;

	}

	public EditDocument(String name, GeRuDokument document) {
		this.name = name;
		this.document = document;
		this.selectedObject = document;
	}

}
