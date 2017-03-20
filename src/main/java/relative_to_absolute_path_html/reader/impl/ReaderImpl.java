package relative_to_absolute_path_html.reader.impl;

import java.io.IOException;
import java.nio.file.Files;

import relative_to_absolute_path_html.reader.ReadTargetCondition;
import relative_to_absolute_path_html.reader.Reader;

public class ReaderImpl implements Reader {

	@Override
	public String read(ReadTargetCondition condition) throws IOException {
		//引数のチェック
		if(condition == null){
			throw new IllegalArgumentException("conditionがnullです。");
		}

		//改行も含めて読み込みたいのでByteで読み込んでから復号
		byte[] resultLines = Files.readAllBytes(condition.getTargetFilePath());
		return new String(resultLines, condition.getCharSet());
	}

}
