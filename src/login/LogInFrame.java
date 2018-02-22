package login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import controller.HelpAction;
import listeners.HelpListener;
import listenersTree.LoginEnterListener;
import listenersTree.LoginSubmitListener;



public class LogInFrame extends JFrame{
	private static JPanel contentPane;	
	private static JMenuBar mBar;
	private static JLabel labName;
	private static JLabel labPass;
	public static JTextField textName;
	public static JPasswordField passField;
	private static BufferedImage logo;
	private static JPanel leftPan;
	private static JPanel rightPan;
	private static JButton signIn;
	private static LogInFrame logInFrame = null;
	private LogInFrame(){
		
	}
	
	public String getPassword(){
		return  passField.getText().toString();
	}
	
	
	public String getUsername(){	
		return  textName.getText().toString();
	}
	
	public static LogInFrame getInstance(){
		if(logInFrame==null){
			
			logInFrame = new LogInFrame();
			logInFrame.setTitle("GeRuGrok");

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
			double height =  screenSize.getHeight();
			width=width*0.5; //Procenat ekrana
			height=height*0.5;
			logInFrame.mBar = new JMenuBar();
			JMenu mi0 = new JMenu("Help");
			mi0.addMenuListener(new HelpListener());
			mBar.add(Box.createHorizontalGlue());
			mBar.add(mi0);
			mi0.getInputMap().put(KeyStroke.getKeyStroke("F1"),"login");
			mi0.getActionMap().put("login",new HelpAction());
			
			
				
			logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			logInFrame.setBounds(100, 100, (int)width, (int)height);
			String myLoc= (System.getProperty("user.dir")+"/src/gerugrok.jpg");
			ImageIcon icon = new ImageIcon(myLoc);
			logInFrame.setIconImage(icon.getImage());
			
			try {                
		         logo = ImageIO.read(new File(myLoc));
		    } catch (IOException ex) {
		            
		    }		
			JLabel picLabel = new JLabel(new ImageIcon(logo));
			contentPane = new JPanel();
			
			
			
			
			
			
			leftPan = new JPanel();
			GridBagLayout gbl_leftPan = new GridBagLayout();
			gbl_leftPan.columnWidths = new int[]{20, 50, 200, 20};
			gbl_leftPan.rowHeights = new int[]{2, 30, 30, 30, 30, 20};
			gbl_leftPan.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_leftPan.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			leftPan.setLayout(gbl_leftPan);
			
			labName = new JLabel("User name:");
			GridBagConstraints gbc_labName = new GridBagConstraints();
			gbc_labName.insets = new Insets(0, 0, 5, 5);
			gbc_labName.gridx = 1;
			gbc_labName.gridy = 1;
			leftPan.add(labName, gbc_labName);
			
			labPass = new JLabel("Password:");
			GridBagConstraints gbc_labPass = new GridBagConstraints();
			gbc_labPass.insets = new Insets(0, 0, 5, 5);
			gbc_labPass.gridx = 1;
			gbc_labPass.gridy = 2;
			leftPan.add(labPass, gbc_labPass);
			
			textName = new JTextField();
			textName.setName("username");
			textName.setPreferredSize(new Dimension(150,25));
			GridBagConstraints gbc_textName = new GridBagConstraints();
			gbc_textName.insets = new Insets(0, 0, 5, 5);
			gbc_textName.gridx = 2;
			gbc_textName.gridy = 1;
			leftPan.add(textName, gbc_textName);
			
			
			passField = new JPasswordField();
			passField.setName("pass");
			passField.setPreferredSize(new Dimension(150,25));
			GridBagConstraints gbc_passField = new GridBagConstraints();
			gbc_passField.insets = new Insets(0, 0, 5, 5);
			gbc_passField.gridx = 2;
			gbc_passField.gridy = 2;
			leftPan.add(passField, gbc_passField);
			passField.addKeyListener(new LoginEnterListener()); // IMAMO ENTER ! 
			
			JLabel d = new JLabel("username: dizajner pass: diz");
			GridBagConstraints dg = new GridBagConstraints();
			dg.insets = new Insets(0, 0, 5, 5);
			dg.gridx = 2;
			dg.gridy = 4;
			leftPan.add(d, dg);
			
			
					
			signIn = new JButton("Sign in");
			signIn.setPreferredSize(new Dimension(90,25));
			
			signIn.addActionListener( new LoginSubmitListener());
			/*signIn.addActionListener(new ActionListener()
			{
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e)
				{		
					boolean gotIt = false;
					for(int i=0; i<users.getUsers().size(); i++){
						if(textName.getText().toString().equals(users.getUsers().get(i).getUsername())){
							if(passField.getText().equals(users.getUsers().get(i).getPassword())){
								if(users.getUsers().get(i).getTypeOfUser()==UserType.DESIGNER){
									MainWindow main = MainWindow.getInstance();
									if(users.getUsers().get(i).isWantsDialog()){
										ChooseDefaultPath cdp = ChooseDefaultPath.getInstance();
										cdp.show();
										gotIt=true;
									}
								}
								else if(users.getUsers().get(i).getTypeOfUser()==UserType.ADMIN){
									AdminFrame admFrame = AdminFrame.getInstance();
									logInFrame.dispose();
									gotIt=true;
								}
							}
						}
					}
					if(!gotIt)
						JOptionPane.showMessageDialog(null,"Your user name or password is incorrect.");					
				}
			});	*/
			GridBagConstraints gbc_signIn= new GridBagConstraints();
			gbc_signIn.insets = new Insets(0, 0, 5, 5);
			gbc_signIn.gridx = 2;
			gbc_signIn.gridy = 5;
			leftPan.add(signIn, gbc_signIn);
			
		
			
			
		//	leftPan.add(signIn);
			rightPan = new JPanel();
			rightPan.add(picLabel);
			logInFrame.setContentPane(contentPane);
			
			logInFrame.setJMenuBar(mBar);
			contentPane.add(rightPan, BorderLayout.WEST);
			contentPane.add(leftPan, BorderLayout.EAST);
			
			logInFrame.pack();
			logInFrame.setVisible(true);
		}
	      return logInFrame;
	   }

}
