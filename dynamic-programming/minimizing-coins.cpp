#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

const ll INF = 1e9;
unordered_map<ll, ll> memo;

/* Memoized solution
ll num_coins(ll target, const vector<ll> &coins) {
  if (target < 0) {
    return INF;
  }

  if (target == 0) {
    return 0;
  }

  if (memo.contains(target)) {
    return memo[target];
  }

  ll res = INF;
  for (ll coin : coins) {
    res = min(res, num_coins(target - coin, coins) + 1);
  }

  memo[target] = res;
  return res;
}

int main() {
    ll n, target;
    cin >> n >> target;

    vector<ll> coins(n);
    for (ll i = 0; i < n; ++i) {
        cin >> coins[i];
    }

    ll res = num_coins(target, coins);

    cout << (res == INF ? -1 : res);
}
*/

int main() {
  ll n, target;
  cin >> n >> target;

  vector<ll> coins(n);
  for (ll i = 0; i < n; ++i) {
    cin >> coins[i];
  }

  vector<ll> dp(target + 1, 1e9);
  dp[0] = 0;

  for (ll i = 1; i <= target; i++) {
    for (ll &coin : coins) {
      if (i - coin >= 0) {
        dp[i] = min(dp[i], dp[i - coin]+1);
      }
    }
  }

  cout << (dp[target] == 1e9 ? -1 : dp[target]);
}
