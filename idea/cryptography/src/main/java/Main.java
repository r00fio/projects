/**
 * Created by 8lackC on 6/11/15.
 */
public class Main {

    public static void main(String[] args) {
        String s = "Hello World";
        String key = "key";
        byte[] encrypted = new byte[s.getBytes().length];
        byte[] decrypted = new byte[s.getBytes().length];
        int cnt = 0;
        byte[] keyBytes = key.getBytes();
        for (byte b : s.getBytes()) {
            byte k = keyBytes[cnt % 3];
            encrypted[cnt] = (byte)(b ^ k);
            decrypted[cnt] = (byte)(encrypted[cnt] ^ k);
            cnt++;
        }
        System.out.println(new String(encrypted));
        System.out.println(new String(decrypted));
    }

}
