import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class FusenGameMain extends Thread{

	// TODO 自動生成されたメソッド・スタブ

	JFrame frame1;
	BufferedImage backimage , fusenmanimage , karasuimage,darkfusenimage;
	boolean space_flag = false;
	boolean left_flag = false;
	boolean right_flag = false;

	
    FusenGameMain() {
		frame1 = new JFrame("風船ゲーム");
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setBackground(Color.WHITE);
		frame1.setResizable(false);
		frame1.setVisible(true);
		frame1.setSize(600, 400);
		frame1.setLocale(null);

		frame1.addKeyListener(new MyKeyAdapter());

		frame1.createBufferStrategy(2);
		frame1.setIgnoreRepaint(true);

		try {
			backimage = ImageIO.read(getClass().getResource("back.jpg"));
			fusenmanimage = ImageIO.read(getClass().getResource("fusenman.png"));
			karasuimage = ImageIO.read(getClass().getResource("karasu.png"));
			darkfusenimage = ImageIO.read(getClass().getResource("dark_fusenman.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}

		start();
	}
    
    

	public void run() {
		
		Fusenman fusenman = new Fusenman(100 , 200 , fusenmanimage);
		
		int karasu_hikisuu = (int)(Math.random() * 4 + 1 ) * 2;
		Karasu[] karasu = new Karasu[karasu_hikisuu];
		for(int i = 0 ; i < karasu.length ; i++) {
			karasu[i] = new Karasu(karasuimage);
		}
		
		int darkfusen_hikisuu = (int)(Math.random() * 4 + 1 );
		DarkFusen[] darkfusen = new DarkFusen[darkfusen_hikisuu];
		for(int i = 0 ; i < darkfusen.length ; i++) {
			darkfusen[i] = new DarkFusen(darkfusenimage);
		}
		
		int packman_hikisuu = 4;
		Pacman[] pacman = new Pacman[packman_hikisuu];
		for(int i = 0 ; i < pacman.length ; i++) {
			pacman[i] = new Pacman(700,100);
		}
		
		boolean gameover = false;
		
		for(;;) {
			Graphics g = frame1.getGraphics();
			g.drawImage(backimage , 0, 20 , frame1);//背景の描画
			
			//ゲームオーバー判定と敵の描画
			gameover = fusenman.move(space_flag, left_flag , right_flag);
			fusenman.draw(g, frame1);
			
			for(int i = 0 ; i < karasu.length ; i++) {
				karasu[i].move();
				if(gameover == false) {
					gameover = karasu[i].isAtari(fusenman);
				}
				karasu[i].draw(g, frame1);
			}
			
			for(int i = 0 ; i < darkfusen.length ; i++) {
				darkfusen[i].move();
				if(gameover == false) {
					gameover = darkfusen[i].isAtari(fusenman);
				}
				darkfusen[i].draw(g, frame1);
			}
			
			for(int i = 0 ; i < pacman.length ; i++) {
				if(gameover == false) {
					gameover = pacman[i].drawPacman(g, fusenman.x, fusenman.y, frame1);
				}				
			}
			
			

			//ゲームオーバー処理
			if(gameover == true) {
				g.drawString("ゲームオーバー", 300, 200);

				try {
					Thread.sleep(3000);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}

				for(int i = 0 ; i < karasu.length ; i++) {
					karasu[i] = new Karasu(karasuimage);
				}
				for(int i = 0 ; i < darkfusen.length ; i++) {
					darkfusen[i] = new DarkFusen(darkfusenimage);
				}
				
				gameover = false;
			}
			

			try {
				Thread.sleep(30);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	public static void main(String[] args) {
		FusenGameMain main = new FusenGameMain();
		Calendar calendar = Calendar.getInstance();
		long startTime = System.currentTimeMillis();
		int result = 0;
    	for (int i = 0; i < 1000000; i++) {
            result += 1;
        }
    	long endTime = System.currentTimeMillis();
    	System.out.println("経過時間：" + (endTime - startTime) + " ms");
	}

	class MyKeyAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent ev) {
			if(ev.getKeyCode()== KeyEvent.VK_SPACE) {
//				System.out.println("スペースキーが押されました");
				space_flag = true;
			}else if(ev.getKeyCode()== KeyEvent.VK_A) {
//				System.out.println("aキーが押されました");
				left_flag = true;
			}else if(ev.getKeyCode() == KeyEvent.VK_D) {
//				System.out.println("dキーが押されました");
				right_flag = true;
			}
		}

		public void keyReleased(KeyEvent ev) {
			if(ev.getKeyCode() == KeyEvent.VK_SPACE) {
//				System.out.println("スペースキーが離されました");
				space_flag = false;
			}else if(ev.getKeyCode() == KeyEvent.VK_A) {
//				System.out.println("aキーが離されました");
				left_flag = false;
			}else if(ev.getKeyCode() == KeyEvent.VK_D) {
//				System.out.println("dキーが離されました");
				right_flag = false;
			}
		}

	}

}
