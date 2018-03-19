package p6package;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class P6 extends JFrame {
	private int [] pixs;
	private int w,h, mv, maxVal, height, width;
	private DrawingPan dp;
	
	P6() {

		try{
			FileInputStream fis = 
				new FileInputStream("underwater_bmx.ppm");
			BufferedInputStream bis = new BufferedInputStream(fis);
		   byte [] magicNum = new byte[2];
			bis.read(magicNum);

			char chByte = (char) bis.read();
			while(Character.isWhitespace(chByte))
				chByte = (char) bis.read();

			byte fb = (byte) chByte;
			int count = 1;
			byte [] w = new byte[4];
			w[0] = fb; 

			byte aByte = (byte) bis.read();
			while(!Character.isWhitespace(aByte)){
				w[count++] = aByte;
				aByte = (byte) bis.read();
			}

			int i = 0;
			String strWidth = "";
			while(i < count)
				strWidth = strWidth + (w[i++]-48);
			
			count = 0;
			byte [] h = new byte[4];
			aByte = (byte) bis.read();
			while(!Character.isWhitespace(aByte)){
				h[count++] = aByte;
				aByte = (byte) bis.read();
			}
			
			i = 0;
			String strHeight = "";
			while(i < count)
				strHeight = strHeight + (h[i++]-48);
			
			height = Integer.parseInt(strHeight);
			width = Integer.parseInt(strWidth);

			count = 0;
			byte [] mv = new byte[3];
			aByte = (byte) bis.read();
			while(!Character.isWhitespace(aByte)){
				mv[count++] = aByte;
				aByte = (byte) bis.read();
			}
			
			while(Character.isWhitespace(aByte))
				aByte = (byte) bis.read();
			
			i = 0;
			String strmaxVal = "";
			while(i < count)
				strmaxVal = strmaxVal + (mv[i++]-48);

			pixs = new int [width*height*3];
			for (int j = 1; j < height*width*3; j++) {
				pixs[j] = (bis.read());
			}

			dp = new DrawingPan();
			add(dp);
		  }
		  catch(FileNotFoundException e1){}
		  catch(IOException e2){}
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	class DrawingPan extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int x = 0;
			int counter = 0;
			for(int row = 0; row < height; row++) {
				for(int col = 0; col < width; col++) {
					Color c = new Color(pixs[x],pixs[x+1],pixs[x+2]);
					g.setColor(c);
					x+=3;
					g.fillRect(col,row, 1, 1);
					counter++;
				}
			}
		}
	}
	public static void main(String [] args) {
		P6 w = new P6();
	}
}
