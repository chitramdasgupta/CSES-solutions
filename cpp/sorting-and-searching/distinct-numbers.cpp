#include <bits/stdc++.h>
using namespace std;

int main() {
  int n;
  cin >> n;

  // unordered_set gives TLE
  // We do not need lookup here, only insertion
  // unordered_set insertion is O(n) in worst case, and O(1) in average case
  // set insertion is always O(log n)
  set<int> seen;
  for (int i = 0; i < n; ++i) {
    int value;
    cin >> value;
    seen.insert(value);
  }

  cout << seen.size();
}
