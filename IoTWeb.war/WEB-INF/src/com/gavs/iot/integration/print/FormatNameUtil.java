package com.gavs.iot.integration.print;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

public class FormatNameUtil {
	public static Map<String, String> types = new HashMap<String, String>();
	private static final String DEFAULT_FORMAT = "png";

	static {
		types.put("gif", "gif");
		types.put("jpg", "jpg");
		types.put("jpeg", "jpg");
		types.put("png", "png");
	}

	public static String formatForExtension(String extension) {
		final String type = types.get(extension);
		if (type == null) {
			return DEFAULT_FORMAT;
		}
		return type;
	}

	public static String formatForFilename(String fileName) {
		final int dotIndex = fileName.lastIndexOf('.');
		if (dotIndex < 0) {
			return DEFAULT_FORMAT;
		}
		final String ext = fileName.substring(dotIndex + 1);
		return formatForExtension(ext);
	}
	
	public static String getFilename(MultipartFormDataInput input) throws Exception {

		String originalFilename = null;
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("file");
		
		for (InputPart inputPart : inputParts) {
			MultivaluedMap<String, String> header = inputPart.getHeaders();
			String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
			for (String filename : contentDisposition) {
				if ((filename.trim().startsWith("filename"))) {
					String[] name = filename.split("=");
					originalFilename = name[1].trim().replaceAll("\"", "").replaceAll(" ", "");
				}
			}
		}
		return originalFilename;
	}
}
