package relative_to_absolute_path_html.reader.impl;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import relative_to_absolute_path_html.reader.ReadTargetCondition;
import relative_to_absolute_path_html.reader.Reader;

public class ReaderImplTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	Reader reader = new ReaderImpl();

	private final String TEST_FILE_DIR = "src/test/resources/relative_to_absolute_path_html/reader";
	private final String TEST_FILE_NAME = "test.txt";
	Path TEST_FILE_PATH = FileSystems.getDefault().getPath(TEST_FILE_DIR, TEST_FILE_NAME);

	private final String BR_N = "\n";
	private final String BR_RN = "\r\n";

	@Test
	public void 引数がnull() throws IOException {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("conditionがnullです。");

		reader.read(null);
	}

	@Test
	public void ファイルが存在しない() throws IOException {
		thrown.expect(NoSuchFileException.class);
		final String TARGET_FILE_NAME = "none";
		reader.read(new ReadTargetCondition(TEST_FILE_DIR ,TARGET_FILE_NAME, Charset.defaultCharset()));
	}

	@Test
	public void 正常系_UTF8() throws IOException {
		Files.delete(TEST_FILE_PATH);

		StringBuilder sb = new StringBuilder();
		sb.append("アイウエオ");
		sb.append(BR_N);
		sb.append("あいうえお");
		sb.append(BR_RN);
		sb.append("aiueo");

		List<String> lines = new ArrayList<>();
		lines.add(sb.toString());
		Files.write(TEST_FILE_PATH, sb.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
		ReadTargetCondition cond = new ReadTargetCondition(TEST_FILE_DIR, TEST_FILE_NAME, StandardCharsets.UTF_8);
		String result = reader.read(cond);
		assertEquals(sb.toString(), result);
	}

	@Test
	public void 正常系_MS932() throws IOException{
		Files.delete(TEST_FILE_PATH);

		StringBuilder sb = new StringBuilder();
		sb.append("アイウエオ");
		sb.append(BR_N);
		sb.append("あいうえお");
		sb.append(BR_RN);
		sb.append("aiueo");

		List<String> lines = new ArrayList<>();
		lines.add(sb.toString());
		Files.write(TEST_FILE_PATH, sb.toString().getBytes(Charset.forName("MS932")), StandardOpenOption.CREATE);
		ReadTargetCondition cond = new ReadTargetCondition(TEST_FILE_DIR, TEST_FILE_NAME, Charset.forName("MS932"));
		String result = reader.read(cond);
		assertEquals(sb.toString(), result);
	}
}
