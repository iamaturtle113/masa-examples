package practice;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static practice.IOMethods.*;
/* 181009(Tue)
 * 参考、ポケリ
 * パターンの指定の正規表現がよくわからん。
 * あとPatternオブジェクトとMatcherオブジェクトの使い方。
 */

/*
 * Masa -> x
 * Masa.* -> o
 * Masa* -> x
 * ^Masa.* -> 0
 */

/*
 * Pattern pattern
 * Matcher matcher
 * Pattern pat
 */
public class RegexPractice01 {

	public static void main(String[] args) {
		String str="Masa's test for Regex";
		String patternStr=".*regex.*";
		Pattern pattern=Pattern.compile(patternStr,Pattern.CASE_INSENSITIVE);
		Matcher matcher=pattern.matcher(str);
		println(str);

		Pattern pat=matcher.pattern();
		println(pat.pattern());
		println(pattern.flags());
		println(matcher.matches());
		
		if(matcher.matches()) {
			println("Matched. " + str);
		}
		else {
			println("Not matched. ");
		}
	}

}
