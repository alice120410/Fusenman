import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Karasu {
	
	int karasu_x;
	int karasu_y;
	BufferedImage image;
	int speed;
	
	Karasu(BufferedImage image){
		karasu_x = 600;
		karasu_y = (int)(Math.random() * 360);
		this.image = image;
		speed = 10;
	}
	
	void move() {
		karasu_x -= speed;
		if(karasu_x < 0 ) karasu_x = 600;//画面外に出たときの処理
	}
	
	//int fusen_x , int fusen_y等の引数でもいいが、Fusenオブジェクトを渡してそのフィールド変数を参照するほうが望ましい
	boolean isAtari(Fusenman fusenman) {
		if (fusenman.x < karasu_x + 40 && fusenman.x + 40 > karasu_x && fusenman.y < karasu_y + 40 && fusenman.y + 40 > karasu_y) 
			return true;
		
		return false;
	}
	
	void draw(Graphics g , JFrame frame1) {
	//描画処理
		g.drawImage(image , karasu_x , karasu_y , frame1);
	}

}
