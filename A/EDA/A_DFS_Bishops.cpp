#include <iostream>
#include <string>
#include <vector>
#include <queue>
#include <utility>

using namespace std;

typedef vector<vector<string>> Graf;
typedef vector<vector<bool>> Visited;
typedef pair<int, int> pos;

//contar los bishops totales 
//bishop en diagonales solo: 
//i+1, j+1
//i-1, j-1
//i+1, j-1
//i-1, j+1
int convert_string_to_int(string s){
    int x = 0;
    for(int i = 0; i < s.size(); ++i){
        x = x*10 + (s[i] - '0');
    }
    return x;
}
bool DFS_bishops(int n, int m, Graf g){
    
    Visited vistos = Visited(n, vector<bool>(m, false)); //matriz de visitados.
    int ratio_intra = -1; //entre componentes tmb tiene que tener el mismo numero.
    for(int i = 0; i < g.size(); ++i){
        for(int j = 0; j < g[0].size(); ++j){
            if(! vistos[i][j] && g[i][j] != "X"){
                queue<pos> q;
                q.push(make_pair(i,j));
                int count = 0;
                int suma_componente = 0;
                while(! q.empty()){
                    pos p = q.front(); q.pop();
                    vistos[i][j] = true;
                    if(p.first != 0 && p.second != 0 && g[p.first+1][p.second-1] != "X" && ! vistos[p.first+1][p.second+1])
                        q.push(pos(p.first + 1, p.second - 1));
                    if(p.first != g.size() - 1 && p.second != 0 && g[p.first+1][p.second-1] != "X" && ! vistos[p.first+1][p.second-1])
                        q.push(pos(p.first + 1, p.second - 1));
                    if(p.first != 0 && p.second != g[0].size()-1 && g[p.first-1][p.second+1] != "X" && ! vistos[p.first-1][p.second+1])
                        q.push(pos(p.first - 1, p.second + 1));
                    if(p.first != g.size() - 1 && p.second != g[0].size()-1 && g[p.first-1][p.second+1] != "X" && ! vistos[p.first+1][p.second+1])
                        q.push(pos(p.first + 1, p.second + 1));
                    ++count;
                    suma_componente += convert_string_to_int(g[p.first][p.second]);
                }
                if(suma_componente%count != 0) return false;
                int ratio_bishop_componente = suma_componente/count;
                if(ratio_intra == -1) ratio_intra = ratio_bishop_componente; //first DFS
                else if(ratio_intra != ratio_bishop_componente) return false;
            }
        }
    }
    return true;
}

int main(){
    int t, n, m;
    cin >> t;
    for(int n_case = 0; n_case < t; ++n_case){
        cin >> n >> m;
        Graf g = Graf(n, vector<string>(m));
        string s;
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < m; ++j){
                cin >> g[i][j];
            }
        }
        if (DFS_bishops(n, m, g)) 
            cout << "Case " << n_case << ": yes" << endl;
        else 
            cout << "Case " << n_case << ": no" << endl;
    }
    
}