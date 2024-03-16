#include <bits/stdc++.h>

using namespace std;

int main() {
  long n;
  cin >> n;

  vector<long> nums;
  for (long i = 0; i < n; ++i) {
    long temp;
    cin >> temp;
    nums.push_back(temp);
  }

  long res = 0;
  for (long i = 1; i < nums.size(); ++i) {
    if (nums[i] >= nums[i - 1]) {
      continue;
    }

    long diff = nums[i - 1] - nums[i];
    nums[i] += diff;
    res += diff;
  }

  cout << res;
}
