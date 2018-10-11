package practice;
import static practice.IOMethods.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*2018-10-11-Thu
 * /home/masa/workspace/Playにbar.serファイルができていた。
 * Eclipseのツリー構造のビルド体系が気になる。Resourcesフォルダができてないのなんでだろう。
 * HFJの１４章、P419のほとんどコピー。
 * 
 */
public class SerializationPractice01 implements Serializable {

	private int width;
	private int height;
	
	public void setWidth(int w) {
		width = w;
	}
	
	public void setHeight(int h) {
		height = h;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public static void main(String[] args) {
		SerializationPractice01 myBox = new SerializationPractice01();
		myBox.setWidth(50);
		myBox.setHeight(20);
		
		//直列化のための処理　bar.serファイルに書き込む　ファイルはデフォルトディレクトリにできる
		//いまは/home/masa/workspace/Play
		try {
			FileOutputStream fos = new FileOutputStream("bar.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(myBox);
			oos.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
		//デシリアライゼーション　直列化復元の処理
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("bar.ser"));
			SerializationPractice01 myBoxAgain = (SerializationPractice01) ois.readObject();
			println("Read from serialized file \"bar.ser\" " + myBoxAgain.getWidth());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
		}
	}


