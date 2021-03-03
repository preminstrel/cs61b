public class OffByN implements CharacterComparator {
    private int num;

    public OffByN(int i) {
        num = i;
    }

    public boolean equalChars(char x, char y) {
        if ((int) (x - y) == num || (int) (y - x) == num) {
            return true;
        }
        return false;
    }
}
