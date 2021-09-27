public class ReverseList {
  // Swap the first half of the array with the second half.
  //
  // Args:
  // arr: the array to be reversed
  // n: The length of the array.
  // Returns:
  // Nothing is being returned.
  public static void main(String[] args) {
    int[] arr = new int[] { 1, 2, 3 };
    int n = arr.length;
    int t;
    for (int i = 0; i < n / 2; i++) {
      t = arr[i];
      arr[i] = arr[n - i - 1];
      arr[n - i - 1] = t;
    }

    for (int j = 0; j < n; j++) {
      System.out.println(arr[j]);
    }
  }
}
