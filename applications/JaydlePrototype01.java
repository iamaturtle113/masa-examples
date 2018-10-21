package applications;
/*180512(Sat)
 * Youtube-Dl-Extended用のプロセスビルダーを書く
 * 
 */

import java.lang.ProcessBuilder;
import static practice.IOMethods.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Process;
import java.lang.Runtime;
import java.util.*;
import javax.security.auth.callback.TextOutputCallback;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/*180809Thu 
 * 
 */
public class JaydlePrototype01 extends JFrame implements ActionListener
{
	 static JTextArea textOut=new JTextArea();
	 JScrollPane scrollpane=new JScrollPane(textOut,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	 static String initText=("URLを入力してください");
	 static JTextField textIn=new JTextField(initText);
	 static String strIn=null;
	 static File dir=new File ("/home/masa/Desktop/");
	
	public static void processBuild(List<String> cmdList)//String[] cmdArray)
	{
		try 
		{

			ProcessBuilder pb=new ProcessBuilder(cmdList);
	
			pb.directory(dir);
			
			Process p=pb.start();
			BufferedReader bfr2=new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader bfrErr=new BufferedReader(new InputStreamReader(p.getErrorStream()));
			String line=null;
				while((line=bfr2.readLine())!=null)
			//最後まで読み込むと、lineがnullになることを利用している
				{
					println(line);
					textOut.append(line+"\n");
				}
				while((line=bfrErr.readLine())!=null)
				//最後まで読み込むと、lineがnullになることを利用している
				{
					println(line);
					textOut.append(line+"\n");
				}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}//processBuildおわり
	
	public static List<String> toList(String[] strArray)
	{
		try
		{
		List<String> cmdList=new ArrayList<String>();
		for(String s:strArray)
			{
				cmdList.add(s);
			}
		return cmdList;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static void dlAudio(String url)
	{
		String[] audioDl= {"youtube-dl","--no-playlist","--extract-audio","--audio-format","mp3",url,"-o","'%(title)s.%(ext)s'"};
		List<String> listA=toList(audioDl);
		processBuild(listA);
	}
	

	
	public static void main(String[] args) 
	{
		JaydlePrototype01 gui=new JaydlePrototype01();
		gui.init();
	}//end of main method
	
	public void init() {
		JPanel panel =new JPanel();
		JLabel text =new JLabel("\"JAva Youtube-DL Exteded implementation by Masataka Nakamura\"");
		JFrame frame = new JFrame("Jaydle");
		textOut.setLineWrap(true);
		JButton button=new JButton("ダウンロードする");
		button.setPreferredSize(new Dimension(100, 30));
		button.addActionListener(this);	
		panel.setLayout(new BorderLayout());//このコードがパネルのレイアウト変更には必要
		panel.add(BorderLayout.NORTH,text);//"Youtube-DL Exteded Java Implementation by Masataka Nakamura"
		panel.add(BorderLayout.SOUTH,textIn);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(BorderLayout.CENTER,scrollpane);
		frame.getContentPane().add(BorderLayout.NORTH,panel);
		frame.getContentPane().add(BorderLayout.SOUTH,button);
		frame.setSize(800,600);
		frame.setVisible(true);
	}//End of init()
	
	public void actionPerformed(ActionEvent e)
	{
			strIn=textIn.getText();
			if(strIn.length()>13)
				dlAudio(strIn);
	}

}//end of class ProcessBuild

