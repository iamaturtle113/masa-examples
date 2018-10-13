package applications;
import static practice.IOMethods.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/*180426(Thu)
 * アクションリスナーの実装をして、スピードを変えられるようにした。
 * でもプレイ、ストップは失敗した。たぶんスリープの時間をムゲンにすれば止められる。
 * java.awt.eventのインポート、内部クラスのスコープ、boolean、スタティック変数の使用
 * Simulation01.x などで手間取った。
 * type not resolvedはインポートエラー
 * あとはスライダーを使ったり、ボタンを増やしたり、テキストフィールドから入力を受け取ったりしたい。
 * 
 * 180428(Sat)
 * パネルにボタンを追加して、それをフレームにボーダーレイアウトで組み込んだ。
 * うまくいった。HFJ　jpP392を参考にした。
 * 
 *180503(Thu)
 *上下にうごくメソッドを追加したい。
 *ネームの衝突少し解消。変数のインポート（Sim01.toggle)衝突したクラスの削除
 * 
 * 181010(Wed)
 * ToggleListnerはSimulation01からインポートされているというか、
 * 勝手に使われているんか？
 * import 宣言コメントアウトしても、大丈夫だった。問題となっているのはパッケージ内のクラスのスコープのとこだと思う。
 * 
 * 2018-10-13-Sat
 *workspace/masa/newMasa　から移植した。
 *Simulation01への依存をとったら、下のバウンドの境界線がずれた。
 *と思ったけど、前からそうだったみたい。
 *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
 */



public class MotionBall01 {
	
	static int x=0;//横に進む距離（ピクセル）
	static int y=0;//縦
	static String c;
	static int ovalWidth=200;
	static int ovalHeight=200;
	static int toggle=10;
	int frameHeight = 900;
	int frameWidth = 1200;
	
	
	public static void main(String[] args) {

		MotionBall01 gui = new MotionBall01(); //MotionBall01とは、このプログラム兼クラスの名前
		//クラスのオブジェクトのインスタンス化、Similation2型オブジェクトでguiと命名
		gui.go();
		System.out.println("MotionBall01!!Gui application's running!");
	
	}

	// go method, this is instance method of gui object
		public void go() {
			JFrame frame = new JFrame();
			JPanel panel =new JPanel();

			//JTextArea textArea=new JTextArea(3,10);
			JButton buttonSU =new JButton("Speed Up");
			JButton buttonSD =new JButton("Speed Down");
			buttonSU.addActionListener(new ToggleListenerSU());
			buttonSD.addActionListener(new ToggleListenerSD());
			panel.add(buttonSU);
			panel.add(buttonSD);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			///////////////ORDERING IS IMPORTANT/////////////////

			//inner of go()	
			drawPanel DP=new drawPanel();
			frame.getContentPane().add(BorderLayout.CENTER, DP);// DP is a drawPanel's obj
			frame.getContentPane().add(BorderLayout.SOUTH,panel);
			frame.setSize(frameWidth,frameHeight);
			
			frame.setVisible(true);
			//start drawPanel DP
				

			
			//---------------------NEW REPAINT PART -------------------------------//
			//In go() method
			int LeftOrRight=0;//1なら右へ
			int UpOrDown=0;//１なら下へ
			//boolean play=true;
			//if (toggle / 2 == 1) play=false;
			
			
			while(true) {//ここで判定してもForループのなかはしばらく変わらない
				//いまframeWidth=1200
				if (x== frameWidth - ovalWidth) LeftOrRight=1;// frameWidth - ovalWidth
				if (LeftOrRight==0)
					x++;
				
				if (LeftOrRight==1)
					x--;
				if (x==0) LeftOrRight=0;
				
				//上下のコード
				int margin = 30;
				if (y==frameHeight - ovalHeight - margin ) UpOrDown=1;// frameHeight - ovalHeight
				//height900のとき680で調度良かった
				//真下についた時の条件
				if (UpOrDown==0)
					y++;
				
				if (UpOrDown==1)
					y--;
				if (y==0) UpOrDown=0;
				
				//肝心のリペイントメソッド
				DP.repaint();
				try {
					//if (toggle < 10) 
					
					//1秒間あたり6コマにしたい
					// 1000/6=166
						Thread.sleep(toggle);//166);
					//if (toggle >= 10) Thread.sleep(1000*120); 
					//このやり方だとfpsの変更になりよくない
					//座標の差異の値を変更する
				}catch(Exception ex) {}
			
			
//--------------------上下に動くメソッドの始まり--------------------------------------//
					
						
						DP.repaint();
						try {
							//if (toggle < 10) 
							
							//1秒間あたり6コマにしたい
							// 1000/6=166
								Thread.sleep(toggle);//166);
							//if (toggle >= 10) Thread.sleep(1000*120); 
							//このやり方だとfpsの変更になりよくない
							//座標の差異の値を変更する
						}catch(Exception ex) {}
			//}
			}
			
		}
	}

	class drawPanel extends JPanel { //DP for drawPanel
		//This g object doesn't change about their parameter 

		public void paintComponent(Graphics g) {
			
			g.setColor(Color.GRAY);//たぶんバックグラウンドの色
			g.fillRect(0, 0, this.getWidth(),this.getHeight() );//背景の色を埋める？
			g.setColor(Color.GREEN);//また色の定義

			
			g.fillOval(MotionBall01.x, MotionBall01.y, MotionBall01.ovalWidth, MotionBall01.ovalHeight );

		}
	}
	
		///*
	
		class ToggleListenerSU implements ActionListener{
			public void actionPerformed(ActionEvent event) {
				MotionBall01.toggle--;
				System.out.println("Speed Up!!!Toggle is " + MotionBall01.toggle );
			} 
		}
		
	
		class ToggleListenerSD implements ActionListener{
			public void actionPerformed(ActionEvent event) {
				MotionBall01.toggle++;
				System.out.println("Speed Down!!!Toggle is " + MotionBall01.toggle );
			} 
		}
	
	

	/* Want to add play stop buttons
	 * and change layout.
	 * 
	 * 
	 */


