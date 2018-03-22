package projectpackage;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class P4 extends JFrame{
	private int width, height, w, m, maxVal, bitsToSkip, bitNum;
	private int [] pixs, barray;
	private DrawingPanel dp;
	
	P4() {
		try {
			FileInputStream fis = new FileInputStream("fool.pbm");
			BufferedInputStream bis = new BufferedInputStream(fis);
			byte [] magicNum = new byte [2];
			bis.read(magicNum);
			
			char chByte = (char) bis.read();
			while(Character.isWhitespace(chByte))
				chByte = (char) bis.read();
			
			byte fb = (byte) chByte;
			int count = 1;
			byte [] w = new byte[3];
			w[0] = fb;
			
			byte aByte = (byte) bis.read();
			while(!Character.isWhitespace(aByte)) {
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
			while(!Character.isWhitespace(aByte)) {
				h[count++] = aByte;
				aByte = (byte) bis.read();
			}
			
			i = 0;
			String strHeight = "";
			while(i < count)
				strHeight = strHeight + (h[i++]-48);
			
			System.out.println(strWidth);
			System.out.print(strHeight);
			
			height = Integer.parseInt(strHeight);
			width = Integer.parseInt(strWidth);
			
			pixs = new int [((width*8)+1)*height];
			barray = new int [((width*8)+1)*height];

			bitsToSkip = (8-(width % 8));
			
			for (int j = 0; j < pixs.length; j++) {
				pixs[j] = (bis.read());
			}
			int c = 0;
			for(int j = 0; j < width*height; j++) {
				for (int k = 0; k <= 7; k++) {
					barray[c] = getBit(j, k);
					c++;
				}
			}
			
			dp = new DrawingPanel();
			add(dp);
		} 
		catch (FileNotFoundException e) {} 
		catch (IOException e) { }
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class DrawingPanel extends JPanel {
		@Override
		public void paintComponent(Graphics g) { 
			super.paintComponent(g);
			bitNum = 0;
			for (int row = 0; row < height; row++) { 
				if (bitNum != 0) 
					bitNum += bitsToSkip;
				for (int col = 0; col < width; col++) {
					g.setColor(getColor(barray[bitNum]));
					g.fillRect(col, row, 1, 1);
					bitNum++;
				}
			}
			System.out.println("Done with the painting");
		}
	}
	
	public Color getColor (int num) {
		return (num == 1) ? new Color(0, 0, 0): new Color(255, 255, 255);
	}
	
	int getBit(int allBits, int k) {
		int index = allBits;
		int bitNum = k;
		int temp = ((pixs[index] >>> (7-bitNum)) & 1);
		return temp;
	}
	
	public static void main (String[] args) {
		P4 w = new P4();
	}	
}
