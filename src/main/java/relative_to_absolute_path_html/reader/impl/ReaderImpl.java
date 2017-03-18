package relative_to_absolute_path_html.reader.impl;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

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
		Path path = FileSystems.getDefault().getPath(condition.getTargetFileDir(), condition.getTargetFileName());
		byte[] resultLines = Files.readAllBytes(path);
		return new String(resultLines, condition.getCharSet());
	}

}
