package relative_to_absolute_path_html.converter;

import java.util.Collections;
import java.util.List;

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

	public List<String> getAttributes() {
		return attributes;
	}

	public ConvertTarget(String tag, List<String> attribute) {
		super();
		this.tag = tag;
		this.attributes = Collections.unmodifiableList(attribute);
	}

	/**
	 * タグ名
	 */
	private String tag;

	/**
	 * 属性名
	 */
	private List<String> attributes;
}
