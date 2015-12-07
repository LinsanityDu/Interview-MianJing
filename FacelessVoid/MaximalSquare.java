/*Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.*/



// Discussion
public int maximalSquare(char[][] a) {
  if (a == null || a.length == 0 || a[0].length == 0)
    return 0;

  int max = 0, n = a.length, m = a[0].length;

  // dp(i, j) represents the length of the square 
  // whose lower-right corner is located at (i, j)
  // dp(i, j) = min{ dp(i-1, j-1), dp(i-1, j), dp(i, j-1) }
  int[][] dp = new int[n + 1][m + 1];

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= m; j++) {
      if (a[i - 1][j - 1] == '1') {
        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
        max = Math.max(max, dp[i][j]);
      }
    }
  }

  // return the area
  return max * max;
}



/*Now let's try to optimize the above solution. As can be seen, each time when we update size[i][j], we only need size[i][j - 1], size[i - 1][j - 1] (at the previous left column) and size[i - 1][j] (at the current column). So we do not need to maintain the full m*n matrix. In fact, keeping two columns is enough. Now we have the following optimized solution.*/

int maximalSquare(vector<vector<char>>& matrix) {
    int m = matrix.size();
    if (!m) return 0;
    int n = matrix[0].size();
    vector<int> pre(m, 0);
    vector<int> cur(m, 0);
    int maxsize = 0;
    for (int i = 0; i < m; i++) {
        pre[i] = matrix[i][0] - '0';
        maxsize = max(maxsize, pre[i]);
    }
    for (int j = 1; j < n; j++) {
        cur[0] = matrix[0][j] - '0';
        maxsize = max(maxsize, cur[0]);
        for (int i = 1; i < m; i++) {
            if (matrix[i][j] == '1') {
                cur[i] = min(cur[i - 1], min(pre[i - 1], pre[i])) + 1;
                maxsize = max(maxsize, cur[i]);
            }
        }
        swap(pre, cur);
        fill(cur.begin(), cur.end(), 0);
    }
    return maxsize * maxsize;
}

// Discuss
public int maximalSquare(char[][] a) {
    if(a.length == 0) return 0;
    int m = a.length, n = a[0].length, result = 0;
    int[][] b = new int[m+1][n+1];
    for (int i = 1 ; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if(a[i-1][j-1] == '1') {
                b[i][j] = Math.min(Math.min(b[i][j-1] , b[i-1][j-1]), b[i-1][j]) + 1;
                result = Math.max(b[i][j], result); // update result
            }
        }
    }
    return result*result;
}



// Another
 public int maxSquare(int[][] matrix) {
        // write your code here
        int ans = 0;
        int n = matrix.length;
        int m;
        if(n > 0)
            m = matrix[0].length;
        else 
            return ans;
        int [][]res = new int [n][m];
        for(int i = 0; i < n; i++){
            res[i][0] = matrix[i][0];
            ans = Math.max(res[i][0] , ans);
            for(int j = 1; j < m; j++) {
                if(i > 0) {
                    if(matrix[i][j] > 0) {
                        res[i][j] = Math.min(res[i - 1][j],Math.min(res[i][j-1], res[i-1][j-1])) + 1;
                    } else {
                        res[i][j] = 0;
                    }
                    
                }
                else {
                    res[i][j] = matrix[i][j];
                }
                ans = Math.max(res[i][j], ans);
            }
        }
        return ans*ans;
    }