package relative_to_absolute_path_html.reader;

import java.nio.charset.Charset;

/**
 * ファイルの読み込み条件
 * @author inabajunmr
 */
public class ReadTargetCondition {

	public String getTargetFileDir() {
		return targetFileDir;
	}

	public String getTargetFileName() {
		return targetFileName;
	}

	public Charset getCharSet() {
		return charSet;
	}

	public ReadTargetCondition(String targetFileDir, String targetFileName, Charset charSet) {
		super();

		if(targetFileDir == null || targetFileName == null || charSet == null){
			throw new IllegalArgumentException("ファイルパス及び文字コードは必須です。");
		}
		this.targetFileDir = targetFileDir;
		this.targetFileName = targetFileName;
		this.charSet = charSet;
	}

	/**
	 * 対象ファイルの配置先ディレクトリ
	 */
	private String targetFileDir;

	/**
	 * 対象ファイルのファイル名
	 */
	private String targetFileName;

	/**
	 * 対象ファイルの文字コード
	 */
	private Charset charSet;
}
