package relative_to_absolute_path_html.converter.impl;

import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import relative_to_absolute_path_html.converter.ConvertCondition;
import relative_to_absolute_path_html.converter.ConvertTarget;
import relative_to_absolute_path_html.converter.Converter;

public class ConverterImpl implements Converter {

	final static String PATTERN_1 = "(<\\s*";
	final static String PATTERN_2 = "\\s*";
	final static String PATTERN_3 = "\\s*=[\"'])([^\"']+)([\"'][^>]*>)";

	Logger log = LoggerFactory.getLogger(ConverterImpl.class);

	@Override
	public String convert(String htmlStr, ConvertCondition cond) {
		if(htmlStr == null || cond == null){
			throw new IllegalArgumentException("変換対象の文字列及び変換条件は必須です。");
		}
		//Jsoupのリファレンス https://jsoup.org/apidocs/
		Document doc = Jsoup.parse(htmlStr);

		//全ての対象タグ・属性をループしながら変換
		for(ConvertTarget target : cond.getTargets()){
			//			StringBuilder patternString = new StringBuilder();
			//			patternString.append(PATTERN_1).append(target.getTag()).append(PATTERN_2).append(target.getAttribute()).append(PATTERN_3);
			//			Pattern pattern = Pattern.compile(patternString.toString());
			//			Matcher matcher = pattern.matcher(htmlStr);
			//			matcher.

			Elements elements = doc.getElementsByTag(target.getTag());
			for(Element element : elements){
				for(String attribute : target.getAttributes()){
					String value = element.attr(attribute);
					if(value == null || value.isEmpty()){
						continue;
					}

					//URLを絶対パスに変換する
					//エレメントから属性を削除
					//変換後のURLで属性を追加
				}
			}

		}

		return null;
	}


	/**
	 * URLが相対パスであれば絶対パスに直して返却します。<BR>
	 * 元から絶対パスであれば、変換せずそのまま返却します。<BR>
	 * sourceUrlがnullの場合、例外をスローします。<BR>
	 * targetURLがnullの場合、nullを返却します。<BR>
	 * 変換に失敗した場合、例外をスローせずnullを返却します。
	 * @param sourceUrl
	 * @param targetUrl
	 * @return
	 */
	protected String convertRelativeToAbsolutePath(URL sourceUrl, String targetUrl){
		//引数のチェック
		if(sourceUrl == null){
			throw new IllegalArgumentException("sourceUrlは必須です。");
		}

		if(targetUrl == null){
			return null;
		}

		//URLを変換する。
		try {
			URL url = new URL(sourceUrl , targetUrl);
			return url.toString();
		} catch (MalformedURLException e) {
			log.warn("URLの変換に失敗しました。sourceURL:" + sourceUrl.toString() + " targetURL:" + targetUrl, e);
			return null;
		}
	}

}
