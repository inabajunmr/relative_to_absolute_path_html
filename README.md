# Library
If URL in html file is relative path, this library convert absolutely path and create new html file.

Before convert.

```
<a href="/aaa/bbb.html">
```

After convert.
```
<a href="http://example.com/aaa/bbb/html">
```

## Usage
```
Converter converter = new ConverterImpl();
Reader reader = new ReaderImpl();

private final String TEST_FILE_DIR = "src/test/resources/relative_to_absolute_path_html/converter";
private final String TEST_FILE_NAME = "test.txt";

String htmlStr = reader.read(new ReadTargetCondition(Paths.get(TEST_FILE_DIR, TEST_FILE_NAME), Charset.defaultCharset()));
String result = converter.convert(htmlStr, new ConvertCondition(new URL("http://test.com/test1/test2/test3/test.html")));
```

### Arguments
1. Target html String.
2. Target URL. This URL is used for converting URL in html file.
