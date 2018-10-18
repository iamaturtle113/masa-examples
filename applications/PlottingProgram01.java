package applications;
import static practice.IOMethods.*;
import javax.swing.*;

//import masa_stdlib.Drawing;

import java.awt.*;
//import  masa_stdlib.Drawing.*;
/*180601(Fri)
 * プロッティングしたくなる。まず線の描画
 * 座標のｘとｙの順番を間違えていた。
 * フレームのサイズとパネルのサイズのsetのところ
 * 
 */


public class PlottingProgram01 extends JPanel 
{
	
	static int frameHeight=600;//DrawPanel's size
	public static int frameWidth=600;
	static String equation1 = "Input equation 1";//方程式のひとつ目
	static String equation2 = "Input equation 2";
	Point p1=new Point(100,100);
	Point p2=new Point(200,200);
	Point p3=new Point(0,300,5);//点専用のポイント
	Point pCenter=new Point(frameWidth/2,frameHeight/2,10);
	Point pV1=new Point(pCenter.x,frameHeight);//座標の縦線用の始点
	Point pV2=new Point(pCenter.x,0);//座標の縦線用の終点
	Point pH1=new Point(0,frameHeight/2);
	Point pH2=new Point(frameWidth,frameHeight/2);
	
	//メジャー用の文字のためのJLabelを生成
	private JLabel[] generateLabels()
    {
		int length=30;
        JLabel[] l=new JLabel[length];
        //int y=0;
        int k=length-1;
        for (int i=0;i<length;i++)
        {
        	
            l[i]=new JLabel(Integer.toString(k));
            k=k-1;
        }
        return l;
        
    }
	

 

	public void init() 
	{
		JFrame frame = new JFrame("PlottingProgram01 Program!!!");
		DrawPanel DP=new DrawPanel();//DrawPanelはJPanelのオーバーライドクラスのインスタンス
		JPanel equaionsPanel = new JPanel(); 
		equaionsPanel.setBackground(Color.CYAN);
		equaionsPanel.setLayout(new GridLayout(2,0,10,100));//追加
		JTextField equationField1 = new JTextField("Test Strings sub to var equation1");
		JTextField equationField2 = new JTextField(equation2);
		equationField1.setPreferredSize(new Dimension(200,1));//
		equationField1.setForeground(Color.BLUE);//
		equationField1.setBackground(Color.GRAY);
		equaionsPanel.add(equationField1);//追加
		equaionsPanel.add(equationField2);//追加
		
		DP.setLayout(null);
		
//initにlabeladdをかく
		//Draw strings by labels
		int y=5;
		JLabel[] l=generateLabels();
        for (int i=0;i<l.length;i++)
        {
    		l[i].setSize(100, 10);//(width, height);
    		l[i].setLocation(15, y);
    		y+=10;
    		DP.add(l[i]);//Labelをパネルに加える
            DP.add(l[i]);
        }
        
        //座標のOを書き込む
		JLabel O=new JLabel("0");
		O.setSize(100,20);
		O.setLocation(pCenter.x-15,pCenter.y-3);
		DP.add(O);
		//ここまで
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(BorderLayout.CENTER, DP);
		frame.getContentPane().add(BorderLayout.EAST, equaionsPanel);
		DP.setSize(frameWidth,frameHeight);
		
		frame.setSize(frameWidth + 200,frameHeight);
		
		
		frame.setVisible(true);
	}
	/*

    */
	
    
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		PlottingProgram01 plot=new PlottingProgram01();
		plot.init();

		

	}
	
	class DrawPanel extends JPanel
	{ //DP for drawPanel
		JLabel[] lArray=generateLabels();
		public void paintComponent(Graphics g) 
		{
			
			g.setColor(Color.WHITE);//たぶんバックグラウンドの色
			g.fillRect(0, 0, this.getWidth(),this.getHeight() );//背景の色を埋める？
			g.setColor(Color.BLACK);//また色の定義
			drawPointH(g, pCenter);
			drawRuledLine(g);
			drawLineWithPoint(g,pV1,pV2);
			drawLineWithPoint(g,pH1,pH2);
			drawLinearEq(g);
			drawQuadraticEq(g);
			//addLabels(lArray);
			generateLabels();
		}
		
		public void drawPoint(Graphics g,Point p) 
		{
			
			g.drawLine(p.x, p.y, p.x, p.y);
		}
		
		public void drawPointH(Graphics g,Point p) 
		{
			
			g.drawLine(p.x, p.y, p.x, p.y+p.value);
		}
		
		public void drawPointW(Graphics g,Point p) 
		{
			g.drawLine(p.x, p.y, p.x+p.value, p.y);
		}
		
		
			
			//点線を描くメソッド
			//フレームサイズ変えると消える
		public void drawPointLine(Graphics g,Point p) 
		{
			for (int i=0;i<400;i+=10) 
			{
				//g.drawLine(p3.x+i,p3.y,p3.x+i,p3.y);
				drawPointW(g, p);
				//p.x=p.x+i;
				p.x=p.x+10;
			}
		}
		
		//方眼の横線の描画
		public void drawRuledLine(Graphics g) 
		{
			Point pR=new Point(0,0,10);
			for (int i=0;i<frameHeight;i+=10) 
			{
				drawPointW(g,pR);
				pR.y=pR.y+10;
			
			}
			
		}
		
		public void drawLineWithPoint(Graphics g,Point p1,Point p2)
		{
			g.drawLine(p1.x,p1.y,p2.x,p2.y);
		}
//一次の方程式の描画		
/*
 * y座標は一番上が０なので、除算ベースになる
 * 二次の方程式　結果を100で割ると、100ピクセルが1にあたる
 * 50で割ると、50ピクセルが1にあたる
 */
		public void drawLinearEq(Graphics g)
		{
			//This case presume y=2x
			int x0=pCenter.x;
			int y0=pCenter.y;
			for (int x=0;x<frameWidth;x++)
			{
				y0=pCenter.y-2*x;
				x0=x0+1;
				//x0=x0+x; これは平方根のグラフみたいになる
				Point p=new Point(x0,y0);
				drawPoint(g,p);
			}
		}
//二次の方程式の描画		
		public void drawQuadraticEq(Graphics g)
		{
			//This case presume y=x^2
			int x0=pCenter.x;
			int y0=pCenter.y;
			for (int x=0;x<frameWidth;x++)
			{
				y0=pCenter.y-(x*x/25);
				x0=x0+1;
				//x0=x0+x; これは平方根のグラフみたいになる
				Point p=new Point(x0,y0);
				drawPoint(g,p);
			}
		}
		
		//縦座標（Ordinateを表す数字を表示するメソッド
		
	}//class DrawPanel end
	
	
	public class Point
	{
		int x;
		int y;
		int value;
		//int width;
		Point(int x,int y) 
		{
			this.x=x;
			this.y=y;
		}
		
		Point(int x,int y,int value)
		{
			this.x=x;
			this.y=y;
			this. value=value;
		}
		

	}//Point End
	

}//class PlottingProgram01 End 


