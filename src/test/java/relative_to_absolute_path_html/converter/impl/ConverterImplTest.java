package relative_to_absolute_path_html.converter.impl;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import relative_to_absolute_path_html.converter.ConvertCondition;
import relative_to_absolute_path_html.reader.ReadTargetCondition;
import relative_to_absolute_path_html.reader.Reader;
import relative_to_absolute_path_html.reader.impl.ReaderImpl;

public class ConverterImplTest {
	ConverterImpl converter = new ConverterImpl();
	Reader reader = new ReaderImpl();

	private final String TEST_FILE_DIR = "src/test/resources/relative_to_absolute_path_html/converter";
	private final String TEST_FILE_NAME_1 = "test1.txt";
	private final String TEST_FILE_NAME_2 = "test2.txt";

	private final String BR_N = "\n";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

	@Test
	public void 異常系_引数が全てnull(){
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("変換対象の文字列及び変換条件は必須です。");

		converter.convert(null, null);
	}

	@Test
	public void 異常系_変換対象の文字列がnull() throws MalformedURLException{
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("変換対象の文字列及び変換条件は必須です。");

		converter.convert(null, new ConvertCondition(new URL("http://test.com")));
	}

	@Test
	public void 異常系_変換条件がnull(){
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("変換対象の文字列及び変換条件は必須です。");

		converter.convert("<a>test</a>", null);
	}

	@Test
	public void 正常系_パスの変換() throws IOException {
		String htmlStr = reader.read(new ReadTargetCondition(TEST_FILE_DIR, TEST_FILE_NAME_1, Charset.defaultCharset()));
		String result = converter.convert(htmlStr, new ConvertCondition(new URL("http://test.com/test1/test2/test3/test.html")));

		StringBuilder expected = new StringBuilder();
		expected.append("<html>").append(BR_N);
		expected.append("<head></head>").append(BR_N);
		expected.append("<body>").append(BR_N);
		expected.append("<a href=\"http://test.com/test\"></a>").append(BR_N);
		expected.append("<a href=\"https://test.com/test\"></a>").append(BR_N);
		expected.append("<a href=\"http://test.com/test1/test2/test3/test\"></a>").append(BR_N);
		expected.append("<a href=\"http://test.com/test\"></a>").append(BR_N);
		expected.append("<a href=\"http://test.com/test1/test2/test\"></a>").append(BR_N);
		expected.append("<a href=\"http://test.com/test1/test\"></a>").append(BR_N);
		expected.append("</body>").append(BR_N);
		expected.append("</html>");

		//パーサーがインデントをつけてくるので削除して比較する
		assertEquals(expected.toString(), result.replaceAll(" +<", "<").replaceAll("> +", ">"));
	}

	@Test
	public void 正常系_パスの変換_デフォルト() throws IOException {
		String htmlStr = reader.read(new ReadTargetCondition(TEST_FILE_DIR, TEST_FILE_NAME_2, Charset.defaultCharset()));
		String result = converter.convert(htmlStr, new ConvertCondition(new URL("http://test.com/test1/test2/test3/test.html")));

		StringBuilder expected = new StringBuilder();
		expected.append("<html>").append(BR_N);
		expected.append("<head>").append(BR_N);
		expected.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://test.com/test1/test2/test3/xxx.css\">").append(BR_N);
		expected.append("<script type=\"text/javascript\" src=\"http://test.com/test1/test2/test3/test.js\"></script>").append(BR_N);
		expected.append("</head>").append(BR_N);
		expected.append("<body>").append(BR_N);
		expected.append("<a href=\"http://test.com/test\"></a>").append(BR_N);
		expected.append("<a href=\"https://test.com/test\"></a>").append(BR_N);
		expected.append("<a href=\"http://test.com/test1/test2/test3/test\"></a>").append(BR_N);
		expected.append("<a href=\"http://test.com/test\"></a>").append(BR_N);
		expected.append("<a href=\"http://test.com/test1/test2/test\"></a>").append(BR_N);
		expected.append("<a href=\"http://test.com/test1/test\"></a>").append(BR_N);
		expected.append("</body>").append(BR_N);
		expected.append("</html>");

		//パーサーがインデントをつけてくるので削除して比較する
		assertEquals(expected.toString(), result.replaceAll(" +<", "<").replaceAll("> +", ">"));
	}
	//TODO 指定したタグで動くこと、デフォルトがちゃんと動くこと、同一行にURLが複数あっても動くこと、URLのくくり文字がダブルクオートでも動くこと、タグのインデントが様々な感じでも動くこと

	@Test
	public void convertRelativeToAbsolutePath_異常系_引数が全てnull() throws MalformedURLException{
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("sourceUrlは必須です。");

		converter.convertRelativeToAbsolutePath(null, null);
	}

	@Test
	public void convertRelativeToAbsolutePath_異常系_sourceUrlがnull() throws MalformedURLException{
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("sourceUrlは必須です。");

		converter.convertRelativeToAbsolutePath(null, "test");
	}

	@Test
	public void convertRelativeToAbsolutePath_正常系_targetUrlがnull() throws MalformedURLException{
		String result = converter.convertRelativeToAbsolutePath(new URL("http://test.com/test1/test2/test3/test.html"), null);

		//結果が変わらないこと
		assertNull(result);
	}

	@Test
	public void convertRelativeToAbsolutePath_正常系_絶対パス_http() throws MalformedURLException{
		final String path = "http://test.com/test";
		String result = converter.convertRelativeToAbsolutePath(new URL("http://test.com/test1/test2/test3/test.html"), path);

		//結果が変わらないこと
		assertEquals(path, result);
	}


	@Test
	public void convertRelativeToAbsolutePath_正常系_絶対パス_https() throws MalformedURLException{
		final String path = "https://test.com/test";
		String result = converter.convertRelativeToAbsolutePath(new URL("https://test.com/test1/test2/test3/test.html"), path);

		//結果が変わらないこと
		assertEquals(path, result);
	}

	@Test
	public void convertRelativeToAbsolutePath_正常系_相対パス_スラッシュなし() throws MalformedURLException{
		final String path = "test";
		String result = converter.convertRelativeToAbsolutePath(new URL("https://test.com/test1/test2/test3/test.html"), path);

		assertEquals("https://test.com/test1/test2/test3/test", result);
	}

	@Test
	public void convertRelativeToAbsolutePath_正常系_相対パス_スラッシュあり() throws MalformedURLException{
		final String path = "/test";
		String result = converter.convertRelativeToAbsolutePath(new URL("https://test.com/test1/test2/test3/test.html"), path);

		assertEquals("https://test.com/test", result);
	}

	@Test
	public void convertRelativeToAbsolutePath_正常系_相対パス_階層1() throws MalformedURLException{
		final String path = "../test";
		String result = converter.convertRelativeToAbsolutePath(new URL("http://test.com/test1/test2/test"), path);

		assertEquals("http://test.com/test1/test", result);
	}

	@Test
	public void convertRelativeToAbsolutePath_正常系_相対パス_階層2() throws MalformedURLException{
		final String path = "../test";
		String result = converter.convertRelativeToAbsolutePath(new URL("http://test.com/test1/test2/test"), path);

		assertEquals("http://test.com/test1/test", result);
	}



}
