package projectpackage;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.*;   

public class ProjectImage extends JFrame {
	// Menu Items
	private JMenuBar menuBar;
	private JMenu menu;
	
	// Declarations
	private int [] pixs;
	private int [] barray;
	private int w,h, mv, maxVal, height, width;
	//private DrawingPan dp;
	
	
	ProjectImage() {
		// Create menu objects
		JMenuBar menubar = new JMenuBar(); 
		JMenu file = new JMenu("File");
		JFileChooser fileChooser = new JFileChooser();
	
		// File Chooser
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

	    // Open File
	    JMenuItem menuOpen = new JMenuItem("Open");
	    menuOpen.setMnemonic(KeyEvent.VK_O);
	    menuOpen.setToolTipText("Open file");
	    menuOpen.addActionListener((ActionEvent event) -> {
	    	// Getting file opener ready
	    	int result = fileChooser.showOpenDialog(menuOpen);
	    	if (result == JFileChooser.APPROVE_OPTION) {
	    		File selectedFile = fileChooser.getSelectedFile();

		
	    		// Checks the file
		    	FileInputStream fis;
				try {
		    		// Reading the file's text part
					fis = new FileInputStream(selectedFile);
					 byte [] magicNum = new byte[2];
			    		fis.read(magicNum);
			    		
			    		char chByte = (char) fis.read();
			    		while(Character.isWhitespace(chByte))
			    			chByte = (char) fis.read();

			    		byte fb = (byte) chByte;
			    		//chByte has the first byte of width
			    		int count = 1;
			    		byte [] w = new byte[3];
			    		w[0] = fb; 

			    		byte aByte = (byte) fis.read();
			    		while(!Character.isWhitespace(aByte)){
			    			w[count++] = aByte;
			    			aByte = (byte) fis.read();
			    		}

			    		System.out.println("count: " + chByte);
			    		int i = 0;
			    		String strWidth = "";
			    		while(i < count)
			    			strWidth = strWidth + (w[i++]-48);
			    		
			    		byte [] h = new byte[3];
			    		count = 0;
			    		aByte = (byte) fis.read();
			    		while(!Character.isWhitespace(aByte)){
			    			h[count++] = aByte;
			    			aByte = (byte) fis.read();
			    		}
			    		
			    		i = 0;
			    		String strHeight = "";
			    		while(i < count)
			    			strHeight = strHeight + (h[i++]-48);
			    		System.out.println(strHeight);
			    		
			    		height = Integer.parseInt(strHeight);
			    		width = Integer.parseInt(strWidth);

			    	switch (new String(magicNum)) {
			    	
			    	
			    	case "P1":
			    		break;
			    		
			    		
			    	case "P2":
			    		break;
			    		
			    		
			    	case "P3":
			    		break;
			    		
			    		
			    	case "P4":
			    		break;
			    		
			    		
			    	case "P5":
			    		count = 0;
			    		byte [] mv = new byte[3];
			    		aByte = (byte) fis.read();
			    		while(!Character.isWhitespace(aByte)){
			    			mv[count++] = aByte;
			    			aByte = (byte) fis.read();
			    		}
			    		
			    		i = 0;
			    		String strmaxVal = "";
			    		while(i < count)
			    			strmaxVal = strmaxVal + (mv[i++]-48);
			    		System.out.println(strmaxVal);

			    		pixs = new int [width*height];
			    		pixs[0] = aByte;
			    		for (int j = 1; j < height*width; j++) {
			    			pixs[j] = (fis.read());
			    		}
			    		break;
			    		
			    		
			    	case "P6":
			    		break;
			    	default:
			    	    JOptionPane.showMessageDialog(menuOpen, "Only PGM,PBM and PPM files are allowed.", "Error", JOptionPane.ERROR_MESSAGE);
			    		break;
			    	}
				}  
				/*
				dp = new DrawingPan();
				add(dp);
				*/
				catch(FileNotFoundException e1){
					System.out.println(e1);
				}
				catch(IOException e2){}
	    	}
	    });
	    
	    // Exit Application
	    JMenuItem menuExit = new JMenuItem("Exit");
	    menuExit.setMnemonic(KeyEvent.VK_E);
	    menuExit.setToolTipText("Exit application");
	    menuExit.addActionListener((ActionEvent event) -> {
	        System.exit(0);
	    });
	
	    
	    // File
	    file.add(menuOpen);
	    file.add(menuExit);
		file.setMnemonic(KeyEvent.VK_F);
	
		// Menu bar
	    menubar.add(file);
	
	    // Window
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    setJMenuBar(menubar);
	    setTitle("OzanView");
		setSize(dim.width,dim.height);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

	
	public static void main (String [] args) {
		ProjectImage w = new ProjectImage();
	}
}
