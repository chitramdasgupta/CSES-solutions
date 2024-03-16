#include <bits/stdc++.h>

using namespace std;

long longestRepetition(string &dna) {
  int count = 1;
  int res = 1;
  for (int i = 0; i < dna.size() - 1; ++i) {
    if (dna[i] == dna[i + 1]) {
      ++count;
    } else {
      count = 1;
    }

    res = max(res, count);
  }

  return res;
}

int main() {
  string dna;
  cin >> dna;

  long res = longestRepetition(dna);
  cout << res;
}