package com.example.maddiemin.viewsnews;

import java.util.Comparator;

public class articleComparator<Article> implements Comparator<Article> {
    @Override
    public int compare(Article a, Article b) {
        return a.toString().compareTo(b.toString());
    }
}
