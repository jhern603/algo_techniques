//package Prog17_03;
//
//public class Prog17_03{
//public int lcsLength(String s, String r){
//  char[] charS = s.toCharArray();
//  char[] charR = r.toCharArray();
//
//  return lcsLength( charS, charR, charS.length, charR.length );
//}
//private int lcsLength(char[] s, char[] r, int n, int m){
//  if(n == 0 || m == 0) return 0;
//  else{
//    if(s[n-1]==r[m-1]){
//      return 1+lcsLength( s, r, n-1, m-1 );
//    }
//    else
//      return Math.max(lcsLength( s, r, n-1, m ), lcsLength( s,r,n,m-1 ));
//  }
//}
//
//
//private int lcsLengthDP(char[] s, char[] r, int n, int m){
//  int[][] table = new int[n+1][m+1];
//
//        for (int i = 0;i <= n; i++) {
//            for (int j = 0; j <= m; j++) {
//              if(i ==0 || j ==0)
//                table[i][j] == 0;
//              else
//                if(s[i-1]==r[j-1])
//            }
//        }
//}
//}
