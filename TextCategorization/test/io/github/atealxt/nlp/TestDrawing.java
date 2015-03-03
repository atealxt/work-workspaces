package io.github.atealxt.nlp;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.Test;

public class TestDrawing {

	@Test
	public void test() throws IOException {
		int itemCnt = 16, dimLevel = 3, groupByCnt = (int) Math.ceil((Math.log(itemCnt) / Math.log(dimLevel)));
		int imagePadding = 5, width = 200, height = (int) (itemCnt * 40 / 3.0), lineMargin = 10, lineHeight = 3, tagLineWidth = 30;

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bi.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("TimesRoman", Font.PLAIN, 12);
		g2.setFont(font);
		// ig2.setPaint(Color.black);

		FontMetrics fontMetrics = g2.getFontMetrics();
		int lineXTo = fontMetrics.stringWidth("test" + itemCnt) * 2, iGroup = 1;
		double lineYStart = 0.0, lineYEnd = 0.0;
		for (int i = 1; i <= itemCnt; i++) {
			String message = "test" + i;
			int stringWidth = fontMetrics.stringWidth(message);
			int stringHeight = fontMetrics.getAscent();
			g2.drawString(message, imagePadding, i * stringHeight);
			double lineY = i * stringHeight - stringHeight / 2.0 + lineHeight;
			g2.draw(new Line2D.Double(stringWidth + lineMargin, lineY, lineXTo, lineY));
			lineYEnd = lineY;
			if (lineYStart == 0) {
				lineYStart = lineY;
			}
			if (i % groupByCnt == 0 || i == itemCnt) {
				g2.draw(new Line2D.Double(lineXTo, lineYStart, lineXTo, lineYEnd));
				g2.draw(new Line2D.Double(lineXTo, (lineYStart + lineYEnd) / 2.0, lineXTo + tagLineWidth, (lineYStart + lineYEnd) / 2.0));
				String groupName = "Group " + iGroup++;
				int xGroupStart = lineXTo + tagLineWidth + imagePadding, yGroupStart = (int) ((lineYStart + lineYEnd) / 2.0 + stringHeight / 2.0);
				g2.drawString(groupName, xGroupStart, yGroupStart);
				lineYStart = 0;
			}
		}

		URL location = TestDrawing.class.getProtectionDomain().getCodeSource().getLocation();
		File jpg = new File(location.getPath(), "test.jpg");
		ImageIO.write(bi, "JPEG", jpg);
	}
}
