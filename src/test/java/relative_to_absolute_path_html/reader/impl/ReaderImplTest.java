package relative_to_absolute_path_html.reader.impl;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import relative_to_absolute_path_html.reader.ReadTargetCondition;
import relative_to_absolute_path_html.reader.Reader;

public class ReaderImplTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	Reader reader = new ReaderImpl();

	private final String TEST_FILE_DIR = "src/test/resources/relative_to_absolute_path_html.reader/impl/";

	@Test
	public void 引数がnull() throws IOException {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("conditionがnullです。");

		reader.read(null);
	}

	@Test
	public void ファイルが存在しない() throws IOException {
		final String TARGET_FILE_NAME = "none";
		reader.read(new ReadTargetCondition(TEST_FILE_DIR ,TARGET_FILE_NAME, Charset.defaultCharset().name()));	}
	@Test
	public void 正常系() throws IOException {
		final String TARGET_FILE_NAME = "test.html";
		String test = reader.read(new ReadTargetCondition(TEST_FILE_DIR ,TARGET_FILE_NAME,
				Charset.defaultCharset().name()));
		//TODO
	}

	//TODO 正常系、文字コード
	//TODO 正常系、改行コード

}
