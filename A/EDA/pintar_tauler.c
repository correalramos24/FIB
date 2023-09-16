#include <iostream>
#include <vector>
#include <queue>


using namespace std;

typedef vector<vector<char>> Graf;

int main(){

    int n, m;
    cin >> n >> m;
    char c;
    Graf g(n);
    for(int i = 0; i< n; ++i){
        for(int j = 0; j < m; ++j){     
            cin >> c;
        }
    }

    // # mur
    // . casella buida
    // [A..Z] or [a..z] pintar zona (conjunt maximal de caselles horizontal/vertical)
}
