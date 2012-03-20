package no.kantega.kriska.conversation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.wicket.util.collections.MultiMap;

public class ConversationRepository2 {

	private static final String DEFAULT_ANSWER = "hei";

	private MultiMap<String, Spoken> map;

	private Spoken lastSpoken;

	public ConversationRepository2() {
		map = new MultiMap<String, ConversationRepository2.Spoken>();
	}

	public String speakSentence(String sentence) {
		Spoken spoken = put(sentence);
		String answer = findAnswer(sentence);
		Spoken answerS = put(answer);
		map.addValue(answer, answerS);
		return answer;
	}

	private String findAnswer(String sentence) {
		if (map.isEmpty()) {
			return DEFAULT_ANSWER;
		}
		if (map.get(sentence) == null) {
			Set<String> keySet = map.keySet();
			Random rand = new Random(System.currentTimeMillis());

			return map.get(
					((String) keySet.toArray()[rand.nextInt(keySet.size())]))
					.get(0).nextSpoken.sentence;

		}
		List<Spoken> candidates = new ArrayList<Spoken>(map.get(sentence));
		if (candidates.isEmpty()) {
			return DEFAULT_ANSWER;
		}

		if (candidates.get(0).nextSpoken == null) {
			return DEFAULT_ANSWER;
		}

		Spoken bestCandidate = candidates.get(0);
		int bestDepth = 0;

		for (Spoken candidate : candidates) {
			if (candidate.nextSpoken != null
					&& candidate.nextSpoken.nextSpoken != null) {

				int candDepthi = findEqualityDepth(candidate, lastSpoken);
				if (candDepthi >= bestDepth) {
					bestCandidate = candidate;
					bestDepth = candDepthi;
				}
			}
		}

		return bestCandidate.nextSpoken.sentence;
	}

	private int findEqualityDepth(Spoken candidate, Spoken fasit) {
		int depth = 0;

		while (fasit != null && candidate != null
				&& fasit.sentence.equals(candidate.sentence)) {
			depth++;
			fasit = fasit.previousSpoken;
			candidate = candidate.previousSpoken;
		}
		return depth;
	}

	private Spoken put(String sentence) {
		Spoken spoken = new Spoken(lastSpoken, sentence);
		if (lastSpoken != null) {
			lastSpoken.setNextSpoken(spoken);
		}
		lastSpoken = spoken;

		return spoken;
	}

	class Spoken {

		private Spoken previousSpoken;
		private Spoken nextSpoken;
		private String sentence;

		public Spoken(Spoken lastSpoken, String sentence) {
			previousSpoken = lastSpoken;
			this.sentence = sentence;
		}

		public void setNextSpoken(Spoken spoken) {
			nextSpoken = spoken;
		}

		public String toString() {
			return sentence;
		}
	}
}
