package io.github.atealxt.nlp;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Test;

public class TestDrawing {

	private static final int ITEM_CNT = 16;
	private static final int DIMENSION_DEPTH = 3;

	@Test
	public void test() throws IOException {
		int itemCnt = ITEM_CNT, dimLevel = DIMENSION_DEPTH, groupByCnt = (int) Math.ceil((Math.log(itemCnt) / Math.log(dimLevel)));
		int imagePadding = 5, lineMargin = 10, lineWidth = 50, lineHeight = 3, tagLineWidth = 30, groupWidth = 50, itemWidth = 40, itemHeight = 40;
		double scale = 3.0;
		int width = itemWidth + (lineWidth + tagLineWidth + imagePadding + lineMargin + groupWidth) * dimLevel;
		int height = (int) (itemCnt * itemHeight / scale);

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bi.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("TimesRoman", Font.PLAIN, 12);
		g2.setFont(font);
		// ig2.setPaint(Color.black);

		FontMetrics fontMetrics = g2.getFontMetrics();
		double lineYStart = 0.0, lineYEnd = 0.0;
		int stringHeight = fontMetrics.getAscent(), iGroup = 1;
		List<Point> group = new ArrayList<>();

		// Print item
		for (int i = 1; i <= itemCnt; i++) {
			String message = "test" + i;
			int x = imagePadding, y = i * stringHeight;
			g2.drawString(message, x, y);
			group.add(new Point(x + fontMetrics.stringWidth(message), y));
		}

		// Print group
		while (group.size() > 1) {
			int lineXTo = (int) (group.get(group.size() - 1).getX() + lineWidth);
			List<Point> groupNew = new ArrayList<>();
			for (int i = 1; i <= group.size(); i++) {
				Point p = group.get(i - 1);
				int lineXStart = (int) (p.getX() + lineMargin);
				double lineY = p.getY() - stringHeight / 2.0 + lineHeight / 2.0;
				g2.draw(new Line2D.Double(lineXStart, lineY, lineXTo, lineY));
				lineYEnd = lineY;
				if (lineYStart == 0) {
					lineYStart = lineY;
				}
				if (i % groupByCnt == 0 || i == group.size()) {
					g2.draw(new Line2D.Double(lineXTo, lineYStart, lineXTo, lineYEnd));
					g2.draw(new Line2D.Double(lineXTo, (lineYStart + lineYEnd) / 2.0, lineXTo + tagLineWidth, (lineYStart + lineYEnd) / 2.0));
					String groupName = "Group " + iGroup++;
					int xGroupStart = lineXTo + tagLineWidth + imagePadding, yGroupStart = (int) ((lineYStart + lineYEnd) / 2.0 + stringHeight / 2.0);
					g2.drawString(groupName, xGroupStart, yGroupStart);
					groupNew.add(new Point(xGroupStart + fontMetrics.stringWidth(groupName), yGroupStart));
					lineYStart = 0;
				}
			}
			group = groupNew;
		}

		URL location = TestDrawing.class.getProtectionDomain().getCodeSource().getLocation();
		File jpg = new File(location.getPath(), "test.jpg");
		ImageIO.write(bi, "JPEG", jpg);
	}
}
