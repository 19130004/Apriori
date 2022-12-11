package apriori;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Apriori {
	Map<String, ItemSet> dataSet;
	double support;
	Set<ItemSet> setL;

	public Apriori(Map<String, ItemSet> dataSet, double support) {
		super();
		this.dataSet = dataSet;
		this.support = support;
		setL = new HashSet<>();

	}

	public Set<ItemSet> getCandidates() {
		Set<ItemSet> setList = new HashSet<>();
		for (ItemSet is : this.dataSet.values()) {
			for (Integer i : is.itemset) {
				int count = countFrequent(i);
				Set<Integer> setItem = new HashSet<>();
				setItem.add(i);
				ItemSet itemSet = new ItemSet(setItem, count);
				if (!checkContain(itemSet, setList)) {
					setList.add(itemSet);
				}

			}
		}
		setList = checkMinSupport(setList);
		return setList;
	}

	private Set<ItemSet> checkMinSupport(Set<ItemSet> setList) {
		Set<ItemSet> re = new HashSet<>();
		for (ItemSet is : setList) {
			if (is.support >= this.support) {
				re.add(is);
			}
		}
		return re;
	}

	private boolean checkContain(ItemSet itemSet, Set<ItemSet> setList) {
		for (ItemSet is : setList) {
			if (is.itemset.equals(itemSet.itemset)) {
				return true;
			}
		}
		return false;
	}

	private int countFrequent(Integer i) {
		int count = 0;
		for (ItemSet is : this.dataSet.values()) {
			for (Integer k : is.itemset) {
				if (k == i)
					count++;

			}
		}
		return count;
	}

	public Set<Set<Integer>> getSubnet(Set<Integer> set) {
		Set<Set<Integer>> setRes = new HashSet<>();
		int arr[] = new int[set.size()];
		int count = 0;

		for (Integer i : set) {
			arr[count] = i;
			count++;
		}

		int n = arr.length;
		for (int i = 0; i < (1 << n); i++) {
			Set<Integer> setA = new HashSet<>();
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0) {
					setA.add(arr[j]);
				}

			}
			if (setA.size() == set.size() - 1) {
				setRes.add(setA);
			}
		}

		return (setRes.isEmpty()) ? null : setRes;
	}

	public Set<Set<Integer>> getCandidates(Set<ItemSet> set, int size) {
		Set<Set<Integer>> setRes = new HashSet<>();
		int arr[] = new int[set.size()];
		int count = 0;
		for (ItemSet is : set) {
			for (Integer i : is.itemset) {
				arr[count] = i;
				System.out.println(arr[count]);
			}
			count++;
		}
		int n = arr.length;
		for (int i = 0; i < (1 << n); i++) {
			Set<Integer> setA = new HashSet<>();
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0) {
					setA.add(arr[j]);
				}

			}
			if (setA.size() == size) {
				setRes.add(setA);
			}
		}

		return (setRes.isEmpty()) ? null : setRes;
	}

	public Set<ItemSet> gen() {
		System.out.println("buoc phat sinh lan thu 1");
		Set<ItemSet> setCan = getCandidates();
		this.setL.addAll(setCan);
		printSetItem(this.setL);
		int size = 2;
		Set<Set<Integer>> setGen = new HashSet<>();
		while ((setGen = getCandidates(setCan, size)) != null) {
			System.out.println("Buoc phat sinh thu:" + size );
			Set<ItemSet> setItem = new HashSet<>();
			for (Set<Integer> item : setGen) {
				if (has_subnet(item)) {
					int count = countFrequent(item);
					setItem.add(new ItemSet(item, count));
				}
			}
			if(size == 2) {
				setItem = checkMinSupport(setItem);
			}
			this.setL.addAll(setItem);
			printSetItem(this.setL);
			size++;
		}
		return setL;

	}

	private int countFrequent(Set<Integer> item) {
		int count = 0;
		for (ItemSet it : this.dataSet.values()) {
			if (it.itemset.containsAll(item))
				count++;
		}
		return count;
	}

	private boolean has_subnet(Set<Integer> item) {
		Set<Set<Integer>> setSubnet = getSubnet(item);
		int count = 0;
		for (Set<Integer> it : setSubnet) {
			if (checkSunet(it)) {
				count++;
			}
		}
		return (count == setSubnet.size()) ? true : false;
	}

	private boolean checkSunet(Set<Integer> it) {
		for (ItemSet item : this.setL) {
			if (item.itemset.equals(it))
				return true;
		}
		return false;
	}

	public void printSetItem1(Set<Set<Integer>> set) {
		for (Set<Integer> is : set) {
			System.out.println(is);
		}
	}

	public void printSetItem(Set<ItemSet> set) {
		for (ItemSet is : set) {
			System.out.println(is);
		}
	}
}
