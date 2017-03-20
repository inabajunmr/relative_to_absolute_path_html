package relative_to_absolute_path_html.reader;

import java.nio.charset.Charset;
import java.nio.file.Path;

/**
 * ファイルの読み込み条件
 * @author inabajunmr
 */
public class ReadTargetCondition {

	public Charset getCharSet() {
		return charSet;
	}

	public ReadTargetCondition(Path targetFilePath, Charset charSet) {
		super();

		if(targetFilePath == null || charSet == null){
			throw new IllegalArgumentException("ファイルパス及び文字コードは必須です。");
		}
		this.targetFilePath = targetFilePath;
		this.charSet = charSet;
	}

	public Path getTargetFilePath() {
		return targetFilePath;
	}

	/**
	 * 対象ファイルの配置先
	 */
	private Path targetFilePath;

	/**
	 * 対象ファイルの文字コード
	 */
	private Charset charSet;
}
