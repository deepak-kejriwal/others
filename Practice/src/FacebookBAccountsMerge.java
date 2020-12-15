import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class FacebookBAccountsMerge {

	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		UnionFind uf = new UnionFind(accounts);

		for (List<String> acts : accounts) {// union
			String parentEmail = acts.get(1); // get parent of first email, can be different than self
			for (int i = 2; i < acts.size(); i++) { // update parent for remaining parent of first email.
				uf.union(parentEmail, acts.get(i));// union
			}
		}

		Map<String, TreeSet<String>> accountGrp = new HashMap<>();
		for (List<String> acts : accounts) {// grouping
			String p = uf.find(acts.get(1)); // get parent of first email, can be different than self
			accountGrp.putIfAbsent(p, new TreeSet<>());
			for (int i = 1; i < acts.size(); i++) {
				accountGrp.get(p).add(acts.get(i));
			}
		}

		Map<String, String> owner = uf.getOwners();

		List<List<String>> res = new ArrayList<>();
		for (String p : accountGrp.keySet()) {
			List<String> emails = new ArrayList<>(accountGrp.get(p));
			emails.add(0, owner.get(p));
			res.add(emails);
		}

		return res;
	}

	class UnionFind {

		private Map<String, String> parents = new HashMap<>();
		Map<String, String> owner = new HashMap<>();

		public UnionFind(List<List<String>> accounts) {
			for (List<String> acts : accounts) {
				for (int i = 1; i < acts.size(); i++) {
					parents.put(acts.get(i), acts.get(i));// Initially all email is different like UnionFind
					owner.put(acts.get(i), acts.get(0)); // owner of email id
				}
			}
		}

		private String find(String s) {
			while (!s.equals(parents.get(s))) {
				s = parents.get(s);
			}
			return s;
		}

		public void union(String p, String q) {
			String parent = find(p);
			String child = find(q);
			parents.put(child, parent);
		}

		public Map<String, String> getOwners() {
			return owner;
		}
	}

	public static void main(String[] args) {
		FacebookBAccountsMerge impl = new FacebookBAccountsMerge();
		List<List<String>> accounts = new ArrayList<>();
		accounts.add(Arrays.asList(new String[] { "David", "David0@m.co", "David4@m.co", "David3@m.co" }));
		accounts.add(Arrays.asList(new String[] { "David", "David5@m.co", "David5@m.co", "David0@m.co" }));
		accounts.add(Arrays.asList(new String[] { "David", "David1@m.co", "David4@m.co", "David0@m.co" }));
		accounts.add(Arrays.asList(new String[] { "David", "David0@m.co", "David1@m.co", "David3@m.co" }));
		accounts.add(Arrays.asList(new String[] { "David", "David4@m.co", "David1@m.co", "David3@m.co" }));

		List<List<String>> res = impl.accountsMerge(accounts);
		System.out.println(res);
	}
}
