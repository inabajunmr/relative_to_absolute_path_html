package relative_to_absolute_path_html.converter;

/**
 * HTMLファイル内の属性を指定する<BR>
 * <a href="test">のtestを対象とする場合、tag=a, attribute=href<br>
 * 想定されないタグや属性を指定するため、パラメータは文字列のまま保持する
 * @author inabajunmr
 *
 */
public class ConvertTarget {
	public String getTag() {
		return tag;
	}

	public String getAttribute() {
		return attribute;
	}

	public ConvertTarget(String tag, String attribute) {
		super();
		this.tag = tag;
		this.attribute = attribute;
	}

	/**
	 * タグ名
	 */
	private String tag;

	/**
	 * 属性名
	 */
	private String attribute;
}
