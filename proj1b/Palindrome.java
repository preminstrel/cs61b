public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> x = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            char t = word.charAt(i);
            x.addLast(t);
        }
        return x;
    }

    public boolean isPalindrome(String word) {
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }
        Deque<Character> x = wordToDeque(word);
        while (!x.isEmpty() && x.size() != 1) {
            if (x.removeFirst() != x.removeLast()) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindromeHelper(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else {
            Character first = deque.removeFirst();
            Character last = deque.removeLast();
            if (cc.equalChars(first, last)) {
                return isPalindromeHelper(deque, cc);
            } else {
                return false;
            }
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper(deque, cc);
    }
}
