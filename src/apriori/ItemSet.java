package apriori;

import java.util.HashSet;
import java.util.Set;

public class ItemSet {
	Set<Integer> itemset;
	double support;

	public ItemSet(Set<Integer> itemset, double support) {
		super();
		this.itemset = itemset;
		this.support = support;
	}

	public ItemSet() {
		itemset = new HashSet<>();
		support = 0;
	}

	@Override
	public String toString() {
		String result = "";
		for (Integer i : itemset) {
			result += i + ",";
		}
		result = result.substring(0, result.length() - 1);
		result+=" : "+support;
		return result;

	}
	public String printDataSet() {
		String result = "";
		for (Integer i : itemset) {
			result += i + ",";
		}
		result = result.substring(0, result.length() - 1);
		return result;

	}
}
