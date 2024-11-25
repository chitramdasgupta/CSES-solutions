#include <bits/stdc++.h>
using namespace std;

int main() {
  long long n, m, k;
  cin >> n >> m >> k;

  vector<long long> req(n);
  for (long long i = 0; i < n; ++i) {
    cin >> req[i];
  }
  ranges::sort(req);

  vector<long long> rooms(m);
  for (long long i = 0; i < m; ++i) {
    cin >> rooms[i];
  }
  ranges::sort(rooms);

  long long count = 0;
  long long index = 0;
  long long room_index = 0;
  while (room_index < rooms.size() && index < req.size()) {
    if (req[index] < rooms[room_index] - k) {
      ++index;
    } else if (req[index] > rooms[room_index] + k) {
      ++room_index;
      continue;
    } else {
      ++count;
      ++index;
      ++room_index;
    }
  }

  cout << count;
}
