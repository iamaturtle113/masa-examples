package applications;
import static practice.IOMethods.*;
//import java.util.Timer;
//import static java.util.Timer.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
/*
 * 180616(Sat)
 * タイマーアプリを作りたくなった。
 *  * import java.util.Timer;
　* import static java.util.Timer.*;
	がstart()、stop()メソッドのコールをできなくしていた。
	
 * setSizeはレイアウトをnullにしないと使えないのかも
 * setFontを使ったほうが楽
 * */
public class CountUpTimer01 extends JFrame implements ActionListener
	{
	Timer timer;
	JLabel label;
	int TimeSummationInSec;//Sum, Unit
	int secDisplayed;//sec in timer
	int min;//Sum min it could be over 60
	int minDisplayed;//min in timer less than 60
	int hour;
	
	public void TimerAppFrame() 
	{//TimerAppFrame
		JFrame frame = new JFrame();
		JPanel panel=new JPanel();
		label=new JLabel("Count up");//JLabelに時間を表示させる 一言目の表示
		panel.setBackground(Color.DARK_GRAY);
		label.setForeground(Color.LIGHT_GRAY);
	    label.setFont(new Font("Arial", Font.PLAIN, 50));
	    label.setHorizontalAlignment(JLabel.CENTER);
	    label.setVerticalAlignment(JLabel.CENTER);//文字列の位置で、レーベルの位置ではない
		label.setPreferredSize(new Dimension(250,150));
		LineBorder lineBoder = new LineBorder(Color.white, 2, true);//丸枠用のコード
		label.setBorder(lineBoder);
		panel.add(label);
	
		timer=new Timer(1000,this);
		frame.getContentPane().add(BorderLayout.CENTER,panel);
		//JButton button=new JButton("Open FileChooser!!");
		//button.addActionListener(this);	
		timer.start();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,200);
		frame.setVisible(true);
	}//End of TimerAppFrame()
	
	public void actionPerformed(ActionEvent e) 
	{
		//場合分け
		if (TimeSummationInSec<60)//
		{
			if (TimeSummationInSec<10)
				label.setText("0:0"+TimeSummationInSec);
			else
				label.setText("0:"+TimeSummationInSec);
		}
		if (TimeSummationInSec>=60)
		{
			min=TimeSummationInSec/60;
			secDisplayed=TimeSummationInSec-(min*60);
			if(min<60) 
			{
				if (secDisplayed<10)
					label.setText(min+":0"+secDisplayed);
				else
					label.setText(min+":"+secDisplayed);
			}
			else if(min>=60)
			{
				hour=min/60;
				minDisplayed=min-(hour*60);
				// secDisplayed minDisplayed double if condition make
				if (secDisplayed<10)//表示するべき秒がひと桁のとき
					if (minDisplayed<10)
					label.setText(hour+":0"+minDisplayed+":0"+secDisplayed);
					if (minDisplayed>=10)
						label.setText(hour+":"+minDisplayed+":0"+secDisplayed);
				else
					if (minDisplayed<10)
						if (secDisplayed<10)
							label.setText(hour+":"+minDisplayed+":0"+secDisplayed);
						else 
							label.setText(hour+":0"+minDisplayed+":"+secDisplayed);
					if (minDisplayed>=10)
						label.setText(hour+":"+minDisplayed+":"+secDisplayed);
						if (secDisplayed<10)
							label.setText(hour+":"+minDisplayed+":0"+secDisplayed);
			}
		}//場合分けおわり
		
		TimeSummationInSec++;
		
	}//end of public void actionPerformed(ActionEvent e) 
	
	public static void main(String[] args) 
	{
		CountUpTimer01 frame=new CountUpTimer01();
		frame.TimerAppFrame();
	}
	
}// End of TimerApp

