package relative_to_absolute_path_html.converter;

public interface Converter {

	/**
	 * ConvertConditionにしたがって、引数のHTML文字列を変換します。<BR>
	 * ファイルがHTMLとして正常かどうかのチェックはしません。<BR>
	 * タグが正常に閉じられていないXMLとして異常な文字列を読み込むと、正常に変換が行われない可能性があります。
	 * @param htmlStr
	 * @param cond
	 * @return
	 */
	public String convert(String htmlStr, ConvertCondition cond);
}
