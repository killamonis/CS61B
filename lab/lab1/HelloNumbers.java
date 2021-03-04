public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int sum = 0;
        System.out.println(" ");
        while (x < 10) {
            System.out.print(sum + " ");
            x = x + 1;
            sum = sum + x;
        }
    }
}