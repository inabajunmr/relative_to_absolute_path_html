package relative_to_absolute_path_html.converter.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import relative_to_absolute_path_html.converter.ConvertCondition;
import relative_to_absolute_path_html.converter.ConvertTarget;
import relative_to_absolute_path_html.converter.Converter;

public class ConverterImpl implements Converter {

	final static String PATTERN_1 = "(<\\s*";
	final static String PATTERN_2 = "\\s*";
	final static String PATTERN_3 = "\\s*=[\"'])([^\"']+)([\"'][^>]*>)";

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
				
			}

		}

		return null;
	}

}
