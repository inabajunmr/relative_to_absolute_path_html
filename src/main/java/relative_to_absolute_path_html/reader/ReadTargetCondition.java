package relative_to_absolute_path_html.reader;

/**
 * ファイルの読み込み条件
 * @author inabajunmr
 */
public class ReadTargetCondition {

	public ReadTargetCondition(String targetFileDir, String targetFileName, String charSet) {
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
	public String targetFileDir;

	/**
	 * 対象ファイルのファイル名
	 */
	public String targetFileName;

	/**
	 * 対象ファイルの文字コード
	 */
	public String charSet;
}
