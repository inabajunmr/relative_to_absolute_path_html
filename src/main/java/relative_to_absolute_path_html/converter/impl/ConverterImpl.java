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
			Elements elements = doc.getElementsByTag(target.getTag());
			for(Element element : elements){
				//タグに設定された全部の属性を変換
				for(String attribute : target.getAttributes()){
					if(!element.hasAttr(attribute)){
						//対象の属性が存在しなければスキップ
						continue;
					}

					//属性に対してURLを取得
					String url = element.attr(attribute);

					//URLを絶対パスに変換する
					String absoluteUrl = convertRelativeToAbsolutePath(cond.getUrl(), url);

					//elementのURLを変換後のURLに設定し直す
					element.attr(attribute, absoluteUrl);
				}
			}
		}

		return doc.outerHtml();
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
