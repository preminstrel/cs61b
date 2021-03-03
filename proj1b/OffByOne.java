public class OffByOne implements CharacterComparator {
    public boolean equalChars(char x, char y) {
        if ((int) (x - y) == 1 || (int) (y - x) == 1) {
            return true;
        }
        return false;
    }
}
