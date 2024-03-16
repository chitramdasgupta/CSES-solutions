#include <bits/stdc++.h>

using namespace std;

int main() {
  long n;
  cin >> n;

  long total = n * (n + 1) / 2;
  long partial = 0;
  for (int i = 0; i < n - 1; ++i) {
    long input;
    cin >> input;
    partial += input;
  }

  cout << total - partial;
}