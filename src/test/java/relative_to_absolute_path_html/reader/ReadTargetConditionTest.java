package relative_to_absolute_path_html.reader;

import java.nio.charset.Charset;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ReadTargetConditionTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
	@Test
	public void 引数が全てnull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("ファイルパス及び文字コードは必須です。");

        new ReadTargetCondition(null,null);
	}

	@Test
	public void ファイルパスがnull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("ファイルパス及び文字コードは必須です。");

        new ReadTargetCondition(null, Charset.defaultCharset());
	}

	@Test
	public void 文字コードがnull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("ファイルパス及び文字コードは必須です。");

        new ReadTargetCondition(Paths.get("test"), null);
	}
}
