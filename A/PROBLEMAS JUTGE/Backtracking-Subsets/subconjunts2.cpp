#include <iostream>
#include <set>
#include <string>
#include <vector>

using namespace std;

/*
void i_create_sets(vector<string>& words, vector<vector<string>> &result, vector<string> &subset, int index, const int m){  
    //if(subset.size() == m) {
    //    result.push_back(subset);
    //    return;   
    //}
    for (int i = index; i < words.size(); i++){
        subset.push_back(words[i]);
        if(subset.size() == m) {
            result.push_back(subset);
            subset.pop_back();
        }
        i_create_sets(words, result, subset, i+1, m);
        subset.pop_back();
    }
    return;
}

vector<vector<string>> createSets(vector<string> words, int m){
    int i = 0;
    vector<string> sub;
    vector<vector<string>> res;
    i_create_sets(words, res, sub, i, m);
    return res;
}
*/

void i_combinar(int i, vector<bool>& a, vector<vector<bool>> & result, int u){
    if(i == a.size() and u == 0) result.push_back(a);
    else if ((a.size() - i) >= u){
        a[i] = 0; 
        i_combinar(i+1, a, result, u);
        a[i] = 1;
        i_combinar(i+1, a, result, u-1);
    }
}

vector<vector<bool>> combinar(int n, int m){
    vector<bool> v(n, 0);
    vector<vector<bool>> ret;
    i_combinar(0, v, ret, m);
    return ret;
}


int main(){
    int m,n;
    cin >> m >> n;

    vector<string> words;
    string w;

    for(int i = 0; i < n; ++i){
        cin >> w;
        words.push_back(w);
    }

    //vector<vector<string>> r = createSets(words, m);
    vector<vector<bool>> combs = combinar(n, m);
/*    
    for(auto xs: combs){
        for(auto x : xs){
            cout << x << " ";
        }
        cout << endl;
    }
*/
    bool esNouCjt = true;
    for(int i = 0; i < combs.size(); ++i){
        cout << "{";
        for(int j = 0; j < combs[i].size(); ++j){
            if(esNouCjt and combs[i][j]){
                cout << words[j];
                esNouCjt = false;
            } 
            else if(combs[i][j]) cout << "," << words[j];
        }
        cout << "}" << endl;
        esNouCjt = true;
    }

    /*
    for(int i = 0; i < r.size(); ++i){
        cout << "{";
        if(r[i].size() != 0) cout << r[i][0];
        for(int j = 1; j < r[i].size(); ++j){
            cout << "," << r[i][j];
        }
        cout << "}" << endl;
    }
    */
}
