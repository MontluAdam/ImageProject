package packageofhomework1;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClassOfHomework1 extends JFrame {
	
	private DrawingPanel2 dp;
	int width = 0, height = 0, maxVal = 0;
	int []pixels;
	
	ClassOfHomework1() {
		try {
			Scanner input = new Scanner(new File("circle.ascii.pbm"));
			System.out.println(input.next());
			System.out.println(input.nextLine());
			System.out.println(input.nextLine());
			width = Integer.parseInt(input.next());
			height = Integer.parseInt(input.next());
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
					int intColor;
					if(pixels[row*width+col] == 1) {
						intColor = 0;
					}
					else  {
						intColor = 255;
					}
					g.setColor(new Color(intColor, intColor, intColor));
					g.fillRect(row, col, 1, 1);
				}
			}
		}
	}
	public static void main(String [] args) {
		ClassOfHomework1 w = new ClassOfHomework1();
	}
}

