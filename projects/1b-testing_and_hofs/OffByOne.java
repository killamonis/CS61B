public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int xInt = x;
        int yInt = y;
        return (Math.abs(xInt - yInt) == 1);
    }
}
