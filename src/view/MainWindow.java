package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

import listeners.SaveAsListener;
import listenersTree.ScrollPaneListener;
import listenersTree.TreePopupMenuClickListener;
import model.GeRuDokModel;
import model.WorkspaceModel;


@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private static MenuBar menuBar;
	public static WorkArea tabbedPane;
	private static ToolBar toolBar;
	private static StatusBar statusBar;
	private static MainWindow main = null;
	public static JScrollPane scrollPane; 
	private static Tree tree;
	private static WorkspaceModel model;
	private MainWindow(){
		
	}
	
	
	public static void deleteMainWidow(){
		GeRuDokModel.getInstance().setActiveWorkspace(null);
		GeRuDokModel.getInstance().setCurrentUser(null);
		main.dispose();
		main=null;
		model.reload();
	}
	
	@SuppressWarnings("deprecation")
	public static MainWindow getInstance(){
	    if(main == null){
	    	main = new MainWindow();
	    	main.setTitle("GeRuDok");
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
			double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight();
			width=width*0.8; //Procenat ekrana
			height=height*0.75; 
			main.setBounds(100, 100, (int)width, (int)height);
			main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			String myLoc = (System.getProperty("user.dir")+"/src/");;
			ImageIcon img = new ImageIcon(myLoc + "gerugrok.jpg");
			main.setIconImage(img.getImage());
			scrollPane = new JScrollPane();
			main.getContentPane().add(scrollPane, BorderLayout.WEST);
			tree = new Tree();//
			tree.setEditable(false);
			model = new WorkspaceModel();
			tree.setModel(model);
			tree.addMouseListener(new TreePopupMenuClickListener(model, tree));
			
			tree.setPreferredSize(new Dimension(200, 64));
			tree.show(false);
			scrollPane.setViewportView(tree);

			scrollPane.addMouseListener(new ScrollPaneListener(model, tree));
			
			
			
			
			menuBar = MenuBar.getInstance(model,tree);
			menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			main.setJMenuBar(menuBar);
				
			toolBar = ToolBar.getInstance(model, tree);
			toolBar.setPreferredSize(new Dimension((int)width, (int)(height*0.1)));
			
			
			main.getContentPane().add(toolBar, BorderLayout.PAGE_START);
			

			statusBar = StatusBar.getInstance();
			statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
			statusBar.setPreferredSize(new Dimension(main.getWidth(), 25));
			statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));
			main.getContentPane().add(statusBar, BorderLayout.SOUTH);
			
			
			
			
			
			//root = new DefaultMutableTreeNode(GeRuDokModel.getInstance());
			//treeModel = new DefaultTreeModel(root);
			
			tabbedPane = WorkArea.getInstance();
			tabbedPane.setVisible(false);
			main.getContentPane().add(tabbedPane, BorderLayout.CENTER);
			
			main.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			    	if (JOptionPane.showConfirmDialog(null, "Do you want to save your work before exiting?", "WARNING",
					        JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
					    // sve sacuva u JSON
						SaveAsListener n = new SaveAsListener();
						n.actionPerformed(null);
					}
			        System.exit(0);
			    }
			});
			
			main.setVisible(true);
	    }
		return main;
	   }
	public static ToolBar getToolBar() {
		return toolBar;
	}
	public static void setToolBar(ToolBar toolBar) {
		MainWindow.toolBar = toolBar;
	}
	public static WorkArea getTabbedPane() {
		return tabbedPane;
	}
	public static void setTabbedPane(WorkArea tabbedPane) {
		MainWindow.tabbedPane = tabbedPane;
	}
	public static JScrollPane getScrollPane() {
		return scrollPane;
	}
	public static void setScrollPane(JScrollPane scrollPane) {
		MainWindow.scrollPane = scrollPane;
	}
	public WorkspaceModel getModel() {
		return model;
	}
	public void setModel(WorkspaceModel model) {
		this.model = model;
	}


}
