import java.util.Arrays;
import java.util.List;

public class GoogleAccountsMerge2 {

	public int getNumberOfUniqueUsers(List<Account> accounts) {
		UnionFind uf = new UnionFind(accounts);
		for (int i = 0; i < accounts.size(); ++i) {
			for (int j = 0; j < accounts.size(); ++j) {
				if (i == j)
					continue;
				Account a = accounts.get(i);
				Account b = accounts.get(j);
				if (a.email.equals(b.email) || a.name.equals(b.name)) {
					uf.union(i, j);
				}
			}
		}
		return uf.getCount();
	}

	class Account {
		String name;
		String email;

		Account(String name, String email) {
			this.name = name;
			this.email = email;
		}
	}

	class UnionFind {

		private int[] id;
		int count;

		public UnionFind(List<Account> accounts) {
			id = new int[accounts.size()];
			count = accounts.size();
			for (int i = 0; i < accounts.size(); i++) {
				id[i] = i;
			}
		}

		public int find(int i) {
			while (i != id[i]) {
				id[i] = id[id[i]];// path compression
				i = id[i];
			}
			return i;
		}

		public void union(int p, int q) {
			int i = find(p);
			int j = find(q);
			if (i != j) {
				id[i] = j;
				--count;
			}
		}

		public int getCount() {
			return count;
		}
	}

	public static void main1(String[] args) {
		GoogleAccountsMerge2 impl = new GoogleAccountsMerge2();
		Account[] accounts = { impl.new Account("Joe", "joe@email.com"),
				impl.new Account("Jane", "jane@email.com"), impl.new Account("Joe2", "joe@email.com") };
		int res = impl.getNumberOfUniqueUsers(Arrays.asList(accounts));
		System.out.println(res);
	}
	
	public static void main(String[] args) {
		GoogleAccountsMerge2 impl = new GoogleAccountsMerge2();
		Account[] accounts = { impl.new Account("A", "a@a"),
				impl.new Account("B", "a@a"), impl.new Account("B", "b@b") };
		int res = impl.getNumberOfUniqueUsers(Arrays.asList(accounts));
		System.out.println(res);
	}
}
