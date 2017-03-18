package relative_to_absolute_path_html.converter.impl;

import relative_to_absolute_path_html.converter.ConvertCondition;
import relative_to_absolute_path_html.converter.Converter;

public class ConverterImpl implements Converter {

	@Override
	public String convert(String htmlStr, ConvertCondition cond) {
		if(htmlStr == null || cond == null){
			throw new IllegalArgumentException("変換対象の文字列及び変換条件は必須です。");
		}
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
