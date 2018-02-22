package model;

import javax.swing.tree.DefaultTreeModel;

@SuppressWarnings("serial")
public class WorkspaceModel extends DefaultTreeModel {

	public WorkspaceModel() {
		super(new Workspace("Workspace"));
	}
	public WorkspaceModel(Workspace ws) {
		super(ws);
	}
}

