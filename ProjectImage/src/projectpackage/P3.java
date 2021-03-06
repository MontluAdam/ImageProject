package p3march9;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class P3 extends JFrame {
	
	private DrawingPanel2 dp;
	int width = 0, height = 0, maxVal = 0;
	int []pixels;
	
	P3() {
		try {
			Scanner input = new Scanner(new File("blackbuck.ascii.ppm"));
			System.out.println(input.next());
			System.out.println(input.nextLine());
			System.out.println(input.nextLine());
			width = Integer.parseInt(input.next());
			height = Integer.parseInt(input.next());
			maxVal = Integer.parseInt(input.next());
			pixels = new int [width*height*3];

			for (int i = 0; i < width*height*3; i++)
				pixels[i] = Integer.parseInt(input.next());

			dp = new DrawingPanel2();
			add(dp);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(width);
		System.out.println(height);
		System.out.println(maxVal);
		
		setSize(650,650);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	class DrawingPanel2 extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int x = 0;
			int counter = 0;
			for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
					g.setColor(new Color(pixels[(x)], pixels[(x)+1], pixels[(x)+2]));
					x+=3;
					g.fillRect(col,row, 1, 1);
					counter++;
				}
			}
		}
	}
	public static void main(String [] args) {
		P3 w = new P3();
	}
}
