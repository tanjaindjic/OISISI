package login;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.User;
import model.Users;

public class AddDesigner extends JFrame {
	private JMenuBar mBar;
	private JPanel contentPane;
	private JLabel labName;
	private JTextField textName;
	private JButton addBTN;
	
	private static AddDesigner addDesigner = new AddDesigner();
	private AddDesigner(){
		setTitle("GeRuGrok");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.2; //Procenat ekrana
		height=height*0.3;
		mBar = new JMenuBar();
		JMenu mi0 = new JMenu("Help");
		mBar.add(Box.createGlue());	
		mBar.add(mi0);
			
		setBounds(200, 200, (int)width, (int)height);
		String myLoc= (System.getProperty("user.dir")+"/src/gerugrok.jpg");
		ImageIcon icon = new ImageIcon(myLoc);
		setIconImage(icon.getImage());
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		GridBagLayout gbl_pan = new GridBagLayout();
		gbl_pan.columnWidths = new int[]{100, 100};
		gbl_pan.rowHeights = new int[]{10, 30, 30};
		gbl_pan.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pan.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_pan);
		
		labName = new JLabel("User name:");
		GridBagConstraints gbc_labName = new GridBagConstraints();
		gbc_labName.insets = new Insets(0, 0, 5, 5);
		gbc_labName.gridx = 0;
		gbc_labName.gridy = 1;
		contentPane.add(labName, gbc_labName);
		
		textName = new JTextField();
		textName.setName("username");
		textName.setPreferredSize(new Dimension(150,25));
		GridBagConstraints gbc_textName = new GridBagConstraints();
		gbc_textName.insets = new Insets(0, 0, 5, 5);
		gbc_textName.gridx = 1;
		gbc_textName.gridy = 1;
		contentPane.add(textName, gbc_textName);
		
		addBTN = new JButton("Add");
		addBTN.setPreferredSize(new Dimension(110,25));
		addBTN.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	if(textName.getText().trim().isEmpty()){
					JOptionPane.showMessageDialog(null,"User name can not be empty.");
					return;
				}
				Users u = AdminFrame.getInstance().getUsers();
				for(int i=0; i<u.getUsers().size(); i++){
					if(u.getUsers().get(i).getUsername().equals(textName.getText())){
						JOptionPane.showMessageDialog(null,"User name is not unique. Please choose another.");
						return;
					}						
				}
				AdminFrame.getInstance().getUsers().getUsers().add(new User(textName.getText()));
				AdminFrame.getInstance().getModel().addElement(textName.getText());
				dispose();
			}
		});	
		GridBagConstraints gbc_addBTN= new GridBagConstraints();
		gbc_addBTN.insets = new Insets(0, 0, 5, 5);
		gbc_addBTN.gridx = 1;
		gbc_addBTN.gridy = 2;
		contentPane.add(addBTN, gbc_addBTN);
			
		setJMenuBar(mBar);
		pack();
		setVisible(true);
		
	}
	public static AddDesigner getInstance(){
	      return addDesigner;
	}
}
