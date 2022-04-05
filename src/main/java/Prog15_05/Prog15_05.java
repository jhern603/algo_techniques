package Prog15_05;

import java.math.BigInteger;

public class Prog15_05 {
    final BigInteger ZERO = BigInteger.ZERO;
    final BigInteger ONE = BigInteger.ONE;
    final BigInteger TWO = BigInteger.valueOf(2);

    public static void main(String[] args) {
        new Prog15_05();
    }

//    public Prog15_05(){
//      BigInteger n;
//      final BigInteger FIRST = new BigInteger("10000000000000819");;
//      final BigInteger LAST = new BigInteger("170141183460469231731687303715884105727");;
//
//        for (n = FIRST; n.compareTo(LAST) <= 0; n = n.add(BigInteger.ONE))
//            if (isPrime(n)) System.out.println(n);
//    }
//
//    public boolean isPrime(BigInteger n){
//      BigInteger s = n.sqrt();
//
//      if(n.compareTo( java.math.BigInteger.ONE ) == 0) return false;
//      BigInteger i;
//        for ( i = TWO; i.compareTo(s)<=0; i=i.add(ONE)) {
//          if(n.mod(i).compareTo(ZERO) == 0) return false;
//
//        }
//        return true;
//    }
}
