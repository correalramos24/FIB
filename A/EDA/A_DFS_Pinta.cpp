#include <iostream>
#include <vector>
#include <utility>
#include <queue>

using namespace std;

using VC = vector<char>;
using MC = vector<VC>;

int n, m;
MC g;

void printar_taulell(){
    for(int i = 0; i < n; ++i){
        for(int j = 0; j <m; ++j){
            cout << g[i][j];
        }
        cout << endl;
    }
    cout << endl;
}

void pintar_zona(int i, int j){
    char r = g[i][j];
    cout << "pintando la zona " << r << endl;
    cout << i << " " << j << endl;
    //printar_taulell();
    int ii = i+1;
    while(g[ii][j] == '.') {
        g[ii][j] = r;
        ii += 1;
    }
    //printar_taulell();
    ii = i-1;
    while(g[ii][j] == '.') {
        g[ii][j] = r;
        ii -= 1;
    }
    int jj = j+1;
    while(g[i][jj] == '.') {
        g[i][jj] = r;
        jj += 1;
    }
    jj = j-1;
    while(g[i][jj] == '.') {
        g[i][jj] = r;
        jj -= 1;
    }
}

void pintar_zona_bfs(int i, int j){
    queue<pair<int, int>> q;
    char color = g[i][j];
    q.push(make_pair(i, j));
    
    while(! q.empty()){
        pair<int, int> actual = q.front();
        q.pop();
        for(int i = 0; i < 4; ++i){
            pair<int,int> newcurrent = actual;
            if (i==0) newcurrent.first++;
            else if (i==1) newcurrent.first--;
            else if (i==2) newcurrent.second++;
            else if (i==3) newcurrent.second--;
            
            if(g[newcurrent.first][newcurrent.second] == '.'){
                q.push(newcurrent);
                g[newcurrent.first][newcurrent.second] = color;
            }
        }
        
    }
}

int main(){
    while(cin >> n >> m){
        g = MC(n, VC(m));
        char aux;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                cin >> aux;
                g[i][j] = aux;
            }
        }
        for(int i = 0;i < n; ++i){
            for(int j = 0; j <m; ++j){
                if ((g[i][j] >= 'A' && g[i][j] <= 'Z') || (g[i][j] >= 'a' && g[i][j] <= 'z')){
                    pintar_zona_bfs(i, j);
                }
            }
        }
        printar_taulell();
    }
    
}