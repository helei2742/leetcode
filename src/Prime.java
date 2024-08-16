
public class Prime {

    private boolean[] isNotPrime;
    private int[] prime;
    public void fn(int n, int k){
        isNotPrime = new boolean[100000000];
        prime = new int[n/2];
        int idx = 0;
        for(int i = 2; i <= n; i++){
            if(!isNotPrime[i]){
                prime[idx++] = i;
            }
            for (int j = 0; j < idx && prime[j] * i < n; j++) {
                isNotPrime[i*prime[j]] = true;
                if(i%prime[j] == 0) break;
            }
            if(idx == k) break;
        }
        for (int i = 0; i < idx; i++) {
            System.out.println(prime[i]);
        }
    }

    public static void main(String[] args) {
        Prime p = new Prime();
        p.fn(100000, 5);
    }
}
