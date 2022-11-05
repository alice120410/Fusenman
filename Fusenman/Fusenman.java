import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class Fusenman {
	
	int x;
	int y;
	BufferedImage image;

	
	Fusenman(int x , int y , BufferedImage image){
		this.x = x;
		this.y = y;
		this.image = image;
	}
	
	boolean move(boolean space_flag , boolean left_flag , boolean right_flag) {
		if(y >= 400) {
			//y = 400 - 48;
			return true;
		}else if(y < 0 - 28) {
			//y = 0 + 20;
			return true;
		}

		if(!space_flag) {
			y = y + 5;
		}else {
			y = y - 5;
		}
		
		if(left_flag) {
			x = x - 5;
		}
		if(right_flag) {
			x = x + 5;
		}
		return false;
	}
	
	void draw(Graphics g , JFrame frame1) {
		g.drawImage(image, x, y, frame1);
	}

}
