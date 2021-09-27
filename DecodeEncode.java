import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DecodeEncode {
  public static void main(String[] args) {
    String input = "aaaaa";
    String encoded = "bcbcbcbcbc";
    String decodeInput = "bcbc";
    String[] decoded = new String[] { "aa", "ae", "ea", "ee" };
    List<String> answer = Arrays.asList(decoded);
    // key = {‘a’: ‘bc’, ‘d’:‘cd’, ‘e’:‘bc’}
    Map<Character, String> dict = new HashMap<Character, String>();
    dict.put('a', "bc");
    dict.put('d', "cd");
    dict.put('e', "bc");

    // encode
    System.out.println(encode(input, dict).equals(encoded));

    // decode
    List<String> ans = Arrays.asList(decode(decodeInput, dict));
    System.out.println(ans.containsAll(answer));

  }

  static String encode(String input, Map<Character, String> dict) {
    String encoded = "";
    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      encoded += dict.get(c);
    }
    return encoded;
  }

  static String[] decode(String encoded, Map<Character, String> dict) {
    HashSet<String> outputs = new HashSet<String>();

    // reverse map
    HashMap<String, ArrayList<String>> decodeMap = new HashMap<String, ArrayList<String>>();
    for (Character key : dict.keySet()) {
      if (decodeMap.containsKey(dict.get(key))) {
        decodeMap.get(dict.get(key)).add(Character.toString(key));
      } else {
        ArrayList<String> al = new ArrayList<String>();
        al.add(Character.toString(key));
        decodeMap.put(dict.get(key), al);
      }
    }

    ArrayList<String> firstLetter = decodeMap.get(encoded.substring(0, 2));
    for (String letter : firstLetter) {
      outputs.add(letter);
    }

    // get possible combinations
    for (int i = 2; i < encoded.length(); i = i + 2) { // loop thru letters
      ArrayList<String> decodes = decodeMap.get(encoded.substring(i, i + 2));

      HashSet<String> olds = (HashSet<String>) outputs.clone();
      for (String sol : olds) { // loop thru basis
        for (String next : decodes) { // add new additions
          String temp = sol;
          temp += next;
          outputs.add(temp);
        }
      }
      for (String sol : olds) { // get rid of shorter ones
        outputs.remove(sol);
      }
    }

    // format output
    String[] arr = new String[outputs.size()];
    int i = 0;
    for (String output : outputs) {
      arr[i++] = output;
    }
    return arr;
  }
}