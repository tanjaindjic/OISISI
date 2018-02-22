package login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.User;
import model.Users;

public class AdminFrame extends JFrame{
	private static JPanel contentPane;
	private static JMenuBar mBar;
	private static BufferedImage logo;
	private JLabel lab1;
	private JLabel lab2;
	private JLabel lab3;
	private static JButton removeBTN;
	private static JButton addBTN;
	private static JButton resetPassBTN;
	private static JPanel leftPan;
	private static JPanel rightPan;
	private JTextField textName;
	private static JList list;
	private static Users users;
	private static DefaultListModel<String> model;
	
	public static DefaultListModel<String> getModel() {
		return model;
	}
	public static void setModel(DefaultListModel<String> model) {
		AdminFrame.model = model;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	
	private static AdminFrame adminFrame = null;
	private AdminFrame(){
		
	}
	public static AdminFrame getInstance(){
		if(adminFrame==null){

			UsersJson js = new UsersJson();
			users = js.loadUsersFromJSon();
			
			AdminFrame af = new AdminFrame();
			af.setTitle("GeRuGrok");
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight();
			width=width*0.5; //Procenat ekrana
			height=height*0.5;
			mBar = new JMenuBar();
			JMenu mi0 = new JMenu("Help");
			mBar.add(Box.createGlue());
			JMenu mi1 = new JMenu("LogOut");
			mi1.addActionListener(new AdminLogOutListener());
			mBar.add(mi1);
			mBar.add(mi0);
				
			af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			af.setBounds(100, 100, (int)width, (int)height);
			String myLoc= (System.getProperty("user.dir")+"/src/gerugrok.jpg");
			ImageIcon icon = new ImageIcon(myLoc);
			af.setIconImage(icon.getImage());
			
			af.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			        UsersJson uj = new UsersJson();
			        uj.saveUsersToJSon(users);
			        System.exit(0);
			    }
			});
			
			try {                
		         logo = ImageIO.read(new File(myLoc));
		    } catch (IOException ex) {
		           
		    }		
			JLabel picLabel = new JLabel(new ImageIcon(logo));
			
			rightPan = new JPanel();
			GridBagLayout gbl_rightPan = new GridBagLayout();
			gbl_rightPan.columnWidths = new int[]{300};
			gbl_rightPan.rowHeights = new int[]{10, 30, 30, 30, 30, 20};
			gbl_rightPan.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_rightPan.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			rightPan.setLayout(gbl_rightPan);
			
					
			addBTN = new JButton("Add designer");
			addBTN.setPreferredSize(new Dimension(110,25));
			addBTN.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{	
					AddDesigner addDesigner = AddDesigner.getInstance();
				}
			});	
			GridBagConstraints gbc_addBTN= new GridBagConstraints();
			gbc_addBTN.insets = new Insets(0, 0, 5, 5);
			gbc_addBTN.gridx = 0;
			gbc_addBTN.gridy = 4;
			rightPan.add(addBTN, gbc_addBTN);
			
			list = new JList();
			list.setLayoutOrientation(JList.VERTICAL);
			model = new DefaultListModel<>();
			 for(int i=0; i< users.getUsers().size(); i++){
				 model.addElement(users.getUsers().get(i).getUsername());
			 }
			 list.setModel(model);
			
			JScrollPane listScroller = new JScrollPane(list);
			listScroller.setPreferredSize(new Dimension(200, 250));
			GridBagConstraints gbc_list = new GridBagConstraints();
			gbc_list.insets = new Insets(5, 5, 5, 5);
			gbc_list.gridx =0;
			gbc_list.gridy =0;
			rightPan.add(listScroller, gbc_list);
			
			removeBTN = new JButton("Remove designer");
			removeBTN.setPreferredSize(new Dimension(140,25));
			removeBTN.addActionListener(new ActionListener()
			{
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e)
				{	//TODO			
					if(list.getSelectedIndex()==-1){
						JOptionPane.showMessageDialog(null,"Please select user name from the list.");
						return;
					}
					for(int i=0; i<users.getUsers().size(); i++){
						if(users.getUsers().get(i).getUsername().equals(list.getSelectedValue())){
							users.getUsers().remove(i);
							model.remove(i);
							return;
						}
					}
				}
			});	
			GridBagConstraints gbc_removeBTN= new GridBagConstraints();
			gbc_removeBTN.insets = new Insets(0, 0, 5, 5);
			gbc_removeBTN.gridx = 0;
			gbc_removeBTN.gridy = 2;
			rightPan.add(removeBTN, gbc_removeBTN);
			
			resetPassBTN = new JButton("Reset password");
			resetPassBTN.setPreferredSize(new Dimension(140,25));
			resetPassBTN.addActionListener(new ActionListener()
			{
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e)
				{	
					if(list.getSelectedIndex()==-1){
						JOptionPane.showMessageDialog(null,"Please select user name from the list.");
						return;
					}
					for(int i=0; i<users.getUsers().size(); i++){
						if(users.getUsers().get(i).getUsername().equals(list.getSelectedValue())){
							users.getUsers().get(i).setPassword("grok");
							JOptionPane.showMessageDialog(null,"Password is set to 'grok'.");
							return;
						}
					}
				}
			});	
			GridBagConstraints gbc_resetPassBTN= new GridBagConstraints();
			gbc_resetPassBTN.insets = new Insets(0, 0, 5, 5);
			gbc_resetPassBTN.gridx = 0;
			gbc_resetPassBTN.gridy = 1;
			rightPan.add(resetPassBTN, gbc_resetPassBTN);
			
			leftPan = new JPanel();
			leftPan.add(picLabel); //GO PIG
			
			contentPane = new JPanel();
			af.setContentPane(contentPane);
			
			af.setJMenuBar(mBar);
			contentPane.add(leftPan, BorderLayout.EAST);
			contentPane.add(rightPan, BorderLayout.WEST);
			af.pack();
			af.setVisible(true);
			adminFrame = af;
		}
		return adminFrame;
	}
}
