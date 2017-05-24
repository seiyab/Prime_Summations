import java.util.Iterator;

public class PrimeSummations {
    public static void main(String[] args){
        int n = 100;
        int[] psns = primeSummationNumbers(n);
        for(int i=1; i<n; i++){
            if(psns[i] > 5000){
                System.out.println(i);
                break;
            }
        }
    }

    public static int[] primeSummationNumbers(int n){
        int[] dp = new int[n+1];
        // dp[i] holds 'How many different ways are possible to write i as the sum of {x | x<=p, x is a prime}'
        for(int p : new Primes(n)){
            dp[p] +=1;
            for(int i=p+1; i<dp.length; i++){
                dp[i] += dp[i-p];
            }
        }
        return dp;
    }
}

class Primes implements Iterable<Integer> {
    /*
     * Usage:
     * for(int p : new Primes(10)){
     *     System.out.println(p);
     * }
     *
     * Outputs:
     * 2
     * 3
     * 5
     * 7
     *
     * */
    private final int n;

    Primes(int n){
        this.n = n;
    }

    public PrimesIter iterator(){
        return new PrimesIter();
    }

    class PrimesIter implements Iterator<Integer> {
        private int i=2;

        @Override
        public boolean hasNext() {
            while(!isPrime(this.i)){
                this.i++;
            }
            return this.i < n;
        }

        @Override
        public Integer next(){
            while(!isPrime(this.i)){
                this.i++;
            }
            return i++;
        }

        private boolean isPrime(int n){
            boolean out = true;
            for(int i=2; i<=Math.sqrt(n); i++){
                if(n%i==0){
                    out = false;
                    break;
                }
            }
            return out;
        }
    }
}
