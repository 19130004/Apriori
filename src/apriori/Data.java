package apriori;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Data {
	public static Map<String, ItemSet> importFileText(String fileName, String charset, String delemited, double support)
			throws IOException, FileNotFoundException {
		Map<String, ItemSet> dataSet = new HashMap<>();
		File f = new File(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), charset));
		String line = br.readLine();
		while ((line = br.readLine()) != null) {
			String arrObj[] = line.split(delemited);
			ItemSet itemSet = new ItemSet();
			itemSet.support = support;
			if (arrObj.length == 2) {
				String[] items = arrObj[1].split(",");
				for (String s : items) {
					itemSet.itemset.add(Integer.parseInt(s));
				}
			}
			dataSet.put(arrObj[0], itemSet);
		}

		br.close();
		return dataSet;
	}


}
