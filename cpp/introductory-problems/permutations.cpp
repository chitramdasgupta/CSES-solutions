#include <bits/stdc++.h>

using namespace std;

int main() {
  long n;
  cin >> n;

  if (n == 2 || n == 3) {
    cout << "NO SOLUTION";
    return 0;
  }

  int val = 2;
  while (val <= n) {
    cout << val << " ";
    val += 2;
  }

  val = 1;
  while (val <= n) {
    cout << val << " ";
    val += 2;
  }

  return 0;
}
