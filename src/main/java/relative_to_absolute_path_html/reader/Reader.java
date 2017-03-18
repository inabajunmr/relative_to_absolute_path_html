package relative_to_absolute_path_html.reader;

import java.io.IOException;

/**
 * ファイルからテキストを読み込むためのクラス
 * @author inabajunmr
 *
 */
public interface Reader {

	/**
	 * 引数からファイルを特定し、ファイルを読み込み文字列を返却します。<br>
	 * ファイルが存在しない場合、読み込みに失敗した場合、引数がnullは例外をスローします。<br>
	 * @param condition
	 * @return
	 * @throws IOException
	 */
	public String read(ReadTargetCondition condition) throws IOException;
}
