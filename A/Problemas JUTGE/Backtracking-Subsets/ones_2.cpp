#include <iostream>
#include <set>
#include <string>
#include <vector>

using namespace std;
void pintar(const vector<int>& a){
    cout << a[0];
    for(int i = 1 ;i < a.size(); ++i){
        cout << " " << a[i];
    }
    cout << endl;
}

void binari(int i, vector<int>& a, int u){
    if(i == a.size() and u == 0) pintar(a);
    else if ((a.size() - i) >= u){
        a[i] = 0; 
        binari(i+1, a, u);
        
        a[i] = 1;
        binari(i+1, a, u-1);
        
    }
}

int main(){
    int n , u;
    cin >> n >> u;
    vector<int> z(n, 0);
    binari(0, z, u);
}