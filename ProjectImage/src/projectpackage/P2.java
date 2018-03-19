package feb9package;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageProcess2 extends JFrame {
	
	private DrawingPanel2 dp;
	int width = 0, height = 0, maxVal = 0;
	int []pixels;
	
	ImageProcess2() {
		try {
			Scanner input = new Scanner(new File("circle.aasscii.pbm"));
			System.out.println(input.next());
			width = Integer.parseInt(input.next());
			height = Integer.parseInt(input.next());
			maxVal = Integer.parseInt(input.next());
			pixels = new int [width*height];
			
			for (int i = 0; i < width*height; i++)
				pixels[i] = Integer.parseInt(input.next());

			dp = new DrawingPanel2();
			add(dp);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setSize(650,650);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	class DrawingPanel2 extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
					int intColor = pixels[row*width+col];
					g.setColor(new Color(intColor, intColor, intColor));
					g.fillRect(row, col, 1, 1);
				}
			}
		}
	}
	public static void main(String [] args) {
		ImageProcess w = new ImageProcess();
	}
}
