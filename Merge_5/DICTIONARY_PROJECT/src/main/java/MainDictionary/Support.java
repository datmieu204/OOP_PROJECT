package MainDictionary;

import java.util.ArrayList;

public class Support {
    private static void merge(ArrayList<Word> list_word, int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;

        ArrayList<Word> L = new ArrayList<Word>();
        ArrayList<Word> R = new ArrayList<Word>();

        for(int i = 0; i < n1; i++){
            L.add(list_word.get(l + i));
        }

        for(int j = 0; j < n2; j++){
            R.add(list_word.get(m + 1 + j));
        }

        int i = 0;
        int j = 0;
        int k = l;

        while(i < n1 && j < n2){
            if(L.get(i).getWordTarget().compareTo(R.get(j).getWordTarget()) <= 0){
                list_word.set(k, L.get(i));
                i++;
            } else {
                list_word.set(k, R.get(j));
                j++;
            }
            k++;
        }

        while(i < n1){
            list_word.set(k, L.get(i));
            i++;
            k++;
        }

        while(j < n2){
            list_word.set(k, R.get(j));
            j++;
            k++;
        }
    }

    public static void mergeSort(ArrayList<Word> list_word, int l, int r){
        if(l < r){
            int m = l + (r - l) / 2;

            mergeSort(list_word, l, m);
            mergeSort(list_word, m + 1, r);
            merge(list_word, l, m, r);
        }
    }

    public static int binarySearch(String word_target, ArrayList<Word> list_word){
        int l = 0;
        int r = list_word.size() - 1;

        while(l <= r){
            int m = l + (r - l) / 2;

            if(list_word.get(m).getWordTarget().compareTo(word_target) == 0){
                return m;
            }

            if(list_word.get(m).getWordTarget().compareTo(word_target) < 0){
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return -1;
    }

}
