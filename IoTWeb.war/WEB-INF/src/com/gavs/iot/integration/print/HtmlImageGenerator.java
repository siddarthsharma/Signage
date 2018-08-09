package com.gavs.iot.integration.print;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HtmlImageGenerator {
	private JEditorPane editorPane;
	static final Dimension DEFAULT_SIZE = new Dimension(800, 800);

	public HtmlImageGenerator() {
		editorPane = createJEditorPane();
	}

	public Dimension getSize() {
		return editorPane.getSize();
	}

	public void setSize(Dimension dimension) {
		editorPane.setSize(dimension);
	}

	public void loadUrl(URL url) {
		try {
			editorPane.setPage(url);
		} catch (IOException e) {
			throw new RuntimeException(String.format("Exception while loading %s", url), e);
		}
	}

	public void loadUrl(String url) {
		try {
			editorPane.setPage(url);
		} catch (IOException e) {
			throw new RuntimeException(String.format("Exception while loading %s", url), e);
		}
	}

	public void loadHtml(String html) {
		editorPane.setText(html);
		System.out.println(html);
		onDocumentLoad();
	}


	public void saveAsImage(String file) {
		saveAsImage(new File(file));
	}

	public void saveAsImage(File file) {

		BufferedImage img = getBufferedImage();
		try {
			//final String formatName = FormatNameUtil.formatForFilename(file.getName());
			ImageIO.write(img, "png", file);
		} catch (IOException e) {
			throw new RuntimeException(String.format("Exception while saving '%s' image", file), e);
		}
	}

	protected void onDocumentLoad() {
	}

	public Dimension getDefaultSize() {
		return DEFAULT_SIZE;
	}

	public BufferedImage getBufferedImage() {
		Dimension prefSize = editorPane.getPreferredSize();
		BufferedImage img = new BufferedImage(prefSize.width, editorPane.getPreferredSize().height-3, BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = img.getGraphics();
		editorPane.setSize(prefSize);
		editorPane.paint(graphics);
		return img;
	}

	protected JEditorPane createJEditorPane() {
		final JEditorPane editorPane = new JEditorPane();
		editorPane.setSize(getDefaultSize());
		editorPane.setEditable(false);
		final SynchronousHTMLEditorKit kit = new SynchronousHTMLEditorKit();
		editorPane.setEditorKitForContentType("text/html", kit);
		editorPane.setContentType("text/html");
		editorPane.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("page")) {
					onDocumentLoad();
				}
			}
		});
		return editorPane;
	}
}
