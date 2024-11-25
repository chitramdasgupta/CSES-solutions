#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

/* Memoization
unordered_map<ll, ll> memo;

ll combo(ll n) {
  if (n < 0) {
    return 0;
  }
  if (n == 0) {
      return 1;
  }
  if (memo.contains(n)) {
    return memo[n];
  }

  ll res = 0;
  for (int i = 6; i >= 1; --i) {
    res += combo(n - i);
  }

  memo[n] = res;
  return res;
}

int main() {
  ll n;
  cin >> n;

  ll res = combo(n);

  cout << res % (ll)(1e9 + 7);
}
*/

int main() {
  ll n;
  cin >> n;

  vector<ll> dp(n + 1, 0);
  dp[0] = 1;

  for (ll i = 1; i <= n; ++i) {
    for (int j = 1; j <= 6 && j <= i; ++j) {
      dp[i] = (dp[i] + dp[i - j]) % (ll)(1e9 + 7);
    }
  }

  cout << dp[n];
}
