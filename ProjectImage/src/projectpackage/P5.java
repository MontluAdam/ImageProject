import javax.swing.*;
import java.io.*;
import java.awt.*;

public class P5 extends JFrame{
	private int [] pixs;
	private int width,height,maxVal;
	private DrawingPanel dp;
	P5(){
	  try{
		//----------------------FILE PART-------------------\\
		  
		FileInputStream fis = 
			new FileInputStream("baboon.pgm");
		byte [] magicNum = new byte[2];
		fis.read(magicNum);
		
		//----------------------FILE PART-------------------\\

		
		//----------------------GET FIRST BYTE AND ASSIGN TO WIDTH-------------------\\
		
		char chByte = (char) fis.read();
		while(Character.isWhitespace(chByte))
			chByte = (char) fis.read();

		byte fb = (byte) chByte;
		int count = 1;
		byte [] w = new byte[3];
		w[0] = fb; 
		
		//----------------------GET FIRST BYTE AND ASSIGN TO WIDTH-------------------\\

		//----------------------APPEND THE REST OF THE NUMBERS AND GET WIDTH-------------------\\
		
		byte aByte = (byte) fis.read();
		while(!Character.isWhitespace(aByte)){
			w[count++] = aByte;
			aByte = (byte) fis.read();
		}


		int i = 0;
		String strWidth = new String(w);		
		width = Integer.parseInt(strWidth);
		
		aByte = (byte) fis.read();
		while(Character.isWhitespace(aByte))
			aByte = (byte) fis.read();
		
		//----------------------APPEND THE REST OF THE NUMBERS AND GET WIDTH-------------------\\

		//----------------------HEIGHT-------------------\\	
		
		count = 1;
		byte [] h = new byte[3];
		h[0] = aByte; 

		aByte = (byte) fis.read();
		while(!Character.isWhitespace(aByte)){
			h[count++] = aByte;
			aByte = (byte) fis.read();
		}
		height = Integer.parseInt(new String(h));

		aByte = (byte) fis.read();
		while(Character.isWhitespace(aByte))
			aByte = (byte) fis.read();
		
		//----------------------HEIGHT-------------------\\		

		//----------------------MAX VALUE-------------------\\		
		
		count = 1;
		byte [] mv = new byte[3];
		mv[0] = aByte;
		aByte = (byte) fis.read();
		while(!Character.isWhitespace(aByte)){
			mv[count++] = aByte;
			aByte = (byte) fis.read();
		}

		aByte = (byte) fis.read();
		while(Character.isWhitespace(aByte))
			aByte = (byte) fis.read();
		
		//----------------------MAX VALUE-------------------\\		

		
		//----------------------ASSIGN BYTE ARRAY-------------------\\		

		pixs = new int[width*height];
		pixs[0] = aByte;
		for(int k = 1; k < width*height; k++)
			pixs[k] =  fis.read();
		
		//----------------------ASSIGN BYTE ARRAY-------------------\\		

		dp = new DrawingPanel();
		add(dp);
		
	  }
	  catch(FileNotFoundException e1){}
	  catch(IOException e2){}

		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}   
	class DrawingPanel extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			for(int row = 0; row < height; row++) {
				for(int col = 0; col < width; col++) {
				  try{
					Color c = new Color(
						pixs[row*width+col],
						pixs[row*width+col],
						pixs[row*width+col]);
						g.setColor(c);
						g.fillRect(col,row,1,1);
					}catch(IllegalArgumentException e){
					}
				}
			}
		}
	}
	public static void main(String [] args){
	   P5 window = new P5();
	}
}
