# Library
If URL in html file is relative path, this library convert absolutely path and create new html file.

Before convert.

```
<a href="/aaa/bbb.html">
```

After convert.
```
<a href="http:example.com/aaa/bbb/html">
```

## Usage
```
String result = converter.convert(htmlStr, new ConvertCondition(new URL("http://test.com/test1/test2/test3/test.html")));
```

### Arguments
1. Target html String.
2. Target URL. This URL is used for converting URL in html file.
