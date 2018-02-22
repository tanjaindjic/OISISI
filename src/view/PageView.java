package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.GeRuDokument;

public class PageView  extends JPanel {
	
	public PageView(Tree tree){
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setName(((GeRuDokument)tree.getSelected()).getName());
		setAlignmentX(Component.CENTER_ALIGNMENT);
		GeRuDokument grd = (GeRuDokument)tree.getSelected();
		
		for(int i=0; i<grd.getPages().size();i++){
			JPanel page = new JPanel();		
			page.setBorder(BorderFactory.createLineBorder(Color.black));
			page.setAlignmentX(Component.CENTER_ALIGNMENT);
			page.setName(grd.getPages().get(i).getName());
			page.setMaximumSize(new Dimension((int)grd.getPages().get(i).getWidth(), (int)grd.getPages().get(i).getHeight()));
			page.setSize(new Dimension(new Dimension((int)grd.getPages().get(i).getWidth(), (int)grd.getPages().get(i).getHeight())));
			page.setPreferredSize(new Dimension(new Dimension((int)grd.getPages().get(i).getWidth(), (int)grd.getPages().get(i).getHeight())));
			page.setBackground(Color.white);
		
			for(int i1=0; i1<grd.getPages().get(i).getSlots().size();i1++){
				JPanel page1 = new JPanel();
				page1.setAlignmentX(Component.CENTER_ALIGNMENT);
				page1.setName(grd.getPages().get(i1).getName());
				page1.setMaximumSize(new Dimension((int)grd.getPages().get(i).getSlots().get(i1).getWidth(), (int)grd.getPages().get(i).getSlots().get(i1).getHeight()));
				page1.setSize(new Dimension(new Dimension((int)grd.getPages().get(i).getSlots().get(i1).getWidth(), (int)grd.getPages().get(i).getSlots().get(i1).getHeight())));
				page1.setPreferredSize(new Dimension(new Dimension((int)grd.getPages().get(i).getSlots().get(i1).getWidth(), (int)grd.getPages().get(i).getSlots().get(i1).getHeight())));
				page1.setBackground(Color.lightGray);
				page1.setBorder(BorderFactory.createLineBorder(Color.black));
				page.add(page1);
				page.add(Box.createVerticalStrut(10));
			}
			
			add(page);
			add(Box.createVerticalStrut(20));
			
		}	
	}
	
public PageView(GeRuDokument t){
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setAlignmentX(Component.CENTER_ALIGNMENT);
		setName(t.getName());
		GeRuDokument grd = t;
		for(int i=0; i<grd.getPages().size();i++){
			JPanel page = new JPanel();			
			
			page.setName(grd.getPages().get(i).getName());
			page.setBorder(BorderFactory.createLineBorder(Color.black));
			page.setMaximumSize(new Dimension((int)grd.getPages().get(i).getWidth(), (int)grd.getPages().get(i).getHeight()));
			page.setSize(new Dimension(new Dimension((int)grd.getPages().get(i).getWidth(), (int)grd.getPages().get(i).getHeight())));
			page.setPreferredSize(new Dimension(new Dimension((int)grd.getPages().get(i).getWidth(), (int)grd.getPages().get(i).getHeight())));
			page.setBackground(Color.white);
			page.setLayout(new BoxLayout(page, BoxLayout.Y_AXIS));
			
			for(int i1=0; i1<grd.getPages().get(i).getSlots().size();i1++){
				JPanel page1 = new JPanel();
				page1.setAlignmentX(Component.CENTER_ALIGNMENT);
				page1.setName(grd.getPages().get(i).getName());
				page1.setMaximumSize(new Dimension((int)grd.getPages().get(i).getSlots().get(i1).getWidth(), (int)grd.getPages().get(i).getSlots().get(i1).getHeight()));
				page1.setSize(new Dimension(new Dimension((int)grd.getPages().get(i).getSlots().get(i1).getWidth(), (int)grd.getPages().get(i).getSlots().get(i1).getHeight())));
				page1.setPreferredSize(new Dimension(new Dimension((int)grd.getPages().get(i).getSlots().get(i1).getWidth(), (int)grd.getPages().get(i).getSlots().get(i1).getHeight())));
				page1.setBackground(Color.lightGray);
				page1.setBorder(BorderFactory.createLineBorder(Color.black));
				page.add(page1);
				page.add(Box.createVerticalStrut(10));
			}
			page.setAlignmentX(Component.CENTER_ALIGNMENT);
			add(page);
			add(Box.createVerticalStrut(20));
			
		}	
	}
	

}
