package apriori;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class Test {

	public static void main(String[] args) throws IOException {
		String path = "file/data1.txt";
		String charset = "UNICODE";
		String delemited = "\t";
		double support = 0.5;
		Map<String, ItemSet> map = Data.importFileText(path, charset, delemited, support);
		System.out.println("TID: " + "\t" + "Items: ");
		for (Map.Entry<String, ItemSet> mapEntry : map.entrySet()) {
			System.out.println(mapEntry.getKey() + "\t" + mapEntry.getValue().printDataSet());
		}
		Apriori apri  = new Apriori(map, 2);
		Set<ItemSet> setCi = apri.getCandidates();
		System.out.println("items\tsupport");
		for(ItemSet is : setCi) {
			System.out.println(is);
		}
//		apri.printSetItem1(apri.getCandidates(setCi, 2));
		apri.gen();
	}

}
