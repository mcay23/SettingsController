package yuwono;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class SettingsController {

	private LinkedHashMap<String, String> fields;
	private File path;

	public SettingsController(File file) {
		this.path = file;
		fields = new LinkedHashMap<String, String>();
		load();
	}

	public void add(String key, String value) {
		fields.put(key, value);
	}

	public void remove(String key) {
		fields.remove(key);
	}
	
	public void clear() {
		fields.clear();
	}

	public String get(String key) {
		if (fields.get(key) == null || fields.get(key).trim().length() == 0) {
			return "No Data";
		}
		return fields.get(key);
	}

	public void load() {
		try {
			path.createNewFile();
			BufferedReader in = new BufferedReader(new FileReader(path));
			for (String line = in.readLine(); line != null; line = in.readLine()) {
				String[] kv = line.split("=");
				if (kv.length == 2) {
					fields.put(kv[0], kv[1]);
				} else if (line.trim().length() > 0 && (line.charAt(0) != '#')) {
					throw new RuntimeException();
				}
			}
			in.close();
		} catch (RuntimeException e) {
			System.out.println("Invalid settings file, load failed.");
			fields.clear();
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void write() {
		String temp = "";
		for (Map.Entry<String, String> entry : fields.entrySet()) {
		    temp += entry.getKey() + "=" + entry.getValue() + "\n";
		}
		System.out.println(temp);
		try {
		    FileWriter fw = new FileWriter(path, false);
		    fw.write(temp);
		    fw.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}       

	}

}
