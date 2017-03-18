package relative_to_absolute_path_html.reader;

/**
 * ファイルの読み込み条件
 * @author inabajunmr
 */
public class ReadTargetCondition {

	public ReadTargetCondition(String targetFilePath, String charSet) {
		super();

		if(targetFilePath == null || charSet == null){
			throw new IllegalArgumentException("ファイルパス及び文字コードは必須です。");
		}
		this.targetFilePath = targetFilePath;
		this.charSet = charSet;
	}

	/**
	 * ファイルパス
	 */
	public String targetFilePath;

	/**
	 * 対象ファイルの文字コード
	 */
	public String charSet;
}
