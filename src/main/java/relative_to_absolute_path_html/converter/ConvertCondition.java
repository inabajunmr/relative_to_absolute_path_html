package relative_to_absolute_path_html.converter;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * HTMLファイルの変換条件
 * @author inabajunmr
 *
 */
public class ConvertCondition {
	private static List<ConvertTarget> defaultTargets;

	/**
	 * 変換対象のターゲットを一般的なものに最初から搭載
	 * @param url
	 */
	public ConvertCondition(URL url) {
		super();
		this.url = url;
		if(defaultTargets == null){
			defaultTargets = new ArrayList<>();
			defaultTargets.add(new ConvertTarget("a", "href"));
			defaultTargets.add(new ConvertTarget("link", "href"));
			defaultTargets.add(new ConvertTarget("script", "src"));
		}

		this.targets = Collections.unmodifiableList(defaultTargets);
	}

	public ConvertCondition(List<ConvertTarget> targets, URL url) {
		super();
		this.targets = Collections.unmodifiableList(targets);
		this.url = url;
	}

	public List<ConvertTarget> getTargets() {
		return targets;
	}

	public URL getUrl() {
		return url;
	}

	/**
	 * 変換対象のタグ及び属性
	 */
	private List<ConvertTarget> targets;

	/**
	 * 変換対象のHTML取得元URL<br>
	 * 絶対パスに変換する際のプレフィックを切り出すために使用する
	 */
	private URL url;
}
