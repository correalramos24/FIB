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

void binari(int i, vector<int>& a){
    if(i == a.size()) pintar(a);
    else{
        a[i] = 0;
        binari(i+1, a);
        a[i] = 1;
        binari(i+1, a);
    }
    return;
}

int main(){
    int n;
    cin >> n;
    vector<int> z(n);
    binari(0, z);
}