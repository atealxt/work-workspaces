package test1;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class TopKTerm {

	public static void main(String args[]) {
		
		Map<String,Integer> map = new HashMap<String, Integer>();
		
		String[] strs = article.split(" ");
		for(String term: strs) {
			
			//filter1 stopword
			term = PATTERN_STOPWORDS.matcher(term).replaceAll("");
			
			//filter2 char
			if (!PATTERN_CHAR.matcher(term).matches()) {
				continue;
			}
			term = PATTERN_TRIM.matcher(term).replaceAll("");
			
			//filter3 length
			if (term.length() < 2) {
				continue;
			}
			
			//filter4 case
			term = term.toLowerCase();
			
			if (!map.containsKey(term)) {
				map.put(term, 1);
			} else {
				map.put(term, map.get(term) + 1);
			}			
		}
		
		List<Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		System.out.println(list.subList(0, 10));
	}

	private static String article = "Golden State Warriors win NBA Championship Andre Iguodala #9 and Stephen Curry #30 of the Golden State Warriors looks after their NBA Trophy and MVP Trophy after winning game Six against the Cleveland Cavaliers at the Quicken Loans Arena During Game Six of the 2015 NBA Finals on June 16, 2015 in Cleveland,Ohio. Winners of the last two contests, Steph Curry and the Dubs held a 3-2 series lead going into the game, knowing that a win would see them claim the title for the first time in 40 years. Regular season MVP Curry drew defenders and created space for Andre Iguodala to can an open three as Golden State led by eight in the first quarter. In the second, LeBron James drove in for the host, and the four-time MVP helped the Cavs cut the deficit to six points. James’s fade-away missed, but Tristan Thompson was there for the two-handed putback, and the half ended 45-43 to Golden State. With five minutes left in the third quarter, Curry found Iguodala for a big jam, and the Dubs led by 10 points. Draymond Green then went to work and muscled in for a short bank shot to push his side 15 points ahead. He recorded a triple-double on the night. James then made a steal and raced the other way to for the slam, cutting the lead to seven early in the fourth quarter. But Curry responded with a deep trey leads to maintain a comfortable margin.";
	
	// Just include punctuation marks, I didn't count English stop word, which should be considered in real world.
	private static Pattern PATTERN_STOPWORDS = Pattern.compile("[.\":'?,;!\\-_#(){}\\[\\]<>$*&%+^=]+");
	private static final Pattern PATTERN_CHAR = Pattern.compile("^\\s*?[/@]*[a-zA-Z]+[a-zA-Z/@]*\\s*$");
	private static final Pattern PATTERN_TRIM = Pattern.compile("\\s+");
	// there could still have many other filters, like language specific, spell...	
}

