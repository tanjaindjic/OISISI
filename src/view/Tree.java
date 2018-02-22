package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import controller.command.CommandManager;
import controller.observerArg.GeRuDokument_Arg;
import controller.observerArg.Projekat_Arg;
import controller.observerArg.RadniProstor_Arg;
import controller.observerArg.Stranica_Arg;
import model.GeRuDokument;

@SuppressWarnings("serial")
public class Tree extends JTree implements Observer{
	protected DefaultMutableTreeNode rootNode;
	protected DefaultTreeModel treeModel;

	public Tree(){
	}
	
	public Tree(DefaultTreeModel tm){
		super(tm);
		treeModel=tm;
	
	}

	
	public Object getSelected(){
		Object selectedNode = (Object)this.getLastSelectedPathComponent();
		return selectedNode;
	}

	

	@Override
	public void update(Observable o, Object arg) {
		
		
		if(arg instanceof GeRuDokument_Arg){
			
			
			checkGeRuDok(arg);
			
		}else if(arg instanceof Projekat_Arg){
			
			
			checkProjekat(arg);
			
		}else if(arg instanceof RadniProstor_Arg){
			
			
			checkRadni(arg);
			
		}else if(arg instanceof Stranica_Arg){
			this.revalidate();
			this.repaint();
			//treeModel.reload();
			
			checkStranica(arg);
			
		}else{
			System.out.println("Poslat nepostojeci argument");
		}
		
	
			
		
	}
	
