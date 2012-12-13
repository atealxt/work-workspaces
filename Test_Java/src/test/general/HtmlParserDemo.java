package test.general;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

public class HtmlParserDemo {

    public static void main(final String args[]) {
        final String html = "<p id=\"lh\"><a href=\"http://e.baidu.com/?refer=888\">加入百度推广</a> | <a href=\"http://top.baidu.com\">搜索风云榜</a> | <a href=\"http://home.baidu.com\">关于百度</a> | <a href=\"http://ir.baidu.com\">About Baidu</a></p>";
        System.out.println(substr(html, 4, true));
        System.out.println(substr(html, 4, false));
    }

    /**
     * 截取前X个显示的字符
     *
     * @param containsTag 是否保留样式（HTML tag）
     * */
    public static String substr(final String src, final int len, final boolean containsTag) {
        if (StringUtils.isEmpty(src)) {
            return "";
        }
        final StringBuilder buffer = new StringBuilder();
        try {
            final Parser parser = new Parser(src);
            final NodeList list = parser.parse(null);
            append(list, buffer, new LenInfo(src, len), containsTag);
        } catch (final ParserException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    private static void append(
            final NodeList list,
            final StringBuilder buffer,
            final LenInfo lenInfo,
            final boolean containsTag) {
        final SimpleNodeIterator iterator = list.elements();
        Node node = null;
        while (iterator.hasMoreNodes()) {
            node = iterator.nextNode();
            if (node instanceof TextNode) {
                if (lenInfo.isAppend()) {
                    final int sub = lenInfo.getLen() + node.getText().length() - lenInfo.getMax();
                    if (sub >= 0) {
                        final int min = Math.min(lenInfo.getMax() - lenInfo.getLen(), node.getText().length());
                        final String s = node.getText().substring(0, min) + "...";
                        buffer.append(s);
                        lenInfo.setLen(lenInfo.getLen() + s.length());
                        lenInfo.setAppend(false);
                    } else {
                        buffer.append(node.getText());
                        lenInfo.setLen(lenInfo.getLen() + node.getText().length());
                    }
                }
            } else if (containsTag) {
                buffer.append(lenInfo.getSrc().substring(node.getStartPosition(), node.getEndPosition()));
            }

            if (node.getChildren() != null) {
                append(node.getChildren(), buffer, lenInfo, containsTag);
            }
            if (containsTag && node instanceof Tag) {
                final Tag endTag = ((Tag) node).getEndTag();
                if (endTag != null) {
                    buffer.append(lenInfo.getSrc().substring(endTag.getStartPosition(), endTag.getEndPosition()));
                }
            }
        }
    }
}

class LenInfo {

    int len;
    int max;
    boolean append;
    String src;

    public LenInfo(final String src, final int max) {
        super();
        this.src = src;
        this.max = max;
        append = true;
    }

    public int getLen() {
        return len;
    }

    public void setLen(final int len) {
        this.len = len;
    }

    public int getMax() {
        return max;
    }

    public boolean isAppend() {
        return append;
    }

    public void setAppend(final boolean append) {
        this.append = append;
    }

    public String getSrc() {
        return src;
    }
}
