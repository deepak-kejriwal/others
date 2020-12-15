import java.util.Arrays;
import java.util.List;

public class GoogleAccountsMerge {

	public int getNumberOfUniqueUsers(List<Account> accounts) {
		UnionFind uf = new UnionFind(accounts);
		for (int i = 0; i < accounts.size(); ++i) {
			for (int j = 0; j < accounts.size(); ++j) {
				if (i == j)
					continue;
				Account a = accounts.get(i);
				Account b = accounts.get(j);
				if (a.email.equals(b.email) || a.name.equals(b.name)) {
					uf.union(a.id, b.id);
				}
			}
		}
		return uf.getCount();
	}

	class Account {
		String name;
		String email;
		int id;

		Account(String name, String email, int id) {
			this.name = name;
			this.email = email;
			this.id = id;
		}
	}

	class UnionFind {

		private int[] id;
		int count;

		public UnionFind(List<Account> accounts) {
			id = new int[accounts.size()];
			count = accounts.size();
			for (int i = 0; i < accounts.size(); i++) {
				id[i] = accounts.get(i).id;
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

	public static void main(String[] args) {
		GoogleAccountsMerge impl = new GoogleAccountsMerge();
		Account[] accounts = { impl.new Account("Joe", "joe@email.com", 0),
				impl.new Account("Jane", "jane@email.com", 1), impl.new Account("Joe2", "joe@email.com", 2) };
		int res = impl.getNumberOfUniqueUsers(Arrays.asList(accounts));
		System.out.println(res);
	}
}
