/**
 * Object  that decides whether words are palindromes or not.
 */
public class Palindrome {
    /**
     * Converts a string into a Deque comprised of its letters.
     * @param word being transformed into a Character Deque
     * @return Character Deque
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> letters = new LinkedListDeque<>();
        if (word == null) {
            return null;
        }
        // @source: https://tinyurl.com/y5xk58u4
        for (char c: word.toCharArray()) {
            letters.addLast(c);
        }
        return letters;
    }

    /**
     * Determines whether a word is a palindrome.
     * @param word being checked for its palindrome-ness
     * @return true if a palindrome, false if not
     */
    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        if (word.length() <= 1) {
            return true;
        }
        Deque<Character> maybePal = wordToDeque(word);
        while (maybePal.size() > 1) {
            if (maybePal.removeFirst() != maybePal.removeLast()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Overidden version of isPalindrome that determines whether a word meets
     * the palindrome requirements of the CharacterComparator object.
     * @param word being checked for its palindrome-ness
     * @param cc CharacterComparator being used on the characters of the word
     * @return true if a palindrome, false if not
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        if (word.length() <= 1) {
            return true;
        }
        Deque<Character> maybePal = wordToDeque(word);
        while (maybePal.size() > 1) {
            if (!cc.equalChars(maybePal.removeFirst(), maybePal.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
