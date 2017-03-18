package relative_to_absolute_path_html.reader.impl;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import relative_to_absolute_path_html.reader.Reader;

public class ReaderImplTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

	Reader reader = new ReaderImpl();

	@Test
	public void 引数がnull() throws IOException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("conditionがnullです。");

		reader.read(null);
	}

	@Test
	public void ファイルが存在しない() {
		//TODO
	}
	@Test
	public void 正常系() {
		//TODO
	}

}
