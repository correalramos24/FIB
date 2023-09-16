#include <iostream>
#include <set>
#include <string>
#include <vector>

using namespace std;

void i_createSets(vector<string>& A, vector<vector<string>> &result, vector<string> &subset, int index){
    result.push_back(subset);
    for(int i = index;i < A.size(); ++i){
        subset.push_back(A[i]); //añadimos el ultimo
        i_createSets(A, result, subset, i+1);
        subset.pop_back(); //quitamos el ultimo
    }
    return;
}
vector<vector<string>> createSets(vector<string> a){
    int i = 0;
    vector<string> sub;
    vector<vector<string>> res;
    i_createSets(a, res, sub, i);
    return res;
}
int main(){
    int n;
    cin >> n;
    vector<string> words;
    string w;
    for(int i = 0;i < n; ++i){
        cin >> w;
        words.push_back(w);
    }
    
    vector<vector<string>> r = createSets(words);
    for(int i = 0; i < r.size(); ++i){
        cout << "{";
        if(r[i].size() != 0) cout << r[i][0];
        for(int j = 1; j < r[i].size(); ++j){
            cout << "," << r[i][j];
        }
        cout << "}" << endl;
    }

}

        