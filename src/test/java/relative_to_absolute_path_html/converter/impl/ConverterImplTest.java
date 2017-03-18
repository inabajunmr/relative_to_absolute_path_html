package relative_to_absolute_path_html.converter.impl;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import org.junit.Test;

import relative_to_absolute_path_html.converter.ConvertCondition;
import relative_to_absolute_path_html.converter.Converter;
import relative_to_absolute_path_html.reader.ReadTargetCondition;
import relative_to_absolute_path_html.reader.Reader;
import relative_to_absolute_path_html.reader.impl.ReaderImpl;

public class ConverterImplTest {
	Converter converter = new ConverterImpl();
	Reader reader = new ReaderImpl();

	private final String TEST_FILE_DIR = "src/test/resources/relative_to_absolute_path_html/converter";
	private final String TEST_FILE_NAME = "test1.txt";

	private final String BR_N = "\n";

	@Test
	public void 正常系_パスの変換() throws IOException {
		String htmlStr = reader.read(new ReadTargetCondition(TEST_FILE_DIR, TEST_FILE_NAME, Charset.defaultCharset()));
		String result = converter.convert(htmlStr, new ConvertCondition(new URL("http://test.com/test1/test2/test3/test.html")));

		StringBuilder expected = new StringBuilder();
		expected.append("<body>").append(BR_N);
		expected.append("<a href='http://test.com/test'></a>").append(BR_N);
		expected.append("<a href='https://test.com/test'></a>").append(BR_N);
		expected.append("<a href='http://test.com/test1/tset2/test3/test'></a>").append(BR_N);
		expected.append("<a href='http://test.com/test'></a>").append(BR_N);
		expected.append("<a href='http://test.com/test1/test2/test'></a>").append(BR_N);
		expected.append("<a href='http://test.com/test1/test'></a>").append(BR_N);
		expected.append("</body>").append(BR_N);
		assertEquals(expected, result);
	}

	//TODO 指定したタグで動くこと、デフォルトがちゃんと動くこと、同一行にURLが複数あっても動くこと、URLのくくり文字がダブルクオートでも動くこと

}