	private void checkGeRuDok(Object arg){
		
		GeRuDokument_Arg g= (GeRuDokument_Arg)arg;	
		CommandManager cmd = CommandManager.getCMD();
		
		switch (g.akcijeGRD) {
		case DODAJ:
			PageView pv = new PageView(this);
			if(this.getSelected() instanceof GeRuDokument){				
				if(WorkArea.getInstance().getTabCount()>0 && WorkArea.getInstance().getTitleAt(0).equals("orengvoiseur823ru"))
					
					WorkArea.getInstance().removeTabAt(0);		
			}
			int a= WorkArea.getInstance().getTabCount();
			if(a>0){
				for(int j=0; j<a; j++){
					if(WorkArea.getInstance().getTitleAt(j).contains(((GeRuDokument)this.getSelected()).getUniqueID().toString())){
						WorkArea.getInstance().removeTabAt(j);	
						j=a;
					}
				}
			}
			String name = ((GeRuDokument)this.getSelected()).getName() + " (" + ((GeRuDokument)this.getSelected()).getUniqueID().toString() +")";
			
			WorkArea.getInstance().add(name, new JScrollPane(pv));
			
			
			int index = WorkArea.getInstance().indexOfTab(name);
			JPanel pnlTab = new JPanel(new GridBagLayout());
			pnlTab.setOpaque(false);
			JLabel lblTitle = new JLabel(name);
			JButton btnClose = new JButton("x");
			btnClose.setBackground(Color.white); 
			btnClose.setOpaque(true); 
			btnClose.setMaximumSize(new Dimension(50,25));
			btnClose.setPreferredSize(new Dimension(50,25));
			btnClose.setMinimumSize(new Dimension(50,25));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weightx = 1;

			pnlTab.add(lblTitle, gbc);

			gbc.gridx++;
			gbc.weightx = 0;
			pnlTab.add(btnClose, gbc);

			WorkArea.getInstance().setTabComponentAt(index, pnlTab);

			btnClose.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					Component selected = WorkArea.getInstance().getSelectedComponent();
			        if (selected != null) {

			        	WorkArea.getInstance().remove(selected);
				}
				}
				
			});
			
			
			
			WorkArea.getInstance().revalidate();
			WorkArea.getInstance().repaint();
			MainWindow.getInstance().tabbedPane.revalidate();
			MainWindow.getInstance().tabbedPane.repaint();
			MainWindow.getInstance().tabbedPane.setVisible(true);
			this.revalidate();
			this.repaint();
			
			
			break;
		case OBRISI:
			
			break;
			
		case DELI:
			
			break;
			
		case PREIMENUJ:
		
		break;
		
		case PROMENI_PATH:
			
			break;

		default:
			break;
		}
		
		
	}
	
	
	
	private void checkRadni(Object arg){
				
	}
	
	
	private void checkProjekat(Object arg){
		
		
		
	}



	private void checkStranica(Object arg){
		
		Stranica_Arg gs= (Stranica_Arg)arg;
		GeRuDokument g = (GeRuDokument) this.getSelectionPath().getParentPath().getLastPathComponent();
		@SuppressWarnings("unused")
		CommandManager cmd = CommandManager.getCMD();
		
		switch (gs.akcijeStranica) {
		case DODAJ:
			PageView pv = new PageView(g);
			if(g instanceof GeRuDokument){				
				if(WorkArea.getInstance().getTabCount()>0 && WorkArea.getInstance().getTitleAt(0).equals("orengvoiseur823ru"))
					WorkArea.getInstance().removeTabAt(0);		
			}
			//uklanjanje istih tabova
			int size= WorkArea.getInstance().getTabCount();
			if(size>0){
				for(int j=0; j<size; j++){
					if(WorkArea.getInstance().getTitleAt(j).contains(g.getUniqueID().toString())){
						WorkArea.getInstance().removeTabAt(j);	
					}
				}
			}
			String name = (g.getName() + " (" + g.getUniqueID().toString() +")");
			
			//dodavanje novog taba sa update-ovanim prikazom stranica i slotova
			WorkArea.getInstance().add(name, new JScrollPane(pv));

			
			int index = WorkArea.getInstance().indexOfTab(name);
			JPanel pnlTab = new JPanel(new GridBagLayout());
			pnlTab.setOpaque(false);
			JLabel lblTitle = new JLabel(name);
			JButton btnClose = new JButton("x");
			btnClose.setBackground(Color.white); 
			btnClose.setOpaque(true); 
			btnClose.setMaximumSize(new Dimension(50,25));
			btnClose.setPreferredSize(new Dimension(50,25));
			btnClose.setMinimumSize(new Dimension(50,25));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weightx = 1;

			pnlTab.add(lblTitle, gbc);

			gbc.gridx++;
			gbc.weightx = 0;
			pnlTab.add(btnClose, gbc);

			WorkArea.getInstance().setTabComponentAt(index, pnlTab);

			btnClose.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					Component selected = WorkArea.getInstance().getSelectedComponent();
			        if (selected != null) {

			        	WorkArea.getInstance().remove(selected);
				}
				}
				
			});
			WorkArea.getInstance().revalidate();
			WorkArea.getInstance().repaint();
			MainWindow.getInstance().tabbedPane.revalidate();
			MainWindow.getInstance().tabbedPane.repaint();
			MainWindow.getInstance().tabbedPane.setVisible(true);
			
			break;
		case OBRISI:
			
			break;
			
		case DELI:
			
			break;
			
		case PREIMENUJ:
		
		break;
		
		case PROMENI_PATH:
			
			break;

		default:
			break;
		}
		
		
	
	}
	
	 private final List<DefaultMutableTreeNode> getSearchNodes(DefaultMutableTreeNode root) {
         List<DefaultMutableTreeNode> searchNodes = new ArrayList<DefaultMutableTreeNode>();

         Enumeration<?> e = root.preorderEnumeration();
         while(e.hasMoreElements()) {
             searchNodes.add((DefaultMutableTreeNode)e.nextElement());
         }
         return searchNodes;
     }



	public final DefaultMutableTreeNode findNode(String searchString) {

        List<DefaultMutableTreeNode> searchNodes = getSearchNodes((DefaultMutableTreeNode)this.getModel().getRoot());
        DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)this.getLastSelectedPathComponent();

        DefaultMutableTreeNode foundNode = null;
        int bookmark = -1;

        if( currentNode != null ) {
            for(int index = 0; index < searchNodes.size(); index++) {
                if( searchNodes.get(index) == currentNode ) {
                    bookmark = index;
                    break;
                }
            }
        }

        for(int index = bookmark + 1; index < searchNodes.size(); index++) {    
            if(searchNodes.get(index).toString().toLowerCase().contains(searchString.toLowerCase())) {
                foundNode = searchNodes.get(index);
                break;
            }
        }

        if( foundNode == null ) {
            for(int index = 0; index <= bookmark; index++) {    
                if(searchNodes.get(index).toString().toLowerCase().contains(searchString.toLowerCase())) {
                    foundNode = searchNodes.get(index);
                    break;
                }
            }
        }
        return foundNode;
    }   
	
	public String getExpansionState(){

		StringBuilder sb = new StringBuilder();

		for ( int i = 0; i < this.getRowCount(); i++ ){

			if ( this.isExpanded(i) ){

				sb.append(i).append(",");

			}

		}

		return sb.toString();

	}
	
	
	public void setExpansionState(String s){

		String[] indexes = s.split(",");

		for ( String st : indexes ){

			int row = Integer.parseInt(st);

			this.expandRow(row);
			this.setSelectionRow(row);

		}

	}
	
	
}
